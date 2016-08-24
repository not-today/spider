package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.heetian.spider.mysql.model.Judgment;

/**
 * 
 * @author tst
 *
 */
public class JudgmentDao extends Dao{
	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(JudgmentDao.class);
	public static void insertJds(List<Judgment> judgments){
		Iterator<Judgment> iter = judgments.iterator();
		while(iter.hasNext()){
			Judgment jd = iter.next();
			try {
				List<Object[]> objs = searchJudgment(jd.getId());
				if(objs!=null&&objs.size()>0){
					iter.remove();
				}
			} catch (SQLException e) {
				iter.remove();
				logger.error("查询judgment对象出错："+jd.toString());
			}
		}
		batchJudgment(judgments);
	}
	/**
	 * 
	 * @param jdID
	 * @return
	 * @throws SQLException 
	 */
	public static List<Object[]> searchJudgment(String jdID) throws SQLException {
		String sql = "select ID,Code,Title,Type,Court,Year,Content,CourtDate,PubDate,CourtProceeding,origletext from judgment  where ID=" + jdID;
		return queryRunner.query(sql, new ArrayListHandler());
	}
	/**
	 * 
	 * @param jd
	 * @return
	 */
	public static int insertJudgment(Judgment jd) {
		String sql = "INSERT INTO judgment(ID,Code,Title,Type,Court,Year,Content,CourtDate,PubDate,CourtProceeding,origletext) VALUE(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {jd.getId(),jd.getCode(),jd.getTitle(),jd.getType(),jd.getCourt(),jd.getYear(),jd.getContent(),jd.getCourtDate(),jd.getPubDate(),jd.getCourtProceeding(),jd.getOrigleText()};
		try {
			return queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_shareholder", e);
			return -1;
		} 

	}
	/**
	 * 
	 * @param judgments
	 * @return
	 */
	public static int[] batchJudgment(List<Judgment> judgments) {
		if (judgments == null || judgments.size() <= 0) {
			return new int[]{-1};
		}
		String sql = "INSERT INTO judgment(ID,Code,Title,Type,Court,Year,Content,CourtDate,PubDate,CourtProceeding,origletext) VALUE(?,?,?,?,?,?,?,?,?,?,?)";
		Object[][] params = new Object[judgments.size()][12];
		for (int i = 0; i < judgments.size(); i++) {
			Judgment judgment = judgments.get(i);
			params[i][0] = judgment.getId();
			params[i][1] = judgment.getCode();
			params[i][2] = judgment.getTitle();
			params[i][3] = judgment.getType();
			params[i][4] = judgment.getCourt();
			params[i][5] = judgment.getYear();
			params[i][6] = judgment.getContent();
			params[i][7] = judgment.getCourtDate();
			params[i][8] = judgment.getPubDate();
			params[i][9] = judgment.getCourtProceeding();
			params[i][10] = judgment.getOrigleText();
		}
		try {
			return queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入judgment:"+new Gson().toJson(judgments)+"", e);
			return new int[-1];
		}
	}
}
