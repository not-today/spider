package com.heetian.spider.proxy.dao;

import com.heetian.spider.dbcp.dao.imple.ProxyDaoIHazelcastImp;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoIHazelcastInter;

public class Manager {
	public static final ProxyDaoIHazelcastInter dao = new ProxyDaoIHazelcastImp();
}
