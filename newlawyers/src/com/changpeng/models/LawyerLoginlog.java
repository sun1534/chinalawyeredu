package com.changpeng.models;

import java.sql.Timestamp;

/**
 * LawyerLoginlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LawyerLoginlog implements java.io.Serializable {

	// Fields

	private int loginid;
	private int lawyerid;
	private String lawyername;
	private Timestamp loginTime;
	private Timestamp logoutTime;
	private String loginip;
	private boolean islast;
	private int inSysTime;
	private String contextid;
	private String remarks;
	private String loginremarks;
	private int provinceid;
	private int cityid;
	private int officeid;

	
	/**
	 * @return the provinceid
	 */
	public int getProvinceid() {
		return provinceid;
	}

	/**
	 * @param provinceid the provinceid to set
	 */
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}

	/**
	 * @return the cityid
	 */
	public int getCityid() {
		return cityid;
	}

	/**
	 * @param cityid the cityid to set
	 */
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	/**
	 * @return the officeid
	 */
	public int getOfficeid() {
		return officeid;
	}

	/**
	 * @param officeid the officeid to set
	 */
	public void setOfficeid(int officeid) {
		this.officeid = officeid;
	}

	/** default constructor */
	public LawyerLoginlog() {
	}

	/** minimal constructor */
	public LawyerLoginlog(int lawyerid, String lawyername, Timestamp loginTime,
			String loginip) {
		this.lawyerid = lawyerid;
		this.lawyername = lawyername;
		this.loginTime = loginTime;
		this.loginip = loginip;
	}



	// Property accessors

	public int getLoginid() {
		return this.loginid;
	}

	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	public int getLawyerid() {
		return this.lawyerid;
	}

	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	public String getLawyername() {
		return this.lawyername;
	}

	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
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

	public String getLoginip() {
		return this.loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	public boolean getIslast() {
		return this.islast;
	}

	public void setIslast(boolean islast) {
		this.islast = islast;
	}

	public int getInSysTime() {
		return this.inSysTime;
	}

	public void setInSysTime(int inSysTime) {
		this.inSysTime = inSysTime;
	}

	public String getContextid() {
		return this.contextid;
	}

	public void setContextid(String contextid) {
		this.contextid = contextid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLoginremarks() {
		return this.loginremarks;
	}

	public void setLoginremarks(String loginremarks) {
		this.loginremarks = loginremarks;
	}

}