package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_yearReport_shareholderCapital;

/**
 * <p>
 * Title: Enterprise_yearReport_shareholderCapitalDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_股东及出资信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class Enterprise_yearReport_shareholderCapitalDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_yearReport_shareholderCapitalDao.class);

	/***
	 * 查询指定条数的enterprise_yearReport_shareholderCapital集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_yearReport_shareholderCapital(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_yearReport_shareholderCapital  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_yearReport_shareholderCapital", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_yearReport_shareholderCapital
	 * 
	 * @param enterprise_yearReport_shareholderCapital	 * @return
	 */
	public static int updateEnterprise_yearReport_shareholderCapital(Enterprise_yearReport_shareholderCapital enterprise_yearReport_shareholderCapital) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_yearReport_shareholderCapital set regNumber=?,shareholder=?,subscriptions=?,subscriptionDate=?,subscriptionType=?,realAmount=?,realAmountDate=?,realAmountType=? where regNumber=? ");
		Object[] params = { enterprise_yearReport_shareholderCapital.getRegNumber(),enterprise_yearReport_shareholderCapital.getShareholder(),enterprise_yearReport_shareholderCapital.getSubscriptions(),enterprise_yearReport_shareholderCapital.getSubscriptionDate(),enterprise_yearReport_shareholderCapital.getSubscriptionType(),enterprise_yearReport_shareholderCapital.getRealAmount(),enterprise_yearReport_shareholderCapital.getRealAmountDate(),enterprise_yearReport_shareholderCapital.getRealAmountType(),enterprise_yearReport_shareholderCapital.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_yearReport_shareholderCapital", e);
		}
		return result;
	}

	/***
	 * 向enterprise_yearReport_shareholderCapital插入数据
	 * 
	 * @param enterprise_yearReport_shareholderCapital	 * @return
	 */
	public static int insertEnterprise_yearReport_shareholderCapital(Enterprise_yearReport_shareholderCapital enterprise_yearReport_shareholderCapital) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,shareholder,subscriptions,subscriptionDate,subscriptionType,realAmount,realAmountDate,realAmountType) values(?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_yearReport_shareholderCapital.getRegNumber(),enterprise_yearReport_shareholderCapital.getShareholder(),enterprise_yearReport_shareholderCapital.getSubscriptions(),enterprise_yearReport_shareholderCapital.getSubscriptionDate(),enterprise_yearReport_shareholderCapital.getSubscriptionType(),enterprise_yearReport_shareholderCapital.getRealAmount(),enterprise_yearReport_shareholderCapital.getRealAmountDate(),enterprise_yearReport_shareholderCapital.getRealAmountType()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_yearReport_shareholderCapital", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_yearReport_shareholderCapital数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_yearReport_shareholderCapitalByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,shareholder,subscriptions,subscriptionDate,subscriptionType,realAmount,realAmountDate,realAmountType from enterprise_yearReport_shareholderCapital where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_yearReport_shareholderCapital", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_yearReport_shareholderCapital(List<Enterprise_yearReport_shareholderCapital> enterprise_yearReport_shareholderCapitalList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,shareholder,subscriptions,subscriptionDate,subscriptionType,realAmount,realAmountDate,realAmountType) values(?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_yearReport_shareholderCapitalList.size()][9-1];
		if (enterprise_yearReport_shareholderCapitalList != null && enterprise_yearReport_shareholderCapitalList.size() > 0) {

			for (int i = 0; i < enterprise_yearReport_shareholderCapitalList.size(); i++) {
				Enterprise_yearReport_shareholderCapital enterprise_yearReport_shareholderCapital = enterprise_yearReport_shareholderCapitalList.get(i);
										
						 params[i][2-2] = enterprise_yearReport_shareholderCapital.getRegNumber();				
						 params[i][3-2] = enterprise_yearReport_shareholderCapital.getShareholder();				
						 params[i][4-2] = enterprise_yearReport_shareholderCapital.getSubscriptions();				
						 params[i][5-2] = enterprise_yearReport_shareholderCapital.getSubscriptionDate();				
						 params[i][6-2] = enterprise_yearReport_shareholderCapital.getSubscriptionType();				
						 params[i][7-2] = enterprise_yearReport_shareholderCapital.getRealAmount();				
						 params[i][8-2] = enterprise_yearReport_shareholderCapital.getRealAmountDate();				
						 params[i][9-2] = enterprise_yearReport_shareholderCapital.getRealAmountType();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_yearReport_shareholderCapital", e);
		}

		return result;
	}
}
