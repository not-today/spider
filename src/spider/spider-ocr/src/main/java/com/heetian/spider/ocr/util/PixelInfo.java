package com.heetian.spider.ocr.util;

import java.util.ArrayList;
import java.util.List;
//http://www.docin.com/p-637349797.html
public class PixelInfo implements Comparable<PixelInfo> {
	// 颜色值
	private int rgb;
	// 出现次数
	private Integer count;
	// 出现顺序
	private Integer index;
	// 最小X坐标
	private int minX;
	// 最大X坐标
	private int maxX;
	// 最小Y坐标
	private int minY;
	// 最大Y坐标
	private int maxY;
	private List<Point> points;
	public PixelInfo(int rgb, int x, int y, int index) {
		this.count = 1;
		this.index = index;
		this.rgb = rgb;
		this.minX = x;
		this.minY = y;
		this.maxX = x;
		this.maxY = y;
		this.points = new ArrayList<Point>();
		this.points.add(new Point(x, y));
	}
	public void addPoint(int x, int y) {
		this.minX = Math.min(x, this.minX); // 计算最小X坐标
		this.maxX = Math.max(x, this.maxX); // 计算最大X坐标
		this.minY = Math.min(y, this.minY); // 计算最小Y坐标
		this.maxY = Math.max(y, this.maxY); // 计算最大Y坐标
		this.count++; // 统计出现次数
		this.points.add(new Point(x, y)); // 坐标点
	}
	public Integer getCount() {
		return count;
	}
	public Integer getIndex() {
		return index;
	}
	public int getRgb() {
		return rgb;
	}
	public int getMinX() {
		return minX;
	}
	public int getMaxX() {
		return maxX;
	}
	public int getMinY() {
		return minY;
	}
	public int getMaxY() {
		return maxY;
	}
	public List<Point> getPoints() {
		return points;
	}
	@Override
	public int compareTo(PixelInfo pixelInfo) {
		return pixelInfo.getCount().compareTo(this.getCount());
	}
	public class Point {
		private int x;
		private int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
	}
	public static void main(String[] args) {
		int x = 0;
		for(int y=0;y<10;y++)
			System.out.println(x++);
		System.out.println(x);
	}
	@Override
	public String toString() {
		return "count[" + count+"]";
	}
}
