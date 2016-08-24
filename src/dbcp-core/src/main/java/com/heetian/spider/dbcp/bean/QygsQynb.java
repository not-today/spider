package com.heetian.spider.dbcp.bean;

import java.util.List;

/**
 * 企业年报
 * @author tst
 *
 */
public class QygsQynb {
	/**
	 * 报送年度
	 */
	private String subDate;
	/**
	 * 发布日期
	 */
	private String pubDate;
	/**
	 * 详情关联字段
	 */
	private String uuid;
	private QygsQynbBasic basic;
	private QygsQynbAssets assets;
	private List<QygsQynbContribution> contributions;
	private List<QygsQynbEdit> edits;
	private List<QygsQynbGua> guas;
	private List<QygsQynbInvestment> investments;
	private List<QygsQynbStock> stocks;
	private List<QygsQynbWebInfo> webinfos;
	public String getSubDate() {
		return subDate;
	}
	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public QygsQynbAssets getAssets() {
		return assets;
	}
	public void setAssets(QygsQynbAssets assets) {
		this.assets = assets;
	}
	public QygsQynbBasic getBasic() {
		return basic;
	}
	public void setBasic(QygsQynbBasic basic) {
		this.basic = basic;
	}
	public List<QygsQynbContribution> getContributions() {
		return contributions;
	}
	public void setContributions(List<QygsQynbContribution> contributions) {
		this.contributions = contributions;
	}
	public List<QygsQynbEdit> getEdits() {
		return edits;
	}
	public void setEdits(List<QygsQynbEdit> edits) {
		this.edits = edits;
	}
	public List<QygsQynbGua> getGuas() {
		return guas;
	}
	public void setGuas(List<QygsQynbGua> guas) {
		this.guas = guas;
	}
	public List<QygsQynbInvestment> getInvestments() {
		return investments;
	}
	public void setInvestments(List<QygsQynbInvestment> investments) {
		this.investments = investments;
	}
	public List<QygsQynbStock> getStocks() {
		return stocks;
	}
	public void setStocks(List<QygsQynbStock> stocks) {
		this.stocks = stocks;
	}
	public List<QygsQynbWebInfo> getWebinfos() {
		return webinfos;
	}
	public void setWebinfos(List<QygsQynbWebInfo> webinfos) {
		this.webinfos = webinfos;
	}
}
