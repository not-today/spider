package com.heetian.spider.process.chongqing;

import com.heetian.spider.component.TSTPageProcessor;
import com.heetian.spider.dbcp.bean.GsgsBranch;
import com.heetian.spider.dbcp.bean.GsgsChange;
import com.heetian.spider.dbcp.bean.GsgsMainPersonel;
import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.dbcp.bean.GsgsShareholder;
import com.heetian.spider.dbcp.bean.GsgsShareholderDetail;
import com.heetian.spider.utils.CheckUtils;
import com.heetian.spider.utils.TSTUtils;

/*
 )]}',
 {
 "base":{"pripid":"5002411201503310938413","regno":"500241007912162","entname":"重庆有享科技有限公司","enttypeno":"1151","enttype":"有限责任公司(自然人独资)","estdate":"2015-04-07","lerep":"傅胜志","regcap":10.0,"dom":"重庆市秀山县中和街道茶园巷70号附3号","opscope":"计算机软硬件开发；计算机的技术开发、技术转让及技术服务；电子技术开发；网站建设；利用互联网销售：百货、服装、机电设备、仪器仪表。(依法须经批准的项目，经相关部门批准后方可开展经营活动)","opfrom":"2015-04-07","regorgno":"500241","regorg":"重庆市秀山土家族苗族自治县工商行政管理局","issblicdate":"2015-04-07","opstateno":"1","opstate":"存续（在营、开业、在册）","localorg":"500241","localadm":"500241014","idcard":"4AE4A5A7672AAAB7A5E204FA46F81DFB","apprdate":"2015-04-07"},
 "members":[{"oid":"5002411201504073830328","pripid":"5002411201503310938413","name":"傅胜志","position":"执行董事、经理"},{"oid":"5002411201504073830374","pripid":"5002411201503310938413","name":"付群英","position":"监事"}],
 "brunchs":[],
 "accounts":[],
 "investors":[{"oid":"5002411201504070521845","pripid":"5002411201503310938413","invtypeno":"1","invtype":"自然人","inv":"傅胜志","certype":"中华人民共和国居民身份证","gInvaccon":[],"gInvsubcon":[]}],
 "alters":[],
 "ccjc":[],
 "illegals":[],
 "punishments":[],
 "stock":[],
 "motage":[],
 "gtjnz":[],
 "nzfz":[],
 "qyjy":[]
 }
 */
public class Container {
	private CQRegistBaseInfoJson base;// 登记信息页面上基本信息
	private CQMainPersonJson[] members;// 公司组成成员
	private CQBranchInfoJson[] brunchs;// 分支机构
	// private CQClearingInfoJson[] accounts;//清算信息
	private CQShareholderJson[] investors;// 股东信息
	private CQChangeInfoJson[] alters;// 变更信息
	// private CQSpotChecksInfoJson[] ccjc;//抽查检查信息
	// private CQAdministrativeSanctionInfoJson[] punishments;//行政处罚信息
	// private String[] illegals;//严重违法信息
	// private CQEquityPledgeInfoJson[] stock;//股权出质登记信息
	// private CQChattelMortgageInfoJson[] motage;//动产抵押登记信息
	//private String[] gtjnz;//
	//private String[] nzfz;//

	// private CQManageAbnormalInfoJson[] qyjy;//经营异常信息
	public String toConvert(TSTPageProcessor pro,String url){
		GsgsRegister jb = base.toConvert();
		if(jb==null)
			return null;
		jb.setPvn(pro.getcode());;
		String regNumber = jb.getRgc();
		jb.setUrl(url);
		if(members!=null&&members.length>0){
			for(CQMainPersonJson member:members)
				jb.getKeyPersons().add(member.toConvert(regNumber));
		}
		if(brunchs!=null&&brunchs.length>0){
			for(CQBranchInfoJson brunch:brunchs)
				jb.getBranchs().add(brunch.toConvert(regNumber));
		}
		if(alters!=null&&alters.length>0){
			for(CQChangeInfoJson alter:alters)
				jb.getChanges().add(alter.toConvert(regNumber));
		}
		if(investors!=null&&investors.length>0){
			for(CQShareholderJson investor:investors){
				jb.getShareholders().add(investor.toConvert(regNumber));
			}
		}
		pro.setDataJB(jb,regNumber);
		return regNumber;
	}
	public CQRegistBaseInfoJson getBase() {
		return base;
	}

	public void setBase(CQRegistBaseInfoJson base) {
		this.base = base;
	}

	public CQMainPersonJson[] getMembers() {
		return members;
	}

	public void setMembers(CQMainPersonJson[] members) {
		this.members = members;
	}

