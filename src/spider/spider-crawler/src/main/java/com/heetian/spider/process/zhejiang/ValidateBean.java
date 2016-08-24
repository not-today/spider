package com.heetian.spider.process.zhejiang;
//{"nameResponse":{"message":"验证码输入错误","totalCount":0,"name":"330000000032256","appConInfoList":[]}}
public class ValidateBean {
	private validateBeanSub1 nameResponse;

	public validateBeanSub1 getNameResponse() {
		return nameResponse;
	}

	public void setNameResponse(validateBeanSub1 nameResponse) {
		this.nameResponse = nameResponse;
	}
	
	
}
//"message":"验证码输入错误","totalCount":0,"name":"330000000032256","appConInfoList":[]
class validateBeanSub1{
	private String message;
	private int totalCount;
	private String name;
	//private String appConInfoList;
	public int getTotalCount() {
		return totalCount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}