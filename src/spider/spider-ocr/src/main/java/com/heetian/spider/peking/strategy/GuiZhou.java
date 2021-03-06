package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 贵州省验证码识别程序
 * @author tst
 *
 */
public class GuiZhou extends AbstractRecognized{
	public GuiZhou(){
		init("stencil"+File.separator+"guizhou",Color.BLACK,distiguingsh_many);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		img = clearBG(img);// 清除背景颜色
		img = ImageUtils.kmeansFilter(img);
		img = ImageUtils.smoothFilter(img);
		//img = binaryzation(img);
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		List<BufferedImage> subimages = colorClusters(img, Color.WHITE.getRGB(),50);
		return listToArray(subimages);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String[] arr = new String[3];
		int index = 0;
		boolean arrOver = false;
		for(BufferedImage subimage:imgs){
			String s = ImageUtils.recognizedByPixelMatch(ImageUtils.getPixel(subimage,Color.BLACK.getRGB()), stencil);
			if(index==0){
				if(s.matches("\\d+")){
					arr[index] = s;
					index++;
				}
			}else if(index==1){
				if(s.matches("\\+")||s.matches("-")){
					arr[index] = s;
					index++;
				}
			}else if(index==2){
				if(s.matches("\\d+")){
					arr[index] = s;
					arrOver = true;
					break;
				}
			}
		}
		if(!arrOver)
			throw new ImageConverte("识别出错,不完整:"+Arrays.toString(arr));
		return ResultProcess.newInstance(ValidateType.count, arr);
	}
	/**
	 * 清除背景颜色、切出左侧30像素、上面10像素、右侧30像素空白
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public BufferedImage clearBG(BufferedImage bufferedImage){
		BufferedImage image = new BufferedImage(bufferedImage.getWidth() - 150, 30, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgb = bufferedImage.getRGB(x + 30, y + 10);
				image.setRGB(x, y, getGray(rgb) < 230 ? rgb : 0xFFFFFF);
			}
		}
		return image;
	}
	/**
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public BufferedImage binaryzation(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		BufferedImage binaryBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				binaryBufferedImage.setRGB(w, h, rgb == -1 ? 0xFFFFFF : 0x000000);
			}
		}
		return binaryBufferedImage;
	}

	/**
	 * 获取灰度值ֵ
	 * 
	 * @param rgb
	 * @return
	 */
	private static int getGray(int rgb) {
		Color c = new Color(rgb);
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return (r * 30 + g * 59 + b * 11) / 100;
	}
	//--------------------------------------------
	/**
	 * 根据图形联通性原则对二值图像进行分割
	 * @param image
	 * @param bg   背景色
	 * @param wast 图片总像素小于多少个，则舍去该图片
	 * @return
	 */
	public static List<BufferedImage> twoPassCluster(BufferedImage image, int bg,int wast){
		Map<String,Integer> map = new LinkedHashMap<String, Integer>();
		int lastSign = 0;
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {				
				if(image.getRGB(w, h) == bg) 
					continue;
				// 循环计算相邻标记
				Integer minSign = null;
				List<Integer> signs = new ArrayList<Integer>();
				for(int x = w - 1; x <= w; x++){
					for(int y = h - 1; y <= h + 1; y++){
						Integer sign = map.get(x+"-"+y);
						if(sign == null) 
							continue;
						signs.add(sign);
						minSign = minSign == null || sign < minSign ? sign : minSign;
					}
				}
				// 如果相邻像素点未标记，则使用  lastSign + 1 作为本像素点标记
				if(minSign == null){
					map.put(w + "-" + h, ++lastSign);
					continue;
				}
				// 如果相邻像素点有标记，则使用相邻像素点的最小标记作为本像素点标记
				map.put(w + "-" + h, minSign);
				// 如果相邻像素点有多个标记，则需记录这些像素点之间的关系（代表这些像素点需要合并）
				signs.remove(minSign);
				for(Integer sign : signs ){
					for(String key : map.keySet()){
						if(map.get(key) == sign) map.put(key, minSign);
					}
				}
			}
		}
		// 将sign相同的像素点存储在一个List中
		Map<Integer, List<int[]>> clusters = new LinkedHashMap<Integer, List<int[]>>();
		for(String key : map.keySet()){
			Integer sign = map.get(key);
			int x = Integer.parseInt(key.substring(0, key.indexOf("-")));
			int y = Integer.parseInt(key.substring(key.indexOf("-") + 1));
			if(clusters.get(sign) != null){
				clusters.get(sign).add(new int[]{x,y});
				continue;
			}
			List<int[]>  cluster = new ArrayList<int[]>();
			cluster.add(new int[]{x,y});
			clusters.put(sign, cluster);
		}
		// 切分字符
		List<BufferedImage> results = new ArrayList<BufferedImage>();
		for(Entry<Integer, List<int[]>> entry : clusters.entrySet()){
			List<int[]> cluster = entry.getValue();
			int minX = -1, minY = -1, maxX = -1, maxY = -1;
			for(int[] pixel : cluster){
				minX = pixel[0] < minX || minX == -1 ? pixel[0] : minX;
				minY = pixel[1] < minY || minY == -1 ? pixel[1] : minY;
				maxX = pixel[0] > maxX || maxX == -1 ? pixel[0] : maxX;
				maxY = pixel[1] > maxY || maxY == -1 ? pixel[1] : maxY;
			}
			BufferedImage binaryBufferedImage = new BufferedImage(maxX - minX + 1, maxY - minY + 1, BufferedImage.TYPE_BYTE_BINARY);
			for(int[] pixel : cluster){
				binaryBufferedImage.setRGB(pixel[0] - minX , pixel[1] - minY, 0xFFFFFF);
			}
			if(binaryBufferedImage.getWidth() * binaryBufferedImage.getHeight() >= wast){
				for (int x = 0; x < binaryBufferedImage.getWidth(); x++) {
					for (int y = 0; y < binaryBufferedImage.getHeight(); y++) {
						binaryBufferedImage.setRGB(x, y, binaryBufferedImage.getRGB(x,y)==Color.BLACK.getRGB() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
					}
				}				
				results.add(binaryBufferedImage);
			}
		}
		return results;
	}	
	/**
	 * 根据字符颜色切图(用于纯色字符)
	 * @param image
	 * @param bg
	 * @return
	 */
	public static List<BufferedImage> colorClusters(BufferedImage image, int bg,int wast){
		// 将颜色相同的像素点存储在一个List中
		Map<Integer, List<int[]>> clusters = new LinkedHashMap<Integer, List<int[]>>();
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				int rgb = image.getRGB(w, h);
				if(rgb == bg) continue;
				if(clusters.get(rgb) != null){
					clusters.get(rgb).add(new int[]{w,h});
					continue;
				}
				List<int[]>  cluster = new ArrayList<int[]>();
				cluster.add(new int[]{w,h});
				clusters.put(rgb, cluster);
			}
		}
		// 切分字符
		List<BufferedImage> results = new ArrayList<BufferedImage>();
		for(Entry<Integer, List<int[]>> entry : clusters.entrySet()){
			List<int[]> cluster = entry.getValue();
			int minX = -1, minY = -1, maxX = -1, maxY = -1;
			for(int[] pixel : cluster){
				minX = pixel[0] < minX || minX == -1 ? pixel[0] : minX;
				minY = pixel[1] < minY || minY == -1 ? pixel[1] : minY;
				maxX = pixel[0] > maxX || maxX == -1 ? pixel[0] : maxX;
				maxY = pixel[1] > maxY || maxY == -1 ? pixel[1] : maxY;
			}
			BufferedImage binaryBufferedImage = new BufferedImage(maxX - minX + 1, maxY - minY + 1, BufferedImage.TYPE_BYTE_BINARY);
			for(int[] pixel : cluster){
				binaryBufferedImage.setRGB(pixel[0] - minX , pixel[1] - minY, Color.WHITE.getRGB());
			}
			if(binaryBufferedImage.getWidth() * binaryBufferedImage.getHeight() >= wast){
				for (int x = 0; x < binaryBufferedImage.getWidth(); x++) {
					for (int y = 0; y < binaryBufferedImage.getHeight(); y++) {
						binaryBufferedImage.setRGB(x, y, binaryBufferedImage.getRGB(x,y)==Color.BLACK.getRGB() ? Color.WHITE.getRGB() : Color.BLACK.getRGB());
					}
				}				
				results.add(binaryBufferedImage);
			}
		}
		return results;
	}
}
