package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_spotChecks;

/**
 * <p>
 * Title: IndustryCommerce_spotChecksDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_抽查检查结果表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Mon Apr 13 13:59:50 CST 2015
 */
public class IndustryCommerce_spotChecksDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_spotChecksDao.class);

	/***
	 * 查询指定条数的industryCommerce_spotChecks集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_spotChecks(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_spotChecks  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_spotChecks", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_spotChecks
	 * 
	 * @param industryCommerce_spotChecks	 * @return
	 */
	public static int updateIndustryCommerce_spotChecks(IndustryCommerce_spotChecks industryCommerce_spotChecks) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_spotChecks set regNumber=?,entname=?,enttype=?,insauth=?,insdate=?,insres=?,instype=?,pid=?,pripid=?,regno=?,updatetime=? where regNumber=? ");
		Object[] params = { industryCommerce_spotChecks.getRegNumber(),industryCommerce_spotChecks.getEntname(),industryCommerce_spotChecks.getEnttype(),industryCommerce_spotChecks.getInsauth(),industryCommerce_spotChecks.getInsdate(),industryCommerce_spotChecks.getInsres(),industryCommerce_spotChecks.getInstype(),industryCommerce_spotChecks.getPid(),industryCommerce_spotChecks.getPripid(),industryCommerce_spotChecks.getRegno(),industryCommerce_spotChecks.getUpdatetime(),industryCommerce_spotChecks.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_spotChecks", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_spotChecks插入数据
	 * 
	 * @param industryCommerce_spotChecks	 * @return
	 */
	public static int insertIndustryCommerce_spotChecks(IndustryCommerce_spotChecks industryCommerce_spotChecks) {

		int result = 0;
		
		String sql = "insert into industryCommerce_spotChecks (regNumber,entname,enttype,insauth,insdate,insres,instype,pid,pripid,regno,updatetime) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_spotChecks.getRegNumber(),industryCommerce_spotChecks.getEntname(),industryCommerce_spotChecks.getEnttype(),industryCommerce_spotChecks.getInsauth(),industryCommerce_spotChecks.getInsdate(),industryCommerce_spotChecks.getInsres(),industryCommerce_spotChecks.getInstype(),industryCommerce_spotChecks.getPid(),industryCommerce_spotChecks.getPripid(),industryCommerce_spotChecks.getRegno(),industryCommerce_spotChecks.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_spotChecks", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_spotChecks数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_spotChecksByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,entname,enttype,insauth,insdate,insres,instype,pid,pripid,regno,updatetime from industryCommerce_spotChecks where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_spotChecks", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_spotChecks(List<IndustryCommerce_spotChecks> industryCommerce_spotChecksList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_spotChecks (regNumber,entname,enttype,insauth,insdate,insres,instype,pid,pripid,regno,updatetime) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_spotChecksList.size()][11];
		if (industryCommerce_spotChecksList != null && industryCommerce_spotChecksList.size() > 0) {

			for (int i = 0; i < industryCommerce_spotChecksList.size(); i++) {
				IndustryCommerce_spotChecks industryCommerce_spotChecks = industryCommerce_spotChecksList.get(i);
										
						 params[i][0] = industryCommerce_spotChecks.getRegNumber();				
						 params[i][1] = industryCommerce_spotChecks.getEntname();				
						 params[i][2] = industryCommerce_spotChecks.getEnttype();				
						 params[i][3] = industryCommerce_spotChecks.getInsauth();				
						 params[i][4] = industryCommerce_spotChecks.getInsdate();				
						 params[i][5] = industryCommerce_spotChecks.getInsres();				
						 params[i][6] = industryCommerce_spotChecks.getInstype();				
						 params[i][7] = industryCommerce_spotChecks.getPid();				
						 params[i][8] = industryCommerce_spotChecks.getPripid();				
						 params[i][9] = industryCommerce_spotChecks.getRegno();				
						 params[i][10] = industryCommerce_spotChecks.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_spotChecks", e);
		}

		return result;
	}
}
