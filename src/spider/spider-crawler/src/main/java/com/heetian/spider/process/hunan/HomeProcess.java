package com.heetian.spider.process.hunan;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.HunanProcessHandlePrepare;
/**
 * 湖南星电建设实业集团股份有限公司
 * @author tst
 *
 */
public class HomeProcess extends HunanProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.hnaic.gov.cn/notice/?aa=aa");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String sessionID = page.getHtml().xpath("//form//input[@name='session.token']/@value").get();
		NameValuePair[] nvps = {
				new BasicNameValuePair("condition.pageNo", "1"),
				new BasicNameValuePair("captcha","32"),	
				new BasicNameValuePair("condition.insType", ""),
				new BasicNameValuePair("session.token", sessionID),
				new BasicNameValuePair("condition.keyword", ((TSTPageProcessor)task).getSeedNm())
		};
		String url = builderURL("/notice/search/ent_info_list", task.getSite());
		Request req= builderRequest(url, Method.POST, null, null, nvps);
		page.addTargetRequest(req);
	}
}
