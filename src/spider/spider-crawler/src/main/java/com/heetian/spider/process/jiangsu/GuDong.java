package com.heetian.spider.process.jiangsu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.utils.AnalysisForTable;
import com.heetian.spider.utils.TSTUtils;

public class GuDong {
	private String dateFormat;
	private List<SubGD> items;
	private String total;
	private Map<String,NameValuePair[]> nameValuePair = new HashMap<String,NameValuePair[]>();
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public List<SubGD> getItems() {
		return items;
	}

	public void setItems(List<SubGD> items) {
		this.items = items;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	public String getURL(String tail){
		//return "/ecipplatform/inner_ci/ci_gsRelease_investorInfor.jsp?"+tail;
		return "/ecipplatform/ciServlet.json?ciDetail=true&"+tail;
	}
	public List<GsgsShareholder> getGD(String regNumber,String entName) {
		if(items!=null&&items.size()>0){
			List<GsgsShareholder> tmps = new ArrayList<GsgsShareholder>();
			for(SubGD item:items){
				GsgsShareholder share = AnalysisForTable.createShareHolder(regNumber, item.getC1(), item.getC2(), item.getC3(), item.getC4(), null);
				tmps.add(share);
				String c5 = item.getC5();
				String c6 = item.getC6();
				String c7 = item.getC7();
				String uuid = null;
				if(c5!=null&&!"".equals(c5)&&c6!=null&&!"".equals(c6)&&c7!=null&&!"".equals(c7)){
					uuid = TSTUtils.uuid();
					share.setUuid(uuid);
					 /*NameValuePair[] nvps = {
						new BasicNameValuePair("org", item.getCORP_ORG()),
						new BasicNameValuePair("id", item.getCORP_ID()),
						new BasicNameValuePair("seq_id", item.getCORP_SEQ_ID()),
						new BasicNameValuePair("corp_name", entName),
						new BasicNameValuePair("reg_no", regNumber),
						new BasicNameValuePair("touziren_org",c5),
						new BasicNameValuePair("touziren_id", c6),
						new BasicNameValuePair("touziren_seq_id", c7),
						new BasicNameValuePair("existsAbnormal", "false"),
					};*/
					NameValuePair[] nvps = {
							new BasicNameValuePair("ORG", c5),
							new BasicNameValuePair("ID", c6),
							new BasicNameValuePair("SEQ_ID", c7),
							new BasicNameValuePair("CORP_ORG", item.getCORP_ORG()),
							new BasicNameValuePair("CORP_ID", item.getCORP_ID()),
							new BasicNameValuePair("REG_NO", regNumber),
							new BasicNameValuePair("specificQuery", "investorInfor"),
					};
					nameValuePair.put(uuid,nvps);
				}
			}
			if(tmps.size()>0)
				return tmps;
		}
		return null;
	}
	public Map<String,NameValuePair[]> getParameters(){
		if(nameValuePair.size()>0){
			return nameValuePair;
		}
		return null;
	}
}
class SubGD{
	private String C5;
	private String C6;
	private String C7;
	//lookup_touziren(5,6,7)用来构建函数
	/**
	function lookup_touziren(org, id, seq_id) {
		var $form = jQuery('#formx');
		$form.attr('action', getPath() + '/inner_ci/ci_gsRelease_investorInfor.jsp');
		jQuery('#touziren_org').val(org);
		jQuery('#touziren_id').val(id);
		jQuery('#touziren_seq_id').val(seq_id);
		$form.submit();
	}
	*/
	private String CORP_ORG;
	private String CORP_ID;
	private String CORP_SEQ_ID;
	private String C1 ;  //股东类型
	private String C2 ;    //股东
	private String C3 ;   //证件类型
	private String C4 ;   //证件号
	private String STOCK_TYPE;  
	private String RN;
	public String getC5() {
		return C5;
	}
	public void setC5(String c5) {
		C5 = c5;
	}
	public String getC6() {
		return C6;
	}
	public void setC6(String c6) {
		C6 = c6;
	}
	public String getC7() {
		return C7;
	}
	public void setC7(String c7) {
		C7 = c7;
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
	public String getCORP_SEQ_ID() {
		return CORP_SEQ_ID;
	}
	public void setCORP_SEQ_ID(String cORP_SEQ_ID) {
		CORP_SEQ_ID = cORP_SEQ_ID;
	}
	public String getC1() {
		return C1;
	}
	public void setC1(String c1) {
		C1 = c1;
	}
	public String getC2() {
		return C2;
	}
	public void setC2(String c2) {
		C2 = c2;
	}
	public String getC3() {
		return C3;
	}
	public void setC3(String c3) {
		C3 = c3;
	}
	public String getC4() {
		return C4;
	}
	public void setC4(String c4) {
		C4 = c4;
	}
	public String getSTOCK_TYPE() {
		return STOCK_TYPE;
	}
	public void setSTOCK_TYPE(String sTOCK_TYPE) {
		STOCK_TYPE = sTOCK_TYPE;
	}
	public String getRN() {
		return RN;
	}
	public void setRN(String rN) {
		RN = rN;
	}
}