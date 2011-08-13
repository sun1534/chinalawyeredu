package com.sxit.info.model;

import java.util.Date;

/**
 * TinfAttach entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TinfAttach implements java.io.Serializable {

	// Fields

	private Long attachid;
	private TinfInfo tinfInfo;
	private String filename;
	private String filepath;
	private Long createuser;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TinfAttach() {
	}

	/** minimal constructor */
	public TinfAttach(Long attachid, TinfInfo tinfInfo) {
		this.attachid = attachid;
		this.tinfInfo = tinfInfo;
	}

	/** full constructor */
	public TinfAttach(Long attachid, TinfInfo tinfInfo, String filename,
			String filepath, Long createuser, Date createtime) {
		this.attachid = attachid;
		this.tinfInfo = tinfInfo;
		this.filename = filename;
		this.filepath = filepath;
		this.createuser = createuser;
		this.createtime = createtime;
	}

	// Property accessors

	public Long getAttachid() {
		return this.attachid;
	}

	public void setAttachid(Long attachid) {
		this.attachid = attachid;
	}

	public TinfInfo getTinfInfo() {
		return this.tinfInfo;
	}

	public void setTinfInfo(TinfInfo tinfInfo) {
		this.tinfInfo = tinfInfo;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Long getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(Long createuser) {
		this.createuser = createuser;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}