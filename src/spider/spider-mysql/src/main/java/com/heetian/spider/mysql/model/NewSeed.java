package com.heetian.spider.mysql.model;

public class NewSeed {
	public static final String COMEFROMTYPE="11315";
	public static final String COMEFROMTYPE_TYC="天眼查";
	public static final String COMEFROMTYPE_HGYX="浩格云信";
	public static final String COMEFROMTYPE_XYSJ="信用世界";
	public static final String COMEFROMTYPE_XYA="信易安";
	private int id;
	private String entname;
	private String website;
	private String industry;
	private String area;
	private String comeFrom;
	
	public NewSeed() {
		super();
	}
	public NewSeed(String entname, String comeFrom) {
		super();
		this.entname = entname;
		this.comeFrom = comeFrom;
	}
	@Override
	public String toString() {
		return "NewSeed [id=" + id + ", entname=" + entname + ", website="
				+ website + ", industry=" + industry + ", area=" + area
				+ ", comeFrom=" + comeFrom + "]";
	}
	public String getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEntname() {
		return entname;
	}
	public void setEntname(String entname) {
		this.entname = entname;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
