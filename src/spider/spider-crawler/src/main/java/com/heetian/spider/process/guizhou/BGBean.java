package com.heetian.spider.process.guizhou;

import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.utils.AnalysisForTable;

public class BGBean {
	private String successed;
	private List<BgData> data;
	public String getSuccessed() {
		return successed;
	}
	public void setSuccessed(String successed) {
		this.successed = successed;
	}
	public List<BgData> getData() {
		return data;
	}
	public void setData(List<BgData> data) {
		this.data = data;
	}
	public List<GsgsChange> getbg(String regNumber) {
		if(data==null||data.size()<=0)
			return null;
		List<GsgsChange> bgs = new ArrayList<GsgsChange>();
		for(BgData bgdata :data){
			GsgsChange bg = AnalysisForTable.createChangeInfo(regNumber, bgdata.getBcsxmc(), bgdata.getBcnr(), bgdata.getBghnr(), bgdata.getHzrq());
			bgs.add(bg);
		}
		return bgs;
	}
}

class BgData{
	/*rownum:1;
	bcsxmc:"行业代码变更";
	hzrq:"2015年4月23日";
	bcnr:"F5226";
	bghnr:"F5226";*/
	private String rownum;
	private String bcsxmc;
	private String hzrq;
	private String bcnr;
	private String bghnr;
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getBcsxmc() {
		return bcsxmc;
	}
	public void setBcsxmc(String bcsxmc) {
		this.bcsxmc = bcsxmc;
	}
	public String getHzrq() {
		return hzrq;
	}
	public void setHzrq(String hzrq) {
		this.hzrq = hzrq;
	}
	public String getBcnr() {
		return bcnr;
	}
	public void setBcnr(String bcnr) {
		this.bcnr = bcnr;
	}
	public String getBghnr() {
		return bghnr;
	}
	public void setBghnr(String bghnr) {
		this.bghnr = bghnr;
	}
	
}