package com.heetian.spider.dbcp.bean;
/**
 * 企业年报_对外提供保证担保信息
 * @author tst
 *
 */
public class QygsQynbGua {
	/**
	 * 债权人
	 */
	private String creditor;
	/**
	 * 债务人
	 */
	private String obligor;
	/**
	 * 主债权种类
	 */
	private String type;
	/**
	 * 主债权数额
	 */
	private String money;
	/**
	 * 履行债务的期限
	 */
	private String performDate;
	/**
	 * 保证的期间
	 */
	private String ensureDate;
	/**
	 * 保证的方式
	 */
	private String method;
	/**
	 * 详情关联字段
	 */
	private String uuid;
	public String getCreditor() {
		return creditor;
	}
	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}
	public String getObligor() {
		return obligor;
	}
	public void setObligor(String obligor) {
		this.obligor = obligor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getPerformDate() {
		return performDate;
	}
	public void setPerformDate(String performDate) {
		this.performDate = performDate;
	}
	public String getEnsureDate() {
		return ensureDate;
	}
	public void setEnsureDate(String ensureDate) {
		this.ensureDate = ensureDate;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
