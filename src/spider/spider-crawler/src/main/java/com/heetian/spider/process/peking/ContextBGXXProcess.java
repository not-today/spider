package com.heetian.spider.process.peking;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.process.abstractclass.PekingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextBGXXProcess extends PekingProcessHandlePrepare {
	public ContextBGXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/gjjbj/gjjQueryCreditAction!biangengFrame.dhtml");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		nextURL(page, task);
		List<String> trs = page.getHtml().css("table").xpath("//tr").all();
		if(trs!=null&&trs.size()>3){
			trs.remove(0);
			trs.remove(0);
			trs.remove(trs.size()-1);
			List<GsgsChange> bgxx = new ArrayList<GsgsChange>();
			String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
			String entName = (String) page.getRequest().getExtra(ENTNAME);
			for(String tr:trs){
				Html tableHtml = new Html("<table>"+tr+"</table>");
				List<String> text = tableHtml.xpath("//table//tr//td").all();
				if(text.size()==4){
					bgxx.add(AnalysisForTable.createChangeInfo(regNumber, AnalysisForTable.getTdText(text.get(0)), AnalysisForTable.getTdText(text.get(1)), AnalysisForTable.getTdText(text.get(2)), AnalysisForTable.getTdText(text.get(3))));
				}else if(text.size()==3){
					Html tableHtmltmp = new Html(text.get(1));
					//详情解析，获取a链接url访问
					String url = tableHtmltmp.xpath("//a/@onclick").regex("(showDialog\\s*\\(\\s*'\\s*)(/gjjbj.+)(\\s*'\\s*,\\s*'\\s*.+\\s*'\\s*,\\s*'\\s*.+\\s*'\\s*\\))",2).get();
					Request request = builderRequest(builderURL(url+"&"+urlTail(),task.getSite()), Method.GET, regNumber, entName, null);
					request.putExtra("ChangeInfoBeanNull2", AnalysisForTable.createChangeInfo(regNumber, AnalysisForTable.getTdText(text.get(0)), null, null, AnalysisForTable.getTdText(text.get(2))));
					page.addTargetRequest(request);
				}else{
					//判断是否有其他情况
					logger.error("变更结构结构异常导致而没有存入的信息：["+regNumber+"]"+text.toString());
				}
			}
			if(bgxx.size()>0){
				((TSTPageProcessor) task).setDataBG(bgxx,regNumber);
			}
		}
	}
}