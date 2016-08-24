package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_mainPersonnel
 * </p>
 * <p>
 * Description: 工商公示信息_主要人员表Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Thu Mar 19 18:12:07 CST 2015
 */
public class IndustryCommerce_mainPersonel {

    private int id;  //id
    private String regNumber;  //注册号
    private String name;  //姓名
    private String position;  //职务
    private String pripid;  //pripid
    private String recid;  //recid
    private String xzqh;  //xzqh


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
	 * 返回 position 的值   
	 * @return position  
	 */
	public String getPosition() {
		return  position;
	}
	/**  
	 * 设置  position 的值  
	 * @param  position
	 */
	public void setPosition(String  position) {
		this. position =  position;
	}

    /**  
	 * 返回 pripid 的值   
	 * @return pripid  
	 */
	public String getPripid() {
		return  pripid;
	}
	/**  
	 * 设置  pripid 的值  
	 * @param  pripid
	 */
	public void setPripid(String  pripid) {
		this. pripid =  pripid;
	}

    /**  
	 * 返回 recid 的值   
	 * @return recid  
	 */
	public String getRecid() {
		return  recid;
	}
	/**  
	 * 设置  recid 的值  
	 * @param  recid
	 */
	public void setRecid(String  recid) {
		this. recid =  recid;
	}

    /**  
	 * 返回 xzqh 的值   
	 * @return xzqh  
	 */
	public String getXzqh() {
		return  xzqh;
	}
	/**  
	 * 设置  xzqh 的值  
	 * @param  xzqh
	 */
	public void setXzqh(String  xzqh) {
		this. xzqh =  xzqh;
	}
	@Override
	public String toString() {
		return "IndustryCommerce_mainPersonel [id=" + id + ", regNumber="
				+ regNumber + ", name=" + name + ", position=" + position
				+ ", pripid=" + pripid + ", recid=" + recid + ", xzqh=" + xzqh
				+ "]";
	}
}
