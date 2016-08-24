package com.heetian.spider.process.abstractclass;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;



public abstract class NingXiaProcessHandlePrepare extends ProcessHandlePrepare {
	protected static final String CHARSET_KEY = "CHARSET_KEY";
	protected static final String CHARSET_UTF8 = "UTF-8";
	protected static final String CHARSET_GBK = "gbk";
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		String onclick = new Html(requestString).xpath("//a/@onclick").regex("(tzrczxx\\s*\\(\\s*')(.+)(\\s*'\\))",2).get();
		if(onclick!=null&&!"".equals(onclick)){
			String tail = page.getRequest().getUrl().split("\\?")[1];
			String url = builderURL("/ECPS/tzrczxxAction_tzrczxxxx.action?xh="+onclick+"&"+tail,site);
			Request request = builderRequest(url, Method.GET, regNumber, entName, null);
			request.putExtra(ProcessHandle.uuid_key, uuid);
			page.addTargetRequest(request);		
		}
	}
	protected void nextPage(String type,Page page, PageProcessor task, String regNumber,String entName, List<String> nexts) {
		if(nexts==null||nexts.size()<=0){
			return;
		}
		String cu_url = page.getRequest().getUrl();
		int cu_num = -1;
		if(cu_url.contains(type)){
			cu_num = 1;
		}else{
			String tmp = obtainURL_PageNum(cu_url, "currPage");
			if(tmp!=null&&tmp.matches("\\d+"))
				cu_num = Integer.parseInt(tmp);
		}
		for(String next :nexts){
			String nextnum = obtainURL_PageNum(next, "currPage");
			int next_num = -1;
			if(nextnum!=null&&nextnum.matches("\\d+"))
				next_num = Integer.parseInt(nextnum);
			if(next_num==(cu_num+1)){
				Request gdRequest = builderRequest(builderURL(next,task.getSite()),Method.GET, regNumber,entName, null);
				gdRequest.putExtra(Request.private_charset, CHARSET_GBK);
				page.addTargetRequest(gdRequest);
			}
		}
	}
	public String obtainURL_PageNum(String url,String paraName){
		if(!url.contains("?"))
			return null;
		String paras[] = url.split("\\?")[1].split("&");
		if(paras!=null){
			for(String para:paras){
				if(para.contains(paraName)){
					String[] tmps = para.split("=");
					if(tmps!=null&&tmps.length>=2){
						return tmps[1].trim();
					}
				}
			}
		}
		return null;
	}
}
