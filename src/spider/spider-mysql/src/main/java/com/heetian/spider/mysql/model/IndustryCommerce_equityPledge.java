package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_equityPledge
 * </p>
 * <p>
 * Description: 工商公示信息_股权出质登记信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Mon Apr 13 12:19:28 CST 2015
 */
public class IndustryCommerce_equityPledge {

    private int id;  //id
    private String regNumber;  //注册号
    private String equityno;  //登记编号
    private String pledgor;  //出质人
    private String blicno;  //(出质人)证照/证件号码
    private String impam;  //出质股数数额
    private String imporg;  //质权人
    private String impmorblicno;  //(质权人)证照/证件号码
    private String equpledate;  //股权出质设立登记日期
    private String status;  //状态
    private String type;  //type
    private String blictype;  //blictype
    private String pledamunit;  //pledamunit
    private String pripid;  //pripid
    private String regcapcur;  //regcapcur
    private String xzqh;  //xzqh
    private String change;


    @Override
	public String toString() {
		return "IndustryCommerce_equityPledge [id=" + id + ", regNumber="
				+ regNumber + ", equityno=" + equityno + ", pledgor=" + pledgor
				+ ", blicno=" + blicno + ", impam=" + impam + ", imporg="
				+ imporg + ", impmorblicno=" + impmorblicno + ", equpledate="
				+ equpledate + ", status=" + status + ", type=" + type
				+ ", blictype=" + blictype + ", pledamunit=" + pledamunit
				+ ", pripid=" + pripid + ", regcapcur=" + regcapcur + ", xzqh="
				+ xzqh + ", change=" + change + "]";
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	/**  
	 * 返回 id 的值   
	 * @return id  
	 */
	public int getId() {
		return  id;
	}
	/**  
	 * 设置  id 的值  
	 * @param  id
	 */
	public void setId(int  id) {
		this. id =  id;
	}

    /**  
	 * 返回 regNumber 的值   
	 * @return regNumber  
	 */
	public String getRegNumber() {
		return  regNumber;
	}
	/**  
	 * 设置  regNumber 的值  
	 * @param  regNumber
	 */
	public void setRegNumber(String  regNumber) {
		this. regNumber =  regNumber;
	}

    /**  
	 * 返回 equityno 的值   
	 * @return equityno  
	 */
	public String getEquityno() {
		return  equityno;
	}
	/**  
	 * 设置  equityno 的值  
	 * @param  equityno
	 */
	public void setEquityno(String  equityno) {
		this. equityno =  equityno;
	}

    /**  
	 * 返回 pledgor 的值   
	 * @return pledgor  
	 */
	public String getPledgor() {
		return  pledgor;
	}
	/**  
	 * 设置  pledgor 的值  
	 * @param  pledgor
	 */
	public void setPledgor(String  pledgor) {
		this. pledgor =  pledgor;
	}

    /**  
	 * 返回 blicno 的值   
	 * @return blicno  
	 */
	public String getBlicno() {
		return  blicno;
	}
	/**  
	 * 设置  blicno 的值  
	 * @param  blicno
	 */
	public void setBlicno(String  blicno) {
		this. blicno =  blicno;
	}

    /**  
	 * 返回 impam 的值   
	 * @return impam  
	 */
	public String getImpam() {
		return  impam;
	}
	/**  
	 * 设置  impam 的值  
	 * @param  impam
	 */
	public void setImpam(String  impam) {
		this. impam =  impam;
	}

    /**  
	 * 返回 imporg 的值   
	 * @return imporg  
	 */
	public String getImporg() {
		return  imporg;
	}
	/**  
	 * 设置  imporg 的值  
	 * @param  imporg
	 */
	public void setImporg(String  imporg) {
		this. imporg =  imporg;
	}

    /**  
	 * 返回 impmorblicno 的值   
	 * @return impmorblicno  
	 */
	public String getImpmorblicno() {
		return  impmorblicno;
	}
	/**  
	 * 设置  impmorblicno 的值  
	 * @param  impmorblicno
	 */
	public void setImpmorblicno(String  impmorblicno) {
		this. impmorblicno =  impmorblicno;
	}

    /**  
	 * 返回 equpledate 的值   
	 * @return equpledate  
	 */
	public String getEqupledate() {
		return  equpledate;
	}
	/**  
	 * 设置  equpledate 的值  
	 * @param  equpledate
	 */
	public void setEqupledate(String  equpledate) {
		this. equpledate =  equpledate;
	}

    /**  
	 * 返回 status 的值   
	 * @return status  
	 */
	public String getStatus() {
		return  status;
	}
	/**  
	 * 设置  status 的值  
	 * @param  status
	 */
	public void setStatus(String  status) {
		this. status =  status;
	}

    /**  
	 * 返回 type 的值   
	 * @return type  
	 */
	public String getType() {
		return  type;
	}
	/**  
	 * 设置  type 的值  
	 * @param  type
	 */
	public void setType(String  type) {
		this. type =  type;
	}

    /**  
	 * 返回 blictype 的值   
	 * @return blictype  
	 */
	public String getBlictype() {
		return  blictype;
	}
	/**  
	 * 设置  blictype 的值  
	 * @param  blictype
	 */
	public void setBlictype(String  blictype) {
		this. blictype =  blictype;
	}

    /**  
	 * 返回 pledamunit 的值   
	 * @return pledamunit  
	 */
	public String getPledamunit() {
		return  pledamunit;
	}
	/**  
	 * 设置  pledamunit 的值  
	 * @param  pledamunit
	 */
	public void setPledamunit(String  pledamunit) {
		this. pledamunit =  pledamunit;
	}

    /**  
	 * 返回 pripid 的值   
	 * @return pripid  
	 */
	public String getPripid() {
		return  pripid;
	}
	/**  
	 * 设置  pripid 的值  
	 * @param  pripid
	 */
	public void setPripid(String  pripid) {
		this. pripid =  pripid;
	}

    /**  
	 * 返回 regcapcur 的值   
	 * @return regcapcur  
	 */
	public String getRegcapcur() {
		return  regcapcur;
	}
	/**  
	 * 设置  regcapcur 的值  
	 * @param  regcapcur
	 */
	public void setRegcapcur(String  regcapcur) {
		this. regcapcur =  regcapcur;
	}

    /**  
	 * 返回 xzqh 的值   
	 * @return xzqh  
	 */
	public String getXzqh() {
		return  xzqh;
	}
	/**  
	 * 设置  xzqh 的值  
	 * @param  xzqh
	 */
	public void setXzqh(String  xzqh) {
		this. xzqh =  xzqh;
	}
	
}
