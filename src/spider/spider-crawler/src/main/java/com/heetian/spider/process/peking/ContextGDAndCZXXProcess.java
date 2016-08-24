package com.heetian.spider.process.peking;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends PekingProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!touzirenInfo.dhtml");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		nextURL(page, task);
		List<String> trs = page.getHtml().xpath("//div[@id='sifapanding']/table//tr").all();
		if(trs!=null&&trs.size()>4){
			trs.remove(0);
			trs.remove(0);
			trs.remove(0);
			trs.remove(trs.size()-1);
			String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
			String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
			((TSTPageProcessor) task).setDataGDXQ(AnalysisForTable.gdxxxxHTMLProcessToJAVAObject(trs, uuid, regNumber),uuid,regNumber);
		}
	}
}
