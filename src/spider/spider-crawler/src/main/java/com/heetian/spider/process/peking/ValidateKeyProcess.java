package com.heetian.spider.process.peking;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
/**
 * 校验关键字  gjjbj/gjjQueryCreditAction!findLiteralWord.dhtml
 * @author tst
 *
 */
public class ValidateKeyProcess extends PekingProcessHandlePrepare{
	public ValidateKeyProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!findLiteralWord.dhtml");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String result = page.getRawText();
		if(result!=null&&"success".equals(result.trim().replace(" ", ""))){
			task.getSite().getHeaders().remove("X-Requested-With");
			NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra("nameValuePair");
			String url = builderURL("/gjjbj/gjjQueryCreditAction!getBjQyList.dhtml?"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url, Method.POST, null,null, nvps));
			return;
		}
		task.getSite().getHeaders().remove("X-Requested-With");
	}
}
