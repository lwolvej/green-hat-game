package com.nuist.ui.wu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author 吴惠敏
 * 豌豆实体类
 */
public class Pea {
    BufferedImage[] images;
    BufferedImage image;
    BufferedImage rw1, rw2;
    int index;
    //移动速度
    int xSpeed;
    //横纵坐标
    volatile int x, y;
    public static final int WIDTH = 24;
    public static final int HEIGHT = 24;
    PeaShooter peashooter = new PeaShooter();

    public Pea() {
        initPea();
        images = new BufferedImage[]{rw1, rw2};
        index = 0;
        xSpeed = 1;
        x = 120;
        y = peashooter.getY() + 10;
    }

    private void initPea() {
        try {
            rw1 = ImageIO.read(Pea.class.getResourceAsStream("/image/wu/6.png"));
            rw2 = ImageIO.read(Pea.class.getResourceAsStream("/image/wu/6.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //移动方法
    public void step() {
        //图片切换
        image = images[index++ / 100 % images.length];
        //坐标移动
        x += xSpeed;
    }

    //判断是否越界
    public boolean outOfBounds() {
        //若大于等于，返回true；否则，返回false
        return x >= WIDTH + RankListFrame.WIDTH;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void paintPea(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }


}
