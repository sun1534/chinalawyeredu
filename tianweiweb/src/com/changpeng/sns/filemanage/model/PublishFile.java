package com.changpeng.sns.filemanage.model;


/**
 * SnsFile entity. @author MyEclipse Persistence Tools
 */

public class PublishFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer typeid;
	private String fileName;
	private String url;
	
	private Short publishstatus;

	// Constructors

	/** default constructor */
	public PublishFile() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Short getPublishstatus() {
		return publishstatus;
	}

	public void setPublishstatus(Short publishstatus) {
		this.publishstatus = publishstatus;
	}



}