package com.nuist.game.thread.task;

import com.nuist.game.entity.EnemyPeople;
import com.nuist.game.service.GameEventService;

import java.util.TimerTask;

/**
 * 电脑自动射击任务
 *
 * @author LwolveJ
 */
public class EnemyAutoShotTask extends TimerTask {

    private EnemyPeople enemyPeople;

    private GameEventService gameEventService;

    public EnemyAutoShotTask(EnemyPeople enemyPeople, GameEventService gameEventService) {
        this.enemyPeople = enemyPeople;
        this.gameEventService = gameEventService;
    }


    @Override
    public void run() {
        if (enemyPeople.getShot() && enemyPeople.getActivate()) {
            gameEventService.shot(enemyPeople);
        }
    }
}
