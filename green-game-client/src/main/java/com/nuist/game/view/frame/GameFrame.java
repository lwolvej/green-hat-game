package com.nuist.game.view.frame;


import com.nuist.game.GameConstants;
import com.nuist.game.view.panel.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏界面
 *
 * @author LwolveJ
 */
public class GameFrame extends JFrame {
    private static final long serialVersionUID = 5832686558174052959L;

    private GamePanel gamePanel;

    public GameFrame() {
        super();

        this.setSize(GameConstants.GAME_PANEL_WIDTH, GameConstants.GAME_PANEL_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 设置显示的位置在屏幕中间
        Dimension screenSizeInfo = Toolkit.getDefaultToolkit().getScreenSize();
        int leftTopX = ((int) screenSizeInfo.getWidth() - this.getWidth()) / 2;
        int leftTopY = ((int) screenSizeInfo.getHeight() - this.getHeight()) / 2;
        this.setLocation(leftTopX, leftTopY);
        System.out.println(GameFrame.class.getResource("/game.wav").getFile());
    }

}
