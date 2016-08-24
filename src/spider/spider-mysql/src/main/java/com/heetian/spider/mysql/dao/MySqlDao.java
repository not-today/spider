package com.heetian.spider.mysql.dao;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.Companys;
import com.heetian.spider.mysql.model.Seed;

/**
 * mysql 数据库插入
 * <p>
 * Title: MySqlDao.java
 * </p>
 * <p>
 * Description: 描述
 * </p>
 * 
 * @author admin
 * @version 1.0
 * @created 2015年3月11日 下午8:20:30
 */
public class MySqlDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(MySqlDao.class);

	public static List<Object[]> searchSeed(int begin, int end) {
		List<Object[]> arraylist = null;
		String sql = "select * from seed limit " + begin + "," + end;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query( sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询seed", e);
		}
		return arraylist;
	}

	/***
	 * 查询指定条数的seed集合 描述
	 * 
	 * @param str
	 *            查询条件
	 * @return
	 */
	public static List<Object[]> searchSeed(String str) {
		List<Object[]> arraylist = null;
		StringBuilder sql = new StringBuilder("select * from seed ");
		sql.append(str);
		try {
			logger.info("本次执行sql："+sql.toString());
			arraylist = queryRunner.query( sql.toString(), new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询seed", e);
		}
		return arraylist;
	}


	/***
	 * 查询flag为0的指定集合
	 * 描述
	 * @param number
	 * @return
	 */
	public static List<Object []> searchCompnays(int number){
		List<Object[]> arraylist = null;
		String sql = "select * from companys  where  flag='0' limit " + number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query( sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询companys", e);
		}
		return arraylist;
	}
	/**
	 * 根据seed指定的条件进行查询 描述
	 * 
	 * @param seed
	 * @return
	 */
	public static List<Object[]> searchSeedBy(Seed seed) {
		List<Object[]> arraylist = null;
		StringBuffer sql = new StringBuffer(512);
		sql.append(" select * from seed as po where (1=1) ");
		Method[] methods = seed.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String methodName = method.getName();
			if (methodName.startsWith("get") && !methodName.endsWith("Str") && !methodName.startsWith("getClass") && !methodName.endsWith("ExtAttribute")) {
				String fieldName = methodName.substring(3);
				fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
				String returnType = method.getReturnType().getName();
				try {
					if (returnType.equals("java.lang.String")) {
						String value = (String) method.invoke(seed, null);
						if (value != null && !"".equals(value.trim())) {
							sql.append(" and po." + fieldName + " = '" + value + "'");
						}
					} else if (returnType.equals("java.lang.Long")) {
						Long value = (Long) method.invoke(seed, null);
						if (value.longValue() != 0) {
							sql.append(" and po." + fieldName + " = " + value.longValue());
						}
					} else if (returnType.equals("int")) {
						Integer value = (Integer) method.invoke(seed, null);
						if (value.intValue() != 0) {
							sql.append(" and po." + fieldName + " = " + value.intValue());
						}
					} else if (returnType.equals("double")) {
						Double value = (Double) method.invoke(seed, null);
						if (value.doubleValue() != 0) {
							sql.append(" and po." + fieldName + " = " + value.doubleValue());
						}
					} else if (returnType.equals("java.lang.Integer")) {
						Integer value = (Integer) method.invoke(seed, null);
						if (value != null && value.intValue() != 0) {
							sql.append(" and po." + fieldName + " =" + value.intValue());
						}
					} else if (returnType.equals("java.lang.Double")) {
						Double value = (Double) method.invoke(seed, null);
						if (value != null && value.doubleValue() != 0) {
							sql.append(" and po." + fieldName + " =" + value.doubleValue());
						}
					}else if(returnType.equals("java.sql.Timestamp")){
						Timestamp value = (Timestamp) method.invoke(seed, null);
						if (value != null) {
							sql.append(" and po." + fieldName + " =" + value.toString());
						}
					}
				} catch (Exception e) {
					logger.error("searchSeedBy", e);
				}
			}
		}
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query( sql.toString(),new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询seed", e);
		}

		return arraylist;
	}

	/***
	 * 更新Seed表 描述
	 * 
	 * @param seed
	 * @return
	 */
	public static int updateSeed(Seed seed) {
		int result = 0;
		String sql = "update seed set name=?,sdProvince=?,matchTime=?,start_time=?,end_time=? where id=? ";
		Object[] params = { seed.getName(), seed.getSdProvince(),seed.getMatchTime(),seed.getStart_time(),seed.getEnd_time(),seed.getId() };
		try {
			result = queryRunner.update( sql, params);
		} catch (SQLException e) {
			logger.error("更新seed", e);
		}
		return result;
	}
	public static int deleteSeed(int seedID) {
		int result = 0;
		String sql = "delete from seed where id=? ";
		Object[] params = { seedID };
		try {
			//logger.info(seed.getName()+":本次执行sql："+sql);
			result = queryRunner.update( sql, params);
		} catch (SQLException e) {
			logger.error("删除seed", e);
		}
		return result;
	}
	/***
	 * 向种子表插入数据
	 * 
	 * @param seed
	 * @return
	 */
	public static int insertSeed(Seed seed) {
		int result = 0;
		String sql = "insert into seed (name,sdProvince,matchTime,start_time,end_time) values(?,?,?,?,?)";
		Object[] params = { seed.getName(), seed.getSdProvince(), seed.getMatchTime(), seed.getStart_time(),seed.getEnd_time()};
		try {
			//logger.info("本次执行sql："+sql);
			result = queryRunner.update( sql, params);
		} catch (SQLException e) {
			logger.error("插入seed", e);
		} 
		return result;
	}

	/***
	 * 
	 * 根据注册号查询companys数据库表
	 * 
	 * @param companys
	 * @return
	 */
	public static int searchCompanys(Companys companys) {
		int result = 0;
		try {
			String sql = "select * from companys where regNumber='" + companys.getRegNumber()+"'";
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query( sql,new ArrayListHandler());
			result = arraylist.size();
		} catch (SQLException e) {
			logger.error("查询Companys", e);
		} 
		return result;
	}
	/***
	 * 
	 * 根据注册号查询companys数据库表
	 * 
	 * @param companys
	 * @return
	 */
	public static List<Object[]> searchCompanys(String regNum) {
		try {
			String sql = "select * from companys where regNumber='" + regNum+"'";
			//logger.info("本次执行sql："+sql);
			return queryRunner.query( sql,new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询Companys", e);
			return null;
		} 
	}
	/***
	 * 
	 * 根据名称注册号查询companys数据库表 (by gao_jun,2015年3月15日)
	 * 
	 * @param name
	 *            公司名称
	 * @param regNumber
	 *            公司注册号
	 * @return 记录条数
	 */
	public static int searchCompanysByNameAndRegNumber(String name,String regNumber) {
		int result = 0;
		try {
			StringBuilder sql = new StringBuilder("select * from companys where name='").append(name)
					.append("' and regNumber='").append(regNumber).append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query( sql.toString(),
					new ArrayListHandler());
			result = arraylist.size();
		} catch (SQLException e) {
			logger.error("查询Companys", e);
		}

		return result;

	}

	/***
	 * 根据企业名字查询企业
	 * @param name
	 * @return
	 */
	public static int searchCompanysByName(String name)
	{
		int result=0;
		try {

			StringBuilder sql = new StringBuilder("select * from companys where name='").append(name)
					.append("'");
			logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query( sql.toString(),
					new ArrayListHandler());
			result = arraylist.size();
		} catch (SQLException e) {
			logger.error("根据名称查询Companys{},异常信息{}",name, e);
		}

		return result;
	}
	
	/**
	 * 根据name查询seed
	 * @param name
	 * @return
	 */
	public static int searchSeedByName(String name){
		int result=0;
		try {
			StringBuilder sql = new StringBuilder("select * from seed where name='").append(name).append("'");
			//logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query( sql.toString(),new ArrayListHandler());
			result = arraylist.size();
		} catch (SQLException e) {
			logger.error("根据名称查询Seed{},异常信息{}",name, e);
		}
		return result;
	}
	public static List<Object[]> searchSeedByNameAndGetResult(String name){
		try {
			StringBuilder sql = new StringBuilder("select * from seed where name='").append(name).append("'");
			return queryRunner.query( sql.toString(),new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("根据名称查询Seed{},异常信息{}",name, e);
			return null;
		}
	}
	/***
	 * 查询flag为0的企业信息列表,flag为1 标识查询过
	 * 描述
	 * @param flag
	 * @return
	 */
	public static List<Object[]> searchCompanysByFlag(String flag)
	{
		List<Object[]> arraylist=new ArrayList<Object[]>();
		try {

			StringBuilder sql = new StringBuilder("select * from companys where flag='").append(flag).append("'");
			logger.info("本次执行sql："+sql);
			arraylist= queryRunner.query( sql.toString(),
					new ArrayListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("查询Companys", e);
		}
		return arraylist;
	}
	/***
	 * 公司企业表插入信息 描述
	 * 
	 * @param company
	 * @return
	 */
	public static int insertCompanys(Companys company) {
		int result = 0;
		String sql = "insert into companys (name,regNumber,url,gather_num) values(?,?,?,?)";
		Object[] params = { company.getName(), company.getRegNumber(),company.getUrl(),company.getGather_num() };
		try {
			//logger.info("本次执行sql："+sql);
			result = queryRunner.update( sql, params);
		} catch (SQLException e) {
			logger.error("插入Companys", e);
		} 
		return result;
	}

	/***
	 * 公司企业信息表中批量插入数据 描述
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchCompanys(List<Companys> companys) {
		int result[] = {};
		String sql = "insert into companys (name,regNumber,url) values(?,?,?)";
		List<Companys> companysNew = new ArrayList<Companys>();
		// 批量插入前先批量查询一下，看看有没有重复的，重复的删除掉，不再保存(by gao_jun,2015年3月15日)
		if (companys != null && companys.size() > 0) {
			for (Companys com : companys) {
				if (searchCompanysByNameAndRegNumber(com.getName(),
						com.getRegNumber()) <= 0) {
					companysNew.add(com);
				}
			}
		}
		String[][] params = new String[companysNew.size()][3];
		if (companysNew != null && companysNew.size() > 0) {
			for (int i = 0; i < companysNew.size(); i++) {
				Companys comp = companysNew.get(i);
				params[i][0] = comp.getName();
				params[i][1] = comp.getRegNumber();
				params[i][2] = comp.getUrl();
			}
		}
		try {
			logger.info("本次执行sql："+sql);
			result = queryRunner.batch( sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入companys", e);
		}
		return result;
	}

	public static void updateCompany(Companys company) {
		String sql = "update companys set name=?,regNumber=?,url=?,flag=?,recordingTime=?,entId=?,type=?,gather_num=? where id=? ";
		Object[] params = {company.getName(),company.getRegNumber(),company.getUrl(),company.getFlag(),company.getRecordingTime()
				,company.getEntId(),company.getType(),company.getGather_num(),company.getId()};
		try {
			//logger.info(seed.getName()+":本次执行sql："+sql);
			 queryRunner.update( sql, params);
		} catch (SQLException e) {
			logger.error("更新companys", e);
		}
	}

}
