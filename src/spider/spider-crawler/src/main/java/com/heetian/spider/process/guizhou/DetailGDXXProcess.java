package com.heetian.spider.process.guizhou;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.GuiZhouProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
/**
 * 
 * @author tst
 *
 */
public class DetailGDXXProcess extends GuiZhouProcessHandlePrepare{
	public DetailGDXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/nzgs/tzrxx.jsp");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		Request oldRequest = page.getRequest();
		String regNumber = (String) oldRequest.getExtra(REGNUMBER);
		String entName = (String) oldRequest.getExtra(ENTNAME);
		//searchTzrDetail(2, 4, "1e7a1d2104b83f144a1cfd5da5e9471f5029413b18fbf4ff3eafbfc0af87c163", "国信文化艺术股份有限公司","touziren");
		String programsStr = page.getHtml().regex("(searchTzrDetail\\s*\\(\\s*)(.+\"touziren\")(\\s*\\)\\s*;.+)",2).replace("[\\s\"']", "").get();
		if(programsStr!=null&&!"".equals(programsStr)){
			String jbprograms[] = programsStr.split(",");
			if(jbprograms.length>=3){
				NameValuePair nvps[] = {
						new BasicNameValuePair("c", jbprograms[0]),
						new BasicNameValuePair("t", jbprograms[1]),
						new BasicNameValuePair("nbxh", jbprograms[2]),
						new BasicNameValuePair("czmc", jbprograms[3])
				};
				Request request = builderRequest(builderURL("/nzgs/search!searchTzr.shtml"+"?"+urlTail()+"&"+Math.random(), task.getSite()), Method.POST, regNumber, entName, nvps);
				request.putExtra(ProcessHandle.uuid_key, page.getRequest().getExtra(ProcessHandle.uuid_key));
				page.addTargetRequest(request);
			}
		}
	}
}
