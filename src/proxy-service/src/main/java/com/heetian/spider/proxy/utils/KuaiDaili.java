package com.heetian.spider.proxy.utils;

import java.util.regex.Matcher;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.proxy.bean.JSONBean2;
import com.heetian.spider.proxy.bean.ProxyBean2;

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
			Matcher matcher = Proxy.pattern.matcher(proxy);
			if(matcher.find()){
				String ip = matcher.group(1);
				if(dao.queryProxyIP(ip)==null){
					Proxy tmp = new Proxy(ip,matcher.group(2),null);
					tmp.setStable(ProxyStatus.NO);
					dao.addProxy(tmp);
				}
			}
		}
	}
}
