package  com.heetian.spider.dbcp.bean;
/**
 * 登记信息，主要人员信息表
 * @author tst
 *
 */
public class GsgsMainPersonel {
    private String regNumber; 
    /**
     * 姓名，name
     */
    private String name; 
    /**
     * 职务,position
     */
    private String pos;
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	/**
	 * 姓名，name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 姓名，name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 职务,position
	 * @return
	 */
	public String getPos() {
		return pos;
	}
	/**
	 * 职务,position
	 * @param pos
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}
}
