package com.nuist.game.enums;

import com.nuist.game.resources.map.Map;
import com.nuist.game.resources.map.Map1;
import com.nuist.game.resources.xml.XmlMap;

/**
 * 等级枚举
 *
 * @author LwolveJ
 */
public enum LevelEnum {

    FIRST_LEVEL(1, "第一关", new Map1(new XmlMap())),
    SECOND_LEVEL(2, "第二关", new Map1()),
    THIRD_LEVEL(3, "第三关", new Map1()),
    FORTH_LEVEL(4, "第四关", new Map1());

    private Integer level;

    private String name;

    private Map map;

    LevelEnum(Integer level, String name, Map map) {
        this.level = level;
        this.name = name;
        this.map = map;
    }

    public static LevelEnum getByLevel(int level) {
        for (LevelEnum elem : LevelEnum.values()) {
            if (elem.getLevel() == level) {
                return elem;
            }
        }
        return null;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
