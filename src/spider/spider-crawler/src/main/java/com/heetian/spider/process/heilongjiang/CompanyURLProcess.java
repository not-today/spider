package com.heetian.spider.process.heilongjiang;

import java.util.Iterator;
import java.util.List;

import com.heetian.spider.process.abstractclass.HeiLongJiangProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends HeiLongJiangProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/searchList.jspx");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String codeResult = page.getHtml().xpath("//div[@class='search']/div[@class='tianbao']/ul/li/font/text()").get();
		if(codeResult!=null){
			if(codeResult.contains("验证码不正确或已失效")){
				String url = builderURL("/validateCode.jspx?type=1&id=" + Math.random()+"&"+urlTail(),task.getSite());
				page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
				return;
			}else if(codeResult.contains("不能以该关键字作为条件进行查询，请重新输入更明确的条件")){
				return;
			}else{
				logger.warn("在对验证码检验的时候出现了其他的情况:"+codeResult);
				return;
			}
		}
		List<String> uls = page.getHtml().xpath("//div/div[@class='list']/ul").all();
		if(uls==null||uls.size()<=0){
			return;
		}
		Iterator<String> iter = uls.iterator();
		while(iter.hasNext()){
			Html html = new Html(iter.next());
			String regNumber = html.xpath("//li[2]/span[1]/text()").replace("\\s", "").get();
			String entName = html.xpath("//li[1]/a/allText()").replace("\\s", "").get();
			String url = builderURL(html.xpath("//li[1]/a/@href").replace("\\s", "").get(),task.getSite());
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				iter.remove();
				continue;
			}
			page.addTargetRequest(builderRequest(url,Method.GET, regNumber,entName, null));
		}
	}
}
