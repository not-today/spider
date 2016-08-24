package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Enterprise_reportYear_foreignGuarantee
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_对外提供保证担保信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Enterprise_reportYear_foreignGuarantee {

    private int id;  //id
    private String regNumber;  //注册号
    private String creditor;  //债权人
    private String obligor;  //债务人
    private String creditorType;  //主债权种类
    private String creditorAmount;  //主债权数额
    private String debtMaturity;  //履行债务的期限
    private String assureTime;  //保证的期间
    private String assureType;  //保证的方式
    private String assureRange;  //保证担保的范围


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
	 * 返回 creditor 的值   
	 * @return creditor  
	 */
	public String getCreditor() {
		return  creditor;
	}
	/**  
	 * 设置  creditor 的值  
	 * @param  creditor
	 */
	public void setCreditor(String  creditor) {
		this. creditor =  creditor;
	}

    /**  
	 * 返回 obligor 的值   
	 * @return obligor  
	 */
	public String getObligor() {
		return  obligor;
	}
	/**  
	 * 设置  obligor 的值  
	 * @param  obligor
	 */
	public void setObligor(String  obligor) {
		this. obligor =  obligor;
	}

    /**  
	 * 返回 creditorType 的值   
	 * @return creditorType  
	 */
	public String getCreditorType() {
		return  creditorType;
	}
	/**  
	 * 设置  creditorType 的值  
	 * @param  creditorType
	 */
	public void setCreditorType(String  creditorType) {
		this. creditorType =  creditorType;
	}

    /**  
	 * 返回 creditorAmount 的值   
	 * @return creditorAmount  
	 */
	public String getCreditorAmount() {
		return  creditorAmount;
	}
	/**  
	 * 设置  creditorAmount 的值  
	 * @param  creditorAmount
	 */
	public void setCreditorAmount(String  creditorAmount) {
		this. creditorAmount =  creditorAmount;
	}

    /**  
	 * 返回 debtMaturity 的值   
	 * @return debtMaturity  
	 */
	public String getDebtMaturity() {
		return  debtMaturity;
	}
	/**  
	 * 设置  debtMaturity 的值  
	 * @param  debtMaturity
	 */
	public void setDebtMaturity(String  debtMaturity) {
		this. debtMaturity =  debtMaturity;
	}

    /**  
	 * 返回 assureTime 的值   
	 * @return assureTime  
	 */
	public String getAssureTime() {
		return  assureTime;
	}
	/**  
	 * 设置  assureTime 的值  
	 * @param  assureTime
	 */
	public void setAssureTime(String  assureTime) {
		this. assureTime =  assureTime;
	}

    /**  
	 * 返回 assureType 的值   
	 * @return assureType  
	 */
	public String getAssureType() {
		return  assureType;
	}
	/**  
	 * 设置  assureType 的值  
	 * @param  assureType
	 */
	public void setAssureType(String  assureType) {
		this. assureType =  assureType;
	}

    /**  
	 * 返回 assureRange 的值   
	 * @return assureRange  
	 */
	public String getAssureRange() {
		return  assureRange;
	}
	/**  
	 * 设置  assureRange 的值  
	 * @param  assureRange
	 */
	public void setAssureRange(String  assureRange) {
		this. assureRange =  assureRange;
	}
	
}
