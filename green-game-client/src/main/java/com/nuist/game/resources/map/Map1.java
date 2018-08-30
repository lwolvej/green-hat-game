package com.nuist.game.resources.map;

import com.nuist.game.entity.Brick;
import com.nuist.game.resources.xml.XmlMap;

import java.util.Vector;

public class Map1 extends Map {

    public Map1() {

    }

    public Map1(XmlMap xmlMap) {
        super(xmlMap);
        Vector<Brick> bricks = this.getBricks();
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0)
                continue;
            Brick brick = new Brick(20 * i + 10, 470);
            bricks.add(brick);
        }
    }
}
