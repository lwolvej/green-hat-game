package com.nuist.converter;

import com.nuist.entity.Score;
import io.vertx.core.json.JsonObject;

/**
 * @author LwolveJ
 */
public class ScoreConverter {

    public static void fromJson(JsonObject object, Score score) {
        if (object.getValue("user_name") != null) {
            score.setUserName((String) object.getValue("user_name"));
        }
        if(object.getValue("userName") != null) {
            score.setUserName((String) object.getValue("userName"));
        }
        if(object.getValue("clock") != null) {
            score.setClock((String) object.getValue("clock"));
        }
        if (object.getValue("number") instanceof Number) {
            score.setNumber(((Number) object.getValue("number")).intValue());
        }
    }

    public static void toJson(Score score, JsonObject object) {
        if(score.getUserName() != null) {
            object.put("userName", score.getUserName());
        }
        if(score.getClock() != null) {
            object.put("clock", score.getClock());
        }
        if(score.getNumber() != null) {
            object.put("number", score.getNumber());
        }
    }
}
