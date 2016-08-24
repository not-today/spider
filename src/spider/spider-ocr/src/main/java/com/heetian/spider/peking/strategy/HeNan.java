package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 河南省验证码识别程序
 * @author tst
 *
 */
public class HeNan extends AbstractRecognized{
	public HeNan(){
		forbiddenStencil();
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int width = img.getWidth();
		int height = img.getHeight();
		img = img.getSubimage(25, 0, width-60, height);
		width = img.getWidth();
		height = img.getHeight();
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				Color color = new Color(img.getRGB(x, y));
				if((color.getRed() + color.getGreen() + color.getBlue())>700){
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
		return isOrigalPic(recogScop);
	}

	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String tmp = googleRecognize(imgs[0], picType, picName , CallUtil.laguage_chi);
		if(tmp==null)
			return null;
		return ResultProcess.newInstance(ValidateType.noCount, chaArrToStrArr(tmp.toCharArray()));
	}
}
