package com.heetian.spider.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class TSTPipeline implements Pipeline {
	private static Logger logger = LoggerFactory.getLogger(TSTPipeline.class);
	@Override
	public void process(ResultItems resultItems, Task task) {
		logger.warn("警告，resultitems中有冗余数据:"+resultItems);
	}
}
