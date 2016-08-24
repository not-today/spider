package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ImageUtils;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 广东省验证码识别程序
 * @author tst
 *
 */
public class GuangDong extends AbstractRecognized{
	public GuangDong(){
		init("stencil"+File.separator+"guangdong",Color.WHITE,distiguingsh_many);
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		// 清除背景颜色
		ImageUtils.clearBackground(img);
		// 根据平滑度降噪
		ImageUtils.filterBySmooth(img);
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		// 切割
		BufferedImage[] subImages = ImageUtils.subImageByRGB(img);
		if(subImages==null||subImages.length<=0){
			return null;
		}
		return subImages;
	}
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String chs[] = new String[3];
		for(int i =0;i<imgs.length;i++){
			@SuppressWarnings("unchecked")
			String tmp = ImageUtils.recognizedByPixelMatch(ImageUtils.getPixel(imgs[i],Color.WHITE.getRGB()), stencil);
			if(i==0){
				chs[0] = tmp;
			}else if(i==1){
				chs[1] = tmp;
			}else if(i==2){
				chs[2] = tmp;
			}
		}
		return ResultProcess.newInstance(ValidateType.count, chs);
	}
}
