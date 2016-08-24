package com.heetian.spider.process.jilin;

public class BianGengXX {
	 private String altaf;
	 private String altbe;
	 private BianGengXXSub altdate;
	 private String altitem;
	 private String openo;
	 private String pripid;
	 private String xzqh;
	public String getAltaf() {
		return altaf;
	}
	public void setAltaf(String altaf) {
		this.altaf = altaf;
	}
	public String getAltbe() {
		return altbe;
	}
	public void setAltbe(String altbe) {
		this.altbe = altbe;
	}

	public BianGengXXSub getAltdate() {
		return altdate;
	}
	public void setAltdate(BianGengXXSub altdate) {
		this.altdate = altdate;
	}
	public String getAltitem() {
		return altitem;
	}
	public void setAltitem(String altitem) {
		this.altitem = altitem;
	}
	public String getOpeno() {
		return openo;
	}
	public void setOpeno(String openo) {
		this.openo = openo;
	}
	public String getPripid() {
		return pripid;
	}
	public void setPripid(String pripid) {
		this.pripid = pripid;
	}
	public String getXzqh() {
		return xzqh;
	}
	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}
	@Override
	public String toString() {
		return "BianGengXX [altaf=" + altaf + ", altbe=" + altbe + ", altdate="
				+ altdate + ", altitem=" + altitem + ", openo=" + openo
				+ ", pripid=" + pripid + ", xzqh=" + xzqh + "]";
	}
	 
}
class BianGengXXSub{
	private String date;
	private String day;
	private String hours;
	private String minutes;
	private String month;
	private String seconds;
	private String time;
	private String timezoneOffset;
	private String year;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	@Override
	public String toString() {
		return "BianGengXXSub [date=" + date + ", day=" + day + ", hours="
				+ hours + ", minutes=" + minutes + ", month=" + month
				+ ", seconds=" + seconds + ", time=" + time
				+ ", timezoneOffset=" + timezoneOffset + ", year=" + year + "]";
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSeconds() {
		return seconds;
	}
	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(String timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
}