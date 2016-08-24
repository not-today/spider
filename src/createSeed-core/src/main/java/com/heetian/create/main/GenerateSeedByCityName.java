package com.heetian.create.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.heetian.create.utils.Utils;
import com.heetian.spider.dbcp.dao.inter.RegisterDaoInter;
import com.heetian.spider.dbcp.utils.DaoFactory;

public class GenerateSeedByCityName {
	public static String province = "-1";
	private  static RegisterDaoInter inter = DaoFactory.instanceReg("com.heetian.spider.dbcp.dao.imple.RegisterDaoImple");
	// 福建省 ，省市县区名称 
	private static String[] CITYS = null;
	private static String[] CHARACTERS = {};
	static{
		Properties p = new Properties();
		InputStream in =  null;
		try {
			in = GenerateSeedByCityName.class.getClassLoader().getResourceAsStream("font.properties");
			p.load(in);
			province = p.getProperty("province");
			String results[] = p.getProperty("CHARACTERS").split(";");
			CHARACTERS = new String[results.length];
			for(int x=0;x<results.length;x++){
				CHARACTERS[x] = Utils.uniToChar(results[x]);
			}
			CITYS = inter.loadRegins(province);
			System.out.println(Arrays.toString(CHARACTERS));
			System.out.println(Arrays.toString(CITYS));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// 中国常用汉字
	public static void main(String[] args) {
		List<String> seeds = new ArrayList<String>();
		for(int i = 0; i < CITYS.length; i++){
			for(int j = 0; j < CHARACTERS.length; j++){
				seeds.add(CITYS[i]+CHARACTERS[j]);
				if(seeds.size() < 1000) 
					continue;
				inter.batchInsertSeedNm1(seeds);
				seeds.clear();
				System.out.println("一次一千条数据插入成功");
			}
		}
		if(seeds.size()>0)
			inter.batchInsertSeedNm1(seeds);
	}
}
