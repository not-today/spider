package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Enterprise_reportYear_website
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_网站信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Enterprise_reportYear_website {

    private int id;  //id
    private String regNumber;  //注册号
    private String type;  //类型
    private String name;  //名称
    private String webUrl;  //网址


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
	 * 返回 type 的值   
	 * @return type  
	 */
	public String getType() {
		return  type;
	}
	/**  
	 * 设置  type 的值  
	 * @param  type
	 */
	public void setType(String  type) {
		this. type =  type;
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
	 * 返回 webUrl 的值   
	 * @return webUrl  
	 */
	public String getWebUrl() {
		return  webUrl;
	}
	/**  
	 * 设置  webUrl 的值  
	 * @param  webUrl
	 */
	public void setWebUrl(String  webUrl) {
		this. webUrl =  webUrl;
	}
	
}
