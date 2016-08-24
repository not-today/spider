package com.heetian.spider.utils;

import java.util.HashMap;
import java.util.Map;


/**
 * 各省份区域代码
 * @author tst
 *PvnCode.code_zhejiang
 */
public class PvnCode {
	/**
	 * 总局
	 */
	public static final String code_zongju = "100000";
	/**
	 * 安徽
	 */
	public static final String code_anhui = "340000";
	/**
	 * 重庆
	 */
	public static final String code_chongqing = "500000";
	/**
	 * 福建
	 */
	public static final String code_fujian = "350000";
	/**
	 * 甘肃
	 */
	public static final String code_gansu = "620000";
	/**
	 * 广东
	 */
	public static final String code_guangdong = "440000";
	/**
	 * 广西
	 */
	public static final String code_guangxi = "450000";
	/**
	 * 贵州
	 */
	public static final String code_guizhou = "520000";
	/**
	 * 海南
	 */
	public static final String code_hainan = "460000";
	/**
	 * 河北
	 */
	public static final String code_hebei = "130000";
	/**
	 * 黑龙江
	 */
	public static final String code_heilongjiang = "230000";
	/**
	 * 河南
	 */
	public static final String code_henan = "410000";
	/**
	 * 湖北
	 */
	public static final String code_hubei = "420000";
	/**
	 * 湖南
	 */
	public static final String code_hunan = "430000";
	/**
	 * 江苏
	 */
	public static final String code_jiangsu = "320000";
	/**
	 * 江西
	 */
	public static final String code_jiangxi = "360000";
	/**
	 * 吉林
	 */
	public static final String code_jilin = "220000";
	/**
	 * 辽宁
	 */
	public static final String code_liaoning = "210000";
	/**
	 * 内蒙古
	 */
	public static final String code_neimenggu = "150000";
	/**
	 * 宁夏
	 */
	public static final String code_ningxia = "640000";
	/**
	 * 北京
	 */
	public static final String code_beijing = "110000";
	/**
	 * 青海
	 */
	public static final String code_qinghai = "630000";
	/**
	 * 山东
	 */
	public static final String code_shandong = "370000";
	/**
	 * 上海
	 */
	public static final String code_shanghai = "310000";
	/**
	 * 山西
	 */
	public static final String code_shanxi = "140000";
	/**
	 * 陕西
	 */
	public static final String code_shaaxi = "610000";
	/**
	 * 四川
	 */
	public static final String code_sichuan = "510000";
	/**
	 * 天津
	 */
	public static final String code_tianjin = "120000";
	/**
	 * 新疆
	 */
	public static final String code_xinjiang = "650000";
	/**
	 * 西藏
	 */
	public static final String code_xizang = "540000";
	/**
	 * 云南
	 */
	public static final String code_yunnan = "530000";
	/**
	 * 浙江
	 */
	public static final String code_zhejiang = "330000";
	private static final Map<String, String> homeURLS = new HashMap<String,String>();
	static {
		homeURLS.put(code_zongju, "http://gsxt.saic.gov.cn/zjgs/?xx=xx");
		homeURLS.put(code_anhui, "http://www.ahcredit.gov.cn/search.jspx");
		homeURLS.put(code_chongqing, "http://gsxt.cqgs.gov.cn/?xx=xx");
		homeURLS.put(code_fujian, "http://wsgs.fjaic.gov.cn/creditpub/home");
		homeURLS.put(code_gansu, "http://xygs.gsaic.gov.cn/gsxygs/main.jsp");
		homeURLS.put(code_guangdong, "http://gsxt.gdgs.gov.cn/aiccips/?xxxx");
		homeURLS.put(code_guangxi, "http://gxqyxygs.gov.cn/search.jspx");
		homeURLS.put(code_guizhou, "http://gsxt.gzgs.gov.cn/?xx=xx");//http://gsxt.gzgs.gov.cn/?xxxx
		homeURLS.put(code_hainan, "http://aic.hainan.gov.cn:1888/aiccips?xx=xx");
		homeURLS.put(code_hebei, "http://www.hebscztxyxx.gov.cn/notice/?xx=xx");
		homeURLS.put(code_heilongjiang, "http://gsxt.hljaic.gov.cn/search.jspx");
		homeURLS.put(code_henan, "http://222.143.24.157/search.jspx");
		homeURLS.put(code_hubei, "http://xyjg.egs.gov.cn/ECPS_HB/search.jspx");
		homeURLS.put(code_hunan, "http://gsxt.hnaic.gov.cn/notice/?aa=aa");
		homeURLS.put(code_jiangsu, "http://www.jsgsj.gov.cn:58888/province?xxxx");
		homeURLS.put(code_jiangxi, "http://gsxt.jxaic.gov.cn/?xx=xx");
		homeURLS.put(code_jilin, "http://211.141.74.198:8081/aiccips/?xxx");
		homeURLS.put(code_liaoning, "http://gsxt.lngs.gov.cn/saicpub/entPublicitySC/entPublicityDC/entPublicity/search/searchmain.jsp");
		homeURLS.put(code_neimenggu, "http://www.nmgs.gov.cn:7001/aiccips/?xx=xx");
		homeURLS.put(code_ningxia, "http://gsxt.ngsh.gov.cn/ECPS/index.jsp");
		homeURLS.put(code_beijing, "http://qyxy.baic.gov.cn/beijing");
		homeURLS.put(code_qinghai, "http://218.95.241.36/search.jspx");
		homeURLS.put(code_shandong, "http://218.57.139.24/?xxx");
		homeURLS.put(code_shanghai, "https://www.sgs.gov.cn/notice/home");
		homeURLS.put(code_shanxi, "http://218.26.1.108/search.jspx");
		homeURLS.put(code_shaaxi, "http://xygs.snaic.gov.cn/?xx=xx");
		homeURLS.put(code_sichuan, "http://gsxt.scaic.gov.cn/?xxx");
		homeURLS.put(code_tianjin, "http://tjcredit.gov.cn/platform/saic/index.ftl");
		homeURLS.put(code_xinjiang, "http://gsxt.xjaic.gov.cn:7001/?xxxx");
		homeURLS.put(code_xizang, "http://gsxt.xzaic.gov.cn/search.jspx");
		homeURLS.put(code_yunnan, "http://gsxt.ynaic.gov.cn/notice/?xx=xx");
		homeURLS.put(code_zhejiang, "http://gsxt.zjaic.gov.cn/zhejiang.jsp");
	}
	public static String get(String code)throws NullPointerException{
		String url = homeURLS.get(code);
		if(url==null)
			throw new NullPointerException("省份区域码可能存在问题，导致寻找主页url失败："+code);
		return url;
	}
}
