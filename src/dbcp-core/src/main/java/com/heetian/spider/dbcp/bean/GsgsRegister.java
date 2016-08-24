package com.heetian.spider.dbcp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 登记信息，基本信息表
 * 
 * @author tst
 *
 */
public class GsgsRegister {
	/**
	 * 注册号,regNumber
	 *cy.setRgc(regNumber);
	 */
	private String rgc;
	/**
	 * 统一信用代码
	 * cy.setNsc(jbxxBean.getCredit());//新增
	 */
	private String nsc;
	/**
	 * 行政区域码
	 * cy.setPvn(jbxxBean.getPro());
	 */
	private String pvn;
	/**
	 * 企业名称
	 * cy.setName(jbxxBean.getName());
	 */
	private String name;
	/**
	 * 企业类型
	 * cy.setType(jbxxBean.getType());
	 */
	private String type;
	/**
	 * 法定代表人
	 * cy.setLp(jbxxBean.getLegalPerson());
	 */
	private String lp;
	/**
	 * 住所
	 * cy.setAddr(jbxxBean.getAddress());
	 */
	private String addr;
	/**
	 * 注册资本
	 * cy.setRc(jbxxBean.getRegisteredCapital());
	 */
	private String rc;
	/**
	 * 成立日期
	 * cy.setFdd(jbxxBean.getEstablishDate());
	 */
	private String fdd;
	/**
	 * 经营期限自
	 * cy.setMds(jbxxBean.getDatebegin());
	 */
	private String mds;
	/**
	 * 经营期限至
	 * cy.setMdn(jbxxBean.getDateend());
	 */
	private String mdn;
	/**
	 * 经营范围
	 * cy.setRgs(jbxxBean.getRanges());
	 */
	private String rgs;
	/**
	 * 登记机关
	 * cy.setGov(jbxxBean.getRegisterOrgan());
	 */
	private String gov;
	/**
	 * 核准日期
	 * cy.setApd(jbxxBean.getApprovalDate());
	 */
	private String apd;
	/**
	 * 登记状态
	 * cy.setStt(jbxxBean.getRegStatus());
	 */
	private String stt;
	/**
	 * 经营者
	 * cy.setMng(jbxxBean.getManager());
	 */
	private String mng;
	/**
	 * 经营场所
	 * cy.setMddr(jbxxBean.getManagerPlace());
	 */
	private String mddr;
	/**
	 * 组成形式
	 * cy.setFrm(jbxxBean.getForm());
	 */
	private String frm;
	/**
	 * 注册日期
	 * cy.setRgd(jbxxBean.getRegDate());
	 */
	private String rgd;
	/**
	 * 吊销日期
	 * cy.setRvd(jbxxBean.getRevokeDate());
	 */
	private String rvd;
	/**
	 * 业务范围
	 * cy.setBns(jbxxBean.getBusinessScope());
	 */
	private String bns;
	/**
	 * 成员出资总额
	 * cy.setTmn(jbxxBean.getTotalAmount());
	 */
	private String tmn;
	/**
	 * 投资人
	 * cy.setIvt(jbxxBean.getInvestor());
	 */
	private String ivt;
	/**
	 * 经营期限自
	 * cy.setBds(jbxxBean.getBusDatebegin());
	 */
	private String bds;
	/**
	 * 经营期限至
	 * cy.setBdn(jbxxBean.getBusDateend());
	 */
	private String bdn;
	/**
	 * 负责人
	 * cy.setCp(jbxxBean.getChargePerson());
	 */
	private String cp;
	/**
	 * 营业场所
	 * cy.setBddr(jbxxBean.getBusPlace());
	 */
	private String bddr;
	/**
	 * 执行事务合伙人
	 * cy.setMp(jbxxBean.getManagingPartner());
	 */
	private String mp;
	/**
	 * 合伙期限自
	 * cy.setPds(jbxxBean.getPartnerDatebegin());
	 */
	private String pds;
	/**
	 * 合伙期限至
	 * cy.setPdn(jbxxBean.getPartnerDateend());
	 */
	private String pdn;
	/**
	 * 主要经营场所
	 * cy.setKddr(jbxxBean.getMainManagerplace());
	 */
	private String kddr;
	/**
	 * 最后修改时间
	 * private String upt;		
	 */
	private long upt;
	/**
	 * 基本信息url
	 * cy.setUrl(jbxxBean.getUrl());
	 */
	private String url;
	/**
	 * 登记表，股东信息
	 */
	private List<GsgsShareholder> shareholders = new ArrayList<GsgsShareholder>();
	/**
	 * 登记表，变更信息
	 */
	private List<GsgsChange> changes = new ArrayList<GsgsChange>();
	/**
	 * 登记表，主要人员信息
	 */
	private List<GsgsMainPersonel> keyPersons = new ArrayList<GsgsMainPersonel>();
	/**
	 * 登记表,分支结构信息
	 */
	private List<GsgsBranch> branchs = new ArrayList<GsgsBranch>();
	/**
	 * 企业年报信息
	 */
	private QygsQynb qygsQynb;
	
	
	
