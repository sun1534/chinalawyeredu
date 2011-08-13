package com.changpeng.service.model;

import java.util.Date;

/**
 * TserPhobill entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TserPhobill implements java.io.Serializable {

	// Fields

	private long phobillid;
	private String username;
	private String phone;
	private String fee;
	private String feetime;
	private String comments;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TserPhobill() {
	}

	/** full constructor */
	public TserPhobill(String username, String phone, String fee,
			String feetime, String comments, Date createtime) {
		this.username = username;
		this.phone = phone;
		this.fee = fee;
		this.feetime = feetime;
		this.comments = comments;
		this.createtime = createtime;
	}

	// Property accessors

	public long getPhobillid() {
		return this.phobillid;
	}

	public void setPhobillid(long phobillid) {
		this.phobillid = phobillid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFee() {
		return this.fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFeetime() {
		return this.feetime;
	}

	public void setFeetime(String feetime) {
		this.feetime = feetime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}