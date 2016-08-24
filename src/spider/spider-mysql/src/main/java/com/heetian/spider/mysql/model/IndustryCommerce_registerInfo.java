package  com.heetian.spider.mysql.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * Title: IndustryCommerce_registerInfo
 * </p>
 * <p>
 * Description: 工商公示信息_登记信息表Bean
 * </p>
 * 
 * @author heetian
 * @version 1.0
 * @created Tue Apr 07 17:41:38 CST 2015
 */
public class IndustryCommerce_registerInfo {
	private String pro;//省份字段
    private int id;  //id
    private String regNumber;  //注册号
    private String credit;  //统一信用代码
    private String name;  //名称
    private String type;  //类型
    private String legalPerson;  //法定代表人
    private String address;  //住所
    private String registeredCapital;  //注册资本
    private String establishDate;  //成立日期
    private String datebegin;  //经营期限自
    private String dateend;  //经营期限至
    private String ranges;  //经营范围
    private String registerOrgan;  //登记机关
    private String approvalDate;  //核准日期
    private String regStatus;  //登记状态
    private String manager;  //经营者
    private String managerPlace;  //经营场所
    private String Form;  //组成形式
    private String regDate;  //注册日期
    private String revokeDate;  //吊销日期
    private String businessScope;  //业务范围
    private String totalAmount;  //成员出资总额
    private String investor;  //投资人
    private String busDatebegin;  //经营期限自
    private String busDateend;  //经营期限至

    private String chargePerson; //负责人
    private String busPlace; //营业场所
    
    private String managingPartner;//执行事务合伙人
    private String partnerDatebegin; //合伙期限自
    private String partnerDateend; //合伙期限至
    private String mainManagerplace; //主要经营场所
    private Date lastUpdatetime;//最后修改时间
    private String url;
    private List<IndustryCommerce_shareholder> holderList=new ArrayList<IndustryCommerce_shareholder>(); 

    private List<IndustryCommerce_changeInfo> changeinfoList=new ArrayList<IndustryCommerce_changeInfo>();
   
