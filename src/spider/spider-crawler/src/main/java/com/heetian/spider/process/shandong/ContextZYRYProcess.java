package com.heetian.spider.process.shandong;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.process.abstractclass.ShanDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextZYRYProcess extends ShanDongProcessHandlePrepare {
	public ContextZYRYProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/pub/gsryxx");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String jsonStr = page.getRawText();
		if(jsonStr!=null&&!"".equals(jsonStr)&&!"'[]'".equals(jsonStr)&&!"[]".equals(jsonStr)&&!"null".equals(jsonStr)){
			List<ZYRYXX> results = AnalysisForJson.jsonToObject(jsonStr,new TypeToken<List<ZYRYXX>>() {});
			List<GsgsMainPersonel> mainPersonls = new ArrayList<GsgsMainPersonel>();
			String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
			for(ZYRYXX result:results){
				mainPersonls.add(AnalysisForTable.createMainPerson(regNumber, result.getName(), result.getPosition()));
			}
			((TSTPageProcessor) task).setDataZYRY(mainPersonls,regNumber);
		}
	}
}
