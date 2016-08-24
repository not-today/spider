package com.heetian.spider.process.peking;

import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.SeedStatusEnum;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends PekingProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setProcessName(processName_list);
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String context = page.getRawText();
		TSTPageProcessor tst = (TSTPageProcessor) task;
		if(context==null||context.contains("可能访问过于频繁或非正常访问")){
			tst.setProxyStatus(ProxyStatus.NO);
			tst.setStatus(SeedStatusEnum.reco);
			return;
		}
		List<String> params = page.getHtml().xpath("//div[@class='list']/ul/li/a/@onclick").regex("(\\s*\\w+\\s*\\()(.*)(\\).*)",2).replace("['\\s]", "").all();
		if(params==null||params.size()<=0){
			return;
		}
		Iterator<String> iter = params.iterator();
		while(iter.hasNext()){
			String tmp = iter.next();
			String paramPer[] = tmp.split(",");
			if(paramPer==null||paramPer.length<3){
				logger.warn("构建基本url参数出现问题:"+tmp);
				continue;
			}
			String entName = paramPer[0].trim();
			String regId = paramPer[1] ;
			String regNumber = paramPer[2].trim();
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				iter.remove();
				continue;
			}
			String credit_ticket = paramPer[3];
			String nextURL = builderURL("/gjjbj/gjjQueryCreditAction!openEntInfo.dhtml?entId="
			+regId+"&credit_ticket="+credit_ticket+"&entNo="+regNumber+"&timeStamp="+System.currentTimeMillis(),task.getSite());
			NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra("nameValuePair");
			Request request = builderRequest(nextURL, Method.GET, regNumber,entName, nvps);
			nextURL = request.getUrl();
			request.putExtra("entId", regId);
			page.addTargetRequest(request);
		}
		tst.setSeedSdP(params.size());
	}
}
