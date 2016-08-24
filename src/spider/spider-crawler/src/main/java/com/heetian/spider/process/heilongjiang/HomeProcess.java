package com.heetian.spider.process.heilongjiang;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.HeiLongJiangProcessHandlePrepare;
/**
 * 233006100000003,230100400001903,230199100055264
 * @author tst
 */
public class HomeProcess extends HeiLongJiangProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.hljaic.gov.cn/search.jspx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/validateCode.jspx?type=1&id=" + Math.random()+"&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
