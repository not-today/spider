package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_equityChange;

/**
 * <p>
 * Title: Enterprise_equityChangeDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_股权变更信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Apr 02 16:40:51 CST 2015
 */
public class Enterprise_equityChangeDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_equityChangeDao.class);

	/***
	 * 查询指定条数的enterprise_equityChange集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_equityChange(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_equityChange  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_equityChange", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_equityChange
	 * 
	 * @param enterprise_equityChange	 * @return
	 */
	public static int updateEnterprise_equityChange(Enterprise_equityChange enterprise_equityChange) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_equityChange set regNumber=?,inv=?,transamprpre=?,transampraft=?,altdate=?,ifpub=?,pid=?,pripid=?,updatetime=? where regNumber=? ");
		Object[] params = { enterprise_equityChange.getRegNumber(),enterprise_equityChange.getInv(),enterprise_equityChange.getTransamprpre(),enterprise_equityChange.getTransampraft(),enterprise_equityChange.getAltdate(),enterprise_equityChange.getIfpub(),enterprise_equityChange.getPid(),enterprise_equityChange.getPripid(),enterprise_equityChange.getUpdatetime(),enterprise_equityChange.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_equityChange", e);
		}
		return result;
	}

	/***
	 * 向enterprise_equityChange插入数据
	 * 
	 * @param enterprise_equityChange	 * @return
	 */
	public static int insertEnterprise_equityChange(Enterprise_equityChange enterprise_equityChange) {

		int result = 0;
		
		String sql = "insert into enterprise_equityChange (regNumber,inv,transamprpre,transampraft,altdate,ifpub,pid,pripid,updatetime) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_equityChange.getRegNumber(),enterprise_equityChange.getInv(),enterprise_equityChange.getTransamprpre(),enterprise_equityChange.getTransampraft(),enterprise_equityChange.getAltdate(),enterprise_equityChange.getIfpub(),enterprise_equityChange.getPid(),enterprise_equityChange.getPripid(),enterprise_equityChange.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_equityChange", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_equityChange数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_equityChangeByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,inv,transamprpre,transampraft,altdate,ifpub,pid,pripid,updatetime from enterprise_equityChange where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_equityChange", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_equityChange(List<Enterprise_equityChange> enterprise_equityChangeList) {
		int result[] = {};
		
		
		String sql = "insert into enterprise_equityChange (regNumber,inv,transamprpre,transampraft,altdate,ifpub,pid,pripid,updatetime) values(?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_equityChangeList.size()][9];
		if (enterprise_equityChangeList != null && enterprise_equityChangeList.size() > 0) {

			for (int i = 0; i < enterprise_equityChangeList.size(); i++) {
				Enterprise_equityChange enterprise_equityChange = enterprise_equityChangeList.get(i);
										
						 params[i][0] = enterprise_equityChange.getRegNumber();				
						 params[i][1] = enterprise_equityChange.getInv();				
						 params[i][2] = enterprise_equityChange.getTransamprpre();				
						 params[i][3] = enterprise_equityChange.getTransampraft();				
						 params[i][4] = enterprise_equityChange.getAltdate();				
						 params[i][5] = enterprise_equityChange.getIfpub();				
						 params[i][6] = enterprise_equityChange.getPid();				
						 params[i][7] = enterprise_equityChange.getPripid();				
						 params[i][8] = enterprise_equityChange.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_equityChange", e);
		}

		return result;
	}
}
