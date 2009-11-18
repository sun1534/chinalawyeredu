package com.sxit.models.service;

import java.util.Date;

/**
 * CorePartner entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CorePartner implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String logo;
	private String url;
	private String description;
	private Integer status;
	private Date createtime;

	// Constructors

	/** default constructor */
	public CorePartner() {
	}

	/** minimal constructor */
	public CorePartner(String name, String logo, Integer status) {
		this.name = name;
		this.logo = logo;
		this.status = status;
	}

	/** full constructor */
	public CorePartner(String name, String logo, String url, String description, Integer status, Date createtime) {
		this.name = name;
		this.logo = logo;
		this.url = url;
		this.description = description;
		this.status = status;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}