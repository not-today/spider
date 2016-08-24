package com.heetian.spider.process.tianjing;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.process.abstractclass.TianJingProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * @author tst
 *
 */
public class Detai2lURLProcess extends TianJingProcessHandlePrepare{
	public Detai2lURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/platform/saic/topInfoClass.json");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String json = page.getRawText();
		if(json!=null&&!"".equals(json.trim())){
			List<OptionData> optiondatas = AnalysisForJson.jsonToObject(json, new TypeToken<List<OptionData>>(){});
			if(optiondatas!=null&&optiondatas.size()>0){
				String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
				String entName = (String) page.getRequest().getExtra(ENTNAME);
				String entId = (String) page.getRequest().getExtra("entId");
				String hasInfo = (String) page.getRequest().getExtra("hasInfo");
				for(OptionData tmp:optiondatas){
					String url = null;
					if("登记信息".equals(tmp.getName())){
						url = tmp.getUrl();
					}else if("备案信息".equals(tmp.getName())){
						url = tmp.getUrl();
					}else{
						
					}
					if(url!=null){
						Request request = builderRequest(builderURL(url,task.getSite()), Method.GET, regNumber, entName, null); 
						request.putExtra("entId", entId);
						request.putExtra("hasInfo", hasInfo);
						page.addTargetRequest(request);
					}
				}
			}
		}
	}
}
