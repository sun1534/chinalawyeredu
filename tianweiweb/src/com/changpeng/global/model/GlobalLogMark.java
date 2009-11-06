package com.changpeng.global.model;

import java.sql.Timestamp;

/**
 * GlobalLogMark entity. @author MyEclipse Persistence Tools
 */

public class GlobalLogMark implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Short userRole;
	private Short markValue;
	private Short action;
	private Timestamp createTime;
	private String description;

	// Constructors

	/** default constructor */
	public GlobalLogMark() {
	}

	/** minimal constructor */
	public GlobalLogMark(Integer userid, Short userRole, Short markValue,
			Short action, Timestamp createTime) {
		this.userid = userid;
		this.userRole = userRole;
		this.markValue = markValue;
		this.action = action;
		this.createTime = createTime;
	}

	/** full constructor */
	public GlobalLogMark(Integer userid, Short userRole, Short markValue,
			Short action, Timestamp createTime, String description) {
		this.userid = userid;
		this.userRole = userRole;
		this.markValue = markValue;
		this.action = action;
		this.createTime = createTime;
		this.description = description;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Short getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Short userRole) {
		this.userRole = userRole;
	}

	public Short getMarkValue() {
		return this.markValue;
	}

	public void setMarkValue(Short markValue) {
		this.markValue = markValue;
	}

	public Short getAction() {
		return this.action;
	}

	public void setAction(Short action) {
		this.action = action;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}