package com.heetian.spider.process.guangdong;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.GuangDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * 
 * @author tst
 *
 */
public class ValidateCodeProcess extends GuangDongProcessHandlePrepare {
	public ValidateCodeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/CheckEntContext/checkCode.html");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String json = page.getRawText();
		String resultsImage[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		CodeBean bean = AnalysisForJson.jsonToObject(json, new TypeToken<CodeBean>() {});
		if(bean==null||!"1".equals(bean.getFlag())){
			logger.warn("验证码校验结果异常:"+bean.getTip());
			RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			String url = builderURL("http://gsxt.gdgs.gov.cn/aiccips/verify.html?random="+Math.random()+"&"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
			return;
		}
		RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
		NameValuePair[] nvps = {
				new BasicNameValuePair("code",resultsImage[0]),
				new BasicNameValuePair("textfield",bean.getTextfield()),
		};
		String url = builderURL("/aiccips/CheckEntContext/showInfo.html?"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.POST, null,null, nvps));
		return;
	}
}
