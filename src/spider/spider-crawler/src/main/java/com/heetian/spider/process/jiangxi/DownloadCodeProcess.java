package com.heetian.spider.process.jiangxi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends JiangXiProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/warningetp/reqyzm.do");
		setProcessName(processName_down);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		NameValuePair[] nvps = {
				new BasicNameValuePair("yzm", results[0]),
				new BasicNameValuePair("searchtext", ((TSTPageProcessor)task).getSeedNm()),
		};
		String url = "/warningetp/yzm.do?inputvalue="+results[0]+"&"+urlTail();
		Request request = builderRequestPost(builderURL(url, task.getSite()), nvps);
		request.putExtra(RecognizedContext.saveName, results);
		page.addTargetRequest(request);
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		return builderRequestGet(builderURL("/warningetp/reqyzm.do?r="+System.currentTimeMillis(),site));
	}
}
