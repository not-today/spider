package com.heetian.spider.process.liaoning;

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
import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 * 解析验证码  CheckCodeCaptcha
 * @author tst
 *
 */
public class DownloadCodeProcess extends LiaoNingProcessHandlePrepare implements KeyPoint_CODE{ 
	private static final String next = "/saicpub/entPublicitySC/entPublicityDC/lngsSearchFpc.action?";
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/saicpub/commonsSC/loginDC/securityCode.action");
		setProcessName(processName_down);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		NameValuePair[] nvps = new BasicNameValuePair[2];
		nvps[0] = new BasicNameValuePair("authCode", results[0]); 
		nvps[1] = new BasicNameValuePair("solrCondition", ((TSTPageProcessor)task).getSeedNm());
		String url = builderURL(next+urlTail(),task.getSite());
		Request request = builderRequest(url,Method.POST, null,null, nvps);
		request.putExtra(RecognizedContext.saveName, results);
		page.addTargetRequest(request);
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/saicpub/commonsSC/loginDC/securityCode.action?tdate="+TSTUtils.randomNum()+"&"+urlTail(),site);
		return builderRequest(url,Method.GET, null,null, null);
	}
}
