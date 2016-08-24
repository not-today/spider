package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_shareholderPaidin;

/**
 * <p>
 * Title: Enterprise_shareholderPaidinDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_股东出资实缴表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Apr 02 17:37:46 CST 2015
 */
public class Enterprise_shareholderPaidinDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_shareholderPaidinDao.class);

	/***
	 * 查询指定条数的enterprise_shareholderPaidin集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_shareholderPaidin(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_shareholderPaidin  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_shareholderPaidin", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_shareholderPaidin
	 * 
	 * @param enterprise_shareholderPaidin	 * @return
	 */
	public static int updateEnterprise_shareholderPaidin(Enterprise_shareholderPaidin enterprise_shareholderPaidin) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_shareholderPaidin set regNumber=?,inv=?,condate=?,acconam=?,currency=?,czpid=?,ifpub=?,pid=?,updatetime=? where regNumber=? ");
		Object[] params = { enterprise_shareholderPaidin.getRegNumber(),enterprise_shareholderPaidin.getInv(),enterprise_shareholderPaidin.getCondate(),enterprise_shareholderPaidin.getAcconam(),enterprise_shareholderPaidin.getCurrency(),enterprise_shareholderPaidin.getCzpid(),enterprise_shareholderPaidin.getIfpub(),enterprise_shareholderPaidin.getPid(),enterprise_shareholderPaidin.getUpdatetime(),enterprise_shareholderPaidin.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_shareholderPaidin", e);
		}
		return result;
	}

	/***
	 * 向enterprise_shareholderPaidin插入数据
	 * 
	 * @param enterprise_shareholderPaidin	 * @return
	 */
	public static int insertEnterprise_shareholderPaidin(Enterprise_shareholderPaidin enterprise_shareholderPaidin) {

		int result = 0;
		
		String sql = "insert into enterprise_shareholderPaidin (regNumber,inv,condate,acconam,currency,czpid,ifpub,pid,updatetime) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_shareholderPaidin.getRegNumber(),enterprise_shareholderPaidin.getInv(),enterprise_shareholderPaidin.getCondate(),enterprise_shareholderPaidin.getAcconam(),enterprise_shareholderPaidin.getCurrency(),enterprise_shareholderPaidin.getCzpid(),enterprise_shareholderPaidin.getIfpub(),enterprise_shareholderPaidin.getPid(),enterprise_shareholderPaidin.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_shareholderPaidin", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_shareholderPaidin数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_shareholderPaidinByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,inv,condate,acconam,currency,czpid,ifpub,pid,updatetime from enterprise_shareholderPaidin where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_shareholderPaidin", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_shareholderPaidin(List<Enterprise_shareholderPaidin> enterprise_shareholderPaidinList) {
		int result[] = {};
		
		
		String sql = "insert into enterprise_shareholderPaidin (regNumber,inv,condate,acconam,currency,czpid,ifpub,pid,updatetime) values(?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_shareholderPaidinList.size()][9];
		if (enterprise_shareholderPaidinList != null && enterprise_shareholderPaidinList.size() > 0) {

			for (int i = 0; i < enterprise_shareholderPaidinList.size(); i++) {
				Enterprise_shareholderPaidin enterprise_shareholderPaidin = enterprise_shareholderPaidinList.get(i);
										
						 params[i][0] = enterprise_shareholderPaidin.getRegNumber();				
						 params[i][1] = enterprise_shareholderPaidin.getInv();				
						 params[i][2] = enterprise_shareholderPaidin.getCondate();				
						 params[i][3] = enterprise_shareholderPaidin.getAcconam();				
						 params[i][4] = enterprise_shareholderPaidin.getCurrency();				
						 params[i][5] = enterprise_shareholderPaidin.getCzpid();				
						 params[i][6] = enterprise_shareholderPaidin.getIfpub();				
						 params[i][7] = enterprise_shareholderPaidin.getPid();				
						 params[i][8] = enterprise_shareholderPaidin.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_shareholderPaidin", e);
		}

		return result;
	}
}
