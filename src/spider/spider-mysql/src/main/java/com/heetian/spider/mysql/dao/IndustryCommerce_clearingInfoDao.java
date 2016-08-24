package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_clearingInfo;

/**
 * <p>
 * Title: IndustryCommerce_clearingInfoDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_清算信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class IndustryCommerce_clearingInfoDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_clearingInfoDao.class);

	/***
	 * 查询指定条数的industryCommerce_clearingInfo集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_clearingInfo(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_clearingInfo  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_clearingInfo", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_clearingInfo
	 * 
	 * @param industryCommerce_clearingInfo	 * @return
	 */
	public static int updateIndustryCommerce_clearingInfo(IndustryCommerce_clearingInfo industryCommerce_clearingInfo) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_clearingInfo set regNumber=?,cleanLeader=?,cleanMembers=? where regNumber=? ");
		Object[] params = { industryCommerce_clearingInfo.getRegNumber(),industryCommerce_clearingInfo.getCleanLeader(),industryCommerce_clearingInfo.getCleanMembers(),industryCommerce_clearingInfo.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_clearingInfo", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_clearingInfo插入数据
	 * 
	 * @param industryCommerce_clearingInfo	 * @return
	 */
	public static int insertIndustryCommerce_clearingInfo(IndustryCommerce_clearingInfo industryCommerce_clearingInfo) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,cleanLeader,cleanMembers) values(?,?,?)";
		Object[] params = {industryCommerce_clearingInfo.getRegNumber(),industryCommerce_clearingInfo.getCleanLeader(),industryCommerce_clearingInfo.getCleanMembers()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_clearingInfo", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_clearingInfo数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_clearingInfoByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,cleanLeader,cleanMembers from industryCommerce_clearingInfo where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_clearingInfo", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_clearingInfo(List<IndustryCommerce_clearingInfo> industryCommerce_clearingInfoList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,cleanLeader,cleanMembers) values(?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_clearingInfoList.size()][4-1];
		if (industryCommerce_clearingInfoList != null && industryCommerce_clearingInfoList.size() > 0) {

			for (int i = 0; i < industryCommerce_clearingInfoList.size(); i++) {
				IndustryCommerce_clearingInfo industryCommerce_clearingInfo = industryCommerce_clearingInfoList.get(i);
										
						 params[i][2-2] = industryCommerce_clearingInfo.getRegNumber();				
						 params[i][3-2] = industryCommerce_clearingInfo.getCleanLeader();				
						 params[i][4-2] = industryCommerce_clearingInfo.getCleanMembers();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_clearingInfo", e);
		}

		return result;
	}
}
