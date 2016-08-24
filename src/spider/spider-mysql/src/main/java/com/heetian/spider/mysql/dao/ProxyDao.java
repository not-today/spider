package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Proxy;


/**
 * <p>
 * Title: ProxyDao.java
 * </p>
 * <p>
 * Description: 代理表数据库操作
 * </p>
 * 
 * @author admin
 * @version 1.0
 * @created 2015年3月15日 下午2:36:41
 */
public class ProxyDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(ProxyDao.class);

	/***
	 * 查询指定条数的代理集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchProxy(int number) {
		List<Object[]> arraylist = null;
		String sql = "select ip,port from proxy  where  valid=1 or (valid=2 and   HOUR( timediff( now(),  validTime) )  > 24) limit " + number;
		try {
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询proxy", e);
		}
		return arraylist;
	}
	/***
	 * 查询指定条数的代理集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchProxyDetail(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select ip,port from proxy  where  valid=1 or valid=2  order by ip desc  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询proxy", e);
		}
		return arraylist;
	}
	

	/***
	 * 更新代理表
	 * 
	 * @param seed
	 * @return
	 */
	public static int updateProxy(Proxy proxy) {
		int result = 0;
		String sql = "update proxy set valid=?,validTime=?  where ip=? ";
		Object[] params = { proxy.getValid(), proxy.getValidTime(),proxy.getIp() };
		try {
		//	logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("更新proxy", e);
		}
		return result;
	}

	/***
	 * 向代理表插入数据
	 * 
	 * @param proxy
	 * @return
	 */
	public static int insertProxy(Proxy proxy) {
		if (searchCompanysByNameAndRegNumber(proxy.getIp(), proxy.getPort()) > 0)
			return 0;
		int result = 0;
		
		

		String sql = "insert into proxy (ip,port,country,province,city,ips,proxyType,anonymity,connectTimeMS,source,valid,validTime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { proxy.getIp(), proxy.getPort(), proxy.getCountry(),
				proxy.getProvince(), proxy.getCity(), proxy.getIps(),
				proxy.getProxyType(), proxy.getAnonymity(),
				proxy.getConnectTimeMs(), proxy.getSource(), proxy.getValid(),
				proxy.getValidTime() };

		try {
			//logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入proxy", e);
		} 

		return result;

	}

	/**
	 * 根据IP和端口号查询proxy数据库表
	 * 
	 * @param ip
	 *            IP
	 * @param port
	 *            端口号
	 * @return
	 */
	public static int searchCompanysByNameAndRegNumber(String ip, int port) {
		int result = 0;
		try {
			StringBuilder sql = new StringBuilder( "select * from proxy where ip='").append(ip) .append("' and port=").append(port);
			List<Object[]> arraylist = queryRunner.query(sql.toString(), new ArrayListHandler());
			result = arraylist.size();
		} catch (SQLException e) {
			logger.error("查询proxy", e);
		} 
		return result;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchCompanys(List<Proxy> proxyList) {
		int result[] = {};
		String sql = "insert into proxy (ip,port,country,province,city,ips,proxyType,anonymity,connectTimeMS,source,valid,validTime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		logger.info("本次执行sql："+sql);
		List<Proxy> proxyListNew = new ArrayList<Proxy>();
		// 批量插入前先批量查询com一下，看看有没有重复的，重复的删除掉，不再保存(by gao_jun,2015年3月15日)
		if (proxyList != null && proxyList.size() > 0) {
			for (Proxy proxy : proxyList) {
				if (searchCompanysByNameAndRegNumber(proxy.getIp(),
						proxy.getPort()) <= 0) {
					proxyListNew.add(proxy);
				}
			}

		}
		Object[][] params = new Object[proxyListNew.size()][12];
		if (proxyListNew != null && proxyListNew.size() > 0) {

			for (int i = 0; i < proxyListNew.size(); i++) {
				Proxy proxy = proxyListNew.get(i);
				params[i][0] = proxy.getIp();
				params[i][1] = proxy.getPort();
				params[i][2] = proxy.getCountry();
				params[i][3] = proxy.getProvince();
				params[i][4] = proxy.getCity();
				params[i][5] = proxy.getIps();
				params[i][6] = proxy.getProxyType();
				params[i][7] = proxy.getAnonymity();
				params[i][8] = proxy.getConnectTimeMs();
				params[i][9] = proxy.getSource();
				params[i][10] = proxy.getValid();
				params[i][11] = proxy.getValidTime();
			}
		}
		try {
			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入proxy", e);
		}
		return result;
	}
}
