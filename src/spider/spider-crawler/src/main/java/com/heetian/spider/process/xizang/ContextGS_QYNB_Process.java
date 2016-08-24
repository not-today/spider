package com.heetian.spider.process.xizang;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.XiZangProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContextGS_QYNB_Process extends XiZangProcessHandlePrepare {
	public ContextGS_QYNB_Process(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/enterprisePublicity.jspx");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		List<String> jbTrs = page.getHtml().xpath("//div[@id='qiyenianbao']/table[1]//tr").all();
		if(jbTrs==null||jbTrs.size()<=0)
			return;
		for(String jb:jbTrs){
			Html htm = new Html("<table>"+jb+"</table>");
			String url = htm.xpath("//tr/td[2]/a/@href").get();
			String date = htm.xpath("//tr/td[3]/allText()").get();
			String report = htm.xpath("//tr/td[2]/a/@allText()").get();
			Request request = builderRequest(url,Method.GET, regNumber,entName, null);
			request.putExtra("date", date);
			request.putExtra("report", report);
			request.putExtra("uuid", TSTUtils.uuid());
			page.addTargetRequest(request);
		}
	}
}

