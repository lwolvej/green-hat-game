package com.nuist.game.entity;

import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.enums.PeopleTypeEnum;

import java.util.Timer;

/**
 * 敌人
 *
 * @author LwolveJ
 */
public class EnemyPeople extends People {

    /**
     * 敌人出现的相对位置，固定在五个地方
     */
    private int location;

    /**
     * 玩家在敌人的相对位置
     */
    private DirectionEnum myLocation = DirectionEnum.INVALID;

    /**
     * 我的位置
     */
    private DirectionEnum myDirect = DirectionEnum.DOWN;

    /**
     * 定时器
     */
    private Timer timer;

    private Boolean isShot = false;

    public EnemyPeople(int x, int y, DirectionEnum directionEnum) {
        super(x, y, directionEnum);
        this.setSpeed(4);
        this.setPeopleTypeEnum(PeopleTypeEnum.ENEMY);
        this.setDirect(DirectionEnum.UP);
        this.setBlood(10);
        timer = new Timer();
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public DirectionEnum getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(DirectionEnum myLocation) {
        this.myLocation = myLocation;
    }

    public DirectionEnum getMyDirect() {
        return myDirect;
    }

    public void setMyDirect(DirectionEnum myDirect) {
        this.myDirect = myDirect;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Boolean getShot() {
        return isShot;
    }

    public void setShot(Boolean shot) {
        isShot = shot;
    }
}
