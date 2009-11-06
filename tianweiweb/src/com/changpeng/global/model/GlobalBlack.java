package com.changpeng.global.model;

import java.sql.Timestamp;

/**
 * GlobalBlack entity. @author MyEclipse Persistence Tools
 */

public class GlobalBlack implements java.io.Serializable {

	// Fields

	private Integer blackUserid;
	private Short blackUserRole;
	private Integer createUserid;
	private Short userRole;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public GlobalBlack() {
	}

	/** minimal constructor */
	public GlobalBlack(Short blackUserRole, Timestamp createTime) {
		this.blackUserRole = blackUserRole;
		this.createTime = createTime;
	}

	/** full constructor */
	public GlobalBlack(Short blackUserRole, Integer createUserid,
			Short userRole, Timestamp createTime) {
		this.blackUserRole = blackUserRole;
		this.createUserid = createUserid;
		this.userRole = userRole;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getBlackUserid() {
		return this.blackUserid;
	}

	public void setBlackUserid(Integer blackUserid) {
		this.blackUserid = blackUserid;
	}

	public Short getBlackUserRole() {
		return this.blackUserRole;
	}

	public void setBlackUserRole(Short blackUserRole) {
		this.blackUserRole = blackUserRole;
	}

	public Integer getCreateUserid() {
		return this.createUserid;
	}

	public void setCreateUserid(Integer createUserid) {
		this.createUserid = createUserid;
	}

	public Short getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Short userRole) {
		this.userRole = userRole;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}