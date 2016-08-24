package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Enterprise_intellectualProperty;

/**
 * <p>
 * Title: Enterprise_intellectualPropertyDao.java
 * </p>
 * <p>
 * Description: 企业公示信息_知识产权出质登记信息表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Mon Apr 13 13:59:50 CST 2015
 */
public class Enterprise_intellectualPropertyDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(Enterprise_intellectualPropertyDao.class);

	/***
	 * 查询指定条数的enterprise_intellectualProperty集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchEnterprise_intellectualProperty(int number) {

		List<Object[]> arraylist = null;
		
		

		String sql = "select * from enterprise_intellectualProperty  limit "
				+ number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_intellectualProperty", e);
		}
		return arraylist;
	}

	/***
	 * 更新enterprise_intellectualProperty
	 * 
	 * @param enterprise_intellectualProperty	 * @return
	 */
	public static int updateEnterprise_intellectualProperty(Enterprise_intellectualProperty enterprise_intellectualProperty) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  enterprise_intellectualProperty set regNumber=?,tmname=?,kinds=?,pledgor=?,pledgeeName=?,pleregperfrom=?,pleregperto=?,imporg=?,ifpub=?,changedate=?,pid=?,pripid=?,tmregno=?,type=?,updatetime=? where regNumber=? ");
		Object[] params = { enterprise_intellectualProperty.getRegNumber(),enterprise_intellectualProperty.getTmname(),enterprise_intellectualProperty.getKinds(),enterprise_intellectualProperty.getPledgor(),enterprise_intellectualProperty.getPledgeeName(),enterprise_intellectualProperty.getPleregperfrom(),enterprise_intellectualProperty.getPleregperto(),enterprise_intellectualProperty.getImporg(),enterprise_intellectualProperty.getIfpub(),enterprise_intellectualProperty.getChangedate(),enterprise_intellectualProperty.getPid(),enterprise_intellectualProperty.getPripid(),enterprise_intellectualProperty.getTmregno(),enterprise_intellectualProperty.getType(),enterprise_intellectualProperty.getUpdatetime(),enterprise_intellectualProperty.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_intellectualProperty", e);
		}
		return result;
	}

	/***
	 * 向enterprise_intellectualProperty插入数据
	 * 
	 * @param enterprise_intellectualProperty	 * @return
	 */
	public static int insertEnterprise_intellectualProperty(Enterprise_intellectualProperty enterprise_intellectualProperty) {

		int result = 0;
		
		String sql = "insert into enterprise_intellectualProperty (regNumber,tmname,kinds,pledgor,pledgeeName,pleregperfrom,pleregperto,imporg,ifpub,changedate,pid,pripid,tmregno,type,updatetime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {enterprise_intellectualProperty.getRegNumber(),enterprise_intellectualProperty.getTmname(),enterprise_intellectualProperty.getKinds(),enterprise_intellectualProperty.getPledgor(),enterprise_intellectualProperty.getPledgeeName(),enterprise_intellectualProperty.getPleregperfrom(),enterprise_intellectualProperty.getPleregperto(),enterprise_intellectualProperty.getImporg(),enterprise_intellectualProperty.getIfpub(),enterprise_intellectualProperty.getChangedate(),enterprise_intellectualProperty.getPid(),enterprise_intellectualProperty.getPripid(),enterprise_intellectualProperty.getTmregno(),enterprise_intellectualProperty.getType(),enterprise_intellectualProperty.getUpdatetime()};

		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入enterprise_intellectualProperty", e);
		} 

		return result;

	}

	/**
	 * 根据企业注册号查询enterprise_intellectualProperty数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchEnterprise_intellectualPropertyByRegNumber(String regNumber) {
				
		try {

			StringBuilder sql = new StringBuilder(
					"select id,regNumber,tmname,kinds,pledgor,pledgeeName,pleregperfrom,pleregperto,imporg,ifpub,changedate,pid,pripid,tmregno,type,updatetime from enterprise_intellectualProperty where regNumber='").append(regNumber)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(),
					new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_intellectualProperty", e);
		} 

		return null;
	}

	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchEnterprise_intellectualProperty(List<Enterprise_intellectualProperty> enterprise_intellectualPropertyList) {
		int result[] = {};
		
		
		String sql = "insert into enterprise_intellectualProperty (regNumber,tmname,kinds,pledgor,pledgeeName,pleregperfrom,pleregperto,imporg,ifpub,changedate,pid,pripid,tmregno,type,updatetime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		logger.info("本次执行sql："+sql);
		
		Object[][] params = new Object[enterprise_intellectualPropertyList.size()][15];
		if (enterprise_intellectualPropertyList != null && enterprise_intellectualPropertyList.size() > 0) {

			for (int i = 0; i < enterprise_intellectualPropertyList.size(); i++) {
				Enterprise_intellectualProperty enterprise_intellectualProperty = enterprise_intellectualPropertyList.get(i);
										
						 params[i][0] = enterprise_intellectualProperty.getRegNumber();				
						 params[i][1] = enterprise_intellectualProperty.getTmname();				
						 params[i][2] = enterprise_intellectualProperty.getKinds();				
						 params[i][3] = enterprise_intellectualProperty.getPledgor();				
						 params[i][4] = enterprise_intellectualProperty.getPledgeeName();				
						 params[i][5] = enterprise_intellectualProperty.getPleregperfrom();				
						 params[i][6] = enterprise_intellectualProperty.getPleregperto();				
						 params[i][7] = enterprise_intellectualProperty.getImporg();				
						 params[i][8] = enterprise_intellectualProperty.getIfpub();				
						 params[i][9] = enterprise_intellectualProperty.getChangedate();				
						 params[i][10] = enterprise_intellectualProperty.getPid();				
						 params[i][11] = enterprise_intellectualProperty.getPripid();				
						 params[i][12] = enterprise_intellectualProperty.getTmregno();				
						 params[i][13] = enterprise_intellectualProperty.getType();				
						 params[i][14] = enterprise_intellectualProperty.getUpdatetime();				
							}
		}

		try {

			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_intellectualProperty", e);
		}

		return result;
	}
}
