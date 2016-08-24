package com.heetian.spider.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class Configuration {
	private Properties config = new Properties();// 记录配置项
	private String fn = null;// 记录配置文件名
	public Configuration(Properties config){
		this.config=config;
	}
	// 指定配置项名称，返回配置值
	public String getValue(String itemName){
		return config.getProperty(itemName);
	}
	// 指定配置项名称和默认值，返回配置值
	public String getValue(String itemName, String defaultValue){
		return config.getProperty(itemName, defaultValue);
	}
	// 设置配置项名称及其值
	public void setValue(String itemName, String value) {
		config.setProperty(itemName, value);
	}
	// 保存配置文件，指定文件名和抬头描述
	public void saveFile(String description) throws IOException {
		FileOutputStream fout = new FileOutputStream(fn);
		config.store(fout, description);// 保存文件
		fout.close();
	}
}