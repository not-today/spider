package com.heetian.spider.dbcp.utils;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastUtils {
	private static HazelcastInstance client;
	public static HazelcastInstance getConnect(ClientConfig clientConfig) {
		return HazelcastClient.newHazelcastClient(clientConfig);
	}
	public static HazelcastInstance getConnect() {
		return HazelcastClient.newHazelcastClient();
	}
	public synchronized static HazelcastInstance getOnlyOneConnect(){
		if(client==null)
			return getConnect();
		return client;
	}
}
