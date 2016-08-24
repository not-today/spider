package com.heetian.spider.process.peking;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends PekingProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/CheckCodeYunSuan");
		setProcessName(processName_down);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		/*Matcher matcher = Pattern.compile("[^0-9a-zA-Z]*").matcher(results[0]);
		while(matcher.find()){
			results[0] = matcher.replaceAll("");
		}*/
		//if(results[0].matches("[0-9a-zA-Z]{4}")){
			task.getSite().addHeader("X-Requested-With", "XMLHttpRequest");
			nvps[3] = new BasicNameValuePair("checkcode", results[0]); 
			String nextURL = builderURL("/gjjbj/gjjQueryCreditAction!checkCode.dhtml?"+urlTail(),task.getSite());
			Request request = builderRequest(nextURL, Method.POST, null,null, nvps);
			request.putExtra(RecognizedContext.saveName, results);
			page.addTargetRequest(request);
			//return;
		//}
		//page.addTargetRequest(reloadCode(page.getRequest(), task.getSite()));
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		NameValuePair[] nvps = (NameValuePair[]) request.getExtra(NAMEVALUEPAIR);
		String url = builderURL("/CheckCodeYunSuan?currentTimeMillis="+nvps[0].getValue()+"&num=14927"+ "&"+urlTail(),site);
		return builderRequest(url, Method.GET, null,null, nvps);
	}
}
