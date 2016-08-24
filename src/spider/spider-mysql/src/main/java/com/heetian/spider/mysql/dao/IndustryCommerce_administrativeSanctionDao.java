package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_administrativeSanction;

/**
 * <p>
 * Title: IndustryCommerce_administrativeSanctionDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_行政处罚信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Mon Apr 20 15:22:08 CST 2015
 */
public class IndustryCommerce_administrativeSanctionDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_administrativeSanctionDao.class);

	/***
	 * 查询指定条数的industryCommerce_administrativeSanction集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_administrativeSanction(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_administrativeSanction  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_administrativeSanction", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_administrativeSanction
	 * 
	 * @param industryCommerce_administrativeSanction	 * @return
	 */
	public static int updateIndustryCommerce_administrativeSanction(IndustryCommerce_administrativeSanction industryCommerce_administrativeSanction) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_administrativeSanction set regNumber=?,pendecno=?,illegacttype=?,penam=?,forfam=?,penauth=?,pendecissdate=?,pentype=?,pripid=?,regno=?,remark=? where regNumber=? ");
		Object[] params = { industryCommerce_administrativeSanction.getRegNumber(),industryCommerce_administrativeSanction.getPendecno(),industryCommerce_administrativeSanction.getIllegacttype(),industryCommerce_administrativeSanction.getPenam(),industryCommerce_administrativeSanction.getForfam(),industryCommerce_administrativeSanction.getPenauth(),industryCommerce_administrativeSanction.getPendecissdate(),industryCommerce_administrativeSanction.getPentype(),industryCommerce_administrativeSanction.getPripid(),industryCommerce_administrativeSanction.getRegno(),industryCommerce_administrativeSanction.getRemark(),industryCommerce_administrativeSanction.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_administrativeSanction", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_administrativeSanction插入数据
	 * 
	 * @param industryCommerce_administrativeSanction	 * @return
	 */
	public static int insertIndustryCommerce_administrativeSanction(IndustryCommerce_administrativeSanction industryCommerce_administrativeSanction) {

		int result = 0;
		
		String sql = "insert into industryCommerce_administrativeSanction (regNumber,pendecno,illegacttype,penam,forfam,penauth,pendecissdate,pentype,pripid,regno,remark) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_administrativeSanction.getRegNumber(),industryCommerce_administrativeSanction.getPendecno(),industryCommerce_administrativeSanction.getIllegacttype(),industryCommerce_administrativeSanction.getPenam(),industryCommerce_administrativeSanction.getForfam(),industryCommerce_administrativeSanction.getPenauth(),industryCommerce_administrativeSanction.getPendecissdate(),industryCommerce_administrativeSanction.getPentype(),industryCommerce_administrativeSanction.getPripid(),industryCommerce_administrativeSanction.getRegno(),industryCommerce_administrativeSanction.getRemark()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_administrativeSanction", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_administrativeSanction数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_administrativeSanctionByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,pendecno,illegacttype,penam,forfam,penauth,pendecissdate,pentype,pripid,regno,remark from industryCommerce_administrativeSanction where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_administrativeSanction", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_administrativeSanction(List<IndustryCommerce_administrativeSanction> industryCommerce_administrativeSanctionList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_administrativeSanction (regNumber,pendecno,illegacttype,penam,forfam,penauth,pendecissdate,pentype,pripid,regno,remark) values(?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_administrativeSanctionList.size()][11];
		if (industryCommerce_administrativeSanctionList != null && industryCommerce_administrativeSanctionList.size() > 0) {

			for (int i = 0; i < industryCommerce_administrativeSanctionList.size(); i++) {
				IndustryCommerce_administrativeSanction industryCommerce_administrativeSanction = industryCommerce_administrativeSanctionList.get(i);
										
						 params[i][0] = industryCommerce_administrativeSanction.getRegNumber();				
						 params[i][1] = industryCommerce_administrativeSanction.getPendecno();				
						 params[i][2] = industryCommerce_administrativeSanction.getIllegacttype();				
						 params[i][3] = industryCommerce_administrativeSanction.getPenam();				
						 params[i][4] = industryCommerce_administrativeSanction.getForfam();				
						 params[i][5] = industryCommerce_administrativeSanction.getPenauth();				
						 params[i][6] = industryCommerce_administrativeSanction.getPendecissdate();				
						 params[i][7] = industryCommerce_administrativeSanction.getPentype();				
						 params[i][8] = industryCommerce_administrativeSanction.getPripid();				
						 params[i][9] = industryCommerce_administrativeSanction.getRegno();				
						 params[i][10] = industryCommerce_administrativeSanction.getRemark();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_administrativeSanction", e);
		}

		return result;
	}
}
