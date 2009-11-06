package com.changpeng.sns.filemanage.model;

/**
 * SnsDir entity. @author MyEclipse Persistence Tools
 */

public class SnsDir implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String dirname;
	private int dirtype;
	private String description;
	// Constructors

	/** default constructor */
	public SnsDir() {
	}

	/** full constructor */
	public SnsDir(Integer userid, String dirname) {
		this.userid = userid;
		this.dirname = dirname;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getDirname() {
		return this.dirname;
	}

	public void setDirname(String dirname) {
		this.dirname = dirname;
	}

	public int getDirtype() {
		return dirtype;
	}

	public void setDirtype(int dirtype) {
		this.dirtype = dirtype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}