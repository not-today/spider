package com.heetian.spider.dbcp.bean;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.http.HttpHost;

public class Proxy implements Serializable{
	public static final Pattern pattern = Pattern.compile("(\\d{1,}\\.\\d{1,}.\\d{1,}.\\d{1,}):(\\d{1,});{0,1}");
	/**
	 * 
	 */
	private static final long serialVersionUID = -720169144079771043L;
	private HttpHost httphost ;
	private String uuid;
	private String ip;
	private int port;
	/**
	 * 代理可用与否状态
	 */
	private ProxyStatus status = ProxyStatus.YSE;
	/**
	 * 代理稳定与不稳定类型
	 */
	private ProxyStatus stable = ProxyStatus.NO;
	public Proxy(String ip, String port,String uuid){
		this(ip,Integer.parseInt(port),uuid);
	}
	public Proxy(String ip, int port,String uuid){
		super();
		this.ip = ip;
		this.port = port;
		this.uuid = uuid;
		this.httphost = new HttpHost(this.ip, this.port); 
	}
	public HttpHost toConvertHttpHost(){
		return this.httphost;
	}
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public ProxyStatus getStatus() {
		return status;
	}
	public void setStatus(ProxyStatus status) {
		this.status = status;
	}
	
	public ProxyStatus getStable() {
		return stable;
	}
	public void setStable(ProxyStatus stable) {
		this.stable = stable;
	}
	public static String newUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	@Override
	public String toString() {
		return "Proxy [httphost=" + httphost + ", uuid=" + uuid + ", ip=" + ip
				+ ", port=" + port + ", status=" + status + ", stable="
				+ stable + "]";
	}
}
