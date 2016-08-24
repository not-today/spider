package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_shareholderSubscribed;

/**
 * <p>
 * Title: Enterprise_shareholderSubscribedDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_股东出资认缴表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Apr 02 13:35:11 CST 2015
 */
public class Enterprise_shareholderSubscribedDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_shareholderSubscribedDao.class);

	/***
	 * 查询指定条数的enterprise_shareholderSubscribed集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_shareholderSubscribed(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_shareholderSubscribed  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_shareholderSubscribed", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_shareholderSubscribed
	 * 
	 * @param enterprise_shareholderSubscribed	 * @return
	 */
	public static int updateEnterprise_shareholderSubscribed(Enterprise_shareholderSubscribed enterprise_shareholderSubscribed) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_shareholderSubscribed set regNumber=?,condate=?,conform=?,currency=?,czpid=?,ifpub=?,pid=?,subconam=?,updatetime=? where regNumber=? ");
		Object[] params = { enterprise_shareholderSubscribed.getRegNumber(),enterprise_shareholderSubscribed.getCondate(),enterprise_shareholderSubscribed.getConform(),enterprise_shareholderSubscribed.getCurrency(),enterprise_shareholderSubscribed.getCzpid(),enterprise_shareholderSubscribed.getIfpub(),enterprise_shareholderSubscribed.getPid(),enterprise_shareholderSubscribed.getSubconam(),enterprise_shareholderSubscribed.getUpdatetime(),enterprise_shareholderSubscribed.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_shareholderSubscribed", e);
		}
		return result;
	}

	/***
	 * 向enterprise_shareholderSubscribed插入数据
	 * 
	 * @param enterprise_shareholderSubscribed	 * @return
	 */
	public static int insertEnterprise_shareholderSubscribed(Enterprise_shareholderSubscribed enterprise_shareholderSubscribed) {

		int result = 0;
		
		String sql = "insert into enterprise_shareholderSubscribed (regNumber,condate,conform,currency,czpid,ifpub,pid,subconam,updatetime) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_shareholderSubscribed.getRegNumber(),enterprise_shareholderSubscribed.getCondate(),enterprise_shareholderSubscribed.getConform(),enterprise_shareholderSubscribed.getCurrency(),enterprise_shareholderSubscribed.getCzpid(),enterprise_shareholderSubscribed.getIfpub(),enterprise_shareholderSubscribed.getPid(),enterprise_shareholderSubscribed.getSubconam(),enterprise_shareholderSubscribed.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_shareholderSubscribed", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_shareholderSubscribed数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_shareholderSubscribedByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,condate,conform,currency,czpid,ifpub,pid,subconam,updatetime from enterprise_shareholderSubscribed where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_shareholderSubscribed", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_shareholderSubscribed(List<Enterprise_shareholderSubscribed> enterprise_shareholderSubscribedList) {
		int result[] = {};
		
		
		String sql = "insert into enterprise_shareholderSubscribed (regNumber,condate,conform,currency,czpid,ifpub,pid,subconam,updatetime) values(?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_shareholderSubscribedList.size()][9];
		if (enterprise_shareholderSubscribedList != null && enterprise_shareholderSubscribedList.size() > 0) {

			for (int i = 0; i < enterprise_shareholderSubscribedList.size(); i++) {
				Enterprise_shareholderSubscribed enterprise_shareholderSubscribed = enterprise_shareholderSubscribedList.get(i);
										
						 params[i][0] = enterprise_shareholderSubscribed.getRegNumber();				
						 params[i][1] = enterprise_shareholderSubscribed.getCondate();				
						 params[i][2] = enterprise_shareholderSubscribed.getConform();				
						 params[i][3] = enterprise_shareholderSubscribed.getCurrency();				
						 params[i][4] = enterprise_shareholderSubscribed.getCzpid();				
						 params[i][5] = enterprise_shareholderSubscribed.getIfpub();				
						 params[i][6] = enterprise_shareholderSubscribed.getPid();				
						 params[i][7] = enterprise_shareholderSubscribed.getSubconam();				
						 params[i][8] = enterprise_shareholderSubscribed.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_shareholderSubscribed", e);
		}

		return result;
	}
}
