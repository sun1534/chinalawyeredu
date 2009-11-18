package com.sxit.models.log;

import java.util.Date;

/**
 * GlobalLogOperation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class GlobalLogOperation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer provinceId;
	private Integer cityId;
	private Integer districtId;
	private Integer userid;
	private String userName;
	private String mobile;
	private Short userRole;
	private Date operateTime;
	private String operateIp;
	private String modelKey;
	private String modelName;
	private Integer logflag;

	// Constructors

	/** default constructor */
	public GlobalLogOperation() {
	}

	/** minimal constructor */
	public GlobalLogOperation(Integer provinceId, Integer cityId, Integer districtId, Integer userid, Short userRole, Date operateTime,
			String operateIp) {
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
		this.userid = userid;
		this.userRole = userRole;
		this.operateTime = operateTime;
		this.operateIp = operateIp;
	}

	/** full constructor */
	public GlobalLogOperation(Integer provinceId, Integer cityId, Integer districtId, Integer userid, String userName, String mobile, Short userRole,
			Date operateTime, String operateIp, String modelKey, String modelName, Integer logflag) {
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
		this.userid = userid;
		this.userName = userName;
		this.mobile = mobile;
		this.userRole = userRole;
		this.operateTime = operateTime;
		this.operateIp = operateIp;
		this.modelKey = modelKey;
		this.modelName = modelName;
		this.logflag = logflag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Short getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Short userRole) {
		this.userRole = userRole;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateIp() {
		return this.operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public String getModelKey() {
		return this.modelKey;
	}

	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}

	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Integer getLogflag() {
		return this.logflag;
	}

	public void setLogflag(Integer logflag) {
		this.logflag = logflag;
	}

}