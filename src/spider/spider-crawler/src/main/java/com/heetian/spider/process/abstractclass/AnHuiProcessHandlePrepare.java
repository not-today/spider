package com.heetian.spider.process.abstractclass;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;



public abstract class AnHuiProcessHandlePrepare extends ProcessHandlePrepare {
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		//详情解析
		String url = new Html(requestString).xpath("//a/@onclick").regex("(window\\.open\\(\\s*')(/queryInvDetailAction.jspx\\?id=\\d*)(\\s*'\\))",2).get();
		if(url!=null&&!"".equals(url)){
			Request request = builderRequest(builderURL(url,site), Method.GET, regNumber, entName, null);
			request.putExtra(ProcessHandle.uuid_key, uuid);			
			page.addTargetRequest(request);
		}
	}
}
