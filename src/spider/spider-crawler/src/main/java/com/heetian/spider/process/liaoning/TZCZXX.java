package com.heetian.spider.process.liaoning;

import java.util.List;

public class TZCZXX {
/**
 {"rowspan":1,"tRegTzrrjxxList":[
		认缴		{"condate":"","condateStr":"","conform":"1","conformName":"货币","currency":"","currencyName":"","id":"210200200600052665sy11r","invid":"210200200600052665sy","subconam":36900}
				],
	    实缴 "tRegTzrsjxxList":[
				{"acconam":36900,"condate":"2001-03-19","condateStr":"2001年03月19日","conform":"1","conformName":"货币","currency":"","currencyName":"","id":"210200200600052665sy11s","invid":"210200200600052665sy"}
				],
	   出资 　"tRegTzrxx":{"blicno":" ","blictype":"","blictypeName":"","cerno":"210225196404150190","certype":"1","certypeName":"","country":"","countryName":"","currency":"","currencyName":"","dom":"","inv":"隋信敏","invid":"210200200600052665sy","invtype":"22","invtypeName":"非农民自然人","liacconam":36900,"lisubconam":36900,"pripid":"210200000021995031500017","respform":"","respformName":"","sconform":"货币","sconformName":"货币"}
},
 */
	private String rowspan;
	private List<TRegTzrrjxxList> tRegTzrrjxxList;
	private List<TRegTzrsjxxList> tRegTzrsjxxList;
	private TRegTzrxx tRegTzrxx;
	public String getRowspan() {
		return rowspan;
	}
	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}
	public List<TRegTzrrjxxList> gettRegTzrrjxxList() {
		return tRegTzrrjxxList;
	}
	public void settRegTzrrjxxList(List<TRegTzrrjxxList> tRegTzrrjxxList) {
		this.tRegTzrrjxxList = tRegTzrrjxxList;
	}
	public List<TRegTzrsjxxList> gettRegTzrsjxxList() {
		return tRegTzrsjxxList;
	}
	public void settRegTzrsjxxList(List<TRegTzrsjxxList> tRegTzrsjxxList) {
		this.tRegTzrsjxxList = tRegTzrsjxxList;
	}
	public TRegTzrxx gettRegTzrxx() {
		return tRegTzrxx;
	}
	public void settRegTzrxx(TRegTzrxx tRegTzrxx) {
		this.tRegTzrxx = tRegTzrxx;
	}
	@Override
	public String toString() {
		return "TZCZXX [rowspan=" + rowspan + ", tRegTzrrjxxList="
				+ tRegTzrrjxxList + ", tRegTzrsjxxList=" + tRegTzrsjxxList
				+ ", tRegTzrxx=" + tRegTzrxx + "]";
	}
	
}
class TRegTzrrjxxList{
	private String condate;
	private String condateStr;
	private String conform;
	private String conformName;
	private String currency;
	private String currencyName;
	private String id;
	private String invid;
	private String subconam;
	public String getCondate() {
		return condate;
	}
	public void setCondate(String condate) {
		this.condate = condate;
	}
	public String getCondateStr() {
		return condateStr;
	}
	public void setCondateStr(String condateStr) {
		this.condateStr = condateStr;
	}
	public String getConform() {
		return conform;
	}
	public void setConform(String conform) {
		this.conform = conform;
	}
	public String getConformName() {
		return conformName;
	}
	public void setConformName(String conformName) {
		this.conformName = conformName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvid() {
		return invid;
	}
	public void setInvid(String invid) {
		this.invid = invid;
	}
	public String getSubconam() {
		return subconam;
	}
	public void setSubconam(String subconam) {
		this.subconam = subconam;
	}
	@Override
	public String toString() {
		return "TRegTzrrjxxList [condate=" + condate + ", condateStr="
				+ condateStr + ", conform=" + conform + ", conformName="
				+ conformName + ", currency=" + currency + ", currencyName="
				+ currencyName + ", id=" + id + ", invid=" + invid
				+ ", subconam=" + subconam + "]";
	}
	
}
class TRegTzrsjxxList{
	private String acconam;
	private String condate;
	private String condateStr;
	private String conform;
	private String conformName;
	private String currency;
	private String currencyName;
	private String id;
	private String invid;
	public String getAcconam() {
		return acconam;
	}
	public void setAcconam(String acconam) {
		this.acconam = acconam;
	}
	public String getCondate() {
		return condate;
	}
	public void setCondate(String condate) {
		this.condate = condate;
	}
	public String getCondateStr() {
		return condateStr;
	}
	public void setCondateStr(String condateStr) {
		this.condateStr = condateStr;
	}
	public String getConform() {
		return conform;
	}
	public void setConform(String conform) {
		this.conform = conform;
	}
	public String getConformName() {
		return conformName;
	}
	public void setConformName(String conformName) {
		this.conformName = conformName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvid() {
		return invid;
	}
	public void setInvid(String invid) {
		this.invid = invid;
	}
	@Override
	public String toString() {
		return "TRegTzrsjxxList [acconam=" + acconam + ", condate=" + condate
				+ ", condateStr=" + condateStr + ", conform=" + conform
				+ ", conformName=" + conformName + ", currency=" + currency
				+ ", currencyName=" + currencyName + ", id=" + id + ", invid="
				+ invid + "]";
	}
	
}
class TRegTzrxx{
	private String blicno;
	private String blictype;
	private String blictypeName;
	private String cerno;
	private String certype;
	private String certypeName;
	private String country;
	private String countryName;
	private String currency;
	private String currencyName;
	private String dom;
	private String inv;
	private String invid;
	private String invtype;
	private String invtypeName;
	private String liacconam;
	private String lisubconam;
	private String pripid;
	private String respform;
	private String respformName;
	private String sconform;
	private String sconformName;
	public String getBlicno() {
		return blicno;
	}
	public void setBlicno(String blicno) {
		this.blicno = blicno;
	}
	public String getBlictype() {
		return blictype;
	}
	public void setBlictype(String blictype) {
		this.blictype = blictype;
	}
	public String getBlictypeName() {
		return blictypeName;
	}
	public void setBlictypeName(String blictypeName) {
		this.blictypeName = blictypeName;
	}
	public String getCerno() {
		return cerno;
	}
	public void setCerno(String cerno) {
		this.cerno = cerno;
	}
	public String getCertype() {
		return certype;
	}
	public void setCertype(String certype) {
		this.certype = certype;
	}
	public String getCertypeName() {
		return certypeName;
	}
	public void setCertypeName(String certypeName) {
		this.certypeName = certypeName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getDom() {
		return dom;
	}
	public void setDom(String dom) {
		this.dom = dom;
	}
	public String getInv() {
		return inv;
	}
	public void setInv(String inv) {
		this.inv = inv;
	}
	public String getInvid() {
		return invid;
	}
	public void setInvid(String invid) {
		this.invid = invid;
	}
	public String getInvtype() {
		return invtype;
	}
	public void setInvtype(String invtype) {
		this.invtype = invtype;
	}
	public String getInvtypeName() {
		return invtypeName;
	}
	public void setInvtypeName(String invtypeName) {
		this.invtypeName = invtypeName;
	}
	public String getLiacconam() {
		return liacconam;
	}
	public void setLiacconam(String liacconam) {
		this.liacconam = liacconam;
	}
	public String getLisubconam() {
		return lisubconam;
	}
	public void setLisubconam(String lisubconam) {
		this.lisubconam = lisubconam;
	}
	public String getPripid() {
		return pripid;
	}
	public void setPripid(String pripid) {
		this.pripid = pripid;
	}
	public String getRespform() {
		return respform;
	}
	public void setRespform(String respform) {
		this.respform = respform;
	}
	public String getRespformName() {
		return respformName;
	}
	public void setRespformName(String respformName) {
		this.respformName = respformName;
	}
	public String getSconform() {
		return sconform;
	}
	public void setSconform(String sconform) {
		this.sconform = sconform;
	}
	public String getSconformName() {
		return sconformName;
	}
	public void setSconformName(String sconformName) {
		this.sconformName = sconformName;
	}
	@Override
	public String toString() {
		return "TRegTzrxx [blicno=" + blicno + ", blictype=" + blictype
				+ ", blictypeName=" + blictypeName + ", cerno=" + cerno
				+ ", certype=" + certype + ", certypeName=" + certypeName
				+ ", country=" + country + ", countryName=" + countryName
				+ ", currency=" + currency + ", currencyName=" + currencyName
				+ ", dom=" + dom + ", inv=" + inv + ", invid=" + invid
				+ ", invtype=" + invtype + ", invtypeName=" + invtypeName
				+ ", liacconam=" + liacconam + ", lisubconam=" + lisubconam
				+ ", pripid=" + pripid + ", respform=" + respform
				+ ", respformName=" + respformName + ", sconform=" + sconform
				+ ", sconformName=" + sconformName + "]";
	}
	
}