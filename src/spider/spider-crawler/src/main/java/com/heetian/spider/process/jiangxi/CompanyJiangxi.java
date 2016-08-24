package com.heetian.spider.process.jiangxi;

import java.util.List;

/*
 {
 "total":50,
 "data":
 	[
 	{"PRIPID":"3600005000039878","PRIPTYPE":"1130","ENTNAME":"江西南方钢铁炉料有限公司","REGNO":"3600002180490","REGORG_CN":"江西省工商行政管理局","REGSTATE_CN":"吊销","ESTDATE":"1998年05月06日","DOM":"江西省南昌市西湖区北京东路３３３号","REGSTATE":"2","NAME":"梁丽珍","ROWNUM":1,"ROWNUM_":1,"erpurl":"stateinfopages/yxgs/companyinfo.jsp","namelike":"法定代表人："},
 	{"PRIPID":"3600005000042389","PRIPTYPE":"1130","ENTNAME":"江西省东南钢铁有限公司","REGNO":"61265551-8","REGORG_CN":"江西省工商行政管理局","REGSTATE_CN":"吊销","ESTDATE":"1996年05月27日","DOM":"江西省南昌市西湖区二经路１６号","REGSTATE":"2","NAME":"裘永刚","ROWNUM":2,"ROWNUM_":2,"erpurl":"stateinfopages/yxgs/companyinfo.jsp","namelike":"法定代表人："},
 	{"PRIPID":"3600002000040952","ISMOVE":"2","PRIPTYPE":"4310","ENTNAME":"南昌钢铁厂鑫昌开发总公司运输公司","REGNO":"360000020024878","REGORG_CN":"江西省工商行政管理局","REGSTATE_CN":"开业","ESTDATE":"1993年07月21日","DOM":"江西省南昌市西湖区钢铁厂内","REGSTATE":"1","NAME":"胡国强","ROWNUM":3,"ROWNUM_":3,"erpurl":"stateinfopages/fgsqyfrfzjgxx/companyinfo.jsp","namelike":"负责人："},
 	{"PRIPID":"3600002000038988","ISMOVE":"2","PRIPTYPE":"4320","ENTNAME":"江西省供销社废钢铁联合集团","REGNO":"360000020016329","REGORG_CN":"江西省工商行政管理局","REGSTATE_CN":"开业","ESTDATE":"1990年07月26日","DOM":"江西省南昌市西湖区省政府大院内","REGSTATE":"1","NAME":"胡斌","ROWNUM":4,"ROWNUM_":4,"erpurl":"stateinfopages/fgsqyfrfzjgxx/companyinfo.jsp","namelike":"负责人："},
 	{
 		"PRIPID":"3600002000040604",
 		"ISMOVE":"2",
 		"PRIPTYPE":"4310",
 		"ENTNAME":"南昌钢铁厂鑫昌开发总公司综合服务分公司",
 		"REGNO":"360000020019758",
 		"REGORG_CN":"江西省工商行政管理局",
 		"REGSTATE_CN":"开业",
 		"ESTDATE":"1993年06月04日",
 		"DOM":"江西省南昌市西湖区罗家集南昌钢铁厂内",
 		"REGSTATE":"1",
 		"NAME":"于宝咏",
 		"ROWNUM":5,
 		"ROWNUM_":5,
 		"erpurl":"stateinfopages/fgsqyfrfzjgxx/companyinfo.jsp",
 		"namelike":"负责人："
 	},
 	{"PRIPID":"3600002000040946","ISMOVE":"2","PRIPTYPE":"4310","ENTNAME":"南昌钢铁厂鑫昌开发总公司建筑安装公司","REGNO":"360000020020617","REGORG_CN":"江西省工商行政管理局","REGSTATE_CN":"开业","ESTDATE":"1993年07月21日","DOM":"江西省南昌市西湖区钢铁厂内","REGSTATE":"1","NAME":"刘一词","ROWNUM":6,"ROWNUM_":6,"erpurl":"stateinfopages/fgsqyfrfzjgxx/companyinfo.jsp","namelike":"负责人："},
 	{"PRIPID":"3600002000040186","ISMOVE":"2","PRIPTYPE":"4310","ENTNAME":"南昌钢铁厂实业开发公司服装加工厂","REGNO":"360000020021259","REGORG_CN":"江西省工商行政管理局","REGSTATE_CN":"开业","ESTDATE":"1989年04月12日","DOM":"江西省南昌市西湖区钢铁厂内","REGSTATE":"1","NAME":"钱世惠","ROWNUM":7,"ROWNUM_":7,"erpurl":"stateinfopages/fgsqyfrfzjgxx/companyinfo.jsp","namelike":"负责人："},
 	{"PRIPID":"36000020100305000009738","ISMOVE":"2","PRIPTYPE":"1130","ENTNAME":"江西省富鸿钢铁贸易有限公司","REGNO":"360000210008508","REGORG_CN":"江西省工商行政管理局","REGSTATE_CN":"开业","ESTDATE":"2010年03月11日","DOM":"江西省南昌市昌东工业园二路","REGSTATE":"1","NAME":"李长兴","ROWNUM":8,"ROWNUM_":8,"erpurl":"stateinfopages/yxgs/companyinfo.jsp","namelike":"法定代表人："},
 	{"PRIPID":"360502001012007061400780","PRIPTYPE":"4120","ENTNAME":"江西省新余钢铁总厂新兴开发总公司实业公司山凤自动化服务部","REGNO":"3605021100482","REGORG_CN":"新余市渝水区市场和质量监督管理局","REGSTATE_CN":"注销","ESTDATE":"1992年12月23日","DOM":"良山镇","REGSTATE":"3","NAME":"龙小明","ROWNUM":9,"ROWNUM_":9,"erpurl":"stateinfopages/ffrqy/companyinfo.jsp","namelike":"负责人："},
 	{"PRIPID":"3605006000001537","ISMOVE":"2","PRIPTYPE":"2130","ENTNAME":"新余钢铁有限责任公司设备材料部","REGNO":"360500120000823","REGORG_CN":"新余市市场和质量监督管理局","REGSTATE_CN":"开业","ESTDATE":"1995年05月04日","DOM":"江西省新余市渝水区新钢冶金路","REGSTATE":"1","NAME":"李同庚","ROWNUM":10,"ROWNUM_":10,"erpurl":"stateinfopages/fgsxx/companyinfo.jsp","namelike":"负责人："}
 	]
 }
 */

