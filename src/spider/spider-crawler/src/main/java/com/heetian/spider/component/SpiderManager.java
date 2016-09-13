package com.heetian.spider.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.PvnObj;
import com.heetian.spider.dbcp.utils.ConstantDBCP;
import com.heetian.spider.utils.BufferedSeed;
import com.heetian.spider.utils.ProxyManager;
import com.heetian.spider.utils.PvnCode;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;

public class SpiderManager implements SpiderManagerInter{
	private static Logger logger = LoggerFactory.getLogger(SpiderManager.class);
	private long beginPrintTime ;
	private static final long timePeriod = 1000*60*10;
	private long totalSpiders = 0;//已经运行了多少spider
	private static final List<Spider> spiders = new ArrayList<Spider>();
	@Override
	public void createSpiderAndAddPool(){
		TSTPageProcessor page = new TSTPageProcessor();
		Site site = page.getSite();
		BufferedSeed seed = SBContainer.remove();
		page.setSeed(seed);
		PvnObj pvnObj = ProxyManager.getProxyType(seed.getCode());
		if(pvnObj==null){
			logger.error("根据区域码获取代理编码等配置失败，而导致程序停止："+seed.getCode());
			System.exit(0);
		}
		String type = pvnObj.getProxyType();
		site.setCharset(pvnObj.getCharset());
		site.setHttpProxy(setProxy( seed, type)) ;
		
		try {
			String homeURL = PvnCode.get(page.getProvince());
			Spider spider = Spider.create(page)
					.addUrl(homeURL)
					.setDownloader(new TSTDownload())
					.addPipeline(new TSTPipeline())
					.thread(1);
			logger.info("创建一个spider："+page.getSeedNm()+":"+page.getProvince());
			spiders.add(spider);
			spider.start();
		} catch (NullPointerException e) {
			logger.error("创建spider失败："+e.getMessage());
		}
	}
	private HttpHost setProxy(BufferedSeed seed, String type) {
		HttpHost host = null;
		if(ConstantDBCP.proxyType_stable.equals(type)){
			if(SpiderComponentCFG.useFixedIP!=null&&SpiderComponentCFG.useFixedIP.size()>0){
				host = SpiderComponentCFG.useFixedIP.removeFirst();
				SpiderComponentCFG.useFixedIP.addLast(host);
				logger.debug("创建一个带有稳定代理的pageprocessor:"+host);
			}else{
				logger.error("配置文件中，配置成使用固定代理，但是固定代理没有代理ip，请查看原因");
			}
		}else if(ConstantDBCP.proxyType_nostable.equals(type)){
			Proxy proxy = ProxyManager.pollProxy(seed.getCode());
			int count = 1;
			while(proxy==null){
				if(count>5)
					break;
				proxy = ProxyManager.pollProxy(seed.getCode());
				count++;
			}
			if(proxy!=null){
				seed.setProxy(proxy);
				host = proxy.toConvertHttpHost() ;
				logger.debug("创建一个带有不稳定代理的pageprocessor:"+proxy.toConvertHttpHost());
			}else{
				logger.error("从数据库获取代理失败，请查看原因");
			}
		}else if(ConstantDBCP.proxyType_noprxy.equals(type)){
			logger.debug("创建一个不使用代理的pageprocessor");
		}
		return host;
	}
	@Override
	public void removeSpiders() {
		int total = spiders.size();
		if(total<=0)
			return;
		Iterator<Spider> iter = spiders.iterator();
		while(iter.hasNext()){
			Spider spider = iter.next();
			if (spider.getStatus().ordinal() == Status.Stopped.ordinal()){
				TSTPageProcessor page = (TSTPageProcessor)spider.getPageProcessor();
				page.destroyMyself();
				iter.remove();
				logger.info("终止一个spider："+page.getSeedNm()+":"+page.getProvince());
				this.totalSpiders++;
			}
		}
		int remain = spiders.size();
		if(System.currentTimeMillis()-beginPrintTime>timePeriod){
			logger.info("已经运行spider["+this.totalSpiders+"];正在运行的spider["+remain+"];"
					+ "此次移除已经运行完成spider["+(total-remain)+"];并发运行最大数["+SpiderComponentCFG.spiderNumber+"]");
			beginPrintTime = System.currentTimeMillis();
		}
	}
	
	@Override
	public boolean spiderIsEnough() {
		if(spiders.size()<SpiderComponentCFG.spiderNumber){
			return false;
		}
		return true;
	}
	@Override
	public boolean spiderIsNull() {
		if(spiders.size()<=0){
			return true;
		}
		return false;
	}
}
