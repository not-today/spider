package com.heetian.spider.process.chongqing;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ChongQingProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends ChongQingProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/sc.action");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		NameValuePair[] nvps = {
				new BasicNameValuePair("code", results[0]),
				new BasicNameValuePair("key",((TSTPageProcessor)task).getSeedNm())
		};
		String url = builderURL("/search.action?"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.POST, null,null, nvps));
		return;
	
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/sc.action?width=130&height=40&fs=23&"+urlTail(),site);
		return builderRequest(url,Method.GET, null,null, null);
	}
}
