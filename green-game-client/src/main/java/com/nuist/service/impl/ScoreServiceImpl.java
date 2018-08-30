package com.nuist.service.impl;

import com.nuist.entity.Score;
import com.nuist.exception.MyException;
import com.nuist.service.ScoreService;
import com.nuist.util.HttpUtil;
import com.nuist.util.JsonUtil;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.util.List;

/**
 * 分数服务实现
 *
 * @author LwolveJ
 */
public class ScoreServiceImpl implements ScoreService {

    private OkHttpClient client = HttpUtil.getCLIENT();

    private static final String GET_HISTORY = "http://47.92.195.140:13152/api/score/";

    private static final String GET_LIST = "http://47.92.195.140:13152/api/score/list/10";

    private static final String ADD_SCORE = "http://47.92.195.140:13152/api/score";

    @Override
    public List<Score> getHistoryScore(String userName) {
        Response response = HttpUtil.doGet(GET_HISTORY + userName, client);
        return getList(response);
    }

    @Override
    public List<Score> getRankList() {
        Response response = HttpUtil.doGet(GET_LIST, client);
        return getList(response);
    }

    @Override
    public Boolean addScore(String userName, String score) {
        Response response = HttpUtil.doPost2(ADD_SCORE, userName, score, client);
        if (response.body() != null) {
            String json;
            try {
                json = response.body().string();
            } catch (Exception e) {
                throw new MyException("Exception happen when add score!");
            }
            return JsonUtil.getScoreInfoFromJson(json);
        } else {
            throw new MyException("No source found!");
        }
    }

    private List<Score> getList(Response response) {
        if (response.body() != null) {
            String json;
            try {
                json = response.body().string();
            } catch (Exception e) {
                throw new MyException("Exception happen when get list!");
            }
            return JsonUtil.getRankInfoFromJson(json);
        } else {
            throw new MyException("No source found!");
        }
    }
}
