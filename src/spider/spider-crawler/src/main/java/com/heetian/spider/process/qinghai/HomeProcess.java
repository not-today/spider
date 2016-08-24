package com.heetian.spider.process.qinghai;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.QingHaiProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class HomeProcess extends QingHaiProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://222.143.24.157/search.jspx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/validateCode.jspx?type=1&id=" + Math.random()+"&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
