package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 湖北省验证码识别程序
 * @author tst
 *
 */
public class OldHuBei extends AbstractRecognized{
	public OldHuBei(){
		forbiddenStencil();
	}
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		// 第一步 ： 消除灰色背景及干扰线
		img = grayFilter(img);
		// 第二步 ： 二值化
		img = binaryzation(img);
		// 第三步 ： 根据平滑性去噪点
		img = smoothFilter(img);
		// 第四步 ： 再次根据平滑性去噪点
		img = smoothFilter(img);
		// 第四步 ： 再次根据平滑性去噪点
		img = smoothFilter(img);
		return img;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		return isOrigalPic(recogScop);
	}
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String tmp = googleRecognize(imgs[0], picType, picName,CallUtil.laguage_chi);
		if(tmp==null)
			return null;
		return ResultProcess.newInstance(ValidateType.noCount, chaArrToStrArr(tmp.toCharArray()));
	}
	/**
	 * 灰色过滤(用于背景色、干扰线是灰色，前景色是彩色)
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @throws IOException
	 */
	public BufferedImage grayFilter(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		// 根据颜色饱和度清除背景颜色
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				Color c = new Color(rgb);
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				float[] hsb = Color.RGBtoHSB(r, g, b,null);
				int saturation = Math.round(hsb[1]*100);	
				int brightness = Math.round(hsb[2]*100);
				if(saturation < 15 && brightness > 40){	// 将饱和度低于5的颜色替换为白色（灰色饱和度低）
					bufferedImage.setRGB(w, h, 0xFFFFFF);
				}
			}
		}
		return bufferedImage;
	}
	/**
	 * 根据平滑度降噪
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @throws IOException
	 */
	public BufferedImage smoothFilter(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				// 计算像素点周围颜色值
				if(w <= 1 || h <= 1 || w >= width - 2 || h >= height -2){
					bufferedImage.setRGB(w, h, 0xFFFFFF);
					continue;
				}
				// 周围2圈相同颜色像素点个数
				int count = 0;
				for(int x = w - 2; x <= w + 2 ; x++ ){
					for(int y = h - 2; y <= h + 2; y++ ){
						if(Math.abs(x - w) == 2 || Math.abs(y - h) == 2){
							count += bufferedImage.getRGB(x, y) == rgb ? 1 : 0;
							continue;
						}
						if(Math.abs(x - w) == 1 && Math.abs(y - h) == 1){
							count += bufferedImage.getRGB(x, y) == rgb ? 2 : 0;
							continue;
						}
						if(x == w && y == h){
							continue;
						}
						count += bufferedImage.getRGB(x, y) == rgb ? 8 : 0;
					}
				}
				if(count < 9) bufferedImage.setRGB(w, h, 0xFFFFFF);
			}
		}
		return bufferedImage;
	}
	/**
	 * 
	 * @param bufferedImage
	 * @param dir
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public BufferedImage binaryzation(BufferedImage bufferedImage){
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		BufferedImage binaryBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		// 灰度化
		for (int w = 0; w < width; w++) {
			for (int h = 0; h < height; h++) {
				int rgb = bufferedImage.getRGB(w, h);
				int gray = getGray(rgb);
				
				if(gray > 200){
					binaryBufferedImage.setRGB(w, h, 0xFFFFFF);
				}
				else{
					binaryBufferedImage.setRGB(w, h, 0x000000);
				}
			}
		}
		return binaryBufferedImage;
	}
	/**
	 * 灰度化是获取灰色颜色值
	 * @param rgb
	 * @return
	 */
	private int getGray(int rgb){
		Color c = new Color(rgb);
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return (r*30 + g*59 + b*11)/100;
		
	}
}
