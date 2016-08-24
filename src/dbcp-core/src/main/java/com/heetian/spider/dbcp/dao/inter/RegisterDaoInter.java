package com.heetian.spider.dbcp.dao.inter;

import java.util.List;
import java.util.Map;

public interface RegisterDaoInter {
	/**
	 * 加载区域名称
	 * @param reginCode
	 * @return
	 */
	public String[] loadRegins(String reginCode);
	/**
	 * 加载所有区域码
	 * @return
	 */
	public List<String> getAllRegionCode();
	/**
	 * 加载所有注册号，根据一个区域码
	 * @param regionCode
	 * @return
	 */
	public List<String> getRegListByRegionCode(String regionCode);
	/**
	 * 插入注册号
	 * @param regList
	 * @return
	 */
	public boolean batchInsertSeedRg(List<String> regList);
	/**
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<String> queryCompanyName(int start,int limit);
	/**
	 * 
	 * @return
	 */
	public List<Map<String,String>> queryAllRegionList();
	/**
	 * 
	 * @param seedList
	 * @return
	 */
	public boolean batchInsertSeedNm(List<Map<String,String>> seedList);
	/**
	 * 
	 * @param seedList
	 * @return
	 */
	public boolean batchInsertSeedNm1(List<String> seedList);
}
