package com.changpeng.core.friend.model;

import java.sql.Timestamp;

/**
 * CoreFriend entity. @author MyEclipse Persistence Tools
 */

public class CoreFriend implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer friendUserid;
	private Short friendUserRole;
	private Integer categoryId;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public CoreFriend() {
	}

	/** full constructor */
	public CoreFriend(Integer userid, Integer friendUserid,
			Short friendUserRole, Integer categoryId, Timestamp createTime) {
		this.userid = userid;
		this.friendUserid = friendUserid;
		this.friendUserRole = friendUserRole;
		this.categoryId = categoryId;
		this.createTime = createTime;
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

	public Integer getFriendUserid() {
		return this.friendUserid;
	}

	public void setFriendUserid(Integer friendUserid) {
		this.friendUserid = friendUserid;
	}

	public Short getFriendUserRole() {
		return this.friendUserRole;
	}

	public void setFriendUserRole(Short friendUserRole) {
		this.friendUserRole = friendUserRole;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}