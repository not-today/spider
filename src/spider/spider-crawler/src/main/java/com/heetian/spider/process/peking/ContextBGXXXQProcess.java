package com.heetian.spider.process.peking;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextBGXXXQProcess extends PekingProcessHandlePrepare {
	public ContextBGXXXQProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!tzrBgxx.dhtml");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		Selectable html = page.getHtml();
		String before = null;
		String after = null;
		if(page.getRequest().getUrl().contains("/gjjbj/gjjQueryCreditAction!tzrBgxx.dhtml")){
			String key1 = "姓名/名称",key2 = "投资人类型";
			before = analysisTR(html.xpath("//table[2]").xpath("//tr").all(),key1,key2);		
			after = analysisTR(html.xpath("//table[3]").xpath("//tr").all(),key1,key2);
		}else if(page.getRequest().getUrl().contains("/gjjbj/gjjQueryCreditAction!zyryBgxx.dhtml")){
			String key1 = "姓名",key2 = "职位";
			before = analysisTR(html.xpath("//table[2]").xpath("//tr").all(),key1,key2);		
			after = analysisTR(html.xpath("//table[3]").xpath("//tr").all(),key1,key2);
		}else{
			System.out.println("变更详情:"+page.getRequest().getUrl());
		}
		GsgsChange info = (GsgsChange) page.getRequest().getExtra("ChangeInfoBeanNull2");
		if(info!=null){
			info.setChs(before);
			info.setChn(after);
			List<GsgsChange> infos = new ArrayList<GsgsChange>();
			infos.add(info);
			((TSTPageProcessor) task).setDataBG(infos,info.getRegNumber());
		}
	}
	
	private String analysisTR(List<String> trs,String key1,String key2) {
		if(trs!=null&&trs.size()>2){
			trs.remove(0);
			trs.remove(0);
			String name = "";
			for(String tr:trs){
				Html tableHtml = new Html("<table>"+tr+"</table>");
				List<String> text = tableHtml.xpath("//table//tr//td").all();
				if(text.size()==3){
					name =key1+"="+AnalysisForTable.getTdText(text.get(1))+","+key2+"="+AnalysisForTable.getTdText(text.get(2))+";"+name;
				}else{
					//判断是否有其他情况
					//logger.error("主要人员结构结构异常导致而没有存入的信息：["+regNumber+"]"+text.toString());
				}
			}
			return name;
		}else{
			return null;
		}
	}
}