package com.heetian.spider.process.hainan;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.HaiNanProcessHandlePrepare;
/**
 * 海南南国食品实业有限公司,昌江石碌南国食品商行,海马汽车集团股份有限公司
 * @author tst
 *
 */
public class HomeProcess extends HaiNanProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://aic.hainan.gov.cn:1888/aiccips?xx=xx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/aiccips/verify.html?random="+Math.random(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
