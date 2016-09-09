package com.heetian.spider.utils;

import com.google.gson.reflect.TypeToken;
import com.heetian.spider.component.SBContainer;
/**
 * id:
 * seed:
 * url:
 * pvn:
 * results:[]
 * json串格式
 * @author tst
 *
 */
public class ReciveSeedProcess extends TopicProcessConsumerAbs {
	@Override
	public void process(String msg) {
		SeedJsonBean msgBean = AnalysisForJson.jsonToObject(msg, new TypeToken<SeedJsonBean>() {});
		if(msgBean==null){
			logger.warn("解析种子失败：原消息为"+msg);
			return;
		}
		String name = msgBean.getSeed();
		String code = msgBean.getPvn();
		BufferedSeed seed = new BufferedSeed(msgBean);
		name = name==null?"":name.replaceAll("\\s", "");
		code = code==null?"":code.replaceAll("\\s", "");
		if("".equals(name)||"".equals(code)){
			logger.warn("解析种子失败：原消息为"+msg);
			return;
		}
		seed.setOrigin(msgBean);
		SBContainer.add(seed);
		logger.debug("解析出一个种子"+msgBean.toStringSeed());
	}
}
