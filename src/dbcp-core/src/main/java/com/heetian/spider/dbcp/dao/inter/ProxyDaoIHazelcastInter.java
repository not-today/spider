package com.heetian.spider.dbcp.dao.inter;

import java.util.List;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.PvnObj;
import com.heetian.spider.dbcp.utils.HazelcastUtils;

public interface ProxyDaoIHazelcastInter extends ProxyDaoInter{
	/**
	 * 内存数据库客户端
	 */
	 public static final HazelcastInstance client = HazelcastUtils.getOnlyOneConnect();
	 /**
	  * 获取map的标志，以uuid为map的key的map
	  */
	 public static final String name_uuid = "uuid";
	 /**
	  * 从内存数据库获取proxy type map的key
	  */
	 public static final String proxyType_key = "proxyType_key";
	 
	 /**
	  * 获取map的标志，以ip为map的key的map
	  */
	 public static final String name_ip = "ip";
	 /**
	  * 稳定代理的key
	  */
	 public static final String stable_proxy = "stable_proxy";
	/**
	 * 查询proxy，根据code
	 * @param code
	 * @return
	 */
	public Proxy queryProxy(String code);
	/**
	 * 获取代理类型
	 */
	public PvnObj pollProxyType(String code);
	/**
	 * 更改proxy状态
	 * @param proxy
	 * @param code
	 */
	public void updateProxy(Proxy proxy,String code);
	public IMap<Object, Object> showAllType();
	public IMap<Object, Object> showAllStableProxys();
	public void clearProxy();
	public void clearProxyAll();
	public List<Proxy> showAllNotStableProxys();
	public List<Proxy> showAllNotStableSiglePvnProxys(String code);
}
