package com.heetian.spider.process.guizhou;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.EnterUrls;
import com.heetian.spider.process.abstractclass.GuiZhouProcessHandlePrepare;
public class HomeProcess extends GuiZhouProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.gzgs.gov.cn/?xx=xx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		page.addTargetRequest(builderRequestGet(builderURL(EnterUrls.GZDwcode+"&"+urlTail(),task.getSite())));
	}
}
