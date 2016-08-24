package com.heetian.spider.dbcp.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DBCPManager {
	public static Logger logger = LoggerFactory.getLogger(DBCPManager.class);
	private DataSource dataSource;
	private static final DBCPManager instance = new DBCPManager();
	private DBCPManager() {
		Properties dbProperties = new Properties();
		try {
			dbProperties.load(DBCPManager.class.getClassLoader() .getResourceAsStream("dbcp.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(dbProperties);
			Connection conn = getConn();
			DatabaseMetaData mdm = conn.getMetaData();
			logger.info("Connected to " + mdm.getDatabaseProductName() + " " + mdm.getDatabaseProductVersion());
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.error("初始化连接池失败：" + e);
			System.exit(0);
		}
	}
	public static DBCPManager newInstance(){
		return instance;
	}

	/**
	 * 获取链接，用完后记得关闭
	 * 
	 * @see {@link DBManager#closeConn(Connection)}
	 * @return
	 */
	public Connection getConn() {
		return conn(true);
	}
	/**
	 * 获取链接，开启事务的连接
	 * 
	 * @see {@link DBManager#closeConn(Connection)}
	 * @return
	 */
	public Connection getConnCommit() {
		return conn(false);
	}
	private  Connection conn(boolean isSubmit) {
		try {
			Connection conn = dataSource.getConnection();
			conn.setAutoCommit(isSubmit);
			return conn;
		} catch (SQLException e) {
			logger.error("获取数据库连接失败：",e);
			throw new NullPointerException("获取connection对象是为空:"+e.getMessage());
		}
	}
	public void rollback(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			logger.error("回滚事务失败",e);
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn
	 *            需要关闭的连接
	 */
	public void closeConn(Connection conn,Statement st,ResultSet rs) {
		try {
			if(rs!=null&&!rs.isClosed())
				rs.close();
			} catch (SQLException e) {
				logger.error("关闭数据库连接失败：" + e);
			}finally{
				try {
					if(st!=null&&!st.isClosed())
						st.close();
				} catch (SQLException e) {
					logger.error("关闭数据库连接失败：" + e);
				}finally{
					try {
						if (conn != null && !conn.isClosed()) 
							conn.close();
					} catch (SQLException e) {
						logger.error("关闭数据库连接失败：" + e);
					}
				}
			}
		}
}
