package com.uu800.admin.base.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * LogLogin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogLogin implements java.io.Serializable {

	// Fields

	private int id;
	private int userId;
	private String loginName;
	private String loginIp;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private String loginReamrks;
	private String logoutReamrks;
	// Constructors

	/** default constructor */
	public LogLogin() {
	}

	

	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLogoutTime() {
		return this.logoutTime;
	}

	public void setLogoutTime(Timestamp logoutTime) {
		this.logoutTime = logoutTime;
	}




	/**
	 * @return the loginReamrks
	 */
	public String getLoginReamrks() {
		return loginReamrks;
	}



	/**
	 * @param loginReamrks the loginReamrks to set
	 */
	public void setLoginReamrks(String loginReamrks) {
		this.loginReamrks = loginReamrks;
	}



	/**
	 * @return the logoutReamrks
	 */
	public String getLogoutReamrks() {
		return logoutReamrks;
	}



	/**
	 * @param logoutReamrks the logoutReamrks to set
	 */
	public void setLogoutReamrks(String logoutReamrks) {
		this.logoutReamrks = logoutReamrks;
	}

}