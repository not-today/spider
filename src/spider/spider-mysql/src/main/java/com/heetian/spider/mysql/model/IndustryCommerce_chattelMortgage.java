package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_chattelMortgage
 * </p>
 * <p>
 * Description: 工商公示信息_动产抵押信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Apr 09 09:59:57 CST 2015
 */
public class IndustryCommerce_chattelMortgage {

    private int id;  //id
    private String regNumber;  //注册号
    private String morepripid;  //morepripid
    private String morregcno;  //morregcno
    private String mortgagorpripid;  //mortgagorpripid
    private String pefperform;  //pefperform
    private String pefperto;  //pefperto
    private String priclasecam;  //priclasecam
    private String regidate;  //regidate
    private String regorg;  //regorg
    private String remark;  //remark
    private String type;  //type
    private String warcov;  //warcov


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
	 * 返回 morepripid 的值   
	 * @return morepripid  
	 */
	public String getMorepripid() {
		return  morepripid;
	}
	/**  
	 * 设置  morepripid 的值  
	 * @param  morepripid
	 */
	public void setMorepripid(String  morepripid) {
		this. morepripid =  morepripid;
	}

    /**  
	 * 返回 morregcno 的值   
	 * @return morregcno  
	 */
	public String getMorregcno() {
		return  morregcno;
	}
	/**  
	 * 设置  morregcno 的值  
	 * @param  morregcno
	 */
	public void setMorregcno(String  morregcno) {
		this. morregcno =  morregcno;
	}

    /**  
	 * 返回 mortgagorpripid 的值   
	 * @return mortgagorpripid  
	 */
	public String getMortgagorpripid() {
		return  mortgagorpripid;
	}
	/**  
	 * 设置  mortgagorpripid 的值  
	 * @param  mortgagorpripid
	 */
	public void setMortgagorpripid(String  mortgagorpripid) {
		this. mortgagorpripid =  mortgagorpripid;
	}

    /**  
	 * 返回 pefperform 的值   
	 * @return pefperform  
	 */
	public String getPefperform() {
		return  pefperform;
	}
	/**  
	 * 设置  pefperform 的值  
	 * @param  pefperform
	 */
	public void setPefperform(String  pefperform) {
		this. pefperform =  pefperform;
	}

    /**  
	 * 返回 pefperto 的值   
	 * @return pefperto  
	 */
	public String getPefperto() {
		return  pefperto;
	}
	/**  
	 * 设置  pefperto 的值  
	 * @param  pefperto
	 */
	public void setPefperto(String  pefperto) {
		this. pefperto =  pefperto;
	}

    /**  
	 * 返回 priclasecam 的值   
	 * @return priclasecam  
	 */
	public String getPriclasecam() {
		return  priclasecam;
	}
	/**  
	 * 设置  priclasecam 的值  
	 * @param  priclasecam
	 */
	public void setPriclasecam(String  priclasecam) {
		this. priclasecam =  priclasecam;
	}

    /**  
	 * 返回 regidate 的值   
	 * @return regidate  
	 */
	public String getRegidate() {
		return  regidate;
	}
	/**  
	 * 设置  regidate 的值  
	 * @param  regidate
	 */
	public void setRegidate(String  regidate) {
		this. regidate =  regidate;
	}

    /**  
	 * 返回 regorg 的值   
	 * @return regorg  
	 */
	public String getRegorg() {
		return  regorg;
	}
	/**  
	 * 设置  regorg 的值  
	 * @param  regorg
	 */
	public void setRegorg(String  regorg) {
		this. regorg =  regorg;
	}

    /**  
	 * 返回 remark 的值   
	 * @return remark  
	 */
	public String getRemark() {
		return  remark;
	}
	/**  
	 * 设置  remark 的值  
	 * @param  remark
	 */
	public void setRemark(String  remark) {
		this. remark =  remark;
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
	 * 返回 warcov 的值   
	 * @return warcov  
	 */
	public String getWarcov() {
		return  warcov;
	}
	/**  
	 * 设置  warcov 的值  
	 * @param  warcov
	 */
	public void setWarcov(String  warcov) {
		this. warcov =  warcov;
	}
	@Override
	public String toString() {
		return "IndustryCommerce_chattelMortgage [id=" + id + ", regNumber="
				+ regNumber + ", morepripid=" + morepripid + ", morregcno="
				+ morregcno + ", mortgagorpripid=" + mortgagorpripid
				+ ", pefperform=" + pefperform + ", pefperto=" + pefperto
				+ ", priclasecam=" + priclasecam + ", regidate=" + regidate
				+ ", regorg=" + regorg + ", remark=" + remark + ", type="
				+ type + ", warcov=" + warcov + "]";
	}
	
}
