package com.sxit.models.service;

import java.util.Date;

/**
 * CoreChannel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CoreChannel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String pic;
	private String description;
	private Date createtime;
	private Short status;

	// Constructors

	/** default constructor */
	public CoreChannel() {
	}

	/** minimal constructor */
	public CoreChannel(String name, String pic, Date createtime, Short status) {
		this.name = name;
		this.pic = pic;
		this.createtime = createtime;
		this.status = status;
	}

	/** full constructor */
	public CoreChannel(String name, String pic, String description, Date createtime, Short status) {
		this.name = name;
		this.pic = pic;
		this.description = description;
		this.createtime = createtime;
		this.status = status;
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

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}