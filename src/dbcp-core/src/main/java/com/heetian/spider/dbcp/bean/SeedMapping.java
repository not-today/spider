package com.heetian.spider.dbcp.bean;

import java.sql.Timestamp;

public class SeedMapping {
	/**
	 * 标识
	 */
	private String uuid;
	/**
	 * 省份code码
	 */
	private String code;
	/**
	 * 种子获取到的企业数
	 */
	private int number = 0;
	/**
	 * 种子是采集完成，0未被采集，1采集完成
	 */
	private String over;
	/**
	 * 种子开始时间
	 */
	private Timestamp begin;
	/**
	 * 种子结束时间
	 */
	private Timestamp end;
	
	public Timestamp getBegin() {
		return begin;
	}
	public void setBegin(Timestamp begin) {
		this.begin = begin;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getOver() {
		return over;
	}
	public void setOver(String over) {
		this.over = over;
	}
}
