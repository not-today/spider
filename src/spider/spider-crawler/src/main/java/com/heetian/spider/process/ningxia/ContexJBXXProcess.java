package com.heetian.spider.process.ningxia;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.NingXiaProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexJBXXProcess extends NingXiaProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ECPS/qyxxgsAction_initQyjbqk.action");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		TSTPageProcessor tst = (TSTPageProcessor)task;
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		List<String> trs = page.getHtml().xpath("//table[1]//tr").all();
		GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName, trs,tst);
		jbxxBean.setUrl(TSTUtils.bufferedURL((String) page.getRequest().getExtra("currentURL_jiangxi"), httpMethodParam_get, null));
		((TSTPageProcessor) task).setDataJB(jbxxBean,regNumber);
	}
}

