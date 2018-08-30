package com.nuist.ui.chen;

import com.nuist.game.GameMain;
import com.nuist.ui.Invoker;
import com.nuist.ui.tang.HistoryFrame;
import com.nuist.ui.wu.RankListFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/*
 * @author 陈曼钰
 */
public class MenuFrame extends JFrame implements MouseListener {

    JLabel start;
    //登录按钮
    JLabel history;

    JLabel rank;
    JLabel exit;

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 675;

    private String name;

    public MenuFrame(String name) {
        this.name = name;
        start = new JLabel(new ImageIcon(MenuPanel.class.getResource("/image/chen/8start.png")));
        start.setBounds(480, 300, 243, 60);
        start.addMouseListener(this);
        this.add(start);

        history = new JLabel(new ImageIcon(MenuPanel.class.getResource("/image/chen/8history.png")));
        history.setBounds(480, 391, 243, 60);
        history.addMouseListener(this);
        this.add(history);

        rank = new JLabel(new ImageIcon(MenuPanel.class.getResource("/image/chen/8rank.png")));
        rank.setBounds(480, 482, 243, 60);
        rank.addMouseListener(this);
        this.add(rank);

        exit = new JLabel(new ImageIcon(MenuPanel.class.getResource("/image/chen/8exit.png")));
        exit.setBounds(480, 573, 243, 60);
        exit.addMouseListener(this);
        this.add(exit);

        MenuPanel panel = new MenuPanel();
        panel.action();
        this.addKeyListener(panel);
        this.add(panel);

        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setUndecorated(true);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 鼠标点击事件
        int m, n;
        m = e.getX();
        n = e.getY();
        if (e.getSource() == start) {
            this.setVisible(false);
            dispose();
            new GameMain().start(this.getName());
        }
        if (e.getSource() == history) {
            Invoker.show(new HistoryFrame(this.getName()));
        }
        if (e.getSource() == rank) {
            Invoker.show(new RankListFrame());
        }
        if (e.getSource() == exit) {
            this.setVisible(false);
            dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static void main (String... args) {
        new MenuFrame("zhuhuidi");
    }
}
