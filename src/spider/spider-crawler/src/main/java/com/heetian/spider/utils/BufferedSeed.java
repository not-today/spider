package com.heetian.spider.utils;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.enumeration.SeedStatus;

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
	private Set<String> successRegs = new HashSet<String>();
	private Set<String> errorRegs = new HashSet<String>();
	
	/**
	 * 关键字失败多少次
	 */
	private int failNumber = 0;
	private SeedStatus status = SeedStatus.SUCESS;
	private Proxy proxy;
	private SeedJsonBean origin;
	public BufferedSeed() {
	}
	public BufferedSeed(SeedJsonBean origin) {
		super();
		this.origin = origin;
	}
	public SeedJsonBean getOrigin() {
		return origin;
	}

	public void setOrigin(SeedJsonBean origin) {
		this.origin = origin;
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
		successRegs.add(regnumber);
	}
	public void removeError(String regnumber){
		if (regnumber == null || "".equals(regnumber))
			return;
		errorRegs.remove(regnumber);
	}
	public Set<String> getErrorRegs() {
		return errorRegs;
	}

	public void addErrorRegs(String regnumber) {
		if (regnumber == null || "".equals(regnumber))
			return;
			errorRegs.add(regnumber);
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

	public SeedStatus getStatus() {
		return status;
	}

	public void setStatus(SeedStatus status) {
		this.status = status;
	}
	public void initSeedStatus(){
		setStatus(SeedStatus.SUCESS);
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
	public String getName(){
		return this.origin.getSeed();
	}
	public String getCode(){
		return this.origin.getPvn();
	}
}
