package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Gdxx;

/**
 * 
 * @author tst
 *
 */
public class GdxxDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(GdxxDao.class);

	/***
	 * 查询指定条数的industryCommerce_shareholder集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchGdxx(int number) {
		List<Object[]> arraylist = null;
		String sql = "select id,uuid,regNumber,inv,renjiaoe,shijiaoe,renjiaoMethod,renjiaochuzie,renjiaoDate,shijiaoMethod,shijiaochuzie,shijiaoDate,invType from gdxx  limit " + number;
		try {
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholder", e);
		}
		return arraylist;
	}

	/***
	 * 更新industryCommerce_shareholder
	 * 
	 * @param industryCommerce_shareholder	 * @return
	 */
	public static int updateGdxx(Gdxx gdxx) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  gdxx set uuid=?,regNumber=?,inv=?,renjiaoe=?,shijiaoe=?,renjiaoMethod=?,renjiaochuzie=?,renjiaoDate=?,shijiaoMethod=?,shijiaochuzie=?,shijiaoDate=?,invType=? where id=?");
		Object[] params = {gdxx.getUuid(),gdxx.getRegNumber(),gdxx.getInv(),gdxx.getRenjiaoe(),gdxx.getShijiaoe(),gdxx.getRenjiaoMethod(),gdxx.getRenjiaochuzie(),gdxx.getRenjiaoDate(),gdxx.getShijiaoMethod(),gdxx.getShijiaochuzie(),gdxx.getShijiaoDate(),gdxx.getInvType(),gdxx.getId()};
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_shareholder", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_shareholder插入数据
	 * 
	 * @param industryCommerce_shareholder	 * @return
	 */
	public static int insertGdxx(Gdxx gdxx) {
		int result = -1;
		String sql = "insert into industryCommerce_shareholder (uuid,regNumber,inv,renjiaoe,shijiaoe,renjiaoMethod,renjiaochuzie,renjiaoDate,shijiaoMethod,shijiaochuzie,shijiaoDate,invType) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {gdxx.getUuid(),gdxx.getRegNumber(),gdxx.getInv(),gdxx.getRenjiaoe(),gdxx.getShijiaoe(),gdxx.getRenjiaoMethod(),gdxx.getRenjiaochuzie(),gdxx.getRenjiaoDate(),gdxx.getShijiaoMethod(),gdxx.getShijiaochuzie(),gdxx.getShijiaoDate(),gdxx.getInvType()};
		try {
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_shareholder", e);
		} 
		return result;

	}

	/**
	 * 根据企业注册号查询industryCommerce_shareholder数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchGdxxByRegNumber(String regNumber) {
		try {
			StringBuilder sql = new StringBuilder( "select id,uuid,regNumber,inv,renjiaoe,shijiaoe,renjiaoMethod,renjiaochuzie,renjiaoDate,shijiaoMethod,shijiaochuzie,shijiaoDate,invType from gdxx where regNumber='").append(regNumber).append("'");
			List<Object[]> arraylist = queryRunner.query(sql.toString(),new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_shareholder", e);
		} 
		return null;
	}
	/**
	 * 根据企业注册号删除
	 * @param regNumber
	 * @return
	 */
	public static void deleteGdxxByRegNumber(String regNumber) {
		try {
			String sql = "delete from gdxx where regNumber='"+regNumber+"'";
			queryRunner.update(sql);
		} catch (SQLException e) {
			logger.error("查询industryCommerce_branchInfo", e);
		} 
	}
	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchGdxx(List<Gdxx> gdxxs) {
		int result[] = null;
		String sql = "insert into gdxx (uuid,regNumber,inv,renjiaoe,shijiaoe,renjiaoMethod,renjiaochuzie,renjiaoDate,shijiaoMethod,shijiaochuzie,shijiaoDate,invType) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		if (gdxxs != null && gdxxs.size() > 0) {
			Object[][] params = new Object[gdxxs.size()][12];
			for (int i = 0; i < gdxxs.size(); i++) {
				Gdxx gdxx = gdxxs.get(i);
				 params[i][0] = gdxx.getUuid();				
				 params[i][1] = gdxx.getRegNumber();				
				 params[i][2] = gdxx.getInv();				
				 params[i][3] = gdxx.getRenjiaoe();				
				 params[i][4] = gdxx.getShijiaoe();				
				 params[i][5] = gdxx.getRenjiaoMethod();				
				 params[i][6] = gdxx.getRenjiaochuzie();				
				 params[i][7] = gdxx.getRenjiaoDate();				
				 params[i][8] = gdxx.getShijiaoMethod();				
				 params[i][9] = gdxx.getShijiaochuzie();				
				 params[i][10] = gdxx.getShijiaoDate();				
				 params[i][11] = gdxx.getInvType();				
			}
			try {
				result = queryRunner.batch(sql, params);
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("mysql批量插入industryCommerce_shareholder", e);
			}
		}
		return new int[gdxxs.size()];
	}
}