	public CQBranchInfoJson[] getBrunchs() {
		return brunchs;
	}

	public void setBrunchs(CQBranchInfoJson[] brunchs) {
		this.brunchs = brunchs;
	}

	public CQShareholderJson[] getInvestors() {
		return investors;
	}

	public void setInvestors(CQShareholderJson[] investors) {
		this.investors = investors;
	}

	public CQChangeInfoJson[] getAlters() {
		return alters;
	}

	public void setAlters(CQChangeInfoJson[] alters) {
		this.alters = alters;
	}
}

class CQChangeInfoJson {
	private String altaf;// 变更后内容
	private String altbe;// 变更前内容
	private String altdate;// 变更日期
	private String altitemno;// 变更事项编号
	private String altitem;// 变更事项
	private String openo;
	private String pripid;// id
	private String xzqh;
	public GsgsChange toConvert(String regNumber){
		GsgsChange changeInfo = new GsgsChange();
		changeInfo.setRegNumber(regNumber);
		changeInfo.setType(altitem);
		changeInfo.setChs(altbe);
		changeInfo.setChn(altaf);
		changeInfo.setDate(altdate);
		return changeInfo;
	}
	public String getAltitemno() {
		return altitemno;
	}

	public void setAltitemno(String altitemno) {
		this.altitemno = altitemno;
	}

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

	public String getAltdate() {
		return altdate;
	}

	public void setAltdate(String altdate) {
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
}

class CQSubscribedJson {

	public String condate; // 认缴时间
	public String conform;// 认缴类型
	public String subconam;// 认缴出资额

	public String getCondate() {
		return condate;
	}

	public void setCondate(String condate) {
		this.condate = condate;
	}

	public String getConform() {
		return conform;
	}

	public void setConform(String conform) {
		this.conform = conform;
	}

	public String getSubconam() {
		return subconam;
	}

	public void setSubconam(String subconam) {
		this.subconam = subconam;
	}
}

class CQPaidinJson {

	public String acconam; // 实缴额
	public String accondate; // 实缴出资日期
	public String acconform; // 实缴类型

	public String getAcconam() {
		return acconam;
	}

	public void setAcconam(String acconam) {
		this.acconam = acconam;
	}

	public String getAccondate() {
		return accondate;
	}

	public void setAccondate(String accondate) {
		this.accondate = accondate;
	}

	public String getAcconform() {
		return acconform;
	}

	public void setAcconform(String acconform) {
		this.acconform = acconform;
	}
}

class CQShareholderJson {
	private String inv; // 股东
	private String invtype;// 股东类型
	private String liacconam;// 实缴额
	private String lisubconam;// 认缴额
	private String pripid;// id
	private String certype;// 证照/证件类型
	private CQPaidinJson[] gInvaccon;// 实缴明细
	private CQSubscribedJson[] gInvsubcon;// 认缴明细
	private String blictypeno; // 证照、证件类型编号
	private String blictype;// 证照、证件类型
	private String blicno;// 证照、证件号码
	private String name; // 姓名
	private String sconform;// 投资方式
	public GsgsShareholder toConvert(String regNumber){
		if(name != null){
			//封装投资人信息
			GsgsShareholder gd = new GsgsShareholder();
			gd.setRegNumber(regNumber);
			gd.setName(name);
			gd.setType(sconform);
			return gd;
		}else{
			//封装股东信息
			GsgsShareholder gd = new GsgsShareholder();
			gd.setRegNumber(regNumber);
			gd.setName(inv);
			gd.setType(invtype);
			gd.setCrdt((blictype!=null ? blictype:"") +(certype!=null ? certype:""));
			gd.setCrdc(blicno);
			//封装股东及出资信息
			int gInvacconNum = gInvaccon==null?0:gInvaccon.length;
			int gInvsubconNum = gInvsubcon==null?0:gInvsubcon.length;
			int max = gInvacconNum>=gInvsubconNum?gInvacconNum:gInvsubconNum;
			if(max!=0){
				String uuid = TSTUtils.uuid();
				gd.setUuid(uuid);
				for(int x=0;x<max;x++){
					GsgsShareholderDetail gdxq = new GsgsShareholderDetail();
					gdxq.setRegNumber(regNumber);
					gdxq.setUuid(uuid);
					gdxq.setName(inv);
					gdxq.setInvType(invtype);
					gdxq.setPtmn(liacconam);
					gdxq.setStmn(lisubconam);
					if(gInvaccon!=null&&gInvaccon.length>x){
						gdxq.setPmn(gInvaccon[x].getAcconam());
						gdxq.setPd(gInvaccon[x].getAccondate());
						gdxq.setPfrm(gInvaccon[x].getAcconform());
					}
					if(gInvaccon!=null&&gInvaccon.length>x){
						gdxq.setSd(gInvsubcon[x].getCondate());
						gdxq.setSfrm(gInvsubcon[x].getConform());
						gdxq.setSmn(gInvsubcon[x].getSubconam());
					}
					gd.getContributives().add(gdxq);
				}
			}
			return gd;
		}
	}
	public String getInv() {
		return inv;
	}

