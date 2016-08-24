package com.heetian.spider.peking.strategy;

import java.awt.image.BufferedImage;
import java.io.File;

import com.heetian.spider.ocr.exception.ImageConverte;

public interface Recognized {
	public static final String SAVE_DIR = "img"+File.separator;
	public static final String isOriglePic = "YES";
	public static final String picName = "picName";
	public static final String picType = "picType";
	/**
	 * 每种字符只有一张模版情况
	 */
	public static final String distiguingsh_sigle = "sigle";
	/**
	 * 每种字符有多个模版情况
	 */
	public static final String distiguingsh_many = "many";
	/**
	 * 识别程序的接口
	 * @param filePath 文件位置 
	 * @param picType
	 * @return
	 * @throws ImageConverte
	 */
	public abstract String recognition(BufferedImage image,String picName,String picType) throws ImageConverte;
}
