package com.heetian.spider.process.abstractclass;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;



public abstract class NeiMengGuProcessHandlePrepare extends ProcessHandlePrepare {
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		if(requestString.matches(".+该公司的股东及出资信息在\\d{4}年\\d{1,2}月\\d{1,2}日后发生变化的,股东详情企业自主公示.+"))
			return;
		String url = new Html(requestString).xpath("//a/@onclick").regex("(window\\.open\\(\\s*')(.+)(\\s*'\\))",2).replace("\\s", "").get();
		if(url==null||"".equals(url))
			url = new Html(requestString).xpath("//a/@href").replace("\\s", "").get();
		if(url!=null&&!"".equals(url)){
			Request request = builderRequest(builderURL(url,site), Method.GET, regNumber, entName, null);
			String character = (String) page.getRequest().getExtra("char_set_gdxq_guangoong");
			if(character!=null)
				request.putExtra(Request.private_charset, character);
			request.putExtra(ProcessHandle.uuid_key, uuid);
			page.addTargetRequest(request);
		}
	}
}
