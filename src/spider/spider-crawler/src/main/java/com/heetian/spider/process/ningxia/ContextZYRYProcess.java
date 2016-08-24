package com.heetian.spider.process.ningxia;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.NingXiaProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextZYRYProcess extends NingXiaProcessHandlePrepare {
	public ContextZYRYProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ECPS/qybaxxAction_zyryxx.action");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		nextPage("/ECPS/qybaxxAction_zyryxx.action",page, task, regNumber, entName, page.getHtml().xpath("//table[2]//tr//a/@href").all());
		List<String> trs = page.getHtml().xpath("//table[1]//tr").all();
		if(trs!=null&&trs.size()>2){
			trs.remove(0);
			trs.remove(0);
			((TSTPageProcessor) task).setDataZYRY(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(trs,regNumber,page),regNumber);
		}	
	}
}
