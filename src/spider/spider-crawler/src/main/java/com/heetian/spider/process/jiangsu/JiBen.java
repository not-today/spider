package com.heetian.spider.process.jiangsu;

import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.utils.CheckUtils;

public class JiBen {
	private String ORG;
	private String ID;
	private String SEQ_ID;
	private String C2;
	private String C1;
	private String C3;
	private String ADMIT_MAIN;
	private String C7;
	private String FARE_PLACE;
	private String C6;
	private String CAPI_TYPE_NAME;
	private String REG_CAPI_DOLLAR;
	private String INVEST_CAPI;
	private String INVEST_CAPI_DOLLAR;
	private String C5;
	private String PARENT_CORP_NAME;
	private String OPER_MAN_ADDR;
	private String C9;
	private String C10;
	private String ABUITEM;
	private String CBUITEM;
	private String C8;
	private String C11;
	private String HEAD_NAME;
	private String FOREIGN_NAME;
	private String SEND_CORP_REG_SITE;
	private String C13;
	private String CORP_OPERATE;
	private String C4;
	private String WRITEOFF_DATE;
	private String C12;
	private String REVOKE_DATE;

	public GsgsRegister getJiBen() {
		GsgsRegister jbxxBean = new GsgsRegister();
		jbxxBean.setRvd(REVOKE_DATE);
		jbxxBean.setRgc(C1.trim());//注册号 {C1}
		if(CheckUtils.checkCreditCode(C1.trim()))
			jbxxBean.setNsc(C1.trim());//新增
		jbxxBean.setName(C2);//名称  {C2}
		jbxxBean.setType(C3);//类型 {C3}
		jbxxBean.setLp(C5);//法定代表人 {C5}
		jbxxBean.setRc(C6);//注册资本 {MONEY_C6}
	/*  jbxxBean.setTotalAmount(value);*/
		jbxxBean.setFdd(C4);//成立日期 {C4}
		jbxxBean.setAddr(C7);//住所 {C7}
		jbxxBean.setMds(C9);//营业期限自 {C9}
		jbxxBean.setMdn(C10);//营业期限至 {C10}
		jbxxBean.setRgs(C8);//经营范围 {C8}
	/*  jbxxBean.setBusinessScope(value);*/
		jbxxBean.setGov(C11);//登记机关 {C11}
		jbxxBean.setApd(C12);//核准日期 {C12}
		jbxxBean.setStt(C13);//登记状态 {C13}
	/*  jbxxBean.setManager(value);*/
		jbxxBean.setMddr(FARE_PLACE);
	/*  jbxxBean.setForm(value);
		jbxxBean.setRegDate(value);
		jbxxBean.setInvestor(value);
		jbxxBean.setBusDatebegin(value);
		jbxxBean.setBusDateend(value);
		jbxxBean.setChargePerson(value);
		jbxxBean.setBusPlace(value);
		jbxxBean.setMainManagerplace(value);
		jbxxBean.setManagingPartner(value);
		jbxxBean.setPartnerDatebegin(value);
		jbxxBean.setPartnerDateend(value);*/
		jbxxBean.setUpt(System.currentTimeMillis());
		if(C1==null||"".equals(C1)||C2==null||"".equals(C2))
			return null;
		return jbxxBean;
	}

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

	public String getC3() {
		return C3;
	}

	public void setC3(String c3) {
		C3 = c3;
	}

	public String getADMIT_MAIN() {
		return ADMIT_MAIN;
	}

	public void setADMIT_MAIN(String aDMIT_MAIN) {
		ADMIT_MAIN = aDMIT_MAIN;
	}

	public String getC7() {
		return C7;
	}

	public void setC7(String c7) {
		C7 = c7;
	}

	public String getFARE_PLACE() {
		return FARE_PLACE;
	}

	public void setFARE_PLACE(String fARE_PLACE) {
		FARE_PLACE = fARE_PLACE;
	}

	public String getC6() {
		return C6;
	}

	public void setC6(String c6) {
		C6 = c6;
	}

	public String getCAPI_TYPE_NAME() {
		return CAPI_TYPE_NAME;
	}

