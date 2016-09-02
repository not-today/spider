package com.heetian.spider.component;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.utils.BufferedSeed;
/**
 * 存放种子的集合,由于kafka线程和创建种子线程一起操作，因此用vector
 * @author tst
 *
 */
public class SBContainer {
	private static Logger logger = LoggerFactory.getLogger(SBContainer.class);
	private static final Vector<BufferedSeed> seeds = new Vector<BufferedSeed>();
	public static BufferedSeed remove(){
		if(seeds.size()>0){
			BufferedSeed seed = seeds.remove(0);
			logger.debug("移除一个种子还剩余："+seeds.size());
			return seed;
		}
		throw new NullPointerException("容器中没有种子");
	}
	public static void add(BufferedSeed seed){
		seeds.add(seed);
	}
	public static boolean isHave(){
		if(seeds.size()>0)
			return true;
		return false;
	}
}
