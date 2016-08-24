package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ZXXT extends AbstractRecognized{
	public ZXXT(){
		init("stencil"+File.separator+"zxxt",Color.BLACK,distiguingsh_many);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		return brightnessFilter(img);
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		return listToArray(cutByPos(img));
	}
	@SuppressWarnings("unchecked")
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		if(imgs.length!=4)
			return null;
		String result = "";
		for(BufferedImage tmp:imgs){
			result = result + ImageUtils.recognizedByPixelMatch(ImageUtils.getPixel(tmp,Color.BLACK.getRGB()), stencil);
		}
		if(result.length()!=4)
			throw new ImageConverte("识别结果字符数不等于4");
		return ResultProcess.newInstance(ValidateType.noCount, chaArrToStrArr(result.toCharArray()));
	}
	public List<BufferedImage> cutByPos(BufferedImage bufferedImage){
		List<BufferedImage> results = new ArrayList<BufferedImage>();
		for(int i = 0; i < 4; i++){
			results.add(new BufferedImage(12, 20, BufferedImage.TYPE_BYTE_BINARY));
		}
		for (int w = 0; w < 48; w++) {
			int index = w / 12;
			for (int h = 5; h < bufferedImage.getHeight(); h++) {
				int rgb = bufferedImage.getRGB(w, h);
				results.get(index).setRGB(w - index * 12, h - 5, rgb);
			}
		}
		return results;
	}
	/**
	 * 根据图形联通性原则对二值图像进行分割
	 * @param image
	 * @return
	 */
	public static List<BufferedImage> twoPassCluster(BufferedImage image, int bg){
		Map<String,Integer> map = new LinkedHashMap<String, Integer>();
		int lastSign = 0;
		for (int w = 0; w < image.getWidth(); w++) {
			for (int h = 0; h < image.getHeight(); h++) {
				int rgb = image.getRGB(w, h);
				if(rgb == bg) continue;
				// 循环计算相邻标记
				Integer minSign = null;
				List<Integer> signs = new ArrayList<Integer>();
				for(int x = w - 1; x <= w; x++){
					for(int y = h - 1; y <= h + 1; y++){
						Integer sign = map.get(x+"-"+y);
						if(sign == null) continue;
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
		Map<Integer, List<int[]>> clusters = new HashMap<Integer, List<int[]>>();
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
			for (int w = 0; w < binaryBufferedImage.getWidth(); w++) {
				for (int h = 0; h < binaryBufferedImage.getHeight(); h++) {
					int rgb = binaryBufferedImage.getRGB(w, h);
					if(rgb==Color.WHITE.getRGB()){
						binaryBufferedImage.setRGB(w, h, Color.BLACK.getRGB());
					}else{
						binaryBufferedImage.setRGB(w, h,  Color.WHITE.getRGB());
					}
				}
			}
			if(binaryBufferedImage.getWidth() * binaryBufferedImage.getHeight() >= 50)
				results.add(binaryBufferedImage);
		}
		return results;
	}
	/**
	 * 根据亮度降噪
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage brightnessFilter(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		BufferedImage binaryBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				Color c = new Color(rgb);
				float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(),null);
				int brightness = Math.round(hsb[2]*100);
				binaryBufferedImage.setRGB(w, h, brightness > 35 ? 0xFFFFFF : 0x000000);
			}
		}
		return binaryBufferedImage;
	}
}
