package com.heetian.spider.component;

public class SpiderManagerFactory {
	private static final SpiderManagerFactory instance = new SpiderManagerFactory();
	public SpiderManagerFactory(){};
	public static SpiderManagerFactory newInstance(){
		return instance;
	}
	public SpiderManagerInter createSpiderManager(){
		return new SpiderManager();
	}
	
}
