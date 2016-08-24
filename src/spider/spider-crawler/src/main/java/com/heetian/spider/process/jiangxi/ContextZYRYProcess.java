package com.heetian.spider.process.jiangxi;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextZYRYProcess extends JiangXiProcessHandlePrepare {
	public ContextZYRYProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/epriperson/queryPerson.do");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		List<ZYRYBean> bean = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<List<ZYRYBean>>(){});
		if(bean==null||bean.size()<=0)
			return;
		String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
		List<GsgsMainPersonel> ms = new ArrayList<GsgsMainPersonel>();
		for(ZYRYBean tmp:bean){
			ms.add(tmp.toMainPerson(regNumber));
		}
		((TSTPageProcessor) task).setDataZYRY(ms,regNumber);
	}
	public void old(Page page, PageProcessor task, String regNumber) {
		List<String> tables = page.getHtml().xpath("//div[@id='main']/table[@class='detailsList']").all();
		if(tables==null||tables.size()<=0)
			return;
		for(String table:tables){
			if(table==null)
				continue;
			if(table.contains("主要人员信息")){
				Html tablehtml = new Html(table);
				List<String> trs = tablehtml.xpath("//table//tr").all();
				if(trs!=null&&trs.size()>2){
					trs.remove(0);
					trs.remove(0);
					((TSTPageProcessor) task).setDataZYRY(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(trs,regNumber,page),regNumber);
				}	
			}else if(table.contains("分支机构信息")){
				Html tablehtml = new Html(table);
				List<String> trs = tablehtml.xpath("//table//tr").all();
				if(trs!=null&&trs.size()>2){
					trs.remove(0);
					trs.remove(0);
					((TSTPageProcessor) task).setDataFZJG(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(trs,regNumber,page),regNumber);
				}
			}else if(table.contains("参加经营的家庭成员姓名")){
				Html tablehtml = new Html(table);
				List<String> trs = tablehtml.xpath("//table//tr").all();
				if(trs!=null&&trs.size()>2){
					trs.remove(0);
					trs.remove(0);
					((TSTPageProcessor) task).setDataZYRY(AnalysisForTable.jtcyxxHTMLProcessToJAVAObject(trs,regNumber,page),regNumber);
				}
			}else{
				continue;
			}
		}
	}
}
