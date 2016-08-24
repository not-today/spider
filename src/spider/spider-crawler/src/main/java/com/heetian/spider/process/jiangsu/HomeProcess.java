package com.heetian.spider.process.jiangsu;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
/**
 * 
 * @author tst
 *苏宁电器集团有限公司
 *
 */
public class HomeProcess extends JiangSuProcessHandlePrepare{
	public HomeProcess() {
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://www.jsgsj.gov.cn:58888/province/");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		page.addTargetRequest(rDate(1, task));
		String url = builderURL("/province/rand_img.jsp?type=7&temp="+System .currentTimeMillis(), task.getSite());
		page.addTargetRequest(builderRequest(url, Method.GET, null, null, null));
		page.addTargetRequest(rDate(2, task));
		page.addTargetRequest(rDate(3, task));
	}
}
