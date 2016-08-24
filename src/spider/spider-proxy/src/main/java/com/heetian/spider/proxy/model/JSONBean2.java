package com.heetian.spider.proxy.model;


public class JSONBean2 {
private String code;
private String msg;
private ProxyBean2 data= new ProxyBean2();

/**  
 * 返回 msg 的值   
 * @return msg  
 */
public String getMsg() {
	return msg;
}
/**  
 * 设置 msg 的值  
 * @param msg
 */
public void setMsg(String msg) {
	this.msg = msg;
}
/**  
 * 返回 code 的值   
 * @return code  
 */
public String getCode() {
	return code;
}
/**  
 * 设置 code 的值  
 * @param code
 */
public void setCode(String code) {
	this.code = code;
}
/**  
 * 返回 data 的值   
 * @return data  
 */
public ProxyBean2 getData() {
	return data;
}
/**  
 * 设置 data 的值  
 * @param data
 */
public void setData(ProxyBean2 data) {
	this.data = data;
}



}
