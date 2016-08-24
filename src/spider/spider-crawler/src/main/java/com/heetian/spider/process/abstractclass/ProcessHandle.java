package com.heetian.spider.process.abstractclass;

/**
 * 页面处理类统一接口
 * @author tst
 *
 */
public interface ProcessHandle{
	public static final String processName_home = "homepage";
	public static final String processName_down = "downpage";
	public static final String processName_list = "listpage";
	public static final String processName_containJbxx = "containJbxxpage";
	public static final String uuid_key = "uuid_key";
	/**
	 * request的extral的key,保存验证码内容
	 */
	public static final String pictureContent = "pictureContent";
	/**
	 * request的extral的key，保存注册号
	 */
	public static final String REGNUMBER = "regNumber";
	/**
	 * request的extral的key，保存企业名
	 */
	public static final String ENTNAME = "entName";
	/**
	 * request的extral的key，保存post请求参数
	 */
	public static final String NAMEVALUEPAIR = "nameValuePair";
	public String getProcessName();
}
