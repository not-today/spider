package com.heetian.spider.dbcp.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heetian.spider.dbcp.dao.inter.RegisterDaoInter;
import com.heetian.spider.dbcp.utils.DBCPManager;

public class RegisterDaoImple implements RegisterDaoInter {
	private static final DBCPManager manager = DBCPManager.newInstance();
	@Override
	public List<String> getAllRegionCode() {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = manager.getConn();
			stmt = conn.createStatement();
			String sql = "SELECT SUBSTRING(regNumber,1,6) AS organCode FROM industryCommerce_registerInfo WHERE LENGTH(regNumber) = 15 GROUP BY organCode HAVING organCode REGEXP '^[0-9]*$'";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String organCode = rs.getString("organCode");
				list.add(organCode);
			}
			return list;
		} catch (SQLException e) {
			throw new NullPointerException("执行sql语句出现问题:"+e.getMessage());
		}finally{
			manager.closeConn(conn, stmt, rs);
	    }
	}

	@Override
	public List<String> getRegListByRegionCode(String regionCode) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = manager.getConn();
			stmt = conn.createStatement();
			String sql = "SELECT regNumber FROM industryCommerce_registerInfo";
			sql += " WHERE";
			sql += " regNumber LIKE '"+regionCode+"%'";
			sql += " AND regNumber REGEXP '^[0-9]*[0-9Nn][0-9AaBb][0-9]*[0-9Xx]$'";
			sql += " AND LENGTH(regNumber) = 15";
			sql += " ORDER BY regNumber";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String regNumber = rs.getString("regNumber");
				list.add(regNumber);
			}
			return list;
		} catch (SQLException e) {
			throw new NullPointerException("执行sql语句出现问题:"+e.getMessage());
		}finally{
			manager.closeConn(conn, stmt, rs);
	    }
		
	}

	@Override
	public boolean batchInsertSeedRg(List<String> regList) {
		if(regList == null || regList.size() == 0) 
			return false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConn();
			stmt = conn.prepareStatement("INSERT INTO seed (name,sdProvince) VALUE(?,-1)");
			for(String reg : regList){
				stmt.setString(1, reg);
				stmt.addBatch();
			}
			stmt.executeBatch();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			manager.closeConn(conn, stmt, null);
	    }
	}

	@Override
	public List<String> queryCompanyName(int start, int limit) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = manager.getConn();
			stmt = conn.createStatement();
			String sql = "SELECT NAME FROM beijing LIMIT " + start + "," + limit;
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString("name"));
			}
			return list;
		} catch (Exception e) {
			throw new NullPointerException("执行sql语句出现问题:"+e.getMessage());
		}finally{
			manager.closeConn(conn, stmt, rs);
	    }
	}

	@Override
	public List<Map<String, String>> queryAllRegionList() {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = manager.getConn();
			stmt = conn.createStatement();
			String sql = "SELECT SUBSTRING(id,1,2) AS pvn,NAME,shortname AS sname FROM organ_info";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Map<String,String> map = new HashMap<String,String>();
				map.put("pvn", rs.getString("pvn"));
				map.put("name", rs.getString("name"));
				map.put("sname", rs.getString("sname"));
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			throw new NullPointerException("执行sql语句出现问题:"+e.getMessage());
		}finally{
			manager.closeConn(conn, stmt, rs);
	    }
	}

	@Override
	public boolean batchInsertSeedNm(List<Map<String, String>> seedList) {
		if(seedList==null||seedList.size()<=0)
			return false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConn();
			stmt = conn.prepareStatement("INSERT INTO seed (name,sdProvince) VALUE(?,?)");
			for(Map<String,String> seed : seedList){
				stmt.setString(1, seed.get("name"));
				stmt.setString(2, seed.get("pvn"));
				stmt.addBatch();
			}
			stmt.executeBatch();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			manager.closeConn(conn, stmt, null);
	    }
	}
	public boolean batchInsertSeedNm1(List<String> seedList) {
		if(seedList==null||seedList.size()<=0)
			return false;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = manager.getConn();
			conn.setAutoCommit(false);
			/*
			第一个参数指定 ResultSet 的类型。其选项有：
			TYPE_FORWARD_ONLY：缺省类型。只允许向前访问一次，并且不会受到其他用户对该数据库所作更改的影响。
			TYPE_SCROLL_INSENSITIVE：允许在列表中向前或向后移动，甚至可以进行特定定位，例如移至列表中的第四个记录或者从当前位置向后移动两个记录。不会受到其他用户对该数据库所作更改的影响。
			TYPE_SCROLL_SENSITIVE：象 TYPE_SCROLL_INSENSITIVE 一样，允许在记录中定位。这种类型受到其他用户所作更改的影响。如果用户在执行完查询之后删除一个记录，那个记录将从 ResultSet 中消失。类似的，对数据值的更改也将反映在 ResultSet 中。
			第二个参数设置 ResultSet 的并发性，该参数确定是否可以更新 ResultSet。其选项有：
			CONCUR_READ_ONLY：这是缺省值，指定不可以更新
			ResultSet CONCUR_UPDATABLE：指定可以更新 ResultSet 
			 */
			stmt = conn.prepareStatement("INSERT INTO seed (name,sdProvince) VALUE(?,-1)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(String seed : seedList){
				stmt.setString(1, seed);
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			manager.closeConn(conn, stmt, null);
	    }
	}

	@Override
	public String[] loadRegins(String reginCode) {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = manager.getConn();
			String sql = "SELECT name,shortname FROM organ_info WHERE id LIKE '"+reginCode+"%' AND NAME NOT LIKE '%区'";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("name"));
				list.add(rs.getString("shortname"));
			}
			return (String[]) list.toArray(new String[list.size()]);
		} catch (Exception e) {
			throw new NullPointerException("执行sql语句出现问题:"+e.getMessage());
		}finally{
			manager.closeConn(conn, stmt, rs);
	    }
	}

}
