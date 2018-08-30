package com.nuist.game.listener;

import com.nuist.game.context.GameContext;
import com.nuist.game.dto.RealGameData;
import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.service.GameEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 游戏键盘监听事件
 *
 * @author XiaoQi
 */
@Component
public class MainFrameKeyListener implements KeyListener {

    @Autowired
    private GameEventService gameEventService;

    @Autowired
    private GameContext context;


    /**
     * 键盘按压事件：方向和射击
     *
     * @param e 事件
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 键盘释放事件
     *
     * @param e 事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        RealGameData gameData = context.getGameData();
        //向下
        gameData.getMyPeople().forEach(obj -> {
            //如果玩家存活
            if (obj.getLive()) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    //向右
                    obj.setDirect(DirectionEnum.RIGHT);
                    gameData.keyPressDirect(false, false, false, true);
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    //向上
                    obj.setDirect(DirectionEnum.UP);
                    gameData.keyPressDirect(true, false, false, false);
                } else if (e.getKeyCode() == KeyEvent.VK_A) {
                    //向左
                    obj.setDirect(DirectionEnum.LEFT);
                    gameData.keyPressDirect(false, false, true, false);
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    //向下
                    obj.setDirect(DirectionEnum.DOWN);
                    gameData.keyPressDirect(false, true, false, false);
                }
                System.out.println(obj.getY());
                if (e.getKeyCode() == KeyEvent.VK_K && obj.getY() < 700) {
                    //TODO 不确定是否需要确定可以攻击的帽子数量的增加
                    gameEventService.shot(obj);
                }
            } else {
                gameData.keyPressDirect(false, false, false, false);
            }
        });
    }

    @Override
    public void keyReleased(KeyEvent e) {
        RealGameData data = context.getGameData();
        if (e.getKeyCode() == KeyEvent.VK_D) {
            data.setRight(false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            data.setDown(false);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            data.setLeft(false);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            data.setUp(false);
        }
    }

    public GameEventService getGameEventService() {
        return gameEventService;
    }

    public void setGameEventService(GameEventService gameEventService) {
        this.gameEventService = gameEventService;
    }

    public GameContext getContext() {
        return context;
    }

    public void setContext(GameContext context) {
        this.context = context;
    }
}
