package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_seriousIllegal
 * </p>
 * <p>
 * Description: 工商公示信息_严重违法信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class IndustryCommerce_seriousIllegal {

    private int id;  //id
    private String regNumber;  //注册号
    private String includedCause;  //列入严重违法企业名单原因
    private String includedDate;  //列入日期
    private String removeCause;  //移除严重违法企业名单原因
    private String removeDate;  //移除日期
    private String decideOrgan;  //作出决定机关


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
	 * 返回 includedCause 的值   
	 * @return includedCause  
	 */
	public String getIncludedCause() {
		return  includedCause;
	}
	/**  
	 * 设置  includedCause 的值  
	 * @param  includedCause
	 */
	public void setIncludedCause(String  includedCause) {
		this. includedCause =  includedCause;
	}

    /**  
	 * 返回 includedDate 的值   
	 * @return includedDate  
	 */
	public String getIncludedDate() {
		return  includedDate;
	}
	/**  
	 * 设置  includedDate 的值  
	 * @param  includedDate
	 */
	public void setIncludedDate(String  includedDate) {
		this. includedDate =  includedDate;
	}

    /**  
	 * 返回 removeCause 的值   
	 * @return removeCause  
	 */
	public String getRemoveCause() {
		return  removeCause;
	}
	/**  
	 * 设置  removeCause 的值  
	 * @param  removeCause
	 */
	public void setRemoveCause(String  removeCause) {
		this. removeCause =  removeCause;
	}

    /**  
	 * 返回 removeDate 的值   
	 * @return removeDate  
	 */
	public String getRemoveDate() {
		return  removeDate;
	}
	/**  
	 * 设置  removeDate 的值  
	 * @param  removeDate
	 */
	public void setRemoveDate(String  removeDate) {
		this. removeDate =  removeDate;
	}

    /**  
	 * 返回 decideOrgan 的值   
	 * @return decideOrgan  
	 */
	public String getDecideOrgan() {
		return  decideOrgan;
	}
	/**  
	 * 设置  decideOrgan 的值  
	 * @param  decideOrgan
	 */
	public void setDecideOrgan(String  decideOrgan) {
		this. decideOrgan =  decideOrgan;
	}
	
}
