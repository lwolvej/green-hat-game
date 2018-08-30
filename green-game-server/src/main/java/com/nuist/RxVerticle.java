package com.nuist;

import com.nuist.common.RestfulApiVerticle;
import com.nuist.entity.Score;
import com.nuist.entity.User;
import com.nuist.service.JdbcService;
import com.nuist.service.JdbcServiceImpl;
import io.reactivex.Completable;
import io.vertx.core.Future;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;

import java.util.Objects;

/**
 * @author LwolveJ
 */
public class RxVerticle extends RestfulApiVerticle {

    private static final String HOST = "0.0.0.0";

    private static final int PORT = 13152;

    private JdbcService service;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        enableCrossSupport(router);

        router.get(GET_USER).handler(this::handleGetUser);
        router.get(GET_HISTORY).handler(this::handleGetHistory);
        router.get(GET_LIST).handler(this::handleGetList);
        router.post(POST_USER).handler(this::handleCreateUser);
        router.post(POST_SCORE).handler(this::handleCreateScore);

        String host = config().getString("http.address", HOST);
        int port = config().getInteger("http.port", PORT);

        initService().andThen(createHttpServer(router, host, port))
                .subscribe(startFuture::succeeded, startFuture::fail);
    }

    private void handleCreateUser(RoutingContext context) {
        try {
            JsonObject object = context.getBodyAsJson();
            if (!Objects.isNull(object)) {
                final User user = new User(object);
                sendResponse(context, service.insertUser(user), Json::encodePrettily, this::create);
                return;
            }
            badRequest(context);
        } catch (DecodeException e) {
            badRequest(context, e);
        }
    }

    private void handleCreateScore(RoutingContext context) {
        try {
            JsonObject object = context.getBodyAsJson();
            if (!Objects.isNull(object)) {
                final Score score = new Score(object);
                sendResponse(context, service.insertScore(score), Json::encodePrettily, this::create);
                return;
            }
            badRequest(context);
        } catch (DecodeException e) {
            badRequest(context, e);
        }
    }

    private void handleGetUser(RoutingContext context) {
        String userName = context.request().getParam("userName");
        if (userName == null) {
            badRequest(context);
            return;
        }
        sendResponse(context, service.getUser(userName), Json::encodePrettily);
    }

    private void handleGetHistory(RoutingContext context) {
        String userName = context.request().getParam("userName");
        if(userName == null) {
            badRequest(context);
            return;
        }
        sendResponse(context, service.getHistory(userName), Json::encodePrettily);
    }

    private void handleGetList(RoutingContext context) {
        String num = context.request().getParam("num");
        sendResponse(context, service.getList(num), Json::encodePrettily);
    }

    private Completable initService() {
        service = new JdbcServiceImpl(vertx);
        return service.initData();
    }

    private static final String GET_USER = "/api/user/:userName";
    private static final String GET_HISTORY = "/api/score/:userName";
    private static final String POST_USER = "/api/user";
    private static final String GET_LIST = "/api/score/list/:num";
    private static final String POST_SCORE = "/api/score";
}
