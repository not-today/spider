package com.heetian.spider.process.guangdong;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.GuangDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGD2XXProcess extends GuangDongProcessHandlePrepare {
	public ContextGD2XXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("search!investorListShow");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		Request tmp = page.getRequest();
		String regNumber = (String) tmp.getExtra(REGNUMBER);
		String entName = (String) tmp.getExtra(ENTNAME);
		List<String> trs = page.getHtml().xpath("//tbody[@id='tb_body']//tr").all();
		if(trs!=null&&trs.size()>0){
			((TSTPageProcessor) task).setDataGD(AnalysisForTable.gdxxHTMLToJObj(this, trs, regNumber, entName, task.getSite(), page),regNumber);
		}
	}
}