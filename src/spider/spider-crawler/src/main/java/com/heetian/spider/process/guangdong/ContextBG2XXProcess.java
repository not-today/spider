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
public class ContextBG2XXProcess extends GuangDongProcessHandlePrepare {
	public ContextBG2XXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("search!changeListShow");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		Request tmp = page.getRequest();
		String regNumber = (String) tmp.getExtra(REGNUMBER);
		List<String> trs = page.getHtml().xpath("//tbody[@id='tb_body2']//tr").all();
		if(trs!=null&&trs.size()>0){
			((TSTPageProcessor) task).setDataBG(AnalysisForTable.bgxxHTMLProcessToJAVAObject(trs, regNumber, page),regNumber);
		}
	}
}