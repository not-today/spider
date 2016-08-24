package com.heetian.spider.process.tianjing;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.TianJingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends TianJingProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/saicpf/gsgdcz");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		List<String> trs = page.getHtml().xpath("//table//tr").all();
		if(trs!=null&&trs.size()>3){
			trs.remove(0);
			trs.remove(0);
			trs.remove(0);
			String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
			List<GsgsShareholderDetail> gdxxs = new ArrayList<GsgsShareholderDetail>();
			String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
			for(String tr:trs){
				List<String> tds = new Html("<table>"+tr+"</table>").xpath("//table//tr//td").all();
				if(tds==null||tds.size()<=0)
					continue;
				GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
				gdxx.setRegNumber(regNumber);
				gdxx.setUuid(uuid);
				if(tds.size()==3){
					gdxx.setName(AnalysisForTable.getTdText(tds.get(0)));
					gdxx.setStmn(AnalysisForTable.getTdText(tds.get(1)));
					gdxx.setPtmn(AnalysisForTable.getTdText(tds.get(2)));
					
				}else if(tds.size()==6){
					gdxx.setSfrm(AnalysisForTable.getTdText(tds.get(0)));
					gdxx.setSmn(AnalysisForTable.getTdText(tds.get(1)));
					gdxx.setSd(AnalysisForTable.getTdText(tds.get(2)));
					gdxx.setPfrm(AnalysisForTable.getTdText(tds.get(3)));
					gdxx.setPmn(AnalysisForTable.getTdText(tds.get(4)));
					gdxx.setPd(AnalysisForTable.getTdText(tds.get(5)));
				}
				gdxxs.add(gdxx);
			}
			if(gdxxs.size()>0){
				((TSTPageProcessor) task).setDataGDXQ(gdxxs,uuid,regNumber);
			}
				
		}
	}
}
