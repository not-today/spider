package com.heetian.spider.process.tianjing;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.TianJingProcessHandlePrepare;
/**
 * @author tst
 *
 */
public class DetailURLProcess extends TianJingProcessHandlePrepare{
	public DetailURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/platform/saic/viewBase.ftl");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		//	<input id="hasInfo" type="hidden" value="0"/>
		String hasInfo = page.getHtml().xpath("//input[@id='hasInfo']/@value").get();
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		String entId = (String) page.getRequest().getExtra("entId");
		String gsdjlx = page.getHtml().regex("(var\\s*gsdjlx\\s*=\\s*\")(\\d+)(\";)",2).get();
		if(Integer.parseInt(gsdjlx)>=19){
			//logger.info(page.getRawText());
			String paramsComjb = page.getHtml().regex("(\\$\\(\"#contentMain\"\\)\\s*\\.load\\(\"/platform/saic/baseInfo.do\",\\{)(entId:\"?.+\"?,entType:\"?.+\"?)(\\s*\\}\\s*\\)\\s*;\\s*return)",2).replace("\"| |entId|entType|:", "").get();
			String paramsarr[] = paramsComjb.split(",");
			NameValuePair nvps[] = {
					new BasicNameValuePair("entId", paramsarr[0]),
					new BasicNameValuePair("entType", paramsarr[1])
			};
			Request request = builderRequest(builderURL("/platform/saic/baseInfo.do",task.getSite()), Method.POST, regNumber, entName, nvps);
			request.putExtra("entId", entId);
			request.putExtra("hasInfo", hasInfo);
			page.addTargetRequest(request);
			return ;
		}
		String paramsCOmot = page.getHtml().regex("(url:\")(/platform/saic/topInfoClass.json\\?departmentId=.+&entId="+entId+")(\")",2).get();
		Request request = builderRequest(builderURL(paramsCOmot,task.getSite()), Method.GET, regNumber, entName, null);
		request.putExtra("entId", entId);
		request.putExtra("hasInfo", hasInfo);
		page.addTargetRequest(request);
	}
	
}
