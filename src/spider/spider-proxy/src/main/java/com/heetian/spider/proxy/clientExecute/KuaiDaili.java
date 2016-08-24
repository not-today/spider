package com.heetian.spider.proxy.clientExecute;

import java.util.regex.Matcher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.proxy.model.JSONBean2;
import com.heetian.spider.proxy.model.ProxyBean2;

public class KuaiDaili implements ProxyProcessInterface {

	@Override
	public void process(String content) {
		Gson gson = new Gson();
		JSONBean2 obj = gson.fromJson(content, new TypeToken<JSONBean2>(){}.getType());
		if(obj==null)
			return;
		ProxyBean2 proxyBean2 = obj.getData();
		String[] proxys = proxyBean2.getProxy_list();
		if(proxys==null||proxys.length<=0)
			return;
		for (String proxy:proxys) {
			if(proxy==null||"".equals(proxy.trim()))
				continue;
			Matcher matcher = pattern.matcher(proxy);
			if(!matcher.matches())
				continue;
			String[] proxyStr = proxy.split(":");
			String ip = proxyStr[0];
			String port = proxyStr[1];
			if(dao.queryProxyIP(ip)==null){
				//if (testProxy(ip, sport))
				Proxy tmp = new Proxy(ip,port,null);
				tmp.setStable(ProxyStatus.NO);
				dao.addProxy(tmp);
			}
		}
	}
}
