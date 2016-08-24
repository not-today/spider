package com.heetian.spider.process.guizhou;

import java.util.List;

import com.heetian.spider.dbcp.bean.GsgsRegister;
import com.heetian.spider.utils.CheckUtils;

public class JBBean {
	private String successed;
	private List<JbData> data;
	public String getSuccessed() {
		return successed;
	}
	public void setSuccessed(String successed) {
		this.successed = successed;
	}
	public List<JbData> getData() {
		return data;
	}
	public void setData(List<JbData> data) {
		this.data = data;
	}
	public GsgsRegister getJb() {
		if(data==null||data.size()<=0)
			return null;
		JbData jbdata = data.get(0);
		GsgsRegister jb = new GsgsRegister();
		jb.setRgc(jbdata.getZch());
		if(CheckUtils.checkCreditCode(jbdata.getZch()))
			jb.setNsc(jbdata.getZch());//新增
		if(jbdata.getQymc()==null||"".equals(jbdata.getQymc().trim())){
			jb.setName(jbdata.getZhmc());//名称
		}else{
			jb.setName(jbdata.getQymc());//名称
		}
		if(jbdata.getQylxmc()==null||"".equals(jbdata.getQylxmc().trim())){
			jb.setType(jbdata.getZcxsmc());//类型
		}else{
			jb.setType(jbdata.getQylxmc());//类型
		}
		if(jbdata.getFddbr()==null||"".equals(jbdata.getFddbr().trim())){
			jb.setLp(jbdata.getJyzm());//法定代表人
		}else{
			jb.setLp(jbdata.getFddbr());//法定代表人
		}
		jb.setRc(jbdata.getZczb());//注册资本
		jb.setFdd(jbdata.getClrq());//成立日期
		jb.setAddr(jbdata.getZs());//住所
		jb.setMds(jbdata.getYyrq1());//营业期限自
		jb.setMdn(jbdata.getYyrq2());//营业期限至
		jb.setRgs(jbdata.getJyfw());//经营范围
		jb.setGov(jbdata.getDjjgmc());//登记机关
		jb.setApd(jbdata.getHzrq());//核准日期
		jb.setStt(jbdata.getMclxmc());//登记状态
		jb.setUpt(System.currentTimeMillis());
		
		return jb;
	}
}

class JbData{
/*qzjyfwmc:null;	`
tzze:null;     `
optypemc:"其它类";  `
hzrq:"2015年4月23日";
ybjyfw:"批零兼营：预包装食品；酒类研发、包装装潢设计；白酒生产。";.  `
qymc:"贵州茅台酒厂（集团）白金酒有限责任公司";
mclxmc:"存续";
clrq:"2013年5月16日";
zch:"520000000120008";
djjgmc:"贵州省工商行政管理局";
qylxmc:"其他有限责任公司";
fddbr:"张城";
zczb:"30000 万元人民币";
zs:"贵州省仁怀市茅台镇国酒社区";
jyfw:"批零兼营：预包装食品；酒类研发、包装装潢设计；白酒生产。";
nsqy:null;	`
tzzemy:null;	`
yyrq1:"2013年5月15日";
yyrq2:null

qzjyfw:null
ybjyfw:"食品、饮料、食杂、百货批零兼营。"
hzrq:"2015年9月16日"
clrq:"2015年9月16日"
zch:"522726600194997"
djjgmc:"黔南州独山县工商局"
zhmc:"独山县千里香南北调味品店"
jyjzrq:null
jyzm:"莫兴莉"
zs:"独山县百泉镇影山路新北集市场15-17号门面"
jyfw:"法律、法规、国务院决定规定禁止的不得经营；法律、法规、国务院决定规定应当许可（审批）的，经审批机关批准后凭许可（审批）文件经营;法律、法规、国务院决定规定无需许可（审批）的，市场主体自主选择经营。<br />章程、协议、申请书记载的经营范围：食品、饮料、食杂、百货批零兼营。"
jyqsrq:"2015年9月16日"
zcxsmc:"个人经营  "
*/
	private String zcxsmc;//组成形式
	private String jyqsrq;//
	private String jyzm;//经营者
	private String jyjzrq;//
	private String zhmc;//企业名
	
