package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Enterprise_reportYear_equityChange
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_股权变更信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Enterprise_reportYear_equityChange {

    private int id;  //id
    private String regNumber;  //注册号
    private String shareholders;  //股东(发起人)
    private String beforScale;  //变更前股权比例
    private String afterScale;  //变更后股权比例
    private String changeDate;  //股权变更日期


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
	 * 返回 shareholders 的值   
	 * @return shareholders  
	 */
	public String getShareholders() {
		return  shareholders;
	}
	/**  
	 * 设置  shareholders 的值  
	 * @param  shareholders
	 */
	public void setShareholders(String  shareholders) {
		this. shareholders =  shareholders;
	}

    /**  
	 * 返回 beforScale 的值   
	 * @return beforScale  
	 */
	public String getBeforScale() {
		return  beforScale;
	}
	/**  
	 * 设置  beforScale 的值  
	 * @param  beforScale
	 */
	public void setBeforScale(String  beforScale) {
		this. beforScale =  beforScale;
	}

    /**  
	 * 返回 afterScale 的值   
	 * @return afterScale  
	 */
	public String getAfterScale() {
		return  afterScale;
	}
	/**  
	 * 设置  afterScale 的值  
	 * @param  afterScale
	 */
	public void setAfterScale(String  afterScale) {
		this. afterScale =  afterScale;
	}

    /**  
	 * 返回 changeDate 的值   
	 * @return changeDate  
	 */
	public String getChangeDate() {
		return  changeDate;
	}
	/**  
	 * 设置  changeDate 的值  
	 * @param  changeDate
	 */
	public void setChangeDate(String  changeDate) {
		this. changeDate =  changeDate;
	}
	
}
