package com.nuist.game.context;

import com.nuist.game.GameConstants;
import com.nuist.game.dto.RealGameData;
import com.nuist.game.entity.EnemyPeople;
import com.nuist.game.entity.MyPeople;
import com.nuist.game.enums.DirectionEnum;
import com.nuist.game.listener.MainFrameKeyListener;
import com.nuist.game.resources.map.Map;
import com.nuist.game.resources.xml.MapParser;
import com.nuist.game.resources.xml.XmlMap;
import com.nuist.game.service.GameEventService;
import com.nuist.game.service.PaintService;
import com.nuist.game.thread.executor.TaskExecutor;
import com.nuist.game.thread.task.GameUpdateTask;
import com.nuist.game.view.frame.GameFrame;
import com.nuist.game.view.panel.GamePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.FileInputStream;

/**
 * 游戏容器
 *
 * @author LwolveJ
 */
@Component
public class GameContext {

    private GameFrame gameFrame;

    private GamePanel gamePanel;

    private RealGameData gameData;

    private JLabel againGameButton;

    private JLabel endGameButton;

    @Autowired
    private MainFrameKeyListener mainFrameKeyListener;

    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Autowired
    private GameEventService gameEventService;

    @Autowired
    private PaintService paintService;

    @Autowired
    private TaskExecutor taskExecutor;

    /**
     * 游戏初始化
     */
    public void init(String name) {
        initGameData(1, name);
        this.gameFrame = new GameFrame();
        this.gamePanel = new GamePanel(paintService);

        this.gameFrame.add(this.gamePanel);
        this.gameFrame.addKeyListener(mainFrameKeyListener);
        this.gameFrame.setVisible(true);
        //执行游戏更新线程
        threadPool.execute(new GameUpdateTask(this));
    }

    /**
     * 初始化游戏数据
     *
     * @param level 开始的等级
     */
    private void initGameData(int level, String name) {
        gameData = new RealGameData();
        gameData.setUserName(name);
        for (int i = 0; i < GameConstants.INIT_ENEMY_NUM; i++) {
            EnemyPeople enemyPeople = new EnemyPeople(i * 140 + 120, 200, DirectionEnum.DOWN);
            enemyPeople.setLocation(i);
            gameData.getEnemyPeople().add(enemyPeople);
        }

        for (int i = 0; i < 1; i++) {
            MyPeople myPeople = new MyPeople(GameConstants.PEOPLE_INIT_X, GameConstants.PEOPLE_INIT_Y, DirectionEnum.UP);
            gameData.getMyPeople().add(myPeople);
        }

        gameData.setLevel(level);
        XmlMap xmlMap = MapParser.getMapFromXml("map1");
        if (xmlMap != null) {
            gameData.setMap(new Map(xmlMap));
        }
        gameData.setEnemyPeopleNum(GameConstants.INIT_ENEMY_NUM);
        gameData.setMyPeopleNum(GameConstants.INIT_PEOPLE_NUM);
        gameData.setHatNum(GameConstants.MY_PEOPLE_INIT_HAT_NUM);
        gameData.setBeKilled(0);
        gameData.setDy(600);

        taskExecutor.startEnemyThreads();
    }

    public void startGame() {
        gameData.setStart(true);
        gameData.getEnemyPeople().forEach(obj -> obj.setActivate(true));
        gameData.getMyPeople().forEach(obj -> obj.setActivate(true));
    }

    public void playMusic() {
        try {
            FileInputStream fis = new FileInputStream(GameContext.class.getResource("/game.wav").getFile());
            AudioStream as = new AudioStream(fis);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCanGet() {
        this.againGameButton.setVisible(true);
        this.endGameButton.setVisible(true);
    }

    /**
     * 重新开始
     *
     * @param level 开始的等级
     */
    public void reset(int level) {

    }

    /**
     * 开始游戏
     *
     * @param level 开始的等级
     */
    public void startGame(int level) {

    }


    //getter/setter
    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public RealGameData getGameData() {
        return gameData;
    }

    public void setGameData(RealGameData gameData) {
        this.gameData = gameData;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public MainFrameKeyListener getMainFrameKeyListener() {
        return mainFrameKeyListener;
    }

    public void setMainFrameKeyListener(MainFrameKeyListener mainFrameKeyListener) {
        this.mainFrameKeyListener = mainFrameKeyListener;
    }

    public ThreadPoolTaskExecutor getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public GameEventService getGameEventService() {
        return gameEventService;
    }

    public void setGameEventService(GameEventService gameEventService) {
        this.gameEventService = gameEventService;
    }

    public PaintService getPaintService() {
        return paintService;
    }

    public void setPaintService(PaintService paintService) {
        this.paintService = paintService;
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public JLabel getAgainGameButton() {
        return againGameButton;
    }

    public void setAgainGameButton(JLabel againGameButton) {
        this.againGameButton = againGameButton;
    }

    public JLabel getEndGameButton() {
        return endGameButton;
    }

    public void setEndGameButton(JLabel endGameButton) {
        this.endGameButton = endGameButton;
    }
}
