package com.heetian.spider.process.shandong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.ShanDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexJBXXProcess extends ShanDongProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/pub/gsgsdetail");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String enttype = (String) page.getRequest().getExtra("enttype");
		NameValuePair[] nvps = new BasicNameValuePair[1];
		nvps[0] = new BasicNameValuePair("encrpripid",(String) page.getRequest().getExtra("encrpripid"));
		Request zyry = builderRequest(builderURL("/pub/gsryxx/"+enttype+"?"+urlTail(),task.getSite()),Method.POST, regNumber,entName, nvps);
		zyry.putExtra("enttype", enttype);
		page.addTargetRequest(zyry);
		
		Request fzjg = builderRequest(builderURL("/pub/gsfzjg/"+enttype+"?"+urlTail(),task.getSite()),Method.POST, regNumber,entName, nvps);
		page.addTargetRequest(fzjg);
		
		List<String> trs = page.getHtml().xpath("//div[@id='jibenxinxi']/table[1]//tr").all();
		GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName,trs,tst);
		if(jbxxBean==null)
			return;
		jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
		tst.setDataJB(jbxxBean,regNumber);
		
		String gdxx = page.getHtml().regex("(var\\s*czxxliststr\\s*=\\s*')(\\[\\{.*\\}\\])('\\s*;\\s*var\\s*bgsxliststr\\s*=\\s*')",2).get();
		if(gdxx!=null&&!"".equals(gdxx)&&!"'[]'".equals(gdxx)&&!"[]".equals(gdxx)&&!"null".equals(gdxx)){
			List<Map<String, String>> gdxxs = AnalysisForJson.jsonToObject(gdxx,new TypeToken<List<Map<String, String>>>() {});
			if(gdxxs!=null&&gdxxs.size()>0){
				List<GsgsShareholder> holders = new ArrayList<GsgsShareholder>();
				for(Map<String, String> result:gdxxs){
					GsgsShareholder holder = AnalysisForTable.createShareHolder(regNumber, result.get("invtype"), result.get("inv"), result.get("blictype"), result.get("blicno"), null);
					holders.add(holder);
					//　　　/aiccips/pub/gsnzczxxdetail/'+encrpripid.toString()+'/'+czxx.recid+
					if("1".equals(result.get("xzqh"))){
						String uuid = TSTUtils.uuid();
						holder.setUuid(uuid);
						String encrpripid = (String) page.getRequest().getExtra("encrpripid");
						String url = builderURL("/pub/gsnzczxxdetail/"+encrpripid+"/"+result.get("recid"),task.getSite());
						Request request = builderRequest(url, Method.GET, regNumber, entName, null);
						request.putExtra(ProcessHandle.uuid_key, uuid);
						page.addTargetRequest(request);
					}
				}
				tst.setDataGD(holders,regNumber);
			}
		}
		String bgxx = page.getHtml().regex("(var\\s*bgsxliststr\\s*=\\s*')(\\[\\{\".*\"\\}\\])('\\s*;)",2).replace("", "").get();
		if(bgxx!=null&&!"".equals(bgxx)&&!"'[]'".equals(bgxx)&&!"[]".equals(bgxx)&&!"null".equals(bgxx)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<BianGengXX> bgxxs = AnalysisForJson.jsonToObject(bgxx,new TypeToken<List<BianGengXX>>() {});
			if(bgxxs!=null&&bgxxs.size()>0){
				List<GsgsChange> changeinfoList = new ArrayList<GsgsChange>();
				for(BianGengXX result:bgxxs){
					changeinfoList.add(AnalysisForTable.createChangeInfo(regNumber, result.getAltitem(), result.getAltbe(), result.getAltaf(), sdf.format(new Date(Long.parseLong(result.getAltdate().getTime())))));
				}
				tst.setDataBG(changeinfoList,regNumber);
			}
		}
	}
}