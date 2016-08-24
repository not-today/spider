package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_reportYear;

/**
 * <p>
 * Title: Enterprise_reportYearDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_企业年报表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class Enterprise_reportYearDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_reportYearDao.class);

	/***
	 * 查询指定条数的enterprise_reportYear集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_reportYear  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_reportYear
	 * 
	 * @param enterprise_reportYear	 * @return
	 */
	public static int updateEnterprise_reportYear(Enterprise_reportYear enterprise_reportYear) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_reportYear set regNumber=?,dubmitYear=?,releaseDate=? where regNumber=? ");
		Object[] params = { enterprise_reportYear.getRegNumber(),enterprise_reportYear.getDubmitYear(),enterprise_reportYear.getReleaseDate(),enterprise_reportYear.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_reportYear", e);
		}
		return result;
	}

	/***
	 * 向enterprise_reportYear插入数据
	 * 
	 * @param enterprise_reportYear	 * @return
	 */
	public static int insertEnterprise_reportYear(Enterprise_reportYear enterprise_reportYear) {

		int result = 0;
		
		String sql = "insert into enterprise_reportYear (regNumber,dubmitYear,releaseDate) values(?,?,?)";
		Object[] params = {enterprise_reportYear.getRegNumber(),enterprise_reportYear.getDubmitYear(),enterprise_reportYear.getReleaseDate()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_reportYear", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_reportYear数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYearByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,dubmitYear,releaseDate from enterprise_reportYear where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_reportYear(List<Enterprise_reportYear> enterprise_reportYearList) {
		int result[] = {};
		
		
		String sql = "insert into enterprise_reportYear (regNumber,dubmitYear,releaseDate) values(?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_reportYearList.size()][4-1];
		if (enterprise_reportYearList != null && enterprise_reportYearList.size() > 0) {

			for (int i = 0; i < enterprise_reportYearList.size(); i++) {
				Enterprise_reportYear enterprise_reportYear = enterprise_reportYearList.get(i);
										
						 params[i][2-2] = enterprise_reportYear.getRegNumber();				
						 params[i][3-2] = enterprise_reportYear.getDubmitYear();				
						 params[i][4-2] = enterprise_reportYear.getReleaseDate();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_reportYear", e);
		}

		return result;
	}
}
