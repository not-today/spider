package com.heetian.spider.process.guizhou;

import java.util.List;

public class GDXQBean {
	
	private String successed; 
	private List<GDXQBeanSub> data;
	public String getSuccessed() {
		return successed;
	}
	public void setSuccessed(String successed) {
		this.successed = successed;
	}
	public List<GDXQBeanSub> getData() {
		return data;
	}
	public void setData(List<GDXQBeanSub> data) {
		this.data = data;
	}
}
class GDXQBeanSub{
	/*
	
	 */

	private String sjczfsmc; 
	private String tzrlxmc; 
	private String rjczfsmc; 
	private String sjcze1; 
	private String sjczrq; 
	private String rjcze; 
	private String sjcze; 
	private String rjcze1; 
	private String czmc; 
	private String rjczrq;
	public String getSjczfsmc() {
		return sjczfsmc;
	}
	public void setSjczfsmc(String sjczfsmc) {
		this.sjczfsmc = sjczfsmc;
	}
	public String getTzrlxmc() {
		return tzrlxmc;
	}
	public void setTzrlxmc(String tzrlxmc) {
		this.tzrlxmc = tzrlxmc;
	}
	public String getRjczfsmc() {
		return rjczfsmc;
	}
	public void setRjczfsmc(String rjczfsmc) {
		this.rjczfsmc = rjczfsmc;
	}
	public String getSjcze1() {
		return sjcze1;
	}
	public void setSjcze1(String sjcze1) {
		this.sjcze1 = sjcze1;
	}
	public String getSjczrq() {
		return sjczrq;
	}
	public void setSjczrq(String sjczrq) {
		this.sjczrq = sjczrq;
	}
	public String getRjcze() {
		return rjcze;
	}
	public void setRjcze(String rjcze) {
		this.rjcze = rjcze;
	}
	public String getSjcze() {
		return sjcze;
	}
	public void setSjcze(String sjcze) {
		this.sjcze = sjcze;
	}
	public String getRjcze1() {
		return rjcze1;
	}
	public void setRjcze1(String rjcze1) {
		this.rjcze1 = rjcze1;
	}
	public String getCzmc() {
		return czmc;
	}
	public void setCzmc(String czmc) {
		this.czmc = czmc;
	}
	public String getRjczrq() {
		return rjczrq;
	}
	public void setRjczrq(String rjczrq) {
		this.rjczrq = rjczrq;
	} 
}