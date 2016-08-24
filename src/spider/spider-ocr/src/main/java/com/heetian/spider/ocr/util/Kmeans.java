package com.heetian.spider.ocr.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Kmeans {

	
	private List<Pixel> list;
	private Pixel[] centers;
	private Pixel[] newCenters;
	private List<Pixel>[] results;
	private int k = 1;
	private int length = 0;
	

	public Kmeans() {

	}

	/**
	 * 随机初始化Kmeans点 
	 * @param list
	 * @param k
	 */
	public Kmeans(List<Pixel> list, int k) {
		
		if(list == null){
			return;
		}
		// 初始化数据
		this.list = list;
		this.length = list.size();
		this.k = k;
		this.centers = this.newCenters = initCenters();

	}
	
	
	/**
	 * 初始化中心点
	 * @return
	 */
	private Pixel[] initCenters(){
		
		
		List<Pixel> centers = new ArrayList<Pixel>();	// 中心点
		
		// 随机一些点，用于计算平局距离
		List<Pixel> samples = new ArrayList<Pixel>();
		for(int i = 0; i < this.length / 5; i++){
			samples.add(this.list.get((int)(Math.random()*this.length)));
		}
		
		// 计算两两之间距离,求平均值
		for(int i = 0; i < this.length; i++){
			Pixel p1 = this.list.get(i);
			// 计算p1 到其他点的平均距离
			int sum = 0;
			for(int j = 0; j < samples.size(); j++){
				Pixel p2 = samples.get(j);
				sum += distance(p1, p2);
			}
			p1.setDis(sum/samples.size());
		}
		
		
		// 根据平均距离进行排序
		Collections.sort(this.list);
		// 从list中选取平均距离前60%作为备选中心点（排除噪点）
		List<Pixel> spareCenters = this.list.subList(0,this.list.size() * 6 / 10);
		
		// 随机一个平均距离偏小的点作为第一中心点
		int random = (int)(Math.random()*10);
		centers.add(spareCenters.get(random));
		spareCenters.remove(random);
		
		
		// 循环迭代筛选出K个互相之间距离尽可能远的点
		for(int i = 1; i < this.k; i++){
			int maxDis = -1, index = -1;
			for(int j = 0; j < spareCenters.size(); j++){
				Pixel p = this.list.get(j);
				int dis = distance(p, centers);
				if(maxDis == -1 || maxDis < dis){
					maxDis = dis;
					index = j;
				}
			}
			centers.add(spareCenters.get(index));
			spareCenters.remove(index);
		}
		
		return centers.toArray(new Pixel[this.k]);
		
	}
	
	

	
	
	@SuppressWarnings("unchecked")
	public List<Pixel>[] execute() {
		
		if(this.list == null){
			return null;
		}
		
		while(isGoon()){
			this.results = new ArrayList[k];
			for (int i = 0; i < k; i++) {  
	            this.results[i] = new ArrayList<Pixel>();  
	        }
			
			this.centers = this.newCenters;
			
			for(Pixel p : this.list){
				Integer minIndex  = null;			
				Integer minDis = null;				
				
				for(int i = 0; i < k; i++){
					Pixel c = centers[i];
					int dis = distance(p, c);
					if(minDis != null && minDis < dis){
						continue;
					}
					minIndex = i;
					minDis = dis;
				}
				this.results[minIndex].add(p);
			}
			
			this.newCenters = calNewCenter();
		}
				
		return optimize();
		
	}
	
	
	/**
	 * 判断是否继续Kmeans算法
	 * @return
	 */
	private boolean isGoon(){
		
		if(this.results == null || this.newCenters == null){
			return true;
		}
		
		boolean bool = false;
		for(int i = 0; i < k; i++){
			
			if(this.centers[i] == null ){
				continue;
			}
			
			if(distance(this.centers[i], this.newCenters[i]) > 1){
				bool = true;
			}
		}
	
		return bool;
		
	}
	
	
	/**
	 * 针对Kmeans聚类结果进行优化
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Pixel>[] optimize(){
		
		List<List<Pixel>> temp1 = new ArrayList<List<Pixel>>();
		for(List<Pixel> pixels : this.results){
			
			sortByH(pixels);							// 根据X坐标进行排序
			int start = -1,last = -1;
			for(int i = 0; i < pixels.size(); i++){		// 按照X坐标连续性将cluster再次分组
				
				if(start == -1){
					start = last = i;
					continue;
				}
				
				if(i == pixels.size() - 1){
					List<Pixel> list = new ArrayList<Pixel>();
					list.addAll(pixels.subList(start, pixels.size()));
					temp1.add(list);
					continue;
				}
				
				if(pixels.get(i).getHue() - pixels.get(last).getHue() <= 4){
					last = i;
					continue;
				}
				
				
				List<Pixel> list = new ArrayList<Pixel>();
				list.addAll(pixels.subList(start, i));
				temp1.add(list);
				
				start = last = i;
			}
		}
		
		
		// 根据X连通性对数据进行优化
		List<List<Pixel>> temp = new ArrayList<List<Pixel>>();
		for(List<Pixel> pixels : temp1 ){
			
			sortByX(pixels);							// 根据X坐标进行排序
			int start = -1,last = -1;
			for(int i = 0; i < pixels.size(); i++){		// 按照X坐标连续性将cluster再次分组
				
				if(start == -1){
					start = last = i;
					continue;
				}
				
				if(i == pixels.size() - 1){
					List<Pixel> list = new ArrayList<Pixel>();
					list.addAll(pixels.subList(start, pixels.size()));
					temp.add(list);
					continue;
				}
				
				if(pixels.get(i).getX() - pixels.get(last).getX() <= 1){
					last = i;
					continue;
				}
				
				
				List<Pixel> list = new ArrayList<Pixel>();
				list.addAll(pixels.subList(start, i));
				temp.add(list);
				
				start = last = i;
			}
		}
		
		
		return temp.toArray(new List[temp.size()]);
	}
	
	
	
	
	
	
	
	private Pixel[] calNewCenter(){
		
		Pixel[] newCenters = new Pixel[k];
		
		for(int i = 0; i < k; i++){
			
			List<Pixel> pixels = this.results[i];
			if(pixels == null || pixels.size() == 0){
				continue;
			}
			
			int sumX = 0, sumY = 0, sumR = 0, sumG = 0, sumB = 0;
			for (Pixel p : pixels) {
				sumX += p.getX();
				sumY += p.getY();
				sumR += p.getRed();
				sumG += p.getGreen();
				sumB += p.getBlue();
			}
			
			newCenters[i] = new Pixel(sumX/pixels.size(), sumY/pixels.size(),sumR/pixels.size(), sumG/pixels.size(), sumB/pixels.size());
			
		}
		
		return newCenters;
	}
	
	
	/**
	 * 计算两个像素点之间的颜色差值
	 * @param p1
	 * @return
	 */
	private int distance(Pixel p1, Pixel p2) {
		
		if(p1 == null || p2 == null){
			return 0;
		}
		
		double dis = Math.pow(p1.getX() - p2.getX(), 2) +  Math.pow(p1.getY() - p2.getY(), 2)
				+ Math.pow(p1.getRed() - p2.getRed(), 2) +  Math.pow(p1.getGreen() - p2.getGreen(), 2) + Math.pow(p1.getBlue() - p2.getBlue(), 2);
		
		return (int) Math.sqrt(dis);
		
	}
	
	
	
	/**
	 * 计算一个点到多个点之间的平均距离
	 * @param p
	 * @param list
	 * @return
	 */
	private int distance(Pixel p, List<Pixel> list){
		
		if(p == null || list == null || list.size() == 0){
			return 0;
		}
		
		int sum = 0;
		for(Pixel p0 : list){
			sum += distance(p, p0);
		}
		
		return sum/list.size();
	}
	
	
	
	/**
	 * 根据X坐标进行排序
	 * @param list
	 * @return
	 */
	private void sortByX(List<Pixel> list){
		
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getX() > list.get(j).getX()) {
					Pixel temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		
	}
	
	

	/**
	 * 根据色调进行排序
	 * @param list
	 * @return
	 */
	private void sortByH(List<Pixel> list){
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).getHue() > list.get(j).getHue()) {
					Pixel temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		
	}
	

}

