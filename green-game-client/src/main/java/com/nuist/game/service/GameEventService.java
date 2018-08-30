package com.nuist.game.service;

import com.nuist.game.context.GameContext;
import com.nuist.game.dto.RealGameData;
import com.nuist.game.entity.*;
import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.enums.StuffTypeEnum;
import com.nuist.game.resources.map.Map;
import com.nuist.game.thread.executor.TaskExecutor;
import com.nuist.game.thread.task.HatMoveTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Vector;

/**
 * 游戏活动服务
 *
 * @author LwolveJ
 */
@Service
public class GameEventService {

    /**
     * 游戏容器
     */
    @Autowired
    private GameContext context;
    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    /**
     * 电脑线程实现
     */
    @Autowired
    private TaskExecutor taskExecutor;

    /**
     * 玩家服务
     */
    @Autowired
    private PeopleEventService peopleEventService;


    /**
     * 帽子和砖块有无击中
     *
     * @param hat   帽子
     * @param stuff 实物
     * @return 有无击中
     */
    public Boolean isHitting(Hat hat, Stuff stuff) {
        return (Math.abs(hat.getX() - stuff.getX()) <= (hat.getWidth() + stuff.getWidth()) / 2)
                && (Math.abs(hat.getY() - stuff.getY()) <= (hat.getWidth() + stuff.getWidth()) / 2);
    }

    /**
     * 帽子和帽子有无击中
     *
     * @param hat1 帽子1
     * @param hat2 帽子2
     * @return 有无击中
     */
    public Boolean isHitting(Hat hat1, Hat hat2) {
        return (Math.abs(hat1.getX() - hat2.getX()) <= hat1.getWidth())
                && (Math.abs(hat1.getY() - hat2.getY()) <= hat1.getHeight());
    }

    /**
     * 刷新状态
     */
    public void refreshState() {
        RealGameData resource = context.getGameData();
        Vector<EnemyPeople> enemies = resource.getEnemyPeople();
        Vector<MyPeople> myTanks = resource.getMyPeople();

        if (!myTanks.isEmpty()) {
            enemies.forEach(enemyTank -> {
                enemyTank.setMyLocation(myTanks.get(0).getDirect());
            });
        }
    }

    /**
     * 帽子动作
     */
    public void doHatEvent() {
        RealGameData resource = context.getGameData();

        Vector<MyPeople> myTanks = resource.getMyPeople();
        Vector<EnemyPeople> enemies = resource.getEnemyPeople();
        Vector<Bomb> bombs = resource.getBombs();
        Map map = resource.getMap();

        if (myTanks.isEmpty()) {
            enemies.forEach(enemyTank -> enemyTank.setShot(false));
        }

        myTanks.forEach(myTank ->
                enemies.forEach(enemyTank -> {

                    peopleEventService.enemyFindAndShot(enemyTank, myTank, map);

                    enemyTank.getHats().forEach(eb -> {
                        if (isHitting(eb, myTank)) {
                            this.afterShotPeople(eb, myTank, bombs);
                        }

                        map.getBricks().stream().filter(brick -> isHitting(eb, brick))
                                .forEach(brick -> afterShotStuff(eb, brick, bombs));
                    });

                    myTank.getHats().forEach(mb -> {
                        enemyTank.getHats().stream().filter(eb -> isHitting(mb, eb))
                                .forEach(eb -> {
                                    mb.setLive(false);
                                    eb.setLive(false);
                                    Bomb bomb = new Bomb(mb.getX(), mb.getY());
                                    bomb.setL(20);
                                    bombs.add(bomb);
                                });


                        if (isHitting(mb, enemyTank)) {
                            this.afterShotPeople(mb, enemyTank, bombs);
                        }

                        map.getBricks().stream().filter(brick -> isHitting(mb, brick))
                                .forEach(brick -> afterShotStuff(mb, brick, bombs));
                    });
                })
        );
    }

    /**
     * 覆盖判定
     */
    public void doOverlapJudge() {

        RealGameData resource = context.getGameData();
        Vector<MyPeople> myPeople = resource.getMyPeople();
        Vector<EnemyPeople> enemies = resource.getEnemyPeople();
        Map map = resource.getMap();
        Vector<Brick> bricks = map.getBricks();

        myPeople.forEach(obj -> {
            obj.setOverlapNo(false);
            obj.setOverlapYes(false);

            if (peopleEventService.isMyPeopleOverlap(obj, enemies)) {
                obj.setOverlapYes(true);
            }

            bricks.stream().filter(brick -> peopleEventService.isPeopleOverlap(obj, brick, 20 + 10))
                    .forEach(brick -> obj.setOverlapYes(true));
        });

        enemies.forEach(obj -> {
            obj.setOverlapNo(false);
            obj.setOverlapYes(false);
            obj.setStopStuffTypeEnum(StuffTypeEnum.INVALID);

            if (peopleEventService.isEnemyPeopleOverlap(obj, enemies, myPeople)) {
                obj.setOverlapYes(true);
            }


            bricks.stream().filter(iron -> peopleEventService.isPeopleOverlap(obj, iron, 20 + 10))
                    .forEach(iron -> {
                        obj.setStopStuffTypeEnum(StuffTypeEnum.BRICK);
                        obj.setOverlapNo(true);
                    });
        });

    }

