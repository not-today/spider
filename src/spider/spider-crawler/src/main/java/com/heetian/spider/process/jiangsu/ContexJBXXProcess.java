package com.heetian.spider.process.jiangsu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForJson;
import com.heetian.spider.utils.TSTUtils;
/**
 * 
 * @author tst
 *宝慧    宝强    苏宁电器集团有限公司     江苏凤凰出版传媒集团有限公司
 */
public class ContexJBXXProcess extends JiangSuProcessHandlePrepare {
	public ContexJBXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/ecipplatform/ciServlet.json");
		setProcessName(processName_containJbxx);
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		for(NameValuePair tmp:nvps){
			if(tmp==null||!"specificQuery".equals(tmp.getName()))
				continue;
			String content = page.getRawText();
			String value = tmp.getValue();
			if(content==null||"".equals(content.trim())||"{}".equals(content.trim())){
				log(page, value);//内容空时，打印日志
				return;
			}
			if("basicInfo".equals(value)){
				resultJBXX(content,regNumber,page,task);
			}else if("investmentInfor".equals(value)){
				resultGDXX(content, regNumber, task, entName, page);
			}else if("investorInfor".equals(value)){
				resultGDCZXX(content, regNumber, page,task);//股东详情
			}else if("personnelInformation".equals(value)){
				resultZYRYXX(content,regNumber,task);
			}else if("branchOfficeInfor".equals(value)){
				resultFZJGXX(content,regNumber,task);
			}else{
				logger.error("获取表数据时，未知表标记信息:"+page.getRawText());
			}
			break;
		}
	}
	private void resultGDCZXX(String content, String regNumber,Page page,PageProcessor task) {
		try {
			GDCZXX bean = AnalysisForJson.jsonToObject(content, new TypeToken<GDCZXX>(){});
			if(bean==null){
				return;
			}
			List<SubGDXXXXRJ> listrenjiao = bean.getListrenjiao();
			List<SubGDXXXXGD> listValue = bean.getListValue();
			List<SubGDXXXXSJ> listshijiao = bean.getListshijiao();
			List<SubGDXXXXCZ> listchuzi = bean.getListchuzi();
			List<GsgsShareholderDetail> gdxqs = new ArrayList<GsgsShareholderDetail>();
			String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
			for(int x=0;x<bean.getMaxSize();x++){
				GsgsShareholderDetail gdxq = new GsgsShareholderDetail();
				gdxq.setRegNumber(regNumber);
				gdxq.setUuid(uuid);
				if(listValue!=null&&listValue.size()>0&&x<listValue.size()){
					SubGDXXXXGD nm = listValue.get(x);
					gdxq.setName(nm.getSTOCK_NAME());
					gdxq.setInvType(nm.getSTOCK_TYPE());
				}
				if(listrenjiao!=null&&listrenjiao.size()>0&&x<listrenjiao.size()){
					SubGDXXXXRJ rj = listrenjiao.get(x);
					gdxq.setSmn(rj.getSHOULD_CAPI());
					gdxq.setSd(rj.getSHOULD_CAPI_DATE());
				}
				if(listshijiao!=null&&listshijiao.size()>0&&x<listshijiao.size()){
					SubGDXXXXSJ sj = listshijiao.get(x);
					gdxq.setPd(sj.getREAL_CAPI_DATE());
					gdxq.setPmn(sj.getREAL_CAPI());
				}
				if(listchuzi!=null&&listchuzi.size()>0&&x<listchuzi.size()){
					SubGDXXXXCZ mt = listchuzi.get(x);
					gdxq.setPfrm(mt.getINVEST_TYPE_NAME());
					gdxq.setSfrm(mt.getINVEST_TYPE_NAME());
				}
				gdxqs.add(gdxq);
			}
			if(gdxqs.size()>0){
				((TSTPageProcessor) task).setDataGDXQ(gdxqs,uuid,regNumber);
			}
		} catch (Exception e) {
			logger.info("股东详情原始json:"+content);
		}
	}
	private void resultGDXX(String content,String regNumber,PageProcessor task,String entName,Page page) {
		GuDong bean = AnalysisForJson.jsonToObject(content, new TypeToken<GuDong>(){});
		if(bean!=null){
			List<GsgsShareholder> shares = bean.getGD(regNumber,entName);
			((TSTPageProcessor) task).setDataGD(shares,regNumber);
			Map<String,NameValuePair[]> parameters = bean.getParameters();
			if(parameters!=null){
				for(Entry<String, NameValuePair[]> nvps:parameters.entrySet()){
					Request request = builderRequest(builderURL(bean.getURL(urlForParamsTailForJiangSu()), task.getSite()), Method.POST, regNumber, entName, nvps.getValue());
					request.putExtra(ProcessHandle.uuid_key, nvps.getKey());	
					page.addTargetRequest(request);
				}
			}
		}
	}
	private void resultFZJGXX(String content,String regNumber,PageProcessor task) {
		FenZhiJiGou bean = AnalysisForJson.jsonToObject(content, new TypeToken<FenZhiJiGou>(){});
		if(bean!=null){
			List<GsgsBranch> brans = bean.getFZJG(regNumber);
			if(brans!=null&&brans.size()>0){
				((TSTPageProcessor) task).setDataFZJG(brans,regNumber);
			}
		}
	}
	private void resultZYRYXX(String content,String regNumber,PageProcessor task) {
		ZhuYaoRenYuan bean = AnalysisForJson.jsonToObject(content, new TypeToken<ZhuYaoRenYuan>(){});
		if(bean!=null){
			List<GsgsMainPersonel> mains = bean.getZYRY(regNumber);
			if(mains!=null&&mains.size()>0){
				((TSTPageProcessor) task).setDataZYRY(mains,regNumber);
			}
		}
	}
	private void resultJBXX(String content,String regNumber,Page page,PageProcessor task) {
		try {
			List<JiBen> bean = AnalysisForJson.jsonToObject(content, new TypeToken<ArrayList<JiBen>>(){});
			if(bean!=null&&bean.size()>0){
				TSTPageProcessor tst = ((TSTPageProcessor) task);
				GsgsRegister tmp = bean.get(0).getJiBen();
				NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
				String urlTmp = TSTUtils.bufferedURL(page.getRequest().getUrl(), httpMethodParam_post, nvps);
				tmp.setUrl(urlTmp);
				tmp.setPvn(tst.getcode());
				tst.setDataJB(tmp,regNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(content);
		}
	}
	private void log(Page page, String value) {
		String msg = null;
		if("basicInfo".equals(value)){
			msg = "基本信息";
		}else if("investmentInfor".equals(value)){
			if(page.getRequest().getUrl().contains("ciDetail=true")){
				msg = "股东详情信息";
			}else{
				msg = "股东信息";
			}
		}else if("personnelInformation".equals(value)){
			msg = "主要人员信息";
		}else if("branchOfficeInfor".equals(value)){
			msg = "分支机构信息";
		}else if("investorInfor".equals(value)){
			msg = "股东详情";
		}else{
			msg = "未知字段";
		}
		logger.info(msg+"[content null];specificQuery的值["+value+"];"+page.getRawText());
	}
}