	private String qzjyfwmc;
	private String tzze;
	private String optypemc;
	private String hzrq;
	private String ybjyfw;
	private String qymc;
	private String mclxmc;
	private String clrq;
	private String zch;
	private String djjgmc;
	private String qylxmc;
	private String fddbr;
	private String zczb;
	private String zs;
	private String jyfw;
	private String nsqy;
	private String tzzemy;
	private String yyrq1;
	private String yyrq2;
	
	public String getZcxsmc() {
		return zcxsmc;
	}
	public void setZcxsmc(String zcxsmc) {
		this.zcxsmc = zcxsmc;
	}
	public String getJyqsrq() {
		return jyqsrq;
	}
	public void setJyqsrq(String jyqsrq) {
		this.jyqsrq = jyqsrq;
	}
	public String getJyzm() {
		return jyzm;
	}
	public void setJyzm(String jyzm) {
		this.jyzm = jyzm;
	}
	public String getJyjzrq() {
		return jyjzrq;
	}
	public void setJyjzrq(String jyjzrq) {
		this.jyjzrq = jyjzrq;
	}
	public String getZhmc() {
		return zhmc;
	}
	public void setZhmc(String zhmc) {
		this.zhmc = zhmc;
	}
	public String getQzjyfwmc() {
		return qzjyfwmc;
	}
	public void setQzjyfwmc(String qzjyfwmc) {
		this.qzjyfwmc = qzjyfwmc;
	}
	public String getTzze() {
		return tzze;
	}
	public void setTzze(String tzze) {
		this.tzze = tzze;
	}
	public String getOptypemc() {
		return optypemc;
	}
	public void setOptypemc(String optypemc) {
		this.optypemc = optypemc;
	}
	public String getHzrq() {
		return hzrq;
	}
	public void setHzrq(String hzrq) {
		this.hzrq = hzrq;
	}
	public String getYbjyfw() {
		return ybjyfw;
	}
	public void setYbjyfw(String ybjyfw) {
		this.ybjyfw = ybjyfw;
	}
	public String getQymc() {
		return qymc;
	}
	public void setQymc(String qymc) {
		this.qymc = qymc;
	}
	public String getMclxmc() {
		return mclxmc;
	}
	public void setMclxmc(String mclxmc) {
		this.mclxmc = mclxmc;
	}
	public String getClrq() {
		return clrq;
	}
	public void setClrq(String clrq) {
		this.clrq = clrq;
	}
	public String getZch() {
		return zch;
	}
	public void setZch(String zch) {
		this.zch = zch;
	}
	public String getDjjgmc() {
		return djjgmc;
	}
	public void setDjjgmc(String djjgmc) {
		this.djjgmc = djjgmc;
	}
	public String getQylxmc() {
		return qylxmc;
	}
	public void setQylxmc(String qylxmc) {
		this.qylxmc = qylxmc;
	}
	public String getFddbr() {
		return fddbr;
	}
	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getZczb() {
		return zczb;
	}
	public void setZczb(String zczb) {
		this.zczb = zczb;
	}
	public String getZs() {
		return zs;
	}
	public void setZs(String zs) {
		this.zs = zs;
	}
	public String getJyfw() {
		return jyfw;
	}
	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}
	public String getNsqy() {
		return nsqy;
	}
	public void setNsqy(String nsqy) {
		this.nsqy = nsqy;
	}
	public String getTzzemy() {
		return tzzemy;
	}
	public void setTzzemy(String tzzemy) {
		this.tzzemy = tzzemy;
	}
	public String getYyrq1() {
		return yyrq1;
	}
	public void setYyrq1(String yyrq1) {
		this.yyrq1 = yyrq1;
	}
	public String getYyrq2() {
		return yyrq2;
	}
	public void setYyrq2(String yyrq2) {
		this.yyrq2 = yyrq2;
	}
	
}