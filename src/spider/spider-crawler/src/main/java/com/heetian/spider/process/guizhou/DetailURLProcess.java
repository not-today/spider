package com.heetian.spider.process.guizhou;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.GuiZhouProcessHandlePrepare;
/**
 * 
 * @author tst
 *
 */
public class DetailURLProcess extends GuiZhouProcessHandlePrepare{
	private static final String url = "/nzgs/search!searchData.shtml";
	public DetailURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/index.jsp");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		//System.out.println(page.getRequest().getUrl());
		Request oldRequest = page.getRequest();
		String regNumber = (String) oldRequest.getExtra(REGNUMBER);
		String entName = (String) oldRequest.getExtra(ENTNAME);
		Selectable li = page.getHtml().xpath("//ul[@id='tabs']/li");
		Selectable jbxx = li.xpath("//li[@id='jbxx']/@onclick").replace("['\\s]", "");
		Site site = task.getSite();
		boolean success = checkURLPrograms(page, site, regNumber, entName,"jbxx",jbxx,"(.*searchOne\\s*\\(\\s*)(\\d,\\d,\\w+,\\w+)(\\s*\\)\\s*;\\s*.*)");
		if(success){
			checkURLPrograms(page, site, regNumber, entName,"bgxx",jbxx,"(.*searchList\\s*\\(\\s*)(\\d,\\d,\\w+,\\w+,\\w+)(\\s*\\)\\s*.*)");
			checkURLPrograms(page, site, regNumber, entName,"gdxx",jbxx,"(.*searchTzrList\\s*\\(\\s*)(\\d,\\d,\\w+,\\w+,\\w+)(\\s*\\)\\s*.*)");
			
			Selectable baxx = li.xpath("//li[@id='baxx']/@onclick").replace("['\\s]", "");
			checkURLPrograms(page, site, regNumber, entName,"zyryxx",baxx,"(.*searchQyZyryList\\s*\\(\\s*)(\\d,\\d,\\w+,\\w+,\\w+)(\\s*\\)\\s*.*)");
			checkURLPrograms(page, site, regNumber, entName,"fzjgxx",baxx,"(.*searchList\\s*\\(\\s*)(\\d,\\d,\\w+,\\w+,\\w+)(\\s*\\)\\s*.*)");
		}else{
			logger.error("基本信息的url构建失败，此条企业丢失:"+entName+"-"+regNumber);
		}
		/**
		 *  <li id="jbxx" class="current" onclick="changeStyle('tabs','jbxx');changeDiv('detailsCon','jibenxinxi');searchOne(0, 5, '', '1_');searchList(0, 3, '', 'bgxxTable', 3);searchTzrList(2, 3, '', 'tzxxTable', 5);">登记信息</li>
        	<li id="baxx" onclick="changeStyle('tabs','baxx');changeDiv('detailsCon','beian');searchQyZyryList(0, 8, '', 'zyryTable', 3);searchList(0, 9, '', 'fzjgTable', 5);searchOne(0, 36, '', '2_');">备案信息</li>
		 */
	}
	private boolean checkURLPrograms(Page page, Site site, String regNumber, String entName, String tableFlag,Selectable html,String regex) {
		String programsStr = html.regex(regex,2).get();
		if(programsStr!=null&&!"".equals(programsStr)){
			String jbprograms[] = programsStr.split(",");
			if(jbprograms.length>=3){
				NameValuePair nvps[] = {new BasicNameValuePair("c", jbprograms[0]), new BasicNameValuePair("t", jbprograms[1]), new BasicNameValuePair("nbxh", jbprograms[2])};
				Request request = builderRequest(builderURL(url+"?"+urlTail()+"&"+Math.random(), site), Method.POST, regNumber, entName, nvps);
				request.putExtra("nbxhflag", jbprograms[2]);
				request.putExtra("tableFlag", tableFlag);
				page.addTargetRequest(request);
				return true;
			}
		}
		return false;
	}
}
