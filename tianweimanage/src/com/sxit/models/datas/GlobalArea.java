package com.sxit.models.datas;

/**
 * GlobalArea entity. @author MyEclipse Persistence Tools
 */

public class GlobalArea implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer parent;
	private String areaName;
	private Short areaLevel;

	// Constructors

	/** default constructor */
	public GlobalArea() {
	}

	/** full constructor */
	public GlobalArea(Integer parent, String areaName, Short areaLevel) {
		this.parent = parent;
		this.areaName = areaName;
		this.areaLevel = areaLevel;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return this.parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Short getAreaLevel() {
		return this.areaLevel;
	}

	public void setAreaLevel(Short areaLevel) {
		this.areaLevel = areaLevel;
	}

}