package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Enterprise_yearReport_shareholderCapital
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_股东及出资信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Enterprise_yearReport_shareholderCapital {

    private int id;  //$column.comment
    private String regNumber;  //注册号
    private String shareholder;  //股东(发起人)
    private String subscriptions;  //认缴出资额(万元)
    private String subscriptionDate;  //认缴出资时间
    private String subscriptionType;  //认缴出资方式
    private String realAmount;  //实缴出资额(万元)
    private String realAmountDate;  //出资时间
    private String realAmountType;  //出资方式


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
	 * 返回 shareholder 的值   
	 * @return shareholder  
	 */
	public String getShareholder() {
		return  shareholder;
	}
	/**  
	 * 设置  shareholder 的值  
	 * @param  shareholder
	 */
	public void setShareholder(String  shareholder) {
		this. shareholder =  shareholder;
	}

    /**  
	 * 返回 subscriptions 的值   
	 * @return subscriptions  
	 */
	public String getSubscriptions() {
		return  subscriptions;
	}
	/**  
	 * 设置  subscriptions 的值  
	 * @param  subscriptions
	 */
	public void setSubscriptions(String  subscriptions) {
		this. subscriptions =  subscriptions;
	}

    /**  
	 * 返回 subscriptionDate 的值   
	 * @return subscriptionDate  
	 */
	public String getSubscriptionDate() {
		return  subscriptionDate;
	}
	/**  
	 * 设置  subscriptionDate 的值  
	 * @param  subscriptionDate
	 */
	public void setSubscriptionDate(String  subscriptionDate) {
		this. subscriptionDate =  subscriptionDate;
	}

    /**  
	 * 返回 subscriptionType 的值   
	 * @return subscriptionType  
	 */
	public String getSubscriptionType() {
		return  subscriptionType;
	}
	/**  
	 * 设置  subscriptionType 的值  
	 * @param  subscriptionType
	 */
	public void setSubscriptionType(String  subscriptionType) {
		this. subscriptionType =  subscriptionType;
	}

    /**  
	 * 返回 realAmount 的值   
	 * @return realAmount  
	 */
	public String getRealAmount() {
		return  realAmount;
	}
	/**  
	 * 设置  realAmount 的值  
	 * @param  realAmount
	 */
	public void setRealAmount(String  realAmount) {
		this. realAmount =  realAmount;
	}

    /**  
	 * 返回 realAmountDate 的值   
	 * @return realAmountDate  
	 */
	public String getRealAmountDate() {
		return  realAmountDate;
	}
	/**  
	 * 设置  realAmountDate 的值  
	 * @param  realAmountDate
	 */
	public void setRealAmountDate(String  realAmountDate) {
		this. realAmountDate =  realAmountDate;
	}

    /**  
	 * 返回 realAmountType 的值   
	 * @return realAmountType  
	 */
	public String getRealAmountType() {
		return  realAmountType;
	}
	/**  
	 * 设置  realAmountType 的值  
	 * @param  realAmountType
	 */
	public void setRealAmountType(String  realAmountType) {
		this. realAmountType =  realAmountType;
	}
	
}
