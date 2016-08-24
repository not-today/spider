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

import javax.imageio.ImageIO;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 针对河南类型的成语验证码
 * @author tst
 *
 */
public class Idiom extends AbstractRecognized{
	public Idiom(){
		init("stencil"+File.separator+"idiom",Color.BLACK,distiguingsh_sigle);
		//init("C:\\Users\\tst\\Desktop\\stencil",Color.BLACK,distiguingsh_sigle);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		// 清除背景颜色
		return clearBG(img);
	}

	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		List<BufferedImage> list = projectionCluster(img,Color.WHITE.getRGB(), 5);
		return listToArray(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String result = "";
        for(BufferedImage img:imgs){
        	String tmp = recognizedByPixelMatch(ImageUtils.getPixel(img, Color.BLACK.getRGB()), stencil);
        	tmp = tmp.split("_")[0];
        	result = result + tmp;
        }
        if("".equals(result.trim()))
        	return null;
        return ResultProcess.newInstance(ValidateType.noCount, chaArrToStrArr(result.toCharArray()));
	}
	public static void main(String[] args) throws IOException {
		File testDataDir = new File("C:\\Users\\tst\\Desktop\\hn_new");
		Idiom i = new Idiom();
		long b = System.currentTimeMillis();
		for (File file : testDataDir.listFiles()) {
			if(file.isDirectory()){
				continue;
			}
			try {
				String result = i.recognition(ImageIO.read(file), file.getName(), "png");
				System.out.println(file.getName()+":"+result);
			} catch (ImageConverte e) {
				e.printStackTrace();
				continue;
			}
		}
		long e = System.currentTimeMillis();
		System.out.println(e-b);
	}
	/**
	 * 清除背景颜色、清除噪点
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage clearBG(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		BufferedImage image = new BufferedImage(width - 30, height, BufferedImage.TYPE_INT_RGB);
		// 清除背景颜色
		for(int w = 0; w < width - 30; w++){
			for(int h = 0; h < height; h++){
				int gray = getGray(bufferedImage.getRGB(w, h));
				image.setRGB(w , h, gray > 200 ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
			}
		}
		return image;
	}
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
	/**
	 * 按X轴投影切图(默认切图间隔为1)
	 * @param image  图片
	 * @param bg 背景颜色
	 * @return
	 */
	public static  List<BufferedImage> projectionCluster(BufferedImage image, int bg){
		return projectionCluster(image, bg, 1);
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
