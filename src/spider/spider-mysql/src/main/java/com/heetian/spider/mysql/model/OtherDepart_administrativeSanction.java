package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: OtherDepart_administrativeSanction
 * </p>
 * <p>
 * Description: 其他部门公示信息_行政处罚信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class OtherDepart_administrativeSanction {

    private int id;  //id
    private String regNumber;  //单位注册号
    private String decisionNumber;  //行政处罚决定书文号
    private String IllegalType;  //违法行为类型
    private String punishContent;  //行政处罚内容
    private String decisionOrgan;  //作出行政处罚决定机关名称
    private String decisionDate;  //作出行政处罚决定日期
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
	 * 返回 decisionNumber 的值   
	 * @return decisionNumber  
	 */
	public String getDecisionNumber() {
		return  decisionNumber;
	}
	/**  
	 * 设置  decisionNumber 的值  
	 * @param  decisionNumber
	 */
	public void setDecisionNumber(String  decisionNumber) {
		this. decisionNumber =  decisionNumber;
	}

    /**  
	 * 返回 IllegalType 的值   
	 * @return IllegalType  
	 */
	public String getIllegalType() {
		return  IllegalType;
	}
	/**  
	 * 设置  IllegalType 的值  
	 * @param  IllegalType
	 */
	public void setIllegalType(String  IllegalType) {
		this. IllegalType =  IllegalType;
	}

    /**  
	 * 返回 punishContent 的值   
	 * @return punishContent  
	 */
	public String getPunishContent() {
		return  punishContent;
	}
	/**  
	 * 设置  punishContent 的值  
	 * @param  punishContent
	 */
	public void setPunishContent(String  punishContent) {
		this. punishContent =  punishContent;
	}

    /**  
	 * 返回 decisionOrgan 的值   
	 * @return decisionOrgan  
	 */
	public String getDecisionOrgan() {
		return  decisionOrgan;
	}
	/**  
	 * 设置  decisionOrgan 的值  
	 * @param  decisionOrgan
	 */
	public void setDecisionOrgan(String  decisionOrgan) {
		this. decisionOrgan =  decisionOrgan;
	}

    /**  
	 * 返回 decisionDate 的值   
	 * @return decisionDate  
	 */
	public String getDecisionDate() {
		return  decisionDate;
	}
	/**  
	 * 设置  decisionDate 的值  
	 * @param  decisionDate
	 */
	public void setDecisionDate(String  decisionDate) {
		this. decisionDate =  decisionDate;
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
