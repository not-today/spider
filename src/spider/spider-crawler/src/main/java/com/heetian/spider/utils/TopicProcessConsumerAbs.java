package com.heetian.spider.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerIterator;
import kafka.message.MessageAndMetadata;

public abstract class TopicProcessConsumerAbs implements TopicProcessConsumer {
	protected static Logger logger = LoggerFactory.getLogger(TopicProcessConsumerAbs.class);
	private ConsumerIterator<byte[], byte[]> consumerIter;
	private String topic;
	public void setConsumerIter(ConsumerIterator<byte[], byte[]> consumerIter,String topic) {
		this.consumerIter = consumerIter;
		this.topic = topic;
	}
	@Override
	public void run() {
		logger.info("启动topic["+this.topic+"]线程-----------------------------------------------");
		try {
			while(consumerIter.hasNext()){
				MessageAndMetadata<byte[], byte[]> mm = consumerIter.next();
				process(new String(mm.message()));
			}
		} catch (Exception e) {
			logger.error("种子接受程序出错",e);
			System.exit(0);
		}
	}
}
