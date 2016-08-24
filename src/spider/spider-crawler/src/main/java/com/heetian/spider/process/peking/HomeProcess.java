package com.heetian.spider.process.peking;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
/**
 * 北京霞光置业有限公司
 * @author tst
 *
 */
public class HomeProcess extends PekingProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setProcessName(processName_home);
		setUniqueWebUri("http://qyxy.baic.gov.cn/beijing");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		Selectable form = page.getHtml().css("form");
		String currentTimeMillis = form.xpath("//form/input[@name='currentTimeMillis']/@value").get();
		String credit_ticket = form.xpath("//form/input[@name='credit_ticket']/@value").get();
		NameValuePair[] nvps = initRequestParams(currentTimeMillis, credit_ticket,((TSTPageProcessor)task).getSeedNm());
		//String url = builderURL("/CheckCodeCaptcha?currentTimeMillis="+ currentTimeMillis,task.getSite());
		String url = builderURL("/CheckCodeYunSuan?currentTimeMillis="+currentTimeMillis+"&num=14927",task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, nvps));
	}

	private NameValuePair[] initRequestParams(String currentTimeMillis, String credit_ticket,String seedName) {
		NameValuePair[] nvps = new NameValuePair[4]; 
		nvps[0] = new BasicNameValuePair("currentTimeMillis", currentTimeMillis); 
		nvps[2] = new BasicNameValuePair("credit_ticket", credit_ticket); 
		nvps[1]= new BasicNameValuePair("keyword", seedName);
		return nvps;
	}
}
