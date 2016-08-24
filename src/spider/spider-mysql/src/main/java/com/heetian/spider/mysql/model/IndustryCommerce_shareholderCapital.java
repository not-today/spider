package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_shareholderCapital
 * </p>
 * <p>
 * Description: 工商公示信息_股东及出资信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Fri Apr 03 22:28:46 CST 2015
 */
public class IndustryCommerce_shareholderCapital {

    private int id;  //id
    private String regNumber;  //注册号
    private String inv;  //股东
    private String blicno;  //blicno
    private String blictype;  //blictype
    private String invtype;  //invtype
    private String liacconam;  //liacconam   认缴额
    private String lisubconam;  //lisubconam　　实缴额
    private String pripid;  //pripid
    private String recid;  //recid
    private String xzqh;  //xzqh

    public boolean isNull(){
    	if((liacconam==null||"".equals(liacconam))&&(lisubconam==null||"".equals(lisubconam))&&(invtype==null||"".equals(invtype)&&(inv==null||"".equals(inv)))){
    		return true;
    	}
    	return false;
    }
    @Override
	public String toString() {
		return "shareholderCapital [id=" + id + ", regNumber="
				+ regNumber + ", inv=" + inv + ", blicno=" + blicno
				+ ", blictype=" + blictype + ", invtype=" + invtype
				+ ", liacconam=" + liacconam + ", lisubconam=" + lisubconam
				+ ", pripid=" + pripid + ", recid=" + recid + ", xzqh=" + xzqh
				+ "]";
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
	 * 返回 inv 的值   
	 * @return inv  
	 */
	public String getInv() {
		return  inv;
	}
	/**  
	 * 股东
	 * @param  inv
	 */
	public void setInv(String  inv) {
		this. inv =  inv;
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
	 * 返回 invtype 的值   
	 * @return invtype  
	 */
	public String getInvtype() {
		return  invtype;
	}
	/**  
	 * 设置  invtype 的值  
	 * @param  invtype
	 */
	public void setInvtype(String  invtype) {
		this. invtype =  invtype;
	}

    /**  
	 * 返回 liacconam 的值   
	 * @return liacconam  
	 */
	public String getLiacconam() {
		return  liacconam;
	}
	/**  
	 * 认缴额（万元） 
	 * @param  liacconam
	 */
	public void setLiacconam(String  liacconam) {
		this. liacconam =  liacconam;
	}

    /**  
	 * 返回 lisubconam 的值   
	 * @return lisubconam  
	 */
	public String getLisubconam() {
		return  lisubconam;
	}
	/**  
	 *  	实缴额（万元
	 * @param  lisubconam
	 */
	public void setLisubconam(String  lisubconam) {
		this. lisubconam =  lisubconam;
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
