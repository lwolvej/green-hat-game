package com.nuist.game.service;

import com.nuist.game.GameConstants;
import com.nuist.game.entity.*;
import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.resources.map.Map;
import com.nuist.game.util.GameTimeUtil;
import org.springframework.stereotype.Service;

import java.util.Vector;

/**
 * 人物活动服务
 *
 * @author LwolveJ
 */
@Service
public class PeopleEventService {

    /**
     * 判断人物是否和另外一个物品重叠
     *
     * @param people 人物
     * @param stuff  物品
     * @param length 两者之间的最短距离
     * @return 是否重叠
     */
    public boolean isPeopleOverlap(People people, Stuff stuff, int length) {
        boolean b = false;
        //得到物品的坐标
        int stuffX = stuff.getX();
        int stuffY = stuff.getY();
        //如果人物向左
        if (people.getDirect() == DirectionEnum.LEFT) {
            people.setX(people.getX() - people.getSpeed());
            if (Math.abs(people.getX() - stuffX) < length
                    && Math.abs(people.getY() - stuffY) < length) {
                b = true;
                people.setX(people.getX() + people.getSpeed());
            } else {
                people.setX(people.getX() + people.getSpeed());
            }
        }
        //人物向右
        if (people.getDirect() == DirectionEnum.RIGHT) {
            people.setX(people.getX() + people.getSpeed());
            if (Math.abs(people.getX() - stuffX) < length
                    && Math.abs(people.getY() - stuffY) < length) {
                b = true;
                people.setX(people.getX() - people.getSpeed());
            } else {
                people.setX(people.getX() - people.getSpeed());
            }
        }
        //人物向上
        if (people.getDirect() == DirectionEnum.UP) {
            people.setY(people.getY() - people.getSpeed());
            if (Math.abs(people.getX() - stuffX) < length
                    && Math.abs(people.getY() - stuffY) < length) {
                b = true;
                people.setY(people.getY() + people.getSpeed());
            } else {
                people.setY(people.getY() + people.getSpeed());
            }
        }
        //人物向下
        if (people.getDirect() == DirectionEnum.DOWN) {
            people.setY(people.getY() + people.getSpeed());
            if (Math.abs(people.getX() - stuffX) < length
                    && Math.abs(people.getY() - stuffY) < length) {
                b = true;
                people.setY(people.getY() - people.getSpeed());
            } else {
                people.setY(people.getY() - people.getSpeed());
            }
        }
        return b;
    }

