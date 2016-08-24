package com.heetian.spider.process.gansu;

import java.util.List;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.process.abstractclass.GanSuProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContexJBXXProcess extends GanSuProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/gsxygs/pub!view.do");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		Selectable jbdiv = page.getHtml().xpath("//div[@id='jibenxinxi']");
		List<String> trs = jbdiv.xpath("//table[1]//tr").all();
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		GsgsRegister jbxxBean = AnalysisForTable.jbxxHTMLProcessToJAVAObject(regNumber, entName, trs,tst);
		if(jbxxBean==null){
			return;
		}
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		String urlTmp = TSTUtils.bufferedURL(page.getRequest().getUrl().split("\\?")[0],httpMethodParam_post,nvps);
		jbxxBean.setUrl(urlTmp);
		tst.setDataJB(jbxxBean,regNumber);
		List<String> gdtrs = jbdiv.xpath("//table[@id='touziren']//tr").all();
		if(gdtrs!=null&&gdtrs.size()>2){
			gdtrs.remove(0);
			gdtrs.remove(0);
			tst.setDataGD(AnalysisForTable.gdxxHTMLToJObj(this,gdtrs, regNumber, entName, task.getSite(), page),regNumber);
		}
		List<String> bgtrs = jbdiv.xpath("//table[@id='changTab']//tr").all();
		if(bgtrs!=null&&bgtrs.size()>2){
			bgtrs.remove(0);
			bgtrs.remove(0);
			tst.setDataBG(AnalysisForTable.bgxxHTMLProcessToJAVAObject(bgtrs, regNumber, page),regNumber);
		}
		Selectable beiandiv = page.getHtml().xpath("//div[@id='beian']");
		List<String> zyrytrs = beiandiv.xpath("//table[@id='perTab']//tr").all();
		if(zyrytrs!=null&&zyrytrs.size()>2){
			zyrytrs.remove(0);
			zyrytrs.remove(0);
			tst.setDataZYRY(AnalysisForTable.zyryxxHTMLProcessToJAVAObject(zyrytrs, regNumber, page),regNumber);
		}
		List<String> fzjgtrs = beiandiv.xpath("//table[@id='branTab']//tr").all();
		if(fzjgtrs!=null&&fzjgtrs.size()>2){
			fzjgtrs.remove(0);
			fzjgtrs.remove(0);
			tst.setDataFZJG(AnalysisForTable.fzjgxxHTMLProcessToJAVAObject(fzjgtrs, regNumber, page),regNumber);
		}
	}
}

