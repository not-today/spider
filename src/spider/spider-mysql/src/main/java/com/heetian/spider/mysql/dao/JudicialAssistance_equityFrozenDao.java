package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.JudicialAssistance_equityFrozen;

/**
 * <p>
 * Title: JudicialAssistance_equityFrozenDao.java
 * </p>
 * <p>
 * Description: 司法协助公示信息_股权冻结信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class JudicialAssistance_equityFrozenDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(JudicialAssistance_equityFrozenDao.class);

	/***
	 * 查询指定条数的judicialAssistance_equityFrozen集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchJudicialAssistance_equityFrozen(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from judicialAssistance_equityFrozen  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询judicialAssistance_equityFrozen", e);
		}
		return arraylist;
	}

	/***
	 * 更新judicialAssistance_equityFrozen
	 * 
	 * @param judicialAssistance_equityFrozen	 * @return
	 */
	public static int updateJudicialAssistance_equityFrozen(JudicialAssistance_equityFrozen judicialAssistance_equityFrozen) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  judicialAssistance_equityFrozen set regNumber=?,enforcedPerson=?,equityAmont=?,executionCourt=?,noticeNumber=?,status=?,details=? where regNumber=? ");
		Object[] params = { judicialAssistance_equityFrozen.getRegNumber(),judicialAssistance_equityFrozen.getEnforcedPerson(),judicialAssistance_equityFrozen.getEquityAmont(),judicialAssistance_equityFrozen.getExecutionCourt(),judicialAssistance_equityFrozen.getNoticeNumber(),judicialAssistance_equityFrozen.getStatus(),judicialAssistance_equityFrozen.getDetails(),judicialAssistance_equityFrozen.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新judicialAssistance_equityFrozen", e);
		}
		return result;
	}

	/***
	 * 向judicialAssistance_equityFrozen插入数据
	 * 
	 * @param judicialAssistance_equityFrozen	 * @return
	 */
	public static int insertJudicialAssistance_equityFrozen(JudicialAssistance_equityFrozen judicialAssistance_equityFrozen) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,enforcedPerson,equityAmont,executionCourt,noticeNumber,status,details) values(?,?,?,?,?,?,?)";
		Object[] params = {judicialAssistance_equityFrozen.getRegNumber(),judicialAssistance_equityFrozen.getEnforcedPerson(),judicialAssistance_equityFrozen.getEquityAmont(),judicialAssistance_equityFrozen.getExecutionCourt(),judicialAssistance_equityFrozen.getNoticeNumber(),judicialAssistance_equityFrozen.getStatus(),judicialAssistance_equityFrozen.getDetails()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入judicialAssistance_equityFrozen", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询judicialAssistance_equityFrozen数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchJudicialAssistance_equityFrozenByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,enforcedPerson,equityAmont,executionCourt,noticeNumber,status,details from judicialAssistance_equityFrozen where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询judicialAssistance_equityFrozen", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchJudicialAssistance_equityFrozen(List<JudicialAssistance_equityFrozen> judicialAssistance_equityFrozenList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,enforcedPerson,equityAmont,executionCourt,noticeNumber,status,details) values(?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[judicialAssistance_equityFrozenList.size()][8-1];
		if (judicialAssistance_equityFrozenList != null && judicialAssistance_equityFrozenList.size() > 0) {

			for (int i = 0; i < judicialAssistance_equityFrozenList.size(); i++) {
				JudicialAssistance_equityFrozen judicialAssistance_equityFrozen = judicialAssistance_equityFrozenList.get(i);
										
						 params[i][2-2] = judicialAssistance_equityFrozen.getRegNumber();				
						 params[i][3-2] = judicialAssistance_equityFrozen.getEnforcedPerson();				
						 params[i][4-2] = judicialAssistance_equityFrozen.getEquityAmont();				
						 params[i][5-2] = judicialAssistance_equityFrozen.getExecutionCourt();				
						 params[i][6-2] = judicialAssistance_equityFrozen.getNoticeNumber();				
						 params[i][7-2] = judicialAssistance_equityFrozen.getStatus();				
						 params[i][8-2] = judicialAssistance_equityFrozen.getDetails();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入judicialAssistance_equityFrozen", e);
		}

		return result;
	}
}
