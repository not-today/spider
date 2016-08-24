package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_mainPersonel;

/**
 * <p>
 * Title: IndustryCommerce_mainPersonnelDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_主要人员表表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Mar 19 18:12:07 CST 2015
 */
public class IndustryCommerce_mainPersonelDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_mainPersonelDao.class);

	/***
	 * 查询指定条数的industryCommerce_mainPersonnel集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_mainPersonel(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_mainPersonel  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_mainPersonel", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_mainPersonnel
	 * 
	 * @param industryCommerce_mainPersonnel	 * @return
	 */
	public static int updateIndustryCommerce_mainPersonnel(IndustryCommerce_mainPersonel industryCommerce_mainPersonnel) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_mainPersonel set regNumber=?,name=?,position=?,pripid=?,recid=?,xzqh=? where regNumber=? ");
		Object[] params = { industryCommerce_mainPersonnel.getRegNumber(),industryCommerce_mainPersonnel.getName(),industryCommerce_mainPersonnel.getPosition(),industryCommerce_mainPersonnel.getPripid(),industryCommerce_mainPersonnel.getRecid(),industryCommerce_mainPersonnel.getXzqh(),industryCommerce_mainPersonnel.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_mainPersonel", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_mainPersonnel插入数据
	 * 
	 * @param industryCommerce_mainPersonnel	 * @return
	 */
	public static int insertIndustryCommerce_mainPersonnel(IndustryCommerce_mainPersonel industryCommerce_mainPersonnel) {

		int result = 0;
		
		String sql = "insert into industryCommerce_mainPersonel (regNumber,name,position,pripid,recid,xzqh) values(?,?,?,?,?,?)";
		Object[] params = {industryCommerce_mainPersonnel.getRegNumber(),industryCommerce_mainPersonnel.getName(),industryCommerce_mainPersonnel.getPosition(),industryCommerce_mainPersonnel.getPripid(),industryCommerce_mainPersonnel.getRecid(),industryCommerce_mainPersonnel.getXzqh()};

		try {
			//logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_mainPersonel", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_mainPersonnel数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_mainPersonnelByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,name,position,pripid,recid,xzqh from industryCommerce_mainPersonel where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_mainPersonel", e);
		} 

		return null;
	}
	/**
	 * 根据企业注册号删除
	 * @param regNumber
	 * @return
	 */
	public static void deleteIndustryCommerce_mainPersonnelByRegNumber(String regNumber) {
		try {
			String sql = "delete from industryCommerce_mainPersonel where regNumber='"+regNumber+"'";
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
	public static int[] batchIndustryCommerce_mainPersonnel(List<IndustryCommerce_mainPersonel> industryCommerce_mainPersonnelList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_mainPersonel (regNumber,name,position,pripid,recid,xzqh) values(?,?,?,?,?,?)";
		
		//logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_mainPersonnelList.size()][6];
		if (industryCommerce_mainPersonnelList != null && industryCommerce_mainPersonnelList.size() > 0) {

			for (int i = 0; i < industryCommerce_mainPersonnelList.size(); i++) {
				IndustryCommerce_mainPersonel industryCommerce_mainPersonnel = industryCommerce_mainPersonnelList.get(i);
										
						 params[i][0] = industryCommerce_mainPersonnel.getRegNumber();				
						 params[i][1] = industryCommerce_mainPersonnel.getName();				
						 params[i][2] = industryCommerce_mainPersonnel.getPosition();				
						 params[i][3] = industryCommerce_mainPersonnel.getPripid();				
						 params[i][4] = industryCommerce_mainPersonnel.getRecid();				
						 params[i][5] = industryCommerce_mainPersonnel.getXzqh();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_mainPersonel", e);
		}

		return result;
	}
}
