package com.heetian.spider.process.shanxixi;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.ShanXiXiProcessHandlePrepare;
/**
 * 陕西汉中钢铁集团有限公司
 * @author tst
 *
 */
public class HomeProcess extends ShanXiXiProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://xygs.snaic.gov.cn/?xx=xx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/ztxy.do?method=createYzm&dt="+System.currentTimeMillis()+"&random="+System.currentTimeMillis() ,task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
