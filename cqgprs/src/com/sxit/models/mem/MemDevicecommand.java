package com.sxit.models.mem;

import java.sql.Timestamp;

/**
 * MemDevicecommand entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemDevicecommand implements java.io.Serializable {

	// Fields

	private int commandid;
	private int deviceid;
	private String commananame;
	private String commandscript;
	private int commandtype;
	private String typename;
	private Timestamp createtime;
	private int createuser;
	private String createusername;

	// Constructors

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

	/**
	 * @return the createuser
	 */
	public int getCreateuser() {
		return createuser;
	}

	/**
	 * @param createuser the createuser to set
	 */
	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}

	/**
	 * @return the createusername
	 */
	public String getCreateusername() {
		return createusername;
	}

	/**
	 * @param createusername the createusername to set
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	/** default constructor */
	public MemDevicecommand() {
	}

	/** minimal constructor */
	public MemDevicecommand(int commandid, int deviceid) {
		this.commandid = commandid;
		this.deviceid = deviceid;
	}

	/** full constructor */
	public MemDevicecommand(int commandid, int deviceid, String commananame, String commandscript, int commandtype,
			String typename) {
		this.commandid = commandid;
		this.deviceid = deviceid;
		this.commananame = commananame;
		this.commandscript = commandscript;
		this.commandtype = commandtype;
		this.typename = typename;
	}

	// Property accessors

	public int getCommandid() {
		return this.commandid;
	}

	public void setCommandid(int commandid) {
		this.commandid = commandid;
	}

	public int getDeviceid() {
		return this.deviceid;
	}

	public void setDeviceid(int deviceid) {
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

	public int getCommandtype() {
		return this.commandtype;
	}

	public void setCommandtype(int commandtype) {
		this.commandtype = commandtype;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}