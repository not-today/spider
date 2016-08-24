package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_administrationPermit;

/**
 * <p>
 * Title: Enterprise_administrationPermitDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_行政许可信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Fri Apr 03 12:06:23 CST 2015
 */
public class Enterprise_administrationPermitDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_administrationPermitDao.class);

	/***
	 * 查询指定条数的enterprise_administrationPermit集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_administrationPermit(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_administrationPermit  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_administrationPermit", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_administrationPermit
	 * 
	 * @param enterprise_administrationPermit	 * @return
	 */
	public static int updateEnterprise_administrationPermit(Enterprise_administrationPermit enterprise_administrationPermit) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_administrationPermit set regNumber=?,licno=?,licname=?,valfrom=?,valto=?,licanth=?,licitem=?,pripid=?,pid=?,ifpub=?,type=?,changedate=?,updatetime=? where regNumber=? ");
		Object[] params = { enterprise_administrationPermit.getRegNumber(),enterprise_administrationPermit.getLicno(),enterprise_administrationPermit.getLicname(),enterprise_administrationPermit.getValfrom(),enterprise_administrationPermit.getValto(),enterprise_administrationPermit.getLicanth(),enterprise_administrationPermit.getLicitem(),enterprise_administrationPermit.getPripid(),enterprise_administrationPermit.getPid(),enterprise_administrationPermit.getIfpub(),enterprise_administrationPermit.getType(),enterprise_administrationPermit.getChangedate(),enterprise_administrationPermit.getUpdatetime(),enterprise_administrationPermit.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_administrationPermit", e);
		}
		return result;
	}

	/***
	 * 向enterprise_administrationPermit插入数据
	 * 
	 * @param enterprise_administrationPermit	 * @return
	 */
	public static int insertEnterprise_administrationPermit(Enterprise_administrationPermit enterprise_administrationPermit) {

		int result = 0;
		
		String sql = "insert into enterprise_administrationPermit (regNumber,licno,licname,valfrom,valto,licanth,licitem,pripid,pid,ifpub,type,changedate,updatetime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_administrationPermit.getRegNumber(),enterprise_administrationPermit.getLicno(),enterprise_administrationPermit.getLicname(),enterprise_administrationPermit.getValfrom(),enterprise_administrationPermit.getValto(),enterprise_administrationPermit.getLicanth(),enterprise_administrationPermit.getLicitem(),enterprise_administrationPermit.getPripid(),enterprise_administrationPermit.getPid(),enterprise_administrationPermit.getIfpub(),enterprise_administrationPermit.getType(),enterprise_administrationPermit.getChangedate(),enterprise_administrationPermit.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_administrationPermit", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_administrationPermit数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_administrationPermitByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,licno,licname,valfrom,valto,licanth,licitem,pripid,pid,ifpub,type,changedate,updatetime from enterprise_administrationPermit where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_administrationPermit", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_administrationPermit(List<Enterprise_administrationPermit> enterprise_administrationPermitList) {
		int result[] = {};
		
		
		String sql = "insert into enterprise_administrationPermit (regNumber,licno,licname,valfrom,valto,licanth,licitem,pripid,pid,ifpub,type,changedate,updatetime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_administrationPermitList.size()][13];
		if (enterprise_administrationPermitList != null && enterprise_administrationPermitList.size() > 0) {

			for (int i = 0; i < enterprise_administrationPermitList.size(); i++) {
				Enterprise_administrationPermit enterprise_administrationPermit = enterprise_administrationPermitList.get(i);
										
						 params[i][0] = enterprise_administrationPermit.getRegNumber();				
						 params[i][1] = enterprise_administrationPermit.getLicno();				
						 params[i][2] = enterprise_administrationPermit.getLicname();				
						 params[i][3] = enterprise_administrationPermit.getValfrom();				
						 params[i][4] = enterprise_administrationPermit.getValto();				
						 params[i][5] = enterprise_administrationPermit.getLicanth();				
						 params[i][6] = enterprise_administrationPermit.getLicitem();				
						 params[i][7] = enterprise_administrationPermit.getPripid();				
						 params[i][8] = enterprise_administrationPermit.getPid();				
						 params[i][9] = enterprise_administrationPermit.getIfpub();				
						 params[i][10] = enterprise_administrationPermit.getType();				
						 params[i][11] = enterprise_administrationPermit.getChangedate();				
						 params[i][12] = enterprise_administrationPermit.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_administrationPermit", e);
		}

		return result;
	}
}
