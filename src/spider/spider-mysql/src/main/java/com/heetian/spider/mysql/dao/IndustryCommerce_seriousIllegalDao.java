package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_seriousIllegal;

/**
 * <p>
 * Title: IndustryCommerce_seriousIllegalDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_严重违法信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class IndustryCommerce_seriousIllegalDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_seriousIllegalDao.class);

	/***
	 * 查询指定条数的industryCommerce_seriousIllegal集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_seriousIllegal(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_seriousIllegal  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_seriousIllegal", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_seriousIllegal
	 * 
	 * @param industryCommerce_seriousIllegal	 * @return
	 */
	public static int updateIndustryCommerce_seriousIllegal(IndustryCommerce_seriousIllegal industryCommerce_seriousIllegal) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_seriousIllegal set regNumber=?,includedCause=?,includedDate=?,removeCause=?,removeDate=?,decideOrgan=? where regNumber=? ");
		Object[] params = { industryCommerce_seriousIllegal.getRegNumber(),industryCommerce_seriousIllegal.getIncludedCause(),industryCommerce_seriousIllegal.getIncludedDate(),industryCommerce_seriousIllegal.getRemoveCause(),industryCommerce_seriousIllegal.getRemoveDate(),industryCommerce_seriousIllegal.getDecideOrgan(),industryCommerce_seriousIllegal.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_seriousIllegal", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_seriousIllegal插入数据
	 * 
	 * @param industryCommerce_seriousIllegal	 * @return
	 */
	public static int insertIndustryCommerce_seriousIllegal(IndustryCommerce_seriousIllegal industryCommerce_seriousIllegal) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,includedCause,includedDate,removeCause,removeDate,decideOrgan) values(?,?,?,?,?,?)";
		Object[] params = {industryCommerce_seriousIllegal.getRegNumber(),industryCommerce_seriousIllegal.getIncludedCause(),industryCommerce_seriousIllegal.getIncludedDate(),industryCommerce_seriousIllegal.getRemoveCause(),industryCommerce_seriousIllegal.getRemoveDate(),industryCommerce_seriousIllegal.getDecideOrgan()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_seriousIllegal", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_seriousIllegal数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_seriousIllegalByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,includedCause,includedDate,removeCause,removeDate,decideOrgan from industryCommerce_seriousIllegal where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_seriousIllegal", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_seriousIllegal(List<IndustryCommerce_seriousIllegal> industryCommerce_seriousIllegalList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,includedCause,includedDate,removeCause,removeDate,decideOrgan) values(?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_seriousIllegalList.size()][7-1];
		if (industryCommerce_seriousIllegalList != null && industryCommerce_seriousIllegalList.size() > 0) {

			for (int i = 0; i < industryCommerce_seriousIllegalList.size(); i++) {
				IndustryCommerce_seriousIllegal industryCommerce_seriousIllegal = industryCommerce_seriousIllegalList.get(i);
										
						 params[i][2-2] = industryCommerce_seriousIllegal.getRegNumber();				
						 params[i][3-2] = industryCommerce_seriousIllegal.getIncludedCause();				
						 params[i][4-2] = industryCommerce_seriousIllegal.getIncludedDate();				
						 params[i][5-2] = industryCommerce_seriousIllegal.getRemoveCause();				
						 params[i][6-2] = industryCommerce_seriousIllegal.getRemoveDate();				
						 params[i][7-2] = industryCommerce_seriousIllegal.getDecideOrgan();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_seriousIllegal", e);
		}

		return result;
	}
}
