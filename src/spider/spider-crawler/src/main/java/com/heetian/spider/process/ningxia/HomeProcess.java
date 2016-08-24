package com.heetian.spider.process.ningxia;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.NingXiaProcessHandlePrepare;
/**
 * 宁夏天地奔牛实业集团有限公司,神华宁夏煤业集团有限责任公司
 * @author tst
 *
 */
public class HomeProcess extends NingXiaProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.jxaic.gov.cn/ECPS/index.jsp");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		//task.getSite().setProvinceCookie(((PekingPageProcessor)task).getWorkInfo().getProvince(), task.getSite().getCookies());//设置cookie缓存
		String url = builderURL("/ECPS/verificationCode.jsp?_=" +System.currentTimeMillis()+"&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
