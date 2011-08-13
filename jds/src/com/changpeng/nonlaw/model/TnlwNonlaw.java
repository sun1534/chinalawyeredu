package com.changpeng.nonlaw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TnlwNonlaw entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TnlwNonlaw implements java.io.Serializable {

	// Fields

	private long nonlawid;
	private String username;
	private String duebill;
	private String lendaccount;
	private String payaccount;
	private String synergicname;
	private String idcard;
	private String lendfee;
	private String balancefee;
	private String overfee;
	private String accrualfee;
	private String castfee;
//	private String overnum;
	private int overnum;
	private String monthfee;
	private String breachfee;
	private String lenddate;
	private String lendoverdate;
	private String projectname;
	private String buyaddr;
	private String homeaddr;
	private String homephoneold;
	private String mobileold;
	private String company;
	private String companyaddr;
	private String companyphone;
	private String alterphone;
	private int state;
	private long bankid;
	private Date createtime;
	private String consigndate;
	private String comments;
	private String lendadmin;
	private String overstat;
	
	private String refee;
	private String remonthfee;
	private int repaystatus;
	private int lawflag;
	private java.util.Date updatetime;
	private String overallfee;
	
	private float curoverstat;
	private String curbalancefee;
	private String curoverfee;
	private String curaccrualfee;
	
	private String bankname; //支行名称
	
	private Set tnlwRepaylogs = new HashSet(0);
	private Set tnlwNonlawlogs = new HashSet(0);
	
	private int tdflag; //退单标记
	
	private String orisigndate; //原委托日期
	private String pici; //导入批次
	
	public String getPici() {
		return pici;
	}
	public void setPici(String pici) {
		this.pici = pici;
	}
	public String getOrisigndate() {
		return orisigndate;
	}
	public void setOrisigndate(String orisigndate) {
		this.orisigndate = orisigndate;
	}
	public int getTdflag() {
		return tdflag;
	}
	public void setTdflag(int tdflag) {
		this.tdflag = tdflag;
	}
	public Set getTnlwNonlawlogs() {
		return tnlwNonlawlogs;
	}
	public void setTnlwNonlawlogs(Set tnlwNonlawlogs) {
		this.tnlwNonlawlogs = tnlwNonlawlogs;
	}
	public String getOverstat() {
		return overstat;
	}
	public void setOverstat(String overstat) {
		this.overstat = overstat;
	}
	public long getNonlawid() {
		return nonlawid;
	}
	public String getUsername() {
		return username;
	}
	public String getDuebill() {
		return duebill;
	}
	public String getLendaccount() {
		return lendaccount;
	}
	public String getPayaccount() {
		return payaccount;
	}
	public String getSynergicname() {
		return synergicname;
	}
	public String getIdcard() {
		return idcard;
	}
	public String getLendfee() {
		return lendfee;
	}
	public String getBalancefee() {
		return balancefee;
	}
	public String getOverfee() {
		return overfee;
	}
	public String getAccrualfee() {
		return accrualfee;
	}
	public String getCastfee() {
		return castfee;
	}
	public int getOvernum() {
		return overnum;
	}
	public String getMonthfee() {
		return monthfee;
	}
	public String getBreachfee() {
		return breachfee;
	}
	public String getLenddate() {
		return lenddate;
	}
	public String getLendoverdate() {
		return lendoverdate;
	}
	public String getProjectname() {
		return projectname;
	}
	public String getBuyaddr() {
		return buyaddr;
	}
	public String getHomeaddr() {
		return homeaddr;
	}
	public String getHomephoneold() {
		return homephoneold;
	}
	public String getMobileold() {
		return mobileold;
	}
	
	public String getCompany() {
		return company;
	}
	public String getCompanyaddr() {
		return companyaddr;
	}
	public String getCompanyphone() {
		return companyphone;
	}
	public String getAlterphone() {
		return alterphone;
	}
	public int getState() {
		return state;
	}
	public long getBankid() {
		return bankid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public String getConsigndate() {
		return consigndate;
	}
	public void setNonlawid(long nonlawid) {
		this.nonlawid = nonlawid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setDuebill(String duebill) {
		this.duebill = duebill;
	}
	public void setLendaccount(String lendaccount) {
		this.lendaccount = lendaccount;
	}
	public void setPayaccount(String payaccount) {
		this.payaccount = payaccount;
	}
	public void setSynergicname(String synergicname) {
		this.synergicname = synergicname;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public void setLendfee(String lendfee) {
		this.lendfee = lendfee;
	}
	public void setBalancefee(String balancefee) {
		this.balancefee = balancefee;
	}
	public void setOverfee(String overfee) {
		this.overfee = overfee;
	}
	public void setAccrualfee(String accrualfee) {
		this.accrualfee = accrualfee;
	}
	public void setCastfee(String castfee) {
		this.castfee = castfee;
	}
	public void setOvernum(int overnum) {
		this.overnum = overnum;
	}
	public void setMonthfee(String monthfee) {
		this.monthfee = monthfee;
	}
	public void setBreachfee(String breachfee) {
		this.breachfee = breachfee;
	}
	public void setLenddate(String lenddate) {
		this.lenddate = lenddate;
	}
	public void setLendoverdate(String lendoverdate) {
		this.lendoverdate = lendoverdate;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public void setBuyaddr(String buyaddr) {
		this.buyaddr = buyaddr;
	}
	public void setHomeaddr(String homeaddr) {
		this.homeaddr = homeaddr;
	}
	public void setHomephoneold(String homephoneold) {
		this.homephoneold = homephoneold;
	}
	public void setMobileold(String mobileold) {
		this.mobileold = mobileold;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	public void setCompanyaddr(String companyaddr) {
		this.companyaddr = companyaddr;
	}
	public void setCompanyphone(String companyphone) {
		this.companyphone = companyphone;
	}
	public void setAlterphone(String alterphone) {
		this.alterphone = alterphone;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setBankid(long bankid) {
		this.bankid = bankid;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public void setConsigndate(String consigndate) {
		this.consigndate = consigndate;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getLendadmin() {
		return lendadmin;
	}

	public void setLendadmin(String lendadmin) {
		this.lendadmin = lendadmin;
	}
	public Set getTnlwRepaylogs() {
		return tnlwRepaylogs;
	}
	public void setTnlwRepaylogs(Set tnlwRepaylogs) {
		this.tnlwRepaylogs = tnlwRepaylogs;
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
	public java.util.Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getOverallfee() {
		return overallfee;
	}
	public void setOverallfee(String overallfee) {
		this.overallfee = overallfee;
	}
	public float getCuroverstat() {
		return curoverstat;
	}
	public String getCurbalancefee() {
		return curbalancefee;
	}
	public String getCuroverfee() {
		return curoverfee;
	}
	public String getCuraccrualfee() {
		return curaccrualfee;
	}
	public void setCuroverstat(float curoverstat) {
		this.curoverstat = curoverstat;
	}
	public void setCurbalancefee(String curbalancefee) {
		this.curbalancefee = curbalancefee;
	}
	public void setCuroverfee(String curoverfee) {
		this.curoverfee = curoverfee;
	}
	public void setCuraccrualfee(String curaccrualfee) {
		this.curaccrualfee = curaccrualfee;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

}