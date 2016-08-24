package com.heetian.spider.process.tianjing;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.TianJingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexDJXX1Process extends TianJingProcessHandlePrepare {
	public ContexDJXX1Process(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/platform/saic/baseInfo.do");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		List<String> tables = page.getHtml().xpath("//table").all();
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		for(String table :tables){
			List<String> trs = new Html(table).xpath("//tr").all();
			if(trs==null||trs.size()<=0){
				continue;
			}
			String trFirst = trs.remove(0);
			if(trFirst==null)
				continue;
			if(trFirst.contains("基本信息")){
				GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName,trs,tst);
				if(jbxxBean==null)
					return;
				jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
				tst.setDataJB(jbxxBean,regNumber);
			}else if(trFirst.contains("变更信息")){
				tst.setDataBG(bgxx(trs,page,regNumber),regNumber);
			}else if(trFirst.contains("股东信息")){
				tst.setDataGD(gdxx(trs,regNumber,entName,task,page),regNumber);
			}else if(trFirst.contains("主要人员信息")){
				tst.setDataZYRY(zyryxx(trs,page,regNumber),regNumber);
			}else if(trFirst.contains("分支机构信息")){
				tst.setDataFZJG(fzjgxx(trs,page,regNumber),regNumber);
			}
		}
	}
	private List<GsgsBranch> fzjgxx(List<String> trs,Page page,String regNumber) {
		if(trs!=null&&trs.size()>1){
			trs.remove(0);
			List<GsgsBranch> branchInfos = new ArrayList<GsgsBranch>();
			for(String tr:trs){
				List<String> text = new Html("<table>"+tr+"</table>").xpath("//table//tr//td").all();
				if(text.size()==4){
					branchInfos.add(AnalysisForTable.createBranch(regNumber, AnalysisForTable.getTdText(text.get(1)), AnalysisForTable.getTdText(text.get(2)), AnalysisForTable.getTdText(text.get(3))));
				}else{
					if(text.size()!=1)
					//判断是否有其他情况
						logger.error("分支结构结构异常导致而没有存入的信息：["+regNumber+"]"+text.toString());
				}
			}
			if(branchInfos.size()>0)
				return branchInfos;
		} 
		return null;
}
private List<GsgsMainPersonel> zyryxx(List<String> trs,Page page,String regNumber) {
	if(trs!=null&&trs.size()>1){
		trs.remove(0);
		List<GsgsMainPersonel> mainPersonls = new ArrayList<GsgsMainPersonel>();
		for(String tr:trs){
			List<String> text = new Html("<table>"+tr+"</table>").xpath("//table//tr//td").all();
			if(text.size()==6){
				String name1 = AnalysisForTable.getTdText(text.get(1));
				String value1 = AnalysisForTable.getTdText(text.get(2));
				if(!"".equals(name1.trim())&&!"".equals(value1.trim())){
					mainPersonls.add(AnalysisForTable.createMainPerson(regNumber, name1, value1));
				}
				String name2 = AnalysisForTable.getTdText(text.get(4));
				String value2 = AnalysisForTable.getTdText(text.get(5));
				if(!"".equals(name2.trim())&&!"".equals(value2.trim())){
					mainPersonls.add(AnalysisForTable.createMainPerson(regNumber, name2, value2));
				}
			}else if(text.size()==4||text.size()==3){
				String name1 = AnalysisForTable.getTdText(text.get(1));
				String value1 = AnalysisForTable.getTdText(text.get(2));
				mainPersonls.add(AnalysisForTable.createMainPerson(regNumber, name1, value1));
			}else{
				if(text.size()!=1)
				//判断是否有其他情况
					logger.error("主要人员结构结构异常导致而没有存入的信息：["+regNumber+"]"+text.toString());
			}
		}
		if(mainPersonls.size()>0)
			return mainPersonls;
	}
	return null;
}
	private List<GsgsChange> bgxx(List<String> trs,Page page,String regNumber) {
		if(trs!=null&&trs.size()>1){
			trs.remove(0);
			List<GsgsChange> changeinfoList = new ArrayList<GsgsChange>();
			for(String tr:trs){
				List<String> text = new Html("<table>"+tr+"</table>").xpath("//table//tr//td").all();
				if(text.size()==4){
					changeinfoList.add(AnalysisForTable.createChangeInfo(regNumber, AnalysisForTable.getTdText(text.get(0)), AnalysisForTable.getTdText(text.get(1)), AnalysisForTable.getTdText(text.get(2)), AnalysisForTable.getTdText(text.get(3))));
				}else{
					if(text.size()!=1)
					//判断是否有其他情况
						logger.error("变更结构结构异常导致而没有存入的信息：["+regNumber+"]"+text.toString());
				}
			}
			if(changeinfoList.size()>0)
				return changeinfoList;
		}
		return null;
	}
	private List<GsgsShareholder> gdxx(List<String> trs,String regNumber,String entName,PageProcessor task,Page page) {
		if(trs!=null&&trs.size()>1){
			trs.remove(0);
			List<GsgsShareholder> holders = new ArrayList<GsgsShareholder>();
			for(String tr:trs){
				List<String> text = new Html("<table>"+tr+"</table>").xpath("//table//tr//td").all();
				String hasInfo = (String) page.getRequest().getExtra("hasInfo");
				if(text.size()==4||text.size()==5){
					String uuid = TSTUtils.uuid();
					GsgsShareholder  gd = AnalysisForTable.createShareHolder(regNumber, AnalysisForTable.getTdText(text.get(0)), AnalysisForTable.getTdText(text.get(1)), AnalysisForTable.getTdText(text.get(2)), AnalysisForTable.getTdText(text.get(3)), null);
					holders.add(gd);
					if(text.size()==5){
						//详情解析CheckDetail.getShareHolder('786404c653ee8a1467011b2180b7b309','349DDA405B770231E04400306EF52828')
						//("/saicpf/gsgdcz?gdczID="+id+"&ENTID="+entId+"&issaic=1&hasInfo="+$("#hasInfo").val());
						Selectable aSelectable = new Html(text.get(4)).xpath("//a/@onclick");
						String parametersCom = aSelectable.regex("(CheckDetail\\.getShareHolder\\s*\\(\\s*)('.+'\\s*,\\s*'.+')(\\s*\\)\\s*)",2).replace(" |'|\\s", "").get();
						if(parametersCom!=null&&!"".equals(parametersCom)){
							String parameterArr[] = parametersCom.split(",");
							String url = builderURL("/saicpf/gsgdcz?gdczid="+parameterArr[0]+"&entid="+parameterArr[1]+"&issaic=1&hasInfo="+hasInfo,task.getSite());
							gd.setUuid(uuid);
							Request request = builderRequest(url, Method.GET, regNumber, entName, null);
							request.putExtra(ProcessHandle.uuid_key, uuid);
							page.addTargetRequest(request);
						}
					}
				}else{
					if(text.size()!=1)//判断是否有其他情况
						logger.error("股东结构结构异常导致而没有存入的信息：["+regNumber+"]"+text.toString());
				}
			}
			if(holders.size()>0)
				return holders;
		}
		return null;
	}
}

