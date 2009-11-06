package com.changpeng.core.user.model;

import java.sql.Timestamp;

/**
 * CoreUserVisit entity. @author MyEclipse Persistence Tools
 */

public class CoreUserVisit implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer visitUserid;
	private Timestamp visitTime;

	// Constructors

	/** default constructor */
	public CoreUserVisit() {
	}

	/** full constructor */
	public CoreUserVisit(Integer userid, Integer visitUserid,
			Timestamp visitTime) {
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

	public Timestamp getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Timestamp visitTime) {
		this.visitTime = visitTime;
	}

}