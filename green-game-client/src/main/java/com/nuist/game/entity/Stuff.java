package com.nuist.game.entity;

import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.enums.StuffTypeEnum;

/**
 * @author LwolveJ
 */
public class Stuff {

    private Integer x;

    private Integer y;

    private Integer width;

    private Integer height;

    private StuffTypeEnum type;

    private Boolean isLive;

    private DirectionEnum direct;

    private Integer blood;

    public Stuff(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.isLive = true;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public StuffTypeEnum getType() {
        return type;
    }

    public void setType(StuffTypeEnum type) {
        this.type = type;
    }

    public Boolean getLive() {
        return isLive;
    }

    public void setLive(Boolean live) {
        isLive = live;
    }

    public DirectionEnum getDirect() {
        return direct;
    }

    public void setDirect(DirectionEnum direct) {
        this.direct = direct;
    }

    public Integer getBlood() {
        return blood;
    }

    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    @Override
    public String toString() {
        return "Stuff{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", type=" + type +
                ", isLive=" + isLive +
                ", direct=" + direct +
                ", blood=" + blood +
                '}';
    }
}
