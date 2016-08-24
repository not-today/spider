package com.heetian.spider.process.hainan;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.process.abstractclass.HaiNanProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextZYRYProcess extends HaiNanProcessHandlePrepare {
	public ContextZYRYProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/aiccips/GSpublicity/vipInfoPage");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		Request tmp = page.getRequest();
		String regNumber = (String) tmp.getExtra(REGNUMBER);
		if(page.getRawText()!=null){
			ZYRYBeanJson beanJson = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<ZYRYBeanJson>(){});
			if(beanJson!=null){
				List<GsgsMainPersonel> zyrys = new ArrayList<GsgsMainPersonel>();
				List<ZYRYContentBean> contentBeans = beanJson.getList();
				if(contentBeans!=null&&contentBeans.size()>0){
					for(ZYRYContentBean contentBean:contentBeans){
						GsgsMainPersonel zyry = AnalysisForTable.createMainPerson(regNumber, contentBean.getName(), contentBean.getPosition());
						zyrys.add(zyry);
					}
					if(zyrys.size()>0)
						((TSTPageProcessor) task).setDataZYRY(zyrys,regNumber);
				}
			}
		}
	}
}
