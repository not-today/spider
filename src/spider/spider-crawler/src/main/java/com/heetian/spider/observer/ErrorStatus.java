package com.heetian.spider.observer;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.process.abstractclass.ProcessHandlePrepare;

public interface ErrorStatus extends Listen{
	/**
	 * 下载过程中出现非200状态码调用的接口
	 * @param page
	 * @param task
	 * @param handle
	 * @param province
	 */
	public void downloadErrorForStatus(Page page,PageProcessor task,ProcessHandlePrepare handle);
}
