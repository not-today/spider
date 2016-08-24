package com.heetian.spider.process.liaoning;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.LiaoNingProcessHandlePrepare;
import com.heetian.spider.utils.TSTUtils;
/**
 * 210200000501777
 * 此解析页面类不进行任何数据解析，只为了获取cookie值以及构建验证码url
 * @author tst
 */
public class HomeProcess extends LiaoNingProcessHandlePrepare{
	private static final String next = "/saicpub/commonsSC/loginDC/securityCode.action?tdate=";
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("/saicpub/entPublicitySC/entPublicityDC/entPublicity/search/searchmain.jsp");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		//task.getSite().setProvinceCookie(((PekingPageProcessor)task).getWorkInfo().getProvince(), task.getSite().getCookies());//设置cookie缓存
		page.addTargetRequest(builderRequest(builderURL(next+TSTUtils.randomNum(),task.getSite()),Method.GET, null,null, null));
	}
}
