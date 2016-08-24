package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_shareholder
 * </p>
 * <p>
 * Description: 工商公示信息_股东信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 25 17:57:55 CST 2015
 */
public class IndustryCommerce_shareholder {

    private int id;  //id
    private String regNumber;  //注册号
    private String shareholder;  //股东
    private String type;  //类别
    private String cardType;  //证件类型
    private String cardId;  //证件号
    private String details;  //详情
    private String ivtf;   //投资方式


    public String getIvtf() {
		return ivtf;
	}
	public void setIvtf(String ivtf) {
		this.ivtf = ivtf;
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
	 * 返回 shareholder 的值   
	 * @return shareholder  
	 */
	public String getShareholder() {
		return  shareholder;
	}
	/**  
	 * 设置  shareholder 的值  
	 * @param  shareholder
	 */
	public void setShareholder(String  shareholder) {
		this. shareholder =  shareholder;
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
	 * 返回 cardType 的值   
	 * @return cardType  
	 */
	public String getCardType() {
		return  cardType;
	}
	/**  
	 * 设置  cardType 的值  
	 * @param  cardType
	 */
	public void setCardType(String  cardType) {
		this. cardType =  cardType;
	}

    /**  
	 * 返回 cardId 的值   
	 * @return cardId  
	 */
	public String getCardId() {
		return  cardId;
	}
	/**  
	 * 设置  cardId 的值  
	 * @param  cardId
	 */
	public void setCardId(String  cardId) {
		this. cardId =  cardId;
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
	@Override
	public String toString() {
		return "IndustryCommerce_shareholder [id=" + id + ", regNumber="
				+ regNumber + ", shareholder=" + shareholder + ", type=" + type
				+ ", cardType=" + cardType + ", cardId=" + cardId
				+ ", details=" + details + "]";
	}
}
