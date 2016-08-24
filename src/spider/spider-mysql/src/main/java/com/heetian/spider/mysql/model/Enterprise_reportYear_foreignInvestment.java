package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Enterprise_reportYear_foreignInvestment
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_对外投资信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Enterprise_reportYear_foreignInvestment {

    private int id;  //id
    private String regNumber;  //注册号
    private String companyName;  //投资设立企业或购买股权企业名称
    private String companyNumber;  //企业注册号


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
	 * 返回 companyName 的值   
	 * @return companyName  
	 */
	public String getCompanyName() {
		return  companyName;
	}
	/**  
	 * 设置  companyName 的值  
	 * @param  companyName
	 */
	public void setCompanyName(String  companyName) {
		this. companyName =  companyName;
	}

    /**  
	 * 返回 companyNumber 的值   
	 * @return companyNumber  
	 */
	public String getCompanyNumber() {
		return  companyNumber;
	}
	/**  
	 * 设置  companyNumber 的值  
	 * @param  companyNumber
	 */
	public void setCompanyNumber(String  companyNumber) {
		this. companyNumber =  companyNumber;
	}
	
}
