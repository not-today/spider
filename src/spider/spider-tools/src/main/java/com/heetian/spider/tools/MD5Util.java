package com.heetian.spider.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * <p>  
 * Title: MD5Util.java 
 * </p>  
 * <p>  
 * Description: 计算指定字符串的MD5值
 * </p> 
 * @author admin    
 * @version 1.0  
 * @created 2015年3月9日 上午9:12:25 
 */
public class MD5Util {
	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

	/**  
	 * 描述
	 * @param s
	 * @return  
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			String result = new String(str).toLowerCase();
			logger.info("{}的MD5计算结果为{}", s, result);
			return result;
		} catch (Exception e) {
			logger.error("计算MD5时执出了异常：",e);
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			System.out.println(encode("钢铁".getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
    /** 
     * @param bytes 
     * @return 
     */  
    public static byte[] decode(final byte[] bytes) {  
        return Base64.decodeBase64(bytes);  
    }  
  
    /** 
     * 二进制数据编码为BASE64字符串 
     * 
     * @param bytes 
     * @return 
     * @throws Exception 
     */  
    public static String encode(final byte[] bytes) {  
        return new String(Base64.encodeBase64(bytes));  
    }  
    public static String encode(String content,String charset) {  
    	try {
			return encode(content.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			logger.warn("对字符串编码出错,程序将采用默认的UTF-8编码进行编码",e);
			try {
				return encode(content.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				return null;
			}
		}
    }  
    
}