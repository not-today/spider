package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_shareholderInvestment;

/**
 * <p>
 * Title: Enterprise_shareholderInvestmentDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_股东出资基本信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Apr 02 15:40:09 CST 2015
 */
public class Enterprise_shareholderInvestmentDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_shareholderInvestmentDao.class);

	/***
	 * 查询指定条数的enterprise_shareholderInvestment集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_shareholderInvestment(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_shareholderInvestment  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_shareholderInvestment", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_shareholderInvestment
	 * 
	 * @param enterprise_shareholderInvestment	 * @return
	 */
	public static int updateEnterprise_shareholderInvestment(Enterprise_shareholderInvestment enterprise_shareholderInvestment) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_shareholderInvestment set regNumber=?,inv=?,liacconam=?,lisubconam=?,pripid=?,ifpub=?,pid=?,updatetime=? where regNumber=? ");
		Object[] params = { enterprise_shareholderInvestment.getRegNumber(),enterprise_shareholderInvestment.getInv(),enterprise_shareholderInvestment.getLiacconam(),enterprise_shareholderInvestment.getLisubconam(),enterprise_shareholderInvestment.getPripid(),enterprise_shareholderInvestment.getIfpub(),enterprise_shareholderInvestment.getPid(),enterprise_shareholderInvestment.getUpdatetime(),enterprise_shareholderInvestment.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_shareholderInvestment", e);
		}
		return result;
	}

	/***
	 * 向enterprise_shareholderInvestment插入数据
	 * 
	 * @param enterprise_shareholderInvestment	 * @return
	 */
	public static int insertEnterprise_shareholderInvestment(Enterprise_shareholderInvestment enterprise_shareholderInvestment) {

		int result = 0;
		
		String sql = "insert into enterprise_shareholderInvestment (regNumber,inv,liacconam,lisubconam,pripid,ifpub,pid,updatetime) values(?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_shareholderInvestment.getRegNumber(),enterprise_shareholderInvestment.getInv(),enterprise_shareholderInvestment.getLiacconam(),enterprise_shareholderInvestment.getLisubconam(),enterprise_shareholderInvestment.getPripid(),enterprise_shareholderInvestment.getIfpub(),enterprise_shareholderInvestment.getPid(),enterprise_shareholderInvestment.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_shareholderInvestment", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_shareholderInvestment数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_shareholderInvestmentByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,inv,liacconam,lisubconam,pripid,ifpub,pid,updatetime from enterprise_shareholderInvestment where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_shareholderInvestment", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_shareholderInvestment(List<Enterprise_shareholderInvestment> enterprise_shareholderInvestmentList) {
		int result[] = {};
		
		
		String sql = "insert into enterprise_shareholderInvestment (regNumber,inv,liacconam,lisubconam,pripid,ifpub,pid,updatetime) values(?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_shareholderInvestmentList.size()][8];
		if (enterprise_shareholderInvestmentList != null && enterprise_shareholderInvestmentList.size() > 0) {

			for (int i = 0; i < enterprise_shareholderInvestmentList.size(); i++) {
				Enterprise_shareholderInvestment enterprise_shareholderInvestment = enterprise_shareholderInvestmentList.get(i);
										
						 params[i][0] = enterprise_shareholderInvestment.getRegNumber();				
						 params[i][1] = enterprise_shareholderInvestment.getInv();				
						 params[i][2] = enterprise_shareholderInvestment.getLiacconam();				
						 params[i][3] = enterprise_shareholderInvestment.getLisubconam();				
						 params[i][4] = enterprise_shareholderInvestment.getPripid();				
						 params[i][5] = enterprise_shareholderInvestment.getIfpub();				
						 params[i][6] = enterprise_shareholderInvestment.getPid();				
						 params[i][7] = enterprise_shareholderInvestment.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_shareholderInvestment", e);
		}

		return result;
	}
}
