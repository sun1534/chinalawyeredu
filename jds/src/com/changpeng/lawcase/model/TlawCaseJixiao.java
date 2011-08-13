package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * TlawJixiao entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawCaseJixiao implements java.io.Serializable {

	// Fields

	private Long jixiaologid;
	private Long caseid;
	private Long userid;
	private int jixiaoid;
	private Double thenumber;
	private String logtime;
	private Timestamp createtime;
	private Long createuserid;
	private String createusername;
	private String whylog;
	private String remarks;
	private int nodeid;
	private int actionid;

	// Constructors

	/**
	 * @return the nodeid
	 */
	public int getNodeid() {
		return nodeid;
	}

	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	/**
	 * @return the actionid
	 */
	public int getActionid() {
		return actionid;
	}

	/**
	 * @param actionid the actionid to set
	 */
	public void setActionid(int actionid) {
		this.actionid = actionid;
	}

	/** default constructor */
	public TlawCaseJixiao() {
	}

	/** minimal constructor */
	public TlawCaseJixiao(Long caseid, Long userid) {
		this.caseid = caseid;
		this.userid = userid;
	}



	// Property accessors

	public Long getJixiaologid() {
		return this.jixiaologid;
	}

	public void setJixiaologid(Long jixiaologid) {
		this.jixiaologid = jixiaologid;
	}

	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public int getJixiaoid() {
		return this.jixiaoid;
	}

	public void setJixiaoid(int jixiaoid) {
		this.jixiaoid = jixiaoid;
	}

	public Double getThenumber() {
		return this.thenumber;
	}

	public void setThenumber(Double thenumber) {
		this.thenumber = thenumber;
	}

	public String getLogtime() {
		return this.logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
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

	public String getWhylog() {
		return this.whylog;
	}

	public void setWhylog(String whylog) {
		this.whylog = whylog;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}