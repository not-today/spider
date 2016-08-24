package com.heetian.spider.dbcp.bean;

/**
 * 登记信息，分支机构信息表
 * 
 * @author tst
 *
 */
public class GsgsBranch {
	/**
	 * 注册号
	 */
	private String regNumber;
	/**
	 * 名称，brname
	 */
	private String name;
	/**
	 * 登记机关,regorg
	 */
	private String gov;
	/**
	 * 分支结构注册号，regno
	 */
	private String rgc;
	/**
	 * 组织结构代码
	 */
	private String osc;
	/**
	 * 全国统一社会信用代码
	 */
	private String nsc;
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	/**
	 * 名称，brname
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 名称，brname
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 登记机关,regorg
	 * @return
	 */
	public String getGov() {
		return gov;
	}
	/**
	 * 登记机关,regorg
	 * @param gov
	 */
	public void setGov(String gov) {
		this.gov = gov;
	}
	/**
	 * 分支结构注册号，regno
	 * @return
	 */
	public String getRgc() {
		return rgc;
	}
	/**
	 * 分支结构注册号，regno
	 * @param rgc
	 */
	public void setRgc(String rgc) {
		this.rgc = rgc;
	}
	/**
	 * 组织结构代码
	 * @return
	 */
	public String getOsc() {
		return osc;
	}
	/**
	 * 组织结构代码
	 * @param osc
	 */
	public void setOsc(String osc) {
		this.osc = osc;
	}
	/**
	 * 全国统一社会信用代码
	 * @return
	 */
	public String getNsc() {
		return nsc;
	}
	/**
	 * 全国统一社会信用代码
	 * @param nsc
	 */
	public void setNsc(String nsc) {
		this.nsc = nsc;
	}
	
}
