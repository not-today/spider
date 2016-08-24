package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_shareholderSubscribed
 * </p>
 * <p>
 * Description: 工商公示信息_股东出资认缴Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Fri Apr 03 22:28:46 CST 2015
 */
public class IndustryCommerce_shareholderSubscribed {

    private int id;  //id
    private String regNumber;  //注册号
    private String condate;  //condate
    private String conform;  //conform　　　实缴出资额（万元）  //方式
    private String subconam;  //subconam  实缴出资方式      //额度
    private String inv;  //inv
    private String pripid;  //pripid
    private String recid;  //recid

    public boolean isNull(){
    	if((subconam==null||"".equals(subconam))&&(condate==null||"".equals(condate))&&(conform==null||"".equals(conform))){
    		return true;
    	}
    	return false;
    }
    @Override
	public String toString() {
		return "shareholderSubscribed [id=" + id
				+ ", regNumber=" + regNumber + ", condate=" + condate
				+ ", conform=" + conform + ", inv=" + inv + ", pripid="
				+ pripid + ", recid=" + recid + ", subconam=" + subconam + "]";
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
	 * 返回 condate 的值   
	 * @return condate  
	 */
	public String getCondate() {
		return  condate;
	}
	/**  
	 * 出资日期  
	 * @param  condate
	 */
	public void setCondate(String  condate) {
		this. condate =  condate;
	}

    /**  
	 * 返回 conform 的值   
	 * @return conform  
	 */
	public String getConform() {
		return  conform;
	}
	/**  
	 * 认缴出资额（万元）  
	 * @param  conform
	 */
	public void setConform(String  conform) {
		this. conform =  conform;
	}

    /**  
	 * 返回 inv 的值   
	 * @return inv  
	 */
	public String getInv() {
		return  inv;
	}
	/**  
	 * 认缴股东
	 * @param  inv
	 */
	public void setInv(String  inv) {
		this. inv =  inv;
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
	 * 返回 subconam 的值   
	 * @return subconam  
	 */
	public String getSubconam() {
		return  subconam;
	}
	/**  
	 * 出资方式 
	 * @param  subconam
	 */
	public void setSubconam(String  subconam) {
		this. subconam =  subconam;
	}
	
}
