package com.heetian.spider.process.jiangxi;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexJBXXProcess extends JiangXiProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/baseinfo/queryenterpriseinfoByRegnore.do");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		TSTPageProcessor tst = (TSTPageProcessor)task;
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		JBBean bean = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<JBBean>(){});
		GsgsRegister jbxxBean = bean.toRegister();
		if(jbxxBean==null)
			return;
		jbxxBean.setPvn(tst.getcode());
		jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
		tst.setDataJB(jbxxBean,regNumber);
	}
}

