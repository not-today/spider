package com.heetian.spider.process.abstractclass;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;




public abstract class ShanXiXiProcessHandlePrepare extends ProcessHandlePrepare {
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		//详情解析  showRyxx('51000000000793072051000000000000015214','5100000000079307')  function showRyxx(ryId,nbxh)
		String onclick = new Html(requestString).xpath("//a/@onclick").regex("(showRyxx\\s*\\()(.+,.+)(\\s*\\))",2).replace("['\\s]", "").get();
		if(onclick!=null&&!"".equals(onclick)){
			String prams[] = onclick.split(",");
			long time = System.currentTimeMillis();
			String url ="/ztxy.do?method=tzrCzxxDetial&maent.xh="+prams[0]+"&maent.pripid="+prams[1]+"&random="+ time;
			NameValuePair[] nvps = {
					new BasicNameValuePair("method", "tzrCzxxDetial"),
					new BasicNameValuePair("maent.xh", prams[0]),
					new BasicNameValuePair("maent.pripid", prams[1]),
					new BasicNameValuePair("random", String.valueOf(time))
			};
			Request request = builderRequest(builderURL(url, site), Method.POST, regNumber, entName, nvps);
			request.putExtra(ProcessHandle.uuid_key, uuid);
			page.addTargetRequest(request);
		}
	}
}
