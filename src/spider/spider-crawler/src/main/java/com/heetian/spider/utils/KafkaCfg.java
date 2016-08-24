package com.heetian.spider.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.tools.Configuration;
import com.heetian.spider.tools.ReadConfig;

public class KafkaCfg {
	/**
	 * 数据topic
	 */
	public static  String topic_data = "TOPIC_QYDATA_SOURCE"; 
	//public static final String topic_data = "TOPIC_QYDATA_SOURCE_SPIDER"; 
	/**
	 * 接收种子topic
	 */
	public static  String topic_seed_accept = "TOPIC_QYDATA_SEED1";

	public static  Map<String, TopicProcessConsumer> process = new HashMap<String, TopicProcessConsumer>();
	
	/**
	 * 回送种子状态topic
	 */
	public static  String topic_seed_send = "TOPIC_QYDATA_SEED2"; 
	/**
	 * 种子正常采集结束
	 */
	public static final String topic_seed_send_status_success = "3"; 
	/**
	 * 其他操作
	 */
	public static final String topic_seed_send_status_other = "2"; 
	/**
	 * 种子失败多次，程序丢失该种子
	 */
	public static final String topic_seed_send_status_fail0 = "11";
	/**
	 * 种子失败一次，但种子回收到采集程序种子集合中，等待下一次采集
	 */
	public static final String topic_seed_send_status_fail1 = "12"; 
	/**
	 * 种子发送过来时，默认状态
	 */
	public static final String topic_seed_send_status_default = "0"; 
	
	private static Logger logger = LoggerFactory.getLogger(KafkaCfg.class);
	public static String ZK_CONNECT;
	public static String AUTO_OFFSET_RESET;
	public static String ZK_SESSION_TIMEOUT_MS;
	public static String ZK_SYNC_TIME_MS;
	public static String AUTO_COMMIT_INTERVAL_MS;
	
	
	public static String SERIALIZER_CALSS;
	public static String METADATA_BROKER_LIST;
	public static String REQUEST_REQUIRED_ACKS;
	static{
		Properties p = new Properties();
		try {
			p.load(ReadConfig.getCfgStream("dataplatform.properties"));
			Configuration conf = new Configuration(p);
			ZK_CONNECT = conf.getValue("zookeeper.connect");
			AUTO_OFFSET_RESET = conf.getValue("auto.offset.reset");
			ZK_SESSION_TIMEOUT_MS = conf.getValue("zookeeper.session.timeout.ms");
			ZK_SYNC_TIME_MS = conf.getValue("zookeeper.sync.time.ms");
			AUTO_COMMIT_INTERVAL_MS = conf.getValue("auto.commit.interval.ms");
			SERIALIZER_CALSS = conf.getValue("serializer.class");
			METADATA_BROKER_LIST =conf.getValue("metadata.broker.list");
			REQUEST_REQUIRED_ACKS = conf.getValue("request.required.acks");
			
			topic_data = conf.getValue("topic_send_data");
			topic_seed_send = conf.getValue("topic_send_seed_status");
			topic_seed_accept = conf.getValue("topic_receive_seed");
			String topicComps[] = conf.getValue("topic_receive_seed").split(",");
			for(int x=0;x<topicComps.length;x++){
				String tmps[] = topicComps[x].split("\\$");
				TopicProcessConsumer processor = (TopicProcessConsumer) Class.forName(tmps[1]).newInstance();
				process.put(tmps[0], processor);
			}
			
		} catch (Exception e) {
			logger.error("加载kafka配置文件出错",e);
			System.exit(0);
		}
	}
	public static String[] getTopics(){
		Set<String> keySet = process.keySet();
		String topics[] = new String[keySet.size()];
		keySet.toArray(topics);
		return topics;
	}
}
