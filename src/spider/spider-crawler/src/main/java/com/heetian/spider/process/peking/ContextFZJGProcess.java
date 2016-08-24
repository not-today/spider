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
public class ContextFZJGProcess extends PekingProcessHandlePrepare {
	public ContextFZJGProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!fzjgFrame.dhtml");
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
			((TSTPageProcessor) task).setDataFZJG(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(trs,regNumber,page),regNumber);
		}
	}
}
