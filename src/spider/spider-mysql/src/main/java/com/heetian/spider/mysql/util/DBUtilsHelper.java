package com.heetian.spider.mysql.util;

import java.sql.SQLException;  

import javax.sql.DataSource;  

import org.apache.commons.dbutils.QueryRunner;  
  
/**  
 * <p>  
 * Title: DBUtilsHelper.java 
 * </p>  
 * <p>  
 * Description: 数据库工具连接类
 * </p> 
 * @author admin    
 * @version 1.0  
 * @created 2015年3月16日 下午4:20:20 
 */
public class DBUtilsHelper {  
	
    /**  数据源 */
    private DataSource ds = null;  
    /**  DBUtil运行容器  */
    private QueryRunner runner = null;  
  
    /**  
    * 构造函数  
    */ 
    public DBUtilsHelper() {  
        try {  
            this.ds = DbPoolConnection.getInstance().getDataSource();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        if (this.ds != null) {  
            this.runner = new QueryRunner(this.ds);  
        }  
    }  
  
    /**  
    * 构造函数
    * @param ds  
    */ 
    public DBUtilsHelper(DataSource ds) {  
        this.ds = ds;  
        this.runner = new QueryRunner(this.ds);  
    }  
  
    /**  
     * 获取DBUtil运行容器 
     * @return  
     */
    public QueryRunner getRunner() {  
        return this.runner;  
    }  
}  
