package com.sxit.mem.models;

import java.util.Date;

/**
 * MemDevice entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemDevice implements java.io.Serializable {

	// Fields

	private Long deviceid;
	private String devicename;
	private String ip;
	private Long port;
	private String description;
	private Long status;
	private Date createtime;
	private Long createuser;

	// Constructors

	/** default constructor */
	public MemDevice() {
	}

	/** minimal constructor */
	public MemDevice(Long deviceid) {
		this.deviceid = deviceid;
	}

	/** full constructor */
	public MemDevice(Long deviceid, String devicename, String ip, Long port, String description, Long status,
			Date createtime, Long createuser) {
		this.deviceid = deviceid;
		this.devicename = devicename;
		this.ip = ip;
		this.port = port;
		this.description = description;
		this.status = status;
		this.createtime = createtime;
		this.createuser = createuser;
	}

	// Property accessors

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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getPort() {
		return this.port;
	}

	public void setPort(Long port) {
		this.port = port;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
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

}