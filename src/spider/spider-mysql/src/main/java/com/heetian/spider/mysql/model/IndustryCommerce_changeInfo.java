package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_changeInfo
 * </p>
 * <p>
 * Description: 工商公示信息_变更信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class IndustryCommerce_changeInfo {

    private int id;  //id
    private String regNumber;  //注册号
    private String alterations;  //变更事项
    private String beginContent;  //变更前内容
    private String endContent;  //变更后内容
    private String date;  //变更日期


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
	 * 返回 变更事项 的值   
	 * @return alterations  
	 */
	public String getAlterations() {
		return  alterations;
	}
	/**  
	 * 设置  变更事项 的值 
	 * @param  alterations
	 */
	public void setAlterations(String  alterations) {
		this. alterations =  alterations;
	}

    /**  
	 * 返回 变更前内容 的值   
	 * @return beginContent  
	 */
	public String getBeginContent() {
		return  beginContent;
	}
	/**  
	 * 设置  变更前内容 的值 
	 * @param  beginContent
	 */
	public void setBeginContent(String  beginContent) {
		this. beginContent =  beginContent;
	}

    /**  
	 * 返回 变更后内容 的值   
	 * @return endContent  
	 */
	public String getEndContent() {
		return  endContent;
	}
	/**  
	 * 设置  变更后内容 的值 
	 * @param  endContent
	 */
	public void setEndContent(String  endContent) {
		this. endContent =  endContent;
	}

    /**  
	 * 返回 date 的值   
	 * @return date  
	 */
	public String getDate() {
		return  date;
	}
	/**  
	 * 设置  date 的值  
	 * @param  date
	 */
	public void setDate(String  date) {
		this. date =  date;
	}
	@Override
	public String toString() {
		return "IndustryCommerce_changeInfo [id=" + id + ", regNumber="
				+ regNumber + ", alterations=" + alterations
				+ ", beginContent=" + beginContent + ", endContent="
				+ endContent + ", date=" + date + "]";
	}
}
