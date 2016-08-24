package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_equityPledge;

/**
 * <p>
 * Title: IndustryCommerce_equityPledgeDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_股权出质登记信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Mon Apr 13 12:19:28 CST 2015
 */
public class IndustryCommerce_equityPledgeDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_equityPledgeDao.class);

	/***
	 * 查询指定条数的industryCommerce_equityPledge集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_equityPledge(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from industryCommerce_equityPledge  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_equityPledge", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_equityPledge
	 * 
	 * @param industryCommerce_equityPledge	 * @return
	 */
	public static int updateIndustryCommerce_equityPledge(IndustryCommerce_equityPledge industryCommerce_equityPledge) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_equityPledge set regNumber=?,equityno=?,pledgor=?,blicno=?,impam=?,imporg=?,impmorblicno=?,equpledate=?,status=?,type=?,blictype=?,pledamunit=?,pripid=?,regcapcur=?,xzqh=? where regNumber=? ");
		Object[] params = { industryCommerce_equityPledge.getRegNumber(),industryCommerce_equityPledge.getEquityno(),industryCommerce_equityPledge.getPledgor(),industryCommerce_equityPledge.getBlicno(),industryCommerce_equityPledge.getImpam(),industryCommerce_equityPledge.getImporg(),industryCommerce_equityPledge.getImpmorblicno(),industryCommerce_equityPledge.getEqupledate(),industryCommerce_equityPledge.getStatus(),industryCommerce_equityPledge.getType(),industryCommerce_equityPledge.getBlictype(),industryCommerce_equityPledge.getPledamunit(),industryCommerce_equityPledge.getPripid(),industryCommerce_equityPledge.getRegcapcur(),industryCommerce_equityPledge.getXzqh(),industryCommerce_equityPledge.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_equityPledge", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_equityPledge插入数据
	 * 
	 * @param industryCommerce_equityPledge	 * @return
	 */
	public static int insertIndustryCommerce_equityPledge(IndustryCommerce_equityPledge industryCommerce_equityPledge) {

		int result = 0;
		
		String sql = "insert into industryCommerce_equityPledge (regNumber,equityno,pledgor,blicno,impam,imporg,impmorblicno,equpledate,status,type,blictype,pledamunit,pripid,regcapcur,xzqh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {industryCommerce_equityPledge.getRegNumber(),industryCommerce_equityPledge.getEquityno(),industryCommerce_equityPledge.getPledgor(),industryCommerce_equityPledge.getBlicno(),industryCommerce_equityPledge.getImpam(),industryCommerce_equityPledge.getImporg(),industryCommerce_equityPledge.getImpmorblicno(),industryCommerce_equityPledge.getEqupledate(),industryCommerce_equityPledge.getStatus(),industryCommerce_equityPledge.getType(),industryCommerce_equityPledge.getBlictype(),industryCommerce_equityPledge.getPledamunit(),industryCommerce_equityPledge.getPripid(),industryCommerce_equityPledge.getRegcapcur(),industryCommerce_equityPledge.getXzqh()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_equityPledge", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_equityPledge数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_equityPledgeByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,equityno,pledgor,blicno,impam,imporg,impmorblicno,equpledate,status,type,blictype,pledamunit,pripid,regcapcur,xzqh from industryCommerce_equityPledge where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_equityPledge", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_equityPledge(List<IndustryCommerce_equityPledge> industryCommerce_equityPledgeList) {
		int result[] = {};
		
		
		String sql = "insert into industryCommerce_equityPledge (regNumber,equityno,pledgor,blicno,impam,imporg,impmorblicno,equpledate,status,type,blictype,pledamunit,pripid,regcapcur,xzqh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[industryCommerce_equityPledgeList.size()][15];
		if (industryCommerce_equityPledgeList != null && industryCommerce_equityPledgeList.size() > 0) {

			for (int i = 0; i < industryCommerce_equityPledgeList.size(); i++) {
				IndustryCommerce_equityPledge industryCommerce_equityPledge = industryCommerce_equityPledgeList.get(i);
										
						 params[i][0] = industryCommerce_equityPledge.getRegNumber();				
						 params[i][1] = industryCommerce_equityPledge.getEquityno();				
						 params[i][2] = industryCommerce_equityPledge.getPledgor();				
						 params[i][3] = industryCommerce_equityPledge.getBlicno();				
						 params[i][4] = industryCommerce_equityPledge.getImpam();				
						 params[i][5] = industryCommerce_equityPledge.getImporg();				
						 params[i][6] = industryCommerce_equityPledge.getImpmorblicno();				
						 params[i][7] = industryCommerce_equityPledge.getEqupledate();				
						 params[i][8] = industryCommerce_equityPledge.getStatus();				
						 params[i][9] = industryCommerce_equityPledge.getType();				
						 params[i][10] = industryCommerce_equityPledge.getBlictype();				
						 params[i][11] = industryCommerce_equityPledge.getPledamunit();				
						 params[i][12] = industryCommerce_equityPledge.getPripid();				
						 params[i][13] = industryCommerce_equityPledge.getRegcapcur();				
						 params[i][14] = industryCommerce_equityPledge.getXzqh();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_equityPledge", e);
		}

		return result;
	}
}
