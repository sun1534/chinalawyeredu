package com.changpeng.core.model;

import java.sql.Timestamp;

/**
 * CorePublish entity. @author MyEclipse Persistence Tools
 */

public class CorePublish implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer productid;
	private Integer channelid;
	private Timestamp starttime;
	private Timestamp endtime;
	private Integer userid;
	private Short statusid;
	private Double fee;
	private Timestamp createtime;
	private Timestamp approvcetime;
	private Timestamp feetime;
	private String orderno;
	private Short paystatus;
	private String remarks;
	private Short userRole;

	// Constructors

	/** default constructor */
	public CorePublish() {
	}

	/** minimal constructor */
	public CorePublish(Integer productid, Integer channelid,
			Timestamp starttime, Timestamp endtime, Integer userid,
			Short statusid, Double fee, Timestamp createtime, String remarks) {
		this.productid = productid;
		this.channelid = channelid;
		this.starttime = starttime;
		this.endtime = endtime;
		this.userid = userid;
		this.statusid = statusid;
		this.fee = fee;
		this.createtime = createtime;
		this.remarks = remarks;
	}

	/** full constructor */
	public CorePublish(Integer productid, Integer channelid,
			Timestamp starttime, Timestamp endtime, Integer userid,
			Short statusid, Double fee, Timestamp createtime,
			Timestamp approvcetime, Timestamp feetime, String orderno,
			Short paystatus, String remarks) {
		this.productid = productid;
		this.channelid = channelid;
		this.starttime = starttime;
		this.endtime = endtime;
		this.userid = userid;
		this.statusid = statusid;
		this.fee = fee;
		this.createtime = createtime;
		this.approvcetime = approvcetime;
		this.feetime = feetime;
		this.orderno = orderno;
		this.paystatus = paystatus;
		this.remarks = remarks;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductid() {
		return this.productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getChannelid() {
		return this.channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Short getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Short statusid) {
		this.statusid = statusid;
	}

	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getApprovcetime() {
		return this.approvcetime;
	}

	public void setApprovcetime(Timestamp approvcetime) {
		this.approvcetime = approvcetime;
	}

	public Timestamp getFeetime() {
		return this.feetime;
	}

	public void setFeetime(Timestamp feetime) {
		this.feetime = feetime;
	}

	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public Short getPaystatus() {
		return this.paystatus;
	}

	public void setPaystatus(Short paystatus) {
		this.paystatus = paystatus;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Short getUserRole() {
		return userRole;
	}

	public void setUserRole(Short userRole) {
		this.userRole = userRole;
	}

}