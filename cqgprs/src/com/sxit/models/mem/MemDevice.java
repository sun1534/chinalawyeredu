package com.sxit.models.mem;

import java.sql.Timestamp;

import org.json.JSONObject;

/**
 * MemDevice entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemDevice implements java.io.Serializable {

private int istransit;
//#{'0':'TELNET','2':'SSH','1':'华为TELNET'}
private int ishuawei;
	// Fields
	
	private int deviceid;
	private String devicename;
	private String ip;
	private int port;
	private String description;
	private int status;
	private String loginName;
	private String loginPwd;
	private Timestamp createtime;
	private int createuser;
	private String createusername;

	// Constructors

	/**
	 * @return the createusername
	 */
	public String getCreateusername() {
		return createusername;
	}

	/**
	 * @param createusername
	 *            the createusername to set
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	/** default constructor */
	public MemDevice() {
	}

	/** minimal constructor */
	public MemDevice(int deviceid) {
		this.deviceid = deviceid;
	}

	// Property accessors

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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public int getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(int createuser) {
		this.createuser = createuser;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the loginPwd
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * @param loginPwd
	 *            the loginPwd to set
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	/**
	 * @return the istransit
	 */
	public int getIstransit() {
		return istransit;
	}

	/**
	 * @param istransit the istransit to set
	 */
	public void setIstransit(int istransit) {
		this.istransit = istransit;
	}

	/**
	 * @return the ishuawei
	 */
	public int getIshuawei() {
		return ishuawei;
	}

	/**
	 * @param ishuawei the ishuawei to set
	 */
	public void setIshuawei(int ishuawei) {
		this.ishuawei = ishuawei;
	}

	

}