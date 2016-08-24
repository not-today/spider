package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.QygsQynb;
import com.heetian.spider.mysql.model.QygsQynbAssets;
import com.heetian.spider.mysql.model.QygsQynbBasic;
import com.heetian.spider.mysql.model.QygsQynbContribution;
import com.heetian.spider.mysql.model.QygsQynbEdit;
import com.heetian.spider.mysql.model.QygsQynbGua;
import com.heetian.spider.mysql.model.QygsQynbInvestment;
import com.heetian.spider.mysql.model.QygsQynbStock;
import com.heetian.spider.mysql.model.QygsQynbWebInfo;

/**
 * 企业公示信息页面
 * @author tst
 *
 */
public class QygsQynbDao extends Dao{
	private static Logger logger = LoggerFactory.getLogger(QygsQynbDao.class);
	public static void addQygsQynb(QygsQynb qygsqynb){
		insertQygsQynb(qygsqynb);
		
		QygsQynbBasic bs = qygsqynb.getBasic();
		if(bs!=null)
			insertQygsQynbBasic(bs);
		
		QygsQynbAssets at = qygsqynb.getAssets();
		if(at!=null)
			insertQygsQynbAssets(at);
		
		List<QygsQynbInvestment> invs = qygsqynb.getInvestments();
		if(invs!=null&&invs.size()>0)
			insertQygsQynbInvestment(invs);
		
		
		List<QygsQynbContribution> cbs = qygsqynb.getContributions();
		if(cbs!=null&&cbs.size()>0)
			insertQygsQynbContributions(cbs);
		
		List<QygsQynbEdit> edits = qygsqynb.getEdits();
		if(edits!=null&&edits.size()>0)
			insertQygsQynbEdits(edits);
		
		List<QygsQynbGua> guas = qygsqynb.getGuas();
		if(guas!=null&&guas.size()>0)
			insertQygsQynbGua(guas);
		
		List<QygsQynbStock> sks = qygsqynb.getStocks();
		if(sks!=null&&sks.size()>0)
			insertQygsQynbStocks(sks);
		
		List<QygsQynbWebInfo> wis = qygsqynb.getWebinfos();
		if(wis!=null&&wis.size()>0)
			insertQygsQynbWebInfos(wis);
	}
	/**
	 * 插入企业年报
	 * @param qygsqynb
	 * @return
	 */
	public static boolean insertQygsQynb(QygsQynb qygsqynb) {
		String sql = "insert into qygs_qynb(uuid,sub_date,pub_date) values(?,?,?)";
		Object[] params = {qygsqynb.getUuid(),qygsqynb.getSubDate(),qygsqynb.getPubDate()};
		try {
			queryRunner.update(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynb出错", e);
			return false;
		} 
	}
	/**
	 * 插入企业年报_企业基本信息
	 * @param bs
	 * @return
	 */
	public static boolean insertQygsQynbBasic(QygsQynbBasic bs) {
		String sql = "insert into qygs_qynb_basic("
				+ "reg,name,tel,zip_code,mailing_addr,"
				+ "email,make_over,status,have_web,invested,"
				+ "people_num,uuid"
				+ ") values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {bs.getReg(),bs.getName(),bs.getTel(),
				bs.getZipCode(),bs.getMailingAddr(),bs.getEmail(),
				bs.getMakeOver(),bs.getStatus(),bs.getHaveWeb(),
				bs.getInvested(),bs.getPeopleNum(),bs.getUuid()};
		try {
			queryRunner.update(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynbBasic出错", e);
			return false;
		} 
	}
	
	/**
	 * 企业年报_企业资产状况信息
	 * @param qqas
	 * @return
	 */
	public static boolean insertQygsQynbAssets(QygsQynbAssets qqa) {
		String sql = "insert into qygs_qynb_assets ("
				+ "assets_total,liabilities_total,profit_total,"
				+ "net_profit,owner_interest,business_total,"
				+ "business_main_total,taxes,uuid"
				+ ") values(?,?,?,?,?,?,?,?,?)";
		Object[] params = {qqa.getAssetsTotal(),qqa.getLiabilitiesTotal(),qqa.getProfitTotal(),
				qqa.getNetProfit(),qqa.getOwnerInterest(),qqa.getBusinessTotal(),
				qqa.getBusinessOfMainTotal(),qqa.getTaxes(),qqa.getUuid()};
		try {
			queryRunner.update(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynbAssets出错", e);
			return false;
		}
	}
	/**
	 * 插入企业年报_对外投资信息
	 * @param inv
	 * @return
	 */
	public static boolean insertQygsQynbInvestment(List<QygsQynbInvestment> invs) {
		String sql = "insert into qygs_qynb_investment(uuid,name,reg) values(?,?,?)";
		Object[][] params = new Object[invs.size()][8];
		for (int i = 0; i < invs.size(); i++) {
			QygsQynbInvestment inv = invs.get(i);
			params[i][0] = inv.getUuid();				
			params[i][1] = inv.getName();				
			params[i][2] = inv.getReg();				
		}
		if(params.length<=0)
			return true;
		try {
			queryRunner.batch(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynbContribution出错", e);
			return false;
		}
	}
	/**
	 * 企业年报_股东（发起人）及出资信息
	 * @param cbs
	 * @return
	 */
	public static boolean insertQygsQynbContributions(List<QygsQynbContribution> cbs) {
		String sql = "insert into qygs_qynb_assets ("
				+ "investor,subsribed_money,subsribed_date,"
				+ "subsribed_method,paid_money,contribution_date,"
				+ "contribution_method,uuid"
				+ ") values(?,?,?,?,?,?,?,?)";
		Object[][] params = new Object[cbs.size()][8];
		for (int i = 0; i < cbs.size(); i++) {
			QygsQynbContribution cb = cbs.get(i);
			params[i][0] = cb.getInvestor();				
			params[i][1] = cb.getSubsribedMoney();				
			params[i][2] = cb.getSubsribedDate();				
			params[i][3] = cb.getSubsribedMethod();				
			params[i][4] = cb.getPaidMoney();				
			params[i][5] = cb.getContributionDate();				
			params[i][6] = cb.getContributionMethod();				
			params[i][7] = cb.getUuid();				
		}
		if(params.length<=0)
			return true;
		try {
			queryRunner.batch(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynbContribution出错", e);
			return false;
		}
	}
	/**
	 * 企业年报_修改记录
	 * @param edits
	 * @return
	 */
	public static boolean insertQygsQynbEdits(List<QygsQynbEdit> edits) {
		String sql = "insert into qygs_qynb_assets ("
				+ "edit_content,edit_before,edit_after,edit_date,uuid"
				+ ") values(?,?,?,?,?)";
		Object[][] params = new Object[edits.size()][5];
		for (int i = 0; i < edits.size(); i++) {
			QygsQynbEdit edit = edits.get(i);
			params[i][0] = edit.getEditContent();				
			params[i][1] = edit.getEditBefore();				
			params[i][2] = edit.getEditAfter();				
			params[i][3] = edit.getEditDate();				
			params[i][4] = edit.getUuid();				
		}
		if(params.length<=0)
			return true;
		try {
			queryRunner.batch(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynbEdit出错", e);
			return false;
		}
	}
	/**
	 * 企业年报_对外提供保证担保信息
	 * @param guas
	 * @return
	 */
	public static boolean insertQygsQynbGua(List<QygsQynbGua> guas) {
		String sql = "insert into qygs_qynb_assets ("
				+ "creditor,obligor,type,money,perform_date,ensure_date,method,uuid"
				+ ") values(?,?,?,?,?,?,?,?)";
		Object[][] params = new Object[guas.size()][8];
		for (int i = 0; i < guas.size(); i++) {
			QygsQynbGua gua = guas.get(i);
			params[i][0] = gua.getCreditor();				
			params[i][1] = gua.getObligor();				
			params[i][2] = gua.getType();				
			params[i][3] = gua.getMoney();				
			params[i][4] = gua.getPerformDate();				
			params[i][5] = gua.getEnsureDate();				
			params[i][6] = gua.getMethod();				
			params[i][7] = gua.getUuid();				
		}
		if(params.length<=0)
			return true;
		try {
			queryRunner.batch(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynbGua出错", e);
			return false;
		}
	}
	/**
	 * 企业年报_股权变更信息
	 * @param sks
	 * @return
	 */
	public static boolean insertQygsQynbStocks(List<QygsQynbStock> sks) {
		String sql = "insert into qygs_qynb_assets ("
				+ "investor,before_proportion,after_proportion,date,uuid"
				+ ") values(?,?,?,?,?)";
		Object[][] params = new Object[sks.size()][5];
		for (int i = 0; i < sks.size(); i++) {
			QygsQynbStock sk = sks.get(i);
			params[i][0] = sk.getInvestor();				
			params[i][1] = sk.getBeforeProportion();				
			params[i][2] = sk.getAfterProportion();				
			params[i][3] = sk.getDate();				
			params[i][4] = sk.getUuid();				
		}
		if(params.length<=0)
			return true;
		try {
			queryRunner.batch(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynbStock出错", e);
			return false;
		}
	}
	/**
	 * 企业年报_网站或网店信息
	 * @param wis
	 * @return
	 */
	public static boolean insertQygsQynbWebInfos(List<QygsQynbWebInfo> wis) {
		String sql = "insert into qygs_qynb_assets ("
				+ "type,name,website,uuid ) values(?,?,?,?)";
		Object[][] params = new Object[wis.size()][4];
		for (int i = 0; i < wis.size(); i++) {
			QygsQynbWebInfo wi = wis.get(i);
			params[i][0] = wi.getType();				
			params[i][1] = wi.getName();				
			params[i][2] = wi.getWebsite();				
			params[i][3] = wi.getUuid();				
		}
		if(params.length<=0)
			return true;
		try {
			queryRunner.batch(sql, params);
			return true;
		} catch (SQLException e) {
			logger.error("批量插入QygsQynbWebInfo出错", e);
			return false;
		}
	}
}
