package com.heetian.spider.process.jiangsu;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * 
 * @author tst
 * 变更信息
 */
public class ContexBGXXProcess extends JiangSuProcessHandlePrepare {
	public ContexBGXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ecipplatform/commonServlet.json?commonEnter=true");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		for(NameValuePair tmp:nvps){
			if(tmp==null||!"propertiesName".equals(tmp.getName()))
				continue;
			String content = page.getRawText();
			if(content==null||"".equals(content.trim()))
				continue;
			String value = tmp.getValue();
			if("biangeng".equals(value)){
				resultBGXX(content,(String) page.getRequest().getExtra(REGNUMBER),task);
			}else if("recordTime".equals(value)){
				//System.out.println(page.getRawText());
			}else{
				logger.error("获取表数据时，未知表字段信息:"+page.getRawText());
			}
		}
	}
	private void resultBGXX(String content,String regNumber,PageProcessor task) {
		BianGeng bean = AnalysisForJson.jsonToObject(content, new TypeToken<BianGeng>(){});
		if(bean!=null){
			((TSTPageProcessor) task).setDataBG(bean.getBG(regNumber),regNumber);
		}
	}
}

