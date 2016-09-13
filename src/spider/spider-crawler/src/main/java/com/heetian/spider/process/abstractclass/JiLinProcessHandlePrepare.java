package com.heetian.spider.process.abstractclass;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.enumeration.SeedStatus;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;


public abstract class JiLinProcessHandlePrepare extends ProcessHandlePrepare {
	protected static final String dwNum = "downloadCodeNumber";
	/**
	 * 吉林特别需要的
	 */
	protected static final String homeFlag = "homeSecondFlag";
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		if(!(this instanceof com.heetian.spider.process.jilin.HomeProcess)){
			if(page.getRawText().contains("<body onload=\"challenge();\">")){
				if(this instanceof com.heetian.spider.process.jilin.DownloadCodeProcess||this instanceof com.heetian.spider.process.jilin.CompanyURLProcess||
						this instanceof com.heetian.spider.process.jilin.ContexJBXXProcess){
					
					page.getRequest().putExtra(homeFlag, null);
					String url = "http://211.141.74.198:8081/aiccips/?xxx&"+urlTail();
					page.addTargetRequest(builderRequest(url, Method.GET, null,null, null));
					
				}else if(this instanceof com.heetian.spider.process.jilin.ContextFZJGProcess||this instanceof com.heetian.spider.process.jilin.ContextZYRYProcess||
					this instanceof com.heetian.spider.process.jilin.ContextGDAndCZXXProcess){
					((TSTPageProcessor)task).setStatus(SeedStatus.FAIL);
					//吉林特使情况的失败，设置数据对象状态
					((TSTPageProcessor)task).setDataStatusForFail((String) page.getRequest().getExtra(REGNUMBER));
				}
				logger.error(this.getClass().getName()+":吉林特有的cookie失效错误");
				return;
			}
		}
		analyticalProcessJiLin(page,task);
	}
	protected abstract void analyticalProcessJiLin(Page page, PageProcessor task);
	@Override
	public void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString) {
		
	}
}
