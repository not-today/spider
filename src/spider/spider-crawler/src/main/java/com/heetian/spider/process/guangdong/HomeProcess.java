package com.heetian.spider.process.guangdong;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.GuangDongProcessHandlePrepare;
/**
 * 珠海格力电器股份有限公司,美的集团有限公司,华为技术有限公司,深圳市中兴通讯资产管理有限公司
 * 中国南方电网有限责任公司
 * 广东振戎能源有限公司
 * 一url：广东省大旺物资企业集团公司（主管部门）
 * 两url：中国长城计算机深圳股份有限公司，深圳市中兴通讯资产管理有限公司
 * 错误url：91440101741884392G，440106000373558，440101000311677，440125000193254，440126000542270 ，440111000868054 ，440101000373326 
 *第三种基本信息url：
	    广州群帮科技有限公司
	    注册号/统一社会信用代码:440101000311677 
	    广东源旺科技有限公司
	    注册号/统一社会信用代码:440125000193254
 * @author tst
 *
 */
public class HomeProcess extends GuangDongProcessHandlePrepare{
	public HomeProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		setUniqueWebUri("http://gsxt.gdgs.gov.cn/aiccips/");
		setProcessName(processName_home);
	}
	@Override
	protected void analyticalProcess(Page page, PageProcessor task) {
		String url = "http://gsxt.gdgs.gov.cn/aiccips/verify.html?random="+Math.random();
		page.addTargetRequest(builderRequest(url,Method.GET, null,null, null));
	}
}
