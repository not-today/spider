package com.heetian.spider.process.SiChuan;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.SiChuanProcessHandlePrepare;
/**
 * 四川光友薯业有限公司
 * @author tst
 *
 */
public class HomeProcess extends SiChuanProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.scaic.gov.cn");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/ztxy.do?method=createYzm&dt="+System.currentTimeMillis()+"&random="+System.currentTimeMillis() ,task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
