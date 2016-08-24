package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: OtderDepart_administrationPermit
 * </p>
 * <p>
 * Description: 其他部门公示信息_行政许可信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class OtderDepart_administrationPermit {

    private int id;  //id
    private String regNumber;  //注册号
    private String fileNumber;  //许可文件编号
    private String fileName;  //许可文件名称
    private String beginDate;  //有效期自
    private String endDate;  //有效期至
    private String permitOrgan;  //许可机关
    private String permitContent;  //许可内容
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
	 * 返回 fileNumber 的值   
	 * @return fileNumber  
	 */
	public String getFileNumber() {
		return  fileNumber;
	}
	/**  
	 * 设置  fileNumber 的值  
	 * @param  fileNumber
	 */
	public void setFileNumber(String  fileNumber) {
		this. fileNumber =  fileNumber;
	}

    /**  
	 * 返回 fileName 的值   
	 * @return fileName  
	 */
	public String getFileName() {
		return  fileName;
	}
	/**  
	 * 设置  fileName 的值  
	 * @param  fileName
	 */
	public void setFileName(String  fileName) {
		this. fileName =  fileName;
	}

    /**  
	 * 返回 beginDate 的值   
	 * @return beginDate  
	 */
	public String getBeginDate() {
		return  beginDate;
	}
	/**  
	 * 设置  beginDate 的值  
	 * @param  beginDate
	 */
	public void setBeginDate(String  beginDate) {
		this. beginDate =  beginDate;
	}

    /**  
	 * 返回 endDate 的值   
	 * @return endDate  
	 */
	public String getEndDate() {
		return  endDate;
	}
	/**  
	 * 设置  endDate 的值  
	 * @param  endDate
	 */
	public void setEndDate(String  endDate) {
		this. endDate =  endDate;
	}

    /**  
	 * 返回 permitOrgan 的值   
	 * @return permitOrgan  
	 */
	public String getPermitOrgan() {
		return  permitOrgan;
	}
	/**  
	 * 设置  permitOrgan 的值  
	 * @param  permitOrgan
	 */
	public void setPermitOrgan(String  permitOrgan) {
		this. permitOrgan =  permitOrgan;
	}

    /**  
	 * 返回 permitContent 的值   
	 * @return permitContent  
	 */
	public String getPermitContent() {
		return  permitContent;
	}
	/**  
	 * 设置  permitContent 的值  
	 * @param  permitContent
	 */
	public void setPermitContent(String  permitContent) {
		this. permitContent =  permitContent;
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
