package com.changpeng.global.model;

/**
 * GlobalNumber entity. @author MyEclipse Persistence Tools
 */

public class GlobalNumber implements java.io.Serializable {

	// Fields

	private String number;
	private Integer provinceId;
	private Integer cityId;

	// Constructors

	/** default constructor */
	public GlobalNumber() {
	}

	/** full constructor */
	public GlobalNumber(Integer provinceId, Integer cityId) {
		this.provinceId = provinceId;
		this.cityId = cityId;
	}

	// Property accessors

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
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

}