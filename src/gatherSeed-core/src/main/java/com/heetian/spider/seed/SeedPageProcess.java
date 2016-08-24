package com.heetian.spider.seed;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.SpiderComponentCFG;
import com.heetian.spider.manager.ManagerCFG;
import com.heetian.spider.mysql.dao.MySqlDao;
import com.heetian.spider.mysql.model.Seed;
import com.heetian.spider.tools.ReadConfig;

public abstract class SeedPageProcess implements PageProcessor{
	protected static final String reg = "[（）\\(\\)、\\-——，,【】！!\\+\\.|\\\\]";
	protected static final String rep = "/";
	private String step = "1";
	private String url;
	private int threadNumer = 1;
	private String character = "UTF-8";
	private int retryTimes = 1;
	private int sleepTime = 1000;
	private int timeOut = 10000;
	private int retrySleepTime = 1000;
	public Log logger = LogFactory.getLog(PageProcessor.class);
	private Site site = new Site();
	
	public void run(){
		Spider spider = Spider.create(this).thread(threadNumer);
		spider.addRequest(httpGET(url, step));
		spider.getSite()
				.setCharset(character)
				.setRetryTimes(retryTimes)
				.setSleepTime(sleepTime)
				.setTimeOut(timeOut)
				.setRetrySleepTime(retrySleepTime)
				.setUseGzip(true);
		
		Pattern pattern = Pattern.compile("(\\{)(.*?)(:)(..*?)(\\},{0,1})");
        Matcher matcher = pattern.matcher(SpiderComponentCFG.header);
        while(matcher.find()){
        	site.addHeader(matcher.group(2), matcher.group(4));        
        }
		
		spider.start();
	} 
	public SeedPageProcess() { 
		super();
		try {
			Properties config = new Properties();
			config.load(new FileInputStream(ReadConfig.getCfgPath("spider.properties")));
			ManagerCFG.initCFG(config);
		} catch (IOException e) {
			logger.error("抛出异常：", e);
			System.exit(0);
		}
		
	}
	public SeedPageProcess(String url, String character, int threadNumer,String step) {
		this();
		if(url!=null&&!"".equals(url))
			this.url = url;
		if(character!=null&&!"".equals(character))
			this.character = character;
		if(threadNumer>0)
			this.threadNumer = threadNumer;
		if(url!=null&&!"".equals(url))
			this.step = step;
	}
	
	@Override
	public Site getSite() {
		return site;
	}
	protected Request httpGET(String url,String key){
		return request(Method.GET, url, key);
	}
	protected Request request(String method,String url,String key){
		Request request = new Request();
		request.setMethod(method);
		request.setUrl(url);
		request.putExtra("key", key);
		return request;
	}
	protected void saveSeedToDB(List<String> entNames,int sdProvince) {
		if(entNames!=null&&entNames.size()>0){
			logger.info("entNames:"+entNames);
			for(String entName:entNames){
				entName = entName.replaceAll("['\"]", "");
				if(MySqlDao.searchSeedByName(entName)==0){
					MySqlDao.insertSeed(toSeed(entName,sdProvince));
				}
			}
		}
	}
	private void saveSeedToDB(List<String> entNames) {
		if(entNames!=null&&entNames.size()>0){
			logger.info("数量:["+entNames.size()+"]"+entNames);
			for(String entName:entNames){
				if(MySqlDao.searchSeedByName(entName)==0){
					MySqlDao.insertSeed(toSeed(entName,1));
				}
			}
		}
	}//725689
	private Seed toSeed(String entName,int sdProvince){
		Seed seed = new Seed();
		seed.setName(entName);
		seed.setSdProvince(sdProvince);
		return seed;
	}
	protected void toData(List<String> position) {
		List<String> keyList = new ArrayList<String>();
		Iterator<String> tmps = position.iterator();
		while(tmps.hasNext()){
			String key = tmps.next().trim();
			if(key.contains("/")){
				tmps.remove();
				String keysubs[] = key.split("/");
				for(String keysub:keysubs){
					if(!"".equals(keysub))
						keyList.add(keysub);
				}
			}else if(key.matches("\\s*")){
				tmps.remove();
			}
		}
		position.addAll(keyList);
		saveSeedToDB(position);
	}
	
	public void write(String msg,String file){
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file,true));
			writer.write(msg);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public int getThreadNumer() {
		return threadNumer;
	}

	public void setThreadNumer(int threadNumer) {
		this.threadNumer = threadNumer;
	}

	public int getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(int retryTimes) {
		this.retryTimes = retryTimes;
	}

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public int getRetrySleepTime() {
		return retrySleepTime;
	}

	public void setRetrySleepTime(int retrySleepTime) {
		this.retrySleepTime = retrySleepTime;
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public void setSite(Site site) {
		this.site = site;
	}
}
