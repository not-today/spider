package com.heetian.spider.process.jiangsu;

import java.util.ArrayList;
import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.utils.AnalysisForTable;

public class ZhuYaoRenYuan {
	private String dateFormat;
	private List<SubZYRY> items;
	private String total;
	public List<GsgsMainPersonel> getZYRY(String regNumber) {
		if(items!=null&&items.size()>0){
			List<GsgsMainPersonel> tmps = new ArrayList<GsgsMainPersonel>();
			for(SubZYRY item:items){
				tmps.add(AnalysisForTable.createMainPerson(regNumber, item.getPERSON_NAME1(), item.getPOSITION_NAME1()));
				String name2 = item.getPERSON_NAME2();
				String position2 = item.getPOSITION_NAME2();
				if(name2!=null&&!"".equals(name2)){
					tmps.add(AnalysisForTable.createMainPerson(regNumber,name2,position2));
				}
			}
			if(tmps.size()>0)
				return tmps;
		}
		return null;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public List<SubZYRY> getItems() {
		return items;
	}
	public void setItems(List<SubZYRY> items) {
		this.items = items;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
}
class SubZYRY{
	private String POSITION_NAME1; //职务1
	private String POSITION_NAME2;    //职务2
	private String PERSON_NAME2;  //姓名2
	private String VALUES2RN;   //序号
	private String PERSON_NAME1;  //姓名1
	private String VALUES1RN;   //序号
	public String getPOSITION_NAME1() {
		return POSITION_NAME1;
	}
	public void setPOSITION_NAME1(String pOSITION_NAME1) {
		POSITION_NAME1 = pOSITION_NAME1;
	}
	public String getPOSITION_NAME2() {
		return POSITION_NAME2;
	}
	public void setPOSITION_NAME2(String pOSITION_NAME2) {
		POSITION_NAME2 = pOSITION_NAME2;
	}
	public String getPERSON_NAME2() {
		return PERSON_NAME2;
	}
	public void setPERSON_NAME2(String pERSON_NAME2) {
		PERSON_NAME2 = pERSON_NAME2;
	}
	public String getVALUES2RN() {
		return VALUES2RN;
	}
	public void setVALUES2RN(String vALUES2RN) {
		VALUES2RN = vALUES2RN;
	}
	public String getPERSON_NAME1() {
		return PERSON_NAME1;
	}
	public void setPERSON_NAME1(String pERSON_NAME1) {
		PERSON_NAME1 = pERSON_NAME1;
	}
	public String getVALUES1RN() {
		return VALUES1RN;
	}
	public void setVALUES1RN(String vALUES1RN) {
		VALUES1RN = vALUES1RN;
	}
}