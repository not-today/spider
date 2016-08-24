package com.heetian.spider.dbcp.dao.inter;

import java.util.List;

import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.PvnCfg;
import com.heetian.spider.dbcp.bean.Seed;
import com.heetian.spider.dbcp.bean.SeedMapping;

public interface ProxyDaoMysqlInter extends ProxyDaoInter{
	public void addProxyReflect(Proxy proxy);
	public void updateProxyReflect();
	public void updateProxyReflect(String uuid,String code);
	public List<Proxy> searchProxy(int number,String code);
	public List<Seed> searchSeed(int number);
	public void updateSeedReflect(SeedMapping sm);
	public void deleteSeedReflect(String uuid,String code);
	public PvnCfg loadPvnCFG(String code);
	
}
