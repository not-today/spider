package com.heetian.spider.process.jiangxi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.enumeration.SeedStatus;
import com.heetian.spider.peking.strategy.IsSucess;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.tools.MD5Util;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 
 * @author tst
 *
 */
public class ValidateCodeProcess extends JiangXiProcessHandlePrepare {
	public ValidateCodeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/warningetp/yzm.do");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String json = page.getRawText();
		TSTPageProcessor tst = (TSTPageProcessor) task;
		if(json==null||json.contains("操作过于频繁，请稍后再访问")){
			tst.setProxyStatus(ProxyStatus.NO);
			tst.setStatus(SeedStatus.FAIL);
			return;
		}
		String results[] = (String[]) page.getRequest().getExtra(RecognizedContext.saveName);
		if(json==null||!json.equals("true")){
			String url = builderURL("/warningetp/reqyzm.do?r="+System.currentTimeMillis(),task.getSite());
			page.addTargetRequest(builderRequestGet(url));
			return;
		}
		logger.warn("提交验证码后的结果："+json);
		RecognizedContext.newInstance().updateCurrentError(IsSucess.SUCCESS, Integer.parseInt(results[1]), Long.parseLong(results[2]));
		String entname = MD5Util.encode(tst.getSeedNm(), "utf-8")/*"6ZKi6ZOB"*/;
		NameValuePair[] nvps = {
				new BasicNameValuePair("ename", entname),
				new BasicNameValuePair("liketype", "qyxy"),
				new BasicNameValuePair("pageIndex", "0"),
				new BasicNameValuePair("pageSize", "10000"),
				};
		task.getSite().addHeader("Referer", "http://gsxt.jxaic.gov.cn/index.jsp?ename="+entname+"&liketype=qyxy");
		task.getSite().addHeader("X-Requested-With","XMLHttpRequest");
		String url = builderURL("/search/queryenterpriseinfoindex.do",task.getSite());
		page.addTargetRequest(builderRequestPost(url, nvps));
	}
}