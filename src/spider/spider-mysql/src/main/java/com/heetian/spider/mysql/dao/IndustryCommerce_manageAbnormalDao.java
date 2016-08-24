package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_manageAbnormal;

/**
 * <p>
 * Title: IndustryCommerce_manageAbnormalDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_经营异常信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Tue Apr 21 09:28:02 CST 2015
 */
public class IndustryCommerce_manageAbnormalDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_manageAbnormalDao.class);

	/***
	 * 查询指定条数的industryCommerce_manageAbnormal集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_manageAbnormal(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_manageAbnormal  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_manageAbnormal", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_manageAbnormal
	 * 
	 * @param industryCommerce_manageAbnormal	 * @return
	 */
	public static int updateIndustryCommerce_manageAbnormal(IndustryCommerce_manageAbnormal industryCommerce_manageAbnormal) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_manageAbnormal set regNumber=?,specause=?,abntime=?,remexcpres=?,remdate=?,decorg=?,pid=?,updatetime=? where regNumber=? ");
		Object[] params = { industryCommerce_manageAbnormal.getRegNumber(),industryCommerce_manageAbnormal.getSpecause(),industryCommerce_manageAbnormal.getAbntime(),industryCommerce_manageAbnormal.getRemexcpres(),industryCommerce_manageAbnormal.getRemdate(),industryCommerce_manageAbnormal.getDecorg(),industryCommerce_manageAbnormal.getPid(),industryCommerce_manageAbnormal.getUpdatetime(),industryCommerce_manageAbnormal.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_manageAbnormal", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_manageAbnormal插入数据
	 * 
	 * @param industryCommerce_manageAbnormal	 * @return
	 */
	public static int insertIndustryCommerce_manageAbnormal(IndustryCommerce_manageAbnormal industryCommerce_manageAbnormal) {

		int result = 0;
		
		String sql = "insert into industryCommerce_manageAbnormal (regNumber,specause,abntime,remexcpres,remdate,decorg,pid,updatetime) values(?,?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_manageAbnormal.getRegNumber(),industryCommerce_manageAbnormal.getSpecause(),industryCommerce_manageAbnormal.getAbntime(),industryCommerce_manageAbnormal.getRemexcpres(),industryCommerce_manageAbnormal.getRemdate(),industryCommerce_manageAbnormal.getDecorg(),industryCommerce_manageAbnormal.getPid(),industryCommerce_manageAbnormal.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_manageAbnormal", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_manageAbnormal数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_manageAbnormalByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,specause,abntime,remexcpres,remdate,decorg,pid,updatetime from industryCommerce_manageAbnormal where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_manageAbnormal", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_manageAbnormal(List<IndustryCommerce_manageAbnormal> industryCommerce_manageAbnormalList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_manageAbnormal (regNumber,specause,abntime,remexcpres,remdate,decorg,pid,updatetime) values(?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_manageAbnormalList.size()][8];
		if (industryCommerce_manageAbnormalList != null && industryCommerce_manageAbnormalList.size() > 0) {

			for (int i = 0; i < industryCommerce_manageAbnormalList.size(); i++) {
				IndustryCommerce_manageAbnormal industryCommerce_manageAbnormal = industryCommerce_manageAbnormalList.get(i);
										
						 params[i][0] = industryCommerce_manageAbnormal.getRegNumber();				
						 params[i][1] = industryCommerce_manageAbnormal.getSpecause();				
						 params[i][2] = industryCommerce_manageAbnormal.getAbntime();				
						 params[i][3] = industryCommerce_manageAbnormal.getRemexcpres();				
						 params[i][4] = industryCommerce_manageAbnormal.getRemdate();				
						 params[i][5] = industryCommerce_manageAbnormal.getDecorg();				
						 params[i][6] = industryCommerce_manageAbnormal.getPid();				
						 params[i][7] = industryCommerce_manageAbnormal.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_manageAbnormal", e);
		}

		return result;
	}
}
