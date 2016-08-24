package com.heetian.spider.process.jiangxi;

import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.utils.AnalysisForTable;

public class ZYRYBean {
	private String NAME;//章健,
	private String POSITION_CN;//监事
	public GsgsMainPersonel toMainPerson(String regnumber){
		return AnalysisForTable.createMainPerson(regnumber, NAME, POSITION_CN);
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPOSITION_CN() {
		return POSITION_CN;
	}
	public void setPOSITION_CN(String pOSITION_CN) {
		POSITION_CN = pOSITION_CN;
	}
}
