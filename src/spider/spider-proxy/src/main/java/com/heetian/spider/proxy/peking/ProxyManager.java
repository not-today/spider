package com.heetian.spider.proxy.peking;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.dao.imple.ProxyDaoIHazelcastImp;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoIHazelcastInter;
import com.heetian.spider.proxy.clientExecute.ClientExecuteProxy;

public class ProxyManager{
	private static final ProxyDaoIHazelcastInter dao = new ProxyDaoIHazelcastImp();
	public static final ProxyManager mng = new ProxyManager();
	private ProxyManager(){}
	public static ProxyManager newInstance(){
		return mng;
	}
	private static final Log logger = LogFactory.getLog(ProxyManager.class);
	/**
	 * 从数据库获取一个代理
	 */
	public Proxy pollProxy(String code) {
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
	public void updateProxy(Proxy proxy,String code){
		dao.updateProxy(proxy, code);;
	}
	public void initProxyManager() {
		new Thread(new ClientExecuteProxy()).start();
		logger.info("开启代理线程........");
	}
}
