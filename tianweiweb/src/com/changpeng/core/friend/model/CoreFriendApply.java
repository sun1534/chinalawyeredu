package com.changpeng.core.friend.model;

import java.sql.Timestamp;

/**
 * CoreFriendApply entity. @author MyEclipse Persistence Tools
 */

public class CoreFriendApply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer activeUserid;
	private Integer passiveUserid;
	private String applyMessage;
	private Timestamp createTime;
	private Short status;

	// Constructors

	/** default constructor */
	public CoreFriendApply() {
	}

	/** minimal constructor */
	public CoreFriendApply(Integer activeUserid, Integer passiveUserid,
			Timestamp createTime, Short status) {
		this.activeUserid = activeUserid;
		this.passiveUserid = passiveUserid;
		this.createTime = createTime;
		this.status = status;
	}

	/** full constructor */
	public CoreFriendApply(Integer activeUserid, Integer passiveUserid,
			String applyMessage, Timestamp createTime, Short status) {
		this.activeUserid = activeUserid;
		this.passiveUserid = passiveUserid;
		this.applyMessage = applyMessage;
		this.createTime = createTime;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActiveUserid() {
		return this.activeUserid;
	}

	public void setActiveUserid(Integer activeUserid) {
		this.activeUserid = activeUserid;
	}

	public Integer getPassiveUserid() {
		return this.passiveUserid;
	}

	public void setPassiveUserid(Integer passiveUserid) {
		this.passiveUserid = passiveUserid;
	}

	public String getApplyMessage() {
		return this.applyMessage;
	}

	public void setApplyMessage(String applyMessage) {
		this.applyMessage = applyMessage;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}