package com.nuist.service;

import com.nuist.entity.JsonResult;
import com.nuist.entity.Score;
import com.nuist.entity.User;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.List;


/**
 * @author LwolveJ
 */
public interface JdbcService {

    Completable initData();

    Single<JsonResult> insertUser(User user);

    Single<JsonResult> insertScore(Score score);

    Maybe<User> getUser(String userName);

    Single<List<Score>> getHistory(String userName);

    Single<List<Score>> getList(String number);
}
