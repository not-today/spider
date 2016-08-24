package com.heetian.spider.process.shandong;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.ShanDongProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends ShanDongProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/pub/indsearch");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String enckeyword = page.getHtml().regex("(var\\s*?enckeyword\\s*?=\\s*?')(.+?)('\\s*?;)",2).get();
		NameValuePair[] nvps = {new BasicNameValuePair("param", enckeyword)};
		Request request = builderRequest(builderURL("/pub/search?"+urlTail(),task.getSite()),Method.POST, null,null,nvps );
		page.addTargetRequest(request);
	}
}
