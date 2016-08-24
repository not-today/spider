package com.heetian.spider.process.hainan;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.process.abstractclass.HaiNanProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContextGDXXProcess extends HaiNanProcessHandlePrepare {
	public ContextGDXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/GSpublicity/invInfoPage.html");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		Request tmp = page.getRequest();
		String regNumber = (String) tmp.getExtra(REGNUMBER);
		String entNo = (String) tmp.getExtra("entNo");
		String regOrg = (String) tmp.getExtra("regOrg");
		if(page.getRawText()!=null){
			GDBeanJson beanJson = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<GDBeanJson>(){});
			if(beanJson!=null){
				List<GsgsShareholder> gds = new ArrayList<GsgsShareholder>();
				List<GDContentBean> contentBeans = beanJson.getList();
				if(contentBeans!=null&&contentBeans.size()>0){
					for(GDContentBean contentBean:contentBeans){
						GsgsShareholder gd = AnalysisForTable.createShareHolder(regNumber, contentBean.getInvType(), contentBean.getInv(), contentBean.getCertName(), contentBean.getCertNo(), null);
						gd.setRegNumber(regNumber);
						gds.add(gd);
	                    if(beanJson.getObj().equals("2")){
	                    	String uuid = TSTUtils.uuid();
	                    	gd.setUuid(uuid);
	                    	String url = builderURL("/aiccips/GSpublicity/invInfoDetails.html?invNo="+contentBean.getInvNo()+"&entNo="+entNo+"&regOrg="+regOrg, task.getSite()).replaceAll("\\s", "");
	                    	Request request = builderRequest(url, Method.GET, regNumber, null, null);
	                    	request.putExtra(ProcessHandle.uuid_key, uuid);
	                    	page.addTargetRequest(request);
	                    }
					}
					if(gds.size()>0)
						((TSTPageProcessor) task).setDataGD(gds,regNumber);
				}
			}
		}
	}
}