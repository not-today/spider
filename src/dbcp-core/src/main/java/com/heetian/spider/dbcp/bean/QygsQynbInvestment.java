package com.heetian.spider.dbcp.bean;
/**
 * 企业年报_对外投资信息
 * @author tst
 *
 */
public class QygsQynbInvestment {
	 	
	/**
	 * 投资设立企业或购买股权企业名称
	 */
	private String name;
	/**
	 * 注册号/统一社会信用代码
	 */
	private String reg;
	/**
	 * 详情关联字段
	 */
	private String uuid;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
