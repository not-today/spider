package com.heetian.spider.mysql.model;
/**
 * 企业年报_企业资产状况信息
 * @author tst
 *
 */
public class QygsQynbAssets {
	/**
	 *  资产总额
	 */
	private String assetsTotal;
	/**
	 * 负债总额
	 */
	private String liabilitiesTotal;
	/**
	 * 利润总额
	 */
	private String profitTotal;
	/**
	 * 净利润
	 */
	private String netProfit;
	/**
	 * 所有者权益合计
	 */
	private String ownerInterest;
	/**
	 * 营业总收入 
	 */
	private String businessTotal;
	/**
	 * 营业总收入中主营业务收入
	 */
	private String businessOfMainTotal;
	/**
	 * 纳税总额
	 */
	private String taxes;
	/**
	 * 详情关联字段
	 */
	private String uuid;
	public String getAssetsTotal() {
		return assetsTotal;
	}
	public void setAssetsTotal(String assetsTotal) {
		this.assetsTotal = assetsTotal;
	}
	public String getLiabilitiesTotal() {
		return liabilitiesTotal;
	}
	public void setLiabilitiesTotal(String liabilitiesTotal) {
		this.liabilitiesTotal = liabilitiesTotal;
	}
	public String getProfitTotal() {
		return profitTotal;
	}
	public void setProfitTotal(String profitTotal) {
		this.profitTotal = profitTotal;
	}
	public String getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}
	public String getOwnerInterest() {
		return ownerInterest;
	}
	public void setOwnerInterest(String ownerInterest) {
		this.ownerInterest = ownerInterest;
	}
	public String getBusinessTotal() {
		return businessTotal;
	}
	public void setBusinessTotal(String businessTotal) {
		this.businessTotal = businessTotal;
	}
	public String getBusinessOfMainTotal() {
		return businessOfMainTotal;
	}
	public void setBusinessOfMainTotal(String businessOfMainTotal) {
		this.businessOfMainTotal = businessOfMainTotal;
	}
	public String getTaxes() {
		return taxes;
	}
	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
