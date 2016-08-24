package com.heetian.spider.mysql.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.heetian.spider.tools.ReadConfig;
  
/**  
 * <p>  
 * Title: DbPoolConnection.java 
 * </p>  
 * <p>  
 * Description: 数据库连接池类
 * </p> 
 * @author gao_jun 
 * @version 1.0  
 * @created 2015年3月16日 下午4:16:43 
 */
public class DbPoolConnection {  

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(DbPoolConnection.class);

    /**  数据库连接池 */
    private static DbPoolConnection databasePool = null;  
    /**  Druid数据源 */
    private static DruidDataSource dds = null;  
    static {  
        Properties properties = loadPropertyFile("dbServer.properties");  
        try {  
            dds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);  
        } catch (Exception e) {  
        	logger.error("抛出异常: " + e);  
        }  
    }  
  
    private DbPoolConnection() {  
    }  
  
    public static synchronized DbPoolConnection getInstance() {  
        if (null == databasePool) {  
            databasePool = new DbPoolConnection();  
        }  
        return databasePool;  
    }  
  
    public DruidDataSource getDataSource() throws SQLException {  
        return dds;  
    }  
  
    public DruidPooledConnection getConnection() throws SQLException {  
        return dds.getConnection();  
    }  
  
    public static Properties loadPropertyFile(String fullFile) {  
        if (null == fullFile || fullFile.equals("")) { 
            throw new IllegalArgumentException("Properties file path can not be null : " + fullFile);  
        }
        InputStream inputStream = null;  
        Properties p = null;  
        try {  
        	//修改 
           inputStream = new FileInputStream(ReadConfig.getCfgFile(fullFile));  
            //inputStream = DbPoolConnection.class.getClassLoader().getResourceAsStream(fullFile);
            p = new Properties();  
            p.load(inputStream);  
        } catch (FileNotFoundException e) {  
            throw new IllegalArgumentException("Properties file not found: " + fullFile);  
        } catch (IOException e) {  
            throw new IllegalArgumentException("Properties file can not be loading: " + fullFile);  
        } finally {  
            try {  
                if (inputStream != null)  
                    inputStream.close();  
            } catch (IOException e) {  
            	logger.error("抛出异常: " + e);  
            }  
        }  
        return p;  
    }  
  
}  
