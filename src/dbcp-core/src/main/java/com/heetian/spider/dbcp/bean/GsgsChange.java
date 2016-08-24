package com.heetian.spider.dbcp.bean;

/**
 * 登记信息，变更信息表
 * 
 * @author tst
 *
 */
public class GsgsChange {
	/**
	 * 注册号
	 */
	private String regNumber;
	/**
	 * 变更事项,alterations
	 */
	private String type;
	/**
	 * 变更前内容，beginContent
	 */
	private String chs;
	/**
	 * 变更后内容,endContent
	 */
	private String chn;
	/**
	 * 变更日期，date
	 */
	private String date;
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	/**
	 * 变更事项,alterations
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 变更事项,alterations
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 变更前内容，beginContent
	 * @return
	 */
	public String getChs() {
		return chs;
	}
	/**
	 * 变更前内容，beginContent
	 * @param chs
	 */
	public void setChs(String chs) {
		this.chs = chs;
	}
	/**
	 * 变更后内容,endContent
	 * @return
	 */
	public String getChn() {
		return chn;
	}
	/**
	 * 变更后内容,endContent
	 * @param chn
	 */
	public void setChn(String chn) {
		this.chn = chn;
	}
	/**
	 * 变更日期，date
	 * @return
	 */
	public String getDate() {
		return date;
	}
	/**
	 * 变更日期，date
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
