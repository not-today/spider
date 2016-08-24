package com.heetian.spider.proxy.utils;

import com.heetian.spider.dbcp.dao.imple.ProxyDaoIHazelcastImp;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoIHazelcastInter;

public interface ProxyProcessInterface {
	public static final ProxyDaoIHazelcastInter dao = new ProxyDaoIHazelcastImp();
	public void process(String ipContent);
}
