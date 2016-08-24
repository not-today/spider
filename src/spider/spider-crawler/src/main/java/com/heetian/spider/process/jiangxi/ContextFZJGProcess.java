package com.heetian.spider.process.jiangxi;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * 
 * @author tst
 *
 */
public class ContextFZJGProcess extends JiangXiProcessHandlePrepare {
	public ContextFZJGProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ebrchinfo/getqueryEBrchinfo.do");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		FZJGBean bean = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<FZJGBean>(){});
		List<FZJGBeanSub> datas = bean.getData();
		String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
		if(datas==null||datas.size()<=0){
			return;
		}
		List<GsgsBranch> bs = new ArrayList<GsgsBranch>();
		for(FZJGBeanSub data:datas){
			bs.add(data.toBranch(regNumber));
		}
		((TSTPageProcessor) task).setDataFZJG(bs,regNumber);
	}
}
