package com.heetian.spider.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.heetian.spider.component.SeedStatusEnum;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.Seed;
import com.heetian.spider.dbcp.bean.SeedMapping;

public class BufferedSeed {
	/**
	 * 关键字失败seedFailNumber次数小于指定值，则种子添加到集合中，等待下一次采集，若超过指定值，则种子丢弃
	 */
	public static final int seedFailNumber = 20;
	/**
	 * 存储数据集合
	 */
	private Map<String, BufferedGsgsRegister> enters = new Hashtable<String, BufferedGsgsRegister>();
	/**
	 * 存放关键字获取到的企业注册码，并且已经存入数据库。当一个种子失败但有部分数据已经入库，下一次种子重新抓取，已经入库的一部分数据在这个集合中能够找到
	 */
	private List<String> successRegs = new ArrayList<String>();
	/**
	 * 关键字失败多少次
	 */
	private int failNumber = 0;
	private SeedStatusEnum status = SeedStatusEnum.update;
	private Proxy proxy;
	private Seed seed;
	private SeedJsonBean origin;
	
	public SeedJsonBean getOrigin() {
		return origin;
	}

	public void setOrigin(SeedJsonBean origin) {
		this.origin = origin;
	}

	public BufferedSeed(Seed seed) {
		this.seed = seed;
		this.seed.setSm(new SeedMapping());
	}
	
	public Map<String, BufferedGsgsRegister> getEnters() {
		return enters;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	public void addSucessReg(String regnumber) {
		if (regnumber == null || "".equals(regnumber))
			return;
		if (!isContainSucessReg(regnumber))
			successRegs.add(regnumber);
	}

	public boolean isContainSucessReg(String regnumber) {
		if (successRegs.contains(regnumber))
			return true;
		return false;
	}

	/**
	 * 返回失败次数
	 */
	public boolean isFail() {
		this.failNumber = this.failNumber + 1; // 调用一次，代表种子失败一次，
		if (this.failNumber <= BufferedSeed.seedFailNumber)
			return true;
		else
			return false;
	}

	public SeedStatusEnum getStatus() {
		return status;
	}

	public void setStatus(SeedStatusEnum status) {
		this.status = status;
	}

	
	public BufferedGsgsRegister getGsgsRegister(String rgc)throws IllegalArgumentException{
		if(rgc==null||"".equals(rgc.trim()))
			throw new IllegalArgumentException("获取/创建GsgsRegister对象失败，因为参数注册号为空");
		BufferedGsgsRegister tmp = enters.get(rgc);
		if(tmp==null){
			tmp = new BufferedGsgsRegister();
			enters.put(rgc, tmp);
		}
		return tmp;
	}
	
	public String getUuid() {
		return this.seed.getUuid();
	}

	public void setUuid(String uuid) {
		this.seed.setUuid(uuid);
	}

	public String getName() {
		return this.seed.getName();
	}

	public void setName(String name) {
		this.seed.setName(name);
	}

	public Timestamp getBegin() {
		return this.seed.getSm().getBegin();
	}

	public void setBegin(Timestamp begin) {
		this.seed.getSm().setBegin(begin);
	}

	public Timestamp getEnd() {
		return this.seed.getSm().getEnd();
	}

	public void setEnd(Timestamp end) {
		this.seed.getSm().setEnd(end);
	}

	public String getCode() {
		return this.seed.getSm().getCode();
	}

	public void setCode(String code) {
		this.seed.getSm().setCode(code);
	}

	public int getNumber() {
		return this.seed.getSm().getNumber();
	}

	public void setNumber(int number) {
		this.seed.getSm().setNumber(number);
	}

	public String getOver() {
		return this.seed.getSm().getOver();
	}

	public void setOver(String over) {
		this.seed.getSm().setOver(over);
	}
}
