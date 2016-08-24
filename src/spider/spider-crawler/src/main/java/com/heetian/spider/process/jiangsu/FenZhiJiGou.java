package com.heetian.spider.process.jiangsu;

import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.utils.AnalysisForTable;

public class FenZhiJiGou {
	private String dateFormat;
	private List<SubFZJG> items;
	private String total;
	public List<GsgsBranch> getFZJG(String regNumber) {
		if(items!=null&&items.size()>0){
			List<GsgsBranch> tmps = new ArrayList<GsgsBranch>();
			for(SubFZJG item:items){
				tmps.add(AnalysisForTable.createBranch(regNumber, item.getC1(), item.getC2(), item.getC3()));
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
	public List<SubFZJG> getItems() {
		return items;
	}
	public void setItems(List<SubFZJG> items) {
		this.items = items;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}
class SubFZJG{
/*	
 	{"dateFormat":"yyyy-MM-dd","items":[{"ORG":876,"ID":2212185,"SEQ_ID":1,"CORP_ORG":876,"CORP_ID":134770,"CORP_SEQ_ID":13,"C2":"江苏宝亮科仪有限责任公司南京分公司","C1":"320106000159044","DIST_ECON_KIND":51,"OPER_MAN_NAME":"王亚军","FARE_TERM_START":null,"C3":"南京市工商行政管理局鼓楼分局","CORP_STATUS":null,"RN":1},{"ORG":876,"ID":4042553,"SEQ_ID":1,"CORP_ORG":876,"CORP_ID":134770,"CORP_SEQ_ID":13,"C2":"江苏宝亮科仪有限责任公司栖霞分公司","C1":"320113000249086","DIST_ECON_KIND":51,"OPER_MAN_NAME":"王亚军","FARE_TERM_START":null,"C3":"南京市工商行政管理局栖霞分局","CORP_STATUS":null,"RN":2}],"total":"2"}
 	ORG:876
	ID:2212185
	SEQ_ID:1
	CORP_ORG:876
	CORP_ID:134770
	CORP_SEQ_ID:13
	C2:"江苏宝亮科仪有限责任公司南京分公司"
	C1:"320106000159044"
	DIST_ECON_KIND:51
	OPER_MAN_NAME:"王亚军"
	FARE_TERM_START:null
	C3:"南京市工商行政管理局鼓楼分局"
	CORP_STATUS:null
	RN:1*/
	
	private String ORG;
	private String ID;
	private String SEQ_ID;
	private String CORP_ORG;
	private String CORP_ID;
	private String CORP_SEQ_ID;
	private String C2;
	private String C1;
	private String DIST_ECON_KIND;
	private String OPER_MAN_NAME;
	private String FARE_TERM_START;
	private String C3;
	private String CORP_STATUS;
	private String RN;
	public String getORG() {
		return ORG;
	}
	public void setORG(String oRG) {
		ORG = oRG;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getSEQ_ID() {
		return SEQ_ID;
	}
	public void setSEQ_ID(String sEQ_ID) {
		SEQ_ID = sEQ_ID;
	}
	public String getCORP_ORG() {
		return CORP_ORG;
	}
	public void setCORP_ORG(String cORP_ORG) {
		CORP_ORG = cORP_ORG;
	}
	public String getCORP_ID() {
		return CORP_ID;
	}
	public void setCORP_ID(String cORP_ID) {
		CORP_ID = cORP_ID;
	}
	public String getCORP_SEQ_ID() {
		return CORP_SEQ_ID;
	}
	public void setCORP_SEQ_ID(String cORP_SEQ_ID) {
		CORP_SEQ_ID = cORP_SEQ_ID;
	}
	public String getC2() {
		return C2;
	}
	public void setC2(String c2) {
		C2 = c2;
	}
	public String getC1() {
		return C1;
	}
	public void setC1(String c1) {
		C1 = c1;
	}
	public String getDIST_ECON_KIND() {
		return DIST_ECON_KIND;
	}
	public void setDIST_ECON_KIND(String dIST_ECON_KIND) {
		DIST_ECON_KIND = dIST_ECON_KIND;
	}
	public String getOPER_MAN_NAME() {
		return OPER_MAN_NAME;
	}
	public void setOPER_MAN_NAME(String oPER_MAN_NAME) {
		OPER_MAN_NAME = oPER_MAN_NAME;
	}
	public String getFARE_TERM_START() {
		return FARE_TERM_START;
	}
	public void setFARE_TERM_START(String fARE_TERM_START) {
		FARE_TERM_START = fARE_TERM_START;
	}
	public String getC3() {
		return C3;
	}
	public void setC3(String c3) {
		C3 = c3;
	}
	public String getCORP_STATUS() {
		return CORP_STATUS;
	}
	public void setCORP_STATUS(String cORP_STATUS) {
		CORP_STATUS = cORP_STATUS;
	}
	public String getRN() {
		return RN;
	}
	public void setRN(String rN) {
		RN = rN;
	}
	@Override
	public String toString() {
		return "SubFZJG [ORG=" + ORG + ", ID=" + ID + ", SEQ_ID=" + SEQ_ID
				+ ", CORP_ORG=" + CORP_ORG + ", CORP_ID=" + CORP_ID
				+ ", CORP_SEQ_ID=" + CORP_SEQ_ID + ", C2=" + C2 + ", C1=" + C1
				+ ", DIST_ECON_KIND=" + DIST_ECON_KIND + ", OPER_MAN_NAME="
				+ OPER_MAN_NAME + ", FARE_TERM_START=" + FARE_TERM_START
				+ ", C3=" + C3 + ", CORP_STATUS=" + CORP_STATUS + ", RN=" + RN
				+ "]";
	}
}
