package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

public class SHProcess {
	
	
	public static void main(String[] args) throws IOException
	{

		
		File testDataDir = new File("E:/OCR/SH");
		
		for (File file : testDataDir.listFiles())
		{
			if(file.isDirectory()){
				continue;
			}
			
			BufferedImage bufferedImage = ImageIO.read(file);
			
			String fileName = file.getName().substring(0,file.getName().indexOf("."));
			
			
			// 清除背景颜色
			bufferedImage = clearBG(bufferedImage);
			String dir = testDataDir.getAbsolutePath()+"/01";
			File destF = new File(dir);
			if (!destF.exists()){
				destF.mkdirs();
			}
			ImageIO.write(bufferedImage, "png", new File(dir, fileName + ".png"));
			
			
			
			// 清除干扰线
			bufferedImage = recgLine(bufferedImage);
			dir = testDataDir.getAbsolutePath()+"/02";
			destF = new File(dir);
			if (!destF.exists()){
				destF.mkdirs();
			}
			ImageIO.write(bufferedImage, "png", new File(dir, fileName + ".png"));
			
			
			
			// 清除单独噪点
			bufferedImage = clearNosie(bufferedImage);
			dir = testDataDir.getAbsolutePath()+"/03";
			destF = new File(dir);
			if (!destF.exists()){
				destF.mkdirs();
			}
			ImageIO.write(bufferedImage, "png", new File(dir, fileName + ".png"));
			
		}

	}
	
	
	
	
	/**
	 * 清除背景颜色
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage clearBG(BufferedImage bufferedImage) throws IOException{
		
		final int width = bufferedImage.getWidth();
		final int height = bufferedImage.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 清除背景颜色
		for(int w = 0; w < width; w++){
			for(int h = 0; h < height; h++){
				int rgb = bufferedImage.getRGB(w, h);
				image.setRGB(w, h, getGray(rgb) < 180 ? rgb : Color.WHITE.getRGB());
			}
		}
		
		return image;
		
	}
	
	
	
	/**
	 * 识别直线
	 * @param bufferedImage
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage recgLine(BufferedImage bufferedImage) throws IOException{
		
		
		Date startTime = new Date();
		
		final int width = bufferedImage.getWidth();
		final int height = bufferedImage.getHeight();
		
		List<int[]> pixels = new ArrayList<int[]>();
		for(int w = 0; w < width; w++){
			for(int h = 0; h < height; h++){
				if(Color.WHITE.getRGB() == bufferedImage.getRGB(w, h)) continue;
				pixels.add(new int[]{w,h});
			}
		}
		
		
		
		for(int i = 0; i < pixels.size(); i++){
			// 起始点
			int[] start = pixels.get(i);
			int x0 = start[0], y0 = start[1];
			for(int j = pixels.size() - 1; j > i; j--){
				// 结束点
				int[] end = pixels.get(j);
				int x1 = end[0], y1 = end[1];
				
				if(x1 == x0 || y1 == y0) continue;
				
				int n = Math.max(Math.abs(x1 - x0), Math.abs(y1 - y0));
				if(n < 30) continue;
				
				List<int[]> list = ddaLine(x0, y0, x1, y1);
				int count = 0;
				for(int[] pixel : list){
					if(Color.WHITE.getRGB() == bufferedImage.getRGB(pixel[0], pixel[1])) count++;
				}
				
				if(count > 5) continue;
				
				for(int[] pixel : list){
					bufferedImage.setRGB(pixel[0], pixel[1], Color.WHITE.getRGB());
				}
				
			}
			
			
		}
		
		Date endTime = new Date();
		
		System.out.println(endTime.getTime() - startTime.getTime());
		 
	
		return bufferedImage;
	}
	
	
	
	/**
	 * 清除单个噪点
	 * @param bufferedImage
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage clearNosie(BufferedImage bufferedImage) throws IOException{
		
		final int width = bufferedImage.getWidth();
		final int height = bufferedImage.getHeight();
		
		// 清除孤点
		for(int w = 0; w < width; w++){
			for(int h = 0; h < height; h++){
				int rgb = bufferedImage.getRGB(w, h);
				// 统计周围黑色像素点个数
				int num = 0;
				for(int x = w - 1; x <= w + 1; x++ ){
					for(int y = h - 1; y <= h + 1; y++){
						if(x < 0 || y < 0 || x >= width || y >= height) continue;
						num += Color.WHITE.getRGB() == bufferedImage.getRGB(x, y) ? 0 : 1;
					}
				}
				if(Color.WHITE.getRGB() != rgb && num < 2) bufferedImage.setRGB(w, h, Color.WHITE.getRGB());
			}
		}
		
		return bufferedImage;
	}
	
	
	
	
	/**
	 * 根据两个点像素坐标，计算直线上所有像素点
	 * @param a
	 * @param b
	 * @return
	 */
	public static List<int[]> ddaLine(int x0, int y0, int x1, int y1){
		List<int[]> pixels = new ArrayList<int[]>();
		// 像素差
		int dx = x1 - x0, dy = y1 - y0;
		// 像素个数
		int n = Math.max(Math.abs(dx), Math.abs(dy));
		// 像素步长
		float stepx = (float)dx/n, stepy = (float)dy/n;
		
		for(int k = 0; k <= n; k++){
			pixels.add(new int[]{Math.round(x0 + k * stepx), Math.round(y0 + k * stepy)});
		}
		
		return pixels;
	}
	/**
	 * 根据X轴投影切图
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	/*public static List<BufferedImage> cutByProjectionX(BufferedImage bufferedImage)throws IOException{
		
		List<BufferedImage> list = OCRUtils.projectionCluster(bufferedImage,Color.WHITE.getRGB());
		return list;
	}*/
	/**
	 *	获取灰度值ֵ
	 * @param rgb
	 * @return
	 */
	private static int getGray(int rgb){
	
		Color c = new Color(rgb);
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return (r*30 + g*59 + b*11)/100;
		
	}
}