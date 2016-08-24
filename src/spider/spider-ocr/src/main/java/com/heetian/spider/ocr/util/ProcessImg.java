package com.heetian.spider.ocr.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: ProcessImg.java
 * </p>
 * <p>
 * Description: 对指定的图片进行背景降噪
 * </p>
 * 
 * @author gao_jun
 * @version 1.0
 * @created 2015年3月6日 下午4:56:35
 */
public class ProcessImg {

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(ProcessImg.class);

	/**
	 * 对指定的图片进行背景降噪
	 * 
	 * @param picFile
	 *            图片名称
	 * @param degree
	 *            旋转度数
	 * @return 空
	 * @throws Exception
	 *             抛出的异常
	 */
	public static void removeBackgroud(String picFile, int degree,
			String targetFile) throws Exception {
		logger.info("传入的图片为{},旋转度数为{}，处理后的图片为{}", picFile, degree, targetFile);
		for (int i = 1; i <= 45; i++) {
			BufferedImage img = ImageIO.read(new File(picFile));
			int width = img.getWidth();
			int height = img.getHeight();
			for (int x = 0; x < width; ++x) {
				for (int y = 0; y < height; ++y) {
					if (isWhite(img.getRGB(x, y)) == 1) {
						img.setRGB(x, y, Color.WHITE.getRGB());
					} else {
						img.setRGB(x, y, Color.BLACK.getRGB());
					}
				}
			}
			// 旋转图片
			img = rotateImage(img, degree);
			ImageIO.write(img, "JPG", new File(targetFile));
		}
	}

	/**
	 * 旋转图片为指定角度
	 * 
	 * @param bufferedimage
	 *            目标图像
	 * @param degree
	 *            旋转角度
	 * @return
	 */
	public static BufferedImage rotateImage(final BufferedImage bufferedimage,
			final int degree) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
	}

	/**
	 * 进行亮度处理
	 * 
	 * @param colorInt
	 *            像素值
	 * @return 处理后的标志值
	 */
	public static int isWhite(int colorInt) {
		Color color = new Color(colorInt);
		// 颜色值0-255，r+g+b可以当做亮度，亮度在0-255*3之间。100只是这个验证码的一个背景区别特征，不同背景用的值不一样
		if (color.getRed() + color.getGreen() + color.getBlue() > 400) {
			return 1;
		}
		return 0;
	}
}
