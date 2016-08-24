package com.heetian.spider.process.zhejiang;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.ZheJiangProcessHandlePrepare;
/**
 * 正泰集团股份有限公司,中国移动通信集团浙江有限公司
 * @author tst
 *
 */
public class HomeProcess extends ZheJiangProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.zjaic.gov.cn/zhejiang.jsp");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/common/captcha/doReadKaptcha.do?"+3+"&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
