package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_reportYear_companyCapitalStatus;

/**
 * <p>
 * Title: Enterprise_reportYear_companyCapitalStatusDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_企业资产状况信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class Enterprise_reportYear_companyCapitalStatusDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_reportYear_companyCapitalStatusDao.class);

	/***
	 * 查询指定条数的enterprise_reportYear_companyCapitalStatus集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_companyCapitalStatus(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_reportYear_companyCapitalStatus  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_companyCapitalStatus", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_reportYear_companyCapitalStatus
	 * 
	 * @param enterprise_reportYear_companyCapitalStatus	 * @return
	 */
	public static int updateEnterprise_reportYear_companyCapitalStatus(Enterprise_reportYear_companyCapitalStatus enterprise_reportYear_companyCapitalStatus) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_reportYear_companyCapitalStatus set regNumber=?,totalAssets=?,totalSales=?,mainBusinessIncome=?,totalTax=?,totalLliabilities=?,totalProfits=?,netProfit=?,totalEquity=? where regNumber=? ");
		Object[] params = { enterprise_reportYear_companyCapitalStatus.getRegNumber(),enterprise_reportYear_companyCapitalStatus.getTotalAssets(),enterprise_reportYear_companyCapitalStatus.getTotalSales(),enterprise_reportYear_companyCapitalStatus.getMainBusinessIncome(),enterprise_reportYear_companyCapitalStatus.getTotalTax(),enterprise_reportYear_companyCapitalStatus.getTotalLliabilities(),enterprise_reportYear_companyCapitalStatus.getTotalProfits(),enterprise_reportYear_companyCapitalStatus.getNetProfit(),enterprise_reportYear_companyCapitalStatus.getTotalEquity(),enterprise_reportYear_companyCapitalStatus.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_reportYear_companyCapitalStatus", e);
		}
		return result;
	}

	/***
	 * 向enterprise_reportYear_companyCapitalStatus插入数据
	 * 
	 * @param enterprise_reportYear_companyCapitalStatus	 * @return
	 */
	public static int insertEnterprise_reportYear_companyCapitalStatus(Enterprise_reportYear_companyCapitalStatus enterprise_reportYear_companyCapitalStatus) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,totalAssets,totalSales,mainBusinessIncome,totalTax,totalLliabilities,totalProfits,netProfit,totalEquity) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_reportYear_companyCapitalStatus.getRegNumber(),enterprise_reportYear_companyCapitalStatus.getTotalAssets(),enterprise_reportYear_companyCapitalStatus.getTotalSales(),enterprise_reportYear_companyCapitalStatus.getMainBusinessIncome(),enterprise_reportYear_companyCapitalStatus.getTotalTax(),enterprise_reportYear_companyCapitalStatus.getTotalLliabilities(),enterprise_reportYear_companyCapitalStatus.getTotalProfits(),enterprise_reportYear_companyCapitalStatus.getNetProfit(),enterprise_reportYear_companyCapitalStatus.getTotalEquity()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_reportYear_companyCapitalStatus", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_reportYear_companyCapitalStatus数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_companyCapitalStatusByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,totalAssets,totalSales,mainBusinessIncome,totalTax,totalLliabilities,totalProfits,netProfit,totalEquity from enterprise_reportYear_companyCapitalStatus where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_companyCapitalStatus", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_reportYear_companyCapitalStatus(List<Enterprise_reportYear_companyCapitalStatus> enterprise_reportYear_companyCapitalStatusList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,totalAssets,totalSales,mainBusinessIncome,totalTax,totalLliabilities,totalProfits,netProfit,totalEquity) values(?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_reportYear_companyCapitalStatusList.size()][10-1];
		if (enterprise_reportYear_companyCapitalStatusList != null && enterprise_reportYear_companyCapitalStatusList.size() > 0) {

			for (int i = 0; i < enterprise_reportYear_companyCapitalStatusList.size(); i++) {
				Enterprise_reportYear_companyCapitalStatus enterprise_reportYear_companyCapitalStatus = enterprise_reportYear_companyCapitalStatusList.get(i);
										
						 params[i][2-2] = enterprise_reportYear_companyCapitalStatus.getRegNumber();				
						 params[i][3-2] = enterprise_reportYear_companyCapitalStatus.getTotalAssets();				
						 params[i][4-2] = enterprise_reportYear_companyCapitalStatus.getTotalSales();				
						 params[i][5-2] = enterprise_reportYear_companyCapitalStatus.getMainBusinessIncome();				
						 params[i][6-2] = enterprise_reportYear_companyCapitalStatus.getTotalTax();				
						 params[i][7-2] = enterprise_reportYear_companyCapitalStatus.getTotalLliabilities();				
						 params[i][8-2] = enterprise_reportYear_companyCapitalStatus.getTotalProfits();				
						 params[i][9-2] = enterprise_reportYear_companyCapitalStatus.getNetProfit();				
						 params[i][10-2] = enterprise_reportYear_companyCapitalStatus.getTotalEquity();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_reportYear_companyCapitalStatus", e);
		}

		return result;
	}
}
