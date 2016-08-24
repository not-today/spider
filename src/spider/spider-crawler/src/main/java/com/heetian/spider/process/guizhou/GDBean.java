package com.heetian.spider.process.guizhou;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.utils.HttpConstant.Method;

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
	public List<GsgsShareholder> getgd(Page page, Site site, String entnameflag, String regnumberflag, String namevaluepairflag) {
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
				String tzr_nbxh = (String) page.getRequest().getExtra("nbxhflag");
				NameValuePair[] nvps = {
				  /*tzr_nbxh:"c9c55194de5b402b1516a2123c2c276f63c501f97d9eebb31aeeb2e2e0d237ab"
					tzr_czmc:"贵州茅台酒厂（集团）保健酒业有限公司"
					nbxh:"c9c55194de5b402b1516a2123c2c276f63c501f97d9eebb31aeeb2e2e0d237ab"
					qymc:"贵州茅台酒厂（集团）白金酒有限责任公司"
					zch:"520000000120008"*/
					new BasicNameValuePair("tzr_nbxh", tzr_nbxh),
					new BasicNameValuePair("tzr_czmc", d.getCzmc()),
					new BasicNameValuePair("nbxh", tzr_nbxh),
					new BasicNameValuePair("qymc", entName),
					new BasicNameValuePair("zch", regNumber)
				};
				Request request = new Request();
				request.setUrl("http://"+site.getDomain()+"/nzgs/tzrxx.jsp"+"?"+Math.random()+"&"+Math.random());
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