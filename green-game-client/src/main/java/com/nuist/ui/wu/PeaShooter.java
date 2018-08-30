package com.nuist.ui.wu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author 吴惠敏
 * 豌豆射手实体类
 */
public class PeaShooter {
    BufferedImage[] images;
    BufferedImage rw1, rw2, rw3, rw4;
    BufferedImage image;
    public static final int WIDTH = 70;
    public static final int HEIGHT = 75;
    volatile int x, y;
    int index;

    public PeaShooter() {
        x = 50;
        y = 200;
        initPeaShooter();
        images = new BufferedImage[]{rw1, rw2, rw3, rw4};
        index = 0;

    }

    //判断是否越界的方法
    public boolean outOfBounds() {
        return false;
    }

    //移动的方法
    public void step() {
        //图片的切换,本质是依次调用3张图片
        //方法每执行200次切换一张图片
        image = images[index++ / 200 % images.length];
    }

    //绘制豌豆射手的方法
    public void paintPerson(Graphics g) {
        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    public BufferedImage getRw4() {
        return rw4;
    }

    public void setRw4(BufferedImage rw4) {
        this.rw4 = rw4;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public BufferedImage getRw1() {
        return rw1;
    }

    public void setRw1(BufferedImage rw1) {
        this.rw1 = rw1;
    }

    public BufferedImage getRw2() {
        return rw2;
    }

    public void setRw2(BufferedImage rw2) {
        this.rw2 = rw2;
    }

    public BufferedImage getRw3() {
        return rw3;
    }

    public void setRw3(BufferedImage rw3) {
        this.rw3 = rw3;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public void initPeaShooter() {
        //赋值
        try {
//            rw1=ImageIO.read(new File("peaShooter2.png"));
//            rw2=ImageIO.read(new File("peaShooter1.png"));
//            rw3=ImageIO.read(new File("peaShooter2.png"));
//            rw4=ImageIO.read(new File("peaShooter3.png"));
            rw1 = ImageIO.read(PeaShooter.class.getResourceAsStream("/image/wu/peaShooter2.png"));
            rw2 = ImageIO.read(PeaShooter.class.getResourceAsStream("/image/wu/peaShooter1.png"));
            rw3 = ImageIO.read(PeaShooter.class.getResourceAsStream("/image/wu/peaShooter2.png"));
            rw4 = ImageIO.read(PeaShooter.class.getResourceAsStream("/image/wu/peaShooter3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