    /**
     * 判断我和其他人是否碰撞
     *
     * @return 是否碰撞
     */
    public boolean isMyPeopleOverlap(MyPeople myPeople, Vector<EnemyPeople> enemyPeople) {
        int len = enemyPeople.size();
        for (int i = 0; i < len; i++) {
            if (isPeopleOverlap(myPeople, enemyPeople.get(i), 40)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否电脑和其他玩家重叠
     *
     * @param enemyPeople       电脑玩家
     * @param enemyPeopleVector 所有电脑玩家
     * @param myPeopleVector    我方电脑玩家
     * @return 是否重叠
     */
    public boolean isEnemyPeopleOverlap(EnemyPeople enemyPeople, Vector<EnemyPeople> enemyPeopleVector, Vector<MyPeople> myPeopleVector) {
        int len1 = enemyPeopleVector.size();
        int len2 = myPeopleVector.size();
        for (int i = 0; i < len1; i++) {
            if (enemyPeople != enemyPeopleVector.get(i)) {
                if (isPeopleOverlap(enemyPeople, enemyPeopleVector.get(i), 40)) {
                    enemyPeople.setOverlapNo(true);
                    return true;
                }
            }
        }
        //如果玩家和敌人重叠，敌人会进行攻击
        for (int i = 0; i < len2; i++) {
            if (isPeopleOverlap(enemyPeople, myPeopleVector.get(i), 40)) {
                enemyPeople.setOverlapYes(true);
                return true;
            }
        }
        enemyPeople.setOverlapYes(false);
        enemyPeople.setOverlapNo(false);
        return false;
    }

    /**
     * 电脑每隔36毫秒一直向上走
     */
    public void enemyGoUp(EnemyPeople enemy) {
        for (; ; ) {
            //休眠36毫秒
            GameTimeUtil.sleepMillis(36);
            //判断能否向上走
            if (!enemy.getOverlapNo() && !enemy.getOverlapYes()) {
                enemy.goUp();
            }
            //判断此时的方向是否已经是向上
            if (enemy.getMyLocation() != DirectionEnum.UP) {
                enemy.setMyDirect(enemy.getMyLocation());
                break;
            }
        }
    }

    /**
     * 电脑每隔36毫秒一直向下走
     *
     * @param enemy 电脑
     */
    public void enemyGoDown(EnemyPeople enemy) {
        for (; ; ) {
            GameTimeUtil.sleepMillis(36);
            if (!enemy.getOverlapNo() && !enemy.getOverlapYes()) {
                enemy.goDown();
            }
            if (enemy.getMyLocation() != DirectionEnum.DOWN) {
                enemy.setMyDirect(enemy.getMyLocation());
                break;
            }
        }
    }

    /**
     * 电脑每隔36毫秒一直向左走
     *
     * @param enemy 电脑
     */
    public void enemyGoLeft(EnemyPeople enemy) {
        for (; ; ) {
            GameTimeUtil.sleepMillis(36);
            if (!enemy.getOverlapNo() && !enemy.getOverlapYes()) {
                enemy.goLeft();
            }
            if (enemy.getMyLocation() != DirectionEnum.LEFT) {
                enemy.setMyDirect(enemy.getMyLocation());
                break;
            }
        }
    }

    public void enemyGoRight(EnemyPeople enemy) {
        for (; ; ) {
            GameTimeUtil.sleepMillis(36);
            if (!enemy.getOverlapNo() && !enemy.getOverlapYes()) {
                enemy.goRight();
            }
            if (enemy.getMyLocation() != DirectionEnum.RIGHT) {
                enemy.setMyDirect(enemy.getMyLocation());
                break;
            }
        }
    }

    /**
     * 电脑从三个方向任意挑选一个方向
     *
     * @param direct1 方向1
     * @param direct2 方向2
     * @param direct3 方向3
     * @return 随机的方向
     */
    public DirectionEnum enemyGoRandomDirection(DirectionEnum direct1, DirectionEnum direct2, DirectionEnum direct3) {
        int num = (int) (Math.random() * 3);
        DirectionEnum direction = DirectionEnum.INVALID;
        switch (num) {
            case 0:
                direction = direct1;
                break;
            case 1:
                direction = direct2;
                break;
            case 2:
                direction = direct3;
                break;
        }
        return direction;
    }

    /**
     * 敌人能够找到我的位置并开火
     *
     * @param enemy 敌人
     * @param my    我
     * @param map   地图
     */
    public void enemyFindAndShot(EnemyPeople enemy, MyPeople my, Map map) {

        int myX = my.getX();
        int myY = my.getY();
        int enX = enemy.getX();
        int enY = enemy.getY();

        if (Math.abs(myX - enX) < GameConstants.PEOPLE_CHANGE_SIZE && myY < GameConstants.GAME_PANEL_HEIGHT) {
            if (enY < myY) {
                int s = 0;
                for (int i = 0; i < map.getBricks().size(); i++) {
                    Brick brick = map.getBricks().get(i);
                    if (Math.abs(enX - brick.getX()) <= 15
                            && brick.getY() > enY
                            && brick.getY() < myY) {
                        s = 1;
                        break;
                    }
                }
                if (s == 0) {
                    enemy.setShot(true);
                    enemy.setMyLocation(DirectionEnum.DOWN);
                }
            } else {
                int s = 0;
                for (int i = 0; i < map.getBricks().size(); i++) {
                    Brick brick = map.getBricks().get(i);
                    if (Math.abs(enX - brick.getX()) <= 15
                            && brick.getY() < enY
                            && brick.getY() > myY) {
                        s = 1;
                        break;
                    }
                }
                if (s == 0) {
                    enemy.setShot(true);
                    enemy.setMyLocation(DirectionEnum.UP);
                }
            }
        } else if (Math.abs(myY - enY) < GameConstants.PEOPLE_CHANGE_SIZE && myY < GameConstants.GAME_PANEL_HEIGHT) {
            if (enX > myX) {
                int s = 0;
                for (int i = 0; i < map.getBricks().size(); i++) {
                    Brick brick = map.getBricks().get(i);
                    if (Math.abs(enY - brick.getY()) <= 15
                            && brick.getX() < enX
                            && brick.getX() > myX) {
                        s = 1;
                        break;
                    }
                }
                if (s == 0) {
                    enemy.setShot(true);
                    enemy.setMyLocation(DirectionEnum.LEFT);
                }
            } else {
                int s = 0;
                for (int i = 0; i < map.getBricks().size(); i++) {
                    Brick brick = map.getBricks().get(i);
                    if (Math.abs(enY - brick.getY()) <= 15
                            && brick.getX() > enX
                            && brick.getX() < myX) {
                        s = 1;
                        break;
                    }
                }
                if (s == 0) {
                    enemy.setShot(true);
                    enemy.setMyLocation(DirectionEnum.RIGHT);
                }
            }
        } else {
            enemy.setShot(false);
            enemy.setMyLocation(DirectionEnum.INVALID);
        }
    }
}
