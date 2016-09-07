package com.heetian.spider.component;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;

public class TstDuplicateRemover extends HashSetDuplicateRemover {
	
	@Override
	/**
	 * 为了去除对相同url的限制
	 */
	public boolean isDuplicate(Request request, Task task) {
		//super.isDuplicate(request, task);
		System.err.println("URL去重结果"+(super.isDuplicate(request, task)?"重复url":"全新url"));
		return false;
	}

}
