package com.heetian.spider.process.guangdong;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.GuangDongProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
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
public class ContexDJ1Process extends GuangDongProcessHandlePrepare {
	public ContexDJ1Process(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/web/GSZJGSPT/QyxyDetail.aspx");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		//System.out.println(page.getRawText());
		task.getSite().addHeader("Referer", page.getRequest().getUrl().split("\\&")[0]);
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		if(Method.GET.equals(page.getRequest().getMethod())){
			List<String> jbTrs = page.getHtml().xpath("//div[@id='jibenxinxi']/table[1]//tr").all();
			GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName, jbTrs,tst);
			if(jbxxBean==null)
				return ;
			String jyfw = page.getHtml().xpath("//span[@id='RegInfo_SSDJCBuItem_labCBuItem']/allText()").get();
			jbxxBean.setRgs(jyfw);
			jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
			tst.setDataJB(jbxxBean,regNumber);
			//股东信息
			List<String> gdTrs = page.getHtml().xpath("//div[@id='jibenxinxi']/table[@id='Table1']//tr").all();
			if(gdTrs!=null&&gdTrs.size()>2){
				gdTrs.remove(0);
				gdTrs.remove(0);
				page.getRequest().putExtra("char_set_gdxq_guangoong", "gb2312");
				tst.setDataGD(AnalysisForTable.gdxxHTMLToJObj(this,gdTrs, regNumber, entName, task.getSite(), page),regNumber);
			}
			//变更
			List<String> bgTrs = page.getHtml().xpath("//div[@id='alterPanel']//table//tr").all();
			if(bgTrs!=null&&bgTrs.size()>2){
				bgTrs.remove(0);
				bgTrs.remove(0);
				tst.setDataBG(AnalysisForTable.bgxxHTMLProcessToJAVAObject(bgTrs, regNumber, page),regNumber);
			}else{
				List<String> inputs = page.getHtml().xpath("//form[@id='form1']//input").all();
				NameValuePair[] nvps = new BasicNameValuePair[inputs.size()+2];
				for(int x=0;x<inputs.size();x++){
					String input = inputs.get(x);
					String name = new Html(input).xpath("//input/@name").get().trim();
					String value = new Html(input).xpath("//input/@value").get().trim();
					if(name.equals("__EVENTTARGET")){
						nvps[x] = new BasicNameValuePair(name, "Timer2");
					}else{
						nvps[x] = new BasicNameValuePair(name, value);
					}
				}
				//http://www.szcredit.com.cn/web/GSZJGSPT/QyxyDetail.aspx?rid=9ee9505ab9eb4f2cbdbe2fc7698d48a5
				//这种变更url会出错。未解决
				nvps[inputs.size()] = new BasicNameValuePair("ScriptManager1", "biangengxinxi|Timer2");
				nvps[inputs.size()+1] = new BasicNameValuePair("__ASYNCPOST", "true");
				Request request = builderRequest(page.getRequest().getUrl(), Method.POST, regNumber, entName, nvps);
				task.getSite().addHeader("Referer", page.getRequest().getUrl());
				task.getSite().addHeader("X-Requested-With", "XMLHttpRequest");
				task.getSite().addHeader("X-MicrosoftAjax", "Delta=true");
				request.putExtra(Request.private_charset, "gb2312");
				page.addTargetRequest(request);
			}
			//主要人员
			List<String> zyryTrs = page.getHtml().xpath("//div[@id='beian']/div[@id='beianPanel']/table[@id='t30']//tr").all();
			if(zyryTrs!=null&&zyryTrs.size()>2){
				zyryTrs.remove(0);
				zyryTrs.remove(0);
				tst.setDataZYRY(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(zyryTrs, regNumber, page),regNumber);
			}
			//分支机构
			List<String> fzjgTrs = page.getHtml().xpath("//div[@id='beian']/div[@id='beianPanel']/table[@id='t31']//tr").all();
			if(fzjgTrs!=null&&fzjgTrs.size()>2){
				fzjgTrs.remove(0);
				fzjgTrs.remove(0);
				tst.setDataFZJG(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(fzjgTrs, regNumber, page),regNumber);
			}
		}else{
			List<String> bgTrs = page.getHtml().xpath("//div[@id='alterPanel']//table//tr").all();
			if(bgTrs!=null&&bgTrs.size()>2){
				bgTrs.remove(0);
				bgTrs.remove(0);
				tst.setDataBG(AnalysisForTable.bgxxHTMLProcessToJAVAObject(bgTrs, regNumber, page),regNumber);
			}
		}
	}
}

