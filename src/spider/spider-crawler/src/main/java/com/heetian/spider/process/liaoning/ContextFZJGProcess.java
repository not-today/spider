package com.heetian.spider.process.liaoning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!fzjgFrame.dhtml?ent_id=&clear=true&timeStamp=1432790102818
 * 分支机构 /gjjbj/gjjQueryCreditAction!fzjgFrame.dhtml
 * @author tst
 *沈阳矿山机械集团设备制造有限公司
 */
public class ContextFZJGProcess extends LiaoNingProcessHandlePrepare {
	public ContextFZJGProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/saicpub/entPublicitySC/entPublicityDC/getFgsxxAction.action");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String jsonStr = page.getHtml().regex("(\\s*fzjgPaging\\s*\\(\\s*\\[\\s*)(\\{.*\\}\\s*,?\\s*)+(\\s*\\]\\s*,\\s*\"\\d*\"\\s*\\))",2).get();
		if(jsonStr!=null&&!"".equals(jsonStr)){
			List<Map<String, String>> results = AnalysisForJson.jsonToObject("["+jsonStr+"]",new TypeToken<List<Map<String, String>>>() {});
			if(results!=null&&results.size()>0){
				String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
				List<GsgsBranch> branchInfos = new ArrayList<GsgsBranch>();
				for(Map<String, String> result:results){
					branchInfos.add(AnalysisForTable.createBranch(regNumber, result.get("regno"), result.get("brname"), result.get("regorgName")));
				}
				if(branchInfos.size()>0){
					((TSTPageProcessor) task).setDataFZJG(branchInfos,regNumber);
				}
			}
		}
	}
}
