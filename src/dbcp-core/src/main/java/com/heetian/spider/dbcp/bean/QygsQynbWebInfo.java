package com.heetian.spider.dbcp.bean;
/**
 * 企业年报_网站或网店信息
 * @author tst
 *
 */
public class QygsQynbWebInfo {
	 	 	
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 网址
	 */
	private String website;
	/**
	 * 详情关联字段
	 */
	private String uuid;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
