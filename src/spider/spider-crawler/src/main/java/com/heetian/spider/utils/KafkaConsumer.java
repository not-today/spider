package com.heetian.spider.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class KafkaConsumer {
	private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	private String commitEnable = "true"; //不自动提交
	public static void startKafkaConsumer() {
		new KafkaConsumer().startConsumer(KafkaCfg.getTopics());
	}
	public void startConsumer(String ...topics) {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		for(String topic:topics){
			topicCountMap.put(topic, 1);
		}
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = creatConsumer().createMessageStreams(topicCountMap);
		Set<Entry<String, List<KafkaStream<byte[], byte[]>>>> entryset = consumerMap.entrySet();
		Iterator<Entry<String, List<KafkaStream<byte[], byte[]>>>> iter = entryset.iterator();
		while(iter.hasNext()){
			Entry<String, List<KafkaStream<byte[], byte[]>>> entry = iter.next();
			String key = entry.getKey();
			List<KafkaStream<byte[], byte[]>> value = entry.getValue();
			KafkaStream<byte[], byte[]> stream = value.get(0);
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
			TopicProcessConsumer consumer = KafkaCfg.process.get(key);
			consumer.setConsumerIter(it,key);
			new Thread(consumer).start();
		}
	}
	public ConsumerConnector creatConsumer() {
		String groupID = "heetian"; //消费端 标识-test消费端
		try {
			groupID = InetAddress.getLocalHost().getHostAddress();
			logger.debug("kafka的groupID是"+groupID+"............................");
		} catch (UnknownHostException e) {
			logger.warn("获取本地ip失败，设置kafka的groupID失败,将其设置为默认groupID["+groupID+"]",e);
		}
		Properties properties = new Properties();
		properties.put("zookeeper.connect", KafkaCfg.ZK_CONNECT);
		properties.put("group.id", groupID);
		properties.put("auto.offset.reset", KafkaCfg.AUTO_OFFSET_RESET);
		properties.put("auto.commit.enable", commitEnable);
		properties.put("zookeeper.session.timeout.ms", KafkaCfg.ZK_SESSION_TIMEOUT_MS);
		properties.put("zookeeper.sync.time.ms", KafkaCfg.ZK_SYNC_TIME_MS);
		properties.put("auto.commit.interval.ms", KafkaCfg.AUTO_COMMIT_INTERVAL_MS);
		return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
	}
}
