package com.nuist.game.service;

import com.nuist.game.GameConstants;
import com.nuist.game.context.GameContext;
import com.nuist.game.dto.RealGameData;
import com.nuist.game.entity.*;
import com.nuist.game.enums.PeopleTypeEnum;
import com.nuist.game.resources.GameImages;
import com.nuist.game.resources.map.Map;
import com.nuist.game.view.frame.EndGameFrame;
import com.nuist.game.view.panel.GamePanel;
import com.nuist.ui.Invoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 绘图服务
 *
 * @author LwolveJ
 */
@Service
public class PaintService {

    @Autowired
    private GameContext gameContext;

    public void drawStuff(Graphics graphics, Stuff stuff, JPanel jPanel, RealGameData gameData) {
        switch (stuff.getType()) {
            //根据不同的方向绘制，需要考虑当有帽子的时候需要变色
            case PEOPLE:
                People people = (People) stuff;
                switch (people.getDirect()) {
                    case DOWN:
                        this.drawDown(graphics, people, gameData);
                        break;
                    case LEFT:
                        this.drawLeft(graphics, people,  gameData);
                        break;
                    case UP:
                        this.drawUp(graphics, people, gameData);
                        break;
                    case RIGHT:
                        this.drawRight(graphics, people, gameData);
                        break;
                }
                break;
            case BRICK:
                Brick brick = (Brick) stuff;
                graphics.drawImage(GameImages.brickImage[1],
                        brick.getX() - 10, brick.getY() - 10,
                        GameConstants.HAT_SIZE, GameConstants.HAT_SIZE, jPanel);
                break;
        }
    }

    public void drawMap(Graphics graphics, Map map, JPanel jPanel, RealGameData gameData) {
        Vector<Brick> bricks = map.getBricks();
        bricks.forEach(obj -> this.drawStuff(graphics, obj, jPanel, gameData));
    }

    public void drawBomb(Graphics graphics, Vector<Bomb> bombs, JPanel jPanel) {
        bombs.forEach(obj -> {
            int l = obj.getL();
            graphics.drawImage(GameImages.bombImage, obj.getX() - l / 2, obj.getY() - l / 2, l, l, jPanel);
            obj.lifeDown();
            if (obj.getLifeTime() == 0) {
                obj.setLive(false);
            }
        });
    }

    /**
     * 画出我和帽子
     *
     * @param graphics       画笔
     * @param jPanel         面板
     * @param myPeopleVector 我
     */
    public void drawMyPeople(Graphics graphics, JPanel jPanel, Vector<MyPeople> myPeopleVector, RealGameData gameData) {
        myPeopleVector.forEach(obj -> {
            this.drawStuff(graphics, obj, jPanel, gameData);
            drawHat(graphics, obj);
        });
    }

    /**
     * 画出敌人和帽子
     *
     * @param graphics    画笔
     * @param jPanel      画板
     * @param enemyPeople 敌人
     */
    public void drawEnemyPeople(Graphics graphics, JPanel jPanel, Vector<EnemyPeople> enemyPeople, RealGameData gameData) {
        enemyPeople.forEach(obj -> {
            this.drawStuff(graphics, obj, jPanel, gameData);
            drawHat(graphics, obj);
        });
    }

    private void drawHat(Graphics graphics, People people) {
        people.getHats().forEach(elem -> {
            if (elem != null) {
                if (elem.getLive()) {
                    graphics.drawImage(GameImages.hatImage,
                            elem.getX() - 2, elem.getY() - 2,
                            GameConstants.HAT_SIZE, GameConstants.HAT_SIZE, null);
                }
            }
        });
    }

    /**
     * 画出向上
     * 和左边类似
     *
     * @param graphics 画板
     * @param people   玩家
     */
    public void drawUp(Graphics graphics, People people, RealGameData gameData) {
        drawLeft(graphics, people, gameData);
    }

    /**
     * 画出向下
     * 和右边类似
     *
     * @param graphics 画笔
     * @param people   玩家
     */
    public void drawDown(Graphics graphics, People people, RealGameData gameData) {
        drawRight(graphics, people, gameData);
    }

