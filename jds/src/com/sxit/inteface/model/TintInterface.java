package com.sxit.inteface.model;

import java.sql.Timestamp;

/**
 * TintInterface entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TintInterface implements java.io.Serializable {

	// Fields

	private Long intefaceid;
	private String username;
	private String mobile;
	private String bqqno;
	private String email;
	private String content;
	private int statusid;
	private Timestamp createtime;
	private int flag;

	// Constructors

	/** default constructor */
	public TintInterface() {
	}

	/** full constructor */
	public TintInterface(String username, String mobile, String bqqno,
			String email, String content, int statusid, Timestamp createtime,
			int flag) {
		this.username = username;
		this.mobile = mobile;
		this.bqqno = bqqno;
		this.email = email;
		this.content = content;
		this.statusid = statusid;
		this.createtime = createtime;
		this.flag = flag;
	}

	// Property accessors

	public Long getIntefaceid() {
		return this.intefaceid;
	}

	public void setIntefaceid(Long intefaceid) {
		this.intefaceid = intefaceid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBqqno() {
		return this.bqqno;
	}

	public void setBqqno(String bqqno) {
		this.bqqno = bqqno;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatusid() {
		return this.statusid;
	}

	public void setStatusid(int statusid) {
		this.statusid = statusid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getFlag() {
		return this.flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}