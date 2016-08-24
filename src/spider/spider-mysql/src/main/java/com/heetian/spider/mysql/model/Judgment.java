package com.heetian.spider.mysql.model;

import com.google.gson.Gson;

public class Judgment {
	/**
	 * 文书ID
	 */
	private String id;
	/**
	 * 裁判要旨段原文
	 */
	private String origleText;
	/**
	 * 案号
	 */
	private String code;
	/**
	 * 案件名称
	 */
	private String title;
	/**
	 * 案件类型
	 */
	private String type;
	/**
	 * 法院名称
	 */
	private String court;
	/**
	 * 年份
	 */
	private String year;
	/**
	 * DocContent
	 */
	private String content;
	/**
	 * 裁判日期
	 */
	private String courtDate;
	/**
	 * 发布日期
	 */
	private String pubDate;
	/**
	 * 审判程序
	 */
	private String courtProceeding;
	
	public String getOrigleText() {
		return origleText;
	}
	public void setOrigleText(String origleText) {
		this.origleText = origleText;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
	}
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCourtDate() {
		return courtDate;
	}
	public void setCourtDate(String courtDate) {
		this.courtDate = courtDate;
	}
	
	
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	
	public String getCourtProceeding() {
		return courtProceeding;
	}
	public void setCourtProceeding(String courtProceeding) {
		this.courtProceeding = courtProceeding;
	}
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	

}
