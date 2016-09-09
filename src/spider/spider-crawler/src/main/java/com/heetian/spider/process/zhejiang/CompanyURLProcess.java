package com.heetian.spider.process.zhejiang;

import java.util.List;

import com.heetian.spider.process.abstractclass.ZheJiangProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends ZheJiangProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/search/doGetAppSearchResult.do");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		//System.out.println(page.getRawText());
		Selectable selectableA = page.getHtml().xpath("//div[@class='da']/div[@class='center-1']/dl[@class='list']");
		List<String> urls = selectableA.xpath("//dt/a/@href").all();
		if(urls==null||urls.size()<=0){
			return;
		}
		List<String> regNumbers = selectableA.xpath("//dt/span[1]/text()").replace("\\s", "").all();
		List<String> entNames = selectableA.xpath("//dt/a/@entname").replace("\\s", "").all();
		for(int i=0;i<urls.size();i++){
			String regNumber = regNumbers.get(i).trim();
			String entName = entNames.get(i).trim();
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				continue;
			}
			String url = builderURL(urls.get(i),task.getSite());
			String url_dj = url.replace("/appbasicinfo/doViewAppBasicInfoByLog.do", "/appbasicinfo/doReadAppBasicInfo.do");
			Request request_dj = builderRequest(url_dj,Method.GET, regNumber,entName, null);
			request_dj.putExtra(keyData, "all");
			page.addTargetRequest(request_dj);
			String url_ba = url.replace("/appbasicinfo/doViewAppBasicInfoByLog.do", "/filinginfo/doViewFilingInfo.do");
			Request request_ba = builderRequest(url_ba,Method.GET, regNumber,entName, null);
			request_ba.putExtra(keyData, "all");
			page.addTargetRequest(request_ba);
		}
	}
}
