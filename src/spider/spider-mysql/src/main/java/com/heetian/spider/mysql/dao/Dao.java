package com.heetian.spider.mysql.dao;

import org.apache.commons.dbutils.QueryRunner;

import com.heetian.spider.mysql.util.DBUtilsHelper;

/**  
 * <p>  
 * Title: Dao.java 
 * </p>  
 * <p>  
 * Description: DAO公共父类
 * </p> 
 * @author admin    
 * @version 1.0  
 * @created 2015年3月16日 下午4:00:52 
 */
public class Dao {
    /**  自行封装的工具类 */
 protected static  DBUtilsHelper dbh = new DBUtilsHelper();  
    
    /**  DBUtil运行容器  */
 protected static   QueryRunner queryRunner = dbh.getRunner(); 

}
