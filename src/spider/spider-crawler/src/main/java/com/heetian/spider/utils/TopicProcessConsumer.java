package com.heetian.spider.utils;

import java.util.regex.Pattern;

import kafka.consumer.ConsumerIterator;

public interface TopicProcessConsumer extends Runnable{
	public static final Pattern pattern = Pattern.compile("[\u4e00-\u9fa5_a-zA-Z0-9_（）()]+\\n(\\d{6}:[0123456789],{0,1})+");
	public void process(String msg);
	public void setConsumerIter(ConsumerIterator<byte[], byte[]> consumerIter,String topic);
}	