    /**
     * 画出向左
     *
     * @param graphics 画笔
     * @param people   玩家
     */
    public void drawLeft(Graphics graphics,  People people, RealGameData gameData) {
        Image image;
        //分成我和敌人，并且判断目前的帽子数量
        if (people.getPeopleTypeEnum() == PeopleTypeEnum.MY) {
            if (people.getHaveHats().size() > 0) {
                image = GameImages.myGreenPeopleImage[3];
            } else {
                image = GameImages.myPeopleImage[3];
            }
            graphics.setColor(Color.GREEN);
            graphics.drawString(gameData.getUserName(), people.getX() - GameConstants.PEOPLE_CHANGE_SIZE, people.getY() - 40);
        } else {
            if (people.getHats().size() > 0) {
                image = GameImages.enemyGreenPeopleImage[3];
            } else {
                image = GameImages.enemyPeopleImage[3];
            }
            graphics.setColor(Color.GREEN);
            graphics.drawString("电脑玩家", people.getX() - GameConstants.PEOPLE_CHANGE_SIZE, people.getY() - 40);
        }
        graphics.drawImage(image, people.getX() - GameConstants.PEOPLE_CHANGE_SIZE, people.getY() - GameConstants.PEOPLE_CHANGE_SIZE, GameConstants.PEOPLE_SIZE, GameConstants.PEOPLE_SIZE, null);
//        graphics.fillRect(people.getX() - GameConstants.PEOPLE_CHANGE_SIZE, people.getY() - 40, people.getBlood() * 4, 5);
        if(people.getBlood() != 10) {
            int num = 10 - people.getBlood();
            for(int i = 0; i < num; i++) {
                graphics.drawImage(GameImages.hatImage, people.getX() - GameConstants.PEOPLE_CHANGE_SIZE + 20, people.getY() - (GameConstants.PEOPLE_CHANGE_SIZE  - 20) * i - 20, GameConstants.HAT_SIZE, GameConstants.HAT_SIZE, null);
            }
        }
    }

    /**
     * 画出右边
     *
     * @param graphics 画笔
     * @param people   玩家
     */
    public void drawRight(Graphics graphics, People people, RealGameData gameData) {
        Image image;
        //分成我和敌人，并且判断目前的帽子数量
        if (people.getPeopleTypeEnum() == PeopleTypeEnum.MY) {
            if (people.getHaveHats().size() > 0) {
                image = GameImages.myGreenPeopleImage[0];
            } else {
                image = GameImages.myPeopleImage[0];
            }
            graphics.setColor(Color.GREEN);
            graphics.drawString(gameData.getUserName(), people.getX() - GameConstants.PEOPLE_CHANGE_SIZE, people.getY() - 40);
        } else {
            if (people.getHats().size() > 0) {
                image = GameImages.enemyGreenPeopleImage[0];
            } else {
                image = GameImages.enemyPeopleImage[0];
            }
            graphics.setColor(Color.GREEN);
            graphics.drawString("电脑", people.getX() - GameConstants.PEOPLE_CHANGE_SIZE, people.getY() - 40);
        }
        graphics.drawImage(image, people.getX() - GameConstants.PEOPLE_CHANGE_SIZE, people.getY() - GameConstants.PEOPLE_CHANGE_SIZE, GameConstants.PEOPLE_SIZE, GameConstants.PEOPLE_SIZE, null);
//        graphics.fillRect(people.getX() - GameConstants.PEOPLE_CHANGE_SIZE, people.getY() - 40, people.getBlood() * 4, 5);
        if(people.getBlood() != 10) {
            int num = 10 - people.getBlood();
            for(int i = 0; i < num; i++) {
                graphics.drawImage(GameImages.hatImage, people.getX() - GameConstants.PEOPLE_CHANGE_SIZE + 20, people.getY() - (GameConstants.PEOPLE_CHANGE_SIZE - 20)  * i - 20, GameConstants.HAT_SIZE, GameConstants.HAT_SIZE, null);
            }
        }
    }

    public void drawScore(Graphics graphics, RealGameData gameData) {
        int num = (GameConstants.INIT_ENEMY_NUM - gameData.getEnemyPeopleNum()) * 5;
        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("宋体", Font.BOLD, 30));
        graphics.drawString("当前得分" + num, 20, GameConstants.GAME_PANEL_WIDTH / 2);
    }


    /**
     * 原子值用于结束的判定
     */
    private AtomicInteger pan = new AtomicInteger(1);

    public void repaintPanel(Graphics graphics, GamePanel panel) {
        RealGameData gameData = gameContext.getGameData();
        //如果游戏开始
        if (gameData.isStart()) {
            graphics.drawImage(GameImages.backgroundImage, 0, 0, GameConstants.GAME_PANEL_WIDTH, GameConstants.GAME_PANEL_HEIGHT, null);
            this.drawMap(graphics, gameData.getMap(), panel, gameData);
            this.drawMyPeople(graphics, panel, gameData.getMyPeople(), gameData);
            this.drawEnemyPeople(graphics, panel, gameData.getEnemyPeople(), gameData);
            this.drawBomb(graphics, gameData.getBombs(), panel);
            this.drawScore(graphics, gameData);
            //游戏结束
            if (gameData.getMyPeopleNum() == 0) {
                if (pan.get() == 1) {
                    pan.incrementAndGet();
                    Invoker.show(new EndGameFrame(gameData.getUserName(), Integer.toString((GameConstants.INIT_ENEMY_NUM - gameData.getEnemyPeopleNum()) * 5)));
                    gameContext.getGameFrame().dispose();
                }
            }
            //胜利
            if (gameData.getEnemyPeopleNum() == 0) {

            }
        }
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }
}
