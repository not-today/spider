package com.heetian.spider.process.ningxia;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.NingXiaProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class ValidateCodeProcess extends NingXiaProcessHandlePrepare {
	public ValidateCodeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/ECPS/qyxxgsAction_checkVerificationCode.action");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String result = page.getRawText();
		String results[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		if(result.contains("ok")){
			RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(results[1]), Long.parseLong(results[2]));
			NameValuePair[] nvps = {
					new BasicNameValuePair("password", results[0]),
					new BasicNameValuePair("selectValue", ((TSTPageProcessor)task).getSeedNm())
			};
			String url = builderURL("/ECPS/qyxxgsAction_queryXyxx.action?"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url,Method.POST, null,null, nvps));
			return;
		}
		
		RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(results[1]), Long.parseLong(results[2]));
		String url = builderURL("/ECPS/verificationCode.jsp?_=" +System.currentTimeMillis()+"&"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
