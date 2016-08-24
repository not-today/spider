package com.heetian.spider.process.chongqing;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.ChongQingProcessHandlePrepare;
/**
 * 91500109756205209U 
 * @author tst
 *
 */
public class HomeProcess extends ChongQingProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.cqgs.gov.cn/?xx=xx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/sc.action?width=130&height=40&fs=23&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
