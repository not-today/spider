package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_reportYear_equityChange;

/**
 * <p>
 * Title: Enterprise_reportYear_equityChangeDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_股权变更信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class Enterprise_reportYear_equityChangeDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_reportYear_equityChangeDao.class);

	/***
	 * 查询指定条数的enterprise_reportYear_equityChange集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_equityChange(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_reportYear_equityChange  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_equityChange", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_reportYear_equityChange
	 * 
	 * @param enterprise_reportYear_equityChange	 * @return
	 */
	public static int updateEnterprise_reportYear_equityChange(Enterprise_reportYear_equityChange enterprise_reportYear_equityChange) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_reportYear_equityChange set regNumber=?,shareholders=?,beforScale=?,afterScale=?,changeDate=? where regNumber=? ");
		Object[] params = { enterprise_reportYear_equityChange.getRegNumber(),enterprise_reportYear_equityChange.getShareholders(),enterprise_reportYear_equityChange.getBeforScale(),enterprise_reportYear_equityChange.getAfterScale(),enterprise_reportYear_equityChange.getChangeDate(),enterprise_reportYear_equityChange.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_reportYear_equityChange", e);
		}
		return result;
	}

	/***
	 * 向enterprise_reportYear_equityChange插入数据
	 * 
	 * @param enterprise_reportYear_equityChange	 * @return
	 */
	public static int insertEnterprise_reportYear_equityChange(Enterprise_reportYear_equityChange enterprise_reportYear_equityChange) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,shareholders,beforScale,afterScale,changeDate) values(?,?,?,?,?)";
		Object[] params = {enterprise_reportYear_equityChange.getRegNumber(),enterprise_reportYear_equityChange.getShareholders(),enterprise_reportYear_equityChange.getBeforScale(),enterprise_reportYear_equityChange.getAfterScale(),enterprise_reportYear_equityChange.getChangeDate()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_reportYear_equityChange", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_reportYear_equityChange数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_equityChangeByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,shareholders,beforScale,afterScale,changeDate from enterprise_reportYear_equityChange where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_equityChange", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_reportYear_equityChange(List<Enterprise_reportYear_equityChange> enterprise_reportYear_equityChangeList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,shareholders,beforScale,afterScale,changeDate) values(?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_reportYear_equityChangeList.size()][6-1];
		if (enterprise_reportYear_equityChangeList != null && enterprise_reportYear_equityChangeList.size() > 0) {

			for (int i = 0; i < enterprise_reportYear_equityChangeList.size(); i++) {
				Enterprise_reportYear_equityChange enterprise_reportYear_equityChange = enterprise_reportYear_equityChangeList.get(i);
										
						 params[i][2-2] = enterprise_reportYear_equityChange.getRegNumber();				
						 params[i][3-2] = enterprise_reportYear_equityChange.getShareholders();				
						 params[i][4-2] = enterprise_reportYear_equityChange.getBeforScale();				
						 params[i][5-2] = enterprise_reportYear_equityChange.getAfterScale();				
						 params[i][6-2] = enterprise_reportYear_equityChange.getChangeDate();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_reportYear_equityChange", e);
		}

		return result;
	}
}
