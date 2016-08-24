package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.Kmeans;
import com.heetian.spider.ocr.util.Pixel;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 湖北省验证码识别程序
 * @author tst
 *
 */
public class HuBei extends AbstractRecognized{
	public HuBei(){
		init("stencil"+File.separator+"hubei",Color.BLACK,distiguingsh_many);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		// 第一步 ： 消除灰色背景及干扰线
		int h = img.getHeight();
		int w = img.getWidth();
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				if(isBg383gt(img.getRGB(x, y),400)){
					img.setRGB(x, y, Color.WHITE.getRGB());
				}
			}
		}
		img = grayFilter(img);
		img = kmeansFilter(img);
		img = smoothFilter(img);
		return img;
	}
	
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		img = img.getSubimage(0, 0, img.getWidth()-25, img.getHeight());
		List<BufferedImage> list = colorClusters(img,Color.WHITE.getRGB());
		for(BufferedImage tmp:list){
			int h = tmp.getHeight();
			int w = tmp.getWidth();
			for(int x=0;x<w;x++){
				for(int y=0;y<h;y++){
					if(tmp.getRGB(x, y)==Color.BLACK.getRGB())
						tmp.setRGB(x, y, Color.WHITE.getRGB());
					else
						tmp.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		return listToArray(list);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String result = "";
		for(BufferedImage subimage:imgs){
			String tmp = ImageUtils.recognizedByPixelMatch(ImageUtils.getPixel(subimage,Color.BLACK.getRGB()), stencil);
			if("+".equals(tmp)){
				if(!result.contains("+")){
					result = result+tmp;
				}
			}else if("-".equals(tmp)){
				if(!result.contains("-")){
					result = result+tmp;
				}
			}else if("y".equals(tmp)){
				if(!result.contains("x")&&!result.contains("%")){
					result = result+"x";
				}
			}else if("=".equals(tmp)){
				
			}else{
				result = result+tmp;
			}
		}
		Pattern p = Pattern.compile("(\\d{1,2}){1}([+-x%]){1}(\\d{1}){1}");
		Matcher m = p.matcher(result);
		String arr[] = new String[3];
		if(m.find()){
			arr[0] = m.group(1);
			arr[1] = m.group(2);
			arr[2] = m.group(3);
		}
		if(arr[1]!=null&&"%".equals(arr[1]))
			arr[1] = "/";
        return ResultProcess.newInstance(ValidateType.count, arr);
	}
	/**
	 * 灰色过滤(用于背景色、干扰线是灰色，前景色是彩色)
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @throws IOException
	 */
	public BufferedImage grayFilter(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		// 根据颜色饱和度清除背景颜色
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				Color c = new Color(rgb);
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				float[] hsb = Color.RGBtoHSB(r, g, b,null);
				int saturation = Math.round(hsb[1]*100);	
				int brightness = Math.round(hsb[2]*100);
				if(saturation < 30 && brightness > 40){	// 将饱和度低于5的颜色替换为白色（灰色饱和度低）
					bufferedImage.setRGB(w, h, 0xFFFFFF);
				}
			}
		}
		return bufferedImage;
	}
	/**
	 * Kmeans 聚类分析
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public BufferedImage kmeansFilter(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		List<Pixel> pixels = new ArrayList<Pixel>();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb0 = bufferedImage.getRGB(w, h);
				if (rgb0 == -1)
					continue;// 白色背景
				// 计算像素点周围平均颜色
				List<Integer> list = new ArrayList<Integer>();
				list.add(rgb0);
				for (int x = w - 3; x <= w + 3; x++) {
					for (int y = h - 3; y <= h + 3; y++) {
						if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
							continue;
						}
						int rgb1 = bufferedImage.getRGB(x, y);
						if (rgb1 != -1 && isSimilarColor(rgb0, rgb1))
							list.add(rgb1);
					}
				}
				pixels.add(new Pixel(w, h, calAvgColor(list)));
			}
		}
		//Date start = new Date();
		// Kmeans聚类分析
		Kmeans kmeans = new Kmeans(pixels, 4);
		List<Pixel>[] results = kmeans.execute();
		//Date end = new Date();
		//System.out.println(end.getTime() - start.getTime());
		// 求取分组前几种颜色平均值
		int[] rgbs = new int[results.length];
		for (int i = 0; i < results.length; i++) {
			if (results[i].size() < 50) {
				rgbs[i] = -1;
				continue;
			}
			List<Integer> list = new ArrayList<Integer>();
			for (Pixel p : results[i]) {
				list.add(p.getRGB());
			}
			rgbs[i] = calAvgColor(list); // 求平均值
		}
		// 修正颜色
		for (int i = 0; i < results.length; i++) {
			for (Pixel p : results[i]) {
				bufferedImage.setRGB(p.getX(), p.getY(), rgbs[i]);
			}
		}
		return bufferedImage;
	}
	private static boolean isSimilarColor(int rgb1, int rgb2) {

		Color c1 = new Color(rgb1);
		float[] hsb1 = Color.RGBtoHSB(c1.getRed(), c1.getGreen(), c1.getBlue(), null);
		int h1 = Math.round(hsb1[0] * 100);
		Color c2 = new Color(rgb2);
		float[] hsb2 = Color.RGBtoHSB(c2.getRed(), c2.getGreen(), c2.getBlue(), null);
		int h2 = Math.round(hsb2[0] * 100);
		if (Math.abs(h1 - h2) <= 10) {
			return true;
		}
		return false;
	}
	/**
	 * 计算平均颜色
	 * 
	 * @param list
	 * @return
	 */
	private static int calAvgColor(List<Integer> list) {
		if (list == null || list.size() == 0) {
			return 0;
		}
		int sumR = 0, sumG = 0, sumB = 0;
		for (int rgb : list) {
			Color c = new Color(rgb);
			sumR += c.getRed();
			sumG += c.getGreen();
			sumB += c.getBlue();
		}
		return new Color(sumR / list.size(), sumG / list.size(), sumB / list.size()).getRGB();
	}
	/**
	 * 根据平滑度降噪
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @throws IOException
	 */
	public BufferedImage smoothFilter(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				// 计算像素点周围颜色值
				if(w <= 1 || h <= 1 || w >= width - 2 || h >= height -2){
					bufferedImage.setRGB(w, h, 0xFFFFFF);
					continue;
				}
				// 周围2圈相同颜色像素点个数
				int count = 0;
				for(int x = w - 2; x <= w + 2 ; x++ ){
					for(int y = h - 2; y <= h + 2; y++ ){
						if(Math.abs(x - w) == 2 || Math.abs(y - h) == 2){
							count += bufferedImage.getRGB(x, y) == rgb ? 1 : 0;
							continue;
						}
						if(Math.abs(x - w) == 1 && Math.abs(y - h) == 1){
							count += bufferedImage.getRGB(x, y) == rgb ? 2 : 0;
							continue;
						}
						if(x == w && y == h){
							continue;
						}
						count += bufferedImage.getRGB(x, y) == rgb ? 8 : 0;
					}
				}
				if(count < 9) bufferedImage.setRGB(w, h, 0xFFFFFF);
			}
		}
		return bufferedImage;
	}
	/**
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public BufferedImage binaryzation(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		BufferedImage binaryBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		// 灰度化
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				int gray = getGray(rgb);
				
				if(gray > 200){
					binaryBufferedImage.setRGB(w, h, 0xFFFFFF);
				}
				else{
					binaryBufferedImage.setRGB(w, h, 0x000000);
				}
			}
		}
		return binaryBufferedImage;
	}
	/**
	 * 灰度化是获取灰色颜色值
	 * @param rgb
	 * @return
	 */
	private int getGray(int rgb){
	
		Color c = new Color(rgb);
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return (r*30 + g*59 + b*11)/100;
		
	}
	/**
	 * 按X轴投影切图
	 * @param image  图片
	 * @param bg 背景颜色
	 * @param interval 切图间隔（当间隔 >= interval时，  将图切开）
	 * @return
	 */
	public static  List<BufferedImage> projectionCluster(BufferedImage image, int bg, int interval){
		// 切分字符
		Map<String,Integer> map = new LinkedHashMap<String, Integer>();
		int lastSign = 0,lastW = -1;
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				
				int rgb = image.getRGB(w, h);
				if(rgb == bg) continue;
				
				if(lastW == -1 || w - lastW > interval)  lastSign++;
				map.put(w + "-" + h, lastSign);
				lastW = w;
			}
		}
		// 将sign相同的像素点存储在一个List中
		Map<Integer, Set<String>> clusters = new LinkedHashMap<Integer, Set<String>>();
		for(String key : map.keySet()){
			Integer sign = map.get(key);
			if(clusters.get(sign) != null){
				clusters.get(sign).add(key);
				continue;
			}
			Set<String>  cluster = new HashSet<String>();
			cluster.add(key);
			clusters.put(sign, cluster);
		}
		// 切分字符
		List<BufferedImage> results = new ArrayList<BufferedImage>();
		for(Entry<Integer, Set<String>> entry : clusters.entrySet()){
			Set<String> cluster = entry.getValue();
			if(cluster.size() < 10) continue;					// 删除碎片
			int minX = -1, minY = -1, maxX = -1, maxY = -1;		// 计算最小、最大 X坐标、Y坐标
			for(String item : cluster){
				int x = Integer.parseInt(item.substring(0,item.indexOf("-")));
				int y =  Integer.parseInt(item.substring(item.indexOf("-") + 1));
				minX = x < minX || minX == -1 ? x : minX;
				minY = y < minY || minY == -1 ? y : minY;
				maxX = x > maxX || maxX == -1 ? x : maxX;
				maxY = y > maxY || maxY == -1 ? y : maxY;
			}
			BufferedImage binaryBufferedImage = new BufferedImage(maxX - minX + 1, maxY - minY + 1, BufferedImage.TYPE_BYTE_BINARY);
			for(int x = 0; x < binaryBufferedImage.getWidth(); x++){
				for(int y = 0; y< binaryBufferedImage.getHeight(); y++){
					String key = (x + minX) + "-" + (y + minY);
					binaryBufferedImage.setRGB(x , y , cluster.contains(key) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
				}
			}
			results.add(binaryBufferedImage);
		}
		return results;
	}
}
