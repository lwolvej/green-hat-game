package com.nuist.ui.chen;

import com.nuist.exception.MyException;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * @author ChenManYu
 */
public class SmallCaps {
    BufferedImage[] images;
    int index;
    BufferedImage rw1, rw2, image;
    int randomx, randomy;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    Random random = new Random();


    public SmallCaps() {
        intitSmallCaps();
        images = new BufferedImage[]{rw1, rw2};
        index = 0;
        randomy = random.nextInt(MenuFrame.HEIGHT);
        randomx = random.nextInt(MenuFrame.WIDTH);

    }


    private void intitSmallCaps() {

        try {
            rw1 = ImageIO.read(SmallCaps.class.getResourceAsStream("/image/chen/800.png"));
            rw2 = ImageIO.read(SmallCaps.class.getResourceAsStream("/image/chen/801.png"));
        } catch (IOException e) {
            throw new MyException("IO Exception");
        }
    }


    public void step() {
        image = images[index++ / 90 % images.length];
    }


    public void paintSmallCaps(Graphics g) {
        if ((randomx <= 720 && randomx >= 420) || (randomy > 570)) {

        } else
            g.drawImage(image, randomx, randomy, WIDTH, HEIGHT, null);
    }


    public BufferedImage[] getImages() {
        return images;
    }


    public void setImages(BufferedImage[] images) {
        this.images = images;
    }


    public int getIndex() {
        return index;
    }


    public void setIndex(int index) {
        this.index = index;
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


    public BufferedImage getImage() {
        return image;
    }


    public void setImage(BufferedImage image) {
        this.image = image;
    }


    public int getRandomx() {
        return randomx;
    }


    public void setRandomx(int randomx) {
        this.randomx = randomx;
    }


    public int getRandomy() {
        return randomy;
    }


    public void setRandomy(int randomy) {
        this.randomy = randomy;
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


}
