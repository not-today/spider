package com.heetian.spider.dbcp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.dbcp.utils.ConstantDBCP;

public class PvnObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -743865244735839293L;
	private String code;
	private String name;
	private String proxyType = ConstantDBCP.proxyType_stable;
	private String charset = "UTF-8";
	public PvnObj(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProxyType() {
		return proxyType;
	}
	public void setProxyType(String proxyType) {
		this.proxyType = proxyType;
	}
	
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public static List<PvnObj> init32(){
		String pvnarr[] = new String[]{
				"100000:总局","340000:安徽","500000:重庆","350000:福建","620000:甘肃","440000:广东","450000:广西","520000:贵州","460000:海南","130000:河北",
				"230000:黑龙江","410000:河南","420000:湖北","430000:湖南","320000:江苏","360000:江西","220000:吉林","210000:辽宁","150000:内蒙古","640000:宁夏",
				"110000:北京","630000:青海","370000:山东","310000:上海","140000:山西","610000:陕西","510000:四川","120000:天津","650000:新疆","540000:西藏",
				"530000:云南","330000:浙江"};
		List<PvnObj> pvns = new ArrayList<PvnObj>();
		for(String pvn:pvnarr){
			String pvncom[] = pvn.split(":");
			pvns.add(new PvnObj(pvncom[0],pvncom[1]));
		}
		return pvns;
	}
	@Override
	public String toString() {
		return "pvn[c:" + code + ", n:" + name + "]";
	}
	public static String getName(String code){
		List<PvnObj> pvns = init32();
		for(PvnObj pvn:pvns){
			if(pvn.getCode().equals(code))
				return pvn.getName();
		}
		return null;
	}
}
