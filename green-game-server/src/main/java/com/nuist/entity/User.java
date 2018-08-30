package com.nuist.entity;

import com.nuist.converter.UserConverter;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

/**
 * @author lijie
 */
public class User implements Serializable {

    private String userName;

    private String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(JsonObject obj) {
        System.out.println(obj.getValue("userName"));
        UserConverter.fromJson(obj, this);
    }

    public User(String str) {
        UserConverter.fromJson(new JsonObject(str), this);
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        UserConverter.toJson(this, jsonObject);
        return jsonObject;
    }

    public String getUserName() {
        return getOrElse(userName, "");
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return getOrElse(password, "*");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private <T> T getOrElse(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
