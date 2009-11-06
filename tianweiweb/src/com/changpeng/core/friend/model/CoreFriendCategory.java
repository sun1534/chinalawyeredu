package com.changpeng.core.friend.model;

import java.sql.Timestamp;

/**
 * CoreFriendCategory entity. @author MyEclipse Persistence Tools
 */

public class CoreFriendCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String name;
	private Timestamp timeCreate;
	private Short friendCount;
	private Short sort;

	// Constructors

	/** default constructor */
	public CoreFriendCategory() {
	}

	/** minimal constructor */
	public CoreFriendCategory(Integer userid, String name,
			Timestamp timeCreate, Short friendCount) {
		this.userid = userid;
		this.name = name;
		this.timeCreate = timeCreate;
		this.friendCount = friendCount;
	}

	/** full constructor */
	public CoreFriendCategory(Integer userid, String name,
			Timestamp timeCreate, Short friendCount, Short sort) {
		this.userid = userid;
		this.name = name;
		this.timeCreate = timeCreate;
		this.friendCount = friendCount;
		this.sort = sort;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getTimeCreate() {
		return this.timeCreate;
	}

	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Short getFriendCount() {
		return this.friendCount;
	}

	public void setFriendCount(Short friendCount) {
		this.friendCount = friendCount;
	}

	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

}