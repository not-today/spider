package com.heetian.spider.process.jiangxi;

import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.utils.AnalysisForTable;

public class BGBean {
	private String total;//7,
	private List<BGBeanSub> data;//
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<BGBeanSub> getData() {
		return data;
	}
	public void setData(List<BGBeanSub> data) {
		this.data = data;
	}
}
class BGBeanSub{
	private String ALTITEM_CN;//主要人员变更(董事会成员、监事、经理变更),
	private String ALTBE;//姓名;//凌兴国,职务;//董事;姓名;//衷俊华,职务;//董事;姓名;//吴海南,职务;//监事;姓名;//章健,职务;//监事;姓名;//陈东红,职务;//董事;姓名;//黄平辉,职务;//董事;姓名;//闻俊阳,职务;//监事;姓名;//朱毅,职务;//董事长;,
	private String ALTAF;//姓名;//吴海南,职务;//监事;姓名;//章健,职务;//监事;姓名;//衷俊华,职务;//董事长;姓名;//凌兴国,职务;//董事;姓名;//陈东红,职务;//董事;姓名;//黄平辉,职务;//董事;姓名;//闻俊阳,职务;//监事;,
	private String ALTDATE;//2016年03月28日,
	private String ROWNUM_;//1
	public GsgsChange toChange(String regnumber){
		return AnalysisForTable.createChangeInfo(regnumber, ALTITEM_CN, ALTBE, ALTAF, ALTDATE);
	}
	public String getALTITEM_CN() {
		return ALTITEM_CN;
	}
	public void setALTITEM_CN(String aLTITEM_CN) {
		ALTITEM_CN = aLTITEM_CN;
	}
	public String getALTBE() {
		return ALTBE;
	}
	public void setALTBE(String aLTBE) {
		ALTBE = aLTBE;
	}
	public String getALTAF() {
		return ALTAF;
	}
	public void setALTAF(String aLTAF) {
		ALTAF = aLTAF;
	}
	public String getALTDATE() {
		return ALTDATE;
	}
	public void setALTDATE(String aLTDATE) {
		ALTDATE = aLTDATE;
	}
	public String getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(String rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

}