package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.PixelInfo;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
import com.heetian.spider.ocr.util.PixelInfo.Point;
/**
 * 北京市验证码识别程序
 * @author tst
 *
 */
public class ShanDong extends AbstractRecognized{
	public ShanDong(){
		init("stencil"+File.separator+"shandong",Color.BLACK,distiguingsh_many);
	}
	private static final int x_wast = 57;
	private static final int cha_width = 20;
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int w = img.getWidth();
		int h = img.getHeight();
		int black = Color.BLACK.getRGB();
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				if(ImageUtils.clearColor(img.getRGB(x, y), 400)){
					img.setRGB(x, y, Color.WHITE.getRGB());
				}else{
					img.setRGB(x, y, black);
				}
			}
		}
		
		img = split(img, w, h);
		img = img.getSubimage(0, 0, img.getWidth()-x_wast, img.getHeight());
		img = split(img, img.getWidth(), img.getHeight());
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		int w = img.getWidth();
		int nm = -1;
		if(w<=37){
			nm = 30;
		}else if(w>=38&&w<=48){
			nm = 38;
		}else if(w>=49&&w<=55){
			nm = 49;
		}else if(w>=56&&w<=64){
			nm = 61;
		}else if(w>=65&&w<=74){
			nm = 65;
		}else if(w>=75&&w<=85){
			nm = 75;
		}
		List<BufferedImage> images = stencil75(img, cha_width, nm);
		return listToArray(images);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String result = "";
		for(BufferedImage img:imgs){
			result = result + ImageUtils.recognizedByPixelMatch(ImageUtils.getPixel(img, Color.BLACK.getRGB()), stencil);
		}
		if(result.trim().equals(""))
			return null;
		char chs[] = result.toCharArray();
		if(chs.length!=3)
			throw new ImageConverte("识别结果字符数不等于3");
		return ResultProcess.newInstance(ValidateType.count, chaArrToStrArr(chs));
	}
	private BufferedImage split(BufferedImage image, int w, int h) {
		PixelInfo stat = null;
		int black = Color.BLACK.getRGB();
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				if(image.getRGB(x, y)==black){
					if(stat==null){
						stat = new PixelInfo(black, x, y, 0);
						continue;
					}
					stat.addPoint(x, y);
				}
			}
		}
		int width = stat.getMaxX() - stat.getMinX() + 1;
		int height = stat.getMaxY() - stat.getMinY() + 1;
		BufferedImage tmp = new BufferedImage(width, height,BufferedImage.TYPE_BYTE_BINARY);
		for (Point point : stat.getPoints()) {
			tmp.setRGB(point.getX() - stat.getMinX(),point.getY() - stat.getMinY(), 0xFFFFFF);
		}
		w = tmp.getWidth();
		h = tmp.getHeight();
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				if(tmp.getRGB(x, y)==black){
					tmp.setRGB(x, y, Color.WHITE.getRGB());
				}else{
					tmp.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		return tmp;
	}
	 public static BufferedImage removeInterferingLine(BufferedImage img) throws IOException{
    	int[] xx = {1, 0, -1, 0, 1, -1, 1, -1};
    	int[] yy = {0, 1, 0, -1, -1, 1, 1, -1};
    	int Threshold = 5;
    	int[] moudle = new int[8];
    	int height = img.getHeight();
    	int width = img.getWidth();
    	for(int i =0 ; i < width ; i++){
    		for(int j=0 ; j < height ; j++){
    			for(int k=0 ; k < 8 ; k++){
    				if(((i + xx[k])>=0&&(i + xx[k])<width) && ((j + yy[k])>=0&&(j + yy[k])<height)){
    					moudle[k] = img.getRGB(i + xx[k], j + yy[k]);
    				}
    			}
    			Arrays.sort(moudle);
    			int Vmid = moudle[(moudle.length)/2];
    			if(Math.abs(Vmid-img.getRGB(i, j)) < Threshold ){
    				if(img.getRGB(i,j) < 255 - img.getRGB(i, j)){
    					img.setRGB(i, j, 0);
    				}else{
    					img.setRGB(i, j, 255);
    				}
    			}
    		}
    	}
    	ImageIO.write(img, "jpg", new File("B"+Math.random()+".jpg"));
    	return img;
	 }
	 /**
		 * 切割用的
		 * @param dir
		 * @param ch_width
		 * @param ch_number
		 * @param ch_type 0纯英文，1混合，2中文
		 * @throws IOException
		 */
		private List<BufferedImage> stencil75(BufferedImage image,int ch_width,int ch_number){
			List<BufferedImage> images = new ArrayList<BufferedImage>();
			int w = image.getWidth();
			int h = image.getHeight();
			if(ch_number==75){
				int start = 0;
				images.add(image.getSubimage(start, 0, ch_width, h));
				start = ch_width;
				images.add(image.getSubimage(start, 0, ch_width, h));
				start = ch_width+ch_width*2;
				while(start+ch_width>w){
					start = start-1;
				}
				images.add(image.getSubimage(start, 0, ch_width, h));
			}else if(ch_number==65){
				int ch = ch_width-6;
				int start = 0;
				images.add(image.getSubimage(start, 0, ch, h));
				start = ch+1;
				images.add(image.getSubimage(start, 0, ch_width, h));
				start = ch+1+ch_width*2+2;
				while(start+ch>w){
					start = start-1;
				}
				images.add(image.getSubimage(start, 0, ch, h));
			}else if(ch_number==61){
				int start = 0;
				images.add(image.getSubimage(start, 0, ch_width, h));
				start = ch_width+2;
				images.add(image.getSubimage(start, 0, ch_width, h));
				start = ch_width+ch_width+2;
				while(start+ch_width>w){
					start = start-1;
				}
				images.add(image.getSubimage(start, 0, ch_width, h));
			}else if(ch_number==49){
				int start = 0;
				images.add(image.getSubimage(start, 0, ch_width, h));
				start = ch_width;
				images.add(image.getSubimage(start, 0, ch_width-10, h));
				start = ch_width+ch_width-10+2;
				while(start+ch_width>w){
					start = start-1;
				}
				images.add(image.getSubimage(start, 0, ch_width, h));
			}else if(ch_number==38){
				int ch = 11;
				int start = 0;
				images.add(image.getSubimage(start, 0, ch, h));
				start = ch+3;
				images.add(image.getSubimage(start, 0, ch_width, h));
				start = ch+ch_width+3;
				while(start+ch>w){
					start = start-1;
				}
				images.add(image.getSubimage(start, 0, ch, h));
			}else if(ch_number==30){
				int ch = 11;
				int start = 0;
				images.add(image.getSubimage(start, 0, ch, h));
				start = ch+1;
				images.add(image.getSubimage(start, 0, ch-2, h));
				start = ch+ch+4;
				while(start+ch>w){
					start = start-1;
				}
				images.add(image.getSubimage(start, 0, ch, h));
			}
			return images;
		}
}
