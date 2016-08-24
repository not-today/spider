package com.heetian.spider.mysql.model;
/**
 * 企业年报_股权变更信息
 * @author tst
 *
 */
public class QygsQynbStock {
	 	 	 	
	/**
	 * 股东
	 */
	private String investor;
	/**
	 * 变更前股权比例
	 */
	private String beforeProportion;
	/**
	 * 变更后股权比例
	 */
	private String afterProportion;
	/**
	 * 股权变更日期
	 */
	private String date;
	/**
	 * 详情关联字段
	 */
	private String uuid;
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getBeforeProportion() {
		return beforeProportion;
	}
	public void setBeforeProportion(String beforeProportion) {
		this.beforeProportion = beforeProportion;
	}
	public String getAfterProportion() {
		return afterProportion;
	}
	public void setAfterProportion(String afterProportion) {
		this.afterProportion = afterProportion;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
