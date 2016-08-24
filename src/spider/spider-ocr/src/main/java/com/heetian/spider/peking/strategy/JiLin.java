package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 吉林省验证码识别程序
 * @author tst
 *
 */
public class JiLin extends AbstractRecognized{
	public JiLin(){
		init("stencil"+File.separator+"jilin",Color.BLACK,distiguingsh_many);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int h = img.getHeight();
		int w = img.getWidth();
		byte[] tmp = new byte[w*h];
		int size = 0;
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				if(isWhatColor(img.getRGB(x, y), 400)){
					tmp[size] = 0;
					img.setRGB(x, y, Color.WHITE.getRGB());
				}else{
					tmp[size] = 1;
					img.setRGB(x, y, Color.BLACK.getRGB());
				}
				size++;
			}
		}
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		img = clearAroundNull(img);
		img = dispersed(img,Color.WHITE);
		img = img.getSubimage(0, 0, bundaryRIGHT(img), img.getHeight());
		int h = img.getHeight();
		int w = img.getWidth();
		int left = -1;
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				if(img.getRGB(x, y)==Color.BLACK.getRGB()){
					int section = w-1-x;
					if(section>=50&&section<=57){
						left = x;
					}
				}
			}
			if(left!=-1)
				break;
		}
		img = img.getSubimage(0, 0, left+1, h);
		img = tiltCorrection(img);
		img = dispersed(img,Color.WHITE);
		img = clearAroundNull(img);
		LinkedList<Integer> ins = ImageUtils.indexFlag(img, 'x');
		List<BufferedImage> imgs = new ArrayList<>();
		for(int x=0;x<ins.size()-1;x=x+2){
			imgs.add(ImageUtils.split(img, ins.get(x), ins.get(x+1), 'x'));
		}
		return listToArray(imgs);
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
			}else if(tmp.equals("b")){
				if(!result.contains(tmp))
					result = result + tmp;
			}else{
				result = result + tmp;
			}
		}
		result = result.replace("b", "8");
		if(result.trim().equals(""))
			return null;
		char chs[] = result.toCharArray();
		if(chs.length!=3)
			throw new ImageConverte("识别结果字符数不等于3");
		return ResultProcess.newInstance(ValidateType.count, chaArrToStrArr(chs));
	}
	public double getGrayForPoint(int rgb){
		Color color = new Color(rgb);
		return 0.299*color.getRed()+0.578*color.getGreen()+0.144*color.getBlue();
	}
	
	private boolean isWhatColor(int rgb, int colorCode) {
		Color color = new Color(rgb);
		int rgbSub = color.getRed() + color.getGreen() + color.getBlue();
		if (colorCode > (255 + 255 + 255 + 1) / 2) {
			if (rgbSub >= colorCode) {
				return true;
			}
		} else {
			if (rgbSub <= colorCode) {
				return true;
			}
		}
		return false;
	}
}
