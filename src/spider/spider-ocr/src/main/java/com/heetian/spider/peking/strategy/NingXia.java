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
 * 江西省验证码识别程序
 * @author tst
 *
 */
public class NingXia extends AbstractRecognized{
	public NingXia(){
		init("stencil"+File.separator+"ningxia",Color.BLACK,distiguingsh_sigle);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int h = img.getHeight();
		int w = img.getWidth();
		img = img.getSubimage(0, 0, 90, h);
		h = img.getHeight();
		w = img.getWidth();
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				int rgb = img.getRGB(x, y);
				if(ImageUtils.clearColor(rgb, 300))
					img.setRGB(x, y, Color.BLACK.getRGB());
				else
					img.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
		ImageUtils.filterBySmooth(img,2);
		return img;
	}

	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
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
			result = result +  recognizedByPixelMatch(ImageUtils.getPixel(img, Color.BLACK.getRGB()), stencil);
		}
		if(result.trim().equals(""))
			return null;
		char chs[] = result.toCharArray();
		if(chs.length!=3)
			throw new ImageConverte("识别结果字符数不等于3");
		return ResultProcess.newInstance(ValidateType.count, chaArrToStrArr(chs));
	}
}
