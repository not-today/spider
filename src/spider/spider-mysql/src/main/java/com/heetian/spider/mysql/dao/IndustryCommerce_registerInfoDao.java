package com.heetian.spider.mysql.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heetian.spider.mysql.model.IndustryCommerce_registerInfo;

/**
 * <p>
 * Title: IndustryCommerce_registerInfoDao.java
 * </p>
 * <p>
 * Description: 工商公示信息_登记信息表表数据库操作
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Mon Apr 13 10:28:18 CST 2015
 */
public class IndustryCommerce_registerInfoDao extends Dao{

	/** 日志指针 */
	private static Logger logger = LoggerFactory.getLogger(IndustryCommerce_registerInfoDao.class);

	/***
	 * 查询指定条数的industryCommerce_registerInfo集合
	 * 
	 * @param number
	 *            条数
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_registerInfo(int number) {
		List<Object[]> arraylist = null;
		String sql = "select * from industryCommerce_registerInfo  limit " + number;
		try {
			logger.info("本次执行sql："+sql);
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_registerInfo", e);
		}
		return arraylist;
	}
	public static List<Object[]> searchIndustryCommerce_registerInfo(String sql) {
		List<Object[]> arraylist = null;
		try {
			arraylist = queryRunner.query(sql, new ArrayListHandler());
		} catch (SQLException e) {
			logger.error("查询industryCommerce_registerInfo", e);
		}
		return arraylist;
	}
	/***
	 * 更新industryCommerce_registerInfo
	 * 
	 * @param jbxx	 * @return
	 */
	public static int updateIndustryCommerce_registerInfo(IndustryCommerce_registerInfo jbxx) {
		int result = 0;
		StringBuilder sql = new StringBuilder("update  industryCommerce_registerInfo set regNumber=?,name=?,"
				+ "type=?,legalPerson=?,address=?,registeredCapital=?,establishDate=?,datebegin=?,dateend=?,"
				+ "ranges=?,registerOrgan=?,approvalDate=?,regStatus=?,manager=?,managerPlace=?,Form=?,regDate=?,"
				+ "revokeDate=?,businessScope=?,totalAmount=?,investor=?,busDatebegin=?,busDateend=?,chargePerson=?,"
				+ "busPlace=?,managingPartner=?,partnerDatebegin=?,partnerDateend=?,mainManagerplace=?,url=?,lastUpdatetime=? where regNumber=? ");
		Object[] params = { jbxx.getRegNumber(),jbxx.getName(),jbxx.getType(),jbxx.getLegalPerson(),jbxx.getAddress(),
				jbxx.getRegisteredCapital(),jbxx.getEstablishDate(),jbxx.getDatebegin(),jbxx.getDateend(),jbxx.getRanges(),
				jbxx.getRegisterOrgan(),jbxx.getApprovalDate(),jbxx.getRegStatus(),jbxx.getManager(),jbxx.getManagerPlace(),
				jbxx.getForm(),jbxx.getRegDate(),jbxx.getRevokeDate(),jbxx.getBusinessScope(),jbxx.getTotalAmount(),
				jbxx.getInvestor(),jbxx.getBusDatebegin(),jbxx.getBusDateend(),jbxx.getChargePerson(),jbxx.getBusPlace(),
				jbxx.getManagingPartner(),jbxx.getPartnerDatebegin(),jbxx.getPartnerDateend(),jbxx.getMainManagerplace(),
				jbxx.getUrl(),jbxx.getLastUpdatetime(),jbxx.getRegNumber()};
		try {
			logger.info("本次执行sql："+sql.toString());
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			logger.error("更新industryCommerce_registerInfo", e);
		}
		return result;
	}

