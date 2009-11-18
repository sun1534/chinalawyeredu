package com.sxit.models.service;

/**
 * CoreAdvtisment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CoreAdvtisment implements java.io.Serializable {

	// Fields

	private Integer pos;
	private String pic;
	private String url;
	private String description;
	private Short status;
	private Short advtype;

	// Constructors

	/** default constructor */
	public CoreAdvtisment() {
	}

	/** minimal constructor */
	public CoreAdvtisment(String pic, String url, String description) {
		this.pic = pic;
		this.url = url;
		this.description = description;
	}

	/** full constructor */
	public CoreAdvtisment(String pic, String url, String description, Short status, Short advtype) {
		this.pic = pic;
		this.url = url;
		this.description = description;
		this.status = status;
		this.advtype = advtype;
	}

	// Property accessors

	public Integer getPos() {
		return this.pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

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

	public Short getAdvtype() {
		return this.advtype;
	}

	public void setAdvtype(Short advtype) {
		this.advtype = advtype;
	}

}