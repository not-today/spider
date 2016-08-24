package com.heetian.spider.process.jiangxi;

import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.utils.AnalysisForTable;

public class FZJGBean {
	private String total;//1,
	private List<FZJGBeanSub> data;//
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<FZJGBeanSub> getData() {
		return data;
	}
	public void setData(List<FZJGBeanSub> data) {
		this.data = data;
	}
	
}

class FZJGBeanSub{
	private String REGNO;//3600001227900,
	private String BRNAME;//江铃汽车集团财务有限公司证券交易营业部,
	private String REGORG_CN;//江西省工商行政管理局,
	private String ROWNUM_;//1
	public GsgsBranch toBranch(String regNumber){
		return AnalysisForTable.createBranch(regNumber, REGNO, BRNAME, REGORG_CN);
	}
	public String getREGNO() {
		return REGNO;
	}
	public void setREGNO(String rEGNO) {
		REGNO = rEGNO;
	}
	public String getBRNAME() {
		return BRNAME;
	}
	public void setBRNAME(String bRNAME) {
		BRNAME = bRNAME;
	}
	public String getREGORG_CN() {
		return REGORG_CN;
	}
	public void setREGORG_CN(String rEGORG_CN) {
		REGORG_CN = rEGORG_CN;
	}
	public String getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(String rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
}