package com.heetian.spider.process.jiangxi;

import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.utils.CheckUtils;

public class JBBean {
	private String ENTTYPE_CN;// 其他有限责任公司,
	private String REGSTATE;// 1,//
	private String PRIPID;// 3600002000041994,//
	private String REGNO;// 360000110003177,
	private String ENTNAME;// 江铃汽车集团财务有限公司,
	private String ENTTYPE;// 1190,//
	private String NAME;// 衷俊华,
	private String ESTDATE;// 1993年12月29日,
	private String DOM;// 苏圃路１１１号,
	private String OPSCOPE;// 对成员单位办理财务和融资顾问、信用鉴证及相关咨询、代理业务；协助成员单位实现交易款项的收付；经批准的保险代理业务；对成员单位提供担保；办理成员单位之间的委托贷款及委托投资；对成员单位办理票据承兑与贴现；办理成员单位之间的内部转账结算及相应的结算、清算方案设计；吸收成员单位的存款；对成员单位办理贷款及融资租赁；从事同业拆借；经批准发行财务公司债券；承销成员单位的企业债券；对金融机构的股权投资；有价证券投资；成员单位产品的消费信贷、买方信贷及融资租赁。（依法须经批准的项目，经相关部门批准后方可开展经营活动）,
	private String REGORG_CN;// 江西省工商行政管理局,
	private String REGSTATE_CN;// 开业,
	private String REGCAP;// 50000,
	private String OPFROM;// 1993年12月29日,
	private String OPTO;// ,
	private String APPRDATE;// 2016年03月28日,
	private String REGCAPCUR_CN;// 人民币,//
	private String UNISCID;// 913600001582899590,
	private String PRIPTYPE;// 1190,//
	private String namelike;// 法定代表人：//

	public GsgsRegister toRegister() {
		GsgsRegister tmp = new GsgsRegister();
		tmp.setAddr(DOM);
		tmp.setApd(APPRDATE);
		//tmp.setBddr("");
		tmp.setBdn(OPTO);
		tmp.setBds(OPFROM);
		//tmp.setBns("");
		//tmp.setCp("");
		tmp.setFdd(ESTDATE);
		//tmp.setFrm("");
		tmp.setGov(REGORG_CN);
		//tmp.setIvt("");
		//tmp.setKddr("");
		tmp.setLp(NAME);
		//tmp.setMddr("");
		//tmp.setMdn("");
		//tmp.setMds("");
		//tmp.setMng("");
		//tmp.setMp("");
		tmp.setName(ENTNAME);
		if(CheckUtils.checkCreditCode(UNISCID))
			tmp.setNsc(UNISCID);
		//tmp.setPdn("");
		//tmp.setPds("");
		//tmp.setPvn("");//
		tmp.setRc(REGCAP);
		tmp.setRgc(REGNO);
		//tmp.setRgd("");
		tmp.setRgs(OPSCOPE);
		//tmp.setRvd("");
		tmp.setStt(REGSTATE_CN);
		//tmp.setTmn("");
		tmp.setType(ENTTYPE_CN);
		tmp.setUpt(System.currentTimeMillis());
		//tmp.setUrl("");
		return tmp;

	}

	public String getENTTYPE_CN() {
		return ENTTYPE_CN;
	}

	public void setENTTYPE_CN(String eNTTYPE_CN) {
		ENTTYPE_CN = eNTTYPE_CN;
	}

	public String getREGSTATE() {
		return REGSTATE;
	}

	public void setREGSTATE(String rEGSTATE) {
		REGSTATE = rEGSTATE;
	}

	public String getPRIPID() {
		return PRIPID;
	}

	public void setPRIPID(String pRIPID) {
		PRIPID = pRIPID;
	}

	public String getREGNO() {
		return REGNO;
	}

	public void setREGNO(String rEGNO) {
		REGNO = rEGNO;
	}

	public String getENTNAME() {
		return ENTNAME;
	}

	public void setENTNAME(String eNTNAME) {
		ENTNAME = eNTNAME;
	}

	public String getENTTYPE() {
		return ENTTYPE;
	}

	public void setENTTYPE(String eNTTYPE) {
		ENTTYPE = eNTTYPE;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
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

	public String getOPSCOPE() {
		return OPSCOPE;
	}

	public void setOPSCOPE(String oPSCOPE) {
		OPSCOPE = oPSCOPE;
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

	public String getREGCAP() {
		return REGCAP;
	}

	public void setREGCAP(String rEGCAP) {
		REGCAP = rEGCAP;
	}

	public String getOPFROM() {
		return OPFROM;
	}

	public void setOPFROM(String oPFROM) {
		OPFROM = oPFROM;
	}

	public String getOPTO() {
		return OPTO;
	}

	public void setOPTO(String oPTO) {
		OPTO = oPTO;
	}

	public String getAPPRDATE() {
		return APPRDATE;
	}

	public void setAPPRDATE(String aPPRDATE) {
		APPRDATE = aPPRDATE;
	}

	public String getREGCAPCUR_CN() {
		return REGCAPCUR_CN;
	}

	public void setREGCAPCUR_CN(String rEGCAPCUR_CN) {
		REGCAPCUR_CN = rEGCAPCUR_CN;
	}

	public String getUNISCID() {
		return UNISCID;
	}

	public void setUNISCID(String uNISCID) {
		UNISCID = uNISCID;
	}

	public String getPRIPTYPE() {
		return PRIPTYPE;
	}

	public void setPRIPTYPE(String pRIPTYPE) {
		PRIPTYPE = pRIPTYPE;
	}

	public String getNamelike() {
		return namelike;
	}

	public void setNamelike(String namelike) {
		this.namelike = namelike;
	}
}
