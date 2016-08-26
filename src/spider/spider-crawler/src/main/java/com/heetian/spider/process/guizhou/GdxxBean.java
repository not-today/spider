package com.heetian.spider.process.guizhou;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;

import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.process.abstractclass.ProcessHandle;
/**
 * 股东详情，json bean
 * @author tst
 *
 */
public class GdxxBean {
	private String successed;
	private List<GdxxData> data;
	public String getSuccessed() {
		return successed;
	}
	public void setSuccessed(String successed) {
		this.successed = successed;
	}
	public List<GdxxData> getData() {
		return data;
	}
	public void setData(List<GdxxData> data) {
		this.data = data;
	}
	public List<GsgsShareholderDetail> getgdxx(String regNumber, Page page) {
		if(data==null||data.size()<=0)
			return null;
		List<GsgsShareholderDetail> gdxqs = new ArrayList<GsgsShareholderDetail>();
		String uuid = (String) page.getRequest().getExtra(ProcessHandle.uuid_key);
		for(GdxxData zyb:data){
			GsgsShareholderDetail gdxq = new GsgsShareholderDetail();
			gdxq.setRegNumber(regNumber);
			gdxq.setUuid(uuid);
			gdxq.setName(zyb.getCzmc());
			gdxq.setInvType(zyb.getTzrlxmc());
			gdxq.setStmn(zyb.getRjcze1());
			gdxq.setPtmn(zyb.getSjcze1());
			gdxq.setSd(zyb.getRjczrq());
			gdxq.setSmn(zyb.getRjcze());
			gdxq.setSfrm(zyb.getRjczfsmc());
			gdxq.setPd(zyb.getSjczrq());
			gdxq.setPmn(zyb.getSjcze());
			gdxq.setPfrm(zyb.getSjczfsmc());
			gdxqs.add(gdxq);
		}
		if(gdxqs.size()<=0)
			return null;
		return gdxqs;
	}
}

class GdxxData{
	/**
	 * sjczfsmc;货币,
	 */
	private String sjczfsmc; 
	/**
	 * tzrlxmc;自然人股东,
	 */
	private String tzrlxmc;  
	/**
	 * rjczfsmc;货币,
	 */
	private String rjczfsmc;  
	/**
	 * sjcze1;45 万人民币元,
	 */
	private String sjcze1;  
	/**
	 * sjczrq;null,
	 */
	private String sjczrq;  
	/**
	 * rjcze;45 万人民币元,
	 */
	private String rjcze;  
	/**
	 * sjcze;45 万人民币元,
	 */
	private String sjcze;  
	/**
	 * rjcze1;45 万人民币元,
	 */
	private String rjcze1;  
	/**
	 * czmc;李开霞,
	 */
	private String czmc;  
	/**
	 * rjczrq;null
	 */
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
	public String getSjcze1() {
		return sjcze1;
	}
	public void setSjcze1(String sjcze1) {
		this.sjcze1 = sjcze1;
	}
	public String getRjcze1() {
		return rjcze1;
	}
	public void setRjcze1(String rjcze1) {
		this.rjcze1 = rjcze1;
	}
}