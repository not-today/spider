package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.OtderDepart_administrationPermit;

/**
 * <p>
 * Title: OtderDepart_administrationPermitDao.java
 * </p>
 * <p>
 * Description: 其他部门公示信息_行政许可信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:48:13 CST 2015
 */
public class OtderDepart_administrationPermitDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(OtderDepart_administrationPermitDao.class);

	/***
	 * 查询指定条数的otderDepart_administrationPermit集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchOtderDepart_administrationPermit(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from otderDepart_administrationPermit  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询otderDepart_administrationPermit", e);
		}
		return arraylist;
	}

	/***
	 * 更新otderDepart_administrationPermit
	 * 
	 * @param otderDepart_administrationPermit	 * @return
	 */
	public static int updateOtderDepart_administrationPermit(OtderDepart_administrationPermit otderDepart_administrationPermit) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  otderDepart_administrationPermit set regNumber=?,fileNumber=?,fileName=?,beginDate=?,endDate=?,permitOrgan=?,permitContent=?,status=?,details=? where regNumber=? ");
		Object[] params = { otderDepart_administrationPermit.getRegNumber(),otderDepart_administrationPermit.getFileNumber(),otderDepart_administrationPermit.getFileName(),otderDepart_administrationPermit.getBeginDate(),otderDepart_administrationPermit.getEndDate(),otderDepart_administrationPermit.getPermitOrgan(),otderDepart_administrationPermit.getPermitContent(),otderDepart_administrationPermit.getStatus(),otderDepart_administrationPermit.getDetails(),otderDepart_administrationPermit.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新otderDepart_administrationPermit", e);
		}
		return result;
	}

	/***
	 * 向otderDepart_administrationPermit插入数据
	 * 
	 * @param otderDepart_administrationPermit	 * @return
	 */
	public static int insertOtderDepart_administrationPermit(OtderDepart_administrationPermit otderDepart_administrationPermit) {

		int result = 0;
		
		String sql = "insert into proxy (regNumber,fileNumber,fileName,beginDate,endDate,permitOrgan,permitContent,status,details) values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {otderDepart_administrationPermit.getRegNumber(),otderDepart_administrationPermit.getFileNumber(),otderDepart_administrationPermit.getFileName(),otderDepart_administrationPermit.getBeginDate(),otderDepart_administrationPermit.getEndDate(),otderDepart_administrationPermit.getPermitOrgan(),otderDepart_administrationPermit.getPermitContent(),otderDepart_administrationPermit.getStatus(),otderDepart_administrationPermit.getDetails()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入otderDepart_administrationPermit", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询otderDepart_administrationPermit数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchOtderDepart_administrationPermitByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,fileNumber,fileName,beginDate,endDate,permitOrgan,permitContent,status,details from otderDepart_administrationPermit where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询otderDepart_administrationPermit", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchOtderDepart_administrationPermit(List<OtderDepart_administrationPermit> otderDepart_administrationPermitList) {
		int result[] = {};
		
		
		String sql = "insert into proxy (regNumber,fileNumber,fileName,beginDate,endDate,permitOrgan,permitContent,status,details) values(?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[otderDepart_administrationPermitList.size()][10-1];
		if (otderDepart_administrationPermitList != null && otderDepart_administrationPermitList.size() > 0) {

			for (int i = 0; i < otderDepart_administrationPermitList.size(); i++) {
				OtderDepart_administrationPermit otderDepart_administrationPermit = otderDepart_administrationPermitList.get(i);
										
						 params[i][2-2] = otderDepart_administrationPermit.getRegNumber();				
						 params[i][3-2] = otderDepart_administrationPermit.getFileNumber();				
						 params[i][4-2] = otderDepart_administrationPermit.getFileName();				
						 params[i][5-2] = otderDepart_administrationPermit.getBeginDate();				
						 params[i][6-2] = otderDepart_administrationPermit.getEndDate();				
						 params[i][7-2] = otderDepart_administrationPermit.getPermitOrgan();				
						 params[i][8-2] = otderDepart_administrationPermit.getPermitContent();				
						 params[i][9-2] = otderDepart_administrationPermit.getStatus();				
						 params[i][10-2] = otderDepart_administrationPermit.getDetails();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入otderDepart_administrationPermit", e);
		}

		return result;
	}
}
