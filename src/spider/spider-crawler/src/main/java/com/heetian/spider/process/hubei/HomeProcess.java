package com.heetian.spider.process.hubei;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.HuBeiProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class HomeProcess extends HuBeiProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://xyjg.egs.gov.cn/ECPS_HB/search.jspx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		//task.getSite().setProvinceCookie(((PekingPageProcessor)task).getWorkInfo().getProvince(), task.getSite().getCookies());//设置cookie缓存
		String url = builderURL("/ECPS_HB/validateCode.jspx?type=0&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
