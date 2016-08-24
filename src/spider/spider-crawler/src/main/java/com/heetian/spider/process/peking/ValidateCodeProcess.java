package com.heetian.spider.process.peking;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class ValidateCodeProcess extends PekingProcessHandlePrepare {
	public ValidateCodeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!checkCode.dhtml");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String result = page.getRawText();
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra("nameValuePair");
		String resultsImage[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		if(result!=null&&"success".equals(result.trim().replace(" ", ""))){
			RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			String url = builderURL("/gjjbj/gjjQueryCreditAction!findLiteralWord.dhtml?"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url,Method.POST, null,null, nvps));
			return;
		}
		
		RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
		String url = builderURL("/CheckCodeYunSuan?currentTimeMillis="+nvps[0].getValue()+"&num=14927"+ "&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url, Method.GET, null,null, nvps));
		task.getSite().getHeaders().remove("X-Requested-With");
	}
}
