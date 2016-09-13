package com.heetian.spider.process.jilin;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.enumeration.SeedStatus;
import com.heetian.spider.peking.strategy.RecognizedContext;
import com.heetian.spider.process.abstractclass.JiLinProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.KeyPoint_CODE;
import com.heetian.spider.utils.TSTUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;
/**
 * 
 * @author tst
 *
 */
public class DownloadCodeProcess extends JiLinProcessHandlePrepare implements KeyPoint_CODE{
	public DownloadCodeProcess(){
		this.isDownloadCodeProcess = true;
		this.isStorageProcess = false;
		setUniqueWebUri("/aiccips/securitycode");
		setProcessName(processName_down);
	}
	@Override
	public void analyticalProcessJiLin(Page page,PageProcessor task) {
		int number = toInt(page.getRequest().getExtra(dwNum));
		if(number<=10){
			String results[] = (String[]) page.getRequest().getExtra(pictureContent);
			NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
			nvps[2] = new BasicNameValuePair("secode", TSTUtils.toMD5(results[0])); 
			String url = builderURL("/aiccips/pub/indsearch?"+urlTail(),task.getSite());
			Request request = builderRequest(url,Method.POST, null,null, nvps);
			request.putExtra(RecognizedContext.saveName, results);
			request.putExtra(dwNum, String.valueOf(number+1));
			page.addTargetRequest(request);
			return;
		}else{
			((TSTPageProcessor)task).setStatus(SeedStatus.FAIL);//当验证码请求超过一定次数。回收种子
		}
	}
	@Override
	public Request reloadCode(Request request,Site site) {
		NameValuePair[] nvps = (NameValuePair[]) request.getExtra(NAMEVALUEPAIR);
		int number = Integer.parseInt((String)request.getExtra("downloadCodeNumber"));
		String url = builderURL("/aiccips/securitycode?"+urlTail(),site);
		Request tmp = builderRequest(url,Method.GET, null,null, nvps);
		tmp.putExtra(dwNum, String.valueOf(number+1));
		return tmp;
	}
	private <T> int toInt(T x){
		if(x==null||"".equals(x))
			return -1;
		if((x instanceof String)&&((String)x).matches("\\d+"))
			return Integer.parseInt((String)x);
		return -1;
	}
}
