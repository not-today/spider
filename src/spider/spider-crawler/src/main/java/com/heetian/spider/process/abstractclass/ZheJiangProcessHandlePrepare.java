package com.heetian.spider.process.abstractclass;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;



public abstract class ZheJiangProcessHandlePrepare extends ProcessHandlePrepare {
	protected static final String keyData = "flag_sub";
	protected void nextPage(Page page, String regNumber, String entName, String nextHtml , String regex, String bg,String[] para,String pageNumber) {
		if(nextHtml==null||"".equals(nextHtml.trim()))
			return;
		if(nextHtml.contains("下一页")){
			String nexurl = new Html(nextHtml).xpath("//a[@id='nextPage']/@onclick").regex("(\\s*"+regex+"\\s*\\(\\s*'\\s*)(\\d)(\\s*'\\s*\\)\\s*)",2).get();
			if(nexurl!=null&&nexurl.matches("\\d+")){
				NameValuePair nvps[] = new NameValuePair[]{
						new BasicNameValuePair(para[0], nexurl),
						new BasicNameValuePair(para[1], pageNumber)
				};
				Request request = builderRequest(page.getRequest().getUrl()+"&"+urlTail()+Math.random(), Method.POST, regNumber, entName, nvps);
				request.putExtra(keyData, bg);
				page.addTargetRequest(request);
			}
		}
	}
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		if(requestString==null||"".equals(requestString.trim())){
			return;
		}
		
		String onclick = new Html(requestString).xpath("//td/allText()").get();
		if(onclick!=null&&!"".equals(onclick)){
			logger.error("股东详情信息："+regNumber);
			System.exit(0);
			/*Request request = builderRequest(builderURL(onclick,site), Method.GET, regNumber, entName, null);
			request.putExtra(ProcessHandle.uuid, uuid);
			page.addTargetRequest(request);*/
		}
	}
}
