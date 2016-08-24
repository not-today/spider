package com.heetian.spider.process.peking;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 *   北京金玉满堂投资有限公司(变更信息都有,且变更信息有异常，还有翻页的)
 * @author tst
 *
 */
public class ContexJBXXProcess extends PekingProcessHandlePrepare {
	private static final String urls[] = {
		"/gjjbj/gjjQueryCreditAction!biangengFrame.dhtml","/gjjbj/gjjQueryCreditAction!zyryFrame.dhtml",
		"/gjjbj/gjjQueryCreditAction!tzrFrame.dhtml","/gjjbj/gjjQueryCreditAction!fzjgFrame.dhtml"};
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!openEntInfo.dhtml");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		TSTPageProcessor tst = (TSTPageProcessor)task;
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		String entNameBak = entName;
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		List<String> trs = page.getHtml().xpath("//div[@id='djxxDiv']/div[@id='jbxx']/table//tr").all();
		GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName,trs ,tst);
		if(jbxxBean==null)
			return;
		jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
		tst.setDataJB(jbxxBean,regNumber);
		String entId = (String) page.getRequest().getExtra("entId");
		try {
			entId = URLEncoder.encode(entId,"utf-8");
			entName = URLEncoder.encode(entName,"utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		for(String urlHead:urls){
			String nextURL = "";
			if(urlHead.equals("/gjjbj/gjjQueryCreditAction!tzrFrame.dhtml")){
				//nextURL = urlHead+"?ent_id="+entId+"&entName="+entName+"&clear=true&timeStamp="+System.currentTimeMillis();
				nextURL = urlHead+"?ent_id="+entId+"&entName=&clear=true&timeStamp="+System.currentTimeMillis();
			}else if(urlHead.equals("/gjjbj/gjjQueryCreditAction!fzjgFrame.dhtml")){
				nextURL = urlHead+"?ent_id="+entName+"&clear=true&timeStamp="+System.currentTimeMillis();
			}else{
				nextURL = urlHead+"?ent_id="+entId+"&clear=true&timeStamp="+System.currentTimeMillis();
			}
			page.addTargetRequest(builderRequest(builderURL(nextURL,task.getSite()), Method.GET, regNumber,entNameBak,nvps));
		}
	}
}

