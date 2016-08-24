package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_administrativeSanction;

/**
 * <p>
 * Title: Enterprise_administrativeSanctionDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_行政处罚信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Mon Apr 13 13:59:50 CST 2015
 */
public class Enterprise_administrativeSanctionDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_administrativeSanctionDao.class);

	/***
	 * 查询指定条数的enterprise_administrativeSanction集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_administrativeSanction(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_administrativeSanction  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_administrativeSanction", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_administrativeSanction
	 * 
	 * @param enterprise_administrativeSanction	 * @return
	 */
	public static int updateEnterprise_administrativeSanction(Enterprise_administrativeSanction enterprise_administrativeSanction) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_administrativeSanction set regNumber=?,pendecno=?,illegacttype=?,punishContent=?,penauth=?,pendecissdate=?,forfam=?,ifpub=?,penam=?,pentype=?,pid=?,pripid=?,remark=?,updatetime=? where regNumber=? ");
		Object[] params = { enterprise_administrativeSanction.getRegNumber(),enterprise_administrativeSanction.getPendecno(),enterprise_administrativeSanction.getIllegacttype(),enterprise_administrativeSanction.getPunishContent(),enterprise_administrativeSanction.getPenauth(),enterprise_administrativeSanction.getPendecissdate(),enterprise_administrativeSanction.getForfam(),enterprise_administrativeSanction.getIfpub(),enterprise_administrativeSanction.getPenam(),enterprise_administrativeSanction.getPentype(),enterprise_administrativeSanction.getPid(),enterprise_administrativeSanction.getPripid(),enterprise_administrativeSanction.getRemark(),enterprise_administrativeSanction.getUpdatetime(),enterprise_administrativeSanction.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_administrativeSanction", e);
		}
		return result;
	}

	/***
	 * 向enterprise_administrativeSanction插入数据
	 * 
	 * @param enterprise_administrativeSanction	 * @return
	 */
	public static int insertEnterprise_administrativeSanction(Enterprise_administrativeSanction enterprise_administrativeSanction) {

		int result = 0;
		
		String sql = "insert into enterprise_administrativeSanction (regNumber,pendecno,illegacttype,punishContent,penauth,pendecissdate,forfam,ifpub,penam,pentype,pid,pripid,remark,updatetime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_administrativeSanction.getRegNumber(),enterprise_administrativeSanction.getPendecno(),enterprise_administrativeSanction.getIllegacttype(),enterprise_administrativeSanction.getPunishContent(),enterprise_administrativeSanction.getPenauth(),enterprise_administrativeSanction.getPendecissdate(),enterprise_administrativeSanction.getForfam(),enterprise_administrativeSanction.getIfpub(),enterprise_administrativeSanction.getPenam(),enterprise_administrativeSanction.getPentype(),enterprise_administrativeSanction.getPid(),enterprise_administrativeSanction.getPripid(),enterprise_administrativeSanction.getRemark(),enterprise_administrativeSanction.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_administrativeSanction", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_administrativeSanction数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_administrativeSanctionByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,pendecno,illegacttype,punishContent,penauth,pendecissdate,forfam,ifpub,penam,pentype,pid,pripid,remark,updatetime from enterprise_administrativeSanction where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_administrativeSanction", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_administrativeSanction(List<Enterprise_administrativeSanction> enterprise_administrativeSanctionList) {
		int result[] = {};
		
		
		String sql = "insert into enterprise_administrativeSanction (regNumber,pendecno,illegacttype,punishContent,penauth,pendecissdate,forfam,ifpub,penam,pentype,pid,pripid,remark,updatetime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_administrativeSanctionList.size()][14];
		if (enterprise_administrativeSanctionList != null && enterprise_administrativeSanctionList.size() > 0) {

			for (int i = 0; i < enterprise_administrativeSanctionList.size(); i++) {
				Enterprise_administrativeSanction enterprise_administrativeSanction = enterprise_administrativeSanctionList.get(i);
										
						 params[i][0] = enterprise_administrativeSanction.getRegNumber();				
						 params[i][1] = enterprise_administrativeSanction.getPendecno();				
						 params[i][2] = enterprise_administrativeSanction.getIllegacttype();				
						 params[i][3] = enterprise_administrativeSanction.getPunishContent();				
						 params[i][4] = enterprise_administrativeSanction.getPenauth();				
						 params[i][5] = enterprise_administrativeSanction.getPendecissdate();				
						 params[i][6] = enterprise_administrativeSanction.getForfam();				
						 params[i][7] = enterprise_administrativeSanction.getIfpub();				
						 params[i][8] = enterprise_administrativeSanction.getPenam();				
						 params[i][9] = enterprise_administrativeSanction.getPentype();				
						 params[i][10] = enterprise_administrativeSanction.getPid();				
						 params[i][11] = enterprise_administrativeSanction.getPripid();				
						 params[i][12] = enterprise_administrativeSanction.getRemark();				
						 params[i][13] = enterprise_administrativeSanction.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_administrativeSanction", e);
		}

		return result;
	}
}
