package com.nuist.ui.zhu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * @author 朱雨薇
 * 小人
 *
 */
public class People {
    BufferedImage[] image1, image2;
    BufferedImage rw0, rw1, rw2, rw3, rw4, rw5, rw6, rw7, rw8, rw9;
    BufferedImage image_1, image_2;
    int x1, y, x2;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 120;

    int index;

    public People() {
        initPeopel();
        x1 = 20;
        y = 500;
        x2 = 1080;
        image1 = new BufferedImage[]{rw1, rw2, rw3, rw4, rw5};
        image2 = new BufferedImage[]{rw6, rw7, rw8, rw9, rw0};
        index = 0;
    }

    //图片切换
    public void step() {
        //图片的切换
        image_1 = image1[index++ / 30 % image1.length];
        image_2 = image2[index++ / 30 % image2.length];
    }

    //判断是否越界的方法
    public boolean outOfBounds() {
        return false;
    }

    //绘制小人
    public void paintPeopel(Graphics g) {
        g.drawImage(image_1, x1, y, WIDTH, HEIGHT, null);
        g.drawImage(image_2, x2, y, WIDTH, HEIGHT, null);
    }

    public void initPeopel() {
        try {
            rw1 = ImageIO.read(People.class.getResource("/image/zhu/0.png"));
            rw2 = ImageIO.read(People.class.getResource("/image/zhu/1.png"));
            rw3 = ImageIO.read(People.class.getResource("/image/zhu/2.png"));
            rw4 = ImageIO.read(People.class.getResource("/image/zhu/1.png"));
            rw5 = ImageIO.read(People.class.getResource("/image/zhu/0.png"));
            rw6 = ImageIO.read(People.class.getResource("/image/zhu/21.png"));
            rw7 = ImageIO.read(People.class.getResource("/image/zhu/22.png"));
            rw8 = ImageIO.read(People.class.getResource("/image/zhu/23.png"));
            rw9 = ImageIO.read(People.class.getResource("/image/zhu/22.png"));
            rw0 = ImageIO.read(People.class.getResource("/image/zhu/21.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
