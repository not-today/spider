package com.heetian.spider.process.hainan;

import java.util.Iterator;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.HaiNanProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends HaiNanProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/CheckEntContext/showInfo.html");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		List<String> uls = page.getHtml().xpath("//div[@class='da']/div[@class='center-1']/div[@class='list']/ul").all();
		if(uls==null||uls.size()<=0){
			return;
		}
		Iterator<String> iter = uls.iterator();
		while(iter.hasNext()){
			Html ulhtml = new Html(iter.next());
			String url = ulhtml.xpath("//li[1]/a/@href").get();
			String entName = ulhtml.xpath("//li[1]/a/allText()").replace("\\s", "").get();
			String regNumber = ulhtml.xpath("//li[2]/span[1]/text()").replace("\\s", "").get();
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				iter.remove();
				continue;
			}
			Request request = builderRequest(builderURL(url,task.getSite()),Method.GET, regNumber,entName, null);
			if(url.contains("/web/GSZJGSPT/QyxyDetail.aspx"))
				request.putExtra(Request.private_charset, "gb2312");
			page.addTargetRequest(request);
		}
		((TSTPageProcessor)task).setSeedSdP(uls.size());
	}
}
