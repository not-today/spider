package com.heetian.spider.process.SiChuan;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.SiChuanProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextBAXXProcess extends SiChuanProcessHandlePrepare {
	public ContextBAXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ztxy.do?method=baInfo");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		TSTPageProcessor tst = (TSTPageProcessor)task;
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		Selectable tables = page.getHtml().xpath("//div[@id='beian']");
		//主要人员
		List<String> zyryTrs = tables.xpath("//table[@id='table_ry1']//tr").all();
		if(zyryTrs!=null&&zyryTrs.size()>3){
			zyryTrs.remove(0);
			zyryTrs.remove(0);
			zyryTrs.remove(zyryTrs.size()-1);
			tst.setDataZYRY(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(zyryTrs, regNumber, page),regNumber);
		}
		//分支机构
		List<String> fzjgTrs = tables.xpath("//table[@id='table_fr2']//tr").all();
		if(fzjgTrs!=null&&fzjgTrs.size()>3){
			fzjgTrs.remove(0);
			fzjgTrs.remove(0);
			fzjgTrs.remove(fzjgTrs.size()-1);
			tst.setDataFZJG(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(fzjgTrs, regNumber, page),regNumber);
		}
		List<String> zgbmTrs = tables.xpath("//talbe[@id='table_fr']//tr").all();
		if(zgbmTrs!=null&&zgbmTrs.size()>3){
			zgbmTrs.remove(0);
			zgbmTrs.remove(0);
			zgbmTrs.remove(zgbmTrs.size()-1);
			tst.setDataGD(AnalysisForTable.gdxxHTMLToJObjZGBM(this, zgbmTrs, regNumber, entName, task.getSite(), page), regNumber);
		}
	}
}

