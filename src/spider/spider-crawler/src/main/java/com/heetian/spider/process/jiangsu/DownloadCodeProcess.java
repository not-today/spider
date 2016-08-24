package com.heetian.spider.process.jiangsu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends JiangSuProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/province/rand_img.jsp");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		page.addTargetRequest(rDate(4,task));
		String url = builderURL("/province/infoQueryServlet.json?checkCode=true&"+urlTail(), task.getSite());
		NameValuePair[] nvps = {new BasicNameValuePair("verifyCode", results[0])};
		Request request = builderRequest(url,Method.POST, null,null, nvps);
		request.putExtra("verifyCode", results[0]);
		request.putExtra(RecognizedContext.saveName, results);
		page.addTargetRequest(request);
		return;
		//page.addTargetRequest(rDate(3, task));
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/province/rand_img.jsp?type=7&temp="+System.currentTimeMillis(), site);
		return builderRequest(url, Method.GET, null, null, null);
	}
}
