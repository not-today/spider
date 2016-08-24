package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_shareholderPaidin;

/**
 * <p>
 * Title: IndustryCommerce_shareholderPaidinDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_股东出资实缴表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Fri Apr 03 22:28:46 CST 2015
 */
public class IndustryCommerce_shareholderPaidinDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_shareholderPaidinDao.class);

	/***
	 * 查询指定条数的industryCommerce_shareholderPaidin集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_shareholderPaidin(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_shareholderPaidin  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholderPaidin", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_shareholderPaidin
	 * 
	 * @param industryCommerce_shareholderPaidin	 * @return
	 */
	public static int updateIndustryCommerce_shareholderPaidin(IndustryCommerce_shareholderPaidin industryCommerce_shareholderPaidin) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_shareholderPaidin set regNumber=?,acconam=?,condate=?,conform=?,inv=?,pripid=?,recid=? where regNumber=? ");
		Object[] params = { industryCommerce_shareholderPaidin.getRegNumber(),industryCommerce_shareholderPaidin.getAcconam(),industryCommerce_shareholderPaidin.getCondate(),industryCommerce_shareholderPaidin.getConform(),industryCommerce_shareholderPaidin.getInv(),industryCommerce_shareholderPaidin.getPripid(),industryCommerce_shareholderPaidin.getRecid(),industryCommerce_shareholderPaidin.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_shareholderPaidin", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_shareholderPaidin插入数据
	 * 
	 * @param industryCommerce_shareholderPaidin	 * @return
	 */
	public static int insertIndustryCommerce_shareholderPaidin(IndustryCommerce_shareholderPaidin industryCommerce_shareholderPaidin) {

		int result = 0;
		
		String sql = "insert into industryCommerce_shareholderPaidin (regNumber,acconam,condate,conform,inv,pripid,recid) values(?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_shareholderPaidin.getRegNumber(),industryCommerce_shareholderPaidin.getAcconam(),industryCommerce_shareholderPaidin.getCondate(),industryCommerce_shareholderPaidin.getConform(),industryCommerce_shareholderPaidin.getInv(),industryCommerce_shareholderPaidin.getPripid(),industryCommerce_shareholderPaidin.getRecid()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_shareholderPaidin", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_shareholderPaidin数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_shareholderPaidinByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,acconam,condate,conform,inv,pripid,recid from industryCommerce_shareholderPaidin where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholderPaidin", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_shareholderPaidin(List<IndustryCommerce_shareholderPaidin> industryCommerce_shareholderPaidinList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_shareholderPaidin (regNumber,acconam,condate,conform,inv,pripid,recid) values(?,?,?,?,?,?,?)";
		
		//logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_shareholderPaidinList.size()][7];
		if (industryCommerce_shareholderPaidinList != null && industryCommerce_shareholderPaidinList.size() > 0) {

			for (int i = 0; i < industryCommerce_shareholderPaidinList.size(); i++) {
				IndustryCommerce_shareholderPaidin industryCommerce_shareholderPaidin = industryCommerce_shareholderPaidinList.get(i);
										
						 params[i][0] = industryCommerce_shareholderPaidin.getRegNumber();				
						 params[i][1] = industryCommerce_shareholderPaidin.getAcconam();				
						 params[i][2] = industryCommerce_shareholderPaidin.getCondate();				
						 params[i][3] = industryCommerce_shareholderPaidin.getConform();				
						 params[i][4] = industryCommerce_shareholderPaidin.getInv();				
						 params[i][5] = industryCommerce_shareholderPaidin.getPripid();				
						 params[i][6] = industryCommerce_shareholderPaidin.getRecid();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_shareholderPaidin", e);
		}

		return result;
	}

	public static void deleteIndustryCommerce_shareholderPaidin(String regNumber) {
		try {
			String sql = "delete from industryCommerce_shareholderPaidin where regNumber='"+regNumber+"'";
			queryRunner.update(sql);
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholderPaidin", e);
		} 		
	}
}
