package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_shareholderCapital;

/**
 * <p>
 * Title: Enterprise_shareholderCapitalDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_股东及出资信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Apr 02 10:25:08 CST 2015
 */
public class Enterprise_shareholderCapitalDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_shareholderCapitalDao.class);

	/***
	 * 查询指定条数的enterprise_shareholderCapital集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_shareholderCapital(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_shareholderCapital  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_shareholderCapital", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_shareholderCapital
	 * 
	 * @param enterprise_shareholderCapital	 * @return
	 */
	public static int updateEnterprise_shareholderCapital(Enterprise_shareholderCapital enterprise_shareholderCapital) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_shareholderCapital set regNumber=?,shareholder=?,subscriptions=?,paidAmount=?,subType=?,subAmount=?,subDate=?,realType=?,realAmount=?,realDate=?,pripid=?,recid=? where regNumber=? ");
		Object[] params = { enterprise_shareholderCapital.getRegNumber(),enterprise_shareholderCapital.getShareholder(),enterprise_shareholderCapital.getSubscriptions(),enterprise_shareholderCapital.getPaidAmount(),enterprise_shareholderCapital.getSubType(),enterprise_shareholderCapital.getSubAmount(),enterprise_shareholderCapital.getSubDate(),enterprise_shareholderCapital.getRealType(),enterprise_shareholderCapital.getRealAmount(),enterprise_shareholderCapital.getRealDate(),enterprise_shareholderCapital.getPripid(),enterprise_shareholderCapital.getRecid(),enterprise_shareholderCapital.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_shareholderCapital", e);
		}
		return result;
	}

	/***
	 * 向enterprise_shareholderCapital插入数据
	 * 
	 * @param enterprise_shareholderCapital	 * @return
	 */
	public static int insertEnterprise_shareholderCapital(Enterprise_shareholderCapital enterprise_shareholderCapital) {

		int result = 0;
		
		String sql = "insert into enterprise_shareholderCapital (regNumber,shareholder,subscriptions,paidAmount,subType,subAmount,subDate,realType,realAmount,realDate,pripid,recid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_shareholderCapital.getRegNumber(),enterprise_shareholderCapital.getShareholder(),enterprise_shareholderCapital.getSubscriptions(),enterprise_shareholderCapital.getPaidAmount(),enterprise_shareholderCapital.getSubType(),enterprise_shareholderCapital.getSubAmount(),enterprise_shareholderCapital.getSubDate(),enterprise_shareholderCapital.getRealType(),enterprise_shareholderCapital.getRealAmount(),enterprise_shareholderCapital.getRealDate(),enterprise_shareholderCapital.getPripid(),enterprise_shareholderCapital.getRecid()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_shareholderCapital", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_shareholderCapital数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_shareholderCapitalByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,shareholder,subscriptions,paidAmount,subType,subAmount,subDate,realType,realAmount,realDate,pripid,recid from enterprise_shareholderCapital where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_shareholderCapital", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_shareholderCapital(List<Enterprise_shareholderCapital> enterprise_shareholderCapitalList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,shareholder,subscriptions,paidAmount,subType,subAmount,subDate,realType,realAmount,realDate,pripid,recid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_shareholderCapitalList.size()][12];
		if (enterprise_shareholderCapitalList != null && enterprise_shareholderCapitalList.size() > 0) {

			for (int i = 0; i < enterprise_shareholderCapitalList.size(); i++) {
				Enterprise_shareholderCapital enterprise_shareholderCapital = enterprise_shareholderCapitalList.get(i);
										
						 params[i][0] = enterprise_shareholderCapital.getRegNumber();				
						 params[i][1] = enterprise_shareholderCapital.getShareholder();				
						 params[i][2] = enterprise_shareholderCapital.getSubscriptions();				
						 params[i][3] = enterprise_shareholderCapital.getPaidAmount();				
						 params[i][4] = enterprise_shareholderCapital.getSubType();				
						 params[i][5] = enterprise_shareholderCapital.getSubAmount();				
						 params[i][6] = enterprise_shareholderCapital.getSubDate();				
						 params[i][7] = enterprise_shareholderCapital.getRealType();				
						 params[i][8] = enterprise_shareholderCapital.getRealAmount();				
						 params[i][9] = enterprise_shareholderCapital.getRealDate();				
						 params[i][10] = enterprise_shareholderCapital.getPripid();				
						 params[i][11] = enterprise_shareholderCapital.getRecid();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_shareholderCapital", e);
		}

		return result;
	}
}