    /**
     * 清除和创建
     */
    public void cleanAndCreate() {
        RealGameData data = context.getGameData();

        Vector<MyPeople> myTanks = data.getMyPeople();
        Vector<EnemyPeople> enemies = data.getEnemyPeople();
        Vector<Bomb> bombs = data.getBombs();
        Map map = data.getMap();


        for (int i = 0; i < myTanks.size(); i++) {
            MyPeople myTank = myTanks.get(i);
            Vector<Hat> mb = myTank.getHats();
            mb.removeIf(b -> !b.getLive());

            if (!myTank.getLive()) {
                myTanks.remove(myTank);
                data.setMyPeopleNum(data.getMyPeopleNum() - 1);
                data.setBeKilled(data.getBeKilled() + 1);

                if (data.getMyPeopleNum() >= 1) {
                    // 1
                    MyPeople myTankTemp = new MyPeople(300, 620, DirectionEnum.UP);
                    myTanks.add(myTankTemp);
                }
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            EnemyPeople enemy = enemies.get(i);
            Vector<Hat> eb = enemy.getHats();
            eb.removeIf(b -> !b.getLive());

            if (!enemy.getLive()) {
                enemy.getTimer().cancel();
                int r;


                data.setEnemyPeopleNum(data.getEnemyPeopleNum() - 1);
                r = (int) (Math.random() * 5);
                enemies.remove(enemy);
                if (data.getEnemyPeopleNum() >= 5) {
                    EnemyPeople enemyTank = new EnemyPeople((r) * 140 + 20,
                            -20, DirectionEnum.DOWN);
                    enemyTank.setLocation(r);
                    enemyTank.setActivate(Boolean.TRUE);
                    taskExecutor.startSingleEnemyThread(enemyTank);
                    enemies.add(enemyTank);
                }
                break;

            }
        }
        bombs.removeIf(bomb -> !bomb.isLive());
        map.getBricks().removeIf(brick -> !brick.getLive());
    }

    /**
     * 帽子扔中玩家之后，玩家如果已有5顶帽子，则消失，出现一个大啪。否则多加一帽子，出现一个小啪。
     *
     * @param hat    帽子
     * @param people 玩家
     * @param bombs  炸弹们
     */
    public void afterShotPeople(Hat hat, People people, Vector<Bomb> bombs) {
        //首先将hat设置为不可见
        hat.setLive(false);
        Bomb bomb;
        //blood看成被带的帽子数量
        if (people.getBlood() == 1) {
            people.setLive(false);
            bomb = new Bomb(people.getX(), people.getY());
            people.setBlood(people.getBlood() - 1);
            bomb.setL(120);
            bombs.add(bomb);
        } else {
            bomb = new Bomb(people.getX(), people.getY());
            people.setBlood(people.getBlood() - 1);
            bomb.setL(40);
            bombs.add(bomb);
        }
    }

    /**
     * 如果击中物品之后，默认击中砖块，击中之后，帽子消失，出现一个小啪，砖块无影响
     *
     * @param hat   击中的帽子
     * @param stuff 物品
     * @param bombs 炸弹
     */
    public void afterShotStuff(Hat hat, Stuff stuff, Vector<Bomb> bombs) {
        hat.setLive(false);
        Bomb bomb = new Bomb(hat.getX(), hat.getY());
        bomb.setL(30);
        bombs.add(bomb);
    }

    /**
     * 自己的动作监听
     */
    public void myPeopleEvent(RealGameData data) {
        RealGameData resources = context.getGameData();
        for (int i = 0; i < data.getMyPeople().size(); i++) {
            MyPeople myPeople = data.getMyPeople().get(i);
            if (data.isDown() && !myPeople.getOverlapNo() && !myPeople.getOverlapYes()) {
                myPeople.goDown();
            } else if (data.isUp() && !myPeople.getOverlapNo() && !myPeople.getOverlapYes()) {
                myPeople.goUp();
            } else if (data.isLeft() && !myPeople.getOverlapNo() && !myPeople.getOverlapYes()) {
                myPeople.goLeft();
            } else if (data.isRight() && !myPeople.getOverlapNo() && !myPeople.getOverlapYes()) {
                myPeople.goRight();
            }
        }
    }

    /**
     * 下一关
     */
    public void nextGame() {

    }

    /**
     * 射击
     *
     * @param people 玩家
     */
    public void shot(People people) {
        Hat hat = null;
        switch (people.getDirect()) {
            case UP:
                hat = new Hat(people.getX(), people.getY(), DirectionEnum.UP);
                break;
            case RIGHT:
                hat = new Hat(people.getX(), people.getY(), DirectionEnum.RIGHT);
                break;
            case LEFT:
                hat = new Hat(people.getX(), people.getY(), DirectionEnum.LEFT);
                break;
            case DOWN:
                hat = new Hat(people.getX(), people.getY(), DirectionEnum.DOWN);
                break;
        }
        people.getHats().add(hat);
        threadPool.execute(new HatMoveTask(hat));
    }

    public GameContext getContext() {
        return context;
    }

    public void setContext(GameContext context) {
        this.context = context;
    }

    public ThreadPoolTaskExecutor getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public PeopleEventService getPeopleEventService() {
        return peopleEventService;
    }

    public void setPeopleEventService(PeopleEventService peopleEventService) {
        this.peopleEventService = peopleEventService;
    }
}
