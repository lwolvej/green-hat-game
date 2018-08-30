package com.nuist.ui.zhu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/*
 * @author 朱雨薇
 * 右帽子
 */
public class Hat_1 {
    BufferedImage image;
    int x, y, a, b;
    int xSpeed;
    int ySpeed;
    public static final int WIDTH = 120;
    public static final int HEIGHT = 80;
    Random random = new Random();

    public Hat_1() {
        initHat_1();
        xSpeed = 1;
        ySpeed = 1;
        y = 675 + random.nextInt(675);
        x = 300 + random.nextInt(300);
        b = 675 + random.nextInt(675);
        a = 600 + random.nextInt(300);


    }

    public void initHat_1() {
        try {
            image = ImageIO.read(Hat_1.class.getResource("/image/zhu/hat.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void step() {
        x += xSpeed;
        y -= ySpeed;
        a -= xSpeed;
        b -= ySpeed;
    }

    public boolean outOfBounds() {
        return x <= -WIDTH;

    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public void paintHat(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
        g.drawImage(image, a, b, WIDTH, HEIGHT, null);
    }
}
