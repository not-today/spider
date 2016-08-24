package com.heetian.spider.process.zhejiang;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.ZheJiangProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexDJProcess extends ZheJiangProcessHandlePrepare {
	public ContexDJProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/appbasicinfo/doReadAppBasicInfo.do");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		if("all".equals(page.getRequest().getExtra(keyData))){
			List<String> jbTrs = page.getHtml().xpath("//table[@id='baseinfo']//tr").all();
			if(jbTrs==null||jbTrs.size()<=0)
				return;
			GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName, jbTrs,tst);
			if(jbxxBean==null)
				return;
			jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
			tst.setDataJB(jbxxBean,regNumber);
			gb(page, task, regNumber, entName, tst,true,true);
		}else if("gd".equals(page.getRequest().getExtra(keyData))){
			gb(page, task, regNumber, entName, tst,true,false);
		}else if("bg".equals(page.getRequest().getExtra(keyData))){
			gb(page, task, regNumber, entName, tst,false,true);
		}
	}
	private void gb(Page page, PageProcessor task, String regNumber, String entName, TSTPageProcessor tst,boolean gd,boolean bg) {
		List<String> tables = page.getHtml().css("table").all();
		for(String table:tables){
			if(table==null||"".equals(table.trim()))
				continue;
			if(table.contains("股东信息")){
				if(gd){
					List<String> gdTrs = new Html(table).xpath("//table//tr").all();
					if(gdTrs==null||gdTrs.size()<=2)
						return;
					gdTrs.remove(0);
					gdTrs.remove(0);
					tst.setDataGD(AnalysisForTable.gdxxHTMLToJObj(this,gdTrs, regNumber, entName, task.getSite(), page),regNumber);
					String nextHtml = gdTrs.get(gdTrs.size()-1);
					nextPage(page, regNumber, entName, nextHtml, "goPageEntInvestor", "gd",new String[]{"entInvestorPagination.currentPage","entInvestorPagination.pageSize"},"5");
				}
			}else if(table.contains("变更信息")){
				if(bg){
					List<String> bgTrs = new Html(table).xpath("//table//tr").all();
					if(bgTrs==null||bgTrs.size()<=2)
						continue;
					bgTrs.remove(0);
					bgTrs.remove(0);
					tst.setDataBG(AnalysisForTable.bgxxHTMLProcessToJAVAObject(bgTrs, regNumber, page),regNumber);
					String nextHtml = bgTrs.get(bgTrs.size()-1);
					nextPage(page, regNumber, entName, nextHtml , "goPageCheckAlter", "bg",new String[]{"checkAlterPagination.currentPage","checkAlterPagination.pageSize"},"5");
				}
			}
		}
	}
}

