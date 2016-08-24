package com.heetian.spider.observer;

import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.SeedStatusEnum;
import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.ProcessHandlePrepare;

public abstract class AbstractListen implements ErrorStatus{
	protected static final String PROTECTOL = "http://";
	protected static Logger logger = LoggerFactory.getLogger(AbstractListen.class);
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void downloadErrorForStatus(Page page, PageProcessor task,ProcessHandlePrepare handle) {
		//不执行后续的存储
		TSTPageProcessor tst = (TSTPageProcessor)task;
		page.getResultItems().setSkip(true);
		int status = page.getStatusCode();
		if(status>=0&&status<300){
			errorStatus200(page, tst, handle);
		}else if(status>=300&&status<400){
			Header[] heads = (Header[]) page.getRequest().getExtra("allHead");
			for(Header head:heads){
				if(!"location".equalsIgnoreCase(head.getName()))
					continue;
				errorStatus300(page, tst, handle,head.getValue());
			}
		}else if(status>=400&&status<500){
			errorStatus400(page, tst, handle);
		}else if(status>=500){
			errorStatus500(page, tst, handle);
		}else if(status==-1){
			errorStatus1(page, tst, handle);
		}
	}

	protected void defaultProcess(Page page, TSTPageProcessor tst,ProcessHandlePrepare handle) {
		//要进行的差异化操作
		String regNumber = (String) page.getRequest().getExtra(ProcessHandle.REGNUMBER);
		String entName = (String) page.getRequest().getExtra(ProcessHandle.ENTNAME);
		String seedName = tst.getSeedNm();
		logger.error("http请求失败;关键信息-->"
			+ "种子["+seedName+"]"
			+ "注册号["+regNumber+"]"
			+ "企业名["+entName+"]"
			+ "状态码["+page.getStatusCode()+"]"
			+ "失败原因["+page.getError()+"]"
			+ "url["+page.getRequest().getUrl()+"]"
			+"proxy["+tst.getSite().getHttpProxy()+"]");
		tst.setProxyStatus(ProxyStatus.NO);//对代理进行处理
		tst.setStatus(SeedStatusEnum.reco);//设置种子的类型为内存回收
		//失败请求，若注册码存在则设置数据对象的状态为0，即废弃该对应的注册码数据
		tst.setDataStatusForFail((String) page.getRequest().getExtra(ProcessHandle.REGNUMBER));
	}
	protected String builderURL(String subURL,Site site){
		if(subURL==null)
			throw new RuntimeException("构建url失败，对处理uri时");
		if(!subURL.contains(PROTECTOL))
			subURL = PROTECTOL+site.getDomain()+subURL;
		return subURL;
	}
	protected void errorStatus200(Page page, TSTPageProcessor tst,ProcessHandlePrepare handle){
		defaultProcess(page, tst, handle);
	}
	protected void errorStatus300(Page page, TSTPageProcessor task,ProcessHandlePrepare handle, String value){
		if(value!=null&&!"".equals(value)){
			String url = builderURL(value, task.getSite());
			if(value.contains("?")){
				url = builderURL(url+"&"+System.currentTimeMillis(),task.getSite());
			}else{
				url = builderURL(url+"?"+System.currentTimeMillis(),task.getSite());
			}
			logger.info("302错误,已经跳转到此连接:"+url);
			Request request = page.getRequest();
			request.setUrl(url);
			request.putExtra("redirect","300");
			request.setMethod(Method.GET);
			page.addTargetRequest(request);
		}
	}
	protected void errorStatus400(Page page, TSTPageProcessor tst,ProcessHandlePrepare handle){
		defaultProcess(page, tst, handle);
	}
	protected void errorStatus500(Page page, TSTPageProcessor tst,ProcessHandlePrepare handle){
		defaultProcess(page, tst, handle);
	}
	protected void errorStatus1(Page page, TSTPageProcessor tst,ProcessHandlePrepare handle){
		defaultProcess(page, tst, handle);
	}
}
