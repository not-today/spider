package com.heetian.spider.process.shanghai;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.ShangHaiProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexJBXXProcess extends ShangHaiProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/notice/notice/view?uuid");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		List<String> tables = page.getHtml().xpath("//div[@class='hide']/table[@class='info m-bottom m-top']").all();
		if(tables==null||tables.size()<=0){
			return;
		}
		for(String table:tables){
			if(!table.contains("基本信息")){
				continue;
			}
			TSTPageProcessor tst = ((TSTPageProcessor) task);
			List<String> jbTrs = new Html(table).xpath("//table//tr").all();
			GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(null, null, jbTrs,tst);
			if(jbxxBean==null){
				return;
			}
			String regNumber = jbxxBean.getRgc();
			String entName = jbxxBean.getName();
			if(!TSTUtils.checkIsExitForEnter(task, regNumber, entName)){
				return;
			}
			page.getRequest().putExtra(ENTNAME, entName);
			page.getRequest().putExtra(REGNUMBER, regNumber);
			jbxxBean.setUrl(TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_get, null));
			//股东信息
			List<String> gdTrs = page.getHtml().xpath("//table[@id='investorTable']//tr").all();
			if(gdTrs!=null&&gdTrs.size()>2){
				String title = gdTrs.remove(0);
				gdTrs.remove(0);
				if(title.contains("主管部门（出资人）信息")){
					jbxxBean.setShareholders(AnalysisForTable.gdxxHTMLToJObjZGBM(this,gdTrs, regNumber, entName, task.getSite(), page));
				}else{
					jbxxBean.setShareholders(AnalysisForTable.gdxxHTMLToJObj(this,gdTrs, regNumber, entName, task.getSite(), page));
				}
			}
			//变更
			List<String> bgTrs = page.getHtml().xpath("//table[@id='alterTable']//tr").all();
			if(bgTrs!=null&&bgTrs.size()>2){
				bgTrs.remove(0);
				bgTrs.remove(0);
				jbxxBean.setChanges(AnalysisForTable.bgxxHTMLProcessToJAVAObject(bgTrs, regNumber, page));
			}
			//主要人员
			List<String> zyryTrs = page.getHtml().xpath("//table[@id='memberTable']//tr").all();
			if(zyryTrs!=null&&zyryTrs.size()>2){
				zyryTrs.remove(0);
				zyryTrs.remove(0);
				jbxxBean.setKeyPersons(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(zyryTrs, regNumber, page));
			}
			//分支机构
			List<String> fzjgTrs = page.getHtml().xpath("//table[@id='branchTable']//tr").all();
			if(fzjgTrs!=null&&fzjgTrs.size()>2){
				fzjgTrs.remove(0);
				fzjgTrs.remove(0);
				jbxxBean.setBranchs(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(fzjgTrs, regNumber, page));
			}
			tst.setDataJB(jbxxBean,regNumber);
		}
	}
}

