package com.heetian.spider.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.dbcp.bean.Proxy;
import com.heetian.spider.dbcp.bean.ProxyStatus;
import com.heetian.spider.dbcp.bean.QygsQynb;
import com.heetian.spider.enumeration.SeedStatus;
import com.heetian.spider.observer.ErrorStatus;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.ProcessHandlePrepare;
import com.heetian.spider.tools.SpiderUtils;
import com.heetian.spider.utils.BufferedGsgsRegister;
import com.heetian.spider.utils.BufferedSeed;
import com.heetian.spider.utils.KafkaProducer;
import com.heetian.spider.utils.ProxyManager;
import com.heetian.spider.utils.SeedJsonBean;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class TSTPageProcessor implements PageProcessor {
	private static Logger logger = LoggerFactory.getLogger(TSTPageProcessor.class);
	/**
	 * 种子，一个spider绑定一个pageprocessor。而一个pageprocessor绑定一个种子，即，一个spider绑定一个种子
	 */
	private BufferedSeed seed;
	private Site site = Site.me();
	public void setProxyStatus(ProxyStatus status){
		Proxy proxy = this.seed.getProxy();
		if(proxy!=null)
			proxy.setStatus(status);
	}
	public Proxy getProxy(){
		return this.seed.getProxy();
	}
	/**
	 * 获得区域码
	 * @return
	 */
	public String getcode(){
		return seed.getCode();
	}
	/**
	 * 需保证SBContainer对象内有值，不然出错
	 * @param proxy
	 */
	public TSTPageProcessor() {
		super();
		initSite();
	}
	public void initSite(){
		site.setRetryTimes(SpiderComponentCFG.retryTimes)
			.setSleepTime(SpiderComponentCFG.sleepTime)
			.setUseGzip(SpiderComponentCFG.useGzip)
			.setTimeOut(SpiderComponentCFG.timeOut)
			.setRetrySleepTime(SpiderComponentCFG.retrySleepTime)
			.setUserAgent(SpiderComponentCFG.userAgent)
			;
		//添加http请求头
	    Matcher matcher = Pattern.compile("(\\{)(.*?)(:)(.*?)(\\},{0,1})").matcher(SpiderComponentCFG.header);
	    while(matcher.find()){
	    	String name = matcher.group(2);
	    	String value = matcher.group(4);
	    	site.addHeader(name,value);        
	    }
	}
	@Override
	public void process(Page page) {
		ProcessHandlePrepare handleResponse = (ProcessHandlePrepare) ProcessHandlesManager.getProcessHandle(getProvince());
		Request request = page.getRequest();
		boolean flag = request.getUrl().contains(handleResponse.getUniqueWebUri());
		while(!flag){
			handleResponse = (ProcessHandlePrepare) handleResponse.getNextHandle();
			if(handleResponse==null)
				break;
			flag = page.getRequest().getUrl().contains(handleResponse.getUniqueWebUri());
		}
		if(flag){
			ErrorStatus errorProcess = handleResponse.getErrorStatusListen();
			if(page.getStatusCode()!=200&&errorProcess!=null){
				errorProcess.downloadErrorForStatus(page, this, handleResponse);
				return;
			}
			try{
				String processName = handleResponse.getProcessName();
				beforeAnalyticalProcess(handleResponse,processName,request);
				handleResponse.analyticalPretreatmentProcess(page,this);
				afterAnalyticalProcess(handleResponse, processName, request);
			}catch(Exception e){
				this.setStatus(SeedStatus.FAIL);//设置种子的类型为内存回收
				setDataStatusForFail((String)page.getRequest().getExtra(ProcessHandle.REGNUMBER));
				logger.error("["+seed.getName()+"]页面解析出现异常在call中;错误如下：",e);
			}finally{
				//若处理类的isStorageProcess为false时不需要存储
				if(!handleResponse.isStorageProcess())
					page.getResultItems().setSkip(true);
			}
			return;
		}
		logger.error("遍历["+getProvince()+":"+seed.getName()+"]处理类链条所有对象，没有对应的处理类与该["+page.getRequest().getUrl()+"]url匹配");
	}
	/**
	 * 移除某条对应注册码的数据
	 * @param rgc
	 */
	public void setDataStatusForFail(String rgc){
		if(!SpiderUtils.checkArguForStr(rgc))
			return;
		getDataFromSeed(rgc).fail();
	}
	private void afterAnalyticalProcess(ProcessHandlePrepare handleResponse,String processName, Request request) {
		if(ProcessHandlePrepare.processName_home.equals(processName)){
		}else if(ProcessHandlePrepare.processName_down.equals(processName)){
		}else if(ProcessHandlePrepare.processName_list.equals(processName)){
		}
	}
	private void beforeAnalyticalProcess(ProcessHandlePrepare handleResponse, String processName, Request request) {
		if(ProcessHandlePrepare.processName_home.equals(processName)){
			String host = handleResponse.getHost();
			if(!this.getSite().getHeaders().containsKey("Host")&&host!=null)
				this.getSite().addHeader("Host", host);
			String referer = handleResponse.getReferer();
			if(!this.getSite().getHeaders().containsKey("Referer")&&referer!=null)
				this.getSite().addHeader("Referer", referer);
		}else if(ProcessHandlePrepare.processName_down.equals(processName)){
		}else if(ProcessHandlePrepare.processName_list.equals(processName)){
		}
	}
	@Override
	public Site getSite() {
		return this.site;
	}
	/**
	 * 设置基本信息
	 * @param data
	 * @param regNumber
	 */
	public <T> void setDataJB(GsgsRegister data,String regNumber){
		if(!SpiderUtils.checkArguForStr(regNumber)&&!SpiderUtils.checkArguForObj(data))
			return;
		getDataFromSeed(regNumber).setGsgsRegister(data);
	}
	/**
	 * 设置股东消息
	 * @param data
	 * @param regNumber
	 */
	public void setDataGD(List<GsgsShareholder> data,String regNumber){
		if(!SpiderUtils.checkArguForStr(regNumber)&&!SpiderUtils.checkArguForList(data))
			return;
		GsgsRegister bean = getDataFromSeed(regNumber).getGsgsRegister();
		if(bean==null)
			return;
		bean.setShareholders(data);
	}
	/**
	 * 设置股东详情
	 * @param data
	 * @param regNumber
	 */
	public void setDataGDXQ(List<GsgsShareholderDetail> data,String uuid,String regNumber){
		if(!SpiderUtils.checkArguForStr(regNumber)&&!SpiderUtils.checkArguForList(data))
			return;
		GsgsRegister bean = getDataFromSeed(regNumber).getGsgsRegister();
		if(bean==null)
			return;
		List<GsgsShareholder> shs = bean.getShareholders();
		if(shs==null||shs.size()<=0)
			return;
		for(GsgsShareholder sh:shs){
			if(uuid.equals(sh.getUuid()))
				sh.setContributives(data);
		}
	}
	/**
	 * 设置变更消息
	 * @param data
	 * @param regNumber
	 */
	public void setDataBG(List<GsgsChange> data,String regNumber){
		if(!SpiderUtils.checkArguForStr(regNumber)&&!SpiderUtils.checkArguForList(data))
			return;
		GsgsRegister bean = getDataFromSeed(regNumber).getGsgsRegister();
		if(bean==null)
			return;
		bean.setChanges(data);
	}
	/**
	 * 设置主要人员消息
	 * @param data
	 * @param regNumber
	 */
	public void setDataZYRY(List<GsgsMainPersonel> data,String regNumber){
		if(!SpiderUtils.checkArguForStr(regNumber)&&!SpiderUtils.checkArguForList(data))
			return;
		GsgsRegister bean = getDataFromSeed(regNumber).getGsgsRegister();
		if(bean==null)
			return;
		bean.setKeyPersons(data);
	}
	/**
	 * 设置分支机构消息
	 * @param data
	 * @param regNumber
	 */
	public void setDataFZJG(List<GsgsBranch> data,String regNumber){
		if(!SpiderUtils.checkArguForStr(regNumber)&&!SpiderUtils.checkArguForList(data))
			return;
		GsgsRegister bean = getDataFromSeed(regNumber).getGsgsRegister();
		if(bean==null)
			return;
		bean.setBranchs(data);
	}
	/**
	 * 设置企业公示—企业年报信息
	 * @param data
	 * @param regNumber
	 */
	public void setDataQYNB(QygsQynb data,String regNumber){
		if(!SpiderUtils.checkArguForStr(regNumber)&&!SpiderUtils.checkArguForObj(data))
			return;
		GsgsRegister bean = getDataFromSeed(regNumber).getGsgsRegister();
		if(bean==null)
			return;
		bean.setQygsQynb(data);
	}
	/**
	 * 从集合中获取一个数据对象，根据注册号。若该注册号没有对用的数据对象，则new一个数据对象返回，并把该数据对象添加到集合中
	 * @param key
	 */
	private BufferedGsgsRegister getDataFromSeed(String rgc) throws IllegalArgumentException{
		return seed.getGsgsRegister(rgc);
	}
	/**
	 * spider被垃圾处理器回收前,执行响应操作
	 * 1、删除该注册号的数据；是因为在获取某些数据的时候失败，而导致该注册号的数据不完整
	 * 2、根据种子的标志位，对种子进行相应的处理[内存回收0,种子删除1，更新种子库2]
	 * @param seeds
	 */
	public void destroyMyself(){
		clearErrorData();//清洗错误数据
		switch(seed.getStatus()){
			case SUCESS:
				send();
				break;
			case FAIL:
				if(seed.isFail()){
					seed.initSeedStatus();
					SBContainer.add(seed);
					logger.debug("recovery process seed and init seed flag-->name:"+seed.getName());
				}else{//种子失效，将种子丢失，并且发送成功的企业数据
					send();
					logger.debug("don't process seed because more than a failure-->name:"+seed.getName());
				}
				break;
		}
		ProxyManager.updateProxy(this.getProxy(), this.seed.getCode());//更新代理状态，既将代理重新存入数据库中
	}
	private void send() {
		SeedJsonBean bean = seed.getOrigin();
		bean.setResults(processData());
		KafkaProducer.sendData(gson.toJson(bean));
	}
	private Gson gson = new Gson();
	private List<GsgsRegister> processData() {
		List<GsgsRegister> datatmps = new ArrayList<GsgsRegister>();
		Iterator<Entry<String, BufferedGsgsRegister>> iter = seed.getEnters().entrySet().iterator();
		while(iter.hasNext())
			datatmps.add(iter.next().getValue().getGsgsRegister());
		return datatmps;
	}
	private void clearErrorData() {
		Map<String, BufferedGsgsRegister> enters = seed.getEnters();
		Set<Entry<String, BufferedGsgsRegister>> keys = enters.entrySet();
		Iterator<Entry<String, BufferedGsgsRegister>> iter = keys.iterator();
		while(iter.hasNext()){
			Entry<String, BufferedGsgsRegister> entry = iter.next();
			String rgc = entry.getKey();
			BufferedGsgsRegister value = entry.getValue();
			if(value.getStatus()==ProxyStatus.NO){
				seed.addErrorRegs(rgc);
				logger.info("databean:["+rgc+"]"+":是空数据或者状态为0;{"+value.getGsgsRegister()+"}");
				iter.remove();
			}else if(value.getStatus()==ProxyStatus.YSE){
				seed.addSucessReg(rgc);
				seed.removeError(rgc);
			}
		}
	}
	
	public BufferedSeed getSeed() {
		return seed;
	}
	public void setSeed(BufferedSeed seed) {
		this.seed = seed;
	}
	/**
	 * 获得seed的name
	 * @return
	 */
	public String getSeedNm(){
		return seed.getName();
	}
	public String getProvince() {
		return this.seed.getCode();
	}
	public void setStatus(SeedStatus status){
		seed.setStatus(status);
	}
	public SeedStatus getStatus(){
		return seed.getStatus();
	}
	public BufferedSeed getBufferedSeed() {
		return seed;
	}
	public void setBufferedSeed(BufferedSeed bufferedSeed) {
		this.seed = bufferedSeed;
	}
}
