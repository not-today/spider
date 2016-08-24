package com.heetian.spider.process.ningxia;

import java.util.Iterator;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.NingXiaProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends NingXiaProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/ECPS/qyxxgsAction_queryXyxx.action");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		List<String> divs = page.getHtml().xpath("//dl[@id='qyList']/div").all();
		if(divs==null||divs.size()<=0){
			return;
		}
		Iterator<String> iter = divs.iterator();
		while(iter.hasNext()){
			Html html = new Html(iter.next());
			String url = html.xpath("//dt/a/@href").get();
			String entName = html.xpath("//dt/a/allText()").replace("\\s", "").get();
			String regNumber = html.xpath("//dd/span[1]/allText()").replace("\\s", "").get();
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				iter.remove();
				continue;
			}
			//?qylx=9999&nbxh=VPPc4T8%2FFt3RzCwTeulH1VOt2XL0J1uO8u%2BeiXza%2FdI%3D&qylxFlag=2&zch=640103600452359
			url = builderURL(url,task.getSite());
			Request request = builderRequest(url,Method.GET, regNumber,entName, null);
			if(url.contains("?")){
				String tail = url.split("\\?")[1];
				request.putExtra("tail", tail);
			}
			page.addTargetRequest(request);
		}
		((TSTPageProcessor)task).setSeedSdP(divs.size());
		return;
	}
}