	public void setInv(String inv) {
		this.inv = inv;
	}

	public String getInvtype() {
		return invtype;
	}

	public void setInvtype(String invtype) {
		this.invtype = invtype;
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

	public String getCertype() {
		return certype;
	}

	public void setCertype(String certype) {
		this.certype = certype;
	}

	public String getBlictypeno() {
		return blictypeno;
	}

	public void setBlictypeno(String blictypeno) {
		this.blictypeno = blictypeno;
	}

	public String getBlictype() {
		return blictype;
	}

	public CQPaidinJson[] getgInvaccon() {
		return gInvaccon;
	}

	public void setgInvaccon(CQPaidinJson[] gInvaccon) {
		this.gInvaccon = gInvaccon;
	}

	public CQSubscribedJson[] getgInvsubcon() {
		return gInvsubcon;
	}

	public void setgInvsubcon(CQSubscribedJson[] gInvsubcon) {
		this.gInvsubcon = gInvsubcon;
	}

	public void setBlictype(String blictype) {
		this.blictype = blictype;
	}

	public String getBlicno() {
		return blicno;
	}

	public void setBlicno(String blicno) {
		this.blicno = blicno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSconform() {
		return sconform;
	}

	public void setSconform(String sconform) {
		this.sconform = sconform;
	}

}

class CQBranchInfoJson {
	private String brname;// 名称
	private String pripid;
	private String recid;
	private String regno; // 分支机构注册号
	private String regorg; // 登记机关
	public GsgsBranch toConvert(String regNumber){
		GsgsBranch branckInfo = new GsgsBranch();
		branckInfo.setRegNumber(regNumber);
		branckInfo.setName(brname);
		branckInfo.setRgc(regno);
		branckInfo.setGov(regorg);
		return branckInfo;
	}
	public String getBrname() {
		return brname;
	}

	public void setBrname(String brname) {
		this.brname = brname;
	}

	public String getPripid() {
		return pripid;
	}

	public void setPripid(String pripid) {
		this.pripid = pripid;
	}

	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getRegorg() {
		return regorg;
	}

	public void setRegorg(String regorg) {
		this.regorg = regorg;
	}
}

class CQMainPersonJson {
	private String name; // 姓名
	private String position; // 职务
	private String pripid; // pripid
	private String certypeno;// 证照、证件类型
	private String certype;// 证照、证件类型
	private String invtypeno;// 出资人 类型编号
	private String invtype;// 出资人类型
	public GsgsMainPersonel toConvert(String regNumber){
		GsgsMainPersonel mainPersonel = new GsgsMainPersonel();
		mainPersonel.setRegNumber(regNumber);
		mainPersonel.setName(name);
		mainPersonel.setPos(position);
		return mainPersonel;
	}
	public String getCertypeno() {
		return certypeno;
	}

	public void setCertypeno(String certypeno) {
		this.certypeno = certypeno;
	}

	public String getInvtype() {
		return invtype;
	}

	public void setInvtype(String invtype) {
		this.invtype = invtype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPripid() {
		return pripid;
	}

	public void setPripid(String pripid) {
		this.pripid = pripid;
	}

	public String getCertype() {
		return certype;
	}

	public void setCertype(String certype) {
		this.certype = certype;
	}

	public String getInvtypeno() {
		return invtypeno;
	}

	public void setInvtypeno(String invtypeno) {
		this.invtypeno = invtypeno;
	}

}

class CQRegistBaseInfoJson {
	private String pripid;// entId
	private String regno;// 注册号
	private String entname;// 名称
	private String enttypeno;// 公司类型编号
	private String enttype;// 公司类型
	private String estdate;// 成立日期
	private String lerep;// 法定代表人
	private String name;// 经营者（个体工商户）
	private String regcap;// 注册资本（单位万元）
	private String dom;// 住所
	private String opscope;// 经营范围（国有经济）
	private String opscoandform;// 经营范围（个人独资企业(微型企业)）
	private String opfrom;// 营业期限自
	private String opto;// 营业期限至
	private String regorgno;// 区域编号 （重庆500000）
	private String regorg;// 登记机关
	private String issblicdate;// 核准日期
	private String opstateno;// 登记状态编号
	private String opstate;// 登记状态
	private String localorg;// 本地组织编号
	private String localadm;//
	private String idcard;//
	private String apprdate;// 核准日期
	private String revdate;// 吊销日期
	private String compform;// 组织形式
	private String regdate; // 注册日期
	private String oploc;// 经营场所（个体工商户）
	public GsgsRegister toConvert(){
		GsgsRegister jbxx = new GsgsRegister();
		jbxx.setRgc(regno);
		if(CheckUtils.checkCreditCode(regno))
			jbxx.setNsc(regno);//新增
		jbxx.setName(entname);
		jbxx.setType(enttype);
		jbxx.setFdd(estdate);
		jbxx.setLp(lerep);
		jbxx.setAddr(dom);
		jbxx.setRc(regcap);
		jbxx.setMds(opfrom);
		jbxx.setMdn(opto);
		jbxx.setRgs((opscope!=null ? opscope:"") +(opscoandform!=null ? opscoandform:""));
		jbxx.setGov(regorg);
		//jbxx.setApd(issblicdate);
		jbxx.setApd(apprdate);
		jbxx.setStt(opstate);
		jbxx.setMng(name);
		jbxx.setMddr(oploc);
		jbxx.setRvd(revdate);
		jbxx.setRgd(regdate);
		jbxx.setFrm(compform);
		//添加更新该企业时间
		jbxx.setUpt(System.currentTimeMillis());
		return jbxx;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpscoandform() {
		return opscoandform;
	}

	public void setOpscoandform(String opscoandform) {
		this.opscoandform = opscoandform;
	}

	public String getOpto() {
		return opto;
	}

	public void setOpto(String opto) {
		this.opto = opto;
	}

	public String getIssblicdate() {
		return issblicdate;
	}

	public void setIssblicdate(String issblicdate) {
		this.issblicdate = issblicdate;
	}

	public String getPripid() {
		return pripid;
	}

	public void setPripid(String pripid) {
		this.pripid = pripid;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getEntname() {
		return entname;
	}

	public void setEntname(String entname) {
		this.entname = entname;
	}

	public String getEnttypeno() {
		return enttypeno;
	}

	public void setEnttypeno(String enttypeno) {
		this.enttypeno = enttypeno;
	}

	public String getEnttype() {
		return enttype;
	}

	public void setEnttype(String enttype) {
		this.enttype = enttype;
	}

	public String getEstdate() {
		return estdate;
	}

	public void setEstdate(String estdate) {
		this.estdate = estdate;
	}

	public String getLerep() {
		return lerep;
	}

	public void setLerep(String lerep) {
		this.lerep = lerep;
	}

	public String getRegcap() {
		return regcap;
	}

	public void setRegcap(String regcap) {
		this.regcap = regcap;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getOpscope() {
		return opscope;
	}

	public void setOpscope(String opscope) {
		this.opscope = opscope;
	}

	public String getOpfrom() {
		return opfrom;
	}

	public void setOpfrom(String opfrom) {
		this.opfrom = opfrom;
	}

	public String getRegorgno() {
		return regorgno;
	}

	public void setRegorgno(String regorgno) {
		this.regorgno = regorgno;
	}

	public String getRegorg() {
		return regorg;
	}

	public void setRegorg(String regorg) {
		this.regorg = regorg;
	}

	public String getIssblicadate() {
		return issblicdate;
	}

	public void setIssblicadate(String issblicadate) {
		this.issblicdate = issblicadate;
	}

	public String getOpstateno() {
		return opstateno;
	}

	public void setOpstateno(String opstateno) {
		this.opstateno = opstateno;
	}

	public String getOpstate() {
		return opstate;
	}

	public void setOpstate(String opstate) {
		this.opstate = opstate;
	}

	public String getLocalorg() {
		return localorg;
	}

	public void setLocalorg(String localorg) {
		this.localorg = localorg;
	}

	public String getLocaladm() {
		return localadm;
	}

	public void setLocaladm(String localadm) {
		this.localadm = localadm;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getApprdate() {
		return apprdate;
	}

	public void setApprdate(String apprdate) {
		this.apprdate = apprdate;
	}

	public String getRevdate() {
		return revdate;
	}

	public void setRevdate(String revdate) {
		this.revdate = revdate;
	}

	public String getCompform() {
		return compform;
	}

	public void setCompform(String compform) {
		this.compform = compform;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getOploc() {
		return oploc;
	}

	public void setOploc(String oploc) {
		this.oploc = oploc;
	}
}
