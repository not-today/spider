package com.heetian.spider.process.abstractclass;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;

/**
 * 标记接口，标记子类为验证码处理类
 * @author tst
 *
 */
public interface KeyPoint_CODE {
	public Request reloadCode(Request request,Site site);
}
