package com.heetian.spider.process.guizhou;

import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.utils.AnalysisForTable;

public class FZJGBean {
	private String successed;
	private List<FzjgData> data;
	public String getSuccessed() {
		return successed;
	}
	public void setSuccessed(String successed) {
		this.successed = successed;
	}
	public List<FzjgData> getData() {
		return data;
	}
	public void setData(List<FzjgData> data) {
		this.data = data;
	}
	public List<GsgsBranch> getfz(String regNumber) {
		if(data==null||data.size()<=0)
			return null;
		List<GsgsBranch> bs = new ArrayList<GsgsBranch>();
		for(FzjgData fd:data){
			GsgsBranch b = AnalysisForTable.createBranch(regNumber, fd.getFgszch(), fd.getFgsmc(), fd.getFgsdjjgmc());
			bs.add(b);
		}
		return bs;
	}
}

class FzjgData{
	/*fgsdjjgmc:"贵阳市云岩区工商局";
	nbxh:"5200000000000171";
	rownum:1;
	fgsyyqx1:"2014年6月4日";
	fgsmc:"中国贵州茅台酒厂（集团）有限责任公司贵阳分公司";
	fgszch:"520103000794327";
	fgsnbxh:"5201030100419639";*/
	private String fgsdjjgmc;
	private String nbxh;
	private String rownum;
	private String fgsyyqx1;
	private String fgsmc;
	private String fgszch;
	private String fgsnbxh;
	public String getFgsdjjgmc() {
		return fgsdjjgmc;
	}
	public void setFgsdjjgmc(String fgsdjjgmc) {
		this.fgsdjjgmc = fgsdjjgmc;
	}
	public String getNbxh() {
		return nbxh;
	}
	public void setNbxh(String nbxh) {
		this.nbxh = nbxh;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getFgsyyqx1() {
		return fgsyyqx1;
	}
	public void setFgsyyqx1(String fgsyyqx1) {
		this.fgsyyqx1 = fgsyyqx1;
	}
	public String getFgsmc() {
		return fgsmc;
	}
	public void setFgsmc(String fgsmc) {
		this.fgsmc = fgsmc;
	}
	public String getFgszch() {
		return fgszch;
	}
	public void setFgszch(String fgszch) {
		this.fgszch = fgszch;
	}
	public String getFgsnbxh() {
		return fgsnbxh;
	}
	public void setFgsnbxh(String fgsnbxh) {
		this.fgsnbxh = fgsnbxh;
	}
}