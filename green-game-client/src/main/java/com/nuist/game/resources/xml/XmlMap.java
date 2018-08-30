package com.nuist.game.resources.xml;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

/**
 * 地图设置
 *
 * @author LwolveJ
 */
@ObjectCreate(pattern = "map")
public class XmlMap {

    private XmlBricks bricks;

    @SetNext
    public void setBricks(XmlBricks bricks) {
        this.bricks = bricks;
    }

    public XmlBricks getBricks() {
        return bricks;
    }
}
