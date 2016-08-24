package com.heetian.spider.process.hainan;

import java.util.List;

public class FZJGBeanJson {
		private String totalRecords;
		private List<FZJGContentBean> list;
		private String pageNo;
		private String pageSize;
		private String url;
		private List<FZJGContentBean> selList;
		private String topPageNo;
		private String totalPages;
		private String previousPageNo;
		private String nextPageNo;
		private String bottomPageNo;
		public String getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			this.totalRecords = totalRecords;
		}
		public List<FZJGContentBean> getList() {
			return list;
		}
		public void setList(List<FZJGContentBean> list) {
			this.list = list;
		}
		public String getPageNo() {
			return pageNo;
		}
		public void setPageNo(String pageNo) {
			this.pageNo = pageNo;
		}
		public String getPageSize() {
			return pageSize;
		}
		public void setPageSize(String pageSize) {
			this.pageSize = pageSize;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public List<FZJGContentBean> getSelList() {
			return selList;
		}
		public void setSelList(List<FZJGContentBean> selList) {
			this.selList = selList;
		}
		public String getTopPageNo() {
			return topPageNo;
		}
		public void setTopPageNo(String topPageNo) {
			this.topPageNo = topPageNo;
		}
		public String getTotalPages() {
			return totalPages;
		}
		public void setTotalPages(String totalPages) {
			this.totalPages = totalPages;
		}
		public String getPreviousPageNo() {
			return previousPageNo;
		}
		public void setPreviousPageNo(String previousPageNo) {
			this.previousPageNo = previousPageNo;
		}
		public String getNextPageNo() {
			return nextPageNo;
		}
		public void setNextPageNo(String nextPageNo) {
			this.nextPageNo = nextPageNo;
		}
		public String getBottomPageNo() {
			return bottomPageNo;
		}
		public void setBottomPageNo(String bottomPageNo) {
			this.bottomPageNo = bottomPageNo;
		}
}
class FZJGContentBean{
	private String braNo;
	private String entNO;
	private String brName;
	private String regNO;
	private String prilName;
	private String opFrom;
	private String regOrg;
	public String getBraNo() {
		return braNo;
	}
	public void setBraNo(String braNo) {
		this.braNo = braNo;
	}
	public String getEntNO() {
		return entNO;
	}
	public void setEntNO(String entNO) {
		this.entNO = entNO;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getRegNO() {
		return regNO;
	}
	public void setRegNO(String regNO) {
		this.regNO = regNO;
	}
	public String getPrilName() {
		return prilName;
	}
	public void setPrilName(String prilName) {
		this.prilName = prilName;
	}
	public String getOpFrom() {
		return opFrom;
	}
	public void setOpFrom(String opFrom) {
		this.opFrom = opFrom;
	}
	public String getRegOrg() {
		return regOrg;
	}
	public void setRegOrg(String regOrg) {
		this.regOrg = regOrg;
	}
}