package com.changpeng.report.models;

/**
 * TrptTduser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TrptTduser implements java.io.Serializable {

	// Fields

	private Long tduserid;
	private Long bankid;
	private Long consigntype;
	private Long consignflag;
	private String username;
	private String creditcard;
	private String idcard;
	private String consigndate;
	private Double cnfee;
	private Double usafee;
	private Long repaystatus;
	private Double PCnfee;
	private Double PUsafee;
	private Double BCnfee;
	private Double BUsafee;
	private Double rate;
	private Double sumfee;
	private Double RCnfee;
	private Double RUsafee;
	private String comments;

	// Constructors

	/** default constructor */
	public TrptTduser() {
	}

	/** minimal constructor */
	public TrptTduser(Long tduserid) {
		this.tduserid = tduserid;
	}

	/** full constructor */
	public TrptTduser(Long tduserid, Long bankid, Long consigntype,
			Long consignflag, String username, String creditcard,
			String idcard, String consigndate, Double cnfee, Double usafee,
			Long repaystatus, Double PCnfee, Double PUsafee, Double BCnfee,
			Double BUsafee, Double rate, Double sumfee, Double RCnfee,
			Double RUsafee, String comments) {
		this.tduserid = tduserid;
		this.bankid = bankid;
		this.consigntype = consigntype;
		this.consignflag = consignflag;
		this.username = username;
		this.creditcard = creditcard;
		this.idcard = idcard;
		this.consigndate = consigndate;
		this.cnfee = cnfee;
		this.usafee = usafee;
		this.repaystatus = repaystatus;
		this.PCnfee = PCnfee;
		this.PUsafee = PUsafee;
		this.BCnfee = BCnfee;
		this.BUsafee = BUsafee;
		this.rate = rate;
		this.sumfee = sumfee;
		this.RCnfee = RCnfee;
		this.RUsafee = RUsafee;
		this.comments = comments;
	}

	// Property accessors

	public Long getTduserid() {
		return this.tduserid;
	}

	public void setTduserid(Long tduserid) {
		this.tduserid = tduserid;
	}

	public Long getBankid() {
		return this.bankid;
	}

	public void setBankid(Long bankid) {
		this.bankid = bankid;
	}

	public Long getConsigntype() {
		return this.consigntype;
	}

	public void setConsigntype(Long consigntype) {
		this.consigntype = consigntype;
	}

	public Long getConsignflag() {
		return this.consignflag;
	}

	public void setConsignflag(Long consignflag) {
		this.consignflag = consignflag;
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

	public Double getCnfee() {
		return this.cnfee;
	}

	public void setCnfee(Double cnfee) {
		this.cnfee = cnfee;
	}

	public Double getUsafee() {
		return this.usafee;
	}

	public void setUsafee(Double usafee) {
		this.usafee = usafee;
	}

	public Long getRepaystatus() {
		return this.repaystatus;
	}

	public void setRepaystatus(Long repaystatus) {
		this.repaystatus = repaystatus;
	}

	public Double getPCnfee() {
		return this.PCnfee;
	}

	public void setPCnfee(Double PCnfee) {
		this.PCnfee = PCnfee;
	}

	public Double getPUsafee() {
		return this.PUsafee;
	}

	public void setPUsafee(Double PUsafee) {
		this.PUsafee = PUsafee;
	}

	public Double getBCnfee() {
		return this.BCnfee;
	}

	public void setBCnfee(Double BCnfee) {
		this.BCnfee = BCnfee;
	}

	public Double getBUsafee() {
		return this.BUsafee;
	}

	public void setBUsafee(Double BUsafee) {
		this.BUsafee = BUsafee;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getSumfee() {
		return this.sumfee;
	}

	public void setSumfee(Double sumfee) {
		this.sumfee = sumfee;
	}

	public Double getRCnfee() {
		return this.RCnfee;
	}

	public void setRCnfee(Double RCnfee) {
		this.RCnfee = RCnfee;
	}

	public Double getRUsafee() {
		return this.RUsafee;
	}

	public void setRUsafee(Double RUsafee) {
		this.RUsafee = RUsafee;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}