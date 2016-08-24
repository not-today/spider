package com.heetian.spider.process.shandong;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ShanDongProcessHandlePrepare;
/**
 * 370200018045121 ,371083018012964 
 * @author tst
 *
 */
public class HomeProcess extends ShanDongProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://218.57.139.24/?xxx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String _csrf = page.getHtml().xpath("//form[@id='searchform']/input[@name='_csrf']/@value").get();
		NameValuePair[] nvps = new BasicNameValuePair[3];
		nvps[0] = new BasicNameValuePair("kw", ((TSTPageProcessor)task).getSeedNm());
		nvps[1] = new BasicNameValuePair("_csrf", _csrf);
		task.getSite().addHeader("X-CSRF-TOKEN",_csrf);
		String url = builderURL("/securitycode?"+urlTail(),task.getSite());
		Request request = builderRequest(url,Method.GET, null,null, nvps);
		request.putExtra(dwNum, "1");
		page.addTargetRequest(request);
	}
}
