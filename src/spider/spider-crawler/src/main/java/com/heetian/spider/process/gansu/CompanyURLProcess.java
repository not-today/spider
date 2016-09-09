package com.heetian.spider.process.gansu;

import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.GanSuProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends GanSuProcessHandlePrepare{
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/gsxygs/pub!list.do");
		setProcessName(processName_list);
	}
	private static final String url = "/gsxygs/pub!view.do";
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String rawText = page.getRawText();
		if(rawText.contains("尊敬的用户： 请您输入更精确的查询条件")){
			logger.warn(((TSTPageProcessor)task).getSeedNm()+":尊敬的用户： 请您输入更精确的查询条件");
			return;
		}
		if(rawText.contains("尊敬的用户：验证码输入错误,请重新输入")){
			String url = builderURL("/gsxygs/securitycode.jpg?v="+System.currentTimeMillis()+"&"+urlTail(),task.getSite());
			page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
			return;
		}
		List<String> uls = page.getHtml().xpath("//form[@id='infoForm']/div/div/div[@class='list']/ul").all();
		if(uls==null||uls.size()<=0){
			logger.warn(((TSTPageProcessor)task).getSeedNm()+":企业列表数据as获取为:"+uls+";可能是由于不正常原因导致,但也可能是正常情况");
			return;
		}
		Iterator<String> iter = uls.iterator();
		while(iter.hasNext()){
			String ul = iter.next();
			Selectable ulhtml = new Html(ul);
			String regNumber = ulhtml.xpath("//ul/li[2]/span[1]/allText()").replace("\\s", "").get();
			String id = ulhtml.xpath("//ul/li[1]/a/@id").get();
			String entcate = ulhtml.xpath("//ul/li[1]/a/@_entcate").get();
			String entName = ulhtml.xpath("//ul/li[1]/a/allText()").replace("\\s", "").get();
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				iter.remove();
				continue;
			}	
			NameValuePair[] nvps = {
				new BasicNameValuePair("regno", id),
				new BasicNameValuePair("entcate", entcate)
			};
			Request request = builderRequest(builderURL(url+"?"+urlTail()+"&a="+Math.random(),task.getSite()),Method.POST, regNumber,entName, nvps);
			page.addTargetRequest(request);
		}
	}
}
