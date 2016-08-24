package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_changeInfo;

/**
 * <p>
 * Title: IndustryCommerce_changeInfoDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_变更信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class IndustryCommerce_changeInfoDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_changeInfoDao.class);

	/***
	 * 查询指定条数的industryCommerce_changeInfo集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_changeInfo(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_changeInfo  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_changeInfo", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_changeInfo
	 * 
	 * @param industryCommerce_changeInfo	 * @return
	 */
	public static int updateIndustryCommerce_changeInfo(IndustryCommerce_changeInfo industryCommerce_changeInfo) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_changeInfo set regNumber=?,alterations=?,beginContent=?,endContent=?,date=? where regNumber=? ");
		Object[] params = { industryCommerce_changeInfo.getRegNumber(),industryCommerce_changeInfo.getAlterations(),industryCommerce_changeInfo.getBeginContent(),industryCommerce_changeInfo.getEndContent(),industryCommerce_changeInfo.getDate(),industryCommerce_changeInfo.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_changeInfo", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_changeInfo插入数据
	 * 
	 * @param industryCommerce_changeInfo	 * @return
	 */
	public static int insertIndustryCommerce_changeInfo(IndustryCommerce_changeInfo industryCommerce_changeInfo) {

		int result = 0;
		
		String sql = "insert into industryCommerce_changeInfo (regNumber,alterations,beginContent,endContent,date) values(?,?,?,?,?)";
		Object[] params = {industryCommerce_changeInfo.getRegNumber(),industryCommerce_changeInfo.getAlterations(),industryCommerce_changeInfo.getBeginContent(),industryCommerce_changeInfo.getEndContent(),industryCommerce_changeInfo.getDate()};

		try {
			//logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_changeInfo", e);
		} 

		return result;

	}
	/**
	 * 根据企业注册号删除
	 * @param regNumber
	 * @return
	 */
	public static void deleteIndustryCommerce_changeInfoByRegNumber(String regNumber) {
		try {
			String sql = "delete from industryCommerce_changeInfo where regNumber='"+regNumber+"'";
			queryRunner.update(sql);
		} catch (SQLException e) {
			logger.error("查询industryCommerce_branchInfo", e);
		} 
	}
	/**
	 * 根据企业注册号查询industryCommerce_changeInfo数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_changeInfoByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,alterations,beginContent,endContent,date from industryCommerce_changeInfo where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_changeInfo", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_changeInfo(List<IndustryCommerce_changeInfo> industryCommerce_changeInfoList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_changeInfo (regNumber,alterations,beginContent,endContent,date) values(?,?,?,?,?)";
		
		//logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_changeInfoList.size()][6-1];
		if (industryCommerce_changeInfoList != null && industryCommerce_changeInfoList.size() > 0) {

			for (int i = 0; i < industryCommerce_changeInfoList.size(); i++) {
				IndustryCommerce_changeInfo industryCommerce_changeInfo = industryCommerce_changeInfoList.get(i);
										
						 params[i][2-2] = industryCommerce_changeInfo.getRegNumber();				
						 params[i][3-2] = industryCommerce_changeInfo.getAlterations();				
						 params[i][4-2] = industryCommerce_changeInfo.getBeginContent();				
						 params[i][5-2] = industryCommerce_changeInfo.getEndContent();				
						 params[i][6-2] = industryCommerce_changeInfo.getDate();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_changeInfo", e);
		}

		return result;
	}
}
