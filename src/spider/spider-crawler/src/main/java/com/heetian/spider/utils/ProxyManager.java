package com.heetian.spider.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;

import com.hazelcast.core.IMap;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.PvnObj;
import com.heetian.spider.dbcp.dao.imple.ProxyDaoIHazelcastImp;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoIHazelcastInter;

public class ProxyManager{
	private static final ProxyDaoIHazelcastInter dao = new ProxyDaoIHazelcastImp();
	private static final Log logger = LogFactory.getLog(ProxyManager.class);
	/**
	 * 从数据库获取一个代理
	 */
	public static Proxy pollProxy(String code) {
		Proxy proxy = null;
		while (proxy==null) {
			try {
				proxy = dao.queryProxy(code);
				if (proxy==null) {
					logger.error("代理数目为0，等待添加代理！暂停5秒后继续");
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				logger.error("获取代理出错",e);
				proxy = null;
			}
		}
		return proxy;
	}
	public static void updateProxy(Proxy proxy,String code){
		if(proxy!=null)
			dao.updateProxy(proxy, code);;
	}
	public static PvnObj getProxyType(String code){
		return dao.pollProxyType(code);
	}
	public static LinkedList<HttpHost> stableProxys(){
		LinkedList<HttpHost> proxys = new LinkedList<HttpHost>();
		IMap<Object, Object> imap = dao.showAllStableProxys();
		Collection<Object> coll = imap.values();
		Iterator<Object> iter = coll.iterator();
		while(iter.hasNext()){
			Proxy proxy = (Proxy) iter.next();
			proxys.add(new HttpHost(proxy.getIp(), proxy.getPort()));
		}
		return proxys;
	}
}
