package com.heetian.spider.process.xizang;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.QygsQynb;
import com.heetian.spider.dbcp.bean.QygsQynbAssets;
import com.heetian.spider.dbcp.bean.QygsQynbBasic;
import com.heetian.spider.dbcp.bean.QygsQynbContribution;
import com.heetian.spider.dbcp.bean.QygsQynbEdit;
import com.heetian.spider.dbcp.bean.QygsQynbGua;
import com.heetian.spider.dbcp.bean.QygsQynbInvestment;
import com.heetian.spider.dbcp.bean.QygsQynbStock;
import com.heetian.spider.dbcp.bean.QygsQynbWebInfo;
import com.heetian.spider.process.abstractclass.XiZangProcessHandlePrepare;
import com.heetian.spider.utils.AnalysisForTable;
/**
 * 
 * @author tst
 *
 */
public class ContextGS_QYNB_XQ_Process extends XiZangProcessHandlePrepare {
	public ContextGS_QYNB_XQ_Process(){
		this.isDownloadCodeProcess = false;
		this.isStorageProcess = true;
		setUniqueWebUri("/QueryYearExamineDetail.jspx");
	}
	@Override
	public void analyticalProcess(Page page,PageProcessor task) {
		String regNumber = (String) page.getRequest().getExtra(REGNUMBER);
		String date = (String) page.getRequest().getExtra("date");
		String report = (String) page.getRequest().getExtra("report");
		String uuid = (String) page.getRequest().getExtra("uuid");
		QygsQynb qynb = new QygsQynb();
		qynb.setUuid(uuid);
		qynb.setSubDate(report);
		qynb.setPubDate(date);
		List<String> tables = page.getHtml().xpath("//div[@id='sifapanding']//table").all();
		for(String table:tables){
			if(table==null||!table.contains("企业基本信息")){
				QygsQynbBasic basic = qynbJb(table,uuid);
				qynb.setBasic(basic);
			}
			if(table==null||!table.contains("网站或网店信息")){
				List<QygsQynbWebInfo> webs = qynbWebInfo(table,uuid);
				qynb.setWebinfos(webs);
			}
			if(table==null||!table.contains("股东（发起人）及出资信息")){
				List<QygsQynbContribution> conts = qynbchuzi(table,uuid);
				qynb.setContributions(conts);
			}
			if(table==null||!table.contains("对外投资信息")){
				List<QygsQynbInvestment> invs = qynbtouzi(table,uuid);
				qynb.setInvestments(invs);
			}
			if(table==null||!table.contains("企业资产状况信息")){
				QygsQynbAssets assets = qynbzichang(table,uuid);
				qynb.setAssets(assets);
			}
			if(table==null||!table.contains("对外提供保证担保信息")){
				List<QygsQynbGua> guas = qynbdanbao(table,uuid);
				qynb.setGuas(guas);
			}
			if(table==null||!table.contains("股权变更信息")){
				List<QygsQynbStock> stocks = qynbgdbg(table,uuid);
				qynb.setStocks(stocks);
			}
			if(table==null||!table.contains("修改记录")){
				List<QygsQynbEdit> edits = qynbedit(table,uuid);
				qynb.setEdits(edits);
			}
		}
		TSTPageProcessor tst = ((TSTPageProcessor) task);
		tst.setDataQYNB(qynb, regNumber);
	}
	private List<QygsQynbEdit> qynbedit(String table,String uuid) {
		if(table==null||!table.contains("修改记录"))
			return null;
		List<String> trs = getTrsFromTable(table);
		if(trs==null||trs.size()<=2)
			return null;
		trs.remove(0);
		trs.remove(0);
		List<QygsQynbEdit> edits = new ArrayList<QygsQynbEdit>();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds==null||tds.size()<=0)
				continue;
			if(tds.size()==5){
				String content = AnalysisForTable.getTdText(tds.get(1));
				String bfore = AnalysisForTable.getTdText(tds.get(2)); 
				String after = AnalysisForTable.getTdText(tds.get(3));
				String date = AnalysisForTable.getTdText(tds.get(4));
				if("".equals(content)&&"".equals(bfore)&&"".equals(after)&&"".equals(date))
					continue;
				QygsQynbEdit edit = new QygsQynbEdit();
				edit.setEditContent(content);
				edit.setEditBefore(bfore);
				edit.setEditAfter(after);
				edit.setEditDate(date);
				edit.setUuid(uuid);
				edits.add(edit);
			}else{
				
			}
		}
		return edits;
	}
	private List<QygsQynbStock> qynbgdbg(String table, String uuid) {
		if(table==null||!table.contains("股权变更信息"))
			return null;
		List<String> trs = getTrsFromTable(table);
		if(trs==null||trs.size()<=2)
			return null;
		trs.remove(0);
		trs.remove(0);
		List<QygsQynbStock> qqss = new ArrayList<QygsQynbStock>();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds==null||tds.size()<=0)
				continue;
			if(tds.size()==4){
				String investor = AnalysisForTable.getTdText(tds.get(0));
				String bfore = AnalysisForTable.getTdText(tds.get(1)); 
				String after = AnalysisForTable.getTdText(tds.get(2));
				String date = AnalysisForTable.getTdText(tds.get(3));
				if("".equals(investor)&&"".equals(bfore)&&"".equals(after)&&"".equals(date))
					continue;
				QygsQynbStock qqs = new QygsQynbStock();
				qqs.setInvestor(investor);
				qqs.setBeforeProportion(bfore);
				qqs.setAfterProportion(after);
				qqs.setDate(date);
				qqs.setUuid(uuid);
				qqss.add(qqs);
			}else{
				
			}
		}
		return qqss;
	}
	private List<QygsQynbGua> qynbdanbao(String table, String uuid) {
		if(table==null||!table.contains("对外提供保证担保信息"))
			return null;
		List<String> trs = getTrsFromTable(table); 
		if(trs==null||trs.size()<=2)
			return null;
		trs.remove(0);
		trs.remove(0);
		List<QygsQynbGua> guas = new ArrayList<QygsQynbGua>();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds==null||tds.size()<=0)
				continue;
			if(tds.size()==7){
				String creditor = AnalysisForTable.getTdText(tds.get(0));
				String obligor = AnalysisForTable.getTdText(tds.get(1)); 
				String type = AnalysisForTable.getTdText(tds.get(2));
				String money = AnalysisForTable.getTdText(tds.get(3));
				String performDate = AnalysisForTable.getTdText(tds.get(4));
				String ensureDate = AnalysisForTable.getTdText(tds.get(5));
				String method = AnalysisForTable.getTdText(tds.get(6));
				if("".equals(creditor)&&"".equals(obligor)&&"".equals(type)&&"".equals(money)
						&&"".equals(performDate)&&"".equals(ensureDate)&&"".equals(method))
					continue;
				QygsQynbGua gua = new QygsQynbGua();
				gua.setCreditor(creditor);
				gua.setObligor(obligor);
				gua.setType(type);
				gua.setMoney(money);
				gua.setPerformDate(performDate);
				gua.setEnsureDate(ensureDate);
				gua.setMethod(method);
				gua.setUuid(uuid);
				guas.add(gua);
			}else{
				
			}
		}
		return guas;
	}
	private QygsQynbAssets qynbzichang(String table, String uuid) {
		if(table==null||!table.contains("企业资产状况信息"))
			return null;
		List<String> trs = getTrsFromTable(table); 
		if(trs==null||trs.size()<=2)
			return null;
		trs.remove(0);
		trs.remove(0);
		QygsQynbAssets assets = new QygsQynbAssets();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds==null||tds.size()<=0)
				continue;
			if(tds.size()==4){
				String key = AnalysisForTable.getTdText(tds.get(0));
				String value = AnalysisForTable.getTdText(tds.get(1));
				setQygsQynbAssetsValue(assets, key, value);
				key = AnalysisForTable.getTdText(tds.get(0));
				value = AnalysisForTable.getTdText(tds.get(1));
				setQygsQynbAssetsValue(assets, key, value);
			}else{
				
			}
		}
		assets.setUuid(uuid);
		return assets;
	}
	private void setQygsQynbAssetsValue(QygsQynbAssets assets,String key,String value){
		if(key==null||"".equals(key.trim()))
			return;
		if(key.contains("资产总额")){
			assets.setAssetsTotal(value);
		}else if(key.contains("负债总额")){
			assets.setLiabilitiesTotal(value);
		}else if(key.contains("利润总额")){
			assets.setProfitTotal(value);
		}else if(key.contains("净利润")){
			assets.setNetProfit(value);
		}else if(key.contains("所有者权益合计")){
			assets.setOwnerInterest(value);
		}else if(key.contains("营业总收入")){
			assets.setBusinessTotal(value);
		}else if(key.contains("营业总收入中主营业务收入")){
			assets.setBusinessOfMainTotal(value);
		}else if(key.contains("纳税总额")){
			assets.setTaxes(value);
		}
	}
	private List<QygsQynbInvestment> qynbtouzi(String table, String uuid) {
		if(table==null||!table.contains("对外投资信息"))
			return null;
		List<String> trs = getTrsFromTable(table); 
		if(trs==null||trs.size()<=2)
			return null;
		trs.remove(0);
		trs.remove(0);
		List<QygsQynbInvestment> invs = new ArrayList<QygsQynbInvestment>();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds==null||tds.size()<=0)
				continue;
			if(tds.size()==2){
				String name = AnalysisForTable.getTdText(tds.get(0));
				String reg = AnalysisForTable.getTdText(tds.get(1)); 
				if("".equals(name)&&"".equals(reg))
					continue;
				QygsQynbInvestment in = new QygsQynbInvestment();
				in.setName(name);
				in.setReg(reg);
				in.setUuid(uuid);
				invs.add(in);
			}else{
				
			}
		}
		return invs;
	}
	private List<QygsQynbContribution> qynbchuzi(String table, String uuid) {
		if(table==null||!table.contains("股东（发起人）及出资信息"))
			return null;
		List<String> trs = getTrsFromTable(table); 
		if(trs==null||trs.size()<=1)
			return null;
		trs.remove(0);
		List<QygsQynbContribution> ctbs = new ArrayList<QygsQynbContribution>();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds==null||tds.size()<=0)
				continue;
			if(tds.size()==7){
				String investor = AnalysisForTable.getTdText(tds.get(0));
				String subsribedMoney = AnalysisForTable.getTdText(tds.get(1)); 
				String subsribedDate = AnalysisForTable.getTdText(tds.get(2)); 
				String subsribedMethod = AnalysisForTable.getTdText(tds.get(3)); 
				String paidMoney = AnalysisForTable.getTdText(tds.get(4)); 
				String contributionDate = AnalysisForTable.getTdText(tds.get(5)); 
				String contributionMethod = AnalysisForTable.getTdText(tds.get(6)); 
				if("".equals(investor)&&"".equals(subsribedMoney)&&"".equals(subsribedDate)&&"".equals(subsribedMethod)
						&&"".equals(paidMoney)&&"".equals(contributionDate)&&"".equals(contributionMethod))
					continue;
				QygsQynbContribution ctb = new QygsQynbContribution();
				ctb.setInvestor(investor);
				ctb.setSubsribedMoney(subsribedMoney);
				ctb.setSubsribedDate(subsribedDate);
				ctb.setSubsribedMethod(subsribedMethod);
				ctb.setPaidMoney(paidMoney);
				ctb.setContributionDate(contributionDate);
				ctb.setContributionMethod(contributionMethod);
				ctb.setUuid(uuid);
				ctbs.add(ctb);
			}else{
				
			}
		}
		return ctbs;
	}
	private List<QygsQynbWebInfo> qynbWebInfo(String table, String uuid) {
		if(table==null||!table.contains("网站或网店信息"))
			return null;
		List<String> trs = getTrsFromTable(table); 
		if(trs==null||trs.size()<=2)
			return null;
		trs.remove(0);
		trs.remove(0);
		List<QygsQynbWebInfo> wins = new ArrayList<QygsQynbWebInfo>();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds==null||tds.size()<=0)
				continue;
			if(tds.size()==3){
				String type = AnalysisForTable.getTdText(tds.get(0));
				String name = AnalysisForTable.getTdText(tds.get(1)); 
				String website = AnalysisForTable.getTdText(tds.get(2)); 
				if("".equals(type)&&"".equals(name)&&"".equals(type)&&"".equals(website))
					continue;
				QygsQynbWebInfo win = new QygsQynbWebInfo();
				win.setType(type);
				win.setName(name);
				win.setWebsite(website);
				win.setUuid(uuid);
				wins.add(win);
			}else{
				
			}
		}
		return wins;
	}
	private QygsQynbBasic qynbJb(String table, String uuid) {
		if(table==null||!table.contains("企业基本信息"))
			return null;
		List<String> trs = getTrsFromTable(table);  
		if(trs==null||trs.size()<=2)
			return null;
		trs.remove(0);
		trs.remove(0);
		QygsQynbBasic basic = new QygsQynbBasic();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds==null||tds.size()<=0)
				continue;
			if(tds.size()==2){
				String key = AnalysisForTable.getTdText(tds.get(0));
				String value = AnalysisForTable.getTdText(tds.get(1));
				setQygsQynbQygsQynbBasic(basic, key, value);
			}else if(tds.size()==4){
				String key = AnalysisForTable.getTdText(tds.get(0));
				String value = AnalysisForTable.getTdText(tds.get(1));
				setQygsQynbQygsQynbBasic(basic, key, value);
				key = AnalysisForTable.getTdText(tds.get(0));
				value = AnalysisForTable.getTdText(tds.get(1));
				setQygsQynbQygsQynbBasic(basic, key, value);
				
			}else{
				
			}
		}
		basic.setUuid(uuid);
		return basic;
	}
	private void setQygsQynbQygsQynbBasic(QygsQynbBasic basic,String key,String value){
		if(key==null||"".equals(key.trim()))
			return;
		if(key.contains("注册号/统一社会信用代码")){
			basic.setReg(value);
		}else if(key.contains("企业名称")){
			basic.setName(value);
		}else if(key.contains("企业联系电话")){
			basic.setTel(value);
		}else if(key.contains("邮政编码")){
			basic.setZipCode(value);
		}else if(key.contains("企业通信地址")){
			basic.setMailingAddr(value);
		}else if(key.contains("企业电子邮箱")){
			basic.setEmail(value);
		}else if(key.contains("有限责任公司本年度是否发生股东股权转让")){
			basic.setMakeOver(value);
		}else if(key.contains("企业经营状态")){
			basic.setStatus(value);
		}else if(key.contains("是否有网站或网店")){
			basic.setHaveWeb(value);
		}else if(key.contains("企业是否有投资信息或购买其他公司股权")){
			basic.setInvested(value);
		}else if(key.contains("从业人数")){
			basic.setPeopleNum(value);
		}
	}
	private List<String> getTrsFromTable(String tableSTR){
		Html tablehtml = new Html(tableSTR);
		List<String> trs = tablehtml.css("tr").all(); 
		return trs;
	}
}

