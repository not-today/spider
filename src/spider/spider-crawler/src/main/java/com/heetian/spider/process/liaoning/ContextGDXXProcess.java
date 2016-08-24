package com.heetian.spider.process.liaoning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 股东信息 /gjjbj/gjjQueryCreditAction!tzrFrame.dhtml
 * @author tst
 *
 */
public class ContextGDXXProcess extends LiaoNingProcessHandlePrepare {
	public ContextGDXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/saicpub/entPublicitySC/entPublicityDC/getTzrxxAction.action");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String jsonStr = page.getHtml().regex("(\\s*tzr_paging\\s*\\(\\s*\\[\\s*)(\\{.*\\}\\s*,?\\s*)+(\\s*\\]\\s*,\\s*\"\\d*\"\\s*,\\s*\".*\"\\s*,\\s*\"\\w*\"\\s*\\))",2).get();
		if(jsonStr!=null&&!"".equals(jsonStr)){
			List<Map<String, String>> results = AnalysisForJson.jsonToObject("["+jsonStr+"]",new TypeToken<List<Map<String, String>>>() {});
			if(results!=null&&results.size()>0){
				String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
				String entName = (String) page.getRequest().getExtra(ENTNAME);
				List<GsgsShareholder> holders = new ArrayList<GsgsShareholder>();
				for(Map<String, String> result:results){
					String uuid = TSTUtils.uuid();
					GsgsShareholder gd = AnalysisForTable.createShareHolder(regNumber, result.get("invtypeName"), result.get("inv"), result.get("blictypeName"), result.get("blicno"), null);
					gd.setUuid(uuid);
					holders.add(gd);
					NameValuePair[] nvps = {
							new BasicNameValuePair("pripid", (String) page.getRequest().getExtra("pripidforgd")),
							new BasicNameValuePair("invid", result.get("invid"))
					};
					Request request = builderRequest(builderURL("/saicpub/entPublicitySC/entPublicityDC/getGsgsTzrxxPojoList.action?"+urlTail()+Math.random(),task.getSite()), Method.POST, regNumber, entName, nvps);
					request.putExtra(ProcessHandle.uuid_key, uuid);
					page.addTargetRequest(request);
				}
				if(holders.size()>0){
					((TSTPageProcessor) task).setDataGD(holders,regNumber);
				}
			}
		}
	}
}
