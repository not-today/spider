package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_executive;

/**
 * <p>
 * Title: IndustryCommerce_executiveDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_主管部门（出资人）信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class IndustryCommerce_executiveDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_executiveDao.class);

	/***
	 * 查询指定条数的industryCommerce_executive集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_executive(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_executive  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_executive", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_executive
	 * 
	 * @param industryCommerce_executive	 * @return
	 */
	public static int updateIndustryCommerce_executive(IndustryCommerce_executive industryCommerce_executive) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_executive set regNumber=?,type=?,investor=?,cardType=?,cardNumber=? where regNumber=? ");
		Object[] params = { industryCommerce_executive.getRegNumber(),industryCommerce_executive.getType(),industryCommerce_executive.getInvestor(),industryCommerce_executive.getCardType(),industryCommerce_executive.getCardNumber(),industryCommerce_executive.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_executive", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_executive插入数据
	 * 
	 * @param industryCommerce_executive	 * @return
	 */
	public static int insertIndustryCommerce_executive(IndustryCommerce_executive industryCommerce_executive) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,type,investor,cardType,cardNumber) values(?,?,?,?,?)";
		Object[] params = {industryCommerce_executive.getRegNumber(),industryCommerce_executive.getType(),industryCommerce_executive.getInvestor(),industryCommerce_executive.getCardType(),industryCommerce_executive.getCardNumber()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_executive", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_executive数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_executiveByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,type,investor,cardType,cardNumber from industryCommerce_executive where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_executive", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_executive(List<IndustryCommerce_executive> industryCommerce_executiveList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,type,investor,cardType,cardNumber) values(?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_executiveList.size()][6-1];
		if (industryCommerce_executiveList != null && industryCommerce_executiveList.size() > 0) {

			for (int i = 0; i < industryCommerce_executiveList.size(); i++) {
				IndustryCommerce_executive industryCommerce_executive = industryCommerce_executiveList.get(i);
										
						 params[i][2-2] = industryCommerce_executive.getRegNumber();				
						 params[i][3-2] = industryCommerce_executive.getType();				
						 params[i][4-2] = industryCommerce_executive.getInvestor();				
						 params[i][5-2] = industryCommerce_executive.getCardType();				
						 params[i][6-2] = industryCommerce_executive.getCardNumber();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_executive", e);
		}

		return result;
	}
}
