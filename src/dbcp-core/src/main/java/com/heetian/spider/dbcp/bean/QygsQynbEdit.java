package com.heetian.spider.dbcp.bean;
/**
 * 企业年报_修改记录
 * @author tst
 *
 */
public class QygsQynbEdit {
	/**
	 * 修改事项
	 */
	private String editContent;
	/**
	 * 修改前
	 */
	private String editBefore;
	/**
	 * 修改后
	 */
	private String editAfter;
	/**
	 * 修改日期
	 */
	private String editDate;
	/**
	 * 详情关联字段
	 */
	private String uuid;
	public String getEditContent() {
		return editContent;
	}
	public void setEditContent(String editContent) {
		this.editContent = editContent;
	}
	public String getEditBefore() {
		return editBefore;
	}
	public void setEditBefore(String editBefore) {
		this.editBefore = editBefore;
	}
	public String getEditAfter() {
		return editAfter;
	}
	public void setEditAfter(String editAfter) {
		this.editAfter = editAfter;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
