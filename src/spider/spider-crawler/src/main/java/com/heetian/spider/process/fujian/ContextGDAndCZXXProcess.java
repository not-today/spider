package com.heetian.spider.process.fujian;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.process.abstractclass.FujianProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends FujianProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/creditpub/notice/view_investor");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		gdxqForzongju(page, task);
			
	}
	private void gdxqForzongju(Page page, PageProcessor task) {
		String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
		String rawText = page.getRawText();
		String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
		List<GsgsShareholderDetail> gdxxs = AnalysisForTable.gdxxxxHTMLProcessToJAVAObjectForAdministrator(regNumber, rawText, uuid);
		if(gdxxs.size()>0)
			((TSTPageProcessor) task).setDataGDXQ(gdxxs,uuid,regNumber);
	}
}
