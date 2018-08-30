package com.nuist.game.resources.xml;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * 砖块xml
 *
 * @author LwolveJ
 */
@ObjectCreate(pattern = "map/bricks/brick")
public class XmlBrick {

    @BeanPropertySetter(pattern = "map/bricks/brick/x")
    private Integer x;

    @BeanPropertySetter(pattern = "map/bricks/brick/y")
    private Integer y;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
