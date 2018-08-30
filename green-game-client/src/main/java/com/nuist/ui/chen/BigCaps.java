package com.nuist.ui.chen;

import com.nuist.exception.MyException;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author ChenManYu
 */
public class BigCaps {

	private BufferedImage [] images;

	private BufferedImage rw1,rw2,rw3;
	
	private BufferedImage [] images1;
	private BufferedImage re1,re2,re3;

	private BufferedImage image,image1;

	private int score;
	private int x,y;
	private static final int WIDTH=80;
	private static final int HEIGHT=100;
	
	
	int index;
	
	public BigCaps() {
		score = 0;
		x=200;
		y=260;	
		initBigCaps();
		images=new BufferedImage[]{rw1,rw2,rw3};	
		images1=new BufferedImage[]{re1,re2,re3};
		index=0;
	
	}
	public void step(){
		image = images[index++ / 80 % images.length];
		image1 = images1[index++ / 10 % images1.length];
	}

	public void paintBigCaps(Graphics g){
		g.drawImage(image,x,y,WIDTH,HEIGHT,null);
		g.drawImage(image1,0,280,1200,406,null);
		
	}
	
	public void initBigCaps() {
		try {

//			rw1=ImageIO.read(new File("image/831.png"));
//			rw2=ImageIO.read(new File("image/831.png"));
//			rw3=ImageIO.read(new File("image/833.png"));
//
//			re1=ImageIO.read(new File("image/841.png"));
//			re2=ImageIO.read(new File("image/842.png"));
//			re3=ImageIO.read(new File("image/843.png"));

			rw1 = ImageIO.read(BigCaps.class.getResourceAsStream("/image/chen/831.png"));
			rw2 = ImageIO.read(BigCaps.class.getResourceAsStream("/image/chen/831.png"));
			rw3 = ImageIO.read(BigCaps.class.getResourceAsStream("/image/chen/833.png"));

			re1 = ImageIO.read(BigCaps.class.getResourceAsStream("/image/chen/841.png"));
			re2 = ImageIO.read(BigCaps.class.getResourceAsStream("/image/chen/842.png"));
			re3 = ImageIO.read(BigCaps.class.getResourceAsStream("/image/chen/843.png"));
			
		} catch (IOException e) {
			throw new MyException("IO Exception!");
		}
	
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
}
