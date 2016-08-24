package com.heetian.spider.process.jiangxi;

import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.utils.AnalysisForTable;

public class GDBean {
	private String total;
	private List<GDBeanSub> data;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<GDBeanSub> getData() {
		return data;
	}
	public void setData(List<GDBeanSub> data) {
		this.data = data;
	}
}

class GDBeanSub{
	private String INVID;//36000010406,
	private String INV;//南昌齿轮有限责任公司,
	private String INVTYPE;//20,
	private String CONDATE;//1993年12月29日,
	private String CERNO;//******,
	private String INVTYPE_CN;//自然人股东,
	private String ESTDATE;//1993年12月29日,
	private String ROWNUM_;//1
	private String CERTYPE_CN;
	public GsgsShareholder toSharehold(String regnumber){
		return AnalysisForTable.createShareHolder(regnumber, INVTYPE_CN, INV, CERTYPE_CN, CERNO, null);
	}
	public String getINVID() {
		return INVID;
	}
	public void setINVID(String iNVID) {
		INVID = iNVID;
	}
	public String getINV() {
		return INV;
	}
	public void setINV(String iNV) {
		INV = iNV;
	}
	public String getINVTYPE() {
		return INVTYPE;
	}
	public void setINVTYPE(String iNVTYPE) {
		INVTYPE = iNVTYPE;
	}
	public String getCONDATE() {
		return CONDATE;
	}
	public void setCONDATE(String cONDATE) {
		CONDATE = cONDATE;
	}
	public String getCERNO() {
		return CERNO;
	}
	public void setCERNO(String cERNO) {
		CERNO = cERNO;
	}
	public String getINVTYPE_CN() {
		return INVTYPE_CN;
	}
	public void setINVTYPE_CN(String iNVTYPE_CN) {
		INVTYPE_CN = iNVTYPE_CN;
	}
	public String getESTDATE() {
		return ESTDATE;
	}
	public void setESTDATE(String eSTDATE) {
		ESTDATE = eSTDATE;
	}
	public String getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(String rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getCERTYPE_CN() {
		return CERTYPE_CN;
	}
	public void setCERTYPE_CN(String cERTYPE_CN) {
		CERTYPE_CN = cERTYPE_CN;
	}
}