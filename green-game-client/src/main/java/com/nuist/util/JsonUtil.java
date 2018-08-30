package com.nuist.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nuist.entity.Score;
import com.nuist.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Json解析工具类
 *
 * @author 陈裕豪
 */
public class JsonUtil {
    //登陆json解析
    public static User getLoginInfoFromJson(String json) {
        String password;
        String userName;
        JSONObject object = JSONObject.parseObject(json);
        if (object.get("userName") == null) {
            return null;
        } else {
            userName = object.get("userName").toString();
        }
        password = object.get("password").toString();
        User user = new User.Builder()
                .setUserName(userName)
                .setPassword(password)
                .build();
        return user;
    }

    //注册json解析
    public static User getUserInfoFromJson(String json) {
        String password;
        String userName;
        JSONObject object = JSONObject.parseObject(json);
        if (object.get("success").equals(false)) {
            return null;
        } else {
            String obj = object.get("data").toString();
            JSONObject data = JSONObject.parseObject(obj);
            if (data.get("userName") == null) {
                userName = "";
            } else {
                userName = data.get("userName").toString();
            }
            password = data.get("password").toString();
            User user = new User.Builder()
                    .setUserName(userName)
                    .setPassword(password)
                    .build();
            return user;
        }
    }

    //获得排名
    private static List<Score> getRank(List<Score> scores) {
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                int number1 = o1.getNumber();
                int number2 = o2.getNumber();
                if (number1 > number2) {
                    return -1;
                } else if (number1 == number2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        List<Score> newScores = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            newScores.add(new Score.Builder()
                    .setRank(i + 1)
                    .setNumber(scores.get(i).getNumber())
                    .setUserName(scores.get(i).getUserName())
                    .setTime(scores.get(i).getTime())
                    .build());
        }
        return newScores;
    }

    //排行榜json解析
    public static List<Score> getRankInfoFromJson(String json) {
        List<Score> scores = new ArrayList<>();
        JSONArray array = JSON.parseArray(json);
        int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject object = array.getJSONObject(i);
            String userName;
            if (object.get("userName") == null) {
                userName = "";
            } else {
                userName = object.get("userName").toString();
            }
            Integer number = Integer.parseInt(object.get("number").toString());
            String time = object.get("clock").toString();

            scores.add(new Score.Builder()
                    .setTime(time)
                    .setUserName(userName)
                    .setNumber(number)
                    .build());
        }
        List<Score> newScores = getRank(scores);
        return newScores;
    }

    //提交分数json解析
    public static boolean getScoreInfoFromJson(String json) {
        JSONObject object = JSONObject.parseObject(json);
        return object.get("success").equals(true);
    }
}
