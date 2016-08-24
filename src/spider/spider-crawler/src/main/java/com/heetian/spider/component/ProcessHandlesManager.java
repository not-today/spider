package com.heetian.spider.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.heetian.spider.observer.ErrorStatus;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.process.abstractclass.ProcessHandlePrepare;

public class ProcessHandlesManager {
	private static Logger logger = LoggerFactory.getLogger(ProcessHandlesManager.class);
	private static final BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static final Map<String,ProcessHandle>  processHandles = new HashMap<String, ProcessHandle>();
	private static final ProcessClassName ProcessIDs =  ((ProcessClassName) factory.getBean("processClassNameManager"));
	static{
		Map<String,String> provinceToClass = ProcessIDs.getProcessClassNames();
		if(provinceToClass==null){
			logger.error("请配置ProcessClassName对象，并注入对应的值");
			System.exit(0);
		}
		Set<Entry<String, String>> entry = provinceToClass.entrySet();
		for(Entry<String, String> tmp :entry){
			//对所有省份的首个处理页面类进行迭代
			ProcessHandlePrepare firstPrepare = (ProcessHandlePrepare) factory.getBean(tmp.getValue());
			//设置监听器
			ErrorStatus errorStatus = firstPrepare.getErrorStatusListen();
			if(errorStatus!=null){
				ProcessHandlePrepare nextPrepare = (ProcessHandlePrepare) firstPrepare.getNextHandle();
				while(nextPrepare!=null){
					nextPrepare.setErrorStatusListen(errorStatus);
					nextPrepare = (ProcessHandlePrepare) nextPrepare.getNextHandle();
				}
			}
			String recognized = firstPrepare.getCodeRecognized();
			if(recognized!=null&&!"".equals(recognized)){
				ProcessHandlePrepare nextPrepare = (ProcessHandlePrepare) firstPrepare.getNextHandle();
				while(nextPrepare!=null&&!nextPrepare.isDownloadCodeProcess()){
					nextPrepare = (ProcessHandlePrepare) nextPrepare.getNextHandle();
				}
				if(nextPrepare!=null){
					nextPrepare.setCodeRecognized(recognized);
				}
			}
			//设置识别程序
			processHandles.put(tmp.getKey(), firstPrepare);
		}
	}
	public static ProcessHandle getProcessHandle(String province){
		return processHandles.get(province);
	}
}
