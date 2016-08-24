package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Enterprise_changeInfo
 * </p>
 * <p>
 * Description: 企业公示信息_变更信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Enterprise_changeInfo {

    private int id;  //id
    private int regNumber;  //注册号
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
	public int getRegNumber() {
		return  regNumber;
	}
	/**  
	 * 设置  regNumber 的值  
	 * @param  regNumber
	 */
	public void setRegNumber(int  regNumber) {
		this. regNumber =  regNumber;
	}

    /**  
	 * 返回 alterations 的值   
	 * @return alterations  
	 */
	public String getAlterations() {
		return  alterations;
	}
	/**  
	 * 设置  alterations 的值  
	 * @param  alterations
	 */
	public void setAlterations(String  alterations) {
		this. alterations =  alterations;
	}

    /**  
	 * 返回 beginContent 的值   
	 * @return beginContent  
	 */
	public String getBeginContent() {
		return  beginContent;
	}
	/**  
	 * 设置  beginContent 的值  
	 * @param  beginContent
	 */
	public void setBeginContent(String  beginContent) {
		this. beginContent =  beginContent;
	}

    /**  
	 * 返回 endContent 的值   
	 * @return endContent  
	 */
	public String getEndContent() {
		return  endContent;
	}
	/**  
	 * 设置  endContent 的值  
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
	
}
