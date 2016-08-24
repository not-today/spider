package com.heetian.spider.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.heetian.dataplatform.tools.config.Config;

public class DataToJsonUtilsTest {
	//private static Logger logger = LoggerFactory.getLogger(DataToJsonUtils.class);
	static {
		Config.readConfig();
	}
	public static void test() {
		//x=钢铁
		//y=500000:0
		//z=x\ny,y,y...
		//格式:     z\n\rz\n\r.....   
		//需求：                提出格式中的每个z数据
		//sendKafka("啊:100000");
		//seed\ncode,code,...\r\nseed\ncode,code,...\r\n
		String msg = "钢铁\n500000:0,430000:0\r\n科技\n500000:0,430000:0";
		String pa = "[\u4e00-\u9fa5_a-zA-Z0-9]+\\n(\\d{6}:[0123456789],{0,1})+";
		//String pa = "[\u4e00-\u9fa5_a-zA-Z0-9]+\\n(\\d{6}:\\d,)+\\d{6}:\\d";
		//String pa = "([\u4e00-\u9fa5_a-zA-Z0-9]+?\\n\\d{6}:[0123]{1}(,{1}\\d{6}:[0123]{1})*\\r\\n)+";
		Pattern pattern = Pattern.compile(pa);
		Matcher matcher = pattern.matcher(msg);
		while(matcher.find()){
			String m = matcher.group();
			String ms[] = m.split("\n");
			String mss[] = ms[1].split(",");
			for(String msss:mss){
				String mssss[] = msss.split(":");
				System.out.println(mssss);
			}
			System.out.println(m);
		}
	}
}
