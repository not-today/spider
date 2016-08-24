package com.heetian.spider.dbcp.bean;

import java.io.Serializable;

public enum ProxyStatus  implements Serializable{
	/**
	 * 代理可用状态或稳定型
	 */
	YSE("代理可用状态或稳定型","1"),
	/**
	 * 代理不可用状态或不稳定型
	 */
	NO("代理不可用状态或不稳定型","0");
	private static final long serialVersionUID = -720169144079771042L;
	private String description;
	private String index;
	public String getDescription() {
		return description;
	}
	public String getIndex() {
		return index;
	}
	
	public void setIndex(String index) {
		this.index = index;
	}
	private ProxyStatus(String description, String index) {
		this.description = description;
		this.index = index;
	}
}
