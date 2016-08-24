package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_chattelMortgage;

/**
 * <p>
 * Title: IndustryCommerce_chattelMortgageDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_动产抵押信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Apr 09 09:59:56 CST 2015
 */
public class IndustryCommerce_chattelMortgageDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_chattelMortgageDao.class);

	/***
	 * 查询指定条数的industryCommerce_chattelMortgage集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_chattelMortgage(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_chattelMortgage  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_chattelMortgage", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_chattelMortgage
	 * 
	 * @param industryCommerce_chattelMortgage	 * @return
	 */
	public static int updateIndustryCommerce_chattelMortgage(IndustryCommerce_chattelMortgage industryCommerce_chattelMortgage) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_chattelMortgage set regNumber=?,morepripid=?,morregcno=?,mortgagorpripid=?,pefperform=?,pefperto=?,priclasecam=?,regidate=?,regorg=?,remark=?,type=?,warcov=? where regNumber=? ");
		Object[] params = { industryCommerce_chattelMortgage.getRegNumber(),industryCommerce_chattelMortgage.getMorepripid(),industryCommerce_chattelMortgage.getMorregcno(),industryCommerce_chattelMortgage.getMortgagorpripid(),industryCommerce_chattelMortgage.getPefperform(),industryCommerce_chattelMortgage.getPefperto(),industryCommerce_chattelMortgage.getPriclasecam(),industryCommerce_chattelMortgage.getRegidate(),industryCommerce_chattelMortgage.getRegorg(),industryCommerce_chattelMortgage.getRemark(),industryCommerce_chattelMortgage.getType(),industryCommerce_chattelMortgage.getWarcov(),industryCommerce_chattelMortgage.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_chattelMortgage", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_chattelMortgage插入数据
	 * 
	 * @param industryCommerce_chattelMortgage	 * @return
	 */
	public static int insertIndustryCommerce_chattelMortgage(IndustryCommerce_chattelMortgage industryCommerce_chattelMortgage) {

		int result = 0;
		
		String sql = "insert into industryCommerce_chattelMortgage (regNumber,morepripid,morregcno,mortgagorpripid,pefperform,pefperto,priclasecam,regidate,regorg,remark,type,warcov) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_chattelMortgage.getRegNumber(),industryCommerce_chattelMortgage.getMorepripid(),industryCommerce_chattelMortgage.getMorregcno(),industryCommerce_chattelMortgage.getMortgagorpripid(),industryCommerce_chattelMortgage.getPefperform(),industryCommerce_chattelMortgage.getPefperto(),industryCommerce_chattelMortgage.getPriclasecam(),industryCommerce_chattelMortgage.getRegidate(),industryCommerce_chattelMortgage.getRegorg(),industryCommerce_chattelMortgage.getRemark(),industryCommerce_chattelMortgage.getType(),industryCommerce_chattelMortgage.getWarcov()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_chattelMortgage", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_chattelMortgage数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_chattelMortgageByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,morepripid,morregcno,mortgagorpripid,pefperform,pefperto,priclasecam,regidate,regorg,remark,type,warcov from industryCommerce_chattelMortgage where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_chattelMortgage", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_chattelMortgage(List<IndustryCommerce_chattelMortgage> industryCommerce_chattelMortgageList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_chattelMortgage (regNumber,morepripid,morregcno,mortgagorpripid,pefperform,pefperto,priclasecam,regidate,regorg,remark,type,warcov) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_chattelMortgageList.size()][12];
		if (industryCommerce_chattelMortgageList != null && industryCommerce_chattelMortgageList.size() > 0) {

			for (int i = 0; i < industryCommerce_chattelMortgageList.size(); i++) {
				IndustryCommerce_chattelMortgage industryCommerce_chattelMortgage = industryCommerce_chattelMortgageList.get(i);
										
						 params[i][0] = industryCommerce_chattelMortgage.getRegNumber();				
						 params[i][1] = industryCommerce_chattelMortgage.getMorepripid();				
						 params[i][2] = industryCommerce_chattelMortgage.getMorregcno();				
						 params[i][3] = industryCommerce_chattelMortgage.getMortgagorpripid();				
						 params[i][4] = industryCommerce_chattelMortgage.getPefperform();				
						 params[i][5] = industryCommerce_chattelMortgage.getPefperto();				
						 params[i][6] = industryCommerce_chattelMortgage.getPriclasecam();				
						 params[i][7] = industryCommerce_chattelMortgage.getRegidate();				
						 params[i][8] = industryCommerce_chattelMortgage.getRegorg();				
						 params[i][9] = industryCommerce_chattelMortgage.getRemark();				
						 params[i][10] = industryCommerce_chattelMortgage.getType();				
						 params[i][11] = industryCommerce_chattelMortgage.getWarcov();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_chattelMortgage", e);
		}

		return result;
	}
}
