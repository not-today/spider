package com.heetian.spider.process.jilin;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.process.abstractclass.JiLinProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextZYRYProcess extends JiLinProcessHandlePrepare {
	public ContextZYRYProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/aiccips/pub/gsryxx");
	}
	@Override
	protected void analyticalProcessJiLin(Page page, PageProcessor task) {
		String jsonStr = page.getRawText();
		if(jsonStr!=null&&!"".equals(jsonStr)&&!"'[]'".equals(jsonStr)&&!"[]".equals(jsonStr)&&!"null".equals(jsonStr)){
			//String enttype = (String) page.getRequest().getExtra("enttype");
			//"9999".equals(enttype)  "9100".equals(enttype)||"9200"
			List<ZYRYXX> results = AnalysisForJson.jsonToObject(jsonStr,new TypeToken<List<ZYRYXX>>() {});
			List<GsgsMainPersonel> mainPersonls = new ArrayList<GsgsMainPersonel>();
			String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
			for(ZYRYXX result:results){
				mainPersonls.add(AnalysisForTable.createMainPerson(regNumber, result.getName(), result.getPosition()));
			}
			if(mainPersonls.size()>0){
				((TSTPageProcessor) task).setDataZYRY(mainPersonls,regNumber);
			}
		}
	}
}
