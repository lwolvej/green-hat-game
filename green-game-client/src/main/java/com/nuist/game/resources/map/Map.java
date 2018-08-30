package com.nuist.game.resources.map;

import com.nuist.game.entity.Brick;
import com.nuist.game.resources.xml.XmlMap;

import java.util.Vector;

/**
 * 地图设置
 *
 * @author LwolveJ
 */
public class Map {

    private Vector<Brick> bricks;

    public Map() {
    }

    public Map(XmlMap map) {
        bricks = new Vector<>();
        map.getBricks().getBricks().forEach(obj -> bricks.add(new Brick(obj.getX(), obj.getY())));
    }

    public Vector<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(Vector<Brick> bricks) {
        this.bricks = bricks;
    }
}
