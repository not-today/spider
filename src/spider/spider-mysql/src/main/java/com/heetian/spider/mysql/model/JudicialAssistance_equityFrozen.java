package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: JudicialAssistance_equityFrozen
 * </p>
 * <p>
 * Description: 司法协助公示信息_股权冻结信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class JudicialAssistance_equityFrozen {

    private int id;  //id
    private String regNumber;  //注册号
    private String enforcedPerson;  //被执行人
    private String equityAmont;  //股权数额
    private String executionCourt;  //执行法院
    private String noticeNumber;  //协助公示通知书文号
    private String status;  //状态
    private String details;  //详情


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
	 * 返回 noticeNumber 的值   
	 * @return noticeNumber  
	 */
	public String getNoticeNumber() {
		return  noticeNumber;
	}
	/**  
	 * 设置  noticeNumber 的值  
	 * @param  noticeNumber
	 */
	public void setNoticeNumber(String  noticeNumber) {
		this. noticeNumber =  noticeNumber;
	}

    /**  
	 * 返回 status 的值   
	 * @return status  
	 */
	public String getStatus() {
		return  status;
	}
	/**  
	 * 设置  status 的值  
	 * @param  status
	 */
	public void setStatus(String  status) {
		this. status =  status;
	}

    /**  
	 * 返回 details 的值   
	 * @return details  
	 */
	public String getDetails() {
		return  details;
	}
	/**  
	 * 设置  details 的值  
	 * @param  details
	 */
	public void setDetails(String  details) {
		this. details =  details;
	}
	
}
