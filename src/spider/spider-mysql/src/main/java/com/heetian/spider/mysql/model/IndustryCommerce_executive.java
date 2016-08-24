package  com.heetian.spider.mysql.model;
/**
 * <p>
 * Title: IndustryCommerce_executive
 * </p>
 * <p>
 * Description: 工商公示信息_主管部门（出资人）信息Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Wed Mar 18 15:32:52 CST 2015
 */
public class IndustryCommerce_executive {

    private int id;  //id
    private String regNumber;  //注册号
    private String type;  //出资人类型
    private String investor;  //出资人
    private String cardType;  //证件类型
    private String cardNumber;  //证件号


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
	 * 返回 investor 的值   
	 * @return investor  
	 */
	public String getInvestor() {
		return  investor;
	}
	/**  
	 * 设置  investor 的值  
	 * @param  investor
	 */
	public void setInvestor(String  investor) {
		this. investor =  investor;
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
	 * 返回 cardNumber 的值   
	 * @return cardNumber  
	 */
	public String getCardNumber() {
		return  cardNumber;
	}
	/**  
	 * 设置  cardNumber 的值  
	 * @param  cardNumber
	 */
	public void setCardNumber(String  cardNumber) {
		this. cardNumber =  cardNumber;
	}
	@Override
	public String toString() {
		return "IndustryCommerce_executive [id=" + id + ", regNumber="
				+ regNumber + ", type=" + type + ", investor=" + investor
				+ ", cardType=" + cardType + ", cardNumber=" + cardNumber + "]";
	}
	
}
