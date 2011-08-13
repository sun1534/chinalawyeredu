package com.changpeng.lawcase.model;

import java.sql.Clob;
import java.sql.Timestamp;

/**
 * TlawSusong entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawSusong implements java.io.Serializable {

	// Fields

	private Long caseid;
	private Clob thecontent;
	private Timestamp createtime;
	private Long createuserid;
	private String createusername;
	private Long confirmid;
	private String confirmname;
	private String cfmcontent;
	private Timestamp confirmtime;
	/**
	 * 0草稿1审批通过2审批不通过（对审批通过和审批不通过的需要进行审批）
	 */
	private int statusid;
	private String files;

	// Constructors

	/** default constructor */
	public TlawSusong() {
	}



	// Property accessors

	public Long getCaseid() {
		return this.caseid;
	}

	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}



	/**
	 * @return the thecontent
	 */
	public Clob getThecontent() {
		return thecontent;
	}

	/**
	 * @param thecontent the thecontent to set
	 */
	public void setThecontent(Clob thecontent) {
		this.thecontent = thecontent;
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

	public Long getConfirmid() {
		return this.confirmid;
	}

	public void setConfirmid(Long confirmid) {
		this.confirmid = confirmid;
	}

	public String getConfirmname() {
		return this.confirmname;
	}

	public void setConfirmname(String confirmname) {
		this.confirmname = confirmname;
	}

	public String getCfmcontent() {
		return this.cfmcontent;
	}

	public void setCfmcontent(String cfmcontent) {
		this.cfmcontent = cfmcontent;
	}

	public int getStatusid() {
		return this.statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}



	/**
	 * @return the confirmtime
	 */
	public Timestamp getConfirmtime() {
		return confirmtime;
	}



	/**
	 * @param confirmtime the confirmtime to set
	 */
	public void setConfirmtime(Timestamp confirmtime) {
		this.confirmtime = confirmtime;
	}

}