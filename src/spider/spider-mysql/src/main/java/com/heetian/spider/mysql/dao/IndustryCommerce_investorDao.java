package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_investor;

/**
 * <p>
 * Title: IndustryCommerce_investorDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_投资人信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class IndustryCommerce_investorDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_investorDao.class);

	/***
	 * 查询指定条数的industryCommerce_investor集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_investor(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_investor  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_investor", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_investor
	 * 
	 * @param industryCommerce_investor	 * @return
	 */
	public static int updateIndustryCommerce_investor(IndustryCommerce_investor industryCommerce_investor) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_investor set regNumber=?,name=?,investorWay=? where regNumber=? ");
		Object[] params = { industryCommerce_investor.getRegNumber(),industryCommerce_investor.getName(),industryCommerce_investor.getInvestorWay(),industryCommerce_investor.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_investor", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_investor插入数据
	 * 
	 * @param industryCommerce_investor	 * @return
	 */
	public static int insertIndustryCommerce_investor(IndustryCommerce_investor industryCommerce_investor) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,name,investorWay) values(?,?,?)";
		Object[] params = {industryCommerce_investor.getRegNumber(),industryCommerce_investor.getName(),industryCommerce_investor.getInvestorWay()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_investor", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_investor数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_investorByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,name,investorWay from industryCommerce_investor where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_investor", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_investor(List<IndustryCommerce_investor> industryCommerce_investorList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,name,investorWay) values(?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_investorList.size()][4-1];
		if (industryCommerce_investorList != null && industryCommerce_investorList.size() > 0) {

			for (int i = 0; i < industryCommerce_investorList.size(); i++) {
				IndustryCommerce_investor industryCommerce_investor = industryCommerce_investorList.get(i);
										
						 params[i][2-2] = industryCommerce_investor.getRegNumber();				
						 params[i][3-2] = industryCommerce_investor.getName();				
						 params[i][4-2] = industryCommerce_investor.getInvestorWay();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_investor", e);
		}

		return result;
	}
}
