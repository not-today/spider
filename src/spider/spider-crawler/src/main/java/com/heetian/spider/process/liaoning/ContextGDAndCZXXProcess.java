package com.heetian.spider.process.liaoning;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForJson;
/**
 * http://qyxy.baic.gov.cn/gjjbj/gjjQueryCreditAction!tzrFrame.dhtml?ent_id=81395C2BBE684309BCD1829473D90F6F&entName=&clear=true&timeStamp=1433907595036
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends LiaoNingProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/saicpub/entPublicitySC/entPublicityDC/getGsgsTzrxxPojoList.action");
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String jsonStr = page.getRawText();
		if(jsonStr!=null&&!"".equals(jsonStr)&&!"'[]'".equals(jsonStr)&&!"[]".equals(jsonStr)&&!"null".equals(jsonStr)){
			List<TZCZXX> tzrxxPojos = AnalysisForJson.jsonToObject(jsonStr,new TypeToken<List<TZCZXX>>(){});
			if(tzrxxPojos!=null&&tzrxxPojos.size()>0){
				String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
				String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
				List<GsgsShareholderDetail> gdxxxxs = new ArrayList<GsgsShareholderDetail>();
				for(TZCZXX tzrxxPojo:tzrxxPojos){
					List<TRegTzrrjxxList> tRegTzrrjxxList = tzrxxPojo.gettRegTzrrjxxList();
					List<TRegTzrsjxxList> tRegTzrsjxxList = tzrxxPojo.gettRegTzrsjxxList();
					boolean shijiaoflag = tRegTzrrjxxList!=null&&tRegTzrrjxxList.size()>0;
					boolean renjiaoflag = tRegTzrsjxxList!=null&&tRegTzrsjxxList.size()>0;
					if(shijiaoflag&&renjiaoflag){
						int length = tRegTzrrjxxList.size()>=tRegTzrsjxxList.size()?tRegTzrrjxxList.size():tRegTzrsjxxList.size();
						for(int x=0;x<length;x++){
							GsgsShareholderDetail gdxx = getGdxq(tzrxxPojo.gettRegTzrxx(),uuid,regNumber);
							if(x<tRegTzrsjxxList.size()){
								TRegTzrsjxxList tRegTzrsjxx = tRegTzrsjxxList.get(x);
								gdxx.setPmn(tRegTzrsjxx.getAcconam());
								gdxx.setPd(tRegTzrsjxx.getCondate());
								gdxx.setPfrm(tRegTzrsjxx.getConformName());
							}
							if(x<tRegTzrrjxxList.size()){
								TRegTzrrjxxList tRegTzrrjxx = tRegTzrrjxxList.get(x);
								gdxx.setSmn(tRegTzrrjxx.getSubconam());
								gdxx.setSd(tRegTzrrjxx.getCondate());
								gdxx.setSfrm(tRegTzrrjxx.getConformName());
							}
							gdxxxxs.add(gdxx);
						}
					}else{
						if(shijiaoflag){
							for(TRegTzrrjxxList tRegTzrrjxx:tRegTzrrjxxList){
								GsgsShareholderDetail gdxx = getGdxq(tzrxxPojo.gettRegTzrxx(),uuid,regNumber);
								gdxx.setSmn(tRegTzrrjxx.getSubconam());
								gdxx.setSd(tRegTzrrjxx.getCondate());
								gdxx.setSfrm(tRegTzrrjxx.getConformName());
								gdxxxxs.add(gdxx);
							}
						}
						if(renjiaoflag){
							for(TRegTzrsjxxList tRegTzrsjxx:tRegTzrsjxxList){
								GsgsShareholderDetail gdxx = getGdxq(tzrxxPojo.gettRegTzrxx(),uuid,regNumber);
								gdxx.setPmn(tRegTzrsjxx.getAcconam());
								gdxx.setPd(tRegTzrsjxx.getCondate());
								gdxx.setPfrm(tRegTzrsjxx.getConformName());
								gdxxxxs.add(gdxx);
							}
						}
					}
				}
				((TSTPageProcessor) task).setDataGDXQ(gdxxxxs,uuid,regNumber);
			}
		}
	}
	private GsgsShareholderDetail getGdxq(TRegTzrxx tRegTzrxx,String uuid,String regNumber) {
		GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
		gdxx.setUuid(uuid);
		gdxx.setRegNumber(regNumber);
		gdxx.setName(tRegTzrxx.getInv());
		gdxx.setStmn(tRegTzrxx.getLiacconam());
		gdxx.setPtmn(tRegTzrxx.getLisubconam());
		return gdxx;
	}
}
