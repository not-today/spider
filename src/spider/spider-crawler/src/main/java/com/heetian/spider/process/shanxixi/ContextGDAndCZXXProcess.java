package com.heetian.spider.process.shanxixi;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.ShanXiXiProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends ShanXiXiProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ztxy.do?method=tzrCzxxDetial");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		List<String> trs = page.getHtml().xpath("//div[@id='sifapanding']/table//tr").all();
		if(trs!=null&&trs.size()>3){
			trs.remove(0);
			trs.remove(0);
			trs.remove(0);
			String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
			String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
			((TSTPageProcessor)task).setDataGDXQ(AnalysisForTable.gdxxxxHTMLProcessToJAVAObject_forSiChuna(trs, page,uuid, regNumber), uuid, regNumber);
		}
	}
}
