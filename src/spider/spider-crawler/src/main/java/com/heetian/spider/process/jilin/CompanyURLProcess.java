package com.heetian.spider.process.jilin;

import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;

import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.JiLinProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends JiLinProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/aiccips/pub/indsearch");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcessJiLin(Page page,PageProcessor task) {
		String resultsImage[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		List<String> uls = page.getHtml().xpath("//div/div[@class='list']/ul").all();
		if(uls!=null&&uls.size()>0){
			RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			Iterator<String> iter = uls.iterator();
			while(iter.hasNext()){
				Html html = new Html(iter.next());
				String regNumber = html.xpath("//li[2]/span[1]/text()").replace("\\s","").get();
				String entName = html.xpath("//li[1]/a/text()").replace("\\s","").get();
				String url = html.xpath("//li[1]/a/@href").get();
				if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
					iter.remove();
					continue;
				}
				String ees[] = url.split("/");
				String  enttype=ees[ees.length-2];
				String encrpripid = ees[ees.length-1];
				Request request = builderRequest(builderURL("/aiccips/pub/gsgsdetail/"+enttype+"/"+encrpripid,task.getSite()),Method.GET, regNumber,entName, (NameValuePair[])page.getRequest().getExtra("nameValuePair"));
				request.putExtra("enttype", enttype);
				request.putExtra("encrpripid", encrpripid);
				page.addTargetRequest(request);
			}
			return;
		}
		if(page.getHtml().xpath("//div/div[@class='list-a']/text()").get()!=null){
			RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
			return;
		}
		RecognizedContext.newInstance().updateCurrentError(IsSucess.FAIL, Integer.parseInt(resultsImage[1]), Long.parseLong(resultsImage[2]));
		Request request = builderRequest(builderURL("/aiccips/securitycode?"+urlTail(),task.getSite()),Method.GET, null,null, (NameValuePair[]) page.getRequest().getExtra("nameValuePair"));
		request.putExtra(dwNum, page.getRequest().getExtra(dwNum));
		page.addTargetRequest(request);
	}
}
