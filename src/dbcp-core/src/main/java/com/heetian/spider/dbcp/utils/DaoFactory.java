package com.heetian.spider.dbcp.utils;

import com.heetian.spider.dbcp.dao.inter.ProxyDaoInter;
import com.heetian.spider.dbcp.dao.inter.RegisterDaoInter;

public class DaoFactory {
	private static Object getInstance(String className){
		try {
			Class<?> clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (Exception e) {
			throw new NullPointerException("创建类出错:"+e.getMessage());
		}
	}
	public static RegisterDaoInter instanceReg(String className){
		return (RegisterDaoInter) getInstance(className);
	}
	public static ProxyDaoInter instancePro(String className){
		return (ProxyDaoInter) getInstance(className);
	}
}
