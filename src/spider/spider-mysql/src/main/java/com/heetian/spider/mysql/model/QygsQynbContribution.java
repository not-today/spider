package com.heetian.spider.mysql.model;
/**
 * 企业年报_股东（发起人）及出资信息
 * @author tst
 *
 */
public class QygsQynbContribution {
	/**
	 * 股东（发起人）
	 */
	private String investor;
	/**
	 * 认缴出资额（万元）
	 */
	private String subsribedMoney;
	/**
	 * 认缴出资时间
	 */
	private String subsribedDate;
	/**
	 * 认缴出资方式
	 */
	private String subsribedMethod;
	/**
	 * 实缴出资额（万元）
	 */
	private String paidMoney;
	/**
	 * 出资时间
	 */
	private String contributionDate;
	/**
	 * 出资方式
	 */
	private String contributionMethod;
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
	public String getSubsribedMoney() {
		return subsribedMoney;
	}
	public void setSubsribedMoney(String subsribedMoney) {
		this.subsribedMoney = subsribedMoney;
	}
	public String getSubsribedDate() {
		return subsribedDate;
	}
	public void setSubsribedDate(String subsribedDate) {
		this.subsribedDate = subsribedDate;
	}
	public String getSubsribedMethod() {
		return subsribedMethod;
	}
	public void setSubsribedMethod(String subsribedMethod) {
		this.subsribedMethod = subsribedMethod;
	}
	public String getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(String paidMoney) {
		this.paidMoney = paidMoney;
	}
	public String getContributionDate() {
		return contributionDate;
	}
	public void setContributionDate(String contributionDate) {
		this.contributionDate = contributionDate;
	}
	public String getContributionMethod() {
		return contributionMethod;
	}
	public void setContributionMethod(String contributionMethod) {
		this.contributionMethod = contributionMethod;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
