package com.heetian.spider.process.henan;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.HeNanProcessHandlePrepare;
/**
 * 钧鼎集团有限公司（分页）  卫东区冠军工艺部   金通泰河南企业管理有限公司      郑煤集团（河南）白坪煤业有限公司
 * @author tst
 *
 */
public class HomeProcess extends HeNanProcessHandlePrepare{
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
