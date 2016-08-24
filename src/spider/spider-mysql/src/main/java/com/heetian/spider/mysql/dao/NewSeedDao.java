package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.NewSeed;

/**
 * 
 * @author tst
 *
 */
public class NewSeedDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(NewSeedDao.class);

	/***
	 * 
	 * @param number
	 * @return
	 */
	public static List<Object[]> searchNewSeed(int number) {
		List<Object[]> arraylist = null;
		String sql = "select * from newseed  limit " + number;
		try {
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询enterprise_changeInfo", e);
		}
		return arraylist;
	}

	/***
	 * 更新
	 * @param newSeed
	 * @return
	 */
	public static int updateNewSeed(NewSeed newSeed) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  newseed set entname=?,come_from=?,area=?,website=?,industry=? where id=? ");
		Object[] params = {newSeed.getEntname(),newSeed.getComeFrom(),newSeed.getArea(),newSeed.getWebsite(),newSeed.getIndustry(),newSeed.getId()};
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新enterprise_changeInfo", e);
		}
		return result;
	}
	/***
	 * 插入数据
	 * @param newseed
	 * @return
	 */
	public static int insertNewSeed(NewSeed newseed) {
		int result = 0;
		String sql = "insert into newseed (entname,come_from,area,website,industry) values(?,?,?,?,?)";
		Object[] params = {newseed.getEntname(),newseed.getComeFrom(),newseed.getArea(),newseed.getWebsite(),newseed.getIndustry()};
		try {
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入newseed", e);
		} 
		return result;
	}
	/***
	 * entname字段无重复插入数据
	 * @param newseed
	 * @return
	 */
	public static void insertNewSeedNoRepeat(NewSeed newseed) {
		List<Object[]> newseeds = searchNewSeedByEntName(newseed.getEntname());
		if(newseeds==null||newseeds.size()<=0){
			insertNewSeed(newseed);
		}
	}
	/**
	 * 查询数据
	 * @param entName
	 * @return
	 */
	public static List<Object[]> searchNewSeedByEntName(String entName) {
		try {
			StringBuilder sql = new StringBuilder("select id,entname,come_from,area,website,industry from newseed where entname='").append(entName).append("'");
			List<Object[]> arraylist = queryRunner.query(sql.toString(),new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询enterprise_changeInfo", e);
		} 
		return null;
	}

	/***
	 * 
	 * @param enterprise_changeInfoList
	 * @return
	 */
	public static int[] batchNewSeed(List<NewSeed> newSeeds) {
		int result[] = {};
		String sql = "insert into newseed (entname,come_from,area,website,industry) values(?,?,?,?,?)";
		logger.info("本次执行sql："+sql);
		Object[][] params = new Object[newSeeds.size()][6-1];
		if (newSeeds != null && newSeeds.size() > 0) {
			for (int i = 0; i < newSeeds.size(); i++) {
				NewSeed newSeed = newSeeds.get(i);
				params[i][2-2] = newSeed.getEntname();				
				params[i][3-2] = newSeed.getComeFrom();				
				params[i][4-2] = newSeed.getArea();				
				params[i][5-2] = newSeed.getWebsite();				
				params[i][6-2] = newSeed.getIndustry();				
			}
		}
		try {
			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入enterprise_changeInfo", e);
		}
		return result;
	}
}
