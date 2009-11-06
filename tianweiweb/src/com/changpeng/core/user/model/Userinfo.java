package com.changpeng.core.user.model;

/**
 * CoreUserinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo implements java.io.Serializable {

	private Integer id;
	private String mobile;
	private String userName;
	private Short userRole;
	private Short status;
	private Integer provinceId;
	private Integer cityId;
	private Integer districtId;
	private String pic;

	private String classname;
	private String schoolname;
	// Constructors

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getSchoolname() {
		return schoolname;
	}

	public void setSchoolname(String schoolname) {
		this.schoolname = schoolname;
	}

	/** default constructor */
	public Userinfo() {
	}

	/** minimal constructor */
	public Userinfo(Integer id, String mobile, String userName,
			Short userRole, Short status, Integer provinceId, Integer cityId,
			Integer districtId) {
		this.id = id;
		this.mobile = mobile;
		this.userName = userName;
		this.userRole = userRole;
		this.status = status;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
	}

	/** full constructor */
	public Userinfo(Integer id, String mobile, String userName,
			Short userRole, Short status, Integer provinceId, Integer cityId,
			Integer districtId, String pic) {
		this.id = id;
		this.mobile = mobile;
		this.userName = userName;
		this.userRole = userRole;
		this.status = status;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
		this.pic = pic;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}