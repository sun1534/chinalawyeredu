package com.sxit.models.service;

import java.util.Date;

/**
 * CoreAdvPos entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CoreAdvPos implements java.io.Serializable {

	// Fields

	private Integer pos;
	private String description;
	private Integer createid;
	private Date createtime;

	// Constructors

	/** default constructor */
	public CoreAdvPos() {
	}

	/** minimal constructor */
	public CoreAdvPos(Integer createid, Date createtime) {
		this.createid = createid;
		this.createtime = createtime;
	}

	/** full constructor */
	public CoreAdvPos(String description, Integer createid, Date createtime) {
		this.description = description;
		this.createid = createid;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getPos() {
		return this.pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreateid() {
		return this.createid;
	}

	public void setCreateid(Integer createid) {
		this.createid = createid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}