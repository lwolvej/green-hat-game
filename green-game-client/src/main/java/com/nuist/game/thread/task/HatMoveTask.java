package com.nuist.game.thread.task;

import com.nuist.game.GameConstants;
import com.nuist.game.entity.Hat;
import com.nuist.game.util.GameTimeUtil;

/**
 * 帽子移动任务
 *
 * @author LwolveJ
 */
public class HatMoveTask implements Runnable {

    private Hat hat;

    public HatMoveTask(Hat hat) {
        this.hat = hat;
    }

    @Override
    public void run() {
        while(hat.getLive()) {
            switch (hat.getDirectionEnum()) {
                case UP:
                    hat.setY(hat.getY() - hat.getSpeed());
                    break;
                case RIGHT:
                    hat.setX(hat.getX() + hat.getSpeed());
                    break;
                case LEFT:
                    hat.setX(hat.getX() - hat.getSpeed());
                    break;
                case DOWN:
                    hat.setY(hat.getY() + hat.getSpeed());
                    break;
            }
            if(hat.getX() < 5 || hat.getX() > GameConstants.GAME_PANEL_WIDTH - 5
                || hat.getY() < 5 || hat.getY() > GameConstants.GAME_PANEL_HEIGHT - 5) {
                hat.setLive(false);
            }

            GameTimeUtil.sleepMillis(40);
        }
    }
}
