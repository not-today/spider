package com.heetian.spider.process.zhejiang;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.ZheJiangProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * 校验验证码   /gjjbj/gjjQueryCreditAction!checkCode.dhtml
 * @author tst
 *
 */
public class ValidateCodeProcess extends ZheJiangProcessHandlePrepare {
	public ValidateCodeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/search/doValidatorVerifyCode.do");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String json = page.getRawText();
		String resultsImage[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		ValidateBean bean = AnalysisForJson.jsonToObject(json, new TypeToken<ValidateBean>() {});
		System.out.println(bean.getNameResponse().getMessage());
		if(bean==null||"验证码输入错误".equals(bean.getNameResponse().getMessage())){
			RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			String url = builderURL("/common/captcha/doReadKaptcha.do?" +3+"&"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
			return;
		}
		RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
		NameValuePair[] nvps = {
				new BasicNameValuePair("verifyCode",resultsImage[0]),
				new BasicNameValuePair("name",((TSTPageProcessor)task).getSeedNm()),
				new BasicNameValuePair("clickType","1"),//莫名奇妙的字段
		};
		String url = builderURL("/search/doGetAppSearchResult.do?"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.POST, null,null, nvps));
		return;
	}
}
