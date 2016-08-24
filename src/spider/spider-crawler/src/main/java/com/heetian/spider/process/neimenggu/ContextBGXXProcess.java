package com.heetian.spider.process.neimenggu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.process.abstractclass.NeiMengGuProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextBGXXProcess extends NeiMengGuProcessHandlePrepare {
	public ContextBGXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/aiccips/GSpublicity/entChaPage");
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		Request tmp = page.getRequest();
		String regNumber = (String) tmp.getExtra(REGNUMBER);
		if(page.getRawText()!=null){
			BGBeanJson beanJson = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<BGBeanJson>(){});
			if(beanJson!=null){
				List<GsgsChange> bgs = new ArrayList<GsgsChange>();
				List<BGContentBean> contentBeans = beanJson.getList();
				if(contentBeans!=null&&contentBeans.size()>0){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					for(BGContentBean contentBean:contentBeans){
					 /*if(model.altFiledName=='自然人股东'||model.altFiledName=='法人股东'){	
				    (model.altFiledName==null?"":model.altFiledName)+"</td> ";
                    ('"+load+"GSpublicity/invastorOld.html?oldHistNo="+model.oldHistNo+"&altFiledName="+model.altTable+"&entNo="+model.entNo+"&regOrg="+model.regOrg+"')\">查看变更前资料</a></td> ";
                    ('"+load+"GSpublicity/invastorNew.html?newHistNo="+model.newHistNo+"&altFiledName="+model.altTable+"&entNo="+model.entNo+"&regOrg="+model.regOrg+"')\">查看变更后资料</a></td> ";
                    (model.altDate==null?"":new Date(model.altDate).Format("yyyy年MM月dd日"))+"</td> ";
					 }*/
						GsgsChange bg = AnalysisForTable.createChangeInfo(regNumber, contentBean.getAltFiledName(), contentBean.getAltBe(), contentBean.getAltAf(), sdf.format(new Date(contentBean.getAltDate())));
						bgs.add(bg);
					}
					if(bgs.size()>0)
						((TSTPageProcessor) task).setDataBG(bgs,regNumber);
				}
			}
		}
	}
}