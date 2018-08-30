package com.nuist.game.util;

import com.nuist.exception.MyException;
import com.nuist.game.entity.Stuff;

import java.util.ArrayList;
import java.util.List;

public class MapUtil {

    public static List<Stuff> getFullMapStuff() {
        List<Stuff> list = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
            for(int j = 0; j < 30; j++) {
                list.add(new Stuff(20 * i + 10, 20 * j + 10));
            }
        }
        return list;
    }

    public static Stuff getNearestStuff(int x, int y) {
        List<Stuff> list = getFullMapStuff();
        for(Stuff elem : list) {
            if (Math.abs(elem.getX() - x) <= 10 && Math.abs(elem.getY() - y) <= 10) {
                return elem;
            }
        }
        throw new MyException("&*_*&");
    }
}
