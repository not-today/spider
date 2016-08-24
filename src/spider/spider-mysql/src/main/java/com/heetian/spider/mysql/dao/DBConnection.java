package com.heetian.spider.mysql.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

/***
 * 获取数据库连接的类
 * <p>  
 * Title: DBConnection.java 
 * </p>  
 * <p>  
 * Description: 描述
 * </p> 
 * @author admin    
 * @version 1.0  
 * @created 2015年3月12日 上午10:06:29
 */
public class DBConnection {

	public static Connection getConnection() {
		Connection conn = null;

		String url = "jdbc:mysql://192.168.31.226:3306/spider?useUnicode=true&characterEncoding=utf8";

		String jdbcDriver = "com.mysql.jdbc.Driver";

		String user = "root";

		String password = "123456";

		DbUtils.loadDriver(jdbcDriver);
		try {

			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException e) {

			e.printStackTrace();

		} 
//		finally {
//
//			DbUtils.closeQuietly(conn);
//
//		}

		return conn;
	}
}
