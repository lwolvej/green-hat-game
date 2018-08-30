package com.nuist.converter;

import com.nuist.entity.User;
import io.vertx.core.json.JsonObject;

/**
 * @author LwolveJ
 */
public class UserConverter {

    private static final String USER_NAME = "userName";

    private static final String USER_NAME_1 = "user_name";

    private static final String PASSWORD = "password";

    public static void fromJson(JsonObject object, User user) {
        if (object.getValue(USER_NAME) != null && object.getValue(USER_NAME) instanceof String) {
            user.setUserName((String) object.getValue(USER_NAME));
        }
        if(object.getValue(USER_NAME_1) != null && object.getValue(USER_NAME_1) instanceof String) {
            user.setUserName((String) object.getValue(USER_NAME_1));
        }
        if (object.getValue(PASSWORD) instanceof String) {
            user.setPassword((String) object.getValue(PASSWORD));
        }
    }

    public static void toJson(User user, JsonObject object) {
        object.put(USER_NAME, user.getUserName());
        object.put(PASSWORD, user.getPassword());
    }
}
