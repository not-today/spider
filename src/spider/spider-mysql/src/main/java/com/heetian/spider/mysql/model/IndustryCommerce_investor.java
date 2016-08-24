package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_investor
 * </p>
 * <p>
 * Description: 工商公示信息_投资人信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class IndustryCommerce_investor {

    private int id;  //id
    private String regNumber;  //注册号
    private String name;  //姓名
    private String investorWay;  //出资方式


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
