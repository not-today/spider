package com.heetian.spider.process.neimenggu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
import com.heetian.spider.process.abstractclass.NeiMengGuProcessHandlePrepare;
/**
 * 解析验证码  CheckCodeCaptcha
 * @author tst
 *
 */
public class DownloadCodeProcess extends NeiMengGuProcessHandlePrepare implements KeyPoint_CODE{ 
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/aiccips/verify.html");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		String url = builderURL("/aiccips/CheckEntContext/checkCode.html?"+urlTail(),task.getSite());
		NameValuePair[] nvps = {
				new BasicNameValuePair("code", results[0]),
				new BasicNameValuePair("textfield", ((TSTPageProcessor)task).getSeedNm())
		};
		Request request = builderRequest(url,Method.POST, null,null, nvps);
		request.putExtra(RecognizedContext.saveName, results);
		page.addTargetRequest(request);
		return;
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/aiccips/verify.html?random="+Math.random()+"&"+urlTail(),site);
		return builderRequest(url,Method.GET, null,null, null);
	}
}
