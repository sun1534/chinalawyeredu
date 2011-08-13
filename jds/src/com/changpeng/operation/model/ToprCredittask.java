package com.changpeng.operation.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ToprCredittask entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ToprCredittask implements java.io.Serializable {

	// Fields

	private long credittaskid;
	private ToprCreditcard toprCreditcard;
	private long userid;
	private Date createtime;
	private int taskstat;
	private Date updatetime;
	private Set toprCreditlogs = new HashSet(0);
	
	private String paydate;
	private String canlink;
	// Constructors

	public String getCanlink() {
		return canlink;
	}

	public void setCanlink(String canlink) {
		this.canlink = canlink;
	}

	public String getPaydate() {
		return paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	/** default constructor */
	public ToprCredittask() {
	}

	/** minimal constructor */
	public ToprCredittask(ToprCreditcard toprCreditcard, long userid,
			Date createtime, int taskstat) {
		this.toprCreditcard = toprCreditcard;
		this.userid = userid;
		this.createtime = createtime;
		this.taskstat = taskstat;
	}

	/** full constructor */
	public ToprCredittask(ToprCreditcard toprCreditcard, long userid,
			Date createtime, int taskstat, Date updatetime, Set toprCreditlogs) {
		this.toprCreditcard = toprCreditcard;
		this.userid = userid;
		this.createtime = createtime;
		this.taskstat = taskstat;
		this.updatetime = updatetime;
		this.toprCreditlogs = toprCreditlogs;
	}

	// Property accessors

	public long getCredittaskid() {
		return this.credittaskid;
	}

	public void setCredittaskid(long credittaskid) {
		this.credittaskid = credittaskid;
	}

	public ToprCreditcard getToprCreditcard() {
		return this.toprCreditcard;
	}

	public void setToprCreditcard(ToprCreditcard toprCreditcard) {
		this.toprCreditcard = toprCreditcard;
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

	public int getTaskstat() {
		return this.taskstat;
	}

	public void setTaskstat(int taskstat) {
		this.taskstat = taskstat;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Set getToprCreditlogs() {
		return this.toprCreditlogs;
	}

	public void setToprCreditlogs(Set toprCreditlogs) {
		this.toprCreditlogs = toprCreditlogs;
	}

}