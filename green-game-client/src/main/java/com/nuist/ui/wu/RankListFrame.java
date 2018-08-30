package com.nuist.ui.wu;


import javax.swing.JFrame;

/**
 * @author 吴惠敏
 * 排行榜面板
 */
public class RankListFrame extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;


    public RankListFrame() {

        RankListPanel panel = new RankListPanel();
        panel.action();
        this.add(panel);
        this.addKeyListener(panel);

        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setUndecorated(false);
        this.setLocationRelativeTo(null);
    }
}