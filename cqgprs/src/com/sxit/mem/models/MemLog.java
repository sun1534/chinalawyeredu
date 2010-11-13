package com.sxit.mem.models;

import java.util.Date;

/**
 * MemLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemLog implements java.io.Serializable {

	// Fields

	private Long logid;
	private Long userid;
	private String username;
	private Long deviceid;
	private String devicename;
	private Long commandid;
	private String commandname;
	private String result;
	private Date createtime;

	// Constructors

	/** default constructor */
	public MemLog() {
	}

	/** minimal constructor */
	public MemLog(Long logid) {
		this.logid = logid;
	}

	/** full constructor */
	public MemLog(Long logid, Long userid, String username, Long deviceid, String devicename, Long commandid,
			String commandname, String result, Date createtime) {
		this.logid = logid;
		this.userid = userid;
		this.username = username;
		this.deviceid = deviceid;
		this.devicename = devicename;
		this.commandid = commandid;
		this.commandname = commandname;
		this.result = result;
		this.createtime = createtime;
	}

	// Property accessors

	public Long getLogid() {
		return this.logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(Long deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevicename() {
		return this.devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public Long getCommandid() {
		return this.commandid;
	}

	public void setCommandid(Long commandid) {
		this.commandid = commandid;
	}

	public String getCommandname() {
		return this.commandname;
	}

	public void setCommandname(String commandname) {
		this.commandname = commandname;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}