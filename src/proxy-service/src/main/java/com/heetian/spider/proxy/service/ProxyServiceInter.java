package com.heetian.spider.proxy.service;

import java.util.List;
import java.util.Map;

import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.PvnObj;

public interface ProxyServiceInter {
	public Map<String,PvnObj> showAllType();

	public void edit(String pvn, String type);

	public void editChaset(String pvn, String charset);

	public List<Proxy> showAllNotStableProxys();

	public List<Proxy> showAllNotStableSiglePvnProxys(String code);
}