	public void setCAPI_TYPE_NAME(String cAPI_TYPE_NAME) {
		CAPI_TYPE_NAME = cAPI_TYPE_NAME;
	}

	public String getREG_CAPI_DOLLAR() {
		return REG_CAPI_DOLLAR;
	}

	public void setREG_CAPI_DOLLAR(String rEG_CAPI_DOLLAR) {
		REG_CAPI_DOLLAR = rEG_CAPI_DOLLAR;
	}

	public String getINVEST_CAPI() {
		return INVEST_CAPI;
	}

	public void setINVEST_CAPI(String iNVEST_CAPI) {
		INVEST_CAPI = iNVEST_CAPI;
	}

	public String getINVEST_CAPI_DOLLAR() {
		return INVEST_CAPI_DOLLAR;
	}

	public void setINVEST_CAPI_DOLLAR(String iNVEST_CAPI_DOLLAR) {
		INVEST_CAPI_DOLLAR = iNVEST_CAPI_DOLLAR;
	}

	public String getC5() {
		return C5;
	}

	public void setC5(String c5) {
		C5 = c5;
	}

	public String getPARENT_CORP_NAME() {
		return PARENT_CORP_NAME;
	}

	public void setPARENT_CORP_NAME(String pARENT_CORP_NAME) {
		PARENT_CORP_NAME = pARENT_CORP_NAME;
	}

	public String getOPER_MAN_ADDR() {
		return OPER_MAN_ADDR;
	}

	public void setOPER_MAN_ADDR(String oPER_MAN_ADDR) {
		OPER_MAN_ADDR = oPER_MAN_ADDR;
	}

	public String getC9() {
		return C9;
	}

	public void setC9(String c9) {
		C9 = c9;
	}

	public String getC10() {
		return C10;
	}

	public void setC10(String c10) {
		C10 = c10;
	}

	public String getABUITEM() {
		return ABUITEM;
	}

	public void setABUITEM(String aBUITEM) {
		ABUITEM = aBUITEM;
	}

	public String getCBUITEM() {
		return CBUITEM;
	}

	public void setCBUITEM(String cBUITEM) {
		CBUITEM = cBUITEM;
	}

	public String getC8() {
		return C8;
	}

	public void setC8(String c8) {
		C8 = c8;
	}

	public String getC11() {
		return C11;
	}

	public void setC11(String c11) {
		C11 = c11;
	}

	public String getHEAD_NAME() {
		return HEAD_NAME;
	}

	public void setHEAD_NAME(String hEAD_NAME) {
		HEAD_NAME = hEAD_NAME;
	}

	public String getFOREIGN_NAME() {
		return FOREIGN_NAME;
	}

	public void setFOREIGN_NAME(String fOREIGN_NAME) {
		FOREIGN_NAME = fOREIGN_NAME;
	}

	public String getSEND_CORP_REG_SITE() {
		return SEND_CORP_REG_SITE;
	}

	public void setSEND_CORP_REG_SITE(String sEND_CORP_REG_SITE) {
		SEND_CORP_REG_SITE = sEND_CORP_REG_SITE;
	}

	public String getC13() {
		return C13;
	}

	public void setC13(String c13) {
		C13 = c13;
	}

	public String getCORP_OPERATE() {
		return CORP_OPERATE;
	}

	public void setCORP_OPERATE(String cORP_OPERATE) {
		CORP_OPERATE = cORP_OPERATE;
	}

	public String getC4() {
		return C4;
	}

	public void setC4(String c4) {
		C4 = c4;
	}

	public String getWRITEOFF_DATE() {
		return WRITEOFF_DATE;
	}

	public void setWRITEOFF_DATE(String wRITEOFF_DATE) {
		WRITEOFF_DATE = wRITEOFF_DATE;
	}

	public String getC12() {
		return C12;
	}

	public void setC12(String c12) {
		C12 = c12;
	}

	public String getREVOKE_DATE() {
		return REVOKE_DATE;
	}

	public void setREVOKE_DATE(String rEVOKE_DATE) {
		REVOKE_DATE = rEVOKE_DATE;
	};
}
