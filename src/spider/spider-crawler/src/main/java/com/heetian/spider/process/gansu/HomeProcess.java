package com.heetian.spider.process.gansu;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.GanSuProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class HomeProcess extends GanSuProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://xygs.gsaic.gov.cn/gsxygs/");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		List<String> inputs = page.getHtml().xpath("//form[@id='infoForm']//input").all();
		String url = builderURL("/gsxygs/securitycode.jpg?v="+System.currentTimeMillis()+"&"+urlTail(),task.getSite());
		Request request = builderRequest(url,Method.GET, null,null, null);
		request.putExtra("inputs", inputs);
		page.addTargetRequest(request);
	}
}
