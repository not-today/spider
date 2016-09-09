package com.heetian.spider.process.xinjiang;

import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.heetian.spider.process.abstractclass.XinJiangProcessHandlePrepare;
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
public class CompanyURLProcess extends XinJiangProcessHandlePrepare{
	private static final String uhvk1 = "/ztxy.do?method=qyInfo&djjg=&maent.pripid=";
	private static final String uhvk2 = "/ztxy.do?method=baInfo&maent.pripid=";
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/ztxy.do?method=list");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		task.getSite().addCookie("Referer", page.getRequest().getUrl());
		//先判断提交验证码问题
		String flag = page.getHtml().regex("(var\\s*flag\\s*=\\s*')(.+)('\\s*;)",2).replace("\\s", "").get();
		if("fail".equals(flag)||"outtime".equals(flag)){
			logger.warn("验证码错误或者失效,已经重新下载验证码:"+flag);
			String url = builderURL("/ztxy.do?method=createYzm&dt="+System.currentTimeMillis()+"&random="+System.currentTimeMillis() ,task.getSite());
			page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));;
			return;
		}
		//处理企业url
		List<String> uls = page.getHtml().xpath("//form/div[@class='center-1']/div[@class!='se-yichang']/ul").all();
		if(uls==null||uls.size()<=0){
			return;
		}
		Iterator<String> iter = uls.iterator();
		while(iter.hasNext()){
			String ul = iter.next();
			if(ul==null||ul.contains("已吊销")){
				iter.remove();
				continue;
			}
			Html html = new Html(ul);
			String regNumberCom[] = html.xpath("//ul/li[1]/a/@onclick").replace("[\\s']", "").regex("(openView\\()(.+,.+,.+)(\\))",2).get().split(",");
			if(regNumberCom==null||regNumberCom.length<2)
				continue;
			if(regNumberCom==null||"D".equals(regNumberCom[2])){//新疆独有
				iter.remove();
				continue;
			}
			String regNumber = html.xpath("//ul/li[2]//span[1]/allText()").replace("\\s", "").get();
			String entName = html.xpath("//ul/li[1]/a/allText()").replace("\\s", "").get();
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				iter.remove();
				continue;
			}
			long time = System.currentTimeMillis();
			String urlJB = uhvk1+regNumberCom[0]+"&maent.entbigtype="+regNumberCom[1]+"&random="+time;
			NameValuePair[] nvpsJB = {
					new BasicNameValuePair("method", "qyInfo"),
					new BasicNameValuePair("djjg", ""),
					new BasicNameValuePair("maent.pripid", regNumberCom[0]),
					new BasicNameValuePair("maent.entbigtype", regNumberCom[1]),
					new BasicNameValuePair("random", String.valueOf(time))
			};
			Request requestJB = builderRequest(builderURL(urlJB,task.getSite()),Method.POST, regNumber,entName, nvpsJB);
			page.addTargetRequest(requestJB);
			String urlBA =uhvk2+regNumberCom[0]+"&czmk=czmk2&random="+ time;
			NameValuePair[] nvpsBA = {
					new BasicNameValuePair("method", "baInfo"),
					new BasicNameValuePair("maent.pripid", regNumberCom[0]),
					new BasicNameValuePair("czmk", "czmk2"),
					new BasicNameValuePair("random", String.valueOf(time))
			};
			Request requestBA = builderRequest(builderURL(urlBA,task.getSite()),Method.POST, regNumber,entName, nvpsBA);
			page.addTargetRequest(requestBA);
		}
	}
}
