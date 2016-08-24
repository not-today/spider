package com.heetian.spider.process.jiangsu;
class InnerBean{
	private String mark;

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "InnerBean [mark=" + mark + "]";
	}
}
public class CodeBean {
	private InnerBean bean;
	private String dateFormat;
	private String success;

	public InnerBean getBean() {
		return bean;
	}

	public void setBean(InnerBean bean) {
		this.bean = bean;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public boolean isSuccess(){
		if(this.bean!=null&&!"0".equals(this.bean.getMark()))
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "CodeBean [bean=" + bean + ", dateFormat=" + dateFormat
				+ ", success=" + success + "]";
	}
}
