package com.sxit.mem.models;

/**
 * MemDevicecommand entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemDevicecommand implements java.io.Serializable {

	// Fields

	private Long commandid;
	private Long deviceid;
	private String commananame;
	private String commandscript;
	private Long commandtype;
	private String typename;

	// Constructors

	/** default constructor */
	public MemDevicecommand() {
	}

	/** minimal constructor */
	public MemDevicecommand(Long commandid, Long deviceid) {
		this.commandid = commandid;
		this.deviceid = deviceid;
	}

	/** full constructor */
	public MemDevicecommand(Long commandid, Long deviceid, String commananame, String commandscript, Long commandtype,
			String typename) {
		this.commandid = commandid;
		this.deviceid = deviceid;
		this.commananame = commananame;
		this.commandscript = commandscript;
		this.commandtype = commandtype;
		this.typename = typename;
	}

	// Property accessors

	public Long getCommandid() {
		return this.commandid;
	}

	public void setCommandid(Long commandid) {
		this.commandid = commandid;
	}

	public Long getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(Long deviceid) {
		this.deviceid = deviceid;
	}

	public String getCommananame() {
		return this.commananame;
	}

	public void setCommananame(String commananame) {
		this.commananame = commananame;
	}

	public String getCommandscript() {
		return this.commandscript;
	}

	public void setCommandscript(String commandscript) {
		this.commandscript = commandscript;
	}

	public Long getCommandtype() {
		return this.commandtype;
	}

	public void setCommandtype(Long commandtype) {
		this.commandtype = commandtype;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}