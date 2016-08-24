package com.heetian.spider.utils;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducer {
	private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
	private static Producer<String, String> producer;
	static{
		Properties properties = new Properties();
		properties.put("serializer.class", KafkaCfg.SERIALIZER_CALSS);
		properties.put("metadata.broker.list", KafkaCfg.METADATA_BROKER_LIST);
		properties.put("request.required.acks", KafkaCfg.REQUEST_REQUIRED_ACKS);
		producer = new Producer<String, String>(new ProducerConfig(properties));
	}
	public static void send(String data, String topic) {
		if(data==null||"".equals(data.trim())){
			logger.warn("发送kafk数据失败[null或\"\"]");
			return;
		}
		logger.info("topic["+topic+"]-->内容："+data);
		producer.send(new KeyedMessage<String, String>(topic, data));
	}
	public static void sendData(String enterData){
		send(enterData, KafkaCfg.topic_data);
	}
	public static void sendSeedStatus(String seedStatus){
		send(seedStatus, KafkaCfg.topic_seed_send);
		
	}
	public static void main(String[] args) {
		send("zzz", "test");
	}
}
