package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Companys;

/**
 * <p>
 * Title: CompanysDao.java
 * </p>
 * <p>
 * Description: 企业信息列表表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class CompanysDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(CompanysDao.class);

	/***
	 * 查询指定条数的companys集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchCompanys(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from companys  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询companys", e);
		}
		return arraylist;
	}

	/***
	 * 更新companys
	 * 
	 * @param companys	 * @return
	 */
	public static int updateCompanys(Companys companys) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  companys set name=?,regNumber=?,url=?,flag=?,recordingTime=? where regNumber=? ");
		Object[] params = { companys.getName(),companys.getRegNumber(),companys.getUrl(),companys.getFlag(),companys.getRecordingTime(),companys.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
			logger.info("更新companys影响行数."+result);
		} catch (SQLException e) {
			logger.error("更新companys", e);
		}
		return result;
	}

	/***
	 * 向companys插入数据
	 * 
	 * @param companys	 * @return
	 */
	public static int insertCompanys(Companys companys) {

		int result = 0;
		
		String sql = "insert into companys (name,regNumber,url,flag,recordingTime) values(?,?,?,?,?)";
		Object[] params = {companys.getName(),companys.getRegNumber(),companys.getUrl(),companys.getFlag(),companys.getRecordingTime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入companys", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询companys数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchCompanysByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,name,regNumber,url,flag,recordingTime from companys where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询companys", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchCompanys(List<Companys> companysList) {
		int result[] = {};
		
		
		String sql = "insert into companys (name,regNumber,url,flag,recordingTime) values(?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[companysList.size()][6-1];
		if (companysList != null && companysList.size() > 0) {

			for (int i = 0; i < companysList.size(); i++) {
				Companys companys = companysList.get(i);
										
						 params[i][2-2] = companys.getName();				
						 params[i][3-2] = companys.getRegNumber();				
						 params[i][4-2] = companys.getUrl();				
						 params[i][5-2] = companys.getFlag();				
						 params[i][6-2] = companys.getRecordingTime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入companys", e);
		}

		return result;
	}
}
