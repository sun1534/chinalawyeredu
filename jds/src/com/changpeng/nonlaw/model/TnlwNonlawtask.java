package com.changpeng.nonlaw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TnlwNonlawtask entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TnlwNonlawtask implements java.io.Serializable {

	// Fields

	private long nonlawtaskid;
	private TnlwNonlaw tnlwNonlaw;
	private long userid;
	private Date createtime;
	private int taskstat;
	private Date updatetime;
	private Set tnlwNonlawlogs = new HashSet(0);
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
	public TnlwNonlawtask() {
	}

	/** minimal constructor */
	public TnlwNonlawtask(TnlwNonlaw tnlwNonlaw, long userid, Date createtime,
			int taskstat) {
		this.tnlwNonlaw = tnlwNonlaw;
		this.userid = userid;
		this.createtime = createtime;
		this.taskstat = taskstat;
	}

	/** full constructor */
	public TnlwNonlawtask(TnlwNonlaw tnlwNonlaw, long userid, Date createtime,
			int taskstat, Date updatetime, Set tnlwNonlawlogs) {
		this.tnlwNonlaw = tnlwNonlaw;
		this.userid = userid;
		this.createtime = createtime;
		this.taskstat = taskstat;
		this.updatetime = updatetime;
		this.tnlwNonlawlogs = tnlwNonlawlogs;
	}

	// Property accessors

	public long getNonlawtaskid() {
		return this.nonlawtaskid;
	}

	public void setNonlawtaskid(long nonlawtaskid) {
		this.nonlawtaskid = nonlawtaskid;
	}

	public TnlwNonlaw getTnlwNonlaw() {
		return this.tnlwNonlaw;
	}

	public void setTnlwNonlaw(TnlwNonlaw tnlwNonlaw) {
		this.tnlwNonlaw = tnlwNonlaw;
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

	public Set getTnlwNonlawlogs() {
		return this.tnlwNonlawlogs;
	}

	public void setTnlwNonlawlogs(Set tnlwNonlawlogs) {
		this.tnlwNonlawlogs = tnlwNonlawlogs;
	}

}