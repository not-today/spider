package com.heetian.spider.process.shandong;

public class ZYRYXX {
	//[{"name":"","pripid":"e51249fa-4284-47cb-9c30-9d4acb47a8fb","xzqh":"2200"}]
	//{"name":"姜爱党","pripid":"3c5a44ee-0140-1000-e000-c0cfc0a80101","xzqh":"2200"}
	private String name;
	private String position;
	private String pripid;
	private String recid;
	private String xzqh;
	@Override
	public String toString() {
		return "ZYRYXX [name=" + name + ", position=" + position + ", pripid="
				+ pripid + ", recid=" + recid + ", xzqh=" + xzqh + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPripid() {
		return pripid;
	}
	public void setPripid(String pripid) {
		this.pripid = pripid;
	}
	public String getRecid() {
		return recid;
	}
	public void setRecid(String recid) {
		this.recid = recid;
	}
	public String getXzqh() {
		return xzqh;
	}
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
		
}
