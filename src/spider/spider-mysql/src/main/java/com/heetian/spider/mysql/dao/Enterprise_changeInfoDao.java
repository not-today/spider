package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_changeInfo;

/**
 * <p>
 * Title: Enterprise_changeInfoDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_变更信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class Enterprise_changeInfoDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_changeInfoDao.class);

	/***
	 * 查询指定条数的enterprise_changeInfo集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_changeInfo(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_changeInfo  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_changeInfo", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_changeInfo
	 * 
	 * @param enterprise_changeInfo	 * @return
	 */
	public static int updateEnterprise_changeInfo(Enterprise_changeInfo enterprise_changeInfo) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_changeInfo set regNumber=?,alterations=?,beginContent=?,endContent=?,date=? where regNumber=? ");
		Object[] params = { enterprise_changeInfo.getRegNumber(),enterprise_changeInfo.getAlterations(),enterprise_changeInfo.getBeginContent(),enterprise_changeInfo.getEndContent(),enterprise_changeInfo.getDate(),enterprise_changeInfo.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_changeInfo", e);
		}
		return result;
	}

	/***
	 * 向enterprise_changeInfo插入数据
	 * 
	 * @param enterprise_changeInfo	 * @return
	 */
	public static int insertEnterprise_changeInfo(Enterprise_changeInfo enterprise_changeInfo) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,alterations,beginContent,endContent,date) values(?,?,?,?,?)";
		Object[] params = {enterprise_changeInfo.getRegNumber(),enterprise_changeInfo.getAlterations(),enterprise_changeInfo.getBeginContent(),enterprise_changeInfo.getEndContent(),enterprise_changeInfo.getDate()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_changeInfo", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_changeInfo数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_changeInfoByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,alterations,beginContent,endContent,date from enterprise_changeInfo where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_changeInfo", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_changeInfo(List<Enterprise_changeInfo> enterprise_changeInfoList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,alterations,beginContent,endContent,date) values(?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_changeInfoList.size()][6-1];
		if (enterprise_changeInfoList != null && enterprise_changeInfoList.size() > 0) {

			for (int i = 0; i < enterprise_changeInfoList.size(); i++) {
				Enterprise_changeInfo enterprise_changeInfo = enterprise_changeInfoList.get(i);
										
						 params[i][2-2] = enterprise_changeInfo.getRegNumber();				
						 params[i][3-2] = enterprise_changeInfo.getAlterations();				
						 params[i][4-2] = enterprise_changeInfo.getBeginContent();				
						 params[i][5-2] = enterprise_changeInfo.getEndContent();				
						 params[i][6-2] = enterprise_changeInfo.getDate();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_changeInfo", e);
		}

		return result;
	}
}
