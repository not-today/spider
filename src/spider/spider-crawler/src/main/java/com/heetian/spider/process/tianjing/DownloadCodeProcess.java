package com.heetian.spider.process.tianjing;

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
import com.heetian.spider.process.abstractclass.TianJingProcessHandlePrepare;
/**
 * @author tst
 */
public class DownloadCodeProcess extends TianJingProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/verifycode");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		String url = builderURL("/platform/saic/search.ftl?"+urlTail(),task.getSite());
		NameValuePair[] nvps = new NameValuePair[2]; 
		nvps[0] = new BasicNameValuePair("searchContent", ((TSTPageProcessor) task).getSeedNm()); 
		nvps[1]= new BasicNameValuePair("vcode", results[0]);
		Request request = builderRequest(url,Method.POST, null,null, nvps);
		request.putExtra(RecognizedContext.saveName, results);
		page.addTargetRequest(request);
		return;
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/verifycode?date="+System.currentTimeMillis()+"&"+urlTail(),site);
		return builderRequest(url,Method.GET, null,null, null);
	}
}
