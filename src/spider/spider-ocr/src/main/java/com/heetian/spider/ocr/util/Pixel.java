package com.heetian.spider.ocr.util;

import java.awt.Color;


public class Pixel implements Comparable<Pixel>{
	
	private int x;			// x坐标
	private int y;			// y坐标
	private int r;			// RGB R
	private int g;			// RGB G
	private int b;			// RGB B
	private int rgb;		// RGB
	private Integer dis;	// 距离其他点平均距离
	
	
	public Pixel(int x, int y, int r, int g, int b){
		this.x = x;
		this.y = y;
		this.r = r;
		this.g = g;
		this.b = b;
		this.rgb = new Color(rgb).getRGB();
		
	}

	
	public Pixel(int x, int y, int rgb){
		this.x = x;
		this.y = y;
		this.rgb = rgb;
		
		Color c = new Color(rgb);
		this.r = c.getRed();
		this.g = c.getGreen();
		this.b = c.getBlue();
		
	}
	
	public int getX() {
		return this.x;
	}
	
	
	public int getY() {
		return this.y;
	}
	
	
	public int getHue() {
		float[] hsb1 = Color.RGBtoHSB(this.r, this.g, this.b,null);
		return Math.round(hsb1[0]*100);
	}
	
	
	public int getRed() {
		return this.r;
	}


	public int getGreen() {
		return this.g;
	}


	public int getBlue() {
		return this.b;
	}
	
	public int getRGB() {
		return this.rgb;
	}
	
	public int getDis() {
		return dis;
	}

	public void setDis(int dis) {
		this.dis = dis;
	}
	
	
	@Override
	public int compareTo(Pixel arg0) {
		return this.dis.compareTo(arg0.dis);
	}
	
	
	
	@Override
	public String toString() {	
		return "{" + this.x + "," + this.y + "," + this.getHue() + "}";
	}


}