	/***
	 * 向industryCommerce_registerInfo插入数据
	 * 
	 * @param jbxx	 * @return
	 */
	public static int insertIndustryCommerce_registerInfo(IndustryCommerce_registerInfo jbxx) {
		int result = 0;
		String sql = "insert into industryCommerce_registerInfo (regNumber,name,type,legalPerson,address,"
				+ "registeredCapital,establishDate,datebegin,dateend,ranges,registerOrgan,approvalDate,"
				+ "regStatus,manager,managerPlace,Form,regDate,revokeDate,businessScope,totalAmount,investor,"
				+ "busDatebegin,busDateend,chargePerson,busPlace,managingPartner,partnerDatebegin,partnerDateend,"
				+ "mainManagerplace,lastUpdatetime,url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {jbxx.getRegNumber(),jbxx.getName(),jbxx.getType(),jbxx.getLegalPerson(),jbxx.getAddress(),
				jbxx.getRegisteredCapital(),jbxx.getEstablishDate(),jbxx.getDatebegin(),jbxx.getDateend(),
				jbxx.getRanges(),jbxx.getRegisterOrgan(),jbxx.getApprovalDate(),jbxx.getRegStatus(),
				jbxx.getManager(),jbxx.getManagerPlace(),jbxx.getForm(),jbxx.getRegDate(),jbxx.getRevokeDate(),
				jbxx.getBusinessScope(),jbxx.getTotalAmount(),jbxx.getInvestor(),jbxx.getBusDatebegin(),jbxx.getBusDateend(),
				jbxx.getChargePerson(),jbxx.getBusPlace(),jbxx.getManagingPartner(),jbxx.getPartnerDatebegin(),
				jbxx.getPartnerDateend(),jbxx.getMainManagerplace(),jbxx.getLastUpdatetime(),jbxx.getUrl()};
		try {
			//logger.info("本次执行sql："+sql);
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			logger.error("插入industryCommerce_registerInfo", e);
		} 
		return result;
	}

	/**
	 * 根据企业注册号查询industryCommerce_registerInfo数据库表
	 * 
	 * @param regNumber
	 * @return
	 */
	public static List<Object[]> searchIndustryCommerce_registerInfoByRegNumber(String regNumber) {
		try {
			StringBuilder sql = new StringBuilder(
					"select id,regNumber,name,type,legalPerson,address,registeredCapital,establishDate,datebegin,"
					+ "dateend,ranges,registerOrgan,approvalDate,regStatus,manager,managerPlace,Form,regDate,"
					+ "revokeDate,businessScope,totalAmount,investor,busDatebegin,busDateend,chargePerson,busPlace,"
					+ "managingPartner,partnerDatebegin,partnerDateend,mainManagerplace,url,lastUpdatetime from "
					+ "industryCommerce_registerInfo where regNumber='").append(regNumber)
					.append("'");
			//logger.info("本次执行sql："+sql);
			List<Object[]> arraylist = queryRunner.query(sql.toString(), new ArrayListHandler());
			return arraylist;
		} catch (SQLException e) {
			logger.error("查询industryCommerce_registerInfo", e);
		} 
		return null;
	}
	/**
	 * 根据企业注册号删除
	 * @param regNumber
	 * @return
	 */
	public static void deleteIndustryCommerce_registerInfoByRegNumber(String regNumber) {
		try {
			String sql = "delete from industryCommerce_registerInfo where regNumber='"+regNumber+"'";
			queryRunner.update(sql);
		} catch (SQLException e) {
			logger.error("删除industryCommerce_branchInfo根据注册号", e);
		} 
	}
	/***
	 * 代理表中批量插入数据
	 * 
	 * @param companys
	 * @return
	 */
	public static int[] batchIndustryCommerce_registerInfo(List<IndustryCommerce_registerInfo> industryCommerce_registerInfoList) {
		int result[] = {};
		String sql = "insert into industryCommerce_registerInfo (regNumber,name,type,legalPerson,address,"
				+ "registeredCapital,establishDate,datebegin,dateend,ranges,registerOrgan,approvalDate,"
				+ "regStatus,manager,managerPlace,Form,regDate,revokeDate,businessScope,totalAmount,"
				+ "investor,busDatebegin,busDateend,chargePerson,busPlace,managingPartner,partnerDatebegin,"
				+ "partnerDateend,mainManagerplace,lastUpdatetime,url) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[][] params = new Object[industryCommerce_registerInfoList.size()][31];
		if (industryCommerce_registerInfoList != null && industryCommerce_registerInfoList.size() > 0) {
			for (int i = 0; i < industryCommerce_registerInfoList.size(); i++) {
				IndustryCommerce_registerInfo jbxx = industryCommerce_registerInfoList.get(i);
				 params[i][0] = jbxx.getRegNumber();				
				 params[i][1] = jbxx.getName();				
				 params[i][2] = jbxx.getType();				
				 params[i][3] = jbxx.getLegalPerson();				
				 params[i][4] = jbxx.getAddress();				
				 params[i][5] = jbxx.getRegisteredCapital();				
				 params[i][6] = jbxx.getEstablishDate();				
				 params[i][7] = jbxx.getDatebegin();				
				 params[i][8] = jbxx.getDateend();				
				 params[i][9] = jbxx.getRanges();				
				 params[i][10] = jbxx.getRegisterOrgan();				
				 params[i][11] = jbxx.getApprovalDate();				
				 params[i][12] = jbxx.getRegStatus();				
				 params[i][13] = jbxx.getManager();				
				 params[i][14] = jbxx.getManagerPlace();				
				 params[i][15] = jbxx.getForm();				
				 params[i][16] = jbxx.getRegDate();				
				 params[i][17] = jbxx.getRevokeDate();				
				 params[i][18] = jbxx.getBusinessScope();				
				 params[i][19] = jbxx.getTotalAmount();				
				 params[i][20] = jbxx.getInvestor();				
				 params[i][21] = jbxx.getBusDatebegin();				
				 params[i][22] = jbxx.getBusDateend();				
				 params[i][23] = jbxx.getChargePerson();				
				 params[i][24] = jbxx.getBusPlace();				
				 params[i][25] = jbxx.getManagingPartner();				
				 params[i][26] = jbxx.getPartnerDatebegin();				
				 params[i][27] = jbxx.getPartnerDateend();				
				 params[i][28] = jbxx.getMainManagerplace();	
				 params[i][29] = jbxx.getLastUpdatetime();	
				 params[i][30] = jbxx.getUrl();
			}
		}
		try {
			result = queryRunner.batch(sql, params);
		} catch (SQLException e) {
			logger.error("mysql批量插入industryCommerce_registerInfo", e);
		}
		return result;
	}
}
