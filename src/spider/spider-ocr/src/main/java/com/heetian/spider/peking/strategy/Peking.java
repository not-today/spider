package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 北京市验证码识别程序
 * @author tst
 *http://qyxy.baic.gov.cn/CheckCodeYunSuan?currentTimeMillis=1459995329059&num=14927
 */
public class Peking extends AbstractRecognized{
	public Peking(){
		init("stencil"+File.separator+"peking",Color.BLACK,distiguingsh_many);
	}

	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int width = img.getWidth() - 76;
		int height = img.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 清除北京颜色与噪点并二值化
		for(int w = 0; w < width; w++){
			for(int h = 0; h < height; h++){
				int gray = getGray(img.getRGB(w, h));
				image.setRGB(w, h, gray < 100 ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
			}
		}
		// 清除孤点
		for(int w = 0; w < width; w++){
			for(int h = 0; h < height; h++){
				int rgb = image.getRGB(w, h);
				// 统计周围黑色像素点个数
				int num = 0;
				for(int x = w - 1; x <= w + 1; x++ ){
					for(int y = h - 1; y <= h + 1; y++){
						if(x < 0 || y < 0 || x >= width || y >= height) continue;
						num += Color.WHITE.getRGB() == image.getRGB(x, y) ? 0 : 1;
					}
				}
				if(Color.BLACK.getRGB() == rgb && num < 2) image.setRGB(w, h, Color.WHITE.getRGB());
			}
		}
		return image;
	}

	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		List<BufferedImage> list = projectionCluster(img,Color.WHITE.getRGB());
		Iterator<BufferedImage> iter = list.iterator();
		while(iter.hasNext()){
			BufferedImage tmp = iter.next();
			if(tmp.getWidth()*tmp.getHeight()<=50)
				iter.remove();
		}
		return listToArray(list);
	}

	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String result = "";
		for(BufferedImage img:imgs){
			@SuppressWarnings("unchecked")
			String tmp = ImageUtils.recognizedByPixelMatch(ImageUtils.getPixel(img, Color.BLACK.getRGB()), stencil);
			if(tmp.equals("+")){
				if(!result.contains(tmp))
					result = result + tmp;
			}else if(tmp.equals("-")){
				if(!result.contains(tmp))
					result = result + tmp;
			}else if(tmp.equals("x")){
				if(!result.contains(tmp))
					result = result + tmp;
			}else if(tmp.equals("s")){
				continue;
			}else{
				result = result + tmp;
			}
		
		}
		if(result.trim().equals(""))
			return null;
		char chs[] = result.toCharArray();
		if(chs.length!=3)
			throw new ImageConverte("识别结果字符数不等于3");
		return ResultProcess.newInstance(ValidateType.count, chaArrToStrArr(chs));
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
}
