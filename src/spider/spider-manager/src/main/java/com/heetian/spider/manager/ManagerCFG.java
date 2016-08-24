package com.heetian.spider.manager;

import java.util.Properties;

import com.heetian.spider.component.SpiderComponentCFG;


public class ManagerCFG {
	public static int keepAliveTime = 60;// 线程保持时间
	public static void initCFG(Properties conf){
		keepAliveTime = Integer.parseInt(conf.getProperty("KeepAliveTime"));
		SpiderComponentCFG.spiderNumber = Integer.parseInt(conf.getProperty("spiderNumber"));
		SpiderComponentCFG.retryTimes = Integer.parseInt(conf.getProperty("RetryTimes"));
		SpiderComponentCFG.sleepTime = Integer.parseInt(conf.getProperty("SleepTime"));
		SpiderComponentCFG.useGzip = Boolean.parseBoolean(conf.getProperty("UseGzip"));
		SpiderComponentCFG.timeOut = Integer.parseInt(conf.getProperty("TimeOut"));
		SpiderComponentCFG.retrySleepTime = Integer.parseInt(conf.getProperty("RetrySleepTime"));
		SpiderComponentCFG.userAgent = conf.getProperty("UserAgent");
		SpiderComponentCFG.header = conf.getProperty("Header");//
	}
}