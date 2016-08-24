package com.heetian.spider.peking.pageProcessor;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpHost;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class CodePageProcess implements PageProcessor {
	public  static int x = 528;
	public static String proxy = "114.215.140.117";
	public static int port = 16816;
	public  static File dest = new File("C:\\Users\\tst\\Desktop\\test\\");
	//public static String url = "http://218.57.139.24/securitycode?"+System.currentTimeMillis();
	//public static String url = "http://gsxt.jxaic.gov.cn/ECPS/verificationCode.jsp?_="+System.currentTimeMillis();
	//public static String url = "http://gsxt.ngsh.gov.cn/ECPS/verificationCode.jsp?_="+System.currentTimeMillis();
	//public static String url = "http://gsxt.lngs.gov.cn/saicpub/commonsSC/loginDC/securityCode.action?tdate=3";
	//public static String url = "http://gsxt.cqgs.gov.cn/sc.action?width=130&height=40&fs=23&t="+System.currentTimeMillis();
	//public static String url = "http://gsxt.jxaic.gov.cn/ECPS/common/common_getJjYzmImg.pt?yzmName=searchYzm&imgWidth=200&t="+Math.random();
	//public static String url = "http://gsxt.gzgs.gov.cn/search!generateCode.shtml?validTag=searchImageCode&"+System.currentTimeMillis();
	//public static String url = "http://qyxy.baic.gov.cn/CheckCodeYunSuan?currentTimeMillis="+System.currentTimeMillis()+"&num=14927";
	//public static String url = "http://xyjg.egs.gov.cn/ECPS_HB/validateCode.jspx?type=0&_=1461028562647?type=1&_="+System.currentTimeMillis();
	public static String url = "http://gsxt.jxaic.gov.cn/warningetp/reqyzm.do?r="+System.currentTimeMillis();
	//http://www.11315.com/authCode.jpg?t=Thu%20Mar%2024%202016%2010:43:39%20GMT+0800
	private Site site = new Site();
	@Override
	public void process(Page page) {
		System.out.println(page.getRawText());
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		run();
	}

	private static void run() {
		while(true){
			Spider spider = Spider.create(new CodePageProcess());
			initSite(spider.getSite());
			spider.addUrl(url)
			.setDownloader(new DowaLoadCode())
			.addPipeline(new ConsolePipeline())
			.thread(1)
			.start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	static String Header="{User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv: 38.0) Gecko/20100101 Firefox/38.0},{Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8},{Accept-Encoding: gzip, deflate},{Accept-Language: zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3},{Cache-Control: no-cache},{Connection: keep-alive},{Content-Type: application/x-www-form-urlencoded}";
	private static void initSite(Site site) {
		site.setCharset("UTF-8")
			.setRetryTimes(0)
			.setSleepTime(100)
			.setUseGzip(true)
			.setTimeOut(60000)
			.setRetrySleepTime(1000)
			//.setHttpProxy(new HttpHost(proxy, port))
			.setUserAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv: 38.0) Gecko/20100101 Firefox/38.0")
			;
		//添加http请求头
        Pattern pattern = Pattern.compile("(\\{)(.*?)(:)(..*?)(\\},{0,1})");
        Matcher matcher = pattern.matcher(Header);
        while(matcher.find()){
        	site.addHeader(matcher.group(2), matcher.group(4));        
        }
        site.addHeader("Host", "xyjg.egs.gov.cn");
        site.addHeader("Referer","http://211.141.74.198:8081/aiccips/");
	}
}
