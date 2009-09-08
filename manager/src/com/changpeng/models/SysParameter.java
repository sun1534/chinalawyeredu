package com.changpeng.models;

import java.sql.Timestamp;

/**
 * SysParameter entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysParameter implements java.io.Serializable {

	// Fields

	private String paramname;
	private String paramvalue;
	private int groupid;
	private String comments;
	private Timestamp createtime;
	private int createuserid;

	// Constructors

	/** default constructor */
	public SysParameter() {
	}

	/** minimal constructor */
	public SysParameter(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	/** full constructor */
	public SysParameter(String paramvalue, int groupid, String comments,
			Timestamp createtime, int createuserid) {
		this.paramvalue = paramvalue;
		this.groupid = groupid;
		this.comments = comments;
		this.createtime = createtime;
		this.createuserid = createuserid;
	}

	// Property accessors

	public String getParamname() {
		return this.paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getParamvalue() {
		return this.paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

}