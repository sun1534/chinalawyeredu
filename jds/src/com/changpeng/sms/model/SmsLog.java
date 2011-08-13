package com.changpeng.sms.model;

import java.util.Date;

/**
 * SmsLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SmsLog implements java.io.Serializable {

	// Fields

	private Long smslogid;
	private String mobile;
	private String smscontent;
	private Date createtime;
	private Long createuser;
	private Date sendtime;

	// Constructors

	/** default constructor */
	public SmsLog() {
	}

	/** minimal constructor */
	public SmsLog(String mobile) {
		this.mobile = mobile;
	}

	/** full constructor */
	public SmsLog(String mobile, String smscontent, Date createtime,
			Long createuser, Date sendtime) {
		this.mobile = mobile;
		this.smscontent = smscontent;
		this.createtime = createtime;
		this.createuser = createuser;
		this.sendtime = sendtime;
	}

	// Property accessors

	public Long getSmslogid() {
		return this.smslogid;
	}

	public void setSmslogid(Long smslogid) {
		this.smslogid = smslogid;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmscontent() {
		return this.smscontent;
	}

	public void setSmscontent(String smscontent) {
		this.smscontent = smscontent;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(Long createuser) {
		this.createuser = createuser;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

}