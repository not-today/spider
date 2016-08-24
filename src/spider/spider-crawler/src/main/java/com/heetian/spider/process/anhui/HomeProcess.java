package com.heetian.spider.process.anhui;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.AnHuiProcessHandlePrepare;
/**
 * 钧鼎集团 钧鼎集团有限公司（分页）  卫东区冠军工艺部   金通泰河南企业管理有限公司      安徽海螺集团有限责任公司
 * @author tst
 *
 */
public class HomeProcess extends AnHuiProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://www.ahcredit.gov.cn/search.jspx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/validateCode.jspx?type=1&id=" + Math.random()+"&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
