package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Enterprise_reportYear_changeRecords
 * </p>
 * <p>
 * Description: 企业公示信息_年度报告_修改记录Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Enterprise_reportYear_changeRecords {

    private int id;  //id
    private String regNumber;  //注册号
    private String changeItem;  //修改事项
    private String changeBefor;  //修改前
    private String changeAfter;  //修改后
    private String changeDate;  //修改日期


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
	 * 返回 changeItem 的值   
	 * @return changeItem  
	 */
	public String getChangeItem() {
		return  changeItem;
	}
	/**  
	 * 设置  changeItem 的值  
	 * @param  changeItem
	 */
	public void setChangeItem(String  changeItem) {
		this. changeItem =  changeItem;
	}

    /**  
	 * 返回 changeBefor 的值   
	 * @return changeBefor  
	 */
	public String getChangeBefor() {
		return  changeBefor;
	}
	/**  
	 * 设置  changeBefor 的值  
	 * @param  changeBefor
	 */
	public void setChangeBefor(String  changeBefor) {
		this. changeBefor =  changeBefor;
	}

    /**  
	 * 返回 changeAfter 的值   
	 * @return changeAfter  
	 */
	public String getChangeAfter() {
		return  changeAfter;
	}
	/**  
	 * 设置  changeAfter 的值  
	 * @param  changeAfter
	 */
	public void setChangeAfter(String  changeAfter) {
		this. changeAfter =  changeAfter;
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
