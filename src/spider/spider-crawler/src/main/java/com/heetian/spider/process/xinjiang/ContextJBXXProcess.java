package com.heetian.spider.process.xinjiang;

import java.util.List;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.XinJiangProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContextJBXXProcess extends XinJiangProcessHandlePrepare {
	public ContextJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ztxy.do?method=qyInfo");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		List<String> tables = page.getHtml().xpath("//div[@id='jibenxinxi']//table").all();
		GsgsRegister jbxx = null;
		for(String table:tables){
			if(!table.contains("基本信息"))
				continue;
			List<String> trs = new Html(table).xpath("//tr").all();
			jbxx = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName, trs,tst);
			if(jbxx==null)
				return;
			NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
			jbxx.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(),httpMethodParam_post,nvps));
		}
		if(jbxx==null)
			return;
		//股东
		List<String> gdTrs = page.getHtml().xpath("//div[@id='jibenxinxi']//table[@id='table_fr']//tr").all();
		if(gdTrs!=null&&gdTrs.size()>3){
		 	gdTrs.remove(0);
			//if(tmp.contains("投资人信息")){
			gdTrs.remove(0);
			gdTrs.remove(gdTrs.size()-1);
			jbxx.setShareholders(AnalysisForTable.gdxxHTMLToJObjSC(this,gdTrs, regNumber, entName, task.getSite(), page));
		}
		//变更
		List<String> bgTrs = page.getHtml().xpath("//div[@id='jibenxinxi']//table[@id='table_bg']//tr").all();
		if(bgTrs!=null&&bgTrs.size()>3){
			bgTrs.remove(0);
			bgTrs.remove(0);
			bgTrs.remove(bgTrs.size()-1);
			jbxx.setChanges(AnalysisForTable.bgxxHTMLProcessToJAVAObject(bgTrs, regNumber, page));
		}
		tst.setDataJB(jbxx,regNumber);
	}
}

