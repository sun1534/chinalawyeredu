package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * TlawJiekuanren entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawJiekuanren implements java.io.Serializable {

	// Fields

	private Long caseid;
	
    private TlawLawcase lawcase;

	
	private String jiekuanren;
	private String jiekuantype;
	private String theidcard;
	private String thephone1;
	private String thephone2;
	private Double howmuch;
	private Double remain;
	private String bankno;
	private String startdate;
	private String enddate;
	private String danbaoren;
	private String danbaotype;
	private Long createuserid;
	private String createusername;
	private String remarks;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public TlawJiekuanren() {
	}


	// Property accessors

	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	public String getJiekuanren() {
		return this.jiekuanren;
	}

	public void setJiekuanren(String jiekuanren) {
		this.jiekuanren = jiekuanren;
	}

	public String getJiekuantype() {
		return this.jiekuantype;
	}

	public void setJiekuantype(String jiekuantype) {
		this.jiekuantype = jiekuantype;
	}

	public String getTheidcard() {
		return this.theidcard;
	}

	public void setTheidcard(String theidcard) {
		this.theidcard = theidcard;
	}

	public String getThephone1() {
		return this.thephone1;
	}

	public void setThephone1(String thephone1) {
		this.thephone1 = thephone1;
	}

	public String getThephone2() {
		return this.thephone2;
	}

	public void setThephone2(String thephone2) {
		this.thephone2 = thephone2;
	}

	public Double getHowmuch() {
		return this.howmuch;
	}

	public void setHowmuch(Double howmuch) {
		this.howmuch = howmuch;
	}

	public Double getRemain() {
		return this.remain;
	}

	public void setRemain(Double remain) {
		this.remain = remain;
	}

	public String getBankno() {
		return this.bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getDanbaoren() {
		return this.danbaoren;
	}

	public void setDanbaoren(String danbaoren) {
		this.danbaoren = danbaoren;
	}

	public String getDanbaotype() {
		return this.danbaotype;
	}

	public void setDanbaotype(String danbaotype) {
		this.danbaotype = danbaotype;
	}

	public Long getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Long createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateusername() {
		return this.createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}


	/**
	 * @return the lawcase
	 */
	public TlawLawcase getLawcase() {
		return lawcase;
	}


	/**
	 * @param lawcase the lawcase to set
	 */
	public void setLawcase(TlawLawcase lawcase) {
		this.lawcase = lawcase;
	}

}