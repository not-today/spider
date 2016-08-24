package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_reportYear_changeRecords;

/**
 * <p>
 * Title: Enterprise_reportYear_changeRecordsDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_修改记录表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class Enterprise_reportYear_changeRecordsDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_reportYear_changeRecordsDao.class);

	/***
	 * 查询指定条数的enterprise_reportYear_changeRecords集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_changeRecords(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_reportYear_changeRecords  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_changeRecords", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_reportYear_changeRecords
	 * 
	 * @param enterprise_reportYear_changeRecords	 * @return
	 */
	public static int updateEnterprise_reportYear_changeRecords(Enterprise_reportYear_changeRecords enterprise_reportYear_changeRecords) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_reportYear_changeRecords set regNumber=?,changeItem=?,changeBefor=?,changeAfter=?,changeDate=? where regNumber=? ");
		Object[] params = { enterprise_reportYear_changeRecords.getRegNumber(),enterprise_reportYear_changeRecords.getChangeItem(),enterprise_reportYear_changeRecords.getChangeBefor(),enterprise_reportYear_changeRecords.getChangeAfter(),enterprise_reportYear_changeRecords.getChangeDate(),enterprise_reportYear_changeRecords.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_reportYear_changeRecords", e);
		}
		return result;
	}

	/***
	 * 向enterprise_reportYear_changeRecords插入数据
	 * 
	 * @param enterprise_reportYear_changeRecords	 * @return
	 */
	public static int insertEnterprise_reportYear_changeRecords(Enterprise_reportYear_changeRecords enterprise_reportYear_changeRecords) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,changeItem,changeBefor,changeAfter,changeDate) values(?,?,?,?,?)";
		Object[] params = {enterprise_reportYear_changeRecords.getRegNumber(),enterprise_reportYear_changeRecords.getChangeItem(),enterprise_reportYear_changeRecords.getChangeBefor(),enterprise_reportYear_changeRecords.getChangeAfter(),enterprise_reportYear_changeRecords.getChangeDate()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_reportYear_changeRecords", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_reportYear_changeRecords数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_changeRecordsByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,changeItem,changeBefor,changeAfter,changeDate from enterprise_reportYear_changeRecords where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_changeRecords", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_reportYear_changeRecords(List<Enterprise_reportYear_changeRecords> enterprise_reportYear_changeRecordsList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,changeItem,changeBefor,changeAfter,changeDate) values(?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_reportYear_changeRecordsList.size()][6-1];
		if (enterprise_reportYear_changeRecordsList != null && enterprise_reportYear_changeRecordsList.size() > 0) {

			for (int i = 0; i < enterprise_reportYear_changeRecordsList.size(); i++) {
				Enterprise_reportYear_changeRecords enterprise_reportYear_changeRecords = enterprise_reportYear_changeRecordsList.get(i);
										
						 params[i][2-2] = enterprise_reportYear_changeRecords.getRegNumber();				
						 params[i][3-2] = enterprise_reportYear_changeRecords.getChangeItem();				
						 params[i][4-2] = enterprise_reportYear_changeRecords.getChangeBefor();				
						 params[i][5-2] = enterprise_reportYear_changeRecords.getChangeAfter();				
						 params[i][6-2] = enterprise_reportYear_changeRecords.getChangeDate();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_reportYear_changeRecords", e);
		}

		return result;
	}
}
