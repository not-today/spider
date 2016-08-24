package com.heetian.spider.dbcp.dao.imple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import com.hazelcast.core.IMap;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.dbcp.bean.PvnObj;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoIHazelcastInter;
import com.heetian.spider.dbcp.dao.inter.ProxyDaoInter;
import com.heetian.spider.dbcp.utils.ConstantDBCP;

public class ProxyDaoIHazelcastImp implements ProxyDaoInter ,ProxyDaoIHazelcastInter{
	@Override
	public void addProxy(Proxy proxy) {
		logger.info("获得一条代理:"+proxy);
		String ip = proxy.getIp();
		int port = proxy.getPort();
		String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
		if(proxy.getStable()==ProxyStatus.YSE){//稳定型代理处理
			client.getMap(stable_proxy).put(uuid, new Proxy(ip, port,uuid));
		}else if(proxy.getStable()==ProxyStatus.NO){
			client.getMap(name_ip).put(ip, new Proxy(ip, port,uuid));
			client.getMap(name_uuid).put(uuid, new Proxy(ip, port,uuid));
			for(String pvn:ConstantDBCP.pvns){
				client.getMap(pvn).put(uuid, new Proxy(ip, port,uuid));
			}
		}
	}
	@Override
	public void clearProxy(){
		for(String pvn:ConstantDBCP.pvns){
			client.getMap(pvn).clear();
		}
		IMap<Object, Object> imap = client.getMap(name_ip);
		Set<Entry<Object, Object>> entrysets = imap.entrySet();
		Iterator<Entry<Object, Object>> iter = entrysets.iterator();
		while(iter.hasNext()){
			Entry<Object, Object> entry = iter.next();
			Proxy proxy = (Proxy) entry.getValue();
			for(String pvn:ConstantDBCP.pvns){
				client.getMap(pvn).put(proxy.getUuid(), new Proxy(proxy.getIp(), proxy.getPort(),proxy.getUuid()));
			}
		}
	}
	@Override
	public void clearProxyAll(){
		client.getMap(name_ip).clear();
		client.getMap(name_uuid).clear();
		client.getMap(proxyType_key).clear();
		client.getMap(stable_proxy).clear();
		for(String pvn:ConstantDBCP.pvns){
			client.getMap(pvn).clear();
		}
		IMap<Object, Object> imap = client.getMap(name_ip);
		Set<Entry<Object, Object>> entrysets = imap.entrySet();
		Iterator<Entry<Object, Object>> iter = entrysets.iterator();
		while(iter.hasNext()){
			Entry<Object, Object> entry = iter.next();
			Proxy proxy = (Proxy) entry.getValue();
			System.out.println("还存在"+proxy);
		}
		System.out.println("clean over");
	}
	@Override
	public IMap<Object, Object> showAllStableProxys(){
		return client.getMap(stable_proxy);
	}
	@Override
	public Proxy queryProxy(String code) {
		IMap<Object, Object> imap = client.getMap(code);
		Set<Entry<Object, Object>> entrysets = imap.entrySet();
		Iterator<Entry<Object, Object>> iter = entrysets.iterator();
		while(iter.hasNext()){
			Entry<Object, Object> entry = iter.next();
			Proxy proxy = (Proxy) entry.getValue();
			if(proxy.getStatus()!=ProxyStatus.YSE)
				continue;
			iter.remove();
			return proxy;
		}
		return null;
	}
	
	@Override
	public Proxy queryProxyIP(String ip) {
		return  (Proxy) client.getMap(name_ip).get(ip);
	}
	@Override
	public void updateProxy(Proxy proxy, String code) {
		client.getMap(code).put(proxy.getUuid(), proxy);
	}
	@Override
	public PvnObj pollProxyType(String code){
		return (PvnObj) client.getMap(proxyType_key).get(code);
	}
	public IMap<Object, Object> showAllType(){
		return client.getMap(proxyType_key);
	}
	public  void clearAll(){
		client.getMap(name_ip).clear();
		client.getMap(name_uuid).clear();
		for(String pvn:ConstantDBCP.pvns)
			client.getMap(pvn).clear();
		client.getMap(proxyType_key).clear();
		client.getMap(stable_proxy).clear();
	}
	public static void main(String[] args) {
		ProxyDaoInter pdi = new ProxyDaoIHazelcastImp();
		ProxyDaoIHazelcastImp tmp = (ProxyDaoIHazelcastImp) pdi;
		tmp.clearAll();
		System.out.println("over");
	}
	@Override
	public List<Proxy> showAllNotStableProxys() {
		List<Proxy> ps = new ArrayList<>();
		Iterator<Entry<Object, Object>> iter = client.getMap(name_ip).entrySet().iterator();
		while(iter.hasNext()){
			ps.add((Proxy) iter.next().getValue());
		}
		return ps;
	}
	@Override
	public List<Proxy> showAllNotStableSiglePvnProxys(String code) {
		List<Proxy> ps = new ArrayList<>();
		Iterator<Entry<Object, Object>> iter = client.getMap(code).entrySet().iterator();
		while(iter.hasNext()){
			ps.add((Proxy) iter.next().getValue());
		}
		return ps;
	}
	@Override
	public void deleteProxy(String uuid) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateProxy(Proxy proxy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Proxy queryProxyUUID(String uuid) {
		return null;
	}
	@Override
	public void addProxys(List<Proxy> proxys) {
		// TODO Auto-generated method stub
		
	}
}
