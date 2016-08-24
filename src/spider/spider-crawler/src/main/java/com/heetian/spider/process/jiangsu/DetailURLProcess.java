package com.heetian.spider.process.jiangsu;

import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant.Method;

import com.heetian.spider.process.abstractclass.JiangSuProcessHandlePrepare;
/**
 * @author tst
 */
@Deprecated
public class DetailURLProcess extends JiangSuProcessHandlePrepare{
	public DetailURLProcess(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = false;
		///ecipplatform/inner_cb/cb_queryCorpInfor_gsRelease.jsp
		///ecipplatform/gtgsh/gtgsh_queryCorpInfor_gsRelease.jsp    
		///ecipplatform/pfc/pfc_queryCorpInfor_gsRelease.jsp
		///ecipplatform/inner_pspc/pspc_queryCorpInfor_gsRelease.jsp
		///ecipplatform/inner_pb/pb_queryCorpInfor_gsRelease.jsp  爆开
		setUniqueWebUri("/ecipplatform/inner_ci/ci_queryCorpInfor_gsRelease.jsp");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		//("org", org), ("id", id),("seq_id", seq_id),("reg_no", reg_no),("containContextPath", containContextPath)
		NameValuePair[] nvps = (NameValuePair[]) page.getRequest().getExtra(NAMEVALUEPAIR);
		String org = nvps[0].getValue();
		String id = nvps[1].getValue();
		String seq_id = nvps[2].getValue();
		String regNumber = nvps[3].getValue();
		//String containContextPath = nvps[4].getValue();
		String entName = (String) page.getRequest().getExtra(ENTNAME);
		jbxx(page, task, org, id, seq_id, regNumber, entName);
		gdxx(page, task, org, id, seq_id, regNumber, entName);
		bgxx(page, task, org, id, regNumber, entName);
		zyryxx(page, task, org, id, seq_id, regNumber, entName);
		fzjgxx(page, task, org, id, seq_id, regNumber, entName);
	}
	private void fzjgxx(Page page, PageProcessor task, String org, String id,
			String seq_id, String regNumber, String entName) {
		//分支机构
		NameValuePair[] nvps5 = {
				new BasicNameValuePair("CORP_ORG", org),
				new BasicNameValuePair("CORP_ID", id),
				new BasicNameValuePair("CORP_SEQ_ID", seq_id),
				new BasicNameValuePair("showRecordLine", "1"),
				new BasicNameValuePair("specificQuery", "branchOfficeInfor"),
				new BasicNameValuePair("pageNo","1"),
				new BasicNameValuePair("pageSize","5"),
		};
		page.addTargetRequest(builderRequest("/ecipplatform/ciServlet.json?ciEnter=true&"+urlTail()+Math.random()+""+Math.random(), Method.POST, regNumber, entName, nvps5));
	}
	private void zyryxx(Page page, PageProcessor task, String org, String id,
			String seq_id, String regNumber, String entName) {
		//主要人员信息
		NameValuePair[] nvps4 = {
				new BasicNameValuePair("CORP_ORG", org),
				new BasicNameValuePair("CORP_ID", id),
				new BasicNameValuePair("CORP_SEQ_ID", seq_id),
				new BasicNameValuePair("showRecordLine", "1"),
				new BasicNameValuePair("specificQuery", "personnelInformation"),
				new BasicNameValuePair("pageNo","1"),
				new BasicNameValuePair("pageSize","5"),
		};
		page.addTargetRequest(builderRequest("/ecipplatform/ciServlet.json?ciEnter=true&"+urlTail()+Math.random()+""+Math.random(), Method.POST, regNumber, entName, nvps4));
	}
	private void bgxx(Page page, PageProcessor task, String org, String id,
			String regNumber, String entName) {
		//变更信息
		NameValuePair[] nvps3 = {
				new BasicNameValuePair("showRecordLine", "1"),
				new BasicNameValuePair("specificQuery", "commonQuery"),
				new BasicNameValuePair("propertiesName", "biangeng"),
				new BasicNameValuePair("corp_org", org),
				new BasicNameValuePair("corp_id", id),
				new BasicNameValuePair("tmp",new Date().toString()),
				new BasicNameValuePair("pageNo","1"),
				new BasicNameValuePair("pageSize","5"),
		};
		page.addTargetRequest(builderRequest("/ecipplatform/commonServlet.json?commonEnter=true&"+urlTail()+Math.random()+""+Math.random(), Method.POST, regNumber, entName, nvps3));
	}
	private void gdxx(Page page, PageProcessor task, String org, String id,
			String seq_id, String regNumber, String entName) {
		//股东信息
		NameValuePair[] nvps2 = {
				new BasicNameValuePair("CORP_ORG", org),
				new BasicNameValuePair("CORP_ID", id),
				new BasicNameValuePair("CORP_SEQ_ID", seq_id),
				new BasicNameValuePair("showRecordLine", "1"),
				new BasicNameValuePair("specificQuery", "investmentInfor"),
				new BasicNameValuePair("pageNo","1"),
				new BasicNameValuePair("pageSize","5"),
		};
		page.addTargetRequest(builderRequest("/ecipplatform/ciServlet.json?ciEnter=true&"+urlTail()+Math.random()+""+Math.random(), Method.POST,  regNumber, entName, nvps2));
	}
	private void jbxx(Page page, PageProcessor task, String org, String id,
			String seq_id, String regNumber, String entName) {
		//基本信息
		NameValuePair[] nvps1 = {
				new BasicNameValuePair("org", org),
				new BasicNameValuePair("id", id),
				new BasicNameValuePair("seq_id", seq_id),
				new BasicNameValuePair("specificQuery", "basicInfo"),
		};
		page.addTargetRequest(builderRequest("/ecipplatform/ciServlet.json?ciEnter=true&"+urlTail()+Math.random()+""+Math.random(), Method.POST, regNumber, entName, nvps1));
	}
}
