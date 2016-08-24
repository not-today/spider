package com.heetian.spider.process.guangdong;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.GuangDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexDJ2Process extends GuangDongProcessHandlePrepare {
	public ContexDJ2Process(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/web/GSZJGSPT/QyxyDetail.aspx");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		task.getSite().addHeader("Referer", page.getRequest().getUrl());
		//System.out.println(page.getRawText());
		//String pageCu = page.getHtml().xpath("//label[@id='lblPageCount']/allText()").get();
		//String pageCu = page.getHtml().regex("(\\s*var\\s*where\\s*=\\s*\")(.+)(\";\\s*$)",2).get();
		String pripid = page.getHtml().regex("( pageIndex2,\"entityVo.pripid\":')(.+)(' \"where\")").get();
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		List<String> jbTrs = page.getHtml().xpath("//div[@id='jibenxinxi']/div[1]/table[1]//tr").all();
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName, jbTrs,tst);
		if(jbxxBean==null){
			return ;
		}
		jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
		tst.setDataJB(jbxxBean,regNumber);
		//股东信息
		page.addTargetRequest(createOtherInfo("search!investorListShow", task.getSite(), pripid, " where 1=1", regNumber, entName));
		//变更信息
		page.addTargetRequest(createOtherInfo("search!changeListShow", task.getSite(), pripid, " where 1=1", regNumber, entName));
		//主要人员
		page.addTargetRequest(createOtherInfo("search!staffListShow", task.getSite(), pripid, " where 1=1", regNumber, entName));
		//分支结构
		page.addTargetRequest(createOtherInfo("search!branchListShow", task.getSite(), pripid, " where 1=1", regNumber, entName));
	}
	public Request createOtherInfo(String url ,Site site,String pripid,String where,String regNumber,String entName){
		url = builderURL(url, site)+"?entityVo.curPage=1&entityVo.pripid="+pripid+"&where="+where;
		return builderRequest(url, Method.GET, regNumber, entName, null);
	}
}

