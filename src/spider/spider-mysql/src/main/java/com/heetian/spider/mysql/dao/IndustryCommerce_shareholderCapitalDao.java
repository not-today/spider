package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_shareholderCapital;

/**
 * <p>
 * Title: IndustryCommerce_shareholderCapitalDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_股东及出资信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Fri Apr 03 22:28:45 CST 2015
 */
public class IndustryCommerce_shareholderCapitalDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_shareholderCapitalDao.class);

	/***
	 * 查询指定条数的industryCommerce_shareholderCapital集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_shareholderCapital(int number) {
		List<Object[]> arraylist = null;
		String sql = "select * from industryCommerce_shareholderCapital  limit "  + number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholderCapital", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_shareholderCapital
	 * 
	 * @param industryCommerce_shareholderCapital	 * @return
	 */
	public static int updateIndustryCommerce_shareholderCapital(IndustryCommerce_shareholderCapital industryCommerce_shareholderCapital) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_shareholderCapital set regNumber=?,inv=?,blicno=?,blictype=?,invtype=?,liacconam=?,lisubconam=?,pripid=?,recid=?,xzqh=? where regNumber=? ");
		Object[] params = { industryCommerce_shareholderCapital.getRegNumber(),industryCommerce_shareholderCapital.getInv(),industryCommerce_shareholderCapital.getBlicno(),industryCommerce_shareholderCapital.getBlictype(),industryCommerce_shareholderCapital.getInvtype(),industryCommerce_shareholderCapital.getLiacconam(),industryCommerce_shareholderCapital.getLisubconam(),industryCommerce_shareholderCapital.getPripid(),industryCommerce_shareholderCapital.getRecid(),industryCommerce_shareholderCapital.getXzqh(),industryCommerce_shareholderCapital.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_shareholderCapital", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_shareholderCapital插入数据
	 * 
	 * @param industryCommerce_shareholderCapital	 * @return
	 */
	public static int insertIndustryCommerce_shareholderCapital(IndustryCommerce_shareholderCapital industryCommerce_shareholderCapital) {

		int result = 0;
		
		String sql = "insert into industryCommerce_shareholderCapital (regNumber,inv,blicno,blictype,invtype,liacconam,lisubconam,pripid,recid,xzqh) values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_shareholderCapital.getRegNumber(),industryCommerce_shareholderCapital.getInv(),industryCommerce_shareholderCapital.getBlicno(),industryCommerce_shareholderCapital.getBlictype(),industryCommerce_shareholderCapital.getInvtype(),industryCommerce_shareholderCapital.getLiacconam(),industryCommerce_shareholderCapital.getLisubconam(),industryCommerce_shareholderCapital.getPripid(),industryCommerce_shareholderCapital.getRecid(),industryCommerce_shareholderCapital.getXzqh()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_shareholderCapital", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_shareholderCapital数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_shareholderCapitalByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,inv,blicno,blictype,invtype,liacconam,lisubconam,pripid,recid,xzqh from industryCommerce_shareholderCapital where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholderCapital", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_shareholderCapital(List<IndustryCommerce_shareholderCapital> industryCommerce_shareholderCapitalList) {
		int result[] = {};
		String sql = "insert into industryCommerce_shareholderCapital (regNumber,inv,blicno,blictype,invtype,liacconam,lisubconam,pripid,recid,xzqh) values(?,?,?,?,?,?,?,?,?,?)";
		//logger.info("本次执行sql："+sql);
		Object[][] params = new Object[industryCommerce_shareholderCapitalList.size()][10];
		if (industryCommerce_shareholderCapitalList != null && industryCommerce_shareholderCapitalList.size() > 0) {
			for (int i = 0; i < industryCommerce_shareholderCapitalList.size(); i++) {
				IndustryCommerce_shareholderCapital industryCommerce_shareholderCapital = industryCommerce_shareholderCapitalList.get(i);
				 params[i][0] = industryCommerce_shareholderCapital.getRegNumber();				
				 params[i][1] = industryCommerce_shareholderCapital.getInv();				
				 params[i][2] = industryCommerce_shareholderCapital.getBlicno();				
				 params[i][3] = industryCommerce_shareholderCapital.getBlictype();				
				 params[i][4] = industryCommerce_shareholderCapital.getInvtype();				
				 params[i][5] = industryCommerce_shareholderCapital.getLiacconam();				
				 params[i][6] = industryCommerce_shareholderCapital.getLisubconam();				
				 params[i][7] = industryCommerce_shareholderCapital.getPripid();				
				 params[i][8] = industryCommerce_shareholderCapital.getRecid();				
				 params[i][9] = industryCommerce_shareholderCapital.getXzqh();				
			}
		}
		try {
			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_shareholderCapital", e);
		}
		return result;
	}

	public static void deleteIndustryCommerce_shareholderCapital( String regNumber) {
		try {
			String sql = "delete from industryCommerce_shareholderCapital where regNumber='"+regNumber+"'";
			queryRunner.update(sql);
		} catch (SQLException e) {
			logger.error("查询industryCommerce_branchInfo", e);
		} 		
	}
}
