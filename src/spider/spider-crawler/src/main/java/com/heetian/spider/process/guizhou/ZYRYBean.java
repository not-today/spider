package com.heetian.spider.process.guizhou;

import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.utils.AnalysisForTable;

public class ZYRYBean {
	private String successed;
	private List<ZyryData> data;
	public String getSuccessed() {
		return successed;
	}
	public void setSuccessed(String successed) {
		this.successed = successed;
	}
	public List<ZyryData> getData() {
		return data;
	}
	public void setData(List<ZyryData> data) {
		this.data = data;
	}
	public List<GsgsMainPersonel> getzy(String regNumber) {
		if(data==null||data.size()<=0)
			return null;
		List<GsgsMainPersonel> zyrys = new ArrayList<GsgsMainPersonel>();
		for(ZyryData zyb:data){
			GsgsMainPersonel zyry = AnalysisForTable.createMainPerson(regNumber, zyb.getXm(), zyb.getZwmc());
			zyrys.add(zyry);
		}
		return zyrys;
	}
}

class ZyryData{
	/*zwmc:"董事兼总经理";
	rownum:1;
	xm:"蔡芳新"*/
	private String zwmc;
	private String rownum;
	private String xm;
	public String getZwmc() {
		return zwmc;
	}
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
}