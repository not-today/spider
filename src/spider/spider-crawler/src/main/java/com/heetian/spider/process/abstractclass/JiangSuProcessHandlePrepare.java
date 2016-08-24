package com.heetian.spider.process.abstractclass;

import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;


public abstract class JiangSuProcessHandlePrepare extends ProcessHandlePrepare {
	protected Request rDate(int n,PageProcessor task){
		NameValuePair[] nvps = {
				new BasicNameValuePair("showRecordLine",  "0"),
				new BasicNameValuePair("specificQuery",  "commonQuery"),
				new BasicNameValuePair("propertiesName",  "recordTime"),
				new BasicNameValuePair("n",  String.valueOf(n)),
				new BasicNameValuePair("tmp",  new Date().toString())
		};
		String url = builderURL("/province/commonServlet.json?commonEnter=true&"+urlForParamsTailForJiangSu(), task.getSite());
		return builderRequest(url, Method.POST, null, null, nvps);
	}
	protected String urlForParamsTailForJiangSu(){
		return urlTail()+Math.random()+Math.random();
	}
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		
	}
}
