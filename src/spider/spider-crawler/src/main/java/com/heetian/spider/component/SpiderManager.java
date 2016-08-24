package com.heetian.spider.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Spider.Status;

import com.heetian.spider.utils.PvnCode;

public class SpiderManager implements SpiderManagerInter{
	private static Logger logger = LoggerFactory.getLogger(SpiderManager.class);
	private long beginPrintTime ;
	private static final long timePeriod = 1000*60*10;
	private long totalSpiders = 0;//已经运行了多少spider
	private static final List<Spider> spiders = new ArrayList<Spider>();
	@Override
	public void createSpiderAndAddPool(){
		TSTPageProcessor page = new TSTPageProcessor();
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
