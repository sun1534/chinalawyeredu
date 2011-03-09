package com.uu800.admin.base.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * LogOpt entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogOpt implements java.io.Serializable {

	// Fields

	private int id;
	private int userId;
	private String loginName;
	private int orgId;
	private String moduleId;
	private String rightCode;
	private String logMsg;
	private String result;
	private String logIp;
	private Timestamp logTime;

	// Constructors

	/** default constructor */
	public LogOpt() {
	}

	/** minimal constructor */
	public LogOpt(int id, int userId, int orgId, String logIp, Timestamp logTime) {
		this.id = id;
		this.userId = userId;
		this.orgId = orgId;
		this.logIp = logIp;
		this.logTime = logTime;
	}

	/** full constructor */
	public LogOpt(int id, int userId, String loginName, int orgId, String moduleId, String rightCode,
			String logMsg, String result, String logIp, Timestamp logTime) {
		this.id = id;
		this.userId = userId;
		this.loginName = loginName;
		this.orgId = orgId;
		this.moduleId = moduleId;
		this.rightCode = rightCode;
		this.logMsg = logMsg;
		this.result = result;
		this.logIp = logIp;
		this.logTime = logTime;
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

	public int getOrgId() {
		return this.orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getRightCode() {
		return this.rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public String getLogMsg() {
		return this.logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLogIp() {
		return this.logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public Timestamp getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}

}