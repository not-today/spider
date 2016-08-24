package com.heetian.spider.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.heetian.spider.component.SBContainer;
import com.heetian.spider.dbcp.bean.Seed;
/**
 * 解析kafka接收的数据，格式：seed\ncode:0,code:0,...\r\nseed\ncode:0,code:0,...\r\n
 * @author tst
 *
 */
public class ReciveSeedProcess extends TopicProcessConsumerAbs {
	@Override
	public void process(String msg) {
		Matcher matcher = pattern.matcher(msg);
		while(matcher.find()){
			List<String> tmps = new ArrayList<String>();
			String resultComplexy = matcher.group();
			String seedComp[] = resultComplexy.split("\n");
			String codeComplexy[] = seedComp[1].split(",");
			for(String codeComplexys:codeComplexy){
				String codes[] = codeComplexys.split(":");
				BufferedSeed buffSeed = new BufferedSeed(new Seed());
				buffSeed.setName(seedComp[0]);
				buffSeed.setCode(codes[0]);
				SBContainer.add(buffSeed);
				String tmp = seedComp[0]+"$"+codes[0]+"$"+codes[1];
				tmps.add(tmp);
			}
			logger.info("从该条["+msg.replace("\r\n", "@").replace("\n", "$")+"]种子中共解析到种子:"+tmps);
		}
	}
}
