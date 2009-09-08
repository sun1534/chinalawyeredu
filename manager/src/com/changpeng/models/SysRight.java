package com.changpeng.models;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRight entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRight implements java.io.Serializable {

	// Fields

	private String rightcode;
	private String rightname;
	private String parentcode;
	private boolean logflag;
	private boolean ismenu;
//	private boolean iscommon;
	private boolean isextend;
	private int grade;
	private int orderid;
	private String imageurl;
	private String linkurl;
	private String comments;
	private String opentarget;
	// private Set<SysGroup> sysGroups = new HashSet<SysGroup>(0);
	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	// Constructors

	/** default constructor */
	public SysRight() {
	}

	/** minimal constructor */
	public SysRight(String rightname, boolean logflag, boolean ismenu, boolean isextend, int orderid) {
		this.rightname = rightname;
		this.logflag = logflag;
		this.ismenu = ismenu;
		this.isextend = isextend;
		this.orderid = orderid;
	}

	// Property accessors

	public String getRightcode() {
		return this.rightcode;
	}

	public void setRightcode(String rightcode) {
		this.rightcode = rightcode;
	}

	public String getRightname() {
		return this.rightname;
	}

	public void setRightname(String rightname) {
		this.rightname = rightname;
	}

	public String getParentcode() {
		return this.parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public boolean getLogflag() {
		return this.logflag;
	}

	public void setLogflag(boolean logflag) {
		this.logflag = logflag;
	}

	public boolean getIsmenu() {
		return this.ismenu;
	}

	public void setIsmenu(boolean ismenu) {
		this.ismenu = ismenu;
	}

//	public boolean getIscommon() {
//		return this.iscommon;
//	}
//
//	public void setIscommon(boolean iscommon) {
//		this.iscommon = iscommon;
//	}

	public boolean getIsextend() {
		return this.isextend;
	}

	public void setIsextend(boolean isextend) {
		this.isextend = isextend;
	}

	public int getGrade() {
		return this.grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getOrderid() {
		return this.orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getImageurl() {
		return this.imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getLinkurl() {
		return this.linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getOpentarget() {
		return this.opentarget;
	}

	public void setOpentarget(String opentarget) {
		this.opentarget = opentarget;
	}

	// /**
	// * @return the sysGroups
	// */
	// public Set<SysGroup> getSysGroups() {
	// return sysGroups;
	// }
	//
	// /**
	// * @param sysGroups the sysGroups to set
	// */
	// public void setSysGroups(Set<SysGroup> sysGroups) {
	// this.sysGroups = sysGroups;
	// }

	/**
	 * @return the sysRoles
	 */
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	/**
	 * @param sysRoles
	 *            the sysRoles to set
	 */
	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	/**
	 * @return the sysUsers
	 */
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	/**
	 * @param sysUsers
	 *            the sysUsers to set
	 */
	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	/**
	 * 在list中排序的依据
	 */
	private long compareid;

	public long getCompareid() {
		return this.compareid;
	}

	public void setCompareid(long compareid) {
		this.compareid = compareid;
	}

}