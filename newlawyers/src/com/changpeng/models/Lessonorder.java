package com.changpeng.models;

import java.sql.Timestamp;
import java.util.Date;

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
	private Date orderdate;
	private float orderprice;
	private Timestamp paydate;
	private short payresult;
	private String paytype;
	private String remarks;

	// Constructors

	/** default constructor */
	public Lessonorder() {
	}

	/** minimal constructor */
	public Lessonorder(int lessonid, int lawyerid, Date orderdate, float orderprice) {
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

	public Date getOrderdate() {
		return this.orderdate;
	}

	public void setOrderdate(Date orderdate) {
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

	public short getPayresult() {
		return this.payresult;
	}

	public void setPayresult(short payresult) {
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

}