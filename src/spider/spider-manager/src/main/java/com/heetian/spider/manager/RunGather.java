package com.heetian.spider.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.component.SBContainer;
import com.heetian.spider.component.SpiderManagerFactory;
import com.heetian.spider.component.SpiderManagerInter;
import com.heetian.spider.tools.ReadConfig;
import com.heetian.spider.utils.BufferedSeed;
import com.heetian.spider.utils.KafkaConsumer;
import com.heetian.spider.utils.PvnCode;
import com.heetian.spider.utils.SeedJsonBean;

public class RunGather implements Runnable{
	//29个基本html的
	//湖南，河北，云南，福建，上海 
	//河南，黑龙江，广西，安徽，山西，青海，西藏，湖北 
	//新疆，四川，陕西
	//江西(改版)
	//宁夏(不稳定：代理,url分散)
	//北京
	//吉林,山东
	//辽宁
	//天津
	//重庆
	//江苏
	//海南，广东(不稳定：某个url处理)，内蒙古   主要人员有身份证
	//甘肃(不稳定：企业列表获取不到)
	//贵州
	//-------------------------------未完成
	//浙江 验证码
	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(RunGather.class);
	private static SpiderManagerInter spiderManager = SpiderManagerFactory.newInstance().createSpiderManager();
	static{
		test();//测试
		BufferedReader reader = null;
		try {
			System.setProperty("javax.net.ssl.trustStore", ReadConfig.getCfgPath("jssecacerts"));
			Properties config = new Properties();
			reader = new BufferedReader(new InputStreamReader(ReadConfig.getCfgStream("spider.properties"),"UTF-8"));
			config.load(reader);
			ManagerCFG.initCFG(config);
		} catch (IOException e) {
			logger.error("初始化配置文件参数失败，导致的原因是:",e);
			System.exit(0);
		}finally{
			try {
				if(reader!=null){
				reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		while (true) {
			try {
				while(SBContainer.isHave()&&!spiderManager.spiderIsEnough()){
					spiderManager.createSpiderAndAddPool();
				}
				spiderManager.removeSpiders();
				Thread.sleep(3000);
			} catch (Exception e) {logger.error("manager出错",e);}
		}
	}
	public static void main(String[] args) {
		new Thread(new RunGather()).start();//开启爬虫线程
		//new Thread(new AcceptSeed()).start();//开启种子接受线程
		KafkaConsumer.startKafkaConsumer();
	}
	public static void test(){
		SeedJsonBean msg = new SeedJsonBean();
		msg.setPvn(PvnCode.code_chongqing);
		msg.setSeed("科技有限公司");
		BufferedSeed seed = new BufferedSeed(msg);
		SBContainer.add(seed);
	}
}
