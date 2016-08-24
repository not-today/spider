package com.heetian.spider.process.jiangxi;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGDAndCZXXProcess extends JiangXiProcessHandlePrepare {
	public ContextGDAndCZXXProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/einvperson/queryInfo.do");
	}
	public String getcontent(String tr){
		List<String> tds = AnalysisForTable.getTrText(tr);
		return AnalysisForTable.getTdText(tds.get(1));
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		List<String> trs_inv = page.getHtml().xpath("//div[@class='zmxx-content']/table[1]//tr").all();
		String inv = null;
		String renjiaoe = null;
		String shijiaoe = null;
		if(trs_inv!=null&&trs_inv.size()>=3){
			inv = getcontent(trs_inv.get(0));
			renjiaoe = getcontent(trs_inv.get(1));
			shijiaoe = getcontent(trs_inv.get(2));
		}
			
		List<String> trs_rj = page.getHtml().xpath("//div[@class='zmxx-content']/table[2]//tr").all();
		if(trs_rj==null||trs_rj.size()<=2){
			trs_rj = new ArrayList<>();
		}else{
			trs_rj.remove(0);
			trs_rj.remove(0);
		}
		List<String> trs_sj = page.getHtml().xpath("//div[@class='zmxx-content']/table[3]//tr").all();
		if(trs_sj==null||trs_sj.size()<=2){
			trs_sj = new ArrayList<>();
		}else{
			trs_sj.remove(0);
			trs_sj.remove(0);
		}
		int x = -1;
		if(trs_rj.size()>=trs_sj.size()){
			x =trs_rj.size();
		}else{
			x =trs_sj.size();
		}
		List<GsgsShareholderDetail> shds = new ArrayList<>();
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String entname = (String) page.getRequest().getExtra(ENTNAME);
		String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
		for(int y=0;y<x;x++){
			GsgsShareholderDetail shd = new GsgsShareholderDetail();
			shd.setName(inv);
			shd.setPtmn(shijiaoe);
			shd.setStmn(renjiaoe);
			shd.setRegNumber(regNumber);
			shd.setUuid(uuid);
			if(trs_rj.size()>=y+1){
				String tr_rj = trs_rj.get(y);
				try {
					process(tr_rj, shd, "rj");
				} catch (Exception e) {
					logger.warn("处理股东详情出错：reg["+regNumber+"]ent["+entname+"]",e);
				}
			}
			if(trs_sj.size()>=y+1){
				String tr_sj = trs_sj.get(y);
				try {
					process(tr_sj, shd, "sj");
				} catch (Exception e) {
					logger.warn("处理股东详情出错：reg["+regNumber+"]ent["+entname+"]",e);
				}
			}
			if(shd.check())
				shds.add(shd);
		}
		if(shds.size()>0)
			((TSTPageProcessor) task).setDataGDXQ(shds,uuid,regNumber);
	}
	public static void process(String trs,GsgsShareholderDetail shd,String type) {
		if(trs==null||"".equals(trs.trim())||trs.contains("暂无数据")){
			return;
		}
		List<String> tds = AnalysisForTable.getTrText(trs);
		if(tds==null)
			return;
		if(tds.size()==3){
			if("rj".equals(type)){
				shd.setSfrm(AnalysisForTable.getTdText(tds.get(3)));
				shd.setSmn(AnalysisForTable.getTdText(tds.get(4)));
				shd.setSd(AnalysisForTable.getTdText(tds.get(5)));
				
			}else if("sj".equals(type)){
				shd.setPfrm(AnalysisForTable.getTdText(tds.get(6)));
				shd.setPmn(AnalysisForTable.getTdText(tds.get(7)));
				shd.setPd(AnalysisForTable.getTdText(tds.get(8)));
			}
			
		}else{
			//判断是否有其他情况
			throw new IllegalArgumentException("股东详情信息tr中的td数量["+tds.size()+"]没有对应的处理办法，而此条tr数据没有入库："+tds.toString());
		}
	}
}
