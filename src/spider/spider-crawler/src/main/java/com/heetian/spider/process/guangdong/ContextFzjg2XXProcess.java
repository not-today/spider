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
public class ContextFzjg2XXProcess extends GuangDongProcessHandlePrepare {
	public ContextFzjg2XXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("search!branchListShow");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		Request tmp = page.getRequest();
		String regNumber = (String) tmp.getExtra(REGNUMBER);
		List<String> trs = page.getHtml().xpath("//tbody[@id='tb_body4']//tr").all();
		if(trs!=null&&trs.size()>0){
			((TSTPageProcessor) task).setDataFZJG(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(trs, regNumber, page),regNumber);
		}
	}
}