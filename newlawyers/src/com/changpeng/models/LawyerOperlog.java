package com.changpeng.models;

import java.sql.Timestamp;

/**
 * LawyerOperlog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LawyerOperlog implements java.io.Serializable {

	// Fields

	private int logid;
	private int lawyerid;
	private String lawyername;
	private int loginid;
	private String rightCode;
	private Timestamp opTime;
	private String opResult;
	private int provinceid;
	private int cityid;
	private int officeid;

	// Constructors

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
	public LawyerOperlog() {
	}

	/** minimal constructor */
	public LawyerOperlog(Timestamp opTime) {
		this.opTime = opTime;
	}

	/** full constructor */
	public LawyerOperlog(int lawyerid, String lawyername, int loginid,
			String rightCode, Timestamp opTime, String opResult) {
		this.lawyerid = lawyerid;
		this.lawyername = lawyername;
		this.loginid = loginid;
		this.rightCode = rightCode;
		this.opTime = opTime;
		this.opResult = opResult;
	}

	// Property accessors

	public int getLogid() {
		return this.logid;
	}

	public void setLogid(int logid) {
		this.logid = logid;
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

	public int getLoginid() {
		return this.loginid;
	}

	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}

	public String getRightCode() {
		return this.rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public Timestamp getOpTime() {
		return this.opTime;
	}

	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}

	public String getOpResult() {
		return this.opResult;
	}

	public void setOpResult(String opResult) {
		this.opResult = opResult;
	}


}