package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: Companys
 * </p>
 * <p>
 * Description: 企业信息列表Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class Companys {

    private int id;  //$column.comment
    private String name;  //名称
    private String regNumber;  //注册号
    private String url;  //url地址
    private String flag;  //是否抓取标志位
    private String recordingTime;  //记录时间
    private int gather_num;//抓取次数
    private String entId;
    private int type;


    public String getEntId() {
		return entId;
	}
	public void setEntId(String entId) {
		this.entId = entId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGather_num() {
		return gather_num;
	}
	public void setGather_num(int gather_num) {
		this.gather_num = gather_num;
	}
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
	 * 返回 url 的值   
	 * @return url  
	 */
	public String getUrl() {
		return  url;
	}
	/**  
	 * 设置  url 的值  
	 * @param  url
	 */
	public void setUrl(String  url) {
		this. url =  url;
	}

    /**  
	 * 返回 flag 的值   
	 * @return flag  
	 */
	public String getFlag() {
		return  flag;
	}
	/**  
	 * 设置  flag 的值  
	 * @param  flag
	 */
	public void setFlag(String  flag) {
		this. flag =  flag;
	}

    /**  
	 * 返回 recordingTime 的值   
	 * @return recordingTime  
	 */
	public String getRecordingTime() {
		return  recordingTime;
	}
	/**  
	 * 设置  recordingTime 的值  
	 * @param  recordingTime
	 */
	public void setRecordingTime(String  recordingTime) {
		this. recordingTime =  recordingTime;
	}
	@Override
	public String toString() {
		return "Companys [id=" + id + ", name=" + name + ", regNumber="
				+ regNumber + ", url=" + url + ", flag=" + flag
				+ ", recordingTime=" + recordingTime + ", gather_num="
				+ gather_num + "]";
	}
	
}
