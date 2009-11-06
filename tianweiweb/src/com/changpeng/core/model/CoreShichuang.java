package com.changpeng.core.model;

import java.util.Date;

/**
 * CoreShichuang entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CoreShichuang implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String url;
	private int order;
	private String type="sc";
	private String remarks;
	private Integer createuserid;
	private String createusername;
	private Date createtime;

	// Constructors

	/** default constructor */
	public CoreShichuang() {
	}

	/** full constructor */
	public CoreShichuang(String title, String url, String remarks, Integer createuserid, String createusername, Date createtime) {
		this.title = title;
		this.url = url;
		this.remarks = remarks;
		this.createuserid = createuserid;
		this.createusername = createusername;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public String getCreateusername() {
		return this.createusername;
	}

	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}