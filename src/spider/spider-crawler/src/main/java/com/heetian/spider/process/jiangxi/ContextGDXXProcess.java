package com.heetian.spider.process.jiangxi;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *
 */
public class ContextGDXXProcess extends JiangXiProcessHandlePrepare {
	public ContextGDXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/einvperson/getqueryeInvPersonService.do");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		GDBean bean = AnalysisForJson.jsonToObject(page.getRawText(), new TypeToken<GDBean>(){});
		List<GDBeanSub> datas = bean.getData();
		if(datas==null||datas.size()<=0)
			return;
			//((TSTPageProcessor) task).setDataGD(AnalysisForTable.gdxxHTMLToJObj(this,null,regNumber,entName,task.getSite(),page),regNumber);
		
		String regNumber = (String)page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		List<GsgsShareholder> ss = new ArrayList<>();
		for(GDBeanSub data:datas){
			String uuid = TSTUtils.uuid();
			GsgsShareholder sh = data.toSharehold(regNumber);
			String INVID = data.getINVID();
			if(INVID!=null&&!"".equals(INVID.trim())){
				sh.setUuid(uuid);
				String url = "http://gsxt.jxaic.gov.cn/einvperson/queryInfo.do?invid="+INVID;
				Request request = builderRequest(builderURL(url+"&_="+System.currentTimeMillis(),task.getSite()),Method.GET, regNumber,entName, null);
				request.putExtra(ProcessHandle.uuid_key, uuid);	
				page.addTargetRequest(request);
			}
			/*String date = data.getESTDATE();
			if(datetime >= "2014-2-28"){
				str=str+'<td><a href="javascript:info(\''+getValue(arrData[i].INVID)+'\')">详情</a></td>';
			}*/
			ss.add(sh);
		}
	}
}
/*
 //股东及出资信息详情
function info(id){
	var index=layer.open({
		type: 2,
		title: false,
		area: ['75%', '75%'],
		title: '',
		skin: 'layui-layer-rim', //加上边框
		content: '/einvperson/queryInfo.do?invid='+id,
	});
}
 */
