package com.nuist.game.thread.executor;

import com.nuist.game.context.GameContext;
import com.nuist.game.entity.EnemyPeople;
import com.nuist.game.service.GameEventService;
import com.nuist.game.service.PeopleEventService;
import com.nuist.game.thread.task.EnemyAutoMoveTask;
import com.nuist.game.thread.task.EnemyAutoShotTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Vector;

/**
 * 任务执行
 *
 * @author LwolveJ
 */
@Component("taskExecutor")
public class TaskExecutor {

    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    /**
     * 游戏容器
     */
    @Autowired
    private GameContext context;

    /**
     * 游戏活动服务
     */
    @Autowired
    private GameEventService gameEventService;

    /**
     * 人物活动服务
     */
    @Autowired
    private PeopleEventService peopleEventService;


    /**
     * 开启所有电脑玩家的线程
     */
    public void startEnemyThreads() {
        Vector<EnemyPeople> enemyPeopleVector = context.getGameData().getEnemyPeople();
        enemyPeopleVector.forEach(obj -> {
            threadPool.execute(new EnemyAutoMoveTask(obj, peopleEventService));
            obj.getTimer().schedule(new EnemyAutoShotTask(obj, gameEventService), 0, 500);
        });
    }

    /**
     * 开启单个电脑线程
     *
     * @param enemy 电脑
     */
    public void startSingleEnemyThread(EnemyPeople enemy) {
        threadPool.execute(new EnemyAutoMoveTask(enemy, peopleEventService));
        enemy.getTimer().schedule(new EnemyAutoShotTask(enemy, gameEventService), 0, 500);
    }


    //setter/getter
    public ThreadPoolTaskExecutor getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public GameContext getContext() {
        return context;
    }

    public void setContext(GameContext context) {
        this.context = context;
    }

    public GameEventService getGameEventService() {
        return gameEventService;
    }

    public void setGameEventService(GameEventService gameEventService) {
        this.gameEventService = gameEventService;
    }

    public PeopleEventService getPeopleEventService() {
        return peopleEventService;
    }

    public void setPeopleEventService(PeopleEventService peopleEventService) {
        this.peopleEventService = peopleEventService;
    }
}
