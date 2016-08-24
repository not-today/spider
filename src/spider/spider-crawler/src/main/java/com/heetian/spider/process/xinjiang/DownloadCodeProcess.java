package com.heetian.spider.process.xinjiang;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
import com.heetian.spider.process.abstractclass.XinJiangProcessHandlePrepare;
/**
 * 解析验证码  CheckCodeCaptcha
 * @author tst
 *
 */
public class DownloadCodeProcess extends XinJiangProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/ztxy.do?method=createYzm");
		setProcessName(processName_down);
	}
	
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/ztxy.do?method=createYzm&dt="+System.currentTimeMillis()+"&random="+System.currentTimeMillis() ,site);
		return builderRequest(url,Method.GET, null,null, null);
	}

	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		String url = builderURL("/ztxy.do?method=list&djjg=&random="+System.currentTimeMillis(),task.getSite());
		NameValuePair[] nvps = {
				new BasicNameValuePair("currentPageNo","1"),
				new BasicNameValuePair("yzm",results[0]),
				new BasicNameValuePair("maent.entname",((TSTPageProcessor)task).getSeedNm()),
				new BasicNameValuePair("cxym","cxlist")
		};
		Request request = builderRequest(url,Method.POST, null,null, nvps);
		page.addTargetRequest(request);
	}
}
