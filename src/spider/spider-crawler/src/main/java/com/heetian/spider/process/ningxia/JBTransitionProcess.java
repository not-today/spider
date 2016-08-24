package com.heetian.spider.process.ningxia;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.NingXiaProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class JBTransitionProcess extends NingXiaProcessHandlePrepare {
	public JBTransitionProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ECPS/qyxxgsAction_initQyxyxxMain.action");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		if(page.getRawText().contains("抱歉，无数据！请检查查询条件！")){
			((TSTPageProcessor) task).setDataStatusForFail(regNumber);
			return;
		}
		List<String> djs = page.getHtml().xpath("//div[@id='jibenxinxi']//iframe/@src").all();
		if(djs==null||djs.size()<=0)
			return;
		String jbURL = null;
		String gdURL = null;
		String bgURL = null;
		for(String tmp:djs){
			if(tmp.contains("qyxxgsAction_initQyjbqk")||tmp.contains("qyxxgsAction_initGtjbqk.action")){
				jbURL =  tmp;
			}else if(tmp.contains("tzrczxxAction_init.action")){
				gdURL =  tmp;
			}else if(tmp.contains("qybgxxAction_init.action")){
				bgURL =  tmp;
			}else{
				logger.error("other:"+tmp+":"+page.getRequest().getUrl());
			}
		}
		Request jbRequest = addRequest(jbURL+"&"+urlTail()+Math.random(), task.getSite(), regNumber, entName);
		if(jbRequest==null)
			return;
		jbRequest.putExtra("currentURL_jiangxi", page.getRequest().getUrl());
		page.addTargetRequest(jbRequest);
		
		Request gdRequest = addRequest(gdURL, task.getSite(), regNumber, entName);
		if(gdRequest!=null){
			gdRequest.putExtra(Request.private_charset, CHARSET_GBK);
			page.addTargetRequest(gdRequest);
		}
		Request bgRequest = addRequest(bgURL, task.getSite(), regNumber, entName);
		if(bgRequest!=null){
			bgRequest.putExtra(Request.private_charset, CHARSET_GBK);				
			page.addTargetRequest(bgRequest);
		}
		List<String> bas = page.getHtml().xpath("//div[@id='beian']//iframe/@src").all();
		if(bas==null||bas.size()<=0)
			return;
		String zyryURL = null;
		String fzjgURL = null;
		//String zgbmxx = null;
		for(String tmp:bas){
			if(tmp.contains("qybaxxAction_zyryxx.action")){
				zyryURL =  tmp;
			}else if(tmp.contains("qybaxxAction_fgsxx.action")){
				fzjgURL =  tmp;
			}else if(tmp.contains("tzrczxxAction_init.action")){
				//zgbmxx = ba;//主管部门（出资人）信息,未实现
			}else{
				if(!tmp.contains("qybaxxAction_qsxx.action"))
					logger.error("other:"+tmp+":"+page.getRequest().getUrl());
			}
		}
		Request zyryRequest = addRequest(zyryURL, task.getSite(), regNumber, entName);
		if(zyryRequest!=null){
			zyryRequest.putExtra(Request.private_charset, CHARSET_GBK);
			page.addTargetRequest(zyryRequest);
		}
		
		Request fzjgRequest = addRequest(fzjgURL, task.getSite(), regNumber, entName);
		if(fzjgRequest!=null){
			fzjgRequest.putExtra(Request.private_charset, CHARSET_UTF8);
			page.addTargetRequest(fzjgRequest);
		}
	}
	private Request addRequest(String url,Site site,String regNumber,String entName){
		if(url==null||"".equals(url.trim()))
			return null;
		return builderRequest(builderURL("/ECPS/"+url,site),Method.GET, regNumber,entName, null);
	}
}

