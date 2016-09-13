package com.heetian.spider.process.guangdong;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.enumeration.SeedStatus;
import com.heetian.spider.process.abstractclass.GuangDongProcessHandlePrepare;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 
 * @author tst
 *
 */
public class TimeOutProcess extends GuangDongProcessHandlePrepare {
	public TimeOutProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/aiccips/timeout/control.html");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		logger.warn("广东由于服务器限制ip导致出现操作过于频繁，稍后再试！");
		TSTPageProcessor tst = (TSTPageProcessor) task;
		tst.setProxyStatus(ProxyStatus.NO);
		tst.setStatus(SeedStatus.FAIL);
	}
}
