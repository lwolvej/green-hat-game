package com.nuist.game.entity;

import com.nuist.game.enums.DirectionEnum;

/**
 * 帽子
 *
 * @author LwolveJ
 */
public class Hat {

    /**
     * 子弹的速度
     */
    private int speed = 10;

    /**
     * 子弹的x坐标
     */
    private int x;

    /**
     * 子弹的y坐标
     */
    private int y;

    /**
     * 子弹的
     */
    private DirectionEnum directionEnum;

    private Boolean isLive = true;

    private int width;

    private int height;

    public Hat(int x, int y, DirectionEnum directionEnum) {
        this.x = x;
        this.y = y;
        this.directionEnum = directionEnum;
        this.width = 20;
        this.height = 20;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirectionEnum getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(DirectionEnum directionEnum) {
        this.directionEnum = directionEnum;
    }

    public Boolean getLive() {
        return isLive;
    }

    public void setLive(Boolean live) {
        isLive = live;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
