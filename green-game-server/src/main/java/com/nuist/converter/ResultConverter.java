package com.nuist.converter;

import com.nuist.entity.JsonResult;
import io.vertx.core.json.JsonObject;

/**
 * @author LwolveJ
 */
public class ResultConverter {

    private static final String SUCCESS = "success";

    private static final String MSG = "msg";

    private static final String DATA = "data";

    public static void fromJson(JsonObject object, JsonResult result) {
        if(object.getValue(SUCCESS) instanceof Boolean) {
            result.setSuccess((Boolean) object.getValue(SUCCESS));
        }
        if(object.getValue(MSG) instanceof String) {
            result.setMsg((String) object.getValue(MSG));
        }
        if(object.getValue(DATA) != null) {
            result.setData(object.getValue(DATA));
        }
    }

    public static void toJson(JsonResult result, JsonObject object) {
        object.put(SUCCESS, result.getSuccess());
        object.put(DATA, result.getData());
        object.put(MSG, result.getMsg());
    }
}
