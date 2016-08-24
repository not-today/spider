package com.heetian.spider.process.jiangxi;

import java.util.Iterator;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.tools.MD5Util;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class CompanyURLProcess extends JiangXiProcessHandlePrepare{
	private static final String urls[] = {"/baseinfo/queryenterpriseinfoByRegnore.do?","/einvperson/getqueryeInvPersonService.do?",
		"/gtalterrecoder/getquerygtalterrecoder.do?","/epriperson/queryPerson.do?","/ebrchinfo/getqueryEBrchinfo.do?"};
	public CompanyURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/search/queryenterpriseinfoindex.do");
		setProcessName(processName_list);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		CompanyJiangxi bean = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<CompanyJiangxi>(){});
		List<CompanyJiangxiSub> datas = bean.getData();
		if(datas==null||datas.size()<=0)
			return;
		Iterator<CompanyJiangxiSub> iter = datas.iterator();
		while(iter.hasNext()){
			CompanyJiangxiSub data = iter.next();
			if("2".equals(data.getREGSTATE())||"3".equals(data.getREGSTATE())||"4".equals(data.getREGSTATE())){
				iter.remove();
				continue;
			}
			String pripid = MD5Util.encode(data.getPRIPID(),"UTF-8");
			String zchregno = MD5Util.encode(data.getREGNO(),"UTF-8");
			String regno = zchregno;
			//String url = "/"+data.getErpurl()+"?pripid="+pripid+"&zchregno="+zchregno+"&regno="+regno;
			String url = "pripid="+pripid+"&zchregno="+zchregno+"&regno="+regno;
			String entname = data.getENTNAME();
			String regnumber = data.getUNISCID();
			if(regnumber==null||"".equals(regnumber.trim()))
				regnumber = data.getREGNO();
			if(!TSTUtils.checkIsExitForEnter(task, regnumber, entname)){
				iter.remove();
				continue;
			}
			for(String tmp:urls){
				Request request = builderRequest(builderURL(tmp+url+"&pageIndex=0&pageSize=5000&_="+System.currentTimeMillis(),task.getSite()),Method.GET, regnumber,entname, null);
				page.addTargetRequest(request);
			}
		}
		((TSTPageProcessor)task).setSeedSdP(datas.size());
	}
}
