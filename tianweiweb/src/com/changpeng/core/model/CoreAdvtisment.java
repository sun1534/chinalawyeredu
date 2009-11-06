package com.changpeng.core.model;

/**
 * CoreAdvtisment entity. @author MyEclipse Persistence Tools
 */

public class CoreAdvtisment implements java.io.Serializable {

	// Fields

	private String pic;
	private String url;
	private Integer pos;
	private String description;
	private Short status;

	// Constructors

	/** default constructor */
	public CoreAdvtisment() {
	}

	/** minimal constructor */
	public CoreAdvtisment(String pic, String url, Integer pos,
			String description) {
		this.pic = pic;
		this.url = url;
		this.pos = pos;
		this.description = description;
	}

	/** full constructor */
	public CoreAdvtisment(String pic, String url, Integer pos,
			String description, Short status) {
		this.pic = pic;
		this.url = url;
		this.pos = pos;
		this.description = description;
		this.status = status;
	}

	// Property accessors

	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPos() {
		return this.pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}