package com.sxit.models.mem;

import java.sql.Timestamp;
import java.util.Date;

/**
 * MemLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemLog implements java.io.Serializable {

	// Fields

	private int logid;
	private int userid;
	private String username;
	private int deviceid;
	private String devicename;
	private int commandid;
	private String commandname;
	private String result;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public MemLog() {
	}

	/** minimal constructor */
	public MemLog(int logid) {
		this.logid = logid;
	}

	// Property accessors

	public int getLogid() {
		return this.logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}

	public String getDevicename() {
		return this.devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public int getCommandid() {
		return this.commandid;
	}

	public void setCommandid(int commandid) {
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

	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}



}