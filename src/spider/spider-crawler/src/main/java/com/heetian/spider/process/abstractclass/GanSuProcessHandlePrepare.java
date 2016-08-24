package com.heetian.spider.process.abstractclass;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;



public abstract class GanSuProcessHandlePrepare extends ProcessHandlePrepare {
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		String onclick = new Html(requestString).xpath("//a/@onclick").regex("(window\\.open\\s*\\(\\s*')(/gsxygs/pub!getDetails.do\\?.+)(\\s*'\\))",2).get();
		if(onclick!=null&&!"".equals(onclick)){
			Request request = builderRequest(builderURL(onclick,site), Method.GET, regNumber, entName, null);
			request.putExtra(ProcessHandle.uuid_key, uuid);			
			page.addTargetRequest(request);
		}
	}
}
