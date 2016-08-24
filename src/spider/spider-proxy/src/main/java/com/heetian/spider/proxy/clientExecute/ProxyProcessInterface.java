package com.heetian.spider.proxy.clientExecute;

import java.util.regex.Pattern;

import com.heetian.spider.dbcp.dao.imple.ProxyDaoIHazelcastImp;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoIHazelcastInter;

public interface ProxyProcessInterface {
	public static final Pattern pattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,}");
	public static final ProxyDaoIHazelcastInter dao = new ProxyDaoIHazelcastImp();
	public void process(String ipContent);
}
