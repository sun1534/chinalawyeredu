package com.sxit.models.workflow;

import java.util.Date;

/**
 * TwflBusinessUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwflBusinessUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer businessid;
	private Integer userid;
	private Integer createuserid;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TwflBusinessUser() {
	}

	/** full constructor */
	public TwflBusinessUser(Integer businessid, Integer userid, Integer createuserid, Date createtime) {
		this.businessid = businessid;
		this.userid = userid;
		this.createuserid = createuserid;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBusinessid() {
		return this.businessid;
	}

	public void setBusinessid(Integer businessid) {
		this.businessid = businessid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}