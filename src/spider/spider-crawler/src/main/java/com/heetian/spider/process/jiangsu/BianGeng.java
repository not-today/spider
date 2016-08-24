package com.heetian.spider.process.jiangsu;

import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.utils.AnalysisForTable;

public class BianGeng {
	private String dateFormat;
	private List<SubBG> items;
	private String total;
	public List<GsgsChange> getBG(String regNumber) {
		if(items!=null&&items.size()>0){
			List<GsgsChange> tmps = new ArrayList<GsgsChange>();
			for(SubBG item:items){
				tmps.add(AnalysisForTable.createChangeInfo(regNumber, item.getC1(), item.getC2(), item.getC3(), item.getC4()));
			}
			if(tmps.size()>0)
				return tmps;
		}
		return null;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public List<SubBG> getItems() {
		return items;
	}
	public void setItems(List<SubBG> items) {
		this.items = items;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}
class SubBG{
	private String C1;//变更事项
	private String C2;//变更前内容
	private String C3;//变更后内容
	private String C4;//变更日期
	private String RN;//未知
	public String getC1() {
		return C1;
	}
	public void setC1(String c1) {
		C1 = c1;
	}
	public String getC2() {
		return C2;
	}
	public void setC2(String c2) {
		C2 = c2;
	}
	public String getC3() {
		return C3;
	}
	public void setC3(String c3) {
		C3 = c3;
	}
	public String getC4() {
		return C4;
	}
	public void setC4(String c4) {
		C4 = c4;
	}
	public String getRN() {
		return RN;
	}
	public void setRN(String rN) {
		RN = rN;
	}
}