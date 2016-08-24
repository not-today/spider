package com.heetian.spider.process.abstractclass;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;


public abstract class PekingProcessHandlePrepare extends ProcessHandlePrepare {
	protected void nextURL(Page page, PageProcessor task) {
		Selectable selectForm = page.getHtml().css("form");//构建后续翻页url
		String pagescount = selectForm.xpath("//input[@id='pagescount']/@value").get();
		String after = selectForm.xpath("//table//tr//a[@title='下一页']/@onclick").regex("\\d+").replace(" ", "").get();
		if(after==null||"0".equals(after.trim())||after.trim().equals(String.valueOf(Integer.parseInt(pagescount)+1))){
		}else{
			String pageNos = selectForm.xpath("//input[@id='pageNos']/@value").get();
			String ent_id = selectForm.xpath("//input[@id='ent_id']/@value").get();
			String fqr = "";
			if("/gjjbj/gjjQueryCreditAction!tzrFrame.dhtml".equals(getUniqueWebUri())){
				fqr = selectForm.xpath("//input[@id='fqr']/@value").get();
			}
			String pageNo = selectForm.xpath("//input[@id='pageNo']/@value").get();
			String pageSize = selectForm.xpath("//input[@id='pageSize']/@value").get();
			String clear = selectForm.xpath("//input[@id='clear']/@value").get();
			pageNos = after;
			// 构建后续url
			String nextURL = getUniqueWebUri();
			BasicNameValuePair[] params = initParam(pagescount, pageNos,ent_id, fqr, pageNo, pageSize, clear);
			String regNumber = (String)page.getRequest().getExtra("regNumber");
			String entName = (String)page.getRequest().getExtra("entName");
			Request request = builderRequest(builderURL(nextURL+"?"+urlTail(),task.getSite()), Method.POST, regNumber,entName, params);
			page.addTargetRequest(request);
		}
	}
	protected BasicNameValuePair[] initParam(String pagescount, String pageNos,String ent_id, String fqr, String pageNo, String pageSize,String clear) {
		BasicNameValuePair[] params = null;
		if("/gjjbj/gjjQueryCreditAction!tzrFrame.dhtml".equals(getUniqueWebUri())){
			params = new BasicNameValuePair[7];
		}else{
			params = new BasicNameValuePair[6];
		}
		params[0] = new BasicNameValuePair("pageNos", pageNos);
		params[1] = new BasicNameValuePair("ent_id", ent_id);
		params[2] = new BasicNameValuePair("pageNo", pageNo);
		params[3] = new BasicNameValuePair("pagescount", pagescount);
		params[4] = new BasicNameValuePair("pageSize", pageSize);
		params[5] = new BasicNameValuePair("clear", clear);
		if("/gjjbj/gjjQueryCreditAction!tzrFrame.dhtml".equals(getUniqueWebUri())){
			params[6] = new BasicNameValuePair("fqr", fqr);
		}
		return params;
	}
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		Html tableHtmltmp = new Html(requestString);
		String onclick = tableHtmltmp.xpath("//a/@onclick").regex("(viewInfo\\s*\\(')(.+)(\\s*'\\s*\\))",2).get();
		if(onclick!=null&&!"".equals(onclick)){
			try {
				String url = "/gjjbj/gjjQueryCreditAction!touzirenInfo.dhtml?chr_id="+URLEncoder.encode(onclick, "utf-8")+"&entName="+URLEncoder.encode(entName, "utf-8")+"&timeStamp="+System.currentTimeMillis()+"&fqr=";
				Request request = builderRequest(builderURL(url+"&"+urlTail(),site), Method.GET, regNumber, entName, null);
				request.putExtra(ProcessHandle.uuid_key, uuid);	
				page.addTargetRequest(request);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}
