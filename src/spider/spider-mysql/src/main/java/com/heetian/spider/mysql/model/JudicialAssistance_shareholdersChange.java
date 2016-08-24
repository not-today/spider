package  com.heetian.spider.mysql.model;
import java.util.Date;
/**
 * <p>
 * Title: JudicialAssistance_shareholdersChange
 * </p>
 * <p>
 * Description: 司法协助公示信息_股东变更信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class JudicialAssistance_shareholdersChange {

    private int id;  //id
    private String regNumber;  //注册号
    private String enforcedPerson;  //被执行人
    private String equityAmont;  //股权数额
    private String assignee;  //受让人
    private String executionCourt;  //执行法院
    private Date details;  //详情


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
	 * 返回 enforcedPerson 的值   
	 * @return enforcedPerson  
	 */
	public String getEnforcedPerson() {
		return  enforcedPerson;
	}
	/**  
	 * 设置  enforcedPerson 的值  
	 * @param  enforcedPerson
	 */
	public void setEnforcedPerson(String  enforcedPerson) {
		this. enforcedPerson =  enforcedPerson;
	}

    /**  
	 * 返回 equityAmont 的值   
	 * @return equityAmont  
	 */
	public String getEquityAmont() {
		return  equityAmont;
	}
	/**  
	 * 设置  equityAmont 的值  
	 * @param  equityAmont
	 */
	public void setEquityAmont(String  equityAmont) {
		this. equityAmont =  equityAmont;
	}

    /**  
	 * 返回 assignee 的值   
	 * @return assignee  
	 */
	public String getAssignee() {
		return  assignee;
	}
	/**  
	 * 设置  assignee 的值  
	 * @param  assignee
	 */
	public void setAssignee(String  assignee) {
		this. assignee =  assignee;
	}

    /**  
	 * 返回 executionCourt 的值   
	 * @return executionCourt  
	 */
	public String getExecutionCourt() {
		return  executionCourt;
	}
	/**  
	 * 设置  executionCourt 的值  
	 * @param  executionCourt
	 */
	public void setExecutionCourt(String  executionCourt) {
		this. executionCourt =  executionCourt;
	}

    /**  
	 * 返回 details 的值   
	 * @return details  
	 */
	public Date getDetails() {
		return  details;
	}
	/**  
	 * 设置  details 的值  
	 * @param  details
	 */
	public void setDetails(Date  details) {
		this. details =  details;
	}
	
}
