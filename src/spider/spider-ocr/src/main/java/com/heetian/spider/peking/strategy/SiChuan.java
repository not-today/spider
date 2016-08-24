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
 * 四川省验证码识别程序
 * @author tst
 *
 */
public class SiChuan extends AbstractRecognized{
	public SiChuan(){
		init("stencil"+File.separator+"sichuan",Color.BLACK,distiguingsh_many);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		img = img.getSubimage(0, 0, img.getWidth() - 4, img.getHeight());
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (ImageUtils.clearColor(img.getRGB(x, y), 500)) {
					img.setRGB(x, y, Color.WHITE.getRGB());
				} else {
					img.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		img = img.getSubimage(0, 0, 48, img.getHeight());
		List<BufferedImage> imgs = twoPassCluster(img, Color.WHITE.getRGB());
		Iterator<BufferedImage> iter = imgs.iterator();
		while(iter.hasNext()){
			BufferedImage imgtmp = iter.next();
			if(imgtmp.getHeight()*imgtmp.getWidth()<=10){
				iter.remove();
				continue;
			}
			
			for (int w = 0; w < imgtmp.getWidth(); w++) {
				for (int h = 0; h < imgtmp.getHeight(); h++) {
					if(Color.BLACK.getRGB()==imgtmp.getRGB(w, h)){
						imgtmp.setRGB(w, h, Color.WHITE.getRGB());
					}else{
						imgtmp.setRGB(w, h, Color.BLACK.getRGB());
					}
				}
			}
		}
		return listToArray(imgs);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String result = "";
		for(BufferedImage img:imgs){
			String tmp = ImageUtils.recognizedByPixelMatch(ImageUtils.getPixel(img, Color.BLACK.getRGB()), stencil);
			if(tmp.equals("+")){
				if(!result.contains(tmp))
					result = result + tmp;
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
}
