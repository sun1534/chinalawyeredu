package com.changpeng.operation.model;

import java.util.Date;

/**
 * ToprCreditlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ToprCreditlog implements java.io.Serializable {

	// Fields

	private long logid;
	private ToprCredittask toprCredittask;
	private long userid;
	private Date createtime;
	private String logtime;
	private String comments;
	
	private ToprCreditcard toprCreditcard;
	// Constructors

	public ToprCreditcard getToprCreditcard() {
		return toprCreditcard;
	}

	public void setToprCreditcard(ToprCreditcard toprCreditcard) {
		this.toprCreditcard = toprCreditcard;
	}

	/** default constructor */
	public ToprCreditlog() {
	}

	/** minimal constructor */
	public ToprCreditlog(ToprCredittask toprCredittask, long userid,
			Date createtime, String logtime) {
		this.toprCredittask = toprCredittask;
		this.userid = userid;
		this.createtime = createtime;
		this.logtime = logtime;
	}

	/** full constructor */
	public ToprCreditlog(ToprCredittask toprCredittask, long userid,
			Date createtime, String logtime, String comments) {
		this.toprCredittask = toprCredittask;
		this.userid = userid;
		this.createtime = createtime;
		this.logtime = logtime;
		this.comments = comments;
	}

	// Property accessors

	public long getLogid() {
		return this.logid;
	}

	public void setLogid(long logid) {
		this.logid = logid;
	}

	public ToprCredittask getToprCredittask() {
		return this.toprCredittask;
	}

	public void setToprCredittask(ToprCredittask toprCredittask) {
		this.toprCredittask = toprCredittask;
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

}