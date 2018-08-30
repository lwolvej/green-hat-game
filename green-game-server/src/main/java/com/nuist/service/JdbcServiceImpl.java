package com.nuist.service;

import com.nuist.entity.JsonResult;
import com.nuist.entity.Score;
import com.nuist.entity.User;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.jdbc.JDBCClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LwolveJ
 */
public class JdbcServiceImpl implements JdbcService {

    private final Vertx vertx;


    private final JDBCClient client;

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public JdbcServiceImpl(Vertx vertx) {
        this.vertx = vertx;
        this.client = JDBCClient.createShared(vertx,
                new JsonObject()
                        .put("provider_class", "io.vertx.ext.jdbc.spi.impl.HikariCPDataSourceProvider")
                        .put("service.type", "jdbc")
                        .put("jdbcUrl", "jdbc:mysql://localhost:3306/game_server?characterEncoding=UTF-8&useSSL=false")
                        .put("driverClassName", "com.mysql.cj.jdbc.Driver")
                        .put("username", "root")
                        .put("password", "12345678")
                        .put("maximumPoolSize", 30)
                        .put("minimumIdle", 20)
                        .put("cachePrepStmts", true)
                        .put("prepStmtCacheSize", 250)
                        .put("prepStmtCacheSqlLimit", 2048));
    }

    @Override
    public Completable initData() {
        return client.rxGetConnection()
                .flatMapCompletable(sqlConnection -> sqlConnection
                        .rxExecute(SQL_INIT)
                        .doOnTerminate(sqlConnection::close)
                );
    }

    @Override
    public Single<JsonResult> insertUser(User user) {
        JsonArray params = new JsonArray()
                .add(user.getUserName())
                .add(user.getPassword());
        return client.rxUpdateWithParams(INSERT_USER, params)
                .map(e -> new JsonResult(true, user, "register success"));
    }

    @Override
    public Single<JsonResult> insertScore(Score score) {
        JsonArray params = new JsonArray()
                .add(score.getUserName())
                .add(FORMAT.format(new Date()))
                .add(score.getNumber());
        return client.rxUpdateWithParams(INSERT_SCORE, params)
                .map(e -> new JsonResult(true, "", "success"));
    }

    @Override
    public Maybe<User> getUser(String userName) {
        return client.rxQueryWithParams(GET_USER, new JsonArray().add(userName))
                .map(ResultSet::getRows)
                .toObservable()
                .flatMapIterable(e -> e)
                .singleElement()
                .map(User::new);
    }

    @Override
    public Single<List<Score>> getHistory(String userName) {
        return client.rxQueryWithParams(GET_HISTORY, new JsonArray().add(userName))
                .map(ar -> ar.getRows()
                        .stream()
                        .map(Score::new)
                        .collect(Collectors.toList())
                );
    }

    @Override
    public Single<List<Score>> getList(String num) {
        return client.rxQueryWithParams(GET_LIST, new JsonArray().add(Integer.valueOf(num)))
                .map(ar -> ar.getRows()
                        .stream()
                        .map(Score::new)
                        .collect(Collectors.toList())
                );
    }

    private static final String SQL_INIT = "CREATE TABLE IF NOT EXISTS `user` ( " +
            " `user_name` VARCHAR(40) NOT NULL, " +
            " `password` VARCHAR(40) NOT NULL, " +
            " PRIMARY KEY(`user_name`)); ";

    private static final String GET_LIST = "SELECT user_name,clock,number FROM score ORDER BY number DESC LIMIT 0, ?";

    private static final String GET_HISTORY = "SELECT user_name,clock,number FROM score WHERE user_name=? ORDER BY number DESC";

    private static final String GET_USER = "SELECT * FROM user WHERE user_name=?";

    private static final String INSERT_SCORE = "INSERT INTO score(user_name, clock, number) VALUES(?, ?, ?)";

    private static final String INSERT_USER = "INSERT INTO user(user_name, password) VALUES(?, ?)";
}
