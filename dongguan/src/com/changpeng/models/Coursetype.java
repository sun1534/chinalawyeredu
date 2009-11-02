package com.changpeng.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Coursetype entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Coursetype implements java.io.Serializable {

	// Fields

	private int typeid;
	private String typename;
	private String createuser;
	private Date createtime;
	private int warecount;
	private Set coursewares = new HashSet(0);

	// Constructors

	/** default constructor */
	public Coursetype() {
	}

	/** minimal constructor */
	public Coursetype(String typename) {
		this.typename = typename;
	}

	/** full constructor */
	public Coursetype(String typename, String createuser, Date createtime,
			int warecount, Set coursewares) {
		this.typename = typename;
		this.createuser = createuser;
		this.createtime = createtime;
		this.warecount = warecount;
		this.coursewares = coursewares;
	}

	// Property accessors

	public int getTypeid() {
		return this.typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getWarecount() {
		return this.warecount;
	}

	public void setWarecount(int warecount) {
		this.warecount = warecount;
	}

	public Set getCoursewares() {
		return this.coursewares;
	}

	public void setCoursewares(Set coursewares) {
		this.coursewares = coursewares;
	}

}