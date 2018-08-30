package com.nuist.game.enums;

/**
 * @author LwolveJ
 */
public enum PeopleTypeEnum {

    INVALID(-1, "无效"), MY(1, "自己"), ENEMY(2, "敌人");


    private Integer key;

    private String name;

    PeopleTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public static PeopleTypeEnum getByKey(Integer key) {
        for (PeopleTypeEnum elem : PeopleTypeEnum.values()) {
            if (elem.getKey().equals(key)) {
                return elem;
            }
        }
        return INVALID;
    }
}
