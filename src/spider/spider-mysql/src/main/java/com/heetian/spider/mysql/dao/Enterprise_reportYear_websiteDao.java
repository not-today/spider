package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_reportYear_website;

/**
 * <p>
 * Title: Enterprise_reportYear_websiteDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_网站信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class Enterprise_reportYear_websiteDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_reportYear_websiteDao.class);

	/***
	 * 查询指定条数的enterprise_reportYear_website集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_website(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_reportYear_website  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_website", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_reportYear_website
	 * 
	 * @param enterprise_reportYear_website	 * @return
	 */
	public static int updateEnterprise_reportYear_website(Enterprise_reportYear_website enterprise_reportYear_website) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_reportYear_website set regNumber=?,type=?,name=?,webUrl=? where regNumber=? ");
		Object[] params = { enterprise_reportYear_website.getRegNumber(),enterprise_reportYear_website.getType(),enterprise_reportYear_website.getName(),enterprise_reportYear_website.getWebUrl(),enterprise_reportYear_website.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_reportYear_website", e);
		}
		return result;
	}

	/***
	 * 向enterprise_reportYear_website插入数据
	 * 
	 * @param enterprise_reportYear_website	 * @return
	 */
	public static int insertEnterprise_reportYear_website(Enterprise_reportYear_website enterprise_reportYear_website) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,type,name,webUrl) values(?,?,?,?)";
		Object[] params = {enterprise_reportYear_website.getRegNumber(),enterprise_reportYear_website.getType(),enterprise_reportYear_website.getName(),enterprise_reportYear_website.getWebUrl()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_reportYear_website", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_reportYear_website数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_websiteByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,type,name,webUrl from enterprise_reportYear_website where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_website", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_reportYear_website(List<Enterprise_reportYear_website> enterprise_reportYear_websiteList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,type,name,webUrl) values(?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_reportYear_websiteList.size()][5-1];
		if (enterprise_reportYear_websiteList != null && enterprise_reportYear_websiteList.size() > 0) {

			for (int i = 0; i < enterprise_reportYear_websiteList.size(); i++) {
				Enterprise_reportYear_website enterprise_reportYear_website = enterprise_reportYear_websiteList.get(i);
										
						 params[i][2-2] = enterprise_reportYear_website.getRegNumber();				
						 params[i][3-2] = enterprise_reportYear_website.getType();				
						 params[i][4-2] = enterprise_reportYear_website.getName();				
						 params[i][5-2] = enterprise_reportYear_website.getWebUrl();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_reportYear_website", e);
		}

		return result;
	}
}
