package com.heetian.spider.process.jiangsu;

import java.util.List;

public class GDCZXX {
		private List<SubGDXXXXRJ> listrenjiao;
		private List<SubGDXXXXGD> listValue;
		private List<SubGDXXXXSJ> listshijiao;
		private List<SubGDXXXXCZ> listchuzi;
		private int maxSize = 0;
		private <T> void count(List<T> list){
			if(list!=null&&list.size()>0){
				if(this.maxSize<list.size())
					this.maxSize = list.size();
			}
		}
		public List<SubGDXXXXRJ> getListrenjiao() {
			count(listrenjiao);
			return listrenjiao;
		}
		public void setListrenjiao(List<SubGDXXXXRJ> listrenjiao) {
			this.listrenjiao = listrenjiao;
		}
		public List<SubGDXXXXGD> getListValue() {
			count(listValue);
			return listValue;
		}
		public void setListValue(List<SubGDXXXXGD> listValue) {
			this.listValue = listValue;
		}
		public List<SubGDXXXXSJ> getListshijiao() {
			count(listshijiao);
			return listshijiao;
		}
		public void setListshijiao(List<SubGDXXXXSJ> listshijiao) {
			this.listshijiao = listshijiao;
		}
		public List<SubGDXXXXCZ> getListchuzi() {
			count(listchuzi);
			return listchuzi;
		}
		public void setListchuzi(List<SubGDXXXXCZ> listchuzi) {
			this.listchuzi = listchuzi;
		}
		public int getMaxSize() {
			return maxSize;
		}
		public void setMaxSize(int maxSize) {
			this.maxSize = maxSize;
		}
		
}
class SubGDXXXXRJ{
	//{SHOULD_CAPI:150000.000000万元人民币,SHOULD_CAPI_DATE:2009-04-30}
	private String SHOULD_CAPI;//认缴额度
	private String SHOULD_CAPI_DATE;//认缴时间
	public String getSHOULD_CAPI() {
		return SHOULD_CAPI;
	}
	public void setSHOULD_CAPI(String sHOULD_CAPI) {
		SHOULD_CAPI = sHOULD_CAPI;
	}
	public String getSHOULD_CAPI_DATE() {
		return SHOULD_CAPI_DATE;
	}
	public void setSHOULD_CAPI_DATE(String sHOULD_CAPI_DATE) {
		SHOULD_CAPI_DATE = sHOULD_CAPI_DATE;
	}
	
}
class SubGDXXXXSJ{
	//{REAL_CAPI:150000.000000万元人民币,REAL_CAPI_DATE:2009-04-30}
	private String REAL_CAPI;//实缴额度
	private String REAL_CAPI_DATE;//实缴时间
	public String getREAL_CAPI() {
		return REAL_CAPI;
	}
	public void setREAL_CAPI(String rEAL_CAPI) {
		REAL_CAPI = rEAL_CAPI;
	}
	public String getREAL_CAPI_DATE() {
		return REAL_CAPI_DATE;
	}
	public void setREAL_CAPI_DATE(String rEAL_CAPI_DATE) {
		REAL_CAPI_DATE = rEAL_CAPI_DATE;
	}
	
}
class SubGDXXXXGD{
	//{ORG:876,ID:143943,SEQ_ID:2,CORP_ORG:876,CORP_ID:123410,STOCK_TYPE:,STOCK_NAME:江苏省人民政府}
	private String ORG;
	private String ID;
	private String SEQ_ID;
	private String CORP_ORG;
	private String CORP_ID;
	private String STOCK_TYPE;
	private String STOCK_NAME;//出资人
	public String getORG() {
		return ORG;
	}
	public void setORG(String oRG) {
		ORG = oRG;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getSEQ_ID() {
		return SEQ_ID;
	}
	public void setSEQ_ID(String sEQ_ID) {
		SEQ_ID = sEQ_ID;
	}
	public String getCORP_ORG() {
		return CORP_ORG;
	}
	public void setCORP_ORG(String cORP_ORG) {
		CORP_ORG = cORP_ORG;
	}
	public String getCORP_ID() {
		return CORP_ID;
	}
	public void setCORP_ID(String cORP_ID) {
		CORP_ID = cORP_ID;
	}
	public String getSTOCK_TYPE() {
		return STOCK_TYPE;
	}
	public void setSTOCK_TYPE(String sTOCK_TYPE) {
		STOCK_TYPE = sTOCK_TYPE;
	}
	public String getSTOCK_NAME() {
		return STOCK_NAME;
	}
	public void setSTOCK_NAME(String sTOCK_NAME) {
		STOCK_NAME = sTOCK_NAME;
	}
	
}
class SubGDXXXXCZ{
	//{INVEST_TYPE_NAME:货币}
	private String INVEST_TYPE_NAME;//出资方式

	public String getINVEST_TYPE_NAME() {
		return INVEST_TYPE_NAME;
	}

	public void setINVEST_TYPE_NAME(String iNVEST_TYPE_NAME) {
		INVEST_TYPE_NAME = iNVEST_TYPE_NAME;
	}
	
}