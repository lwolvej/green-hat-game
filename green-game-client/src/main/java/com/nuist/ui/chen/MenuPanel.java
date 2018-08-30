package com.nuist.ui.chen;

import com.nuist.ui.Invoker;
import com.nuist.ui.tang.HistoryFrame;
import com.nuist.ui.wu.RankListFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/*
 * @author 陈曼钰
 */
public class MenuPanel extends JPanel implements KeyListener {
    public static int abc = 0;
    //背景图像
    Image background;
    //创建大帽帽对象
    BigCaps bigcap = new BigCaps();
    //创建小帽帽对象
    SmallCaps[] small = {};
    int flag = 50;
    int count = 0;

    String userName;

    public boolean gameover = false;

    public MenuPanel() {
        try {
            background = ImageIO.read(MenuPanel.class.getResourceAsStream("/image/chen/8background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, MenuFrame.WIDTH, MenuFrame.HEIGHT, null);
        //绘制玩家
        bigcap.paintBigCaps(g);
        for (int i = 0; i < small.length; i++)
            small[i].paintSmallCaps(g);
        if (count >= flag / 2) {
            Font font = new Font("System 粗体", Font.BOLD, 48);
            g.setColor(Color.green);
            g.setFont(font);
            g.drawString("Congratulations!Green Man!", 300, 280);
        }
    }

    //障碍物入场方法
    int index = 0;
    int j = 0;

    public void enteredAction() {
        index++;
        if (index % 200 == 0) {
            SmallCaps sc = new SmallCaps();//生成一个螃蟹
            //数组扩容一个单位
            if (j++ < flag) {
                small = Arrays.copyOf(small, small.length + 1);//复制一个数组 扩容+1
                small[small.length - 1] = sc;//放到数组的最后一个元素
            }
        }
    }

    //帽帽移动方法
    public void stepAction() {
        for (int i = 0; i < small.length; i++) {
            small[i].step();
        }

    }

    //大帽帽小帽帽相撞方法
    public void wardAction() {
        for (int i = 0; i < small.length; i++) {
            SmallCaps sm = small[i];
            if (bigcap.getX() + bigcap.getWidth() > sm.getRandomx() &&
                    bigcap.getX() < sm.getRandomx() + sm.getWidth() &&
                    bigcap.getY() + bigcap.getHeight() > sm.getRandomy() &&
                    bigcap.getY() < sm.getHeight() + sm.getRandomy()) {
                //豆豆消失
                small[i] = small[small.length - 1];
                small = Arrays.copyOf(small, small.length - 1);
                count++;
            }
        }
    }

    //线程方法

    public void action() {
        //创建线程并启动
        new Thread() {
            public void run() {
                while (true) {
                    enteredAction();
                    stepAction();
                    //				bg.step();
                    bigcap.step();
                    wardAction();
                    repaint();
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            ;
        }.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        //获取玩家当前的坐标
        int x = bigcap.getX();
        int y = bigcap.getY();
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //玩家坐标减小
            bigcap.setY(y - 10);
            if (bigcap.getY() <= 5)//限制上界
                bigcap.setY(y);

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {//限制下界
            //玩家坐标减小
            bigcap.setY(y + 10);
            if (((bigcap.getX() <= 450 || bigcap.getX() >= 700) && bigcap.getY() >= 500) || (bigcap.getX() >= 450 && bigcap.getX() <= 700) && bigcap.getY() >= 595) {
                bigcap.setY(y);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            bigcap.setX(x - 10);
            if ((bigcap.getX() <= 5 && bigcap.getY() <= 500) || (bigcap.getY() >= 500 && bigcap.getX() <= 450))
                bigcap.setX(x);
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bigcap.setX(x + 10);
            if ((bigcap.getX() >= 1130 && bigcap.getY() <= 500) || (bigcap.getY() >= 500 && bigcap.getX() >= 700))

                bigcap.setX(x);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER &&
                bigcap.getY() <= 364 &&
                bigcap.getY() >= 304 &&
                bigcap.getX() <= 721 &&
                bigcap.getX() >= 479
        ) {
            gameover = true;
            new Thread() {
                public void run() {
//                    new GameMain().start();
                }
            }.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER &&
                bigcap.getY() <= 455 &&
                bigcap.getY() >= 395 &&
                bigcap.getX() <= 721 &&
                bigcap.getX() >= 479
        ) {
            gameover = true;
            new Thread() {
                public void run() {
                    new HistoryFrame(userName);
                }
            }.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER &&
                bigcap.getY() <= 546 &&
                bigcap.getY() >= 486 &&
                bigcap.getX() <= 721 &&
                bigcap.getX() >= 479
        ) {
            gameover = true;
            new Thread() {
                public void run() {
                    Invoker.show(new RankListFrame());
                }
            }.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER &&
                bigcap.getY() <= 637 &&
                bigcap.getY() >= 577 &&
                bigcap.getX() <= 721 &&
                bigcap.getX() >= 479
        ) {
            gameover = true;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
