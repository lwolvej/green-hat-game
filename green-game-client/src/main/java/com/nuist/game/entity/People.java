package com.nuist.game.entity;

import com.nuist.game.GameConstants;
import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.enums.PeopleTypeEnum;
import com.nuist.game.enums.StuffTypeEnum;

import java.util.Vector;

/**
 * 玩家实体
 *
 * @author LwolveJ
 */
public class People extends Stuff {

    /**
     * 玩家的速度
     */
    private int speed = 4;

    /**
     * 挡住人前方的东西
     */
    private StuffTypeEnum stopStuffTypeEnum = StuffTypeEnum.INVALID;

    /**
     * 人拥有的帽子
     */
    private Vector<Hat> hats;

    /**
     * 人被带的帽子
     */
    private Vector<Hat> haveHats;

    /**
     * 人的类型
     */
    private PeopleTypeEnum peopleTypeEnum;

    /**
     * 可以通过但是需要一定手段：攻击（电脑碰到玩家）
     */
    private Boolean isOverlapYes = false;

    /**
     * 不可以通过
     */
    private Boolean isOverlapNo = false;

    /**
     * 判断是否处于活跃状态
     */
    private Boolean isActivate = Boolean.FALSE;

    public People(int x, int y, DirectionEnum directionEnum) {
        super(x, y);
        this.setDirect(directionEnum);
        this.setType(StuffTypeEnum.PEOPLE);
        this.hats = new Vector<>();
        this.haveHats = new Vector<>();
        this.setWidth(40);
        this.setHeight(40);
    }

    public void goUp() {
        this.setDirect(DirectionEnum.UP);
        if (this.getY() > 20) {
            this.setY(this.getY() - this.getSpeed());
        } else {
            this.setStopStuffTypeEnum(StuffTypeEnum.BRICK);
        }
    }

    public void goDown() {
        this.setDirect(DirectionEnum.DOWN);
        if (this.getY() < GameConstants.GAME_PANEL_HEIGHT - 20) {
            this.setY(this.getY() + this.getSpeed());
        } else {
            this.setStopStuffTypeEnum(StuffTypeEnum.BRICK);
        }
    }

    public void goLeft() {
        this.setDirect(DirectionEnum.LEFT);
        if (this.getX() > 20 && this.getY() <= GameConstants.GAME_PANEL_HEIGHT - 20) {
            this.setX(this.getX() - this.speed);
        } else {
            this.setStopStuffTypeEnum(StuffTypeEnum.BRICK);
        }
    }

    public void goRight() {
        this.setDirect(DirectionEnum.RIGHT);
        if (this.getX() < GameConstants.GAME_PANEL_WIDTH - 20
                && this.getY() <= GameConstants.GAME_PANEL_HEIGHT - 20) {
            this.setX(this.getX() + this.speed);
        } else {
            this.setStopStuffTypeEnum(StuffTypeEnum.BRICK);
        }
    }

    public void go(DirectionEnum where) {
        switch (where) {
            case DOWN:
                this.goDown();
            case LEFT:
                this.goLeft();
            case UP:
                this.goUp();
            case RIGHT:
                this.goRight();
        }
    }

    public Boolean getOverlapYes() {
        return isOverlapYes;
    }

    public void setOverlapYes(Boolean overlapYes) {
        isOverlapYes = overlapYes;
    }

    public Boolean getOverlapNo() {
        return isOverlapNo;
    }

    public void setOverlapNo(Boolean overlapNo) {
        isOverlapNo = overlapNo;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public StuffTypeEnum getStopStuffTypeEnum() {
        return stopStuffTypeEnum;
    }

    public void setStopStuffTypeEnum(StuffTypeEnum stopStuffTypeEnum) {
        this.stopStuffTypeEnum = stopStuffTypeEnum;
    }

    public Vector<Hat> getHats() {
        return hats;
    }

    public void setHats(Vector<Hat> hats) {
        this.hats = hats;
    }

    public Vector<Hat> getHaveHats() {
        return haveHats;
    }

    public void setHaveHats(Vector<Hat> haveHats) {
        this.haveHats = haveHats;
    }

    public PeopleTypeEnum getPeopleTypeEnum() {
        return peopleTypeEnum;
    }

    public void setPeopleTypeEnum(PeopleTypeEnum peopleTypeEnum) {
        this.peopleTypeEnum = peopleTypeEnum;
    }

    public Boolean getActivate() {
        return isActivate;
    }

    public void setActivate(Boolean activate) {
        isActivate = activate;
    }
}
