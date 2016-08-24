package com.heetian.spider.process.hainan;

import java.util.List;

public class ZYRYBeanJson {
		private String totalRecords;
		private List<ZYRYContentBean> list;
		private String pageNo;
		private String pageSize;
		private String url;
		private List<ZYRYContentBean> selList;
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
		public List<ZYRYContentBean> getList() {
			return list;
		}
		public void setList(List<ZYRYContentBean> list) {
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
		public List<ZYRYContentBean> getSelList() {
			return selList;
		}
		public void setSelList(List<ZYRYContentBean> selList) {
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
class ZYRYContentBean{
	private String vipNO;
	private String entNO;
	private String name;
	private String position;
	private String cerType;
	private String cerNO;
	public String getVipNO() {
		return vipNO;
	}
	public void setVipNO(String vipNO) {
		this.vipNO = vipNO;
	}
	public String getEntNO() {
		return entNO;
	}
	public void setEntNO(String entNO) {
		this.entNO = entNO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCerType() {
		return cerType;
	}
	public void setCerType(String cerType) {
		this.cerType = cerType;
	}
	public String getCerNO() {
		return cerNO;
	}
	public void setCerNO(String cerNO) {
		this.cerNO = cerNO;
	}
}