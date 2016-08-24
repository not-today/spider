package com.heetian.spider.process.liaoning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!zyryFrame.dhtml?ent_id=0CD1263D6AE4009EE053A0630264009E&clear=true&timeStamp=1432790102816
 * 主要人员信息 /gjjbj/gjjQueryCreditAction!zyryFrame.dhtml
 * @author tst
 *
 */
public class ContextZYRYProcess extends LiaoNingProcessHandlePrepare {
	public ContextZYRYProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/saicpub/entPublicitySC/entPublicityDC/getZyryxxAction.action");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String jsonStr = page.getHtml().regex("(\\s*zyry_nz_paging\\s*\\(\\s*\\[\\s*)(\\{.*\\}\\s*,?\\s*)+(\\s*\\]\\s*,\\s*\"\\d*\"\\s*\\))",2).get();
		if(jsonStr!=null&&!"".equals(jsonStr)){
			List<Map<String, String>> results = AnalysisForJson.jsonToObject("["+jsonStr+"]",new TypeToken<List<Map<String, String>>>() {});  
			if(results!=null&&results.size()>0){
				String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
				List<GsgsMainPersonel> mainPersonls = new ArrayList<GsgsMainPersonel>();
				for(Map<String, String> result:results){
					mainPersonls.add(AnalysisForTable.createMainPerson(regNumber, result.get("name"), result.get("positionName")));
				}
				if(mainPersonls.size()>0){
					((TSTPageProcessor) task).setDataZYRY(mainPersonls,regNumber);
				}
			}
		}
	}
}
