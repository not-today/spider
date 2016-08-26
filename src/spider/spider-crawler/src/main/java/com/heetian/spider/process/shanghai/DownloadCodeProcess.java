package com.heetian.spider.process.shanghai;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.heetian.spider.component.EnterUrls;
import com.heetian.spider.process.abstractclass.ShangHaiProcessHandlePrepare;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends ShangHaiProcessHandlePrepare{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/notice/captcha");
		setProcessName(processName_down);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		nvps[1] = new BasicNameValuePair("captcha", "3");
		Request req= builderRequestPost(builderURL(EnterUrls.SHCom+"?"+urlTail(),task.getSite()), nvps);
		page.addTargetRequest(req);
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		return builderRequestGet(builderURL("/notice/captcha?preset=&ra=?"+Math.random(),site));
	}
}
