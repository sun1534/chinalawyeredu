package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * TlawOperlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawOperlog implements java.io.Serializable {

	// Fields

	private Long logid;
	private Long userid;
	private String username;
	private String logcontent;
	private Long caseid;
//	private int nodeid;
	private int actionid;
	private Timestamp logtime;
	/**
	 * 
	 */
	private int opertype;

	// Constructors

	/**
	 * @return the opertype
	 */
	public int getOpertype() {
		return opertype;
	}

	/**
	 * @param opertype the opertype to set
	 */
	public void setOpertype(int opertype) {
		this.opertype = opertype;
	}

	/** default constructor */
	public TlawOperlog() {
	}

	/** full constructor */
	public TlawOperlog(Long userid, String username, String logcontent,
			Long caseid, Timestamp logtime) {
		this.userid = userid;
		this.username = username;
		this.logcontent = logcontent;
		this.caseid = caseid;
		this.logtime = logtime;
	}

	// Property accessors

	public Long getLogid() {
		return this.logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLogcontent() {
		return this.logcontent;
	}

	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}

	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}

	public Timestamp getLogtime() {
		return this.logtime;
	}

	public void setLogtime(Timestamp logtime) {
		this.logtime = logtime;
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

}