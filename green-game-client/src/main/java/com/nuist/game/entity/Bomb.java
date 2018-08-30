package com.nuist.game.entity;

/**
 * 炸弹
 *
 * @author LwolveJ
 */
public class Bomb {

    /**
     * 炸弹的宽度
     */
    private int l;

    /**
     * 炸弹的x坐标
     */
    private int x;

    /**
     * 炸弹的y坐标
     */
    private int y;

    private int lifeTime = 15;

    /**
     * 是否存活
     */
    private boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 添加帽子
     */
    public void lifeDown() {
        if (lifeTime > 0) {
            lifeTime--;
        }
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
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

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public String toString() {
        return "Bomb{" +
                "l=" + l +
                ", x=" + x +
                ", y=" + y +
                ", lifeTime=" + lifeTime +
                ", isLive=" + isLive +
                '}';
    }
}
