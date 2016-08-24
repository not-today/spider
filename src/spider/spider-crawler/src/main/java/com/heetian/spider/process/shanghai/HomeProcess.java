package com.heetian.spider.process.shanghai;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ShangHaiProcessHandlePrepare;
/**
 * 长城汽车股份有限公司
 * @author tst
 *
 */
public class HomeProcess extends ShangHaiProcessHandlePrepare{
	private static final String url_companys = "https://www.sgs.gov.cn/notice/search/ent_info_list";
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("https://www.sgs.gov.cn/notice/home");
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
		Request req= builderRequest(url_companys+"?"+urlTail(), Method.POST, null, null, nvps);
		page.addTargetRequest(req);
	}
}
