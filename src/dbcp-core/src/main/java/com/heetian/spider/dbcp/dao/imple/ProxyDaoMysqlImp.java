package com.heetian.spider.dbcp.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.dbcp.bean.PvnCfg;
import com.heetian.spider.dbcp.bean.PvnObj;
import com.heetian.spider.dbcp.bean.Seed;
import com.heetian.spider.dbcp.bean.SeedMapping;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoInter;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoMysqlInter;
import com.heetian.spider.dbcp.utils.DBCPManager;

public class ProxyDaoMysqlImp implements ProxyDaoInter,ProxyDaoMysqlInter {
	public static void main(String[] args) {
		String useFixedIP="114.215.140.117:16816;101.200.187.22:16816;101.200.159.196:16816;101.200.185.203:16816;115.28.146.28:16816;120.24.184.6:16816;42.51.156.185:16816;121.41.11.179:16816;122.114.87.228:16816;114.215.174.49:16816";
		ProxyDaoMysqlInter dao = new ProxyDaoMysqlImp();
		String tmp[] = useFixedIP.split(";");
		for(String t :tmp){
			String tt[] = t.split(":");
			Proxy p = new Proxy(tt[0], Integer.parseInt(tt[1]), Proxy.newUUID());
			p.setStable(ProxyStatus.YSE);
			dao.addProxy(p);
		}
	}
	@Override
	public void addProxy(Proxy proxy) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into proxy(uuid,ip,port,stable) value(?,?,?,?)");
			ps.setString(1,proxy.getUuid() );
			ps.setString(2,proxy.getIp() );
			ps.setInt(3,proxy.getPort() );
			ps.setString(4,proxy.getStable().getIndex() );
			ps.execute();
			conn.commit();
		} catch (SQLException|NullPointerException e) {
			DBCPManager.newInstance().rollback(conn);
			logger.error("添加proxy失败("+proxy+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
	}
	@Override
	public void addProxyReflect(Proxy proxy){
		Connection conn = null;
		try {
			conn = DBCPManager.newInstance().getConnCommit();
			if(queryProxyIP(proxy.getIp(),conn)==null){
				String uuid = Proxy.newUUID();
				proxy.setUuid(uuid);
				addProxy(proxy,conn);
				List<PvnObj> pvns = PvnObj.init32();
				for(PvnObj pvn:pvns){
					addProxyReflect(uuid, pvn.getCode(), conn);
				}
				conn.commit();
			}
		} catch (NullPointerException | SQLException e) {
			DBCPManager.newInstance().rollback(conn);
			logger.error("添加proxy以及对应关系失败("+proxy+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, null, null);
		}
	}
	private Proxy queryProxyIP(String ip, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select port,uuid,stable from proxy wehre ip=?");
			ps.setString(1,ip);
			rs = ps.executeQuery();
			while(rs.next()){
				Proxy proxy = new Proxy(ip, rs.getInt(1), rs.getString(2));
				if("1".equals(rs.getString(3))){
					proxy.setStable(ProxyStatus.YSE);
				}else{
					proxy.setStable(ProxyStatus.NO);
				}
				return proxy;
			}
		} catch (SQLException e) {
			throw e;
		}finally{
			DBCPManager.newInstance().closeConn(null, ps, null);
		}
		return null;
	}
	private void addProxyReflect(String uuid,String code, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into proxy_reflect(uuid,code) value(?,?)");
			ps.setString(1,uuid);
			ps.setString(2,code);
			ps.execute();
		} catch (SQLException e) {
			throw e;
		}finally{
			DBCPManager.newInstance().closeConn(null, ps, null);
		}
	}
	private void addProxy(Proxy proxy, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("insert into proxy(uuid,ip,port,stable) value(?,?,?,?)");
			ps.setString(1,proxy.getUuid() );
			ps.setString(2,proxy.getIp() );
			ps.setInt(3,proxy.getPort() );
			ps.setString(4,proxy.getStable().getIndex() );
			ps.execute();
		} catch (SQLException e) {
			throw e;
		}finally{
			DBCPManager.newInstance().closeConn(null, ps, null);
		}
	}
	
	@Override
	public void addProxys(List<Proxy> proxys) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into proxy(uuid,ip,port,stable) value(?,?,?,?)");
			Iterator<Proxy> iter = proxys.iterator();
			while(iter.hasNext()){
				Proxy proxy = iter.next();
				ps.setString(1,proxy.getUuid() );
				ps.setString(2,proxy.getIp() );
				ps.setInt(3,proxy.getPort() );
				ps.setString(4,proxy.getStable().getIndex() );
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch (SQLException|NullPointerException e) {
			DBCPManager.newInstance().rollback(conn);
			logger.error("添加proxy失败("+proxys+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
	}
	@Override
	public void deleteProxy(String uuid) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from proxy where uuid=?");
			ps.setString(1,uuid);
			ps.execute();
			conn.commit();
		} catch (SQLException|NullPointerException e) {
			DBCPManager.newInstance().rollback(conn);
			logger.error("删除proxy失败("+uuid+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
	}
	@Override
	public void updateProxy(Proxy proxy) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update proxy set ip=?,port=?,stable=? where uuid=?");
			ps.setString(1,proxy.getIp() );
			ps.setInt(2,proxy.getPort() );
			ps.setString(3,proxy.getStable().getIndex() );
			ps.setString(4,proxy.getUuid() );
			ps.execute();
			conn.commit();
		} catch (SQLException|NullPointerException e) {
			DBCPManager.newInstance().rollback(conn);
			logger.error("更新proxy失败("+proxy+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
	}
	@Override
	public Proxy queryProxyUUID(String uuid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			ps = conn.prepareStatement("select ip,port,stable from proxy wehre uuid=?");
			ps.setString(1,uuid);
			rs = ps.executeQuery();
			while(rs.next()){
				Proxy proxy = new Proxy(rs.getString(1), rs.getInt(2), uuid);
				if("1".equals(rs.getString(3))){
					proxy.setStable(ProxyStatus.YSE);
				}else{
					proxy.setStable(ProxyStatus.NO);
				}
				return proxy;
			}
		} catch (SQLException|NullPointerException e) {
			logger.error("查询proxy失败("+uuid+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
		return null;
	}
	@Override
	public Proxy queryProxyIP(String ip) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			ps = conn.prepareStatement("select port,uuid,stable from proxy wehre ip=?");
			ps.setString(1,ip);
			rs = ps.executeQuery();
			while(rs.next()){
				Proxy proxy = new Proxy(ip, rs.getInt(1), rs.getString(2));
				if("1".equals(rs.getString(3))){
					proxy.setStable(ProxyStatus.YSE);
				}else{
					proxy.setStable(ProxyStatus.NO);
				}
				return proxy;
			}
		} catch (SQLException|NullPointerException e) {
			logger.error("查询proxy失败("+ip+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
		return null;
	}
	@Override
	public void updateProxyReflect() {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update proxy_reflect set status=?");
			ps.setString(1,"1");
			ps.execute();
			conn.commit();
		} catch (SQLException|NullPointerException e) {
			logger.error("更新proxy关系失败",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("回滚更新proxy关系失败",e);
			}
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
	}
	@Override
	public void updateProxyReflect(String uuid, String code) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update proxy_reflect set status=0 where uuid=? and code=?");
			ps.setString(1,uuid);
			ps.setString(1,code);
			ps.execute();
			conn.commit();
		} catch (SQLException|NullPointerException e) {
			logger.error("更新proxy关系失败",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("回滚更新proxy关系失败",e);
			}
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
	}
	@Override
	public List<Proxy> searchProxy(int number,String code) {
		if(code==null||"".equals(code))
			return null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Proxy> proxys = new ArrayList<>();
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			String sql = "select proxy.uuid,proxy.ip,proxy.port,proxy.stable from proxy "
					+ "inner join (select proxy_reflect.uuid from proxy_reflect  where  status=1 and code=? limit  ?) as tmp_table "
					+ "on proxy.uuid=tmp_table.uuid";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			ps.setInt(2, number);
			rs = ps.executeQuery();
			while(rs.next()){
				Proxy proxy = new Proxy(rs.getString(2), rs.getString(3), rs.getString(1));
				if("1".equals(rs.getString(4))){
					proxy.setStable(ProxyStatus.YSE);
				}else{
					proxy.setStable(ProxyStatus.NO);
				}
				proxys.add(proxy);
			}
			conn.commit();
			return proxys;
		} catch (SQLException|NullPointerException e) {
			logger.error("查询proxy关系失败",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("回滚查询proxy关系失败",e);
			}
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
		return null;
	}
	@Override
	public List<Seed> searchSeed(int number) {
		if(number<=0)
			return null;
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Seed> seeds = new ArrayList<Seed>();
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			String sql = "select seed.name,ttb.uuid,ttb.code,ttb.number,ttb.over,ttb.begin,ttb.end from seed "
					+ "inner join (select * from seedmapping  where  over=0 limit  ?) as ttb "
					+ "on seed.uuid=ttb.uuid";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, number);
			rs = ps.executeQuery();
			while(rs.next()){
				Seed seed = new Seed();
				SeedMapping sm = new SeedMapping();
				seed.setSm(sm);
				seed.setName(rs.getString("seed.name"));
				String uuid = rs.getString("ttb.uuid");
				seed.setUuid(uuid);
				sm.setUuid(uuid);
				sm.setBegin(rs.getTimestamp("ttb.begin"));
				sm.setEnd(rs.getTimestamp("ttb.end"));
				sm.setCode(rs.getString("ttb.code"));
				sm.setNumber(rs.getInt("ttb.number"));
				sm.setOver(rs.getString("ttb.over"));
				seeds.add(seed);
			}
			conn.commit();
			return seeds;
		} catch (SQLException|NullPointerException e) {
			logger.error("查询seed关系失败",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("回滚查询seed关系失败",e);
			}
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
		return null;
	}
	@Override
	public void updateSeedReflect(SeedMapping sm) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("update seedmapping set number=? ,over=?,begin=?,end=? where uuid=?");
			ps.setInt(1,sm.getNumber());
			ps.setString(2, sm.getOver());
			ps.setTimestamp(3, sm.getBegin());
			ps.setTimestamp(4, sm.getEnd());
			ps.setString(5, sm.getUuid());
			ps.execute();
			conn.commit();
		} catch (SQLException|NullPointerException e) {
			logger.error("更新seed关系失败",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("回滚更新seed关系失败",e);
			}
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
	}
	@Override
	public void deleteSeedReflect(String uuid, String code) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("delete from seedmapping where uuid=? and code=?");
			ps.setString(1,uuid);
			ps.setString(1,code);
			ps.execute();
			conn.commit();
		} catch (SQLException|NullPointerException e) {
			DBCPManager.newInstance().rollback(conn);
			logger.error("删除seedmapping失败("+uuid+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
	}
	@Override
	public PvnCfg loadPvnCFG(String code) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBCPManager.newInstance().getConn();
			ps = conn.prepareStatement("select id,code,proxy_type,charset from config wehre code=?");
			ps.setString(1,code);
			rs = ps.executeQuery();
			while(rs.next()){
				PvnCfg cfg = new PvnCfg();
				cfg.setId(rs.getInt("id"));
				cfg.setCode(rs.getString("code"));
				cfg.setProxy_type(rs.getString("proxy_type"));
				cfg.setCharset(rs.getString("charset"));
				return cfg;
			}
		} catch (SQLException|NullPointerException e) {
			logger.error("查询cfg失败("+code+")",e);
		}finally{
			DBCPManager.newInstance().closeConn(conn, ps, null);
		}
		return null;
	}
}
