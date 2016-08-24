package com.heetian.spider.peking.strategy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

import com.heetian.spider.ocr.exception.ImageConverte;
import com.heetian.spider.ocr.util.ResultProcess;
import com.heetian.spider.ocr.util.ValidateType;
/**
 * 北京市验证码识别程序
 * @author tst
 *
 */
public class Peking_copy extends AbstractRecognized{
	public Peking_copy(){
		forbiddenStencil();
	}
	private static final int BLACK = 50;
	private static final int WHITE = 700;
	public static double t = 0.000000001;
	public static final String saveDir = "img" + File.separator + "tmp";

	@Override
	protected BufferedImage pretreatment(BufferedImage img,Map<String, String> recogScop) throws ImageConverte {
		int w = img.getWidth();
		int h = img.getHeight();
		//灰度化
		BufferedImage gray = new BufferedImage(w, h,BufferedImage.TYPE_BYTE_GRAY);
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int rgb = img.getRGB(x, y);
				if (isWhatColor(rgb, BLACK) || isWhatColor(rgb, WHITE)) {
					gray.setRGB(x, y, Color.WHITE.getRGB());
				} else {
					gray.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		//阀值处理
		diedaifa(w, h, gray);
		//中值滤波
		gray.setRGB(0, 0, w, h,medianFiltering(gray.getRGB(0, 0, w, h, null, 0, w), w, h),0, w);
		//二值化
		BufferedImage er = new BufferedImage(w, h,BufferedImage.TYPE_BYTE_BINARY);
		for (int x = 1; x < w - 1; ++x) {
			for (int y = 1; y < h - 1; ++y) {
				er.setRGB(x, y, gray.getRGB(x, y));
			}
		}
		for (int x = 1; x < w - 1; ++x) {
			for (int y = 1; y < h - 1; ++y) {
				if(Color.BLACK.getRGB()==er.getRGB(x, y)){
					er.setRGB(x, y, Color.WHITE.getRGB());
				}else{
					er.setRGB(x, y, Color.BLACK.getRGB());
				}
			}
		}
		return er;
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
	private void diedaifa(int w, int h, BufferedImage gray) {
		double[][] graysTmp = new double[w][h];
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				int rgb = gray.getRGB(x, y);
				graysTmp[x][y] = getGrayForPoint(rgb);
			}
		}
		iterator(graysTmp, avgGray(graysTmp,w*h),gray);
	}

	private double avgGray(double[][] graysTmp,int number) {
		double initAvg = 0;
		for (int x = 0; x < graysTmp.length; x++) {
			for (int y = 0; y < graysTmp[x].length; y++) {
				initAvg = initAvg + graysTmp[x][y];
			}
		}
		initAvg = initAvg / number;
		return initAvg;
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

	public static double getGrayForPoint(int rgb) {
		Color color = new Color(rgb);
		return 0.299 * color.getRed() + 0.578 * color.getGreen() + 0.144 * color.getBlue();
	}

	private void iterator(double[][] grays, double initAvg, BufferedImage gray) {
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
		//System.out.println(initAvg + "-" + tmpGray + "=" + (subtract));
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

	/**
	 * 中值滤波
	 * 
	 * @param pix
					// 
	 *            像素矩阵数组
	 * @param w
	 *            矩阵的宽
	 * @param h
	 *            矩阵的高
	 * @return 处理后的数组
	 */
	private static int[] medianFiltering(int pix[], int w, int h) {
		int newpix[] = new int[w * h];
		int[] temp = new int[9];
		ColorModel cm = ColorModel.getRGBdefault();
		int r = 0;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				if (x != 0 && x != w - 1 && y != 0 && y != h - 1) {
					temp[0] = cm.getRed(pix[x - 1 + (y - 1) * w]);
					temp[1] = cm.getRed(pix[x + (y - 1) * w]);
					temp[2] = cm.getRed(pix[x + 1 + (y - 1) * w]);
					temp[3] = cm.getRed(pix[x - 1 + (y) * w]);
					temp[4] = cm.getRed(pix[x + (y) * w]);
					temp[5] = cm.getRed(pix[x + 1 + (y) * w]);
					temp[6] = cm.getRed(pix[x - 1 + (y + 1) * w]);
					temp[7] = cm.getRed(pix[x + (y + 1) * w]);
					temp[8] = cm.getRed(pix[x + 1 + (y + 1) * w]);
					Arrays.sort(temp);
					r = temp[4];
					r = (temp[0]+temp[1]+temp[2]+temp[3]+temp[4]+temp[5]+temp[6]+temp[7]+temp[8])/9;
					newpix[y * w + x] = 255 << 24 | r << 16 | r << 8 | r;
				} else {
					newpix[y * w + x] = pix[y * w + x];
				}
			}
		}
		return newpix;
	}

	public String process(File file) throws ImageConverte {
		try {
			String content = new Tesseract1().doOCR(file);
			System.out.println(content);
			return content;
		} catch (TesseractException e) {
			throw new ImageConverte("获取图片内容失败：" + e);
		}
	}

	/**
	 * 自己加周围8个灰度值再除以9，算出其相对灰度值
	 * 
	 * @param gray
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	public int getAverageColor(int[][] gray, int x, int y, int w, int h) {
		int rs = gray[x][y] + (x == 0 ? 255 : gray[x - 1][y])
				+ (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
				+ (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1])
				+ (y == 0 ? 255 : gray[x][y - 1])
				+ (y == h - 1 ? 255 : gray[x][y + 1])
				+ (x == w - 1 ? 255 : gray[x + 1][y])
				+ (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])
				+ (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
		return rs / 9;
	}

}
