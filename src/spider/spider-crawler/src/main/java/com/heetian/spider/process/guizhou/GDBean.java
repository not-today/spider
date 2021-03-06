package com.heetian.spider.process.guizhou;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.component.EnterUrls;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.process.abstractclass.ProcessHandle;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;

public class GDBean {
	private String successed;
	private List<GdData> data;
	public String getSuccessed() {
		return successed;
	}
	public void setSuccessed(String successed) {
		this.successed = successed;
	}
	public List<GdData> getData() {
		return data;
	}
	public void setData(List<GdData> data) {
		this.data = data;
	}
	public List<GsgsShareholder> getgd(String nbxh, Page page, Site site, String entnameflag, String regnumberflag, String namevaluepairflag) {
		if(data==null||data.size()<=0)
			return null;
		String entName = (String) page.getRequest().getExtra(entnameflag);
		String regNumber = (String) page.getRequest().getExtra(regnumberflag);
		List<GsgsShareholder> ss = new ArrayList<GsgsShareholder>();
		for(GdData d:data){
			String uuid = TSTUtils.uuid();
			GsgsShareholder s = AnalysisForTable.createShareHolder(regNumber, d.getTzrlxmc(), d.getCzmc(), d.getZzlxmc(), d.getZzbh(), null);
			//if ((row.clcnt > 0) || (row.bgcnt > 0));用来判断股东详情是否存在
			if(d.getClcnt()>0||d.getBgcnt()>0){
				
			}else{
				s.setUuid(uuid);
				NameValuePair[] nvps = {
					new BasicNameValuePair("c", "2"),
					new BasicNameValuePair("t", "4"),
					new BasicNameValuePair("nbxh", nbxh),
					new BasicNameValuePair("czmc", d.getCzmc()),
					new BasicNameValuePair("type", "gdxq")
				};
				Request request = new Request();
				request.setUrl("http://gsxt.gzgs.gov.cn"+EnterUrls.GZGdxq+"?"+Math.random()+"&"+Math.random());
				request.setMethod(Method.POST);
				request.putExtra(regnumberflag, regNumber);
				request.putExtra(entnameflag, entName);
				request.putExtra(namevaluepairflag, nvps);
				request.putExtra(ProcessHandle.uuid_key, uuid);
				page.addTargetRequest(request);
			}
			ss.add(s);
		}
		return ss;
	}
}

class GdData{
	/*nbxh:"5200000100050321";
	tzrlxmc:"企业法人";
	zzbh:"110000014789708";
	clcnt:0;
	zzlxmc:"企业法人营业执照(公司)";
	bgcnt:0;
	czmc:"国信文化艺术股份有限公司"*/
	private String nbxh;
	private String tzrlxmc;
	private String zzbh;
	private int clcnt;
	private String zzlxmc;
	private int bgcnt;
	private String czmc;
	public String getNbxh() {
		return nbxh;
	}
	public void setNbxh(String nbxh) {
		this.nbxh = nbxh;
	}
	public String getTzrlxmc() {
		return tzrlxmc;
	}
	public void setTzrlxmc(String tzrlxmc) {
		this.tzrlxmc = tzrlxmc;
	}
	public String getZzbh() {
		return zzbh;
	}
	public void setZzbh(String zzbh) {
		this.zzbh = zzbh;
	}
	public int getClcnt() {
		return clcnt;
	}
	public void setClcnt(int clcnt) {
		this.clcnt = clcnt;
	}
	public int getBgcnt() {
		return bgcnt;
	}
	public void setBgcnt(int bgcnt) {
		this.bgcnt = bgcnt;
	}
	public String getZzlxmc() {
		return zzlxmc;
	}
	public void setZzlxmc(String zzlxmc) {
		this.zzlxmc = zzlxmc;
	}
	public String getCzmc() {
		return czmc;
	}
	public void setCzmc(String czmc) {
		this.czmc = czmc;
	}

}