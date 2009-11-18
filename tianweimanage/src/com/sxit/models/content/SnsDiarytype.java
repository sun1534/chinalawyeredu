package com.sxit.models.content;

/**
 * SnsDiarytype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SnsDiarytype implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String typename;

	// Constructors

	/** default constructor */
	public SnsDiarytype() {
	}

	/** full constructor */
	public SnsDiarytype(Integer userid, String typename) {
		this.userid = userid;
		this.typename = typename;
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

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

}