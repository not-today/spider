package com.heetian.spider.process.liaoning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!biangengFrame.dhtml?ent_id=0907CD07C3C14AF8A97ABF76A19B533B&clear=true&timeStamp=1432781276952
 * 解析变更信息，/gjjbj/gjjQueryCreditAction!biangengFrame.dhtml
 * @author tst
 *
 */
public class ContextBGXXProcess extends LiaoNingProcessHandlePrepare {
	public ContextBGXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/saicpub/entPublicitySC/entPublicityDC/getBgxxAction.action");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String jsonStr = page.getHtml().regex("(\\s*paging\\s*\\(\\s*\\[\\s*)(\\{.*\\}\\s*,?\\s*)+(\\s*\\]\\s*,\\s*size\\))",2).get();
		if(jsonStr!=null&&!"".equals(jsonStr)){
			List<Map<String, String>> results = AnalysisForJson.jsonToObject("["+jsonStr+"]",new TypeToken<List<Map<String, String>>>() {});
			if(results!=null&&results.size()>0){
				String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
				List<GsgsChange> changeinfoList = new ArrayList<GsgsChange>();
				for(Map<String, String> result:results){
					changeinfoList.add(AnalysisForTable.createChangeInfo(regNumber, result.get("altitemName"), result.get("altbe"), result.get("altaf"), result.get("altdate")));
				}
				if(changeinfoList.size()>0){
					((TSTPageProcessor) task).setDataBG(changeinfoList,regNumber);
				}
			}
		}
	}
}