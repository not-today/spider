package com.heetian.spider.process.jilin;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.process.abstractclass.JiLinProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextFZJGProcess extends JiLinProcessHandlePrepare {
	public ContextFZJGProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/aiccips/pub/gsfzjg");
	}
	@Override
	protected void analyticalProcessJiLin(Page page, PageProcessor task) {
		String jsonStr = page.getRawText();
		if(jsonStr!=null&&!"".equals(jsonStr)&&!"'[]'".equals(jsonStr)&&!"[]".equals(jsonStr)&&!"null".equals(jsonStr)){
			List<FZJGXX> results = AnalysisForJson.jsonToObject(jsonStr,new TypeToken<List<FZJGXX>>() {});
			List<GsgsBranch> branchInfos = new ArrayList<GsgsBranch>();
			String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
			for(FZJGXX result:results){
				branchInfos.add(AnalysisForTable.createBranch(regNumber, result.getRegno(), result.getBrname(), result.getRegorg()));
			}
			if(branchInfos.size()>0){
				((TSTPageProcessor) task).setDataFZJG(branchInfos,regNumber);
			}
		}
	}
}
