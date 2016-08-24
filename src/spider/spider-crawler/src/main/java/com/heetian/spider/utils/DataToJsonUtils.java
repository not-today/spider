package com.heetian.spider.utils;


public class DataToJsonUtils {
	//private static Logger logger = LoggerFactory.getLogger(DataToJsonUtils.class);
	/**
	 * 将databean对象转成company对象
	 * @param bean
	 * @return
	 */
	/*private static CompanyComplex toCompany(DataBean bean){
		CompanyComplex cy = setJbxx(bean.getJbxxBean());
		cy.setShareholders(gd(bean.getGdxxs(), bean.getGdxxxxs()));
		cy.setChanges(bg(bean.getBgxxs()));
		cy.setKeyPersons(zy(bean.getZyryxxs()));
		cy.setBranchs(fz(bean.getFzjgxxs()));
		return cy;
	}
	private static List<Change> bg(List<IndustryCommerce_changeInfo> bgxxs) {
		if(bgxxs==null||bgxxs.size()<=0)
			return null;
		List<Change> chs = new ArrayList<Change>();
		for(IndustryCommerce_changeInfo bg:bgxxs){
			Change ch = new Change();
			ch.setType(bg.getAlterations());
			ch.setChs(bg.getBeginContent());
			ch.setChn(bg.getEndContent());
			ch.setDate(bg.getDate());
			chs.add(ch);
		}
		if(chs.size()<=0)
			return null;
		return chs;
	}
	private static List<KeyPerson> zy(List<IndustryCommerce_mainPersonel> zyryxxs) {
		if(zyryxxs==null||zyryxxs.size()<=0)
			return null;
		List<KeyPerson> kps = new ArrayList<KeyPerson>();
		for(IndustryCommerce_mainPersonel zy:zyryxxs){
			KeyPerson kp = new KeyPerson();
			kp.setName(zy.getName());
			kp.setPos(zy.getPosition());
			kps.add(kp);
		}
		if(kps.size()<=0)
			return null;
		return kps;
	}
	private static List<Branch> fz(List<IndustryCommerce_branchInfo> fzjgxxs) {
		if(fzjgxxs==null||fzjgxxs.size()<=0)
			return null;
		List<Branch> bcs = new ArrayList<Branch>();
		for(IndustryCommerce_branchInfo fz:fzjgxxs){
			Branch bc = new Branch();
			bc.setName(fz.getBrname());
			bc.setGov(fz.getRegorg());
			bc.setRgc(fz.getRegno());
			bcs.add(bc);
		}
		if(bcs.size()<=0)
			return null;
		return bcs;
	}
	private static List<Shareholder> gd(List<IndustryCommerce_shareholder> gdxxs, List<Gdxx> gdxxxxs) {
		if(gdxxs==null||gdxxs.size()<=0)
			return null;
		List<Shareholder> shs = new ArrayList<Shareholder>();
		for(IndustryCommerce_shareholder gd:gdxxs){
			Shareholder sh = new Shareholder();
			String uuid = gd.getDetails();
			sh.setContributives(gdxx(gdxxxxs, uuid));
			sh.setSid(uuid);
			sh.setName(gd.getShareholder());
			sh.setType(gd.getType());
			sh.setCrdt(gd.getCardType());
			sh.setCrdc(gd.getCardId());
			sh.setIvtf(gd.getIvtf());//新增
			shs.add(sh);
		}
		if(shs.size()<=0)
			return null;
		return shs;
	}
	private static List<Contributive> gdxx(List<Gdxx> gdxxxxs, String uuid) {
		if(gdxxxxs==null||gdxxxxs.size()<=0)
			return null;
		List<Contributive> cbs = new ArrayList<Contributive>();
		for(Gdxx gdxx:gdxxxxs){
			if(uuid!=null&&uuid.equals(gdxx.getUuid())){
				Contributive cb = new Contributive();
				cb.setSid(uuid);
				cb.setName(gdxx.getInv());
				cb.setStmn(gdxx.getRenjiaoe());
				cb.setPtmn(gdxx.getShijiaoe());
				
				cb.setSmn(gdxx.getRenjiaochuzie());
				cb.setSd(gdxx.getRenjiaoDate());
				cb.setSfrm(gdxx.getShijiaoMethod());
				
				cb.setPmn(gdxx.getShijiaochuzie());
				cb.setPd(gdxx.getShijiaoDate());
				cb.setPfrm(gdxx.getShijiaoMethod());
				cbs.add(cb);
			}
		}
		if(cbs.size()<=0)
			return null;
		return cbs;
	}
	private static CompanyComplex setJbxx(IndustryCommerce_registerInfo jbxxBean) throws IllegalArgumentException{
		if(jbxxBean==null){
			throw new IllegalArgumentException("IndustryCommerce_registerInfo对象为空");
		}
		CompanyComplex cy = new CompanyComplex();
		String regNumber = jbxxBean.getRegNumber();
		if(regNumber==null||"".equals(regNumber.trim())){
			throw new IllegalArgumentException("IndustryCommerce_registerInfo对象中的注册码为空");
		}
		cy.setPvn(jbxxBean.getPro());
		cy.setRgc(regNumber);
		cy.setNsc(jbxxBean.getCredit());//新增
		cy.setName(jbxxBean.getName());
		cy.setType(jbxxBean.getType());
		cy.setLp(jbxxBean.getLegalPerson());
		cy.setAddr(jbxxBean.getAddress());
		cy.setRc(jbxxBean.getRegisteredCapital());
		cy.setFdd(jbxxBean.getEstablishDate());
		cy.setMds(jbxxBean.getDatebegin());
		cy.setMdn(jbxxBean.getDateend());
		cy.setRgs(jbxxBean.getRanges());
		cy.setGov(jbxxBean.getRegisterOrgan());
		cy.setApd(jbxxBean.getApprovalDate());
		cy.setStt(jbxxBean.getRegStatus());
		cy.setMng(jbxxBean.getManager());
		cy.setMddr(jbxxBean.getManagerPlace());
		cy.setFrm(jbxxBean.getForm());
		cy.setRgd(jbxxBean.getRegDate());
		cy.setRvd(jbxxBean.getRevokeDate());
		cy.setBns(jbxxBean.getBusinessScope());
		cy.setTmn(jbxxBean.getTotalAmount());
		cy.setIvt(jbxxBean.getInvestor());
		cy.setBds(jbxxBean.getBusDatebegin());
		cy.setBdn(jbxxBean.getBusDateend());
		cy.setCp(jbxxBean.getChargePerson());
		cy.setBddr(jbxxBean.getBusPlace());
		cy.setMp(jbxxBean.getManagingPartner());
		cy.setPds(jbxxBean.getPartnerDatebegin());
		cy.setPdn(jbxxBean.getPartnerDateend());
		cy.setKddr(jbxxBean.getMainManagerplace());
		cy.setUrl(jbxxBean.getUrl());
		return cy;
	}*/
}
