package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.ArrayUtils;

import com.heetian.spider.ocr.util.PixelInfo;
import com.heetian.spider.ocr.util.PixelInfo.Point;
public class GZImgageHandle {
	private static final Map<String,List<int[][]>> map = new HashMap<String,List<int[][]>>();
	static{
		File dirs = new File("C:\\Users\\tst\\Desktop\\stencil\\");
		if(dirs.exists()){
			for(File dir:dirs.listFiles()){
				List<int[][]> pixels = new ArrayList<>();
				for(File file :dir.listFiles()){
					try {
						pixels.add(getPixel(ImageIO.read(file)));
					} catch (IOException e) {
						e.printStackTrace();
						System.exit(0);
					}
				}
				map.put(dir.getName(), pixels);
			}
		}
	}
	private static int[][] getPixel(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		//System.out.println(width+":"+height);
		int[][] pixel = new int[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if(Color.WHITE.getRGB()==image.getRGB(x, y)){
					pixel[y][x] = 1;
				}
			}
		}
		//printImage(pixel);
		return pixel;
	}
	public static void printImage(int[][] pixel){
		for (int y = 0; y < pixel.length; y++) {
			for (int x = 0; x < pixel[y].length; x++) {
				if(pixel[y][x]==1){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	static int x =1;
	public static void main(String[] args) throws IOException {
		//copy();
		run();
	}

	private static void run() throws IOException {
		//File testDataDir = new File("C:\\Users\\tst\\Desktop\\GD");
		File testDataDir = new File("C:\\Users\\tst\\Desktop\\gz");
		for (File file : testDataDir.listFiles()) {
			if (file.isDirectory()) {
				continue;
			}
			BufferedImage image = ImageIO.read(file);
			// 清除背景颜色
			image = cleanBg(image,testDataDir.getAbsolutePath() + "/01", file.getName());
			// 根据平滑度降噪
			image = filterBySmooth(image,testDataDir.getAbsolutePath() + "/02", file.getName());
			// 更具颜色切图
			cutByColor(image, testDataDir.getAbsolutePath() + "/03",file.getName());
			// // 根据颜色切图
			// bufferedImage =
			// cutByColor(bufferedImage,testDataDir.getAbsolutePath()+"/03",file.getName());
			//
			// bufferedImage =
			// grayScale(bufferedImage,testDataDir.getAbsolutePath()+"/04",file.getName());
		}
	}

	/**
	 * 清除背景色 ①广东验证码特点：背景色是纯色且像素点个数最多 ②通过统计相同颜色像素点个数,计算出背景颜色值,进一步清除背景颜色
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage cleanBg(BufferedImage bufferedImage,String dir, String fileName) throws IOException {
		File destF = new File(dir);
		if (!destF.exists()) {
			destF.mkdirs();
		}
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		// 按每个颜色统计其出现次数
		Map<Integer, PixelInfo> map = new HashMap<Integer, PixelInfo>();
		int index = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = bufferedImage.getRGB(x, y);
				PixelInfo stat = map.get(rgb);
				if (map.get(rgb) == null) {
					map.put(rgb, new PixelInfo(rgb, x, y, index++));
					continue;
				}
				// 添加坐标点(GDVerfyStat实体类中计算了像素点数量)
				stat.addPoint(x, y);
			}
		}
		int sum = 0;
		int num = 0;
		int max_count = 0;
		int max_key = 0;
		for (int rgb : map.keySet()) {
			PixelInfo pixelInfo = map.get(rgb);
			int cur_count = pixelInfo.getCount();
			sum = sum+cur_count;
			num = num +1;
			if(max_count<cur_count){
				max_count = cur_count;
				max_key = rgb;
			}
		}
		List<PixelInfo> list = new ArrayList<PixelInfo>();
		list.add(map.remove(max_key));//添加背景色到集合
		int avg_count = ((sum-max_count)/(num-1))/2;//求的除去背景色外的像素点出现的次数的平均数
		
		for (int rgb : map.keySet()) {
			PixelInfo pixelInfo = map.get(rgb);
			int cur_count = pixelInfo.getCount();
			if(cur_count<avg_count){//几个关键字符的颜色肯定大于像素点的平局次数（除去背景色）
				list.add(pixelInfo);
			}
		}
		for(PixelInfo pixelTmp :list){
			for (Point p : pixelTmp.getPoints()) {
				bufferedImage.setRGB(p.getX(), p.getY(), 0xFFFFFF);
			}
		}
		/*List<PixelInfo> list = new ArrayList<PixelInfo>();
		for (int rgb : map.keySet()) {
			list.add(map.get(rgb));
		}
		Collections.sort(list);
		// 清除背景色
		for (Point p : list.get(0).getPoints()) {
			bufferedImage.setRGB(p.getX(), p.getY(), 0xFFFFFF);
		}*/
		ImageIO.write(bufferedImage, "jpg", new File(dir, fileName));
		return bufferedImage;
	}
	/**
	 * 根据平滑度降噪
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @throws IOException
	 */
	public static BufferedImage filterBySmooth(BufferedImage bufferedImage,String dir, String fileName) throws IOException {
		File destF = new File(dir);
		if (!destF.exists()) {
			destF.mkdirs();
		}
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				// 周围2圈相同颜色像素点个数
				int count = 0;
				for (int x = w - 2; x <= w + 2; x++) {
					for (int y = h - 2; y <= h + 2; y++) {
						if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
							continue;
						}
						count += bufferedImage.getRGB(x, y) == rgb ? 1 : 0;
					}
				}
				if (count <= 5)
					bufferedImage.setRGB(w, h, 0xFFFFFF);
			}
		}
		ImageIO.write(bufferedImage, "png", new File(dir, fileName));
		return bufferedImage;
	}

	/**
	 * 根据颜色进行切图(广东验证码每个字颜色不一样且是纯色)
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static void cutByColor(BufferedImage bufferedImage, String dir,String fileName) throws IOException {
		File destF = new File(dir);
		if (!destF.exists()) {
			destF.mkdirs();
		}
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		// 按每个颜色统计其出现次数、最小X坐标、最大X坐标、最小Y坐标、最大Y坐标
		Map<Integer, PixelInfo> map = new HashMap<Integer, PixelInfo>();
		int index = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = bufferedImage.getRGB(x, y);
				PixelInfo stat = map.get(rgb);
				if (map.get(rgb) == null) {
					map.put(rgb, new PixelInfo(rgb, x, y, index++));
					continue;
				}
				// 记录每种颜色像素点坐标(GDVerfyStat中计算了最小坐标、最大坐标、自左向右出现顺序、坐标点个数等值)
				stat.addPoint(x, y);
			}
		}
		// 根据次数将颜色进行排序
		List<PixelInfo> list = new ArrayList<PixelInfo>();
		for (int rgb : map.keySet()) {
			list.add(map.get(rgb));
		}
		Collections.sort(list);
		// 背景色（白色）最多，其次就是文字，最后是未清除的噪点
		String[] content = new String[3];
		long begin = System.currentTimeMillis();
		for (int i = 1; i < list.size(); i++) {
			if(i>5){
				break;
			}
			PixelInfo stat = list.get(i);
			if(stat.getIndex()==1||stat.getIndex()==2||stat.getIndex()==3){
				int w = stat.getMaxX() - stat.getMinX() + 1;
				int h = stat.getMaxY() - stat.getMinY() + 1;
				BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_BYTE_BINARY);
				for (Point point : stat.getPoints()) {
					image.setRGB(point.getX() - stat.getMinX(),point.getY() - stat.getMinY(), 0xFFFFFF);
				}
				String name = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + stat.getIndex() + fileName.substring(fileName.lastIndexOf("."));
				int pixel[][] = getPixel(image);
				String ch = recognized(pixel);
				content[stat.getIndex()-1] = ch;
				//System.out.println(name+":"+ ch);
				ImageIO.write(image, "png", new File(dir, name));
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(fileName+":"+ArrayUtils.toString(content)+":"+(end-begin));
	}
	public static String recognized(int[][] pixel){
		Set<String> keySet = map.keySet();
		Map<String,Double> probability_max_all_char = new HashMap<String,Double>();
		for(String key :keySet){
			List<int[][]> pixels = map.get(key);
			double[] probabilitys = new double[pixels.size()];
			for(int x=0;x<pixels.size();x++){
				int[][] pixelTmp = pixels.get(x);
				probabilitys[x] = matchPixel(pixelTmp, pixel);
			}
			Arrays.sort(probabilitys);
			double sigle_char_max_Probability = probabilitys[probabilitys.length-1];
			probability_max_all_char.put(key, sigle_char_max_Probability);
		}
		Set<String> probability_key_set = probability_max_all_char.keySet();
		double max = 0;
		String content = "";
		for(String probability_key:probability_key_set){
			double tmp = probability_max_all_char.get(probability_key);
			if(max<tmp){
				max = tmp;
				content = probability_key;
			}
		}
		return content;
	}
	public static double matchPixel(int stencil[][],int target[][]){
		int total = 0;
		int match_total = 0;
		if(stencil.length<target.length){
			for (int y = 0; y < stencil.length; y++) {
				if(stencil[y].length<target[y].length){
					for (int x = 0; x < stencil[y].length; x++) {
						if(match(stencil[y][x], target[y][x])){
							match_total++;
						}
						total++;
					}
				}else{
					for (int x = 0; x < target[y].length; x++) {
						if(match(stencil[y][x], target[y][x])){
							match_total++;
						}
						total++;
					}
				}
			}
		}else{
			for (int y = 0; y < target.length; y++) {
				if(stencil[y].length<target[y].length){
					for (int x = 0; x < stencil[y].length; x++) {
						if(match(stencil[y][x], target[y][x])){
							match_total++;
						}
						total++;
					}
				}else{
					for (int x = 0; x < target[y].length; x++) {
						if(match(stencil[y][x], target[y][x])){
							match_total++;
						}
						total++;
					}
				}
			}
		}
		return ((double)match_total)/total;
	}
	public static boolean match(int stencil,int target){
		if(stencil==target)
			return true;
		return false;
	}
	public static void count(String dir) throws IOException {
		File testDataDir = new File("C:\\Users\\tst\\Desktop\\stencil\\"+dir);
		File[] files = testDataDir.listFiles();
		int counts[] = new int[files.length];
		for(int z=0;z<files.length;z++){
			File tmp = files[z];
			BufferedImage image = ImageIO.read(tmp);
			int width = image.getWidth();
			int height = image.getHeight();
			int count = 0;
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if(Color.WHITE.getRGB()==image.getRGB(x, y)){
						count++;
					}
				}
			}
			counts[z] = count;
		}
		Arrays.sort(counts);
		System.out.println(dir+":"+ArrayUtils.toString(counts));
	}
}
