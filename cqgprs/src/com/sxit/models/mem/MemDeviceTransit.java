package com.sxit.models.mem;

import java.sql.Timestamp;

import org.json.JSONObject;

/**
 * MemDevice entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MemDeviceTransit implements java.io.Serializable {

	private int deviceid;
	private String ip;
	private String port;
	private String loginname;
	private String pwd;
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * @return the loginname
	 */
	public String getLoginname() {
		return loginname;
	}
	/**
	 * @param loginname the loginname to set
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the deviceid
	 */
	public int getDeviceid() {
		return deviceid;
	}
	/**
	 * @param deviceid the deviceid to set
	 */
	public void setDeviceid(int deviceid) {
		this.deviceid = deviceid;
	}
}