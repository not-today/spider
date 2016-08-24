package com.heetian.spider.process.gansu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.GanSuProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends GanSuProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/gsxygs/securitycode.jpg?v=");
		setProcessName(processName_down);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String results[] = (String[]) page.getRequest().getExtra(pictureContent);
		String code = task.getSite().getCookies().get("session_authcode");
		if(code!=null&&code.contains(";"))
			code = code.replaceAll("[;\\s]", "");
		@SuppressWarnings("unchecked")
		List<String> inputs = (List<String>) page.getRequest().getExtra("inputs");
		List<NameValuePair> nms = new ArrayList<NameValuePair>();
		if(inputs!=null&&inputs.size()>0){
			for(String in:inputs){
				Html inh = new Html(in);
				String name = inh.xpath("//input/@name").replace("\\s", "").get();
				if(name==null||"queryVal".equals(name)||"authCodeQuery".equals(name)||"".equals(name))
					continue;
				String value = inh.xpath("//input/@value").get();
				nms.add(new BasicNameValuePair(name, value));
			}
		}
		nms.add(new BasicNameValuePair("queryVal", ((TSTPageProcessor)task).getSeedNm()));
		nms.add(new BasicNameValuePair("authCodeQuery", code));
		NameValuePair[] nvps = nms.toArray(new NameValuePair[nms.size()]);
		String url = builderURL("/gsxygs/pub!list.do?"+System.currentTimeMillis(),task.getSite());
		Request request = builderRequest(url,Method.POST, null,null, nvps);
		request.putExtra(RecognizedContext.saveName, results);
		page.addTargetRequest(request);
		return;
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		String url = builderURL("/gsxygs/securitycode.jpg?v="+System.currentTimeMillis()+"&"+urlTail(),site);
		return builderRequest(url,Method.GET, null,null, null);
	}
}
