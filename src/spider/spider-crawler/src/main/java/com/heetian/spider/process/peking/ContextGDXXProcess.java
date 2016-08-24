package com.heetian.spider.process.peking;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGDXXProcess extends PekingProcessHandlePrepare {
	public ContextGDXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!tzrFrame.dhtml");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		nextURL(page, task);
		List<String> trs = page.getHtml().css("table").xpath("//tr").all();
		if(trs!=null&&trs.size()>3){
			trs.remove(0);
			trs.remove(0);
			trs.remove(trs.size()-1);
			String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
			String entName = (String) page.getRequest().getExtra(ENTNAME);
			((TSTPageProcessor) task).setDataGD(AnalysisForTable.gdxxHTMLToJObj(this,trs,regNumber,entName,task.getSite(),page),regNumber);
		}
	}
}
