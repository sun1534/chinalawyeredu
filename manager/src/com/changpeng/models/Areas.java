package com.changpeng.models;

/**
 * Areas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Areas implements java.io.Serializable {

	// Fields

	private int areaCode;
	private String areaName;
	private int parentAreaCode;
	private int root;
	private int zone;
	private String navigateUrl;
	private int grade;

	// Constructors

	/** default constructor */
	public Areas() {
	}

	

	// Property accessors

	public int getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getParentAreaCode() {
		return this.parentAreaCode;
	}

	public void setParentAreaCode(int parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}

	public int getRoot() {
		return this.root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public int getZone() {
		return this.zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public String getNavigateUrl() {
		return this.navigateUrl;
	}

	public void setNavigateUrl(String navigateUrl) {
		this.navigateUrl = navigateUrl;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}