package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * 案件日志
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawCaselog implements java.io.Serializable {

	// Fields

	private Long caselogid;
	private Long caseid;
	private String logtime;
	private String logcontent;
	private Timestamp createtime;
	private Long createuserid;
	private String createusername;
	/**
	 * 0是案件承办人写的日志,1是调解人写的调解日志
	 */
	private int logtype;

	// Constructors

	/** default constructor */
	public TlawCaselog() {
	}

	/** minimal constructor */
	public TlawCaselog(Timestamp createtime) {
		this.createtime = createtime;
	}

	/** full constructor */
	public TlawCaselog(Long caseid, String logtime, String logcontent,
			Timestamp createtime, Long createuserid, String createusername) {
		this.caseid = caseid;
		this.logtime = logtime;
		this.logcontent = logcontent;
		this.createtime = createtime;
		this.createuserid = createuserid;
		this.createusername = createusername;
	}

	// Property accessors

	public Long getCaselogid() {
		return this.caselogid;
	}

	public void setCaselogid(Long caselogid) {
		this.caselogid = caselogid;
	}

	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	public String getLogtime() {
		return this.logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	public String getLogcontent() {
		return this.logcontent;
	}

	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
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

	/**
	 * @return the logtype
	 */
	public int getLogtype() {
		return logtype;
	}

	/**
	 * @param logtype the logtype to set
	 */
	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}

}