package com.heetian.spider.observer;

import us.codecraft.webmagic.Page;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.process.abstractclass.ProcessHandlePrepare;


public class DefaultErrorStatusListen extends AbstractListen {
	@Override
	protected void errorStatus200(Page page, TSTPageProcessor tst, ProcessHandlePrepare handle) {
		super.errorStatus200(page, tst, handle);
	}

	@Override
	protected void errorStatus300(Page page, TSTPageProcessor task, ProcessHandlePrepare handle, String value) {
		super.errorStatus300(page, task, handle, value);
	}

	@Override
	protected void errorStatus400(Page page, TSTPageProcessor task, ProcessHandlePrepare handle) {
		super.errorStatus400(page, task, handle);
	}

	@Override
	protected void errorStatus500(Page page, TSTPageProcessor task, ProcessHandlePrepare handle) {
		super.errorStatus500(page, task, handle);
	}

	@Override
	protected void errorStatus1(Page page, TSTPageProcessor task, ProcessHandlePrepare handle) {
		super.errorStatus1(page, task, handle);
	}
	
}
