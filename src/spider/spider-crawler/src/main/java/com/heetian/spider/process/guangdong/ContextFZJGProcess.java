package com.heetian.spider.process.guangdong;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.process.abstractclass.GuangDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextFZJGProcess extends GuangDongProcessHandlePrepare {
	public ContextFZJGProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/aiccips/GSpublicity/braInfoPage");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		FZJGBeanJson beanJson = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<FZJGBeanJson>(){});
		if(beanJson==null)
			return;
		List<FZJGContentBean> contentBeans = beanJson.getList();
		if(contentBeans==null||contentBeans.size()<=0)
			return;
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		List<GsgsBranch> zyrys = new ArrayList<GsgsBranch>();
		for(FZJGContentBean contentBean:contentBeans){
			GsgsBranch fzjg = AnalysisForTable.createBranch(regNumber, contentBean.getRegNO(), contentBean.getBrName(), contentBean.getRegOrg());
			zyrys.add(fzjg);
		}
		if(zyrys.size()>0)
			((TSTPageProcessor) task).setDataFZJG(zyrys,regNumber);
	}
}
