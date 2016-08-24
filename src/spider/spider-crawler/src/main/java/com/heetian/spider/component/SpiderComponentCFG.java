package com.heetian.spider.component;

import java.util.LinkedList;

import org.apache.http.HttpHost;

import com.heetian.spider.utils.ProxyManager;

public class SpiderComponentCFG {
	public static int retryTimes = 0;
	public static int sleepTime = 100;
	public static boolean useGzip = true;
	public static int timeOut = 60000;
	public static int retrySleepTime = 1000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0";
	public static CharSequence header = "{User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0},{Accept:text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8},{Accept-Encoding:gzip, deflate},{Accept-Language:zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3},{Cache-Control:no-cache},{Connection:keep-alive},{Content-Type:application/x-www-form-urlencoded}";
	public static int spiderNumber = 1;
	/**
	 * 加载稳定代理
	 */
	public static LinkedList<HttpHost> useFixedIP = ProxyManager.stableProxys();
}
