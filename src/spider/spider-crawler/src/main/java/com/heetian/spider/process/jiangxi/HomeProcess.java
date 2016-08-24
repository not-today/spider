package com.heetian.spider.process.jiangxi;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.process.abstractclass.JiangXiProcessHandlePrepare;
/**
 * 360000110005386,91360111MA35G9G43B
 * @author tst  江铃汽车集团财务有限公司    泰豪科技股份有限公司  信息全有  江西月兔企业集团有限公司    抚州市缘和电器有限公司
 * 香港世民汽车（中国）有限公司江西代表处
 *
 */
public class HomeProcess extends JiangXiProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.jxaic.gov.cn/?xx=xx");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = builderURL("/warningetp/reqyzm.do?r="+System.currentTimeMillis(),task.getSite());
		page.addTargetRequest(builderRequestGet(url));
	}
}
