package com.heetian.spider.process.jiangxi;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * 
 * @author tst
 *
 */
public class ContextBGXXProcess extends JiangXiProcessHandlePrepare {
	public ContextBGXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/gtalterrecoder/getquerygtalterrecoder.do");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		BGBean bean = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<BGBean>(){});
		List<BGBeanSub> datas = bean.getData();
		if(datas==null||datas.size()<=0){
			return;
		}
		List<GsgsChange> cs = new ArrayList<GsgsChange>();
		String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
		for(BGBeanSub data:datas){
			cs.add(data.toChange(regNumber));
		}
		((TSTPageProcessor) task).setDataBG(cs,regNumber);
	}
}