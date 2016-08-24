package com.heetian.spider.dbcp.bean;

public class Seed {
	/**
	 * 标识
	 */
	private String uuid;
	/**
	 * 种子名称
	 */
	private String name;
	/**
	 * 关系对象
	 */
	private SeedMapping sm;
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SeedMapping getSm() {
		return sm;
	}
	public void setSm(SeedMapping sm) {
		this.sm = sm;
	}
}
