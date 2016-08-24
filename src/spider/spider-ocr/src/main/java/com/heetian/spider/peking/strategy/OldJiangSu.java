package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 江苏省验证码识别程序
 * @author tst
 *
 */
public class OldJiangSu extends AbstractRecognized{
	public OldJiangSu(){
		forbiddenStencil();
	}
	public static double t = 0.000000001;
	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int w = img.getWidth();
		int h = img.getHeight();
		img = img.getSubimage(0, 16, w, h-30);
		w = img.getWidth();
		h = img.getHeight();
		BufferedImage grayImage = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		for(int x=0;x<w;x++){
			for(int y=0;y<h;y++){
				if(isWhatColor(img.getRGB(x, y), 550)){
					grayImage.setRGB(x, y, Color.WHITE.getRGB());
				}else{
					grayImage.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		grayImage.setRGB(0, 0, w, h,medianFiltering(grayImage.getRGB(0, 0, w, h, null, 0, w), w, h),0, w);
		//diedaifa(w, h, grayImage);
		return grayImage;
	}
	@Override
	protected BufferedImage[] division(BufferedImage img, Map<String, String> recogScop) throws ImageConverte {
		return isOrigalPic(recogScop);
	}
	@Override
	protected ResultProcess distinguish(Map<String, String> recogScop,String picName, String picType, BufferedImage... imgs) throws ImageConverte {
		String tmp = googleRecognize(imgs[0], picType, picName,CallUtil.laguage_eng);
		if(tmp==null)
			return null;
		return ResultProcess.newInstance(ValidateType.noCount, chaArrToStrArr(tmp.toCharArray()));
	}
	public static void diedaifa(int w, int h, BufferedImage gray) {
		double[][] graysTmp = new double[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int rgb = gray.getRGB(x, y);
				graysTmp[x][y] = getGrayForPoint(rgb);
			}
		}
		iterator(graysTmp, avgGray(graysTmp,w*h),gray);
	}

	private static double avgGray(double[][] graysTmp,int number) {
		double initAvg = 0;
		for (int x = 0; x < graysTmp.length; x++) {
			for (int y = 0; y < graysTmp[x].length; y++) {
				initAvg = initAvg + graysTmp[x][y];
			}
		}
		initAvg = initAvg / number;
		return initAvg;
	}
	public static double getGrayForPoint(int rgb){
		Color color = new Color(rgb);
		return 0.299*color.getRed()+0.578*color.getGreen()+0.144*color.getBlue();
	}
	private static void iterator(double[][] grays, double initAvg, BufferedImage gray) {
		double[][] gt = new double[gray.getWidth()][gray.getHeight()];
		double[][] lt = new double[gray.getWidth()][gray.getHeight()];
		int gtNum = 0;
		int ltNum = 0;
		for (int x = 0; x < grays.length; x++) {
			for (int y = 0; y < grays[x].length; y++) {
				if(grays[x][y]>=initAvg){
					gt[x][y] = grays[x][y];
					gtNum++;
				}else{
					lt[x][y] = grays[x][y];
					ltNum++;
				}
			}
		}
		double tmpGray = (avgGray(gt,gtNum) + avgGray(lt,ltNum)) / 2.0;
		double subtract = new BigDecimal(initAvg).subtract(new BigDecimal(tmpGray)).abs().doubleValue();
		System.out.println(initAvg + "-" + tmpGray + "=" + (subtract));
		if (subtract < t) {
			for (int x = 0; x < grays.length; x++) {
				for (int y = 0; y < grays[x].length; y++) {
					if(grays[x][y]<tmpGray){
						gray.setRGB(x, y, Color.WHITE.getRGB());
					}else{
						gray.setRGB(x, y, Color.BLACK.getRGB());
					}
				}
			}
		} else {
			iterator(grays, tmpGray,gray);
		}
	}
	private static boolean isWhatColor(int rgb, int colorCode) {
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
	private static int[] medianFiltering(int pix[], int w, int h) {
		int newpix[] = new int[w*h];
		int[] temp = new int[9];
		ColorModel cm = ColorModel.getRGBdefault();
		int r=0;
		for(int y=0; y<h; y++) {
			for(int x=0; x<w; x++) {
				if(x!=0 && x!=w-1 && y!=0 && y!=h-1) {
					//g = median[(x-1,y-1) + f(x,y-1)+ f(x+1,y-1)
					//  + f(x-1,y) + f(x,y) + f(x+1,y)
					//  + f(x-1,y+1) + f(x,y+1) + f(x+1,y+1)]
					temp[0] = cm.getRed(pix[x-1+(y-1)*w]); 
					temp[1] = cm.getRed(pix[x+(y-1)*w]);
					temp[2] = cm.getRed(pix[x+1+(y-1)*w]);
					temp[3] = cm.getRed(pix[x-1+(y)*w]);
					temp[4] = cm.getRed(pix[x+(y)*w]);
					temp[5] = cm.getRed(pix[x+1+(y)*w]);
					temp[6] = cm.getRed(pix[x-1+(y+1)*w]);
					temp[7] = cm.getRed(pix[x+(y+1)*w]);
					temp[8] = cm.getRed(pix[x+1+(y+1)*w]);
					Arrays.sort(temp);
					r = temp[4];
					//r = (temp[0]+temp[1]+temp[2]+temp[3]+temp[4]+temp[5]+temp[6]+temp[7]+temp[8])/9;
					newpix[y*w+x] = 255<<24 | r<<16 | r<<8 |r;
				} else {
					newpix[y*w+x] = pix[y*w+x];
				}
			}
		}
		return newpix;
	}
}
