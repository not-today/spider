package com.heetian.spider.mysql.model;
/**
 * 企业年报_企业基本信息
 * @author tst
 *
 */
public class QygsQynbBasic {
	/**
	 * 注册号/统一社会信用代码
	 */
	private String reg;
	/**
	 * 企业名称
	 */
	private String name;
	/**
	 * 企业联系电话
	 */
	private String tel;
	/**
	 * 邮政编码
	 */
	private String zipCode;
	/**
	 * 企业通信地址
	 */
	private String mailingAddr;
	/**
	 * 企业电子邮箱
	 */
	private String email;
	/**
	 * 有限责任公司本年度是否发生股东股权转让
	 */
	private String makeOver;
	/**
	 * 企业经营状态
	 */
	private String status;
	/**
	 * 是否有网站或网店
	 */
	private String haveWeb;
	/**
	 * 企业是否有投资信息或购买其他公司股权
	 */
	private String invested;
	/**
	 * 从业人数 
	 */
	private String peopleNum;
	/**
	 * 详情关联字段
	 */
	private String uuid;
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getMailingAddr() {
		return mailingAddr;
	}
	public void setMailingAddr(String mailingAddr) {
		this.mailingAddr = mailingAddr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMakeOver() {
		return makeOver;
	}
	public void setMakeOver(String makeOver) {
		this.makeOver = makeOver;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHaveWeb() {
		return haveWeb;
	}
	public void setHaveWeb(String haveWeb) {
		this.haveWeb = haveWeb;
	}
	public String getInvested() {
		return invested;
	}
	public void setInvested(String invested) {
		this.invested = invested;
	}
	public String getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
