package  com.heetian.spider.dbcp.bean;


/**
 * 登记信息，股东信息，股东详情信息表
 * @author tst
 *
 */
public class GsgsShareholderDetail {
    /**
     * 与股东存在关联的关系字段
     */
    private String uuid;
    /**
     * 注册号
     */
    private String regNumber; 
    /**
     * 股东类型
     */
    private String invType;  
    /**
     * 股东名称,inv
     */
    private String name;  
    /**
     * 认缴总额,renjiaoe
     */
    private String stmn; 
    /**
     * 实缴总额，shijiaoe
     */
    private String ptmn; 
    /**
     * 认缴方式,renjiaoMethod
     */
    private String sfrm;  
    /**
     * 认缴出资额，renjiaochuzie
     */
    private String smn; 
    /**
     * 认缴日期,renjiaoDate
     */
    private String sd;
    /**
     * 实缴方式，shijiaoMethod
     */
    private String pfrm;
    /**
     * 实缴出资额，shijiaochuzie
     */
    private String pmn;
    /**
     * 实缴日期,shijiaoDate
     */
    private String pd;
    
	/**
	 * 与股东存在关联的关系字段
	 * @return
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * 与股东存在关联的关系字段
	 * @param uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 注册号
	 * @return
	 */
	public String getRegNumber() {
		return regNumber;
	}
	/**
	 * 注册号
	 * @param regNumber
	 */
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	/**
	 * 股东名称,inv
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 股东名称,inv
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 股东类型
	 * @return
	 */
	public String getInvType() {
		return invType;
	}
	/**
	 * 股东类型
	 * @param invType
	 */
	public void setInvType(String invType) {
		this.invType = invType;
	}
	/**
	 * 认缴总额,renjiaoe
	 * @return
	 */
	public String getStmn() {
		return stmn;
	}
	/**
	 * 认缴总额,renjiaoe
	 * @param stmn
	 */
	public void setStmn(String stmn) {
		this.stmn = stmn;
	}
	/**
	 * 实缴总额，shijiaoe
	 * @return
	 */
	public String getPtmn() {
		return ptmn;
	}
	/**
	 * 实缴总额，shijiaoe
	 * @param ptmn
	 */
	public void setPtmn(String ptmn) {
		this.ptmn = ptmn;
	}
	/**
	 * 认缴方式,renjiaoMethod
	 * @return
	 */
	public String getSfrm() {
		return sfrm;
	}
	/**
	 * 认缴方式,renjiaoMethod
	 * @param sfrm
	 */
	public void setSfrm(String sfrm) {
		this.sfrm = sfrm;
	}
	/**
	 * 认缴出资额，renjiaochuzie
	 * @return
	 */
	public String getSmn() {
		return smn;
	}
	/**
	 * 认缴出资额，renjiaochuzie
	 * @param smn
	 */
	public void setSmn(String smn) {
		this.smn = smn;
	}
	/**
	 * 认缴日期,renjiaoDate
	 * @return
	 */
	public String getSd() {
		return sd;
	}
	/**
	 * 认缴日期,renjiaoDate
	 * @param sd
	 */
	public void setSd(String sd) {
		this.sd = sd;
	}
	/**
	 * 实缴方式，shijiaoMethod
	 * @return
	 */
	public String getPfrm() {
		return pfrm;
	}
	/**
	 * 实缴方式，shijiaoMethod
	 * @param pfrm
	 */
	public void setPfrm(String pfrm) {
		this.pfrm = pfrm;
	}
	/**
	 * 实缴出资额，shijiaochuzie
	 * @return
	 */
	public String getPmn() {
		return pmn;
	}
	/**
	 * 实缴出资额，shijiaochuzie
	 * @param pmn
	 */
	public void setPmn(String pmn) {
		this.pmn = pmn;
	}
	/**
	 * 实缴日期,shijiaoDate
	 * @return
	 */
	public String getPd() {
		return pd;
	}
	/**
	 * 实缴日期,shijiaoDate
	 * @param pd
	 */
	public void setPd(String pd) {
		this.pd = pd;
	}
    public boolean check(){
    	if(!checkSTR(invType)&&!checkSTR(name)&&!checkSTR(stmn)
    			&&!checkSTR(ptmn)&&!checkSTR(sfrm)&&!checkSTR(smn)
    			&&!checkSTR(sd)&&!checkSTR(pfrm)&&!checkSTR(pmn)
    			&&!checkSTR(pd))
    		return false;
    	return true;
    }
    public boolean checkSTR(String str){
		if(str==null)
			return false;
		if("".equals(str.replaceAll("\\s*", ""))){
			return false;
		}
		return true;
	}
}
