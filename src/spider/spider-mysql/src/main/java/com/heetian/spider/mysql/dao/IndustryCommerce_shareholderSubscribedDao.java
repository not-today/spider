package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_shareholderSubscribed;

/**
 * <p>
 * Title: IndustryCommerce_shareholderSubscribedDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_股东出资认缴表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Fri Apr 03 22:28:46 CST 2015
 */
public class IndustryCommerce_shareholderSubscribedDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_shareholderSubscribedDao.class);

	/***
	 * 查询指定条数的industryCommerce_shareholderSubscribed集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_shareholderSubscribed(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_shareholderSubscribed  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholderSubscribed", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_shareholderSubscribed
	 * 
	 * @param industryCommerce_shareholderSubscribed	 * @return
	 */
	public static int updateIndustryCommerce_shareholderSubscribed(IndustryCommerce_shareholderSubscribed industryCommerce_shareholderSubscribed) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_shareholderSubscribed set regNumber=?,condate=?,conform=?,inv=?,pripid=?,recid=?,subconam=? where regNumber=? ");
		Object[] params = { industryCommerce_shareholderSubscribed.getRegNumber(),industryCommerce_shareholderSubscribed.getCondate(),industryCommerce_shareholderSubscribed.getConform(),industryCommerce_shareholderSubscribed.getInv(),industryCommerce_shareholderSubscribed.getPripid(),industryCommerce_shareholderSubscribed.getRecid(),industryCommerce_shareholderSubscribed.getSubconam(),industryCommerce_shareholderSubscribed.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_shareholderSubscribed", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_shareholderSubscribed插入数据
	 * 
	 * @param industryCommerce_shareholderSubscribed	 * @return
	 */
	public static int insertIndustryCommerce_shareholderSubscribed(IndustryCommerce_shareholderSubscribed industryCommerce_shareholderSubscribed) {

		int result = 0;
		
		String sql = "insert into industryCommerce_shareholderSubscribed (regNumber,condate,conform,inv,pripid,recid,subconam) values(?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_shareholderSubscribed.getRegNumber(),industryCommerce_shareholderSubscribed.getCondate(),industryCommerce_shareholderSubscribed.getConform(),industryCommerce_shareholderSubscribed.getInv(),industryCommerce_shareholderSubscribed.getPripid(),industryCommerce_shareholderSubscribed.getRecid(),industryCommerce_shareholderSubscribed.getSubconam()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_shareholderSubscribed", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_shareholderSubscribed数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_shareholderSubscribedByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,condate,conform,inv,pripid,recid,subconam from industryCommerce_shareholderSubscribed where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholderSubscribed", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_shareholderSubscribed(List<IndustryCommerce_shareholderSubscribed> industryCommerce_shareholderSubscribedList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_shareholderSubscribed (regNumber,condate,conform,inv,pripid,recid,subconam) values(?,?,?,?,?,?,?)";
		
		//logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_shareholderSubscribedList.size()][7];
		if (industryCommerce_shareholderSubscribedList != null && industryCommerce_shareholderSubscribedList.size() > 0) {

			for (int i = 0; i < industryCommerce_shareholderSubscribedList.size(); i++) {
				IndustryCommerce_shareholderSubscribed industryCommerce_shareholderSubscribed = industryCommerce_shareholderSubscribedList.get(i);
										
						 params[i][0] = industryCommerce_shareholderSubscribed.getRegNumber();				
						 params[i][1] = industryCommerce_shareholderSubscribed.getCondate();				
						 params[i][2] = industryCommerce_shareholderSubscribed.getConform();				
						 params[i][3] = industryCommerce_shareholderSubscribed.getInv();				
						 params[i][4] = industryCommerce_shareholderSubscribed.getPripid();				
						 params[i][5] = industryCommerce_shareholderSubscribed.getRecid();				
						 params[i][6] = industryCommerce_shareholderSubscribed.getSubconam();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_shareholderSubscribed", e);
		}

		return result;
	}

	public static void deleteIndustryCommerce_shareholderSubscribed( String regNumber) {
		try {
			String sql = "delete from industryCommerce_shareholderSubscribed where regNumber='"+regNumber+"'";
			queryRunner.update(sql);
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholderSubscribed", e);
		} 		
	}
}
