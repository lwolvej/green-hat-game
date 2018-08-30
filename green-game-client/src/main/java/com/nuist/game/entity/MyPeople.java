package com.nuist.game.entity;

import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.enums.PeopleTypeEnum;

/**
 * 玩家实体
 *
 * @author LwolveJ
 */
public class MyPeople extends People {

    public MyPeople(int x, int y, DirectionEnum directionEnum) {
        super(x, y, directionEnum);
        this.setPeopleTypeEnum(PeopleTypeEnum.MY);
        this.setBlood(10);
    }
}
