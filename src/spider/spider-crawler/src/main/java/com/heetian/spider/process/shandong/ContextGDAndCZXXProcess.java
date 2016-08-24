package com.heetian.spider.process.shandong;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.ShanDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends ShanDongProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/pub/gsnzczxxdetail/");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String cz = page.getHtml().regex("(var\\s*czxxstr\\s*=')(.+)(';\\s*var\\s*czxxlist)",2).get();
		String rj = page.getHtml().regex("(var\\s*czxxrjstr\\s*=')(.+)(';\\s*var\\s*czxxrjlist)",2).get();
		String sj = page.getHtml().regex("(var\\s*czxxsjstr\\s*=')(.+)(';\\s*var\\s*czxxsjlist)",2).get();
		List<CZ> czs = AnalysisForJson.jsonToObject(cz,new TypeToken<List<CZ>>() {});
		List<RJ> rjs = AnalysisForJson.jsonToObject(rj,new TypeToken<List<RJ>>() {});
		List<SJ> sjs = AnalysisForJson.jsonToObject(sj,new TypeToken<List<SJ>>() {});
		int czn = czs==null?0:czs.size();
		int rjn = rjs==null?0:rjs.size();
		int sjn = sjs==null?0:sjs.size();
		int maxn = czn>=rjn?czn:rjn;
		maxn = maxn>= sjn?maxn:sjn;
		List<GsgsShareholderDetail> gdxxs = new ArrayList<GsgsShareholderDetail>();
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int x=0;x<maxn;x++){
			GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
			gdxx.setUuid(uuid);
			gdxx.setRegNumber(regNumber);
			if(czs!=null&&x<czs.size()){
				CZ czTmp = czs.get(x);
				if(czTmp!=null){
					gdxx.setName(czTmp.getInv());
					gdxx.setInvType(czTmp.getInvtype());
					gdxx.setStmn(czTmp.getLisubconam());
					gdxx.setPtmn(czTmp.getLiacconam());
				}
			}
			if(rjs!=null&&x<rjs.size()){
				RJ rjTmp = rjs.get(x);
				if(rjTmp!=null){
					gdxx.setSmn(rjTmp.getSubconam());
					gdxx.setSfrm(rjTmp.getConform());
					gdxx.setSd(sdf.format(new Date(Long.parseLong(rjTmp.getCondate().getTime())+3600000)));
				}
			}
			if(sjs!=null&&x<sjs.size()){
				SJ sjTmp = sjs.get(x);
				if(sjTmp!=null){
					gdxx.setPmn(sjTmp.getAcconam());
					gdxx.setPfrm(sjTmp.getConform());
					gdxx.setPd(sdf.format(new Date(Long.parseLong(sjTmp.getCondate().getTime())+3600000)));
				}
			}
			gdxxs.add(gdxx);
		}
		((TSTPageProcessor) task).setDataGDXQ(gdxxs,uuid,regNumber);
	}
}
