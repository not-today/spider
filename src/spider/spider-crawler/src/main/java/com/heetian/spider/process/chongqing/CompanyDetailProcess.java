package com.heetian.spider.process.chongqing;

import com.heetian.spider.process.abstractclass.ChongQingProcessHandlePrepare;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

/**
 * 
 * @author tst
 *
 */
public class CompanyDetailProcess extends ChongQingProcessHandlePrepare {
	public CompanyDetailProcess() {
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/search_ent");
	}

	@Override
	public void analyticalProcess(Page page, PageProcessor task) {
		String tail = page.getHtml().xpath("//html[@id='ng-app']/@ng-init").replace("['\\s]", "").replace(";", "&").get();
		if(tail==null){
			return;
		}
		String url = builderURL("/search_getEnt.action?"+tail+ urlTail(), task.getSite());
		page.addTargetRequest(builderRequest(url, Method.GET, null, null, null));
	}
}
