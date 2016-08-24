package com.heetian.spider.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: ReadConfig.java
 * </p>
 * <p>
 * Description: 取得配置 文 件
 * </p>
 * 
 * @author admin
 * @version 1.0
 * @created 2015年3月19日 上午11:26:29
 */
public class ReadConfig {
	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(ReadConfig.class);
	/**
	 * 获取指定的配置文件
	 * 
	 * @param fileName
	 * @throws FileNotFoundException 
	 */
	public static String getCfgPath(String fileName) throws FileNotFoundException {
		return getCfgFile(fileName).getAbsolutePath();
	}
	public static InputStream getCfgStream(String fileName) throws FileNotFoundException{
		return new FileInputStream(getCfgFile(fileName));
	}
	public static File getCfgFile(String fileName) throws FileNotFoundException{
		// 首先按已打包的情况来处理
		File file = new File(System.getProperties().getProperty("user.dir") + File.separator + fileName);
		if (file.exists()) {
			logger.info("文件路劲[所在目录方式]:{}",file.getAbsoluteFile());
			return file;
		} else {
			//开发环境
			try {
				URL url = ReadConfig.class.getClassLoader().getResource(fileName);
				if(url==null)
					throw new FileNotFoundException("文件["+fileName+"]:根据类加载方式找不到url对象(url对象为空)");
				file = new File(url.toURI());
				if(file.exists()){
					logger.info("文件路劲[类加载器方式]:{}",file.getAbsoluteFile());					
					return file;
				}
			} catch (URISyntaxException e) {
				throw new FileNotFoundException("文件["+fileName+"]:找不到路径,原因(获取文件url转uri对象时出错):"+e.getMessage());
			}
		}
		throw new FileNotFoundException("文件["+fileName+"]:找不到路径");
	}
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(getCfgFile("aaaa").getAbsolutePath());
	}
}
