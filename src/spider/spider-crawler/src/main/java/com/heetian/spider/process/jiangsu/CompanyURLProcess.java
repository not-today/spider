package com.heetian.spider.process.jiangsu;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.SeedStatusEnum;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends JiangSuProcessHandlePrepare{
	private static final String nextURLForData = "/ecipplatform/ciServlet.json?ciEnter=true&";
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/province/infoQueryServlet.json?queryCinfo=true");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String context = page.getRawText();
		if(context==null||"".equals(context)){
			return;
		}
		context = context.substring(1,context.length());
		context = context.substring(0,context.length()-1);
		CompanyURLs codeBeans = AnalysisForJson.jsonToObject(context, new TypeToken<CompanyURLs>(){});
		TSTPageProcessor tst = (TSTPageProcessor) task;
		if(codeBeans==null){
			tst.setStatus(SeedStatusEnum.reco);
			logger.error(context);
			return;
		}
		if(codeBeans.getCOUNT().contains("没有符合查询条件的结果")){
			return;
		}
		String tip = codeBeans.getTIPS();
		if(tip!=null&&!"".equals(tip)){
			if(tip.contains("该IP在一天内超过了查询的限定次数")||tip.contains("该IP在一小时内超过了查询的限定次数")|| tip.contains("该IP在一个月内连续5次超过了查询的限定次数")){
				tst.setProxyStatus(ProxyStatus.NO);
				tst.setStatus(SeedStatusEnum.reco);
				logger.error(context);
			}else if(tip.contains("验证码填写错误")){
				String url = builderURL("/province/rand_img.jsp?type=7&temp="+System.currentTimeMillis(), task.getSite());
				page.addTargetRequest(builderRequest(url, Method.GET, null, null, null));
				page.addTargetRequest(rDate(3, task));
			}else{
				tst.setStatus(SeedStatusEnum.reco);
				logger.error("tip不为零的其他原因:"+context);
			}
			return;
		}
		String html = codeBeans.getINFO();
		if(html!=null&&!"".equals(html)){
			Selectable htmlSelectable = new Html(html);
			List<String> as = htmlSelectable.xpath("//dt/a").all();
			if(as==null||as.size()<=0){
				return;
			}
			Iterator<String> iter = as.iterator();
			while(iter.hasNext()){
				Html htmla = new Html(iter.next());
				String entName = htmla.xpath("//a/allText()").replace("\\s", "").get();
				String parastr = htmla.xpath("//a/@onclick").regex("(queryInfor\\s*\\(\\s*)('.+'\\s*,\\s*'.+'\\s*,\\s*'.+'\\s*,\\s*'.+'\\s*,\\s*'.+'\\s*,\\s*'.+')(\\s*\\))",2).replace("['\\s]", "").get();
				String parameters[] = parastr.split(",");
				//String url = parameters[0];
				String org = parameters[1];
				String id = parameters[2];
				String seq_id = parameters[3];
				String regNumber = parameters[4].trim();
				//String containContextPath = parameters[5];
				if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
					iter.remove();
					continue;
				}
				jbxx(page, task, org, id, seq_id, regNumber, entName);
				gdxx(page, task, org, id, seq_id, regNumber, entName);
				bgxx(page, task, org, id, regNumber, entName);
				zyryxx(page, task, org, id, seq_id, regNumber, entName);
				fzjgxx(page, task, org, id, seq_id, regNumber, entName);
			}
			tst.setSeedSdP(as.size());
		}
	}
	private void fzjgxx(Page page, PageProcessor task, String org, String id, String seq_id, String regNumber, String entName) {
		//分支机构
		NameValuePair[] nvps5 = {
				new BasicNameValuePair("CORP_ORG", org),
				new BasicNameValuePair("CORP_ID", id),
				new BasicNameValuePair("CORP_SEQ_ID", seq_id),
				new BasicNameValuePair("showRecordLine", "1"),
				new BasicNameValuePair("specificQuery", "branchOfficeInfor"),
				new BasicNameValuePair("pageNo","1"),
				new BasicNameValuePair("pageSize","5"),
		};
		//String url = builderURL("/ecipplatform/ciServlet.json?ciEnter=true&"+urlForParamsTailForJiangSu(), task.getSite());
		String url = builderURL(nextURLForData+urlForParamsTailForJiangSu(), task.getSite());
		page.addTargetRequest(builderRequest(url, Method.POST, regNumber, entName, nvps5));
	}
	private void zyryxx(Page page, PageProcessor task, String org, String id, String seq_id, String regNumber, String entName) {
		//主要人员信息
		NameValuePair[] nvps4 = {
				new BasicNameValuePair("CORP_ORG", org),
				new BasicNameValuePair("CORP_ID", id),
				new BasicNameValuePair("CORP_SEQ_ID", seq_id),
				new BasicNameValuePair("showRecordLine", "1"),
				new BasicNameValuePair("specificQuery", "personnelInformation"),
				new BasicNameValuePair("pageNo","1"),
				new BasicNameValuePair("pageSize","5"),
		};
		//String url = builderURL("/ecipplatform/ciServlet.json?ciEnter=true&"+urlForParamsTailForJiangSu(),task.getSite());
		String url = builderURL(nextURLForData+urlForParamsTailForJiangSu(),task.getSite());
		page.addTargetRequest(builderRequest(url, Method.POST, regNumber, entName, nvps4));
	}
	private void bgxx(Page page, PageProcessor task, String org, String id, String regNumber, String entName) {
		//变更信息
		NameValuePair[] nvps3 = {
				new BasicNameValuePair("showRecordLine", "1"),
				new BasicNameValuePair("specificQuery", "commonQuery"),
				new BasicNameValuePair("propertiesName", "biangeng"),
				new BasicNameValuePair("corp_org", org),
				new BasicNameValuePair("corp_id", id),
				new BasicNameValuePair("tmp",new Date().toString()),
				new BasicNameValuePair("pageNo","1"),
				new BasicNameValuePair("pageSize","5"),
		};
		String url = builderURL("/ecipplatform/commonServlet.json?commonEnter=true&"+urlForParamsTailForJiangSu(),task.getSite());
		page.addTargetRequest(builderRequest(url, Method.POST, regNumber, entName, nvps3));
	}
	private void gdxx(Page page, PageProcessor task, String org, String id, String seq_id, String regNumber, String entName) {
		//股东信息
		NameValuePair[] nvps2 = {
				new BasicNameValuePair("CORP_ORG", org),
				new BasicNameValuePair("CORP_ID", id),
				new BasicNameValuePair("CORP_SEQ_ID", seq_id),
				new BasicNameValuePair("showRecordLine", "1"),
				new BasicNameValuePair("specificQuery", "investmentInfor"),
				new BasicNameValuePair("pageNo","1"),
				new BasicNameValuePair("pageSize","5"),
		};
		//String url = builderURL("/ecipplatform/ciServlet.json?ciEnter=true&"+urlForParamsTailForJiangSu(),task.getSite());
		String url = builderURL(nextURLForData+urlForParamsTailForJiangSu(),task.getSite());
		page.addTargetRequest(builderRequest(url, Method.POST,  regNumber, entName, nvps2));
	}
	private void jbxx(Page page, PageProcessor task, String org, String id, String seq_id, String regNumber, String entName) {
		//基本信息
		NameValuePair[] nvps1 = {
				new BasicNameValuePair("org", org),
				new BasicNameValuePair("id", id),
				new BasicNameValuePair("seq_id", seq_id),
				new BasicNameValuePair("specificQuery", "basicInfo"),
		};
		//String url = builderURL("/ecipplatform/ciServlet.json?ciEnter=true&"+urlForParamsTailForJiangSu(), task.getSite());
		String url = builderURL(nextURLForData+urlForParamsTailForJiangSu(), task.getSite());
		page.addTargetRequest(builderRequest(url, Method.POST, regNumber, entName, nvps1));
	}
}
