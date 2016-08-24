package com.heetian.spider.process.tianjing;

import java.util.Iterator;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.TianJingProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends TianJingProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/platform/saic/search.ftl");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String msg = page.getHtml().regex("(\\s*var\\s*msg\\s*=')(.+)('\\s*;\\s*if.+)",2).replace("\\s", "").get();
		if(msg!=null&&msg.contains("验证码错误")){
			String url = builderURL("/verifycode?date="+System.currentTimeMillis()+"&"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
		}
		List<String> divs =  page.getHtml().xpath("//div[@class='center']/div[@class='content']/div[@class='result-item']").all();
		if(divs!=null&&divs.size()>0){
			Iterator<String> iter = divs.iterator();
			while(iter.hasNext()){
				Html html = new Html(iter.next());
				String url = html.xpath("//div[@class='result-item']/div[1]/a/@href").get();
				String regNumber = html.xpath("//div[@class='result-item']/div[2]/span[2]/allText()").replace("\\s", "").get();
				String entName = html.xpath("//div[@class='result-item']/div[1]/a/span/allText()").regex("(.+)(（.+）)",1).replace("\\s", "").get();
				if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
					iter.remove();
					continue;
				}
				Request request = builderRequest(builderURL(url,task.getSite()),Method.GET, regNumber,entName, null);
				request.putExtra("entId", url.split("entId=")[1]);
				page.addTargetRequest(request);
			}
			((TSTPageProcessor)task).setSeedSdP(divs.size());
			return;
		}
	}
}
