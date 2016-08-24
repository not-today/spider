package com.heetian.spider.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AnalysisForJson {
	private static Logger logger = LoggerFactory.getLogger(AnalysisForJson.class);
	/**
	 * json字符串转换成某对象
	 * @param jsonStr
	 * @param t
	 * @return
	 */
	public static <T> T jsonToObject(String jsonStr,TypeToken<T> token){
		T obj = null;
		if(jsonStr!=null&&!"".equals(jsonStr.trim())&&!"{}".equals(jsonStr.trim())&&!"[]".equals(jsonStr.trim())&&!"\"\"".equals(jsonStr.trim())){
			try {
				obj = new Gson().fromJson(jsonStr, token.getType());
			} catch (Exception e) {
				logger.error("转化json出错:"+jsonStr,e);
			}
		}
		return obj;
	}
}
