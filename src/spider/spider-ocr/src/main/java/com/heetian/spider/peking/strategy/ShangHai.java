package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
public class ShangHai extends AbstractRecognized{
	public ShangHai(){
		init("stencil"+File.separator+"jiangxi",Color.BLACK,distiguingsh_many);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		/*img = img.getSubimage(0, 0, 80, img.getHeight());
		int h = img.getHeight();
		int w = img.getWidth();
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				int rgb = img.getRGB(x, y);
				if(ImageUtils.clearColor(rgb, 380))
					img.setRGB(x, y, Color.BLACK.getRGB());
				else
					img.setRGB(x, y, Color.WHITE.getRGB());
			}
		}*/
		try {
			ImageUtils.copyFileImg(img, new File("C:\\Users\\tst\\Desktop\\pic\\pic.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		List<BufferedImage> list = projectionCluster(img, Color.WHITE.getRGB());
		return listToArray(list);
	}
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		/*String result = "";
		for(BufferedImage img:imgs){
			@SuppressWarnings("unchecked")
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
			throw new ImageConverte("识别结果字符数不等于3");*/
		String chs[] = {"1","+","2"};
		return ResultProcess.newInstance(ValidateType.count, chs);
	}
	public char[] convert(char[] results){
		char[] rs = new char[3];
		if(results[4]=='n'){
			rs[0] = results[0];
			rs[1] = results[1];
			rs[2] = results[2];
		}else if(results[0]=='n'){
			if(results[1]=='-'){
				rs[0] = results[4];
				rs[1] = '+';
				rs[2] = results[2];
			}else{
				rs[0] = results[4];
				rs[1] = '-';
				rs[2] = results[2];
			}
		}else if(results[2]=='n'){
			if(results[1]=='-'){
				rs[0] = results[0];
				rs[1] = '+';
				rs[2] = results[4];
			}else{
				rs[0] = results[0];
				rs[1] = '-';
				rs[2] = results[4];
			}
		}
		return rs;
	}
}
