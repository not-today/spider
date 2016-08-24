package com.heetian.spider.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.UUID;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.processor.PageProcessor;

import com.heetian.spider.component.TSTPageProcessor;

public class TSTUtils {
	private static Logger logger = LoggerFactory.getLogger(TSTUtils.class);
	public static String toMD5(String content){
	 MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = content.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
	}
	public static String encoder(String str,String charset){
		try {
			return URLEncoder.encode(str,charset);
		} catch (UnsupportedEncodingException e) {
			logger.warn("对字符串编码出错,程序将采用默认的UTF-8编码进行编码",e);
			try {
				return URLEncoder.encode(str,"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				return null;
			}
		}
	}
	/**
	 * 产生一个3整除的随即数
	 * @return
	 */
	public static long randomNum() {
		long tmp = Math.round(Math.random()*10000);
		while(tmp%3!=0){
			tmp=Math.round(Math.random()*10000);
		}
		return tmp;
	}
	public static  boolean checkIsExitForEnter(PageProcessor task, String regNumber,String entName) {
		logger.info("obtain seed["+regNumber+"]"+entName);
		TSTPageProcessor pro = (TSTPageProcessor) task;
		if(regNumber==null||"".equals(regNumber)){
			logger.error("种子["+pro.getSeedNm()+"]注册码获取为空，请查找具体原因["+entName+":"+regNumber+"]");
			return false;
		}
		if(pro.getBufferedSeed().isContainSucessReg(regNumber)){//判断内存中是否已经存在次企业。这是由于种子有抓取失败过的经历。并且有部分数据存入数据库的情况
			logger.info("种子["+pro.getSeedNm()+"]数据库已存在此企业，此为内存中有该企业记录["+entName+":"+regNumber+"]");
			return false;
		}
		return true;
	}
	public static String bufferedURL(String url, String httpmethodparamGet, NameValuePair[] nvps){
		if(nvps==null){
			if(url.contains("?"))
				url = url+"&"+httpmethodparamGet;
			else
				url = url +"?"+httpmethodparamGet;
		}else{
			for(NameValuePair nvp:nvps){
				if(url.contains("?"))
					url = url+"&"+nvp.getName()+"="+nvp.getValue();
				else
					url = url +"?"+nvp.getName()+"="+nvp.getValue();
			}
			if(url.contains("?"))
				url = url+"&"+httpmethodparamGet;
			else
				url = url +"?"+httpmethodparamGet;
		}
		return url;
	}
	public static String uuid(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "").toUpperCase();
	}
	public static String scriptEval(String script,String methodName) throws ScriptException, NoSuchMethodException {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByExtension("js");
		engine.eval(script);
		Invocable inv = (Invocable) engine;
		String res = (String) inv.invokeFunction(methodName, "Scripting");
		return res;
	}
}
