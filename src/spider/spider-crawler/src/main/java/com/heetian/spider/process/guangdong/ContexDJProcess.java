package com.heetian.spider.process.guangdong;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.GuangDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexDJProcess extends GuangDongProcessHandlePrepare {
	public ContexDJProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/GSpublicity/GSpublicityList.html");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		task.getSite().addHeader("Referer", page.getRequest().getUrl());
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		String entNo = page.getHtml().xpath("input[@id='entNo']/@value").get();
		String regOrg = page.getHtml().xpath("input[@id='regOrg']/@value").get();
		String entType = page.getHtml().xpath("input[@id='entType']/@value").get();
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		if(Method.GET.equals(page.getRequest().getMethod())){
			List<String> jbTrs = page.getHtml().xpath("//table[@id='baseinfo']//tr").all();
			GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName, jbTrs,tst);
			if(jbxxBean==null){
				return ;
			}
			String urlTmp = TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null);
			jbxxBean.setUrl(urlTmp);
			tst.setDataJB(jbxxBean,regNumber);
			//股东信息
			String isNext = page.getHtml().xpath("//div[@id='invInfo']/table[2]//tr").get();
			if(match1(isNext)){
				List<String> gdTrs = page.getHtml().xpath("//table[@id='touziren']//tr").all();
				if(gdTrs!=null&&gdTrs.size()>2){
					tst.setDataGD(AnalysisForTable.gdxxHTMLToJObj(this,gdTrs, regNumber, entName, task.getSite(), page),regNumber);
				}
			}else{
				NameValuePair[] nvps = {
					new BasicNameValuePair("pageNo", ""+2),
					new BasicNameValuePair("entNo", entNo),
					new BasicNameValuePair("regOrg", regOrg),
				};
				String url = "http://gsxt.gdgs.gov.cn/aiccips/GSpublicity/invInfoPage.html?"+urlTail()+"&"+Math.random();
				Request request = builderRequest(url, Method.POST, regNumber, entName, nvps);
				request.putExtra("entNo", entNo);
				request.putExtra("regOrg", regOrg);
				page.addTargetRequest(request);
			}
			//变更
			String trbg = page.getHtml().xpath("//div[@id='biangeng']/table[2]//tr").get();
			if(match1(trbg)){
				List<String> bgTrs = page.getHtml().xpath("//div[@id='biangeng']/table[1]//tr").all();
				if(bgTrs!=null&&bgTrs.size()>0){
					tst.setDataBG(AnalysisForTable.bgxxHTMLProcessToJAVAObject(bgTrs, regNumber, page),regNumber);
				}
			}else{
				NameValuePair[] nvps = {
					new BasicNameValuePair("pageNo", ""+2),
					new BasicNameValuePair("entNo", entNo),
					new BasicNameValuePair("regOrg", regOrg),
					new BasicNameValuePair("entType", entType)
				};
				String url = "http://gsxt.gdgs.gov.cn/aiccips/GSpublicity/entChaPage?"+urlTail()+"&"+Math.random();
				Request request = builderRequest(url, Method.POST, regNumber, entName, nvps);
				page.addTargetRequest(request);
			}
			List<String> inputs = page.getHtml().xpath("//form[@id='topForm']//input").all();
			NameValuePair[] nvps = new BasicNameValuePair[inputs.size()];
			for(int x=0;x<inputs.size();x++){
				String input = inputs.get(x);
				String name = new Html(input).xpath("//input/@name").get();
				String value = new Html(input).xpath("//input/@value").get();
				nvps[x] = new BasicNameValuePair(name, value);
				
			}
			String url = "http://gsxt.gdgs.gov.cn/aiccips/GSpublicity/GSpublicityList.html?service=entCheckInfo&"+urlTail();
			Request request = builderRequest(url, Method.POST, regNumber, entName, nvps);
			page.addTargetRequest(request);
		}else{
			//主要人员
			String tr = page.getHtml().xpath("//div[@id='zyry']/table[2]//tr").get();
			if(match1(tr)){
				List<String> zyryTrs = page.getHtml().xpath("//div[@id='zyry']/table[1]//tr").all();
				if(zyryTrs!=null&&zyryTrs.size()>0)
					tst.setDataZYRY(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(zyryTrs, regNumber, page),regNumber);
			}else{
				NameValuePair[] nvps = {
						new BasicNameValuePair("pageNo", ""+2),
						new BasicNameValuePair("entNo", entNo),
						new BasicNameValuePair("regOrg", regOrg),
					};
					String url = "http://gsxt.gdgs.gov.cn/aiccips/GSpublicity/vipInfoPage?"+urlTail()+"&"+Math.random();
					Request request = builderRequest(url, Method.POST, regNumber, entName, nvps);
					page.addTargetRequest(request);
			}
			//分支机构
			String trfzjg = page.getHtml().xpath("//div[@id='branch']/table[2]//tr").get();
			if(match1(trfzjg)){
				List<String> fzjgTrs = page.getHtml().xpath("//div[@id='branch']/table[1]//tr").all();
				if(fzjgTrs!=null&&fzjgTrs.size()>0){
					tst.setDataFZJG(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(fzjgTrs, regNumber, page),regNumber);
				}
			}else{
				NameValuePair[] nvps = {
					new BasicNameValuePair("pageNo", ""+2),
					new BasicNameValuePair("entNo", entNo),
					new BasicNameValuePair("regOrg", regOrg),
				};
				String url = builderURL("/aiccips/GSpublicity/braInfoPage?"+urlTail(), task.getSite());
				Request request = builderRequest(url, Method.POST, regNumber, entName, nvps);
				page.addTargetRequest(request);
			}
		}
	}
	private boolean match1(String isNext) {
		if(isNext==null)
			return true;
		return isNext.replaceAll("\\s", "").matches(".+1/1.+");
	}
}

