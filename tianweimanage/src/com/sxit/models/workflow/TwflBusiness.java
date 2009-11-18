package com.sxit.models.workflow;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TwflBusiness entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwflBusiness implements java.io.Serializable {

	// Fields

	private Integer businessid;
	private String businessname;
	private Integer usertype;
	private Integer statusid;
	private Integer ordernum;
	private String remarks;
	private Set twflProcesses = new HashSet(0);
private int createuserid;
private Timestamp createtime;
	// Constructors

	/** default constructor */
	public TwflBusiness() {
	}

	// Property accessors

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getBusinessid() {
		return this.businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	public String getBusinessname() {
		return this.businessname;
	}

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}

	
	public Integer getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public Integer getOrdernum() {
		return this.ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Set getTwflProcesses() {
		return this.twflProcesses;
	}

	public void setTwflProcesses(Set twflProcesses) {
		this.twflProcesses = twflProcesses;
	}

	public int getCreateuserid() {
		return createuserid;
	}

	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}