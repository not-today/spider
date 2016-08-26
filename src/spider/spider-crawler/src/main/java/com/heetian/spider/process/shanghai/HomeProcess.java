package com.heetian.spider.process.shanghai;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.heetian.spider.component.EnterUrls;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ShangHaiProcessHandlePrepare;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 长城汽车股份有限公司
 * @author tst
 *
 */
public class HomeProcess extends ShangHaiProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("https://www.sgs.gov.cn/notice/home");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		Request request = builderRequestGet(builderURL("/notice/captcha?preset=&ra=?"+Math.random(),task.getSite()));
		String sessionID = page.getHtml().xpath("//form//input[@name='session.token']/@value").get();
		NameValuePair[] nvps = {
				new BasicNameValuePair("condition.pageNo", "1"),
				new BasicNameValuePair("captcha","32"),	
				new BasicNameValuePair("condition.insType", ""),
				new BasicNameValuePair("session.token", sessionID),
				new BasicNameValuePair("condition.keyword", ((TSTPageProcessor)task).getSeedNm())
		};
		request.putExtra(NAMEVALUEPAIR, nvps);
		page.addTargetRequest(request);
	}
	public void old(Page page, PageProcessor task) {
		String sessionID = page.getHtml().xpath("//form//input[@name='session.token']/@value").get();
		NameValuePair[] nvps = {
				new BasicNameValuePair("condition.pageNo", "1"),
				new BasicNameValuePair("captcha","32"),	
				new BasicNameValuePair("condition.insType", ""),
				new BasicNameValuePair("session.token", sessionID),
				new BasicNameValuePair("condition.keyword", ((TSTPageProcessor)task).getSeedNm())
		};
		Request req= builderRequestPost(builderURL(EnterUrls.SHCom+"?"+urlTail(),task.getSite()), nvps);
		page.addTargetRequest(req);
	}
}
