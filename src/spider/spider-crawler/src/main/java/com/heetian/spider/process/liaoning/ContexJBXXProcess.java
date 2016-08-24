package com.heetian.spider.process.liaoning;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 页面处理类，基本信息   /gjjbj/gjjQueryCreditAction!openEntInfo.dhtml
 * @author tst
 *大连实德集团有限公司
 */
public class ContexJBXXProcess extends LiaoNingProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/saicpub/entPublicitySC/entPublicityDC/getJbxxAction.action");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		List<String> trs = page.getHtml().xpath("//div[@id='jibenxinxi']/table//tr").all();
		GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName,trs,tst);
		if(jbxxBean==null){
			return;
		}
		String urlTmp = TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null);
		jbxxBean.setUrl(urlTmp);
		tst.setDataJB(jbxxBean,regNumber);
	}
}

