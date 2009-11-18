package com.sxit.models.log;

import java.util.Date;

/**
 * GlobalLogLogin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class GlobalLogLogin implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String userName;
	private String mobile;
	private Short userRole;
	private String loginIp;
	private Date loginTime;
	private Date quitTime;

	// Constructors

	/** default constructor */
	public GlobalLogLogin() {
	}

	/** minimal constructor */
	public GlobalLogLogin(Integer userid, Short userRole, String loginIp, Date loginTime) {
		this.userid = userid;
		this.userRole = userRole;
		this.loginIp = loginIp;
		this.loginTime = loginTime;
	}

	/** full constructor */
	public GlobalLogLogin(Integer userid, String userName, String mobile, Short userRole, String loginIp, Date loginTime, Date quitTime) {
		this.userid = userid;
		this.userName = userName;
		this.mobile = mobile;
		this.userRole = userRole;
		this.loginIp = loginIp;
		this.loginTime = loginTime;
		this.quitTime = quitTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Short getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Short userRole) {
		this.userRole = userRole;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getQuitTime() {
		return this.quitTime;
	}

	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}

}