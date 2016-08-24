package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 天津省验证码识别程序
 * @author tst
 *
 */
public class TianJing extends AbstractRecognized {
	public TianJing(){
		init("stencil"+File.separator+"tianjing",Color.BLACK,distiguingsh_sigle);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int width = img.getWidth();  
		int height = img.getHeight();  
		for (int x = 0; x < width; ++x) {  
           for (int y = 0; y < height; ++y) {  
               if (isWhite(img.getRGB(x, y)) == 1) {  
                   img.setRGB(x, y, Color.WHITE.getRGB());  
               } else{  
                   img.setRGB(x, y, Color.BLACK.getRGB());  
               }  
           }  
		}
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		List<BufferedImage> images = splitImage(img);
		return listToArray(images);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String result = "";
        for(BufferedImage image:imgs){
        	result = result + recognizedByPixelMatch(ImageUtils.getPixel(image, Color.BLACK.getRGB()), stencil);
        }
        char params[] = result.toCharArray();
        if(params.length!=3)
			throw new ImageConverte("识别结果字符数不等于3");
    	return ResultProcess.newInstance(ValidateType.count, chaArrToStrArr(params));
	}
	public String recognizedByPixelMatch(int[][] piexel,Map<String, int[][]> map_num){
		Set<Entry<String, int[][]>> ents = map_num.entrySet();
		double maxProba = -1;
		String key = null;
		for(Entry<String, int[][]> ent:ents){
			double currentProba = ImageUtils.matchPixel(ent.getValue(), piexel);
			if(maxProba<=currentProba){
				maxProba = currentProba;
				key = ent.getKey();
			}
		}
		return key;
	}
  //根据验证码的固定位置进行分割
    public List<BufferedImage> splitImage(BufferedImage img){  
        List<BufferedImage> subImgs = new ArrayList<BufferedImage>();  
        //int height = img.getHeight();  
        int heightW = 20;
        int heightS = 3;
        //w:14;h:15
        //w:21;h:19
        //int widthW = 
        subImgs.add(img.getSubimage(11, heightS,14, heightW));  
        subImgs.add(img.getSubimage(40, heightS,22, heightW)); 
        subImgs.add(img.getSubimage(70, heightS,14, heightW));  
       // removeBlank(null);
        return subImgs;  
    }  
    public static int isWhite(int colorInt) {  
        Color color = new Color(colorInt);  
        if (color.getGreen() > 156) {  
            return 1;  
        }  
        return 0;  
    }
}
