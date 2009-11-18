package com.sxit.models.workflow;

import java.util.Date;

import com.sxit.models.system.SysUser;

/**
 * TwflPositionUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TwflPositionUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private SysUser sysUser;
	private TwflPosition twflPosition;
	private Integer groupid=0;
	private Integer createuserid;
	private Date createtime;

	// Constructors

	public Integer getCreateuserid() {
		return createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/** default constructor */
	public TwflPositionUser() {
	}

	/** minimal constructor */
	public TwflPositionUser(SysUser sysUser, TwflPosition twflPosition) {
		this.sysUser = sysUser;
		this.twflPosition = twflPosition;
	}

	/** full constructor */
	public TwflPositionUser(SysUser sysUser, TwflPosition twflPosition, Integer groupid) {
		this.sysUser = sysUser;
		this.twflPosition = twflPosition;
		this.groupid = groupid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public TwflPosition getTwflPosition() {
		return this.twflPosition;
	}

	public void setTwflPosition(TwflPosition twflPosition) {
		this.twflPosition = twflPosition;
	}

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

}