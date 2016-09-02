package com.heetian.spider.process.shanghai;

import org.apache.http.NameValuePair;

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
		String url = "https://www.sgs.gov.cn/notice/security/verify_captcha?"+urlTail();
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		Request request = builderRequestPost(url, nvps);
		page.addTargetRequest(request);
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		Request tmp = builderRequestGet(builderURLHttps("/notice/captcha?preset=&ra=?"+Math.random(),site));
		tmp.putExtra(NAMEVALUEPAIR, request.getExtra(NAMEVALUEPAIR));
		return tmp;
	}
}
