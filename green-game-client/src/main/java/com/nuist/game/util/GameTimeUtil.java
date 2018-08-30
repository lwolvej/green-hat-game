package com.nuist.game.util;

import com.nuist.exception.MyException;

import java.util.concurrent.TimeUnit;

/**
 * 游戏时间工具
 *
 * @author LwolveJ
 */
public class GameTimeUtil {

    public static void sleepMillis(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            throw new MyException("游戏时间调度出现问题");
        }
    }
}
