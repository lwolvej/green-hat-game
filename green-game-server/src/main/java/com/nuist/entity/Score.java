package com.nuist.entity;

import com.nuist.converter.ScoreConverter;
import io.vertx.core.json.JsonObject;

import java.io.Serializable;

/**
 * @author LwolveJ
 */
public class Score implements Serializable {

    private String userName;

    private String clock;

    private Integer number;

    public Score() {
    }

    public Score(String userName, String clock, Integer number) {
        this.userName = userName;
        this.clock = clock;
        this.number = number;
    }

    public Score(JsonObject object) {
        ScoreConverter.fromJson(object, this);
    }

    public Score(String str) {
        ScoreConverter.fromJson(new JsonObject(str), this);
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        ScoreConverter.toJson(this, object);
        return object;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Score{" +
                "userName='" + userName + '\'' +
                ", clock='" + clock + '\'' +
                ", number=" + number +
                '}';
    }
}
