package com.heetian.spider.process.jiangsu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
/**
 * 
 * @author tst
 */
public class ValidateKeyProcess extends JiangSuProcessHandlePrepare{
	public ValidateKeyProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/province/queryResultList.jsp");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		//System.out.println(page.getRawText());
		page.addTargetRequest(rDate(1, task));
		page.addTargetRequest(rDate(4, task));
		NameValuePair[] tmp = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		NameValuePair[] nvps = {
				new BasicNameValuePair("verifyCode", tmp[0].getValue()),
				new BasicNameValuePair("name", tmp[1].getValue())
		};
		Request request = builderRequest(builderURL("/province/infoQueryServlet.json?queryCinfo=true", task.getSite()), Method.POST, null, null, nvps);
		page.addTargetRequest(request);
	}
}
