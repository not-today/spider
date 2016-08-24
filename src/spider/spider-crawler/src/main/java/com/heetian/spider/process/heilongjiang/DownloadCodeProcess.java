package com.heetian.spider.process.heilongjiang;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.HeiLongJiangProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends HeiLongJiangProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/validateCode.jspx");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		NameValuePair[] nvps = {
				new BasicNameValuePair("checkNo", results[0].replaceAll("\\s", "")),
				new BasicNameValuePair("entName",((TSTPageProcessor)task).getSeedNm())
		};
		String url = builderURL("/searchList.jspx?"+urlTail(),task.getSite());
		page.addTargetRequest(builderRequest(url,Method.POST, null,null, nvps));
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/validateCode.jspx?type=1&id=" + Math.random()+"&"+urlTail(),site);
		return builderRequest(url,Method.GET, null,null, null);
	}
}
