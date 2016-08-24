package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.JudicialAssistance_shareholdersChange;

/**
 * <p>
 * Title: JudicialAssistance_shareholdersChangeDao.java
 * </p>
 * <p>
 * Description: 司法协助公示信息_股东变更信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class JudicialAssistance_shareholdersChangeDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(JudicialAssistance_shareholdersChangeDao.class);

	/***
	 * 查询指定条数的judicialAssistance_shareholdersChange集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchJudicialAssistance_shareholdersChange(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from judicialAssistance_shareholdersChange  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询judicialAssistance_shareholdersChange", e);
		}
		return arraylist;
	}

	/***
	 * 更新judicialAssistance_shareholdersChange
	 * 
	 * @param judicialAssistance_shareholdersChange	 * @return
	 */
	public static int updateJudicialAssistance_shareholdersChange(JudicialAssistance_shareholdersChange judicialAssistance_shareholdersChange) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  judicialAssistance_shareholdersChange set regNumber=?,enforcedPerson=?,equityAmont=?,assignee=?,executionCourt=?,details=? where regNumber=? ");
		Object[] params = { judicialAssistance_shareholdersChange.getRegNumber(),judicialAssistance_shareholdersChange.getEnforcedPerson(),judicialAssistance_shareholdersChange.getEquityAmont(),judicialAssistance_shareholdersChange.getAssignee(),judicialAssistance_shareholdersChange.getExecutionCourt(),judicialAssistance_shareholdersChange.getDetails(),judicialAssistance_shareholdersChange.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新judicialAssistance_shareholdersChange", e);
		}
		return result;
	}

	/***
	 * 向judicialAssistance_shareholdersChange插入数据
	 * 
	 * @param judicialAssistance_shareholdersChange	 * @return
	 */
	public static int insertJudicialAssistance_shareholdersChange(JudicialAssistance_shareholdersChange judicialAssistance_shareholdersChange) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,enforcedPerson,equityAmont,assignee,executionCourt,details) values(?,?,?,?,?,?)";
		Object[] params = {judicialAssistance_shareholdersChange.getRegNumber(),judicialAssistance_shareholdersChange.getEnforcedPerson(),judicialAssistance_shareholdersChange.getEquityAmont(),judicialAssistance_shareholdersChange.getAssignee(),judicialAssistance_shareholdersChange.getExecutionCourt(),judicialAssistance_shareholdersChange.getDetails()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入judicialAssistance_shareholdersChange", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询judicialAssistance_shareholdersChange数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchJudicialAssistance_shareholdersChangeByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,enforcedPerson,equityAmont,assignee,executionCourt,details from judicialAssistance_shareholdersChange where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询judicialAssistance_shareholdersChange", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchJudicialAssistance_shareholdersChange(List<JudicialAssistance_shareholdersChange> judicialAssistance_shareholdersChangeList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,enforcedPerson,equityAmont,assignee,executionCourt,details) values(?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[judicialAssistance_shareholdersChangeList.size()][7-1];
		if (judicialAssistance_shareholdersChangeList != null && judicialAssistance_shareholdersChangeList.size() > 0) {

			for (int i = 0; i < judicialAssistance_shareholdersChangeList.size(); i++) {
				JudicialAssistance_shareholdersChange judicialAssistance_shareholdersChange = judicialAssistance_shareholdersChangeList.get(i);
										
						 params[i][2-2] = judicialAssistance_shareholdersChange.getRegNumber();				
						 params[i][3-2] = judicialAssistance_shareholdersChange.getEnforcedPerson();				
						 params[i][4-2] = judicialAssistance_shareholdersChange.getEquityAmont();				
						 params[i][5-2] = judicialAssistance_shareholdersChange.getAssignee();				
						 params[i][6-2] = judicialAssistance_shareholdersChange.getExecutionCourt();				
						 params[i][7-2] = judicialAssistance_shareholdersChange.getDetails();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入judicialAssistance_shareholdersChange", e);
		}

		return result;
	}
}
