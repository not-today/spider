package com.heetian.spider.process.zhejiang;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ZheJiangProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContexBAProcess extends ZheJiangProcessHandlePrepare {
	public ContexBAProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/filinginfo/doViewFilingInfo.do");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		if("all".equals(page.getRequest().getExtra(keyData))){
			zf(page, regNumber, entName, tst,true,true);
		}else if("zyry".equals(page.getRequest().getExtra(keyData))){
			zf(page, regNumber, entName, tst, true, false);
		}else if("fzjg".equals(page.getRequest().getExtra(keyData))){
			zf(page, regNumber, entName, tst, false, true);
		}
	}
	private void zf(Page page, String regNumber, String entName, TSTPageProcessor tst,boolean zy,boolean fz) {
		List<String> tables = page.getHtml().css("table").all();
		for(String table:tables){
			if(table==null||"".equals(table.trim()))
				continue;
			if(table.contains("主要人员信息")){
				if(zy){
					List<String> zyryTrs = new Html(table).xpath("//table//tr").all();
					if(zyryTrs==null||zyryTrs.size()<=2)
						return;
					zyryTrs.remove(0);
					zyryTrs.remove(0);
					tst.setDataZYRY(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(zyryTrs, regNumber, page),regNumber);
					String nextHtml = zyryTrs.get(zyryTrs.size()-1);
					nextPage(page, regNumber, entName, nextHtml, "goPageEntMember", "zyry",new String[]{"entMemberPagination.currentPage","entMemberPagination.pageSize"},"10");
				}
			}else if(table.contains("分支机构信息")){
				if(fz){
					List<String> fzjgTrs = new Html(table).xpath("//table//tr").all();
					if(fzjgTrs==null||fzjgTrs.size()<=2)
						return;
					fzjgTrs.remove(0);
					fzjgTrs.remove(0);
					tst.setDataFZJG(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(fzjgTrs, regNumber, page),regNumber);
					String nextHtml = fzjgTrs.get(fzjgTrs.size()-1);
					nextPage(page, regNumber, entName, nextHtml, "goPageBranchInfo", "fzjg",new String[]{"branchInfoPagination.currentPage","branchInfoPagination.pageSize"},"5");
				}
			}
		}
	}
}

