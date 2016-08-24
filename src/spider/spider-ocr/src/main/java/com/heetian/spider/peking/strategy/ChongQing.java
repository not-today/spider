package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 
 * @author tst
 *
 */
public class ChongQing extends AbstractRecognized{
	public ChongQing(){
		init("stencil"+File.separator+"chongqing",Color.BLACK,distiguingsh_sigle);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		// 清除背景颜色
		ImageUtils.clearBackground(img);
		img = img.getSubimage(0, 0, img.getWidth()-70, img.getHeight());
		int width = img.getWidth();
		int height = img.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(ImageUtils.clearColor(img.getRGB(x, y), 200)){
					img.setRGB(x, y, Color.BLACK.getRGB());
				}else{
					img.setRGB(x, y, Color.WHITE.getRGB());
				}
			}
		}
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		LinkedList<Integer> ins = ImageUtils.indexFlag(img, 'x');
		BufferedImage[] tmps = new BufferedImage[3];
		if (ins.size() == 6) {
			tmps[0] = splitByY(ImageUtils.split(img, ins.get(0), ins.get(1), 'x'));
			tmps[1] = splitByY(ImageUtils.split(img, ins.get(2), ins.get(3), 'x'));
			tmps[2] = splitByY(ImageUtils.split(img, ins.get(4), ins.get(5), 'x'));
		}else if (ins.size() == 8) {
			tmps[0] = splitByY(ImageUtils.split(img, ins.get(0), ins.get(1), 'x'));
			tmps[1] = splitByY(ImageUtils.split(img, ins.get(2), ins.get(5), 'x'));
			tmps[2] = splitByY(ImageUtils.split(img, ins.get(6), ins.get(7), 'x'));
		}else{
			throw new ImageConverte("验证码经过切割，子图片的数量不对，非正常情况:"+ins.size()/2);
		}
		return tmps;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String result = "";
		for(BufferedImage img:imgs){
			result = result + recognizedByPixelMatch(ImageUtils.getPixel(img, Color.BLACK.getRGB()), stencil);
		}
		if(result.trim().equals(""))
			return null;
		char chs[] = result.toCharArray();
		if(chs.length!=3)
			throw new ImageConverte("识别结果字符数不等于3");
		
		return ResultProcess.newInstance(ValidateType.count, chaArrToStrArr(chs));
	}
}
