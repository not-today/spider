package com.heetian.spider.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.process.abstractclass.ProcessHandlePrepare;
//saveBeanToPipeLine(page.getResultItems(),BeanTypeEnum.GDXX,
//saveBeanToPipeLine(page.getResultItems(),BeanTypeEnum.BGXX,
//saveBeanToPipeLine(page.getResultItems(),BeanTypeEnum.ZYRYXX,
//saveBeanToPipeLine(page.getResultItems(),BeanTypeEnum.FZJGXX,

public class AnalysisForTable {
	/**
	 * 用来构建单个tr的html对象，开始标签
	 */
	public static final String beforeTable = "<table>";
	/**
	 * 用来构建单个tr的html对象，结束标签
	 */
	public static final String afterTable = "</table>";
	/**
	 * 用来构建单个td的html对象，开始标签
	 */
	public static final String beforeTableTR = "<table><tr>";
	/**
	 * 用来构建单个td的html对象，开始标签
	 */
	public static final String afterTableTR = "</tr></table>";
	/**
	 * 获取td构建成的html的td内容的xpath语句
	 */
	public static final String tdXpathForContent = "//td/allText()";
	/**
	 * 获取tr构建成的html的所有td字符串xpath语句
	 */
	public static final String trXpathForTds = "//td";
	private static Logger logger = LoggerFactory.getLogger(AnalysisForTable.class);
	/**
	 * 针对所有省份的html 的table 形式的基本信息处理  
	 * @param regNumber 注册号 
	 * @param entName 企业名称
	 * @param trs  含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param tst 
	 * @return
	 */
	public static GsgsRegister jbxxHTMLProcessToJAVAObject(String regNumber, String entName,List<String> trs, TSTPageProcessor tst) {
		if(trs==null||trs.size()<=0){
			logger.error("!!!!!!!企业["+entName+"]("+regNumber+")获取基本信息table为空,请查看");
			return null;
		}
		GsgsRegister jbxxBean = new GsgsRegister(regNumber);
		jbxxBean.setUpt(System.currentTimeMillis());
		jbxxBean.setPvn(tst.getcode());
		for(String tr:trs){
			if(tr.contains("基本信息"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0){
				continue;
			}
			if(tds.size()==4){
				toRegBean(jbxxBean,getTdText(tds.get(0)), getTdText(tds.get(1)));
				toRegBean(jbxxBean,getTdText(tds.get(2)), getTdText(tds.get(3)));
			}else if(tds.size()==3||tds.size()==2){
				toRegBean(jbxxBean,getTdText(tds.get(0)), getTdText(tds.get(1)));
			}else{
				logger.error("基本信息tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		if(regNumber!=null&&!"".equals(regNumber))
			jbxxBean.setRgc(regNumber);
		if(entName!=null&&!"".equals(entName))
			jbxxBean.setName(entName);
		regNumber = jbxxBean.getRgc();
		if(regNumber==null||"".equals(regNumber.replaceAll("\\s", "")))
			return null;
		return jbxxBean;
	}
	/**
	 * 针对所有省份的html 的table 形式的股东信息处理     针对辽宁类型
	 * @param trs  含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param regNumber 注册号
	 * @param entName  企业名称
	 * @param site
	 * @param page
	 */
	public static List<GsgsShareholder> gdxxHTMLToJObj(ProcessHandlePrepare process,List<String> trs,String regNumber,String entName,Site site,Page page) {
		if(trs==null||trs.size()<=0)
			return null;
		List<GsgsShareholder> holders = new ArrayList<GsgsShareholder>();
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1)
				continue;
			if(tds.size()==4){
				holders.add(createShareHolder(regNumber, getTdText(tds.get(0)), getTdText(tds.get(1)), getTdText(tds.get(2)), getTdText(tds.get(3)), null));
			}else if(tds.size()==5){
				String uuid = TSTUtils.uuid();
				process.createRequestForGdxxxx(regNumber, entName, site,uuid, page, tds.get(4));
				holders.add(createShareHolder(regNumber, getTdText(tds.get(0)), getTdText(tds.get(1)), getTdText(tds.get(2)), getTdText(tds.get(3)), uuid));
			}else if(tds.size()==2){
				logger.info("warning:股东信息tr[2已经存入库中] "+tr);
				holders.add(createShareHolder(regNumber, getTdText(tds.get(0)),getTdText(tds.get(1))));
			}else{
				//判断是否有其他情况
				logger.error("股东信息tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		if(holders.size()<=0)
			return null;
		return holders;
	}
	/**
	 * 针对所有省份的html 的table 形式的股东信息处理    针对四川类型
	 * @param trs  含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param regNumber 注册号
	 * @param entName  企业名称
	 * @param site
	 * @param page
	 */
	public static List<GsgsShareholder> gdxxHTMLToJObjSC(ProcessHandlePrepare process,List<String> trs,String regNumber,String entName,Site site,Page page) {
		if(trs==null||trs.size()<=0)
			return null;
		List<GsgsShareholder> holders = new ArrayList<GsgsShareholder>();
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1)
				continue;
			if(tds.size()==4){
				holders.add(createShareHolder(regNumber, getTdText(tds.get(3)), getTdText(tds.get(0)), getTdText(tds.get(1)), getTdText(tds.get(2)), null));
			}else if(tds.size()==5){
				String uuid = TSTUtils.uuid();
				process.createRequestForGdxxxx(regNumber, entName, site,uuid, page, tds.get(4));
				holders.add(createShareHolder(regNumber, getTdText(tds.get(3)), getTdText(tds.get(0)), getTdText(tds.get(1)), getTdText(tds.get(2)), uuid));
			}else if(tds.size()==2){
				logger.info("warning:股东信息tr[2已经存入库中] "+tr);
				holders.add(createShareHolder(regNumber, getTdText(tds.get(0)),getTdText(tds.get(1))));
			}else{
				//判断是否有其他情况
				logger.error("股东信息tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		if(holders.size()<=0)
			return null;
		return holders;
	}
	/**
	 * 针对所有省份的html 的table 形式的股东信息处理    针对主管部门（出资人）信息
	 * @param trs  含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param regNumber 注册号
	 * @param entName  企业名称
	 * @param site
	 * @param page
	 */
	public static List<GsgsShareholder> gdxxHTMLToJObjZGBM(ProcessHandlePrepare process,List<String> trs,String regNumber,String entName,Site site,Page page) {
		if(trs==null||trs.size()<=0)
			return null;
		List<GsgsShareholder> holders = new ArrayList<GsgsShareholder>();
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1)
				continue;
			if(tds.size()==5){
				holders.add(createShareHolder(regNumber, getTdText(tds.get(1)), getTdText(tds.get(2)), getTdText(tds.get(3)), getTdText(tds.get(4)), null));
			}else{
				//判断是否有其他情况
				logger.error("股东信息tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		if(holders.size()<=0)
			return null;
		return holders;
	}
	/**
	 * 针对所有省份的html 的table 形式的变更信息处理 
	 * @param trs  含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param regNumber
	 * @param page
	 */
	public static List<GsgsChange> bgxxHTMLProcessToJAVAObject(List<String> trs,String regNumber, Page page) {
		if(trs==null||trs.size()<=0){
			return null;
		}
		List<GsgsChange> chs = new ArrayList<GsgsChange>();
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1){
				continue;
			}
			if(tds.size()==4){
				chs.add(createChangeInfo(regNumber, getTdText(tds.get(0)), getTdText(tds.get(1)), getTdText(tds.get(2)), getTdText(tds.get(3))));
			}else if(tds.size()==5){
				chs.add(createChangeInfo(regNumber, getTdText(tds.get(1)), getTdText(tds.get(2)), getTdText(tds.get(3)), getTdText(tds.get(4))));
			}else{
				//判断是否有其他情况
				logger.error("变更信息tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		if(chs.size()<=0)
			return null;
		return chs;
	}
	/**
	 * 针对所有省份的html 的table 形式的主要人员信息处理 
	 * @param trs  含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param regNumber
	 * @param page
	 */
	public static List<GsgsMainPersonel> zyryxxHTMLProcessToJAVAObject(List<String> trs,String regNumber, Page page) {
		if(trs==null||trs.size()<=0){
			return null;
		}
		List<GsgsMainPersonel> mns = new ArrayList<GsgsMainPersonel>();
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1){
				continue;
			}
			if(tds.size()==6){
				String name1 = getTdText(tds.get(1));
				String value1 = getTdText(tds.get(2));
				if(!"".equals(name1.trim())){
					mns.add(createMainPerson(regNumber, name1, value1));
				}
				String name2 = getTdText(tds.get(4));
				String value2 = getTdText(tds.get(5));
				if(!"".equals(name2.trim())){
					mns.add(createMainPerson(regNumber, name2, value2));
				}
			}else if(tds.size()==4||tds.size()==3){
				String name1 = getTdText(tds.get(1));
				String value1 = getTdText(tds.get(2));
				if(!"".equals(name1.trim())){
					mns.add(createMainPerson(regNumber, name1, value1));
				}
			}else{
				//判断是否有其他情况
				logger.error("主要人员tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		
		if(mns.size()<=0)
			return null;
		return mns;
	}
	public static List<GsgsMainPersonel> jtcyxxHTMLProcessToJAVAObject(List<String> trs,String regNumber, Page page) {
		if(trs==null||trs.size()<=0){
			return null;
		}
		List<GsgsMainPersonel> mns = new ArrayList<GsgsMainPersonel>();
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1){
				continue;
			}
			if(tds.size()==4){
				String name1 = getTdText(tds.get(1));
				if(!"".equals(name1.trim())){
					mns.add(createMainPerson(regNumber, name1, null));
				}
				String name2 = getTdText(tds.get(3));
				if(!"".equals(name2.trim())){
					mns.add(createMainPerson(regNumber, name2, null));
				}
			}else{
				//判断是否有其他情况
				logger.error("家庭成员tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		
		if(mns.size()<=0)
			return null;
		return mns;
	}
	/**
	 * 针对所有省份的html 的table 形式的分支机构信息处理 
	 * @param trs  含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param regNumber
	 * @param page
	 */
	public static List<GsgsBranch> fzjgxxHTMLProcessToJAVAObject(List<String> trs,String regNumber, Page page) {
		if(trs==null||trs.size()<=0){
			return null;
		}
		List<GsgsBranch> brans = new ArrayList<GsgsBranch>();
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1){
				continue;
			}
			if(tds.size()==4){
				brans.add(createBranch(regNumber, getTdText(tds.get(1)), getTdText(tds.get(2)), getTdText(tds.get(3))));
			}else{
				//判断是否有其他情况
				logger.error("分支信息tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		if(brans.size()<=0)
			return null;
		return brans;
	}
	/**
	 * 针对所有省份的html 的table 形式的股东详情信息处理 
	 * @param trs 含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param page
	 * @param regNumber
	 */
	public static List<GsgsShareholderDetail> gdxxxxHTMLProcessToJAVAObject(List<String> trs,String uuid, String regNumber) {
		if(trs==null||trs.size()<=0){
			return null;
		}
		List<GsgsShareholderDetail> gdxxs = new ArrayList<GsgsShareholderDetail>();
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1){
				continue;
			}
			if(tds.size()==9){
				GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
				gdxx.setUuid(uuid);
				gdxx.setRegNumber(regNumber);
				gdxx.setName(getTdText(tds.get(0)));
				gdxx.setStmn(getTdText(tds.get(1)));
				gdxx.setPtmn(getTdText(tds.get(2)));
				
				gdxx.setSfrm(getTdText(tds.get(3)));
				gdxx.setSmn(getTdText(tds.get(4)));
				gdxx.setSd(getTdText(tds.get(5)));
				gdxx.setPfrm(getTdText(tds.get(6)));
				gdxx.setPmn(getTdText(tds.get(7)));
				gdxx.setPd(getTdText(tds.get(8)));
				gdxxs.add(gdxx);
			}else if(tds.size()==8){
				GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
				gdxx.setUuid(uuid);
				gdxx.setRegNumber(regNumber);
				gdxx.setName(getTdText(tds.get(0)));
				gdxx.setInvType(getTdText(tds.get(1)));
				
				gdxx.setSfrm(getTdText(tds.get(2)));
				gdxx.setSmn(getTdText(tds.get(3)));
				gdxx.setSd(getTdText(tds.get(4)));
				gdxx.setPfrm(getTdText(tds.get(5)));
				gdxx.setPmn(getTdText(tds.get(6)));
				gdxx.setPd(getTdText(tds.get(7)));
				gdxxs.add(gdxx);
			}else if(tds.size()==3){
				GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
				gdxx.setUuid(uuid);
				gdxx.setRegNumber(regNumber);
				gdxx.setPfrm(getTdText(tds.get(0)));
				gdxx.setPmn(getTdText(tds.get(1)));
				gdxx.setPd(getTdText(tds.get(2)));
				gdxxs.add(gdxx);
			}else{
				//判断是否有其他情况
				logger.error("股东详情信息tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		if(gdxxs.size()<=0)
			gdxxs = null;
		return gdxxs;
	}
	public static List<GsgsShareholderDetail> gdxxxxHTMLProcessToJAVAObject_forSiChuna(List<String> trs,Page page,String uuid, String regNumber) {
		if(trs==null||trs.size()<=0){
			return null;
		}
		List<GsgsShareholderDetail> gdxxs = new ArrayList<GsgsShareholderDetail>();
		for(String tr:trs){
			List<String> tds = AnalysisForTable.getTrText(tr);
			if(tds.size()==0){
				continue;
			}
			if(tds.size()==9){
				List<String> td3s = getUL(tds.get(3));
				List<String> td4s = getUL(tds.get(4));
				List<String> td5s = getUL(tds.get(5));
				List<String> td6s = getUL(tds.get(6));
				List<String> td7s = getUL(tds.get(7));
				List<String> td8s = getUL(tds.get(8));
				if(td3s.size()>0){
					for(int x=0;x<td3s.size();x++){
						try {
							GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
							gdxx.setUuid(uuid);
							gdxx.setRegNumber(regNumber);
							gdxx.setName(AnalysisForTable.getTdText(tds.get(0)));
							gdxx.setStmn(AnalysisForTable.getTdText(tds.get(1)));
							gdxx.setPtmn(AnalysisForTable.getTdText(tds.get(2)));
							gdxx.setSfrm(td3s.get(x));
							gdxx.setSmn(td4s.get(x));
							gdxx.setSd(td5s.get(x));
							gdxx.setPfrm(td6s.get(x));
							gdxx.setPmn(td7s.get(x));
							gdxx.setPd(td8s.get(x));
							gdxxs.add(gdxx);
						} catch (Exception e) {
							continue;
						}
					}
					
				}else{
					GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
					gdxx.setUuid(uuid);
					gdxx.setRegNumber(regNumber);
					gdxx.setName(AnalysisForTable.getTdText(tds.get(0)));
					gdxx.setStmn(AnalysisForTable.getTdText(tds.get(1)));
					gdxx.setPtmn(AnalysisForTable.getTdText(tds.get(2)));
					gdxxs.add(gdxx);
				}
			}else{
				//判断是否有其他情况
				logger.error("股东详情信息tr中的td数量["+tds.size()+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		return gdxxs;
	}
	private static List<String> getUL(String str){
		return new Html(str).xpath("//ul/li/allText()").all();
	}
	/**
	 * 针对所有省份的html 的table 形式的股东详情信息处理 
	 * @param trs 含有数据的table的tr元素集合（没有数据的要先进行处理,去除）
	 * @param page
	 * @param regNumber
	 */
	public static List<GsgsShareholderDetail> gdxxxxHTMLProcessToJAVAObjectForHubei(List<String> trs,String uuid, String regNumber) {
		if(trs==null||trs.size()<=0){
			return null;
		}
		List<GsgsShareholderDetail> gdxxs = new ArrayList<GsgsShareholderDetail>();
		String tmp = "";
		for(String tr:trs){
			if(tr.contains("暂无数据"))
				continue;
			List<String> tds = getTrText(tr);
			if(tds.size()==0||tds.size()==1){
				continue;
			}
			if(tds.size()==9){
				String inv = getTdText(tds.get(0));
				tmp = inv;
				GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
				gdxx.setUuid(uuid);
				gdxx.setRegNumber(regNumber);
				gdxx.setName(getTdText(tds.get(0)));
				gdxx.setStmn(getTdText(tds.get(1)));
				gdxx.setPtmn(getTdText(tds.get(2)));
				gdxx.setSfrm(getTdText(tds.get(3)));
				gdxx.setSmn(getTdText(tds.get(4)));
				gdxx.setSd(getTdText(tds.get(5)));
				gdxx.setPfrm(getTdText(tds.get(6)));
				gdxx.setPmn(getTdText(tds.get(7)));
				gdxx.setPd(getTdText(tds.get(8)));
				gdxxs.add(gdxx);
			}else if(tds.size()==3){
				GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
				gdxx.setUuid(uuid);
				gdxx.setRegNumber(regNumber);
				gdxx.setName(tmp);
				gdxx.setPfrm(getTdText(tds.get(0)));
				gdxx.setPmn(getTdText(tds.get(1)));
				gdxx.setPd(getTdText(tds.get(2)));
				gdxxs.add(gdxx);
			}else{
				//判断是否有其他情况
				logger.error("股东详情信息tr中的td数量["+tds.size()+":"+regNumber+"]没有对应的处理办法，而此条tr数据没有入库：["+regNumber+"]"+tds.toString());
			}
		}
		if(gdxxs.size()<=0)
			gdxxs = null;
		return gdxxs;
	}
	public static List<GsgsShareholderDetail> gdxxxxHTMLProcessToJAVAObjectForAdministrator(String regNumber, String rawText, String uuid) {
		String  capregex="(investor.invName = \"){1}(.*?)(\";){1}";
		String paidregex="(invtActl.acConAm = \"){1}(.*?)(\";){1}[\\s]*(invtActl.conDate = \'){1}(.*?)(\';){1}[\\s]*(invtActl.conForm = \"){1}(.*?)(\";){1}";
		String subregex="(invt.subConAm = \"){1}(.*?)(\";){1}[\\s]*(invt.conDate = \'){1}(.*?)(\';){1}[\\s]*(invt.conForm = \"){1}(.*?)(\";){1}";
		Pattern cappattern=Pattern.compile(capregex);
		Pattern paidpattern=Pattern.compile(paidregex);
		Pattern subpattern=Pattern.compile(subregex);
		//获取当前股东的名字
		Matcher capMatcher = cappattern.matcher(rawText);
		String holderName="";
		while(capMatcher.find()) {
			String name=capMatcher.group(2).trim();
			// 去除掉括号里面的内容
			String rep = "(\\({1}.*\\){1})|(\\[{1}.*\\]{1})|(\\{{1}.*\\}{1})|(\\〔{1}.*\\〕{1})|(\\（{1}.*\\）{1})";
			String repName = name.replaceAll(rep, "");
			Pattern p1 = Pattern.compile("([\u4e00-\u9fa5|a-z|A-Z|0-9]{1}.*[\u4e00-\u9fa5|a-z|A-Z|0-9]{1})");
			Matcher matcher = p1.matcher(repName);
			if (matcher.find()) {
				String findStr = matcher.group(0);
				repName = findStr;
			}
			holderName=repName;
		}
		List<GsgsShareholderDetail> gdxxs = new ArrayList<GsgsShareholderDetail>();
		//获取实缴记录
		Matcher paidMatcher=paidpattern.matcher(rawText);
		double paidtotal = 0;
		while(paidMatcher.find()) {
			GsgsShareholderDetail gdxx = new GsgsShareholderDetail();
			gdxx.setPfrm(paidMatcher.group(8));
			gdxx.setPmn(paidMatcher.group(2));
			gdxx.setPd(paidMatcher.group(5));
			gdxxs.add(gdxx);
			paidtotal=paidtotal+countMoney(gdxx.getPmn());
		}
		//获取认缴记录
		Matcher subMather=subpattern.matcher(rawText);
		List<String[]> tmp = new ArrayList<String[]>();
		double subtotal=0;
		while(subMather.find()) {
			String[] subtmp = new String[3];
			subtmp[0] = subMather.group(8);
			subtmp[1] = subMather.group(2);
			subtmp[2] = subMather.group(5);
			tmp.add(subtmp);
			subtotal = subtotal+countMoney(subtmp[1]);
		}
		int index = gdxxs.size()>=tmp.size()?gdxxs.size():tmp.size();
		for(int x=0;x<index;x++){
			GsgsShareholderDetail gdxx = null;
			String[] subtmp = null;
			if(x<gdxxs.size()){
				gdxx = gdxxs.get(x);
			}else{
				gdxx = new GsgsShareholderDetail();
			}
			if(x<tmp.size()){
				subtmp = tmp.get(x);
			}
			gdxx.setRegNumber(regNumber);
			gdxx.setUuid(uuid);
			gdxx.setName(holderName);
			gdxx.setStmn(String.valueOf(subtotal));
			gdxx.setPtmn(String.valueOf(paidtotal));
			if(subtmp!=null){
				gdxx.setSfrm(subtmp[0]);
				gdxx.setSmn(subtmp[1]);
				gdxx.setSd(subtmp[2]);
			}
		}
		return gdxxs;
	}
	private static double countMoney(String moneyStr) {
		String money = moneyStr!=null?moneyStr.replaceAll("\\s", ""):"0";
		try {
			return Double.parseDouble(money);
		} catch (Exception e) {
			return 0;
		}
	}
	public static GsgsMainPersonel createMainPerson(String regNumber,String name,String position){
		GsgsMainPersonel tmp = new GsgsMainPersonel();
		tmp.setRegNumber(regNumber);
		tmp.setName(name);
		tmp.setPos(position);
		return tmp;
	}
	public static List<String> getTrText(String tr) {
		if(tr.matches("[\\w\\W]*<\\s*th[\\w\\W]*>[\\w\\W]*")){
			tr = tr.replaceAll("<\\s*th[\\w\\W]*?>", "<td>");
		}
		if(tr.matches("[\\w\\W]*<\\s*/\\s*th\\s*>[\\w\\W]*")){
			tr = tr.replaceAll("<\\s*/\\s*th\\s*>", "</td>");
		}
		List<String> tds = new Html(beforeTable+tr+afterTable).xpath(trXpathForTds).all();
		if(tds==null)
			return new ArrayList<String>();
		return tds;
	}
	/**
	 * 创建一个变更bean
	 * @param regNumber 注册号
	 * @param type 事项
	 * @param before 变更前内容
	 * @param after 变更后内容
	 * @param date 变更日期
	 * @return
	 */
	public static GsgsChange createChangeInfo(String regNumber,String type,String before,String after,String date){
		GsgsChange tmp = new GsgsChange();
		tmp.setRegNumber(regNumber);
		tmp.setType(type);
		tmp.setChs(before);
		tmp.setChn(after);
		tmp.setDate(date);
		return tmp;
	}
	/**
	 * 创建一个股东bean
	 * @param regNumber 注册号
	 * @param type 股东类型
	 * @param name 股东名字
	 * @param cardType card类型
	 * @param cardID  card编号
	 * @param uuid  股东详情标志
	 * @return
	 */
	public static GsgsShareholder createShareHolder(String regNumber,String type,String name,String cardType,String cardID,String uuid){
		GsgsShareholder tmp = new GsgsShareholder();
		tmp.setRegNumber(regNumber);
		tmp.setType(type); 
		tmp.setName(name);
		tmp.setCrdt(cardType);
		tmp.setCrdc(cardID);
		tmp.setUuid(uuid);
		return tmp;
	}
	/**
	 * 
	 * @param regNumber  注册号
	 * @param name  股东名字
	 * @param ivMethod  投资方式
	 * @return
	 */
	public static GsgsShareholder createShareHolder(String regNumber,String name,String ivMethod){
		GsgsShareholder bean = new GsgsShareholder();
		bean.setRegNumber(regNumber);
		bean.setName(name);
		bean.setIvtf(ivMethod);
		return bean;
	}
	public static GsgsBranch createBranch(String regNumber,String rgc,String name,String gov){
		GsgsBranch branchInfo=new GsgsBranch();
		branchInfo.setRegNumber(regNumber);
		branchInfo.setRgc(rgc);
		branchInfo.setName(name);
		branchInfo.setGov(gov);
		return branchInfo;
	}
	/**
	 * 将td标签内的内容获取
	 * @param td
	 * @return
	 */
	public static String getTdText(String td) {
		String content = new Html(beforeTableTR+td+afterTableTR).xpath(tdXpathForContent).get();
		if(content==null){
			content = "";
		}
		return content;
	}
	private static void toRegBean(GsgsRegister gsgsRegister,String key,String value) {
		if(key==null||"".equals(key.trim()))
			return;
		if (key.contains("注册号")||key.contains("统一社会信用代码")){
			gsgsRegister.setRgc(value);
			if(CheckUtils.checkCreditCode(value))
				gsgsRegister.setNsc(value);
		}else if (key.contains("名称")||key.contains("事务所名称"))
			gsgsRegister.setName(value);
		else if (key.contains("类型"))
			gsgsRegister.setType(value);
		else if (key.contains("法定代表人")||key.contains("主任姓名")||key.contains("首席代表"))
			gsgsRegister.setLp(value);
		else if (key.contains("注册资本"))
			gsgsRegister.setRc(value);
		else if (key.contains("成员出资总额"))
			gsgsRegister.setTmn(value);
		else if (key.contains("成立日期"))
			gsgsRegister.setFdd(value);
		else if (key.contains("住所"))
			gsgsRegister.setAddr(value);
		else if (key.contains("营业期限自"))
			gsgsRegister.setMds(value);
		else if (key.contains("营业期限至"))
			gsgsRegister.setMdn(value);
		else if (key.contains("经营范围")||key.contains("经营(业务)范围"))
			gsgsRegister.setRgs(value);
		else if (key.contains("业务范围"))
			gsgsRegister.setBns(value);
		else if (key.contains("登记机关")||key.contains("主管单位"))
			gsgsRegister.setGov(value);
		else if (key.contains("核准日期"))
			gsgsRegister.setApd(value);
		else if (key.contains("登记状态")||key.contains("经营状态"))
			gsgsRegister.setStt(value);
		else if (key.contains("经营者")||key.contains("股东"))
			gsgsRegister.setMng(value);
		else if (key.contains("经营场所")||key.contains("办公地址")||key.contains("驻在场所")||key.contains("主要经营场所"))
			gsgsRegister.setMddr(value);
		else if (key.contains("组成形式")||key.contains("组织形式"))
			gsgsRegister.setFrm(value);
		else if (key.contains("注册日期"))
			gsgsRegister.setRgd(value);
		else if (key.contains("吊销日期"))
			gsgsRegister.setRvd(value);
		else if (key.contains("投资人"))
			gsgsRegister.setIvt(value);
		else if (key.contains("经营期限自")||key.contains("经营(驻在)期限自"))
			gsgsRegister.setBds(value);
		else if (key.contains("经营期限至")||key.contains("经营(驻在)期限至"))
			gsgsRegister.setBdn(value);
		else if (key.contains("负责人"))
			gsgsRegister.setCp(value);
		else if (key.contains("营业场所"))
			gsgsRegister.setBddr(value);
		else if(key.contains("执行事务合伙人"))
			gsgsRegister.setMp(value);
		else if(key.contains("合伙期限自"))
			gsgsRegister.setPds(value);
		else if(key.contains("合伙期限至"))
			gsgsRegister.setPdn(value);
		else if(key.contains("聘用律师姓名")||key.contains("办公电话")||key.contains("传真")||key.contains("电子邮箱")||key.contains("邮编"))
			gsgsRegister.setRgs(gsgsRegister.getRgs()+";"+key+":"+value);
		else
			logger.warn("基本信息,此"+"key["+key+":"+value+"],没有对应的处理方式而没有数据库中");
	}
}