public class CompanyJiangxi {
	private String total;
	private List<CompanyJiangxiSub> data;
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<CompanyJiangxiSub> getData() {
		return data;
	}
	public void setData(List<CompanyJiangxiSub> data) {
		this.data = data;
	}
}	
class CompanyJiangxiSub{
	private String PRIPID;//3600002000040604,
	private String ISMOVE;//2,
	private String PRIPTYPE;//4310,
	private String ENTNAME;//南昌钢铁厂鑫昌开发总公司综合服务分公司,
	private String REGNO;//360000020019758,
	private String REGORG_CN;//江西省工商行政管理局,
	private String REGSTATE_CN;//开业,
	private String ESTDATE;//1993年06月04日,
	private String DOM;//江西省南昌市西湖区罗家集南昌钢铁厂内,
	private String REGSTATE;//1,
	private String NAME;//于宝咏,
	private String ROWNUM;//5,
	private String ROWNUM_;//5,
	private String erpurl;//stateinfopages/fgsqyfrfzjgxx/companyinfo.jsp,
	private String namelike;//负责人：
	private String UNISCID;
	public String getPRIPID() {
		return PRIPID;
	}
	public void setPRIPID(String pRIPID) {
		PRIPID = pRIPID;
	}
	public String getISMOVE() {
		return ISMOVE;
	}
	public void setISMOVE(String iSMOVE) {
		ISMOVE = iSMOVE;
	}
	public String getPRIPTYPE() {
		return PRIPTYPE;
	}
	public void setPRIPTYPE(String pRIPTYPE) {
		PRIPTYPE = pRIPTYPE;
	}
	public String getENTNAME() {
		return ENTNAME;
	}
	public void setENTNAME(String eNTNAME) {
		ENTNAME = eNTNAME;
	}
	public String getREGNO() {
		return REGNO;
	}
	public void setREGNO(String rEGNO) {
		REGNO = rEGNO;
	}
	public String getREGORG_CN() {
		return REGORG_CN;
	}
	public void setREGORG_CN(String rEGORG_CN) {
		REGORG_CN = rEGORG_CN;
	}
	public String getREGSTATE_CN() {
		return REGSTATE_CN;
	}
	public void setREGSTATE_CN(String rEGSTATE_CN) {
		REGSTATE_CN = rEGSTATE_CN;
	}
	public String getESTDATE() {
		return ESTDATE;
	}
	public void setESTDATE(String eSTDATE) {
		ESTDATE = eSTDATE;
	}
	public String getDOM() {
		return DOM;
	}
	public void setDOM(String dOM) {
		DOM = dOM;
	}
	public String getREGSTATE() {
		return REGSTATE;
	}
	public void setREGSTATE(String rEGSTATE) {
		REGSTATE = rEGSTATE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getROWNUM() {
		return ROWNUM;
	}
	public void setROWNUM(String rOWNUM) {
		ROWNUM = rOWNUM;
	}
	public String getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(String rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getErpurl() {
		return erpurl;
	}
	public void setErpurl(String erpurl) {
		this.erpurl = erpurl;
	}
	public String getNamelike() {
		return namelike;
	}
	public void setNamelike(String namelike) {
		this.namelike = namelike;
	}
	public String getUNISCID() {
		return UNISCID;
	}
	public void setUNISCID(String uNISCID) {
		UNISCID = uNISCID;
	}
	
}
