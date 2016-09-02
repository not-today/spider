package com.heetian.spider.process.shanghai;

import org.apache.http.NameValuePair;

import com.heetian.spider.component.EnterUrls;
import com.heetian.spider.process.abstractclass.ShangHaiProcessHandlePrepare;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 
 * @author tst
 *
 */
public class ValidateCodeProcess extends ShangHaiProcessHandlePrepare {
	public ValidateCodeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/notice/security/verify_captcha");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String result = page.getRawText();
		logger.debug("验证码提交服务器验证结果"+("1".equals(result)?"******成功******":"失败"));
		if(result==null||!"1".equals(result)){
			Request tmp = builderRequestGet(builderURLHttps("/notice/captcha?preset=&ra=?"+Math.random(),task.getSite()));
			tmp.putExtra(NAMEVALUEPAIR, page.getRequest().getExtra(NAMEVALUEPAIR));
			page.addTargetRequest(tmp);
			return;
		}
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		Request req= builderRequestPost(builderURL(EnterUrls.SHCom+"?"+urlTail(),task.getSite()), nvps);
		page.addTargetRequest(req);
	}
}
