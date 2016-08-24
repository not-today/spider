package com.heetian.spider.process.guizhou;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.GuiZhouProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * 
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends GuiZhouProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/nzgs/search!searchTzr.shtml");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		GdxxBean gdxxxxbean = AnalysisForJson.jsonToObject(page.getRawText(),new TypeToken<GdxxBean>() {});
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
		tst.setDataGDXQ(gdxxxxbean.getgdxx(regNumber,page),uuid, regNumber);
	}
}
