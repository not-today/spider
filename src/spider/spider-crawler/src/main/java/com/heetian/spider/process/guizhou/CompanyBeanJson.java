package com.heetian.spider.process.guizhou;

import java.util.List;

public class CompanyBeanJson {
	private String successed;
	private String refresh;
	private int count;
	private List<DataBean> data;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSuccessed() {
		return successed;
	}
	public void setSuccessed(String successed) {
		this.successed = successed;
	}
	public String getRefresh() {
		return refresh;
	}
	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<DataBean> getData() {
		return data;
	}
	public void setData(List<DataBean> data) {
		this.data = data;
	}
}
class DataBean{
/*	nbxh:"265089ffefe590e3ad6a01fd8818fc7d92c579bf6e427202a658d52a7e33b6e3";
	ztlx:"1";
	fddbr:"王开勇";
	mclx:"2";
	clrq:"1989年12月29日";
	qymc:"盘县钢铁厂";
	zch:"520202000097714";
	djjg:"520222";
	djjgmc:"盘县市场监督管理局";
	qylx:"3100";*/
	private String nbxh ;
	private String ztlx ;
	private String fddbr ;
	private String mclx ;
	private String clrq ;
	private String qymc ;
	private String zch ;
	private String djjg ;
	private String 	djjgmc ;
	private String 	qylx ;
	public String getNbxh() {
		return nbxh;
	}
	public void setNbxh(String nbxh) {
		this.nbxh = nbxh;
	}
	public String getZtlx() {
		return ztlx;
	}
	public void setZtlx(String ztlx) {
		this.ztlx = ztlx;
	}
	public String getFddbr() {
		return fddbr;
	}
	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getMclx() {
		return mclx;
	}
	public void setMclx(String mclx) {
		this.mclx = mclx;
	}
	public String getClrq() {
		return clrq;
	}
	public void setClrq(String clrq) {
		this.clrq = clrq;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getZch() {
		return zch;
	}
	public void setZch(String zch) {
		this.zch = zch;
	}
	public String getDjjg() {
		return djjg;
	}
	public void setDjjg(String djjg) {
		this.djjg = djjg;
	}
	public String getDjjgmc() {
		return djjgmc;
	}
	public void setDjjgmc(String djjgmc) {
		this.djjgmc = djjgmc;
	}
	public String getQylx() {
		return qylx;
	}
	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	@Override
	public String toString() {
		return "DataBean [nbxh=" + nbxh + ", ztlx=" + ztlx + ", fddbr=" + fddbr
				+ ", mclx=" + mclx + ", clrq=" + clrq + ", qymc=" + qymc
				+ ", zch=" + zch + ", djjg=" + djjg + ", djjgmc=" + djjgmc
				+ ", qylx=" + qylx + "]";
	}	
}
