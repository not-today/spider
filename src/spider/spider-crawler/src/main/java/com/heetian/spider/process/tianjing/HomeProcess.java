package com.heetian.spider.process.tianjing;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.TianJingProcessHandlePrepare;
//http://tjcredit.gov.cn/platform/saic/index.ftl
/**
 * 天津市远航矿石物流有限公司  天津市临检医学技术有限公司   天津替代医学科技股份有限公司    大港油田集团有限责任公司(all)
 * @author tst
 */
public class HomeProcess extends TianJingProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://tjcredit.gov.cn/platform/saic/index.ftl");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/verifycode?date="+System.currentTimeMillis()+"&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
