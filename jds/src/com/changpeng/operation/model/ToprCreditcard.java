package com.changpeng.operation.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ToprCreditcard entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ToprCreditcard implements java.io.Serializable {

	// Fields
	private String bianhao;
	private long creditcardid;
	private String username;
	private String creditcard;
	private String idcard;
	private String consigndate;
	private String cnfee;
	private String usafee;
	private String hkfee;
	private String eurfee;
	private String overstat;
	private String maxfee;
	private String consigntype;
	private String consignflag;
	private String applynum;
	private String dossiernum;
	private String mobileold;
	private String homephoneold;
	private String workphoneold;

	private String company;
	private String compaddr;
	private String homeaddr;
	private String idcardaddr;
	private String billpostcode;
	private String billaddr;
	private String billpostaddr;
	private String email;
	private String contactpeople1;
	private String contactp1phone1;
	private String contactp1phone2;
	private String contactp1phone3;
	private String contactpeople2;
	private String contactp2phone1;
	private String contactp2phone2;
	private String contactp2phone3;
	private String cautioner;
	private String caucompany;
	private String caucompaddr;
	private String cauhomeaddr;
	private String cauworkphone;
	private String cauhomephone;
	private String caumobile;
	private int state;
	private long bankid;
	
	private java.util.Date createtime;
	
	private String refee;
	private String remonthfee;
	private int repaystatus;
	private int lawflag;
	private String kaihudata;
	private String guiyuanno;
	private java.util.Date updatetime;
	
	
	private String curcnfee;
	private String curusafee;
	private String curhkfee;
	private String cureurfee;
	private String comments;
	
	private Set toprRepaylogs=new HashSet(0);
	// Constructors

	private Set toprCreditlogs=new HashSet(0);
	private int tdflag; //退单标记
	
	private String tddate; //退单日期
	
	public String getTddate() {
		return tddate;
	}
	public void setTddate(String tddate) {
		this.tddate = tddate;
	}
	public int getTdflag() {
		return tdflag;
	}
	public void setTdflag(int tdflag) {
		this.tdflag = tdflag;
	}
	public Set getToprRepaylogs() {
		return toprRepaylogs;
	}


	public void setToprRepaylogs(Set toprRepaylogs) {
		this.toprRepaylogs = toprRepaylogs;
	}


	public java.util.Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}


	/** default constructor */
	public ToprCreditcard() {
	}

	
	// Property accessors

	public long getCreditcardid() {
		return this.creditcardid;
	}

	public void setCreditcardid(long creditcardid) {
		this.creditcardid = creditcardid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCreditcard() {
		return this.creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getConsigndate() {
		return this.consigndate;
	}

	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}

	public String getCnfee() {
		return this.cnfee;
	}

	public void setCnfee(String cnfee) {
		this.cnfee = cnfee;
	}

	public String getUsafee() {
		return this.usafee;
	}

	public void setUsafee(String usafee) {
		this.usafee = usafee;
	}

	public String getHkfee() {
		return this.hkfee;
	}

	public void setHkfee(String hkfee) {
		this.hkfee = hkfee;
	}

	public String getEurfee() {
		return this.eurfee;
	}

	public void setEurfee(String eurfee) {
		this.eurfee = eurfee;
	}

	public String getOverstat() {
		return this.overstat;
	}

	public void setOverstat(String overstat) {
		this.overstat = overstat;
	}

	public String getMaxfee() {
		return this.maxfee;
	}

	public void setMaxfee(String maxfee) {
		this.maxfee = maxfee;
	}

	public String getConsigntype() {
		return this.consigntype;
	}

	public void setConsigntype(String consigntype) {
		this.consigntype = consigntype;
	}

	public String getConsignflag() {
		return this.consignflag;
	}

	public void setConsignflag(String consignflag) {
		this.consignflag = consignflag;
	}

	public String getApplynum() {
		return this.applynum;
	}

	public void setApplynum(String applynum) {
		this.applynum = applynum;
	}

	public String getDossiernum() {
		return this.dossiernum;
	}

	public void setDossiernum(String dossiernum) {
		this.dossiernum = dossiernum;
	}

	public String getMobileold() {
		return this.mobileold;
	}

	public void setMobileold(String mobileold) {
		this.mobileold = mobileold;
	}

	public String getHomephoneold() {
		return this.homephoneold;
	}

	public void setHomephoneold(String homephoneold) {
		this.homephoneold = homephoneold;
	}

	public String getWorkphoneold() {
		return this.workphoneold;
	}

	public void setWorkphoneold(String workphoneold) {
		this.workphoneold = workphoneold;
	}


	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompaddr() {
		return this.compaddr;
	}

	public void setCompaddr(String compaddr) {
		this.compaddr = compaddr;
	}

	public String getHomeaddr() {
		return this.homeaddr;
	}

	public void setHomeaddr(String homeaddr) {
		this.homeaddr = homeaddr;
	}

	public String getIdcardaddr() {
		return this.idcardaddr;
	}

	public void setIdcardaddr(String idcardaddr) {
		this.idcardaddr = idcardaddr;
	}

	public String getBillpostcode() {
		return this.billpostcode;
	}

	public void setBillpostcode(String billpostcode) {
		this.billpostcode = billpostcode;
	}

	public String getBilladdr() {
		return this.billaddr;
	}

	public void setBilladdr(String billaddr) {
		this.billaddr = billaddr;
	}

	public String getBillpostaddr() {
		return this.billpostaddr;
	}

	public void setBillpostaddr(String billpostaddr) {
		this.billpostaddr = billpostaddr;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactpeople1() {
		return this.contactpeople1;
	}

	public void setContactpeople1(String contactpeople1) {
		this.contactpeople1 = contactpeople1;
	}

	public String getContactp1phone1() {
		return this.contactp1phone1;
	}

	public void setContactp1phone1(String contactp1phone1) {
		this.contactp1phone1 = contactp1phone1;
	}

	public String getContactp1phone2() {
		return this.contactp1phone2;
	}

	public void setContactp1phone2(String contactp1phone2) {
		this.contactp1phone2 = contactp1phone2;
	}

	public String getContactp1phone3() {
		return this.contactp1phone3;
	}

	public void setContactp1phone3(String contactp1phone3) {
		this.contactp1phone3 = contactp1phone3;
	}

	public String getContactpeople2() {
		return this.contactpeople2;
	}

	public void setContactpeople2(String contactpeople2) {
		this.contactpeople2 = contactpeople2;
	}

	public String getContactp2phone1() {
		return this.contactp2phone1;
	}

	public void setContactp2phone1(String contactp2phone1) {
		this.contactp2phone1 = contactp2phone1;
	}

	public String getContactp2phone2() {
		return this.contactp2phone2;
	}

	public void setContactp2phone2(String contactp2phone2) {
		this.contactp2phone2 = contactp2phone2;
	}

	public String getContactp2phone3() {
		return this.contactp2phone3;
	}

	public void setContactp2phone3(String contactp2phone3) {
		this.contactp2phone3 = contactp2phone3;
	}

	public String getCautioner() {
		return this.cautioner;
	}

	public void setCautioner(String cautioner) {
		this.cautioner = cautioner;
	}

	public String getCaucompany() {
		return this.caucompany;
	}

	public void setCaucompany(String caucompany) {
		this.caucompany = caucompany;
	}

	public String getCaucompaddr() {
		return this.caucompaddr;
	}

	public void setCaucompaddr(String caucompaddr) {
		this.caucompaddr = caucompaddr;
	}

	public String getCauhomeaddr() {
		return this.cauhomeaddr;
	}

	public void setCauhomeaddr(String cauhomeaddr) {
		this.cauhomeaddr = cauhomeaddr;
	}

	public String getCauworkphone() {
		return this.cauworkphone;
	}

	public void setCauworkphone(String cauworkphone) {
		this.cauworkphone = cauworkphone;
	}

	public String getCauhomephone() {
		return this.cauhomephone;
	}

	public void setCauhomephone(String cauhomephone) {
		this.cauhomephone = cauhomephone;
	}

	public String getCaumobile() {
		return this.caumobile;
	}

	public void setCaumobile(String caumobile) {
		this.caumobile = caumobile;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public long getBankid() {
		return this.bankid;
	}

	public void setBankid(long bankid) {
		this.bankid = bankid;
	}


	public String getRefee() {
		return refee;
	}


	public String getRemonthfee() {
		return remonthfee;
	}


	public int getRepaystatus() {
		return repaystatus;
	}


	public int getLawflag() {
		return lawflag;
	}


	public String getKaihudata() {
		return kaihudata;
	}


	public String getGuiyuanno() {
		return guiyuanno;
	}


	public java.util.Date getUpdatetime() {
		return updatetime;
	}


	public void setRefee(String refee) {
		this.refee = refee;
	}


	public void setRemonthfee(String remonthfee) {
		this.remonthfee = remonthfee;
	}


	public void setRepaystatus(int repaystatus) {
		this.repaystatus = repaystatus;
	}


	public void setLawflag(int lawflag) {
		this.lawflag = lawflag;
	}


	public void setKaihudata(String kaihudata) {
		this.kaihudata = kaihudata;
	}


	public void setGuiyuanno(String guiyuanno) {
		this.guiyuanno = guiyuanno;
	}


	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}


	public String getCurcnfee() {
		return curcnfee;
	}


	public String getCurusafee() {
		return curusafee;
	}


	public String getCurhkfee() {
		return curhkfee;
	}


	public String getCureurfee() {
		return cureurfee;
	}


	public void setCurcnfee(String curcnfee) {
		this.curcnfee = curcnfee;
	}


	public void setCurusafee(String curusafee) {
		this.curusafee = curusafee;
	}


	public void setCurhkfee(String curhkfee) {
		this.curhkfee = curhkfee;
	}


	public void setCureurfee(String cureurfee) {
		this.cureurfee = cureurfee;
	}


	public Set getToprCreditlogs() {
		return toprCreditlogs;
	}


	public void setToprCreditlogs(Set toprCreditlogs) {
		this.toprCreditlogs = toprCreditlogs;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the bianhao
	 */
	public String getBianhao() {
		return bianhao;
	}
	/**
	 * @param bianhao the bianhao to set
	 */
	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

}