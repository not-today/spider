package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Enterprise_reportYear
 * </p>
 * <p>
 * Description: 企业公示信息_企业年报Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Enterprise_reportYear {

    private int id;  //$column.comment
    private String regNumber;  //注册号
    private String dubmitYear;  //报送年度
    private String releaseDate;  //发布日期


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
	 * 返回 dubmitYear 的值   
	 * @return dubmitYear  
	 */
	public String getDubmitYear() {
		return  dubmitYear;
	}
	/**  
	 * 设置  dubmitYear 的值  
	 * @param  dubmitYear
	 */
	public void setDubmitYear(String  dubmitYear) {
		this. dubmitYear =  dubmitYear;
	}

    /**  
	 * 返回 releaseDate 的值   
	 * @return releaseDate  
	 */
	public String getReleaseDate() {
		return  releaseDate;
	}
	/**  
	 * 设置  releaseDate 的值  
	 * @param  releaseDate
	 */
	public void setReleaseDate(String  releaseDate) {
		this. releaseDate =  releaseDate;
	}
	
}
