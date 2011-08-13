package com.changpeng.service.model;

import java.util.Date;

/**
 * TserBus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TserBus implements java.io.Serializable {

	// Fields

	private long busid;
	private String busno;
	private String checktime;
	private String insuretime;
	private String roadfee;
	private String forcetime;
	private String jiyou;
	private String baoyang;
	private String comments;
	private Date createtime;

	// Constructors

	/** default constructor */
	public TserBus() {
	}

	/** full constructor */
	public TserBus(String busno, String checktime, String insuretime,
			String roadfee, String forcetime, String jiyou, String baoyang,
			String comments, Date createtime) {
		this.busno = busno;
		this.checktime = checktime;
		this.insuretime = insuretime;
		this.roadfee = roadfee;
		this.forcetime = forcetime;
		this.jiyou = jiyou;
		this.baoyang = baoyang;
		this.comments = comments;
		this.createtime = createtime;
	}

	// Property accessors

	public long getBusid() {
		return this.busid;
	}

	public void setBusid(long busid) {
		this.busid = busid;
	}

	public String getBusno() {
		return this.busno;
	}

	public void setBusno(String busno) {
		this.busno = busno;
	}

	public String getChecktime() {
		return this.checktime;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}

	public String getInsuretime() {
		return this.insuretime;
	}

	public void setInsuretime(String insuretime) {
		this.insuretime = insuretime;
	}

	public String getRoadfee() {
		return this.roadfee;
	}

	public void setRoadfee(String roadfee) {
		this.roadfee = roadfee;
	}

	public String getForcetime() {
		return this.forcetime;
	}

	public void setForcetime(String forcetime) {
		this.forcetime = forcetime;
	}

	public String getJiyou() {
		return this.jiyou;
	}

	public void setJiyou(String jiyou) {
		this.jiyou = jiyou;
	}

	public String getBaoyang() {
		return this.baoyang;
	}

	public void setBaoyang(String baoyang) {
		this.baoyang = baoyang;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}