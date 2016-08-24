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
			gdxq.setName(zyb.getCzmc());
			gdxq.setInvType(zyb.getTzrlxmc());
			gdxq.setSd(zyb.getRjczrq());
			gdxq.setSmn(zyb.getRjcze());
			gdxq.setSfrm(zyb.getRjczfsmc());
			gdxq.setPd(zyb.getSjczrq());
			gdxq.setPmn(zyb.getSjcze());
			gdxq.setPfrm(zyb.getSjczfsmc());
			gdxq.setUuid(uuid);
			gdxqs.add(gdxq);
		}
		if(gdxqs.size()<=0)
			return null;
		return gdxqs;
	}
}

class GdxxData{
	/*sjczfsmc:null;
	tzrlxmc:"法人股东";
	rjczfsmc:"货币                                                        ";
	sjczrq:null;
	rjcze:"12000 万元人民币";
	sjcze:null;
	czmc:"贵州茅台酒厂（集团）保健酒业有限公司";
	rjczrq:"2013年5月6日";*/
	private String sjczfsmc;
	private String tzrlxmc;
	private String rjczfsmc;
	private String sjczrq;
	private String rjcze;
	private String sjcze;
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
}