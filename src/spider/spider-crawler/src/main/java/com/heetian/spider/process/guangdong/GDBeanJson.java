package com.heetian.spider.process.guangdong;

import java.util.List;

public class GDBeanJson {
		private String totalRecords;
		private List<GDContentBean> list;
		private String pageNo;
		private String pageSize;
		private String url;
		private List<GDContentBean> selList;
		private String topPageNo;
		private String totalPages;
		private String previousPageNo;
		private String nextPageNo;
		private String bottomPageNo;
		private String obj;
		public String getTotalRecords() {
			return totalRecords;
		}
		public void setTotalRecords(String totalRecords) {
			this.totalRecords = totalRecords;
		}
		public List<GDContentBean> getList() {
			return list;
		}
		public void setList(List<GDContentBean> list) {
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
		public List<GDContentBean> getSelList() {
			return selList;
		}
		public void setSelList(List<GDContentBean> selList) {
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
		public String getObj() {
			return obj;
		}
		public void setObj(String obj) {
			this.obj = obj;
		}
}
class GDContentBean{
	private String invNo;
	private String entNo;
	private String invType;
	private String inv;
	private String certName;
	private String certNo;
	private String subConAm;
	private String acConAm;
	private String acConDate;
	private String conForm;
	private String acConForm;
	private String respForm;
	private String exePerson;
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public String getEntNo() {
		return entNo;
	}
	public void setEntNo(String entNo) {
		this.entNo = entNo;
	}
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	public String getInv() {
		return inv;
	}
	public void setInv(String inv) {
		this.inv = inv;
	}
	public String getCertName() {
		return certName;
	}
	public void setCertName(String certName) {
		this.certName = certName;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getSubConAm() {
		return subConAm;
	}
	public void setSubConAm(String subConAm) {
		this.subConAm = subConAm;
	}
	public String getAcConAm() {
		return acConAm;
	}
	public void setAcConAm(String acConAm) {
		this.acConAm = acConAm;
	}
	public String getAcConDate() {
		return acConDate;
	}
	public void setAcConDate(String acConDate) {
		this.acConDate = acConDate;
	}
	public String getConForm() {
		return conForm;
	}
	public void setConForm(String conForm) {
		this.conForm = conForm;
	}
	public String getAcConForm() {
		return acConForm;
	}
	public void setAcConForm(String acConForm) {
		this.acConForm = acConForm;
	}
	public String getRespForm() {
		return respForm;
	}
	public void setRespForm(String respForm) {
		this.respForm = respForm;
	}
	public String getExePerson() {
		return exePerson;
	}
	public void setExePerson(String exePerson) {
		this.exePerson = exePerson;
	}
}

/**
 {"totalRecords":15,

		"list":[
		{"invNo":"ad580827-0151-1000-e000-55500a066465",
		"entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5",
		"invType":"企业法人",
		"inv":"佳昭控股有限公司",
		"certName":"营业执照",
		"certNo":"58945121-000-08-11-6",
		"subConAm":2875.0000,
		"acConAm":1150.0000,
		"conForm":"货币出资",
		"acConForm":"货币出资",
		"exePerson":"0"}
		,{"invNo":"ad580827-0151-1000-e000-55560a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"鼎晖绚彩（香港）有限公司","certName":"营业执照","certNo":"38782028-000-12-11-1","subConAm":5750.0000,"acConAm":2300.0000,"conForm":"货币出资","acConForm":"货币出资","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-55570a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"A股公众投资者","certName":"其他","certNo":"******","subConAm":170905.2667,"acConAm":68632.3389,"acConDate":"Sep 17, 2013 12:00:00 AM","conForm":"股权","acConForm":"股权","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-55580a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"宁波美晟股权投资合伙企业","certName":"营业执照","certNo":"330200000081387","subConAm":7500.0000,"acConAm":3000.0000,"conForm":"货币出资","acConForm":"货币出资","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-55590a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"美的控股有限公司","certName":"营业执照","certNo":"440681000220923","subConAm":149625.0000,"acConAm":59850.0000,"acConDate":"Mar 4, 2013 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-555a0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"融睿股权投资(珠海)合伙企业(有限合伙)","certName":"营业执照","certNo":"440400000311856","subConAm":30450.0000,"acConAm":12180.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资","respForm":"","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-555b0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"天津鼎晖嘉泰股权投资合伙企业(有限合伙)","certName":"营业执照","certNo":"120116000065385","subConAm":7800.0000,"acConAm":3120.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资","respForm":"","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-555c0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"鼎晖美泰（香港）有限公司","certName":"营业执照","certNo":"52423339-000-06-11-9","subConAm":6000.0000,"acConAm":2400.0000,"conForm":"货币出资","acConForm":"货币出资","respForm":"","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-55280a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"袁利群","certName":" ","certNo":" ","subConAm":6050.0000,"acConAm":2400.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资","respForm":""},
		{"invNo":"ad580827-0151-1000-e000-55290a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"栗建伟","certName":" ","certNo":" ","subConAm":5040.0000,"acConAm":2000.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资","respForm":""},
		{"invNo":"ad580827-0151-1000-e000-552a0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"黄晓明","certName":" ","certNo":" ","subConAm":5140.0066,"acConAm":2000.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资"},
		{"invNo":"ad580827-0151-1000-e000-552b0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"郑伟康","certName":" ","certNo":" ","subConAm":2500.0000,"acConAm":1000.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资"},
		{"invNo":"ad580827-0151-1000-e000-552d0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"黄健","certName":" ","certNo":" ","subConAm":7500.0000,"acConAm":3000.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资"},
		{"invNo":"ad580827-0151-1000-e000-552e0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"蔡其武","certName":" ","certNo":" ","subConAm":5015.8200,"acConAm":2000.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资"},
		{"invNo":"ad580827-0151-1000-e000-55300a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"方洪波","certName":" ","certNo":" ","subConAm":9132.6995,"acConAm":3600.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资"}]

		,
		"pageNo":2,
		"pageSize":5,
		"url":"GSpublicity/invInfoPage.html",

		"selList":[
		{"invNo":"ad580827-0151-1000-e000-555a0a066465",
		"entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5",
		"invType":"企业法人",
		"inv":"融睿股权投资(珠海)合伙企业(有限合伙)",
		"certName":"营业执照",
		"certNo":"440400000311856",
		"subConAm":30450.0000,
		"acConAm":12180.0000,
		"acConDate":"Nov 28, 2007 12:00:00 AM",
		"conForm":"货币出资",
		"acConForm":"货币出资",
		"respForm":"",
		"exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-555b0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"天津鼎晖嘉泰股权投资合伙企业(有限合伙)","certName":"营业执照","certNo":"120116000065385","subConAm":7800.0000,"acConAm":3120.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资","respForm":"","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-555c0a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"企业法人","inv":"鼎晖美泰（香港）有限公司","certName":"营业执照","certNo":"52423339-000-06-11-9","subConAm":6000.0000,"acConAm":2400.0000,"conForm":"货币出资","acConForm":"货币出资","respForm":"","exePerson":"0"},
		{"invNo":"ad580827-0151-1000-e000-55280a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"袁利群","certName":" ","certNo":" ","subConAm":6050.0000,"acConAm":2400.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资","respForm":""},
		{"invNo":"ad580827-0151-1000-e000-55290a066465","entNo":"a620ecfa-3397-4e60-8327-8599e514e6c5","invType":"自然人股东","inv":"栗建伟","certName":" ","certNo":" ","subConAm":5040.0000,"acConAm":2000.0000,"acConDate":"Nov 28, 2007 12:00:00 AM","conForm":"货币出资","acConForm":"货币出资","respForm":""}],

		"topPageNo":1,
		"totalPages":3,
		"previousPageNo":1,
		"nextPageNo":3,
		"bottomPageNo":3,
		"obj":"1"}
 */

