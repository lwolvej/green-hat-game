package com.nuist.game.thread.task;

import com.nuist.game.entity.EnemyPeople;
import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.service.PeopleEventService;
import com.nuist.game.util.GameTimeUtil;

/**
 * 电脑自动移动任务
 *
 * @author LwolveJ
 */
public class EnemyAutoMoveTask implements Runnable {

    private EnemyPeople enemy;

    private PeopleEventService eventService;

    public EnemyAutoMoveTask(EnemyPeople enemy, PeopleEventService service) {
        this.enemy = enemy;
        this.eventService = service;
    }

    @Override
    public void run() {
        while (true) {
            //选择方向
            switch (enemy.getDirect()) {
                case DOWN:
                    while (enemy.getActivate()) {
                        //睡眠一定时间保证已经运算过一遍
                        GameTimeUtil.sleepMillis(36);
                        //跑向左边
                        if (DirectionEnum.LEFT.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.LEFT);
                            eventService.enemyGoLeft(enemy);
                        }
                        //跑向右边
                        if (DirectionEnum.RIGHT.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.RIGHT);
                            eventService.enemyGoRight(enemy);
                        }
                        //跑向下面
                        if (DirectionEnum.DOWN.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.DOWN);
                        }
                        //跑向下面
                        if (DirectionEnum.UP.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.UP);
                            eventService.enemyGoUp(enemy);
                        }
                        if (enemy.getY() <= 20 || enemy.getOverlapNo()) {
                            enemy.setDirect(eventService.enemyGoRandomDirection(
                                    DirectionEnum.UP, DirectionEnum.LEFT, DirectionEnum.RIGHT));
                            break;
                        }
                        //如果当前的位置不在下方，跳出
                        if (enemy.getDirect() != DirectionEnum.DOWN) {
                            break;
                        }
                        //如果没有重叠就前进
                        if (!enemy.getOverlapYes()) {
                            enemy.goDown();
                        }
                    }
                    break;
                case LEFT:
                    while (enemy.getActivate()) {
                        GameTimeUtil.sleepMillis(36);
                        //睡眠一定时间保证已经运算过一遍
                        //跑向左边
                        if (DirectionEnum.LEFT.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.LEFT);
                        }
                        //跑向右边
                        if (DirectionEnum.RIGHT.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.RIGHT);
                            eventService.enemyGoRight(enemy);
                        }
                        //跑向下面
                        if (DirectionEnum.DOWN.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.DOWN);
                            eventService.enemyGoDown(enemy);
                        }
                        //跑向下面
                        if (DirectionEnum.UP.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.UP);
                            eventService.enemyGoUp(enemy);
                        }
                        if (enemy.getY() <= 20 || enemy.getOverlapNo()) {
                            enemy.setDirect(eventService.enemyGoRandomDirection(
                                    DirectionEnum.UP, DirectionEnum.DOWN, DirectionEnum.RIGHT));
                            break;
                        }
                        //如果当前的位置不在下方，跳出
                        if (enemy.getDirect() != DirectionEnum.LEFT) {
                            break;
                        }
                        //如果没有重叠就前进
                        if (!enemy.getOverlapYes()) {
                            enemy.goLeft();
                        }
                    }
                    break;
                case RIGHT:
                    while (enemy.getActivate()) {
                        //睡眠一定时间保证已经运算过一遍
                        GameTimeUtil.sleepMillis(36);
                        //跑向左边
                        if (DirectionEnum.LEFT.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.LEFT);
                        }
                        //跑向右边
                        if (DirectionEnum.RIGHT.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.RIGHT);
                            eventService.enemyGoRight(enemy);
                        }
                        //跑向下面
                        if (DirectionEnum.DOWN.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.DOWN);
                            eventService.enemyGoDown(enemy);
                        }
                        //跑向下面
                        if (DirectionEnum.UP.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.UP);
                            eventService.enemyGoUp(enemy);
                        }
                        if (enemy.getY() <= 20 || enemy.getOverlapNo()) {
                            enemy.setDirect(eventService.enemyGoRandomDirection(
                                    DirectionEnum.UP, DirectionEnum.DOWN, DirectionEnum.LEFT));
                            break;
                        }
                        //如果当前的位置不在下方，跳出
                        if (enemy.getDirect() != DirectionEnum.RIGHT) {
                            break;
                        }
                        //如果没有重叠就前进
                        if (!enemy.getOverlapYes()) {
                            enemy.goRight();
                        }
                    }
                    break;
                case UP:
                    while (enemy.getActivate()) {
                        //睡眠一定时间保证已经运算过一遍
                        GameTimeUtil.sleepMillis(36);
                        //跑向左边
                        if (DirectionEnum.LEFT.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.LEFT);
                            eventService.enemyGoLeft(enemy);
                        }
                        //跑向右边
                        if (DirectionEnum.RIGHT.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.RIGHT);
                            eventService.enemyGoRight(enemy);
                        }
                        //跑向下面
                        if (DirectionEnum.DOWN.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.DOWN);
                            eventService.enemyGoDown(enemy);
                        }
                        //跑向下面
                        if (DirectionEnum.UP.equals(enemy.getMyLocation())) {
                            enemy.setDirect(DirectionEnum.UP);
                        }
                        if (enemy.getY() <= 20 || enemy.getOverlapNo()) {
                            enemy.setDirect(eventService.enemyGoRandomDirection(
                                    DirectionEnum.RIGHT, DirectionEnum.DOWN, DirectionEnum.LEFT));
                            break;
                        }
                        //如果当前的位置不在下方，跳出
                        if (enemy.getDirect() != DirectionEnum.UP) {
                            break;
                        }
                        //如果没有重叠就前进
                        if (!enemy.getOverlapYes()) {
                            enemy.goUp();
                        }
                    }
                    break;
            }
            //改变一个方向不能让其太快
            GameTimeUtil.sleepMillis(216);
            //如果死亡的话，直接结束该线程
            if (!enemy.getLive()) {
                break;
            }
        }
    }
}
