package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Lessonorder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonorder implements java.io.Serializable {

	// Fields

	private int id;
	private int lessonid;
	private int lawyerid;
	private Timestamp orderdate;
	private String orderno;
	private float orderprice;
	private Timestamp paydate;
	private int payresult;
	private String paytype;
	private String remarks;

	// Constructors

	/** default constructor */
	public Lessonorder() {
	}

	/** minimal constructor */
	public Lessonorder(int lessonid, int lawyerid, Timestamp orderdate, float orderprice) {
		this.lessonid = lessonid;
		this.lawyerid = lawyerid;
		this.orderdate = orderdate;
		this.orderprice = orderprice;
	}


	// Property accessors

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public int getLawyerid() {
		return this.lawyerid;
	}

	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}

	public Timestamp getOrderdate() {
		return this.orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

	public float getOrderprice() {
		return this.orderprice;
	}

	public void setOrderprice(float orderprice) {
		this.orderprice = orderprice;
	}

	public Timestamp getPaydate() {
		return this.paydate;
	}

	public void setPaydate(Timestamp paydate) {
		this.paydate = paydate;
	}

	public int getPayresult() {
		return this.payresult;
	}

	public void setPayresult(int payresult) {
		this.payresult = payresult;
	}

	public String getPaytype() {
		return this.paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the orderno
	 */
	public String getOrderno() {
		return orderno;
	}

	/**
	 * @param orderno the orderno to set
	 */
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

}