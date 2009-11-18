package com.sxit.models.users;

import java.util.Date;

/**
 * CoreUserVisit entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CoreUserVisit implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer visitUserid;
	private Date visitTime;

	// Constructors

	/** default constructor */
	public CoreUserVisit() {
	}

	/** full constructor */
	public CoreUserVisit(Integer userid, Integer visitUserid, Date visitTime) {
		this.userid = userid;
		this.visitUserid = visitUserid;
		this.visitTime = visitTime;
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

	public Integer getVisitUserid() {
		return this.visitUserid;
	}

	public void setVisitUserid(Integer visitUserid) {
		this.visitUserid = visitUserid;
	}

	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

}