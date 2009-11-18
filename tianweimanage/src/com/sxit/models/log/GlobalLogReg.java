package com.sxit.models.log;

import java.util.Date;

/**
 * GlobalLogReg entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class GlobalLogReg implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer provinceId;
	private Integer cityId;
	private Integer districtId;
	private String mobile;
	private String userName;
	private Short userRole;
	private Date regTime;
	private Short status;
	private Short resourceType;
	private Integer logflag;

	// Constructors

	/** default constructor */
	public GlobalLogReg() {
	}

	/** minimal constructor */
	public GlobalLogReg(Integer provinceId, Integer cityId, Integer districtId, String mobile, Date regTime, Short status, Short resourceType) {
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
		this.mobile = mobile;
		this.regTime = regTime;
		this.status = status;
		this.resourceType = resourceType;
	}

	/** full constructor */
	public GlobalLogReg(Integer provinceId, Integer cityId, Integer districtId, String mobile, String userName, Short userRole, Date regTime,
			Short status, Short resourceType, Integer logflag) {
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
		this.mobile = mobile;
		this.userName = userName;
		this.userRole = userRole;
		this.regTime = regTime;
		this.status = status;
		this.resourceType = resourceType;
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

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Short getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Short userRole) {
		this.userRole = userRole;
	}

	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(Short resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getLogflag() {
		return this.logflag;
	}

	public void setLogflag(Integer logflag) {
		this.logflag = logflag;
	}

}