package com.heetian.spider.process.jiangsu;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
/**
 * @author tst
 */
@Deprecated
public class DetailGDProcess extends JiangSuProcessHandlePrepare{
	public DetailGDProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/ecipplatform/inner_ci/ci_gsRelease_investorInfor.jsp");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		NameValuePair[] tmp = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		NameValuePair[] nvps = {
			new BasicNameValuePair("ORG", tmp[5].getValue()),
			new BasicNameValuePair("ID", tmp[6].getValue()),
			new BasicNameValuePair("SEQ_ID", tmp[7].getValue()),
			new BasicNameValuePair("CORP_ORG", tmp[0].getValue()),
			new BasicNameValuePair("CORP_ID", tmp[1].getValue()),
			new BasicNameValuePair("REG_NO", regNumber),
			new BasicNameValuePair("specificQuery", "investorInfor"),
		};
		
		String url = builderURL("/ecipplatform/ciServlet.json?ciDetail=true&"+urlForParamsTailForJiangSu(), task.getSite());
		Request request = builderRequest(url, Method.POST, regNumber, entName, nvps);
		page.addTargetRequest(request);
	}
}
