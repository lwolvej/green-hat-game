package com.nuist.ui.zhu;

import java.awt.Graphics;
import java.awt.Image;

import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * 背景
 *
 * @author 朱雨薇
 */
public class BackImage extends JLayeredPane {
    Image background;
    Hat_1[] hat1 = {};
    People people = new People();
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 675;

    public BackImage() {
        try {
            background = ImageIO.read(BackImage.class.getResourceAsStream("/image/zhu/登录界面.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, BackImage.WIDTH, BackImage.HEIGHT, null);
        people.paintPeopel(g);
        for (int i = 0; i < hat1.length; i++) {
            hat1[i].paintHat(g);
        }
    }

    //帽子入场
    int index = 0;

    public void enteredAction() {
        index++;
        if (index % 150 == 0) {
            Hat_1 h = new Hat_1();//生成一个帽子
            hat1 = Arrays.copyOf(hat1, hat1.length + 1);
            hat1[hat1.length - 1] = h;

        }
    }

    //帽子移动的方法
    public void stepAction() {
        people.step();
        for (int i = 0; i < hat1.length; i++) {
            hat1[i].step();
        }

    }


    //线程方法
    public void run() {
        new Thread() {
            public void run() {
                while (true) {
                    enteredAction();
                    stepAction();

                    repaint();//重绘方法
                    for (int i = 0; i < hat1.length; i++) {
                        if (hat1[i].getX() < -hat1[i].getWidth()) {
                            Hat_1 h = hat1[i];

                            hat1[i] = hat1[hat1.length - 1];
                            hat1[hat1.length - 1] = h;
                            hat1 = Arrays.copyOf(hat1, hat1.length - 1);
                        }
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }

}

