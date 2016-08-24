package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_clearingInfo
 * </p>
 * <p>
 * Description: 工商公示信息_清算信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class IndustryCommerce_clearingInfo {

    private int id;  //id
    private String regNumber;  //注册号
    private String cleanLeader;  //清算组负责人
    private String cleanMembers;  //清算组成员


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
	 * 返回 cleanLeader 的值   
	 * @return cleanLeader  
	 */
	public String getCleanLeader() {
		return  cleanLeader;
	}
	/**  
	 * 设置  cleanLeader 的值  
	 * @param  cleanLeader
	 */
	public void setCleanLeader(String  cleanLeader) {
		this. cleanLeader =  cleanLeader;
	}

    /**  
	 * 返回 cleanMembers 的值   
	 * @return cleanMembers  
	 */
	public String getCleanMembers() {
		return  cleanMembers;
	}
	/**  
	 * 设置  cleanMembers 的值  
	 * @param  cleanMembers
	 */
	public void setCleanMembers(String  cleanMembers) {
		this. cleanMembers =  cleanMembers;
	}
	
}
