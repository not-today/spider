package com.heetian.spider.process.chongqing;

import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ChongQingProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends ChongQingProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/search.action");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		List<String> divs = page.getHtml().xpath("//div[@id='result']/div[@class='item']").all();
		if(divs==null||divs.size()<=0){
			return;
		}
		// /a[@class='name']
		Iterator<String> iter = divs.iterator();
		while(iter.hasNext()){
			Html html = new Html(iter.next());
			String regnumber = html.xpath("//div[@class='item']/span[1]/span[@class='value']/allText()").replace("\\s", "").get();
			String entName = html.xpath("//div[@class='item']/a/allText()").replace("\\s", "").get();
			if(!TSTUtils.checkIsExitForEnter(task, regnumber, entName)){
				iter.remove();
				continue;
			}
			String data_id = html.xpath("//div[@class='item']/a/@data-id").get();
			String data_type = html.xpath("//div[@class='item']/a/@data-type").get();
			String data_entId = html.xpath("//div[@class='item']/a/@data-entid").get();
			NameValuePair[] nvps = {
					new BasicNameValuePair("id", data_id),
					new BasicNameValuePair("type", data_type),
					new BasicNameValuePair("name", entName),
					new BasicNameValuePair("entId", data_entId)
			};
			String url = builderURL("/search_ent?"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url,Method.POST, null,null, nvps));
		}
		((TSTPageProcessor)task).setSeedSdP(divs.size());
	}
}
