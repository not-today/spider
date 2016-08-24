package com.heetian.spider.manager;

public class MsgBean {
	private String code;
	private String name;
	private String flag;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "MsgBean [code=" + code + ", name=" + name + ", flag=" + flag
				+ "]";
	}
}
