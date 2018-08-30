package com.nuist.game.view.frame;

import com.nuist.game.GameMain;
import com.nuist.ui.Invoker;
import com.nuist.ui.chen.MenuFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * 结束界面
 * @author XiaoQi
 */
public class EndGameFrame extends JFrame implements MouseListener {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 675;

    private JLabel close;
    private JLabel again;

    private String userName;

    public EndGameFrame(String userName, String number) {

        this.setUserName(userName);

        close = new JLabel(new ImageIcon(EndGameFrame.class.getResource("/image/game/close.png")));
        close.setBounds(30, 30, 202, 69);
        close.addMouseListener(this);
        this.add(close);

        again = new JLabel(new ImageIcon(EndGameFrame.class.getResource("/image/game/again.png")));
        again.setBounds(30, 150, 202, 69);
        again.addMouseListener(this);
        this.add(again);

        JLabel score = new JLabel(number);
        score.setBounds(780, 545, 200, 100);
        score.setFont(new java.awt.Font("微软雅黑", Font.BOLD, 60));
        score.setForeground(new Color(56, 175, 54));
        this.add(score);

        BackImage back = new BackImage();
        this.add(back);

        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == close) {
            Invoker.show(new MenuFrame(this.getUserName()));
        } else if (e.getSource() == again) {
            new GameMain().start(this.getUserName());
        }
        this.setVisible(false);
        this.dispose();
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

    class BackImage extends JPanel {
        Image background;

        BackImage() {
            try {
                background = ImageIO.read(EndGameFrame.class.getResourceAsStream("/image/game/over.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(background, 0, 0, 1200, 675, null);
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
