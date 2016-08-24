package  com.heetian.spider.dbcp.bean;
/**
 * 登记信息，股东详情信息
 * @author tst
 *
 */
public class GsgsInvestor {
    private String regNumber;  //注册号
    private String name;  //姓名
    private String investorWay;  //出资方式

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
	 * 返回 name 的值   
	 * @return name  
	 */
	public String getName() {
		return  name;
	}
	/**  
	 * 设置  name 的值  
	 * @param  name
	 */
	public void setName(String  name) {
		this. name =  name;
	}

    /**  
	 * 返回 investorWay 的值   
	 * @return investorWay  
	 */
	public String getInvestorWay() {
		return  investorWay;
	}
	/**  
	 * 设置  investorWay 的值  
	 * @param  investorWay
	 */
	public void setInvestorWay(String  investorWay) {
		this. investorWay =  investorWay;
	}
	
}
