package com.heetian.spider.process.hainan;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.HaiNanProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends HaiNanProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/aiccips/GSpublicity/invInfoDetails.html");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		List<String> trs = page.getHtml().xpath("//table[1]//tr").all();
		if(trs!=null&&trs.size()>2){
			trs.remove(0);
			trs.remove(0);
			String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
			String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
			((TSTPageProcessor) task).setDataGDXQ(AnalysisForTable.gdxxxxHTMLProcessToJAVAObject(trs, uuid, regNumber),uuid,regNumber);
		}
	}
}
