package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_reportYear_foreignGuarantee;

/**
 * <p>
 * Title: Enterprise_reportYear_foreignGuaranteeDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_对外提供保证担保信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class Enterprise_reportYear_foreignGuaranteeDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_reportYear_foreignGuaranteeDao.class);

	/***
	 * 查询指定条数的enterprise_reportYear_foreignGuarantee集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_foreignGuarantee(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_reportYear_foreignGuarantee  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_foreignGuarantee", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_reportYear_foreignGuarantee
	 * 
	 * @param enterprise_reportYear_foreignGuarantee	 * @return
	 */
	public static int updateEnterprise_reportYear_foreignGuarantee(Enterprise_reportYear_foreignGuarantee enterprise_reportYear_foreignGuarantee) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_reportYear_foreignGuarantee set regNumber=?,creditor=?,obligor=?,creditorType=?,creditorAmount=?,debtMaturity=?,assureTime=?,assureType=?,assureRange=? where regNumber=? ");
		Object[] params = { enterprise_reportYear_foreignGuarantee.getRegNumber(),enterprise_reportYear_foreignGuarantee.getCreditor(),enterprise_reportYear_foreignGuarantee.getObligor(),enterprise_reportYear_foreignGuarantee.getCreditorType(),enterprise_reportYear_foreignGuarantee.getCreditorAmount(),enterprise_reportYear_foreignGuarantee.getDebtMaturity(),enterprise_reportYear_foreignGuarantee.getAssureTime(),enterprise_reportYear_foreignGuarantee.getAssureType(),enterprise_reportYear_foreignGuarantee.getAssureRange(),enterprise_reportYear_foreignGuarantee.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_reportYear_foreignGuarantee", e);
		}
		return result;
	}

	/***
	 * 向enterprise_reportYear_foreignGuarantee插入数据
	 * 
	 * @param enterprise_reportYear_foreignGuarantee	 * @return
	 */
	public static int insertEnterprise_reportYear_foreignGuarantee(Enterprise_reportYear_foreignGuarantee enterprise_reportYear_foreignGuarantee) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,creditor,obligor,creditorType,creditorAmount,debtMaturity,assureTime,assureType,assureRange) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_reportYear_foreignGuarantee.getRegNumber(),enterprise_reportYear_foreignGuarantee.getCreditor(),enterprise_reportYear_foreignGuarantee.getObligor(),enterprise_reportYear_foreignGuarantee.getCreditorType(),enterprise_reportYear_foreignGuarantee.getCreditorAmount(),enterprise_reportYear_foreignGuarantee.getDebtMaturity(),enterprise_reportYear_foreignGuarantee.getAssureTime(),enterprise_reportYear_foreignGuarantee.getAssureType(),enterprise_reportYear_foreignGuarantee.getAssureRange()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_reportYear_foreignGuarantee", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_reportYear_foreignGuarantee数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_reportYear_foreignGuaranteeByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,creditor,obligor,creditorType,creditorAmount,debtMaturity,assureTime,assureType,assureRange from enterprise_reportYear_foreignGuarantee where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_reportYear_foreignGuarantee", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_reportYear_foreignGuarantee(List<Enterprise_reportYear_foreignGuarantee> enterprise_reportYear_foreignGuaranteeList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,creditor,obligor,creditorType,creditorAmount,debtMaturity,assureTime,assureType,assureRange) values(?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_reportYear_foreignGuaranteeList.size()][10-1];
		if (enterprise_reportYear_foreignGuaranteeList != null && enterprise_reportYear_foreignGuaranteeList.size() > 0) {

			for (int i = 0; i < enterprise_reportYear_foreignGuaranteeList.size(); i++) {
				Enterprise_reportYear_foreignGuarantee enterprise_reportYear_foreignGuarantee = enterprise_reportYear_foreignGuaranteeList.get(i);
										
						 params[i][2-2] = enterprise_reportYear_foreignGuarantee.getRegNumber();				
						 params[i][3-2] = enterprise_reportYear_foreignGuarantee.getCreditor();				
						 params[i][4-2] = enterprise_reportYear_foreignGuarantee.getObligor();				
						 params[i][5-2] = enterprise_reportYear_foreignGuarantee.getCreditorType();				
						 params[i][6-2] = enterprise_reportYear_foreignGuarantee.getCreditorAmount();				
						 params[i][7-2] = enterprise_reportYear_foreignGuarantee.getDebtMaturity();				
						 params[i][8-2] = enterprise_reportYear_foreignGuarantee.getAssureTime();				
						 params[i][9-2] = enterprise_reportYear_foreignGuarantee.getAssureType();				
						 params[i][10-2] = enterprise_reportYear_foreignGuarantee.getAssureRange();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_reportYear_foreignGuarantee", e);
		}

		return result;
	}
}
