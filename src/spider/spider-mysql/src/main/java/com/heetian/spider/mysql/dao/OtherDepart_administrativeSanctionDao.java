package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.OtherDepart_administrativeSanction;

/**
 * <p>
 * Title: OtherDepart_administrativeSanctionDao.java
 * </p>
 * <p>
 * Description: 其他部门公示信息_行政处罚信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class OtherDepart_administrativeSanctionDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(OtherDepart_administrativeSanctionDao.class);

	/***
	 * 查询指定条数的otherDepart_administrativeSanction集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchOtherDepart_administrativeSanction(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from otherDepart_administrativeSanction  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询otherDepart_administrativeSanction", e);
		}
		return arraylist;
	}

	/***
	 * 更新otherDepart_administrativeSanction
	 * 
	 * @param otherDepart_administrativeSanction	 * @return
	 */
	public static int updateOtherDepart_administrativeSanction(OtherDepart_administrativeSanction otherDepart_administrativeSanction) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  otherDepart_administrativeSanction set regNumber=?,decisionNumber=?,IllegalType=?,punishContent=?,decisionOrgan=?,decisionDate=?,details=? where regNumber=? ");
		Object[] params = { otherDepart_administrativeSanction.getRegNumber(),otherDepart_administrativeSanction.getDecisionNumber(),otherDepart_administrativeSanction.getIllegalType(),otherDepart_administrativeSanction.getPunishContent(),otherDepart_administrativeSanction.getDecisionOrgan(),otherDepart_administrativeSanction.getDecisionDate(),otherDepart_administrativeSanction.getDetails(),otherDepart_administrativeSanction.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新otherDepart_administrativeSanction", e);
		}
		return result;
	}

	/***
	 * 向otherDepart_administrativeSanction插入数据
	 * 
	 * @param otherDepart_administrativeSanction	 * @return
	 */
	public static int insertOtherDepart_administrativeSanction(OtherDepart_administrativeSanction otherDepart_administrativeSanction) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,decisionNumber,IllegalType,punishContent,decisionOrgan,decisionDate,details) values(?,?,?,?,?,?,?)";
		Object[] params = {otherDepart_administrativeSanction.getRegNumber(),otherDepart_administrativeSanction.getDecisionNumber(),otherDepart_administrativeSanction.getIllegalType(),otherDepart_administrativeSanction.getPunishContent(),otherDepart_administrativeSanction.getDecisionOrgan(),otherDepart_administrativeSanction.getDecisionDate(),otherDepart_administrativeSanction.getDetails()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入otherDepart_administrativeSanction", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询otherDepart_administrativeSanction数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchOtherDepart_administrativeSanctionByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,decisionNumber,IllegalType,punishContent,decisionOrgan,decisionDate,details from otherDepart_administrativeSanction where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询otherDepart_administrativeSanction", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchOtherDepart_administrativeSanction(List<OtherDepart_administrativeSanction> otherDepart_administrativeSanctionList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,decisionNumber,IllegalType,punishContent,decisionOrgan,decisionDate,details) values(?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[otherDepart_administrativeSanctionList.size()][8-1];
		if (otherDepart_administrativeSanctionList != null && otherDepart_administrativeSanctionList.size() > 0) {

			for (int i = 0; i < otherDepart_administrativeSanctionList.size(); i++) {
				OtherDepart_administrativeSanction otherDepart_administrativeSanction = otherDepart_administrativeSanctionList.get(i);
										
						 params[i][2-2] = otherDepart_administrativeSanction.getRegNumber();				
						 params[i][3-2] = otherDepart_administrativeSanction.getDecisionNumber();				
						 params[i][4-2] = otherDepart_administrativeSanction.getIllegalType();				
						 params[i][5-2] = otherDepart_administrativeSanction.getPunishContent();				
						 params[i][6-2] = otherDepart_administrativeSanction.getDecisionOrgan();				
						 params[i][7-2] = otherDepart_administrativeSanction.getDecisionDate();				
						 params[i][8-2] = otherDepart_administrativeSanction.getDetails();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入otherDepart_administrativeSanction", e);
		}

		return result;
	}
}
