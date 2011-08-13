package com.sxit.info.model;

/**
 * TinfSet entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TinfSet implements java.io.Serializable {

	// Fields

	private Long setid;
	private TinfType tinfType;
	private Long userid;
	private String username;
	
	/*
	 * 权限id ，设置为1为审批权限，设置为2为发布权限，设置为4为新建权限，
	 * 设置为8为编辑权限，设置为16为删除权限，设置为32为查看权限
	 */
	private Long powerid;

	// Constructors

	/** default constructor */
	public TinfSet() {
	}

	/** minimal constructor */
	public TinfSet(Long setid, TinfType tinfType) {
		this.setid = setid;
		this.tinfType = tinfType;
	}

	/** full constructor */
	public TinfSet(Long setid, TinfType tinfType, Long userid, String username,
			Long powerid) {
		this.setid = setid;
		this.tinfType = tinfType;
		this.userid = userid;
		this.username = username;
		this.powerid = powerid;
	}

	// Property accessors

	public Long getSetid() {
		return this.setid;
	}

	public void setSetid(Long setid) {
		this.setid = setid;
	}

	public TinfType getTinfType() {
		return this.tinfType;
	}

	public void setTinfType(TinfType tinfType) {
		this.tinfType = tinfType;
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

	public Long getPowerid() {
		return this.powerid;
	}

	public void setPowerid(Long powerid) {
		this.powerid = powerid;
	}

}