package com.heetian.spider.process.jiangsu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * 
 * @author tst
 *
 */
public class ValidateCodeProcess extends JiangSuProcessHandlePrepare {
	public ValidateCodeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/province/infoQueryServlet.json?checkCode=true");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String resultsImage[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		String result = page.getRawText();
		if(result!=null&&!"".equals(result)){
			CodeBean codeBean = AnalysisForJson.jsonToObject(result, new TypeToken<CodeBean>(){});
			if(codeBean!=null&&codeBean.isSuccess()){
				RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
				String seedName = ((TSTPageProcessor)task).getSeedNm();
				//验证码成功
				NameValuePair[] nvps = {
						//new BasicNameValuePair("loginAgain", "loginAgain"),
						//new BasicNameValuePair("typeHidden", "typeHidden"),
						new BasicNameValuePair("verifyCode", (String)page.getRequest().getExtra("verifyCode")),
						new BasicNameValuePair("typeName", seedName),
						//new BasicNameValuePair("staff_code2", ""),
						//new BasicNameValuePair("password2", ""),
					//	new BasicNameValuePair("verifyCode2", "请输入右侧验证码"),
						//new BasicNameValuePair("state", ""),
						//new BasicNameValuePair("admit_main", ""),
						//new BasicNameValuePair("status", ""),
						//new BasicNameValuePair("staff_code3", ""),
						//new BasicNameValuePair("password3", ""),
						//new BasicNameValuePair("verifyCode3", "请输入右侧验证码"),
				};
				page.addTargetRequest(builderRequest(builderURL("/province/queryResultList.jsp", task.getSite()), Method.POST, null, null, nvps));
				return;
			}
		}
		RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
		String url = builderURL("/province/rand_img.jsp?type=7&temp="+System .currentTimeMillis(), task.getSite());
		page.addTargetRequest(builderRequest(url, Method.GET, null, null, null));
		page.addTargetRequest(rDate(3, task));
	}
}