    public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	public Date getLastUpdatetime() {
		return lastUpdatetime;
	}
	public void setLastUpdatetime(Date lastUpdatetime) {
		this.lastUpdatetime = lastUpdatetime;
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
	 * 返回 legalPerson 的值   
	 * @return legalPerson  
	 */
	public String getLegalPerson() {
		return  legalPerson;
	}
	/**  
	 * 设置  legalPerson 的值  
	 * @param  legalPerson
	 */
	public void setLegalPerson(String  legalPerson) {
		this. legalPerson =  legalPerson;
	}

    /**  
	 * 返回 address 的值   
	 * @return address  
	 */
	public String getAddress() {
		return  address;
	}
	/**  
	 * 设置  address 的值  
	 * @param  address
	 */
	public void setAddress(String  address) {
		this. address =  address;
	}

    /**  
	 * 返回 registeredCapital 的值   
	 * @return registeredCapital  
	 */
	public String getRegisteredCapital() {
		return  registeredCapital;
	}
	/**  
	 * 设置  registeredCapital 的值  
	 * @param  registeredCapital
	 */
	public void setRegisteredCapital(String  registeredCapital) {
		this. registeredCapital =  registeredCapital;
	}

    /**  
	 * 返回 establishDate 的值   
	 * @return establishDate  
	 */
	public String getEstablishDate() {
		return  establishDate;
	}
	/**  
	 * 设置  establishDate 的值  
	 * @param  establishDate
	 */
	public void setEstablishDate(String  establishDate) {
		this. establishDate =  establishDate;
	}

    /**  
	 * 返回 datebegin 的值   
	 * @return datebegin  
	 */
	public String getDatebegin() {
		return  datebegin;
	}
	/**  
	 * 设置  datebegin 的值  
	 * @param  datebegin
	 */
	public void setDatebegin(String  datebegin) {
		this. datebegin =  datebegin;
	}

    /**  
	 * 返回 dateend 的值   
	 * @return dateend  
	 */
	public String getDateend() {
		return  dateend;
	}
	/**  
	 * 设置  dateend 的值  
	 * @param  dateend
	 */
	public void setDateend(String  dateend) {
		this. dateend =  dateend;
	}

    /**  
	 * 返回 ranges 的值   
	 * @return ranges  
	 */
	public String getRanges() {
		return  ranges;
	}
	/**  
	 * 设置  ranges 的值  
	 * @param  ranges
	 */
	public void setRanges(String  ranges) {
		this. ranges =  ranges;
	}

    /**  
	 * 返回 registerOrgan 的值   
	 * @return registerOrgan  
	 */
	public String getRegisterOrgan() {
		return  registerOrgan;
	}
	/**  
	 * 设置  registerOrgan 的值  
	 * @param  registerOrgan
	 */
	public void setRegisterOrgan(String  registerOrgan) {
		this. registerOrgan =  registerOrgan;
	}

    /**  
	 * 返回 approvalDate 的值   
	 * @return approvalDate  
	 */
	public String getApprovalDate() {
		return  approvalDate;
	}
	/**  
	 * 设置  approvalDate 的值  
	 * @param  approvalDate
	 */
	public void setApprovalDate(String  approvalDate) {
		this. approvalDate =  approvalDate;
	}

    /**  
	 * 返回 regStatus 的值   
	 * @return regStatus  
	 */
	public String getRegStatus() {
		return  regStatus;
	}
	/**  
	 * 设置  regStatus 的值  
	 * @param  regStatus
	 */
	public void setRegStatus(String  regStatus) {
		this. regStatus =  regStatus;
	}

    /**  
	 * 返回 manager 的值   
	 * @return manager  
	 */
	public String getManager() {
		return  manager;
	}
	/**  
	 * 设置  manager 的值  
	 * @param  manager
	 */
	public void setManager(String  manager) {
		this. manager =  manager;
	}

    /**  
	 * 返回 managerPlace 的值   
	 * @return managerPlace  
	 */
	public String getManagerPlace() {
		return  managerPlace;
	}
	/**  
	 * 设置  managerPlace 的值  
	 * @param  managerPlace
	 */
	public void setManagerPlace(String  managerPlace) {
		this. managerPlace =  managerPlace;
	}

    /**  
	 * 返回 Form 的值   
	 * @return Form  
	 */
	public String getForm() {
		return  Form;
	}
	/**  
	 * 设置  Form 的值  
	 * @param  Form
	 */
	public void setForm(String  Form) {
		this. Form =  Form;
	}

    /**  
	 * 返回 regDate 的值   
	 * @return regDate  
	 */
	public String getRegDate() {
		return  regDate;
	}
	/**  
	 * 设置  regDate 的值  
	 * @param  regDate
	 */
	public void setRegDate(String  regDate) {
		this. regDate =  regDate;
	}

    /**  
	 * 返回 revokeDate 的值   
	 * @return revokeDate  
	 */
	public String getRevokeDate() {
		return  revokeDate;
	}
	/**  
	 * 设置  revokeDate 的值  
	 * @param  revokeDate
	 */
	public void setRevokeDate(String  revokeDate) {
		this. revokeDate =  revokeDate;
	}

    /**  
	 * 返回 businessScope 的值   
	 * @return businessScope  
	 */
	public String getBusinessScope() {
		return  businessScope;
	}
	/**  
	 * 设置  businessScope 的值  
	 * @param  businessScope
	 */
	public void setBusinessScope(String  businessScope) {
		this. businessScope =  businessScope;
	}

    /**  
	 * 返回 totalAmount 的值   
	 * @return totalAmount  
	 */
	public String getTotalAmount() {
		return  totalAmount;
	}
	/**  
	 * 设置  totalAmount 的值  
	 * @param  totalAmount
	 */
	public void setTotalAmount(String  totalAmount) {
		this. totalAmount =  totalAmount;
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
	 * 返回 busDatebegin 的值   
	 * @return busDatebegin  
	 */
	public String getBusDatebegin() {
		return  busDatebegin;
	}
	/**  
	 * 设置  busDatebegin 的值  
	 * @param  busDatebegin
	 */
	public void setBusDatebegin(String  busDatebegin) {
		this. busDatebegin =  busDatebegin;
	}

    /**  
	 * 返回 busDateend 的值   
	 * @return busDateend  
	 */
	public String getBusDateend() {
		return  busDateend;
	}
	/**  
	 * 设置  busDateend 的值  
	 * @param  busDateend
	 */
	public void setBusDateend(String  busDateend) {
		this. busDateend =  busDateend;
	}
	public List<IndustryCommerce_shareholder> getHolderList() {
		return holderList;
	}
	public void setHolderList(List<IndustryCommerce_shareholder> holderList) {
		this.holderList = holderList;
	}
	public List<IndustryCommerce_changeInfo> getChangeinfoList() {
		return changeinfoList;
	}
	public void setChangeinfoList(List<IndustryCommerce_changeInfo> changeinfoList) {
		this.changeinfoList = changeinfoList;
	}
	public String getChargePerson() {
		return chargePerson;
	}
	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}
	public String getBusPlace() {
		return busPlace;
	}
	public void setBusPlace(String busPlace) {
		this.busPlace = busPlace;
	}
	public String getManagingPartner() {
		return managingPartner;
	}
	public void setManagingPartner(String managingPartner) {
		this.managingPartner = managingPartner;
	}
	public String getPartnerDatebegin() {
		return partnerDatebegin;
	}
	public void setPartnerDatebegin(String partnerDatebegin) {
		this.partnerDatebegin = partnerDatebegin;
	}
	public String getPartnerDateend() {
		return partnerDateend;
	}
	public void setPartnerDateend(String partnerDateend) {
		this.partnerDateend = partnerDateend;
	}
	public String getMainManagerplace() {
		return mainManagerplace;
	}
	public void setMainManagerplace(String mainManagerplace) {
		this.mainManagerplace = mainManagerplace;
	}
	@Override
	public String toString() {
		return "IndustryCommerce_registerInfo [id=" + id + ", regNumber="
				+ regNumber + ", name=" + name + ", type=" + type
				+ ", legalPerson=" + legalPerson + ", address=" + address
				+ ", registeredCapital=" + registeredCapital
				+ ", establishDate=" + establishDate + ", datebegin="
				+ datebegin + ", dateend=" + dateend + ", ranges=" + ranges
				+ ", registerOrgan=" + registerOrgan + ", approvalDate="
				+ approvalDate + ", regStatus=" + regStatus + ", manager="
				+ manager + ", managerPlace=" + managerPlace + ", Form=" + Form
				+ ", regDate=" + regDate + ", revokeDate=" + revokeDate
				+ ", businessScope=" + businessScope + ", totalAmount="
				+ totalAmount + ", investor=" + investor + ", busDatebegin="
				+ busDatebegin + ", busDateend=" + busDateend
				+ ", chargePerson=" + chargePerson + ", busPlace=" + busPlace
				+ ", managingPartner=" + managingPartner
				+ ", partnerDatebegin=" + partnerDatebegin
				+ ", partnerDateend=" + partnerDateend + ", mainManagerplace="
				+ mainManagerplace + ", lastUpdatetime=" + lastUpdatetime
				+ ", url=" + url + ", holderList=" + holderList
				+ ", changeinfoList=" + changeinfoList + "]";
	}
}
