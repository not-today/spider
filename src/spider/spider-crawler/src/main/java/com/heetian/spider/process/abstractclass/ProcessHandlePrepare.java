package com.heetian.spider.process.abstractclass;

import java.util.Iterator;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.exception.ObserverRegister;
import com.heetian.spider.observer.AbstractListen;
import com.heetian.spider.observer.DefaultErrorStatusListen;
import com.heetian.spider.observer.ErrorStatus;
import com.heetian.spider.observer.ThemeInterface;

public abstract class ProcessHandlePrepare implements ProcessHandle,ThemeInterface{
	protected static final String httpMethodParam_get = "httpMethod=get";
	protected static final String httpMethodParam_post = "httpMethod=post";
	private ErrorStatus errorStatusListen = new DefaultErrorStatusListen();
	protected static Logger logger = LoggerFactory.getLogger(ProcessHandlePrepare.class);
	protected static final String PROTECTOL = "http://";
	protected static final String HEADS = "allHead";
	private String processName;
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * 验证码结果为null的request存储标识
	 */
	public static final String isProcessPageProcess = "CODE_IS_NULL";
	/**
	 * 控制是否为下载验证码
	 */
	protected boolean isDownloadCodeProcess = false;
	/**
	 * 控制是否要存储数据
	 */
	protected boolean isStorageProcess = false;
	/**
	 * web url 唯一标识
	 */
	private String uniqueWebUri;
	/**
	 * 步骤
	 */
	private float setup;
	/**
	 * 下一个处理类
	 */
	private ProcessHandle nextHandle;
	/**
	 * host head
	 */
	private String host;
	/**
	 * referer head
	 */
	private String referer;
	/**
	 * 为某个省份指定特别的验证码识别程序
	 */
	private String codeRecognized;
	
	public String getCodeRecognized() {
		return codeRecognized;
	}
	public void setCodeRecognized(String codeRecognized) {
		this.codeRecognized = codeRecognized;
	}
	public ProcessHandlePrepare(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
	}
	public ProcessHandlePrepare(boolean isDownloadCodeProcess,boolean isStorageProcess){
		this.isDownloadCodeProcess = isDownloadCodeProcess;
		this.isStorageProcess = isStorageProcess;
	}
	@Override
	public AbstractListen getObserver(String name) throws ObserverRegister {
		for(AbstractListen observerTmp:observers){
			if(observerTmp.getName().equals(name)){
				return observerTmp;
			}
		}
		throw new ObserverRegister(name);
	}
	@Override
	public void removeObserver(AbstractListen observer) {
		Iterator<AbstractListen> iter = observers.iterator();
		while(iter.hasNext()){
			AbstractListen tmp = iter.next();
			if(tmp.getName().equals(observer.getName())){
				iter.remove();
			}
		}
	}
	public String getHost() {
		return host;
	}
	
	public String getReferer() {
		return referer;
	}
	public void analyticalPretreatmentProcess(Page page,PageProcessor task) {
		page.setSkip(true);
		Request request = page.getRequest();
		if(!isProcessPageProcess.equals(request.getExtra(isProcessPageProcess))){
			analyticalProcess(page,task);
		}
	}
	/**
	 * 验证码为null，或空时调用
	 * @param task
	 * @return
	 */
	public Request reloadCode(Request request,Site site) {
		return null;
	}
	/**
	 * 页面处理
	 * @param page
	 * @param site
	 */
	protected abstract void analyticalProcess(Page page,PageProcessor task);
	/**
	 * 设置下一个处理器
	 * @param nextHandle
	 * @return
	 */
	public ProcessHandlePrepare setNextHandle(ProcessHandle nextHandle) {
		this.nextHandle = nextHandle;
		return (ProcessHandlePrepare) nextHandle;
	}
	public String getUniqueWebUri() {
		return uniqueWebUri;
	}
	public void setUniqueWebUri(String uniqueWebUri) {
		this.uniqueWebUri = uniqueWebUri;
	}
	@Override
	public void setObserver(AbstractListen observer) {
		boolean flag = false;
		for(AbstractListen observerTmp:observers){
			if(observerTmp.getName().equals(observer.getName())){
				flag = true;
			}
		}
		if(!flag){
			observers.add(observer);
		}
	}
	/**
	 * 构建request对象。
	 * @param url 必须完整url，即包含协议
	 * @param method  
	 * @param regNumber
	 * @param entName
	 * @param nvps
	 * @param site
	 * @return
	 */
	protected Request builderRequest(String url,String method,String regNumber,String entName,NameValuePair[] nvps){
		if(url==null){
			throw new IllegalArgumentException("构建request对象失败,url错误["+url+"]："+regNumber+"|"+entName);
		}
		Request request = new Request();
		request.setUrl(url);
		request.setMethod(method);
		if(regNumber!=null&&!"".equals(regNumber))
			request.putExtra(REGNUMBER, regNumber);
		if(entName!=null&&!"".equals(entName))
			request.putExtra(ENTNAME, entName);
		if(nvps!=null&&nvps.length>0)
			request.putExtra(NAMEVALUEPAIR, nvps);
		return request;
	}
	protected Request builderRequestPost(String url,String regNumber,String entName,NameValuePair[] nvps){
		return builderRequest(url, Method.POST, regNumber, entName, nvps);
	}
	protected Request builderRequestPost(String url,NameValuePair[] nvps){
		return builderRequestPost(url, null, null, nvps);
	}
	protected Request builderRequestGet(String url,String regNumber,String entName){
		return builderRequest(url, Method.GET, regNumber, entName, null);
	}
	protected Request builderRequestGet(String url){
		return builderRequestGet(url, null, null);
	}
	/**
	 * 构建url
	 */
	protected String builderURL(String subURL,Site site){
		if(subURL==null)
			throw new RuntimeException("构建url失败，对处理uri时，原因是uri为null");
		if(!subURL.contains(PROTECTOL))
			subURL = PROTECTOL+site.getDomain()+subURL;
		return subURL;
	}
	/**
	 * 构建股东详情request的抽象方法,因为每个省份的详情处理不同。
	 * @param regNumber
	 * @param entName
	 * @param site
	 * @param uuid 
	 * @param page
	 * @param requestString   含有url相关信息的字符串，根据不同网站进行差异化处理
	 */
	public abstract void createRequestForGdxxxx(String regNumber, String entName,Site site, String uuid, Page page, String requestString);
	
	/**
	 * 在url添加参数后缀，避免服务器返回缓存
	 * @return
	 */
	protected String urlTail(){
		return Thread.currentThread().getName().replace("-", "")+System.currentTimeMillis()+"="+Math.random();
	}
	public boolean isDownloadCodeProcess() {
		return isDownloadCodeProcess;
	}
	public void setDownloadCodeProcess(boolean isDownloadCodeProcess) {
		this.isDownloadCodeProcess = isDownloadCodeProcess;
	}
	public boolean isStorageProcess() {
		return isStorageProcess;
	}
	public void setStorageProcess(boolean isStorageProcess) {
		this.isStorageProcess = isStorageProcess;
	}
	public float getSetup() {
		return setup;
	}
	public void setSetup(float setup) {
		this.setup = setup;
	}
	public void setErrorStatusListen(ErrorStatus errorStatusListen) {
		this.errorStatusListen = errorStatusListen;
	}
	public ErrorStatus getErrorStatusListen() {
		return errorStatusListen;
	}
	public ProcessHandle getNextHandle() {
		return nextHandle;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setReferer(String referer) {
		this.referer = referer;
	}
}
