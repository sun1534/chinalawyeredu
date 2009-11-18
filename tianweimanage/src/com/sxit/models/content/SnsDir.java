package com.sxit.models.content;

/**
 * SnsDir entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SnsDir implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer dirtype;
	private String dirname;
	private String description;

	// Constructors

	/** default constructor */
	public SnsDir() {
	}

	/** full constructor */
	public SnsDir(Integer userid, Integer dirtype, String dirname, String description) {
		this.userid = userid;
		this.dirtype = dirtype;
		this.dirname = dirname;
		this.description = description;
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

	public Integer getDirtype() {
		return this.dirtype;
	}

	public void setDirtype(Integer dirtype) {
		this.dirtype = dirtype;
	}

	public String getDirname() {
		return this.dirname;
	}

	public void setDirname(String dirname) {
		this.dirname = dirname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}