package com.heetian.spider.dbcp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 登记信息，股东信息表
 * 
 * @author tst
 *
 */
public class GsgsShareholder {
	/**
	 * 注册号
	 */
	private String regNumber;
	/**
	 * 股东名字,shareholder
	 */
	private String name;
	/**
	 * 股东类型，type
	 */
	private String type;
	/**
	 * 证件类型，cardType
	 */
	private String crdt;
	/**
	 * 证件号,cardId
	 */
	private String crdc;
	/**
	 * 投资方式
	 */
	private String ivtf;
	/**
	 * 股东详情标记
	 */
	private String uuid;
	/**
	 * 股东详情
	 */
	private List<GsgsShareholderDetail> contributives = new ArrayList<GsgsShareholderDetail>();
	
	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	/**
	 * 股东名字,shareholder
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 股东名字,shareholder
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 股东类型，type
	 * @return
	 */
	public String getType() {
		return type;
	}
	/**
	 * 股东类型，type
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 证件类型，cardType
	 * @return
	 */
	public String getCrdt() {
		return crdt;
	}
	/**
	 * 证件类型，cardType
	 * @param crdt
	 */
	public void setCrdt(String crdt) {
		this.crdt = crdt;
	}
	/**
	 * 证件号,cardId
	 * @return
	 */
	public String getCrdc() {
		return crdc;
	}
	/**
	 * 证件号,cardId
	 * @param crdc
	 */
	public void setCrdc(String crdc) {
		this.crdc = crdc;
	}
	/**
	 * 投资方式
	 * @return
	 */
	public String getIvtf() {
		return ivtf;
	}
	/**
	 * 投资方式
	 * @param ivtf
	 */
	public void setIvtf(String ivtf) {
		this.ivtf = ivtf;
	}
	/**
	 * 股东详情标记
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * 股东详情标记
	 * @param uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 股东详情
	 * @return
	 */
	public List<GsgsShareholderDetail> getContributives() {
		return contributives;
	}
	/**
	 * 股东详情
	 * @param contributives
	 */
	public void setContributives(List<GsgsShareholderDetail> contributives) {
		if(contributives==null||contributives.size()<=0)
			return;
		this.contributives.addAll(contributives);
	}
}
