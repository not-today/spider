package com.heetian.spider.proxy.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hazelcast.core.IMap;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.PvnObj;
import com.heetian.spider.proxy.dao.Manager;

public class ProxyServiceIml implements ProxyServiceInter {

	@Override
	public Map<String,PvnObj> showAllType() {
		IMap<Object, Object> imap = Manager.dao.showAllType();
		Map<String,PvnObj> hmap = new HashMap<String, PvnObj>();
		Set<Object> ikey = imap.keySet();
		Iterator<Object> iter = ikey.iterator();
		while(iter.hasNext()){
			String key = (String) iter.next();
			PvnObj value = (PvnObj) imap.get(key);
			hmap.put(key, value);
		}
		return hmap;
	}

	@Override
	public void edit(String pvn, String type) {
		IMap<Object, Object> noStables = Manager.dao.showAllType();
		PvnObj pvnobj = (PvnObj) noStables.get(pvn);
		pvnobj.setProxyType(type);
		noStables.put(pvn, pvnobj);
	}

	@Override
	public void editChaset(String pvn, String charset) {
		IMap<Object, Object> noStables = Manager.dao.showAllType();
		PvnObj pvnobj = (PvnObj) noStables.get(pvn);
		pvnobj.setCharset(charset);
		noStables.put(pvn, pvnobj);
	}

	@Override
	public List<Proxy> showAllNotStableProxys() {
		return Manager.dao.showAllNotStableProxys();
	}

	@Override
	public List<Proxy> showAllNotStableSiglePvnProxys(String code) {
		return Manager.dao.showAllNotStableSiglePvnProxys(code);
	}
}
