package com.heetian.spider.process.ningxia;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
import com.heetian.spider.process.abstractclass.NingXiaProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends NingXiaProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/ECPS/verificationCode.jsp");
		setProcessName(processName_down);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		NameValuePair[] nvps = {
				new BasicNameValuePair("password", results[0])
		};
		String url = builderURL("/ECPS/qyxxgsAction_checkVerificationCode.action?"+urlTail(),task.getSite());
		Request request = builderRequest(url,Method.POST, null,null, nvps);
		request.putExtra(RecognizedContext.saveName, results);
		page.addTargetRequest(request);
		return;
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/ECPS/verificationCode.jsp?_=" +System.currentTimeMillis()+"&"+urlTail(),site);
		return builderRequest(url,Method.GET, null,null, null);
	}
}
