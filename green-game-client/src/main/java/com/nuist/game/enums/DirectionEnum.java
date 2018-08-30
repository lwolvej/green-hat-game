package com.nuist.game.enums;

/**
 * @author XiaoQi
 */
public enum DirectionEnum {

    INVALID(-1, "无效"),

    UP(1, "上"),

    DOWN(2, "下"),

    LEFT(3, "左"),

    RIGHT(4, "右");

    private Integer key;

    private String name;

    DirectionEnum(Integer key, String name) {
        this.key = key;
        this.name = name;
    }

    public static DirectionEnum getByKey(Integer key) {
        for (DirectionEnum elem : DirectionEnum.values()) {
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
