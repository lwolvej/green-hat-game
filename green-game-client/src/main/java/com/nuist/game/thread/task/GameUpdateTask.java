package com.nuist.game.thread.task;

import com.nuist.game.context.GameContext;
import com.nuist.game.dto.RealGameData;
import com.nuist.game.service.GameEventService;
import com.nuist.game.util.GameTimeUtil;
import com.nuist.game.view.panel.GamePanel;


/**
 * 游戏更新任务
 *
 * @author LwolveJ
 */
public class GameUpdateTask implements Runnable {

    private GameContext gameContext;

    public GameUpdateTask(GameContext context) {
        this.gameContext = context;
    }

    @Override
    public void run() {
        GamePanel panel = gameContext.getGamePanel();
        RealGameData gameData = gameContext.getGameData();
        GameEventService control = gameContext.getGameEventService();
        // 每隔30毫秒重画
        while (true) {
            GameTimeUtil.sleepMillis(30);
            if (gameData.isStart()) {
                if ((gameData.getMyPeopleNum() == 0 || gameData.getEnemyPeopleNum() == 0)
                        && gameData.getDy() > 250) {
                    gameData.setDy(gameData.getDy() - 2);
                }
                if (gameData.getDy() == 250) {
                    panel.repaint();
                    GameTimeUtil.sleepMillis(4000);
                    if (gameData.getLevel() == 5) {
                        gameData.setLevel(1);
                    }
                    if (gameData.getMyPeopleNum() >= 1 && gameData.getLevel() <= 4) {
                        gameData.setLevel(gameData.getLevel() + 1);
                        gameData.setDy(600);
                    }
                }
                if (!gameData.isStop() && gameData.getDy() == 600) {
                    control.cleanAndCreate(); // 从容器中移除死亡的对象
                    control.refreshState();
                    control.doHatEvent();
                    control.doOverlapJudge(); // 判断是否出现重叠
                    control.myPeopleEvent(gameData);
                }
            } else {

                //TODO 游戏结束
            }
            panel.repaint();
        }
    }

    public GameContext getContext() {
        return gameContext;
    }

    public void setContext(GameContext context) {
        this.gameContext = context;
    }
}
