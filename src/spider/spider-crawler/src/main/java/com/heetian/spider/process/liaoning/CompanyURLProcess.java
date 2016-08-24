package com.heetian.spider.process.liaoning;

import java.util.Iterator;
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
import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends LiaoNingProcessHandlePrepare{
	private static final String nomalURLs[] = {"/saicpub/entPublicitySC/entPublicityDC/getJbxxAction.action",//基本信息
		"/saicpub/entPublicitySC/entPublicityDC/getTzrxxAction.action",//股东信息
		"/saicpub/entPublicitySC/entPublicityDC/getBgxxAction.action",//变更信息
		"/saicpub/entPublicitySC/entPublicityDC/getZyryxxAction.action",//主要人员信息
		"/saicpub/entPublicitySC/entPublicityDC/getFgsxxAction.action"//分支机构信息
	};
	private static final String exceptionURLs[] = {"/saicpub/entPublicitySC/entPublicityDC/getJbxxAction.action"};
	
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/saicpub/entPublicitySC/entPublicityDC/lngsSearchFpc.action");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String codeResult = page.getHtml().regex("\\s*var\\s*codevalidator\\s*=\\s*\"fail\"").get();//判断验证码是否输入错误
		String resultsImage[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		if(codeResult==null){
			RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			String jsonStr = page.getHtml().regex("(\\s*searchList_paging\\s*\\(\\s*\\[\\s*)(\\{.*\\}\\s*,?\\s*)+(\\s*\\]\\s*,\\s*\"\\d*\"\\s*\\))",2).get();//获取公司url
			if(jsonStr==null||"".equals(jsonStr.trim())){
				return;
			}
			List<Map<String, String>> results = AnalysisForJson.jsonToObject("["+jsonStr+"]",new TypeToken<List<Map<String, String>>>() {});
			if(results==null||results.size()<=0){
				return;
			}
			Iterator<Map<String, String>> iter = results.iterator();
			while(iter.hasNext()){
				Map<String, String> result = iter.next();
				String regNumber = result.get("regno").trim();
				String entName = result.get("entname").trim();
				if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
					iter.remove();
					continue;
				}
				String urls[] = null;
				if("2".equals(result.get("optstate"))){
					urls = exceptionURLs;
				}else{
					urls = nomalURLs;
				}
				String pripid = result.get("pripid");
				String urltail = "?pripid="+pripid+"&type="+result.get("enttype");
				for(String url :urls){
					Request request = builderRequest(builderURL(url+urltail,task.getSite()),Method.GET, regNumber,entName, null);
					request.putExtra("pripidforgd", pripid);
					page.addTargetRequest(request);
				}
			}
			((TSTPageProcessor)task).setSeedSdP(results.size());
		}else{//验证码输入错误后的处理
			RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			String url = builderURL("/saicpub/commonsSC/loginDC/securityCode.action?tdate="+TSTUtils.randomNum() +"&"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
		}
	}
	@Deprecated
	public NameValuePair[] oldObtain(Page page, PageProcessor task,Map<String, String> result, String regNumber, String entName) {
		NameValuePair[] nvps = {
			 new BasicNameValuePair("regno", regNumber),
			 new BasicNameValuePair("enttype", result.get("enttype")),
			 new BasicNameValuePair("pripid", result.get("pripid")),
			 new BasicNameValuePair("entname", entName),
			 new BasicNameValuePair("opstate", result.get("optstate"))
		};
		String url = builderURL("/saicpub/entPublicitySC/entPublicityDC/sEntDetail.action"+"?"+urlTail(),task.getSite());
		Request request = builderRequest(url,Method.POST, regNumber,entName, nvps);
		request.putExtra("optstate", result.get("optstate"));//登记状态2为注销。其他正常
		page.addTargetRequest(request);
		return nvps;
	}
}
