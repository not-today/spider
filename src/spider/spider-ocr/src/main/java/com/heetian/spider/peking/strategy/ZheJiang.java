package com.heetian.spider.peking.strategy;

import java.awt.image.BufferedImage;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 浙江省验证码识别程序
 * @author tst
 *
 */
public class ZheJiang extends AbstractRecognized{
	public static int x = 0;
	public ZheJiang(){
		forbiddenStencil();
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		return isOrigalPic(recogScop);
	}
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		return ResultProcess.newInstance(ValidateType.noCount, new String[]{"0"});
	}
}
