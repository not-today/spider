package com.heetian.spider.process.heilongjiang;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.HeiLongJiangProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!tzrFrame.dhtml?ent_id=81395C2BBE684309BCD1829473D90F6F&entName=&clear=true&timeStamp=1433907595036
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends HeiLongJiangProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/queryInvDetailAction.jspx");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		List<String> trs = page.getHtml().xpath("//div[@id='details']/table//tr").all();
		if(trs==null||trs.size()<=3){
			return;
		}
		trs.remove(0);
		trs.remove(0);
		trs.remove(0);
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
		((TSTPageProcessor) task).setDataGDXQ(AnalysisForTable.gdxxxxHTMLProcessToJAVAObject(trs, uuid, regNumber),uuid,regNumber);
	}
}
