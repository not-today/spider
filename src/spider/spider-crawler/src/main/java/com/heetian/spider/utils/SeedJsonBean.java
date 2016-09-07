package com.heetian.spider.utils;

import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsRegister;

public class SeedJsonBean {
	private String id;
	private String seed;
	private String  url;
	private String  pvn;
	private List<GsgsRegister>  results;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSeed() {
		return seed;
	}
	public void setSeed(String seed) {
		this.seed = seed;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPvn() {
		return pvn;
	}
	public void setPvn(String pvn) {
		this.pvn = pvn;
	}
	
	public List<GsgsRegister> getResults() {
		return results;
	}
	public void setResults(List<GsgsRegister> results) {
		this.results = results;
	}
	@Override
	public String toString() {
		return "content[seed=" + seed + ", pvn=" + pvn + ", results=" + results + "]";
	}
	public String toStringSeed() {
		return "content[name=" + seed + ",pvn=" + pvn + "]";
	}
	
}
