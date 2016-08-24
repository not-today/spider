package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_branchInfo
 * </p>
 * <p>
 * Description: 工商公示信息_分支机构Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Mar 26 11:09:34 CST 2015
 */
public class IndustryCommerce_branchInfo {

    private int id;  //id
    private String regNumber;  //注册号
    private String brname;  //名称
    private String regorg;  //登记机关
    private String pripid;  //pripid
    private String recid;  //recid
    private String regno;  //regno
    private String xzqh;  //xzqh


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
	 * 返回 brname 的值   
	 * @return brname  
	 */
	public String getBrname() {
		return  brname;
	}
	/**  
	 * 设置  brname 的值  
	 * @param  brname
	 */
	public void setBrname(String  brname) {
		this. brname =  brname;
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
	 * 返回 recid 的值   
	 * @return recid  
	 */
	public String getRecid() {
		return  recid;
	}
	/**  
	 * 设置  recid 的值  
	 * @param  recid
	 */
	public void setRecid(String  recid) {
		this. recid =  recid;
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
	@Override
	public String toString() {
		return "IndustryCommerce_branchInfo [id=" + id + ", regNumber="
				+ regNumber + ", brname=" + brname + ", regorg=" + regorg
				+ ", pripid=" + pripid + ", recid=" + recid + ", regno="
				+ regno + ", xzqh=" + xzqh + "]";
	}
}
