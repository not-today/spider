package com.heetian.spider.process.neimenggu;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.NeiMengGuProcessHandlePrepare;
/**
 * 150000000005766,152700000010020,152201000002988,阿拉善盟金丰源商贸股份有限公司
 * 巴彦淖尔市钯盟金店有限责任公司,分支机构带有分页
 * @author tst
 *
 */
public class HomeProcess extends NeiMengGuProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://www.nmgs.gov.cn:7001/aiccips/?xx=xx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url =builderURL("/aiccips/verify.html?random="+Math.random(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
