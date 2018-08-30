package com.nuist.game.entity;

import com.nuist.game.enums.StuffTypeEnum;

/**
 * @author LwolveJ
 */
public class Brick extends Stuff {

    /**
     * 砖块的构造类
     *
     * @param x 所在x
     * @param y 所在y
     */
    public Brick(Integer x, Integer y) {
        super(x, y);
        this.setType(StuffTypeEnum.BRICK);
        this.setWidth(20);
        this.setHeight(20);
    }
}
