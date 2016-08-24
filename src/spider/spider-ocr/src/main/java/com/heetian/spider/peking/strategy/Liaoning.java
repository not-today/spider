package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 辽宁省验证码识别程序
 * @author tst
 *
 */
public class Liaoning extends AbstractRecognized{
	public Liaoning(){
		init("stencil"+File.separator+"liaoning",Color.BLACK,distiguingsh_sigle);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int h = img.getHeight();
		int w = img.getWidth();
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				int rgb = img.getRGB(x, y);
				if(ImageUtils.clearColor(rgb, 600))
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
		for(BufferedImage imgtmp:imgs){
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
		return ResultProcess.newInstance(ValidateType.noCount, chaArrToStrArr(result.toCharArray()));
	}
}