	public GsgsRegister(String rgc, String nsc) throws IllegalArgumentException {
		super();
		if(rgc==null||"".equals(rgc.trim())){
			throw new IllegalArgumentException("初始化该对象时，由于注册号为空而导致GsgsRegister初始化失败");
		}
		if(nsc==null||"".equals(nsc.trim())){
			throw new IllegalArgumentException("初始化该对象时，由于统一信用代码为空而导致GsgsRegister初始化失败");
		}
		this.rgc = rgc;
		this.nsc = nsc;
	}
	/**
	 * 带注册号的构造方法，若参数为空，则抛出不合法参数异常
	 * @param rgc
	 * @throws IllegalArgumentException
	 */
	public GsgsRegister(String rgc) throws IllegalArgumentException{
		super();
		this.rgc = rgc;
	}

	public GsgsRegister() {
		super();
		this.upt = System.currentTimeMillis()/1000;
	}

	/**
	 * 注册号,regNumber
	 * @return
	 */
	public String getRgc() {
		return rgc;
	}
	/**
	 * 注册号,regNumber
	 * @param rgc
	 */
	public void setRgc(String rgc) {
		if(rgc==null||"".equals(rgc.trim())){
			throw new IllegalArgumentException("初始化该对象时，由于注册号为空而导致GsgsRegister初始化失败");
		}
		this.rgc = rgc;
	}
	/**
	 * 统一信用代码
	 * @return
	 */
	public String getNsc() {
		return nsc;
	}
	/**
	 * 统一信用代码
	 * @param nsc
	 */
	public void setNsc(String nsc) {
		if(nsc==null||"".equals(nsc.trim())){
			throw new IllegalArgumentException("初始化该对象时，由于统一信用代码为空而导致GsgsRegister初始化失败");
		}
		this.nsc = nsc;
	}
	/**
	 * * 行政区域码
	 * cy.setPvn(jbxxBean.getPro());
	 * @return
	 */
	public String getPvn() {
		return pvn;
	}
	/**
	 * * 行政区域码
	 * cy.setPvn(jbxxBean.getPro());
	 * @param pvn
	 */
	public void setPvn(String pvn) {
		this.pvn = pvn;
	}
	/**
	 * 企业名称
	 * cy.setName(jbxxBean.getName());
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 企业名称
	 * cy.setName(jbxxBean.getName());
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 企业类型
	 * cy.setType(jbxxBean.getType());
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 企业类型
	 * cy.setType(jbxxBean.getType());
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 法定代表人
	 * cy.setLp(jbxxBean.getLegalPerson());
	 * @return
	 */
	public String getLp() {
		return lp;
	}
	/**
	 * 法定代表人
	 * cy.setLp(jbxxBean.getLegalPerson());
	 * @param lp
	 */
	public void setLp(String lp) {
		this.lp = lp;
	}
	/**
	 * 住所
	 * cy.setAddr(jbxxBean.getAddress());
	 * @return
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * 住所
	 * cy.setAddr(jbxxBean.getAddress());
	 * @param addr
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 * 注册资本
	 * cy.setRc(jbxxBean.getRegisteredCapital());
	 * @return
	 */
	public String getRc() {
		return rc;
	}
	/**
	 * 注册资本
	 * cy.setRc(jbxxBean.getRegisteredCapital());
	 * @param rc
	 */
	public void setRc(String rc) {
		this.rc = rc;
	}
	/**
	 * 成立日期
	 * cy.setFdd(jbxxBean.getEstablishDate());
	 * @return
	 */
	public String getFdd() {
		return fdd;
	}
	/**
	 * 成立日期
	 * cy.setFdd(jbxxBean.getEstablishDate());
	 * @param fdd
	 */
	public void setFdd(String fdd) {
		this.fdd = fdd;
	}
	/**
	 * 经营期限自
	 * cy.setMds(jbxxBean.getDatebegin());
	 * @return
	 */
	public String getMds() {
		return mds;
	}
	/**
	 * 经营期限自
	 * cy.setMds(jbxxBean.getDatebegin());
	 * @param mds
	 */
	public void setMds(String mds) {
		this.mds = mds;
	}
	/**
	 * 经营期限至
	 * cy.setMdn(jbxxBean.getDateend());
	 * @return
	 */
	public String getMdn() {
		return mdn;
	}
	/**
	 * 经营期限至
	 * cy.setMdn(jbxxBean.getDateend());
	 * @param mdn
	 */
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	/**
	 * 经营范围
	 * cy.setRgs(jbxxBean.getRanges());
	 * @return
	 */
	public String getRgs() {
		return rgs;
	}
	/**
	 * 经营范围
	 * cy.setRgs(jbxxBean.getRanges());
	 * @param rgs
	 */
	public void setRgs(String rgs) {
		this.rgs = rgs;
	}
	/**
	 * 登记机关
	 * cy.setGov(jbxxBean.getRegisterOrgan());
	 * @return
	 */
	public String getGov() {
		return gov;
	}
	/**
	 * 登记机关
	 * cy.setGov(jbxxBean.getRegisterOrgan());
	 * @param gov
	 */
	public void setGov(String gov) {
		this.gov = gov;
	}
	/**
	 * 核准日期
	 * cy.setApd(jbxxBean.getApprovalDate());
	 * @return
	 */
	public String getApd() {
		return apd;
	}
	/**
	 * 核准日期
	 * cy.setApd(jbxxBean.getApprovalDate());
	 * @param apd
	 */
	public void setApd(String apd) {
		this.apd = apd;
	}
	/**
	 * 登记状态
	 * cy.setStt(jbxxBean.getRegStatus());
	 * @return
	 */
	public String getStt() {
		return stt;
	}
	/**
	 * 登记状态
	 * cy.setStt(jbxxBean.getRegStatus());
	 * @param stt
	 */
	public void setStt(String stt) {
		this.stt = stt;
	}
	/**
	 * 经营者
	 * cy.setMng(jbxxBean.getManager());
	 * @return
	 */
	public String getMng() {
		return mng;
	}
	/**
	 * 经营者
	 * cy.setMng(jbxxBean.getManager());
	 * @param mng
	 */
	public void setMng(String mng) {
		this.mng = mng;
	}
	/**
	 * 经营场所
	 * cy.setMddr(jbxxBean.getManagerPlace());
	 * @return
	 */
	public String getMddr() {
		return mddr;
	}
	/**
	 * 经营场所
	 * cy.setMddr(jbxxBean.getManagerPlace());
	 * @param mddr
	 */
	public void setMddr(String mddr) {
		this.mddr = mddr;
	}
	/**
	 * 组成形式
	 * cy.setFrm(jbxxBean.getForm());
	 * @return
	 */
	public String getFrm() {
		return frm;
	}
	/**
	 * 组成形式
	 * cy.setFrm(jbxxBean.getForm());
	 * @param frm
	 */
	public void setFrm(String frm) {
		this.frm = frm;
	}
	/**
	 * 注册日期
	 * cy.setRgd(jbxxBean.getRegDate());
	 * @return
	 */
	public String getRgd() {
		return rgd;
	}
	/**
	 * 注册日期
	 * cy.setRgd(jbxxBean.getRegDate());
	 * @param rgd
	 */
	public void setRgd(String rgd) {
		this.rgd = rgd;
	}
	/**
	 * 吊销日期
	 * cy.setRvd(jbxxBean.getRevokeDate());
	 * @return
	 */
	public String getRvd() {
		return rvd;
	}
	/**
	 * 吊销日期
	 * cy.setRvd(jbxxBean.getRevokeDate());
	 * @param rvd
	 */
	public void setRvd(String rvd) {
		this.rvd = rvd;
	}
	/**
	 * 业务范围
	 * cy.setBns(jbxxBean.getBusinessScope());
	 * @return
	 */
	public String getBns() {
		return bns;
	}
	/**
	 * 业务范围
	 * cy.setBns(jbxxBean.getBusinessScope());
	 * @param bns
	 */
	public void setBns(String bns) {
		this.bns = bns;
	}
	/**
	 * 成员出资总额
	 * cy.setTmn(jbxxBean.getTotalAmount());
	 * @return
	 */
	public String getTmn() {
		return tmn;
	}
	/**
	 * 成员出资总额
	 * cy.setTmn(jbxxBean.getTotalAmount());
	 * @param tmn
	 */
	public void setTmn(String tmn) {
		this.tmn = tmn;
	}
	/**
	 * 投资人
	 * cy.setIvt(jbxxBean.getInvestor());
	 * @return
	 */
	public String getIvt() {
		return ivt;
	}
	/**
	 * 投资人
	 * cy.setIvt(jbxxBean.getInvestor());
	 * @param ivt
	 */
	public void setIvt(String ivt) {
		this.ivt = ivt;
	}
	/**
	 * 经营期限自
	 * cy.setBds(jbxxBean.getBusDatebegin());
	 * @return
	 */
	public String getBds() {
		return bds;
	}
	/**
	 * 经营期限自
	 * cy.setBds(jbxxBean.getBusDatebegin());
	 * @param bds
	 */
	public void setBds(String bds) {
		this.bds = bds;
	}
	/**
	 * 经营期限至
	 * cy.setBdn(jbxxBean.getBusDateend());
	 * @return
	 */
	public String getBdn() {
		return bdn;
	}
	/**
	 * 经营期限至
	 * cy.setBdn(jbxxBean.getBusDateend());
	 * @param bdn
	 */
	public void setBdn(String bdn) {
		this.bdn = bdn;
	}
	/**
	 * 负责人
	 * cy.setCp(jbxxBean.getChargePerson());
	 * @return
	 */
	public String getCp() {
		return cp;
	}
	/**
	 * 负责人
	 * cy.setCp(jbxxBean.getChargePerson());
	 * @param cp
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}
	/**
	 * 营业场所
	 * cy.setBddr(jbxxBean.getBusPlace());
	 * @return
	 */
	public String getBddr() {
		return bddr;
	}
	/**
	 * 营业场所
	 * cy.setBddr(jbxxBean.getBusPlace());
	 * @param bddr
	 */
	public void setBddr(String bddr) {
		this.bddr = bddr;
	}
	/**
	 * 执行事务合伙人
	 * cy.setMp(jbxxBean.getManagingPartner());
	 * @return
	 */
	public String getMp() {
		return mp;
	}
	/**
	 * 执行事务合伙人
	 * cy.setMp(jbxxBean.getManagingPartner());
	 * @param mp
	 */
	public void setMp(String mp) {
		this.mp = mp;
	}
	/**
	 * 合伙期限自
	 * cy.setPds(jbxxBean.getPartnerDatebegin());
	 * @return
	 */
	public String getPds() {
		return pds;
	}
	/**
	 * 合伙期限自
	 * cy.setPds(jbxxBean.getPartnerDatebegin());
	 * @param pds
	 */
	public void setPds(String pds) {
		this.pds = pds;
	}
	/**
	 * 合伙期限至
	 * cy.setPdn(jbxxBean.getPartnerDateend());
	 * @return
	 */
	public String getPdn() {
		return pdn;
	}
	/**
	 * 合伙期限至
	 * cy.setPdn(jbxxBean.getPartnerDateend());
	 * @param pdn
	 */
	public void setPdn(String pdn) {
		this.pdn = pdn;
	}
	/**
	 * 主要经营场所
	 * cy.setKddr(jbxxBean.getMainManagerplace());
	 * @return
	 */
	public String getKddr() {
		return kddr;
	}
	/**
	 * 主要经营场所
	 * cy.setKddr(jbxxBean.getMainManagerplace());
	 * @param kddr
	 */
	public void setKddr(String kddr) {
		this.kddr = kddr;
	}
	/**
	 * 最后修改时间
	 * private String upt;	
	 * @return
	 */
	public long getUpt() {
		return upt;
	}
	/**
	 * 最后修改时间
	 * private String upt;	
	 * @param upt
	 */
	public void setUpt(long upt) {
		this.upt = upt/1000;
	}
	/**
	 * 基本信息url
	 * cy.setUrl(jbxxBean.getUrl());
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 基本信息url
	 * cy.setUrl(jbxxBean.getUrl());
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	public List<GsgsShareholder> getShareholders() {
		return shareholders;
	}
	public void setShareholders(List<GsgsShareholder> shareholders) {
		if(shareholders==null||shareholders.size()<=0)
			return;
		this.shareholders.addAll(shareholders);
	}
	public List<GsgsChange> getChanges() {
		return changes;
	}
	public void setChanges(List<GsgsChange> changes) {
		if(changes==null||changes.size()<=0)
			return;
		this.changes.addAll(changes);
	}
	public List<GsgsMainPersonel> getKeyPersons() {
		return keyPersons;
	}
	public void setKeyPersons(List<GsgsMainPersonel> keyPersons) {
		if(keyPersons==null||keyPersons.size()<=0)
			return;
		this.keyPersons.addAll(keyPersons);
	}
	public List<GsgsBranch> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<GsgsBranch> branchs) {
		if(branchs==null||branchs.size()<=0)
			return;
		this.branchs.addAll(branchs);
	}
	public QygsQynb getQygsQynb() {
		return qygsQynb;
	}
	public void setQygsQynb(QygsQynb qygsQynb) {
		this.qygsQynb = qygsQynb;
	}
}
