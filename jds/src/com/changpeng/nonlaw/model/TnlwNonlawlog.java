package com.changpeng.nonlaw.model;

import java.util.Date;

/**
 * TnlwNonlawlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TnlwNonlawlog implements java.io.Serializable {

	// Fields

	private long logid;
	private TnlwNonlawtask tnlwNonlawtask;
	private long userid;
	private Date createtime;
	private String logtime;
	private String comments;
	private int logtype;
	private long field1;
	
	private TnlwNonlaw tnlwNonlaw;
	
	// Constructors

	public TnlwNonlaw getTnlwNonlaw() {
		return tnlwNonlaw;
	}

	public void setTnlwNonlaw(TnlwNonlaw tnlwNonlaw) {
		this.tnlwNonlaw = tnlwNonlaw;
	}

	/** default constructor */
	public TnlwNonlawlog() {
	}

	/** minimal constructor */
	
	// Property accessors

	public long getLogid() {
		return this.logid;
	}

	public void setLogid(long logid) {
		this.logid = logid;
	}

	public TnlwNonlawtask getTnlwNonlawtask() {
		return this.tnlwNonlawtask;
	}

	public void setTnlwNonlawtask(TnlwNonlawtask tnlwNonlawtask) {
		this.tnlwNonlawtask = tnlwNonlawtask;
	}

	public long getUserid() {
		return this.userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getLogtime() {
		return this.logtime;
	}

	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getLogtype() {
		return logtype;
	}

	public long getField1() {
		return field1;
	}

	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}

	public void setField1(long field1) {
		this.field1 = field1;
	}

}