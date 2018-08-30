package com.nuist.game.enums;

/**
 * @author LwolveJ
 */
public enum StuffTypeEnum {

    INVALID(-1, "无效"),
    PEOPLE(1, "玩家"),
    BRICK(2, "砖块"),
    HAT(3, "帽子"),
    MAP(4, "地图");

    private Integer key;

    private String name;

    StuffTypeEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static StuffTypeEnum getTypeByKey(Integer key) {
        for (StuffTypeEnum elem : StuffTypeEnum.values()) {
            if (elem.getKey().equals(key)) {
                return elem;
            }
        }
        return INVALID;
    }

    public Integer getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
