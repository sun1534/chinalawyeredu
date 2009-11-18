package com.changpeng.core.model;

import java.sql.Timestamp;

/**
 * CoreProduct entity. @author MyEclipse Persistence Tools
 */

public class CoreProduct implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Double price;
	
	public String getPricestr(){
		return com.changpeng.core.progress.action.NumberUtil.toMoney(price);
	}
	
	private String unit;
	private String pic;
	private String description;
	private Timestamp createtime;
	private Short status;
	private Integer producttype;
	private Integer feerate;
	private Integer maxpic;
	private Integer maxvideo;
	private Integer maxaudio;
	private Integer maxcontent;
	private Integer createuserid;

	// Constructors

	/** default constructor */
	public CoreProduct() {
	}

	/** minimal constructor */
	public CoreProduct(String name, Double price, String pic,
			String description, Timestamp createtime, Integer producttype,
			Integer feerate, Integer maxpic, Integer maxvideo,
			Integer maxaudio, Integer maxcontent, Integer createuserid) {
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.description = description;
		this.createtime = createtime;
		this.producttype = producttype;
		this.feerate = feerate;
		this.maxpic = maxpic;
		this.maxvideo = maxvideo;
		this.maxaudio = maxaudio;
		this.maxcontent = maxcontent;
		this.createuserid = createuserid;
	}

	/** full constructor */
	public CoreProduct(String name, Double price, String unit, String pic,
			String description, Timestamp createtime, Short status,
			Integer producttype, Integer feerate, Integer maxpic,
			Integer maxvideo, Integer maxaudio, Integer maxcontent,
			Integer createuserid) {
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.pic = pic;
		this.description = description;
		this.createtime = createtime;
		this.status = status;
		this.producttype = producttype;
		this.feerate = feerate;
		this.maxpic = maxpic;
		this.maxvideo = maxvideo;
		this.maxaudio = maxaudio;
		this.maxcontent = maxcontent;
		this.createuserid = createuserid;
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

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getProducttype() {
		return this.producttype;
	}

	public void setProducttype(Integer producttype) {
		this.producttype = producttype;
	}

	public Integer getFeerate() {
		return this.feerate;
	}

	public void setFeerate(Integer feerate) {
		this.feerate = feerate;
	}

	public Integer getMaxpic() {
		return this.maxpic;
	}

	public void setMaxpic(Integer maxpic) {
		this.maxpic = maxpic;
	}

	public Integer getMaxvideo() {
		return this.maxvideo;
	}

	public void setMaxvideo(Integer maxvideo) {
		this.maxvideo = maxvideo;
	}

	public Integer getMaxaudio() {
		return this.maxaudio;
	}

	public void setMaxaudio(Integer maxaudio) {
		this.maxaudio = maxaudio;
	}

	public Integer getMaxcontent() {
		return this.maxcontent;
	}

	public void setMaxcontent(Integer maxcontent) {
		this.maxcontent = maxcontent;
	}

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

}