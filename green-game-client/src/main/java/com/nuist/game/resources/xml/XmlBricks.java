package com.nuist.game.resources.xml;

import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

import java.util.Vector;

/**
 * 砖块整体
 *
 * @author LwolveJ
 */
@ObjectCreate(pattern = "map/bricks")
public class XmlBricks {

    private Vector<XmlBrick> bricks = new Vector<>();

    @SetNext
    public void addBrick(XmlBrick brick) {
        this.bricks.add(brick);
    }

    public Vector<XmlBrick> getBricks() {
        return bricks;
    }

    public void setBricks(Vector<XmlBrick> bricks) {
        this.bricks = bricks;
    }
}
