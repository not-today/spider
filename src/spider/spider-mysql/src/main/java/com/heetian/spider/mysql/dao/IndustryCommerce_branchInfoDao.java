package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_branchInfo;

/**
 * <p>
 * Title: IndustryCommerce_branchInfoDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_分支机构表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Mar 26 11:09:34 CST 2015
 */
public class IndustryCommerce_branchInfoDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_branchInfoDao.class);

	/***
	 * 查询指定条数的industryCommerce_branchInfo集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_branchInfo(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_branchInfo  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_branchInfo", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_branchInfo
	 * 
	 * @param industryCommerce_branchInfo	 * @return
	 */
	public static int updateIndustryCommerce_branchInfo(IndustryCommerce_branchInfo industryCommerce_branchInfo) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_branchInfo set regNumber=?,brname=?,regorg=?,pripid=?,recid=?,regno=?,xzqh=? where regNumber=? ");
		Object[] params = { industryCommerce_branchInfo.getRegNumber(),industryCommerce_branchInfo.getBrname(),industryCommerce_branchInfo.getRegorg(),industryCommerce_branchInfo.getPripid(),industryCommerce_branchInfo.getRecid(),industryCommerce_branchInfo.getRegno(),industryCommerce_branchInfo.getXzqh(),industryCommerce_branchInfo.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_branchInfo", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_branchInfo插入数据
	 * 
	 * @param industryCommerce_branchInfo	 * @return
	 */
	public static int insertIndustryCommerce_branchInfo(IndustryCommerce_branchInfo industryCommerce_branchInfo) {

		int result = 0;
		
		String sql = "insert into industryCommerce_branchInfo (regNumber,brname,regorg,pripid,recid,regno,xzqh) values(?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_branchInfo.getRegNumber(),industryCommerce_branchInfo.getBrname(),industryCommerce_branchInfo.getRegorg(),industryCommerce_branchInfo.getPripid(),industryCommerce_branchInfo.getRecid(),industryCommerce_branchInfo.getRegno(),industryCommerce_branchInfo.getXzqh()};

		try {
			//logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_branchInfo", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_branchInfo数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_branchInfoByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,brname,regorg,pripid,recid,regno,xzqh from industryCommerce_branchInfo where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_branchInfo", e);
		} 

		return null;
	}
	/**
	 * 根据企业信息号删除
	 * @param regNumber
	 * @return
	 */
	public static void deleteIndustryCommerce_branchInfoByRegNumber(String regNumber) {
		try {
			String sql = "delete from industryCommerce_branchInfo where regNumber='"+regNumber+"'";
			queryRunner.update(sql);
		} catch (SQLException e) {
			logger.error("查询industryCommerce_branchInfo", e);
		} 
	}
	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_branchInfo(List<IndustryCommerce_branchInfo> industryCommerce_branchInfoList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_branchInfo (regNumber,brname,regorg,pripid,recid,regno,xzqh) values(?,?,?,?,?,?,?)";
		
		//logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_branchInfoList.size()][7];
		if (industryCommerce_branchInfoList != null && industryCommerce_branchInfoList.size() > 0) {

			for (int i = 0; i < industryCommerce_branchInfoList.size(); i++) {
				IndustryCommerce_branchInfo industryCommerce_branchInfo = industryCommerce_branchInfoList.get(i);
										
						 params[i][0] = industryCommerce_branchInfo.getRegNumber();				
						 params[i][1] = industryCommerce_branchInfo.getBrname();				
						 params[i][2] = industryCommerce_branchInfo.getRegorg();				
						 params[i][3] = industryCommerce_branchInfo.getPripid();				
						 params[i][4] = industryCommerce_branchInfo.getRecid();				
						 params[i][5] = industryCommerce_branchInfo.getRegno();				
						 params[i][6] = industryCommerce_branchInfo.getXzqh();				
			}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_branchInfo", e);
		}
		return result;
	}
	
}
