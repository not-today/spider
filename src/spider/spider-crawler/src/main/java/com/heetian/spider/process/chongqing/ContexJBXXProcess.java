package com.heetian.spider.process.chongqing;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ChongQingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexJBXXProcess extends ChongQingProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/search_getEnt.action");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String info = page.getRawText();
		info = info.substring(info.indexOf("{"),info.length());
		Container results = AnalysisForJson.jsonToObject(info,new TypeToken<Container>() {});
		String url = TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null);
		String regNumber = results.toConvert((TSTPageProcessor)task, url);
		page.getRequest().putExtra(REGNUMBER, regNumber);
	}
}

