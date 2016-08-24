package  com.heetian.spider.mysql.model;
/**
 * 股东详情
 * @author tst
 *
 */
public class Gdxx {

    private int id;  
    /**
     * uuid
     */
    private String uuid;
    private String regNumber; 
    /**
     * 股东名称
     */
    private String inv;  
    /**
     * 股东类型
     */
    private String invType;  
    /**
     * 认缴额
     */
    private String renjiaoe; 
    /**
     * 实缴额
     */
    private String shijiaoe; 
    /**
     * 认缴方式
     */
    private String renjiaoMethod;  
    /**
     * 认缴出资额
     */
    private String renjiaochuzie; 
    /**
     * 认缴日期
     */
    private String renjiaoDate;
    private String shijiaoMethod;
    private String shijiaochuzie;
    private String shijiaoDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getInv() {
		return inv;
	}
	public void setInv(String inv) {
		this.inv = inv;
	}
	public String getRenjiaoe() {
		return renjiaoe;
	}
	public void setRenjiaoe(String renjiaoe) {
		this.renjiaoe = renjiaoe;
	}
	public String getShijiaoe() {
		return shijiaoe;
	}
	public void setShijiaoe(String shijiaoe) {
		this.shijiaoe = shijiaoe;
	}
	public String getRenjiaoMethod() {
		return renjiaoMethod;
	}
	public void setRenjiaoMethod(String renjiaoMethod) {
		this.renjiaoMethod = renjiaoMethod;
	}
	public String getRenjiaochuzie() {
		return renjiaochuzie;
	}
	public void setRenjiaochuzie(String renjiaochuzie) {
		this.renjiaochuzie = renjiaochuzie;
	}
	public String getRenjiaoDate() {
		return renjiaoDate;
	}
	public void setRenjiaoDate(String renjiaoDate) {
		this.renjiaoDate = renjiaoDate;
	}
	public String getShijiaoMethod() {
		return shijiaoMethod;
	}
	public void setShijiaoMethod(String shijiaoMethod) {
		this.shijiaoMethod = shijiaoMethod;
	}
	public String getShijiaochuzie() {
		return shijiaochuzie;
	}
	public void setShijiaochuzie(String shijiaochuzie) {
		this.shijiaochuzie = shijiaochuzie;
	}
	public String getShijiaoDate() {
		return shijiaoDate;
	}
	public void setShijiaoDate(String shijiaoDate) {
		this.shijiaoDate = shijiaoDate;
	}
	public String getInvType() {
		return invType;
	}
	public void setInvType(String invType) {
		this.invType = invType;
	}
	@Override
	public String toString() {
		return "Gdxx [id=" + id + ", uuid=" + uuid + ", regNumber=" + regNumber
				+ ", inv=" + inv + ", invType=" + invType + ", renjiaoe="
				+ renjiaoe + ", shijiaoe=" + shijiaoe + ", renjiaoMethod="
				+ renjiaoMethod + ", renjiaochuzie=" + renjiaochuzie
				+ ", renjiaoDate=" + renjiaoDate + ", shijiaoMethod="
				+ shijiaoMethod + ", shijiaochuzie=" + shijiaochuzie
				+ ", shijiaoDate=" + shijiaoDate + "]";
	}
}
