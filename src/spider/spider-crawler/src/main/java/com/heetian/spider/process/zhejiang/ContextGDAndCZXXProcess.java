package com.heetian.spider.process.zhejiang;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.ZheJiangProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends ZheJiangProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		List<String> trs = page.getHtml().xpath("xxx").all();
		if(trs!=null&&trs.size()>2){
			trs.remove(0);
			trs.remove(0);
			String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
			TSTPageProcessor tst = ((TSTPageProcessor) task);
			tst.setDataGDXQ(AnalysisForTable.gdxxxxHTMLProcessToJAVAObject(trs,uuid,regNumber),uuid,regNumber);
		}
	}
}
