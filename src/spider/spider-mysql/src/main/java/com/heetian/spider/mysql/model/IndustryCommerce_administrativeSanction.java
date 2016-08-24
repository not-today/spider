package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_administrativeSanction
 * </p>
 * <p>
 * Description: 工商公示信息_行政处罚信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Mon Apr 20 15:22:08 CST 2015
 */
public class IndustryCommerce_administrativeSanction {

    private int id;  //id
    private String regNumber;  //单位注册号
    private String pendecno;  //行政处罚决定书文号
    private String illegacttype;  //违法行为类型
    private String penam;  //罚款金额
    private String forfam;  //没收金额
    private String penauth;  //做出行政处罚决定机关名称
    private String pendecissdate;  //做出行政处罚日期
    private String pentype;  //pentype
    private String pripid;  //pripid
    private String regno;  //regno
    private String remark;  //remark
    private String punishContent;
    private String detail;


    /**  
	 * 返回 id 的值   
	 * @return id  
	 */
	public int getId() {
		return  id;
	}
	public String getPunishContent() {
		return punishContent;
	}
	public void setPunishContent(String punishContent) {
		this.punishContent = punishContent;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
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
	 * 返回 pendecno 的值   
	 * @return pendecno  
	 */
	public String getPendecno() {
		return  pendecno;
	}
	/**  
	 * 设置  pendecno 的值  
	 * @param  pendecno
	 */
	public void setPendecno(String  pendecno) {
		this. pendecno =  pendecno;
	}

    /**  
	 * 返回 illegacttype 的值   
	 * @return illegacttype  
	 */
	public String getIllegacttype() {
		return  illegacttype;
	}
	/**  
	 * 设置  illegacttype 的值  
	 * @param  illegacttype
	 */
	public void setIllegacttype(String  illegacttype) {
		this. illegacttype =  illegacttype;
	}

    /**  
	 * 返回 penam 的值   
	 * @return penam  
	 */
	public String getPenam() {
		return  penam;
	}
	/**  
	 * 设置  penam 的值  
	 * @param  penam
	 */
	public void setPenam(String  penam) {
		this. penam =  penam;
	}

    /**  
	 * 返回 forfam 的值   
	 * @return forfam  
	 */
	public String getForfam() {
		return  forfam;
	}
	/**  
	 * 设置  forfam 的值  
	 * @param  forfam
	 */
	public void setForfam(String  forfam) {
		this. forfam =  forfam;
	}

    /**  
	 * 返回 penauth 的值   
	 * @return penauth  
	 */
	public String getPenauth() {
		return  penauth;
	}
	/**  
	 * 设置  penauth 的值  
	 * @param  penauth
	 */
	public void setPenauth(String  penauth) {
		this. penauth =  penauth;
	}

    /**  
	 * 返回 pendecissdate 的值   
	 * @return pendecissdate  
	 */
	public String getPendecissdate() {
		return  pendecissdate;
	}
	/**  
	 * 设置  pendecissdate 的值  
	 * @param  pendecissdate
	 */
	public void setPendecissdate(String  pendecissdate) {
		this. pendecissdate =  pendecissdate;
	}

    /**  
	 * 返回 pentype 的值   
	 * @return pentype  
	 */
	public String getPentype() {
		return  pentype;
	}
	/**  
	 * 设置  pentype 的值  
	 * @param  pentype
	 */
	public void setPentype(String  pentype) {
		this. pentype =  pentype;
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
	 * 返回 regno 的值   
	 * @return regno  
	 */
	public String getRegno() {
		return  regno;
	}
	/**  
	 * 设置  regno 的值  
	 * @param  regno
	 */
	public void setRegno(String  regno) {
		this. regno =  regno;
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
	@Override
	public String toString() {
		return "IndustryCommerce_administrativeSanction [id=" + id
				+ ", regNumber=" + regNumber + ", pendecno=" + pendecno
				+ ", illegacttype=" + illegacttype + ", penam=" + penam
				+ ", forfam=" + forfam + ", penauth=" + penauth
				+ ", pendecissdate=" + pendecissdate + ", pentype=" + pentype
				+ ", pripid=" + pripid + ", regno=" + regno + ", remark="
				+ remark + ", punishContent=" + punishContent + ", detail="
				+ detail + "]";
	}
	
}
