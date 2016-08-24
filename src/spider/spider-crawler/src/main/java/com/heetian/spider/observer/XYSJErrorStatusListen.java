package com.heetian.spider.observer;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ProcessHandlePrepare;

public class XYSJErrorStatusListen extends AbstractListen {
	
	@Override
	protected void errorStatus300(Page page, TSTPageProcessor task, ProcessHandlePrepare handle, String value) {
		String url = builderURL("http://www.11315.com/index/"+((TSTPageProcessor)task).getSeedNm()+"?"+System.currentTimeMillis(),task.getSite());
		logger.info("302错误,已经跳转到此连接:"+url);
		Request request = new Request();
		request.setUrl(url);
		request.setMethod(Method.GET);
		page.addTargetRequest(request);
	}
}
