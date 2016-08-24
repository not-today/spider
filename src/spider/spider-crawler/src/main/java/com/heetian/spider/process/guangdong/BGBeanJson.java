package com.heetian.spider.process.guangdong;

import java.util.List;

public class BGBeanJson {
		private String totalRecords;
		private List<BGContentBean> list;
		private String pageNo;
		private String pageSize;
		private String url;
		private List<BGContentBean> selList;
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
		public List<BGContentBean> getList() {
			return list;
		}
		public void setList(List<BGContentBean> list) {
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
		public List<BGContentBean> getSelList() {
			return selList;
		}
		public void setSelList(List<BGContentBean> selList) {
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
class BGContentBean{
	private String entChaNo;
	private String entNo;
	private String altItem;
	private String altTable;
	private String altFiled;
	private String altFiledName;
	private String oldHistNo;
	private String newHistNo;
	private String altBe;
	private String altAf;
	private String altDate;
	private String chaType;
	public String getEntChaNo() {
		return entChaNo;
	}
	public void setEntChaNo(String entChaNo) {
		this.entChaNo = entChaNo;
	}
	public String getEntNo() {
		return entNo;
	}
	public void setEntNo(String entNo) {
		this.entNo = entNo;
	}
	public String getAltItem() {
		return altItem;
	}
	public void setAltItem(String altItem) {
		this.altItem = altItem;
	}
	public String getAltTable() {
		return altTable;
	}
	public void setAltTable(String altTable) {
		this.altTable = altTable;
	}
	public String getAltFiled() {
		return altFiled;
	}
	public void setAltFiled(String altFiled) {
		this.altFiled = altFiled;
	}
	public String getAltFiledName() {
		return altFiledName;
	}
	public void setAltFiledName(String altFiledName) {
		this.altFiledName = altFiledName;
	}
	public String getOldHistNo() {
		return oldHistNo;
	}
	public void setOldHistNo(String oldHistNo) {
		this.oldHistNo = oldHistNo;
	}
	public String getNewHistNo() {
		return newHistNo;
	}
	public void setNewHistNo(String newHistNo) {
		this.newHistNo = newHistNo;
	}
	public String getAltBe() {
		return altBe;
	}
	public void setAltBe(String altBe) {
		this.altBe = altBe;
	}
	public String getAltAf() {
		return altAf;
	}
	public void setAltAf(String altAf) {
		this.altAf = altAf;
	}
	public String getAltDate() {
		return altDate;
	}
	public void setAltDate(String altDate) {
		this.altDate = altDate;
	}
	public String getChaType() {
		return chaType;
	}
	public void setChaType(String chaType) {
		this.chaType = chaType;
	}
}