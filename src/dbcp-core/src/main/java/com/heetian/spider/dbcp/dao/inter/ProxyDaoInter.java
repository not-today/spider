package com.heetian.spider.dbcp.dao.inter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.dbcp.bean.Proxy;
/**
 * 内存数据库操作接口
 * @author tst
 *
 */
public interface ProxyDaoInter {
	public static Logger logger = LoggerFactory.getLogger(ProxyDaoInter.class);
	/**
	 * 增加一条proxy记录
	 * @param proxy
	 */
	public void addProxy(Proxy proxy);
	/**
	 * 批量添加proxy记录
	 * @param proxys
	 */
	public void addProxys(List<Proxy> proxys);
	/**
	 * 删除一条proxy记录
	 * @param uuid
	 */
	public void deleteProxy(String uuid);
	/**
	 * 修改一条proxy记录
	 * @param proxy
	 */
	public void updateProxy(Proxy proxy);
	/**
	 * 查询一条proxy记录
	 * @param uuid
	 */
	public Proxy queryProxyUUID(String uuid);
	/**
	 * 查询一条proxy记录
	 * @param ip
	 * @return
	 */
	public Proxy queryProxyIP(String ip);
}