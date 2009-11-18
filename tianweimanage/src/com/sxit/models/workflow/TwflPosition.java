package com.sxit.models.workflow;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TwflPosition entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwflPosition implements java.io.Serializable {

	// Fields

	private Integer positionid;
	private String positionname;
	private Integer groupid=0;
	private Date createtime;
	private Integer createuserid;
	private String remarks;
	private Set twflNodes = new HashSet(0);
	private Set twflPositionUsers = new HashSet(0);

	// Constructors

	/** default constructor */
	public TwflPosition() {
	}

	/** minimal constructor */
	public TwflPosition(String positionname, Integer groupid, Date createtime, Integer createuserid) {
		this.positionname = positionname;
		this.groupid = groupid;
		this.createtime = createtime;
		this.createuserid = createuserid;
	}

	/** full constructor */
	public TwflPosition(String positionname, Integer groupid, Date createtime, Integer createuserid, String remarks, Set twflNodes,
			Set twflPositionUsers) {
		this.positionname = positionname;
		this.groupid = groupid;
		this.createtime = createtime;
		this.createuserid = createuserid;
		this.remarks = remarks;
		this.twflNodes = twflNodes;
		this.twflPositionUsers = twflPositionUsers;
	}

	// Property accessors

	public Integer getPositionid() {
		return this.positionid;
	}

	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}

	public String getPositionname() {
		return this.positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set getTwflNodes() {
		return this.twflNodes;
	}

	public void setTwflNodes(Set twflNodes) {
		this.twflNodes = twflNodes;
	}

	public Set getTwflPositionUsers() {
		return this.twflPositionUsers;
	}

	public void setTwflPositionUsers(Set twflPositionUsers) {
		this.twflPositionUsers = twflPositionUsers;
	}

}