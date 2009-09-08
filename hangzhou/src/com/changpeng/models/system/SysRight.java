
package com.changpeng.models.system;

// Generated 2008-2-21 9:22:49 by Hibernate Tools 3.2.0.CR1

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TSysRight generated by hbm2java
 */
public class SysRight implements java.io.Serializable {
	private static Log LOG = LogFactory.getLog(SysRight.class);
	private String rightcode;
	private String rightname;
	private String parentcode;
	private boolean logflag;

	private boolean ismenu;
	private boolean iscommon;
	private boolean isnode;
	private byte grade;

	private byte orderid;
	private String imageurl;
	private String linkurl;
	private String comments;
	private String opentarget;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);

	public SysRight() {
	}

	public SysRight(String rightcode, String rightname) {
		this.rightcode = rightcode;
		this.rightname = rightname;
	}

	public SysRight(String rightcode, String rightname, boolean logflag, String parentcode, boolean ismenu, boolean iscommon, byte grade,
			Set<SysUser> SysUsers, Set<SysRole> SysRoles) {
		this.rightcode = rightcode;
		this.rightname = rightname;
		this.logflag = logflag;
		this.parentcode = parentcode;
		this.ismenu = ismenu;
		this.iscommon = iscommon;
		this.grade = grade;
		this.sysUsers = SysUsers;

		this.sysRoles = SysRoles;
	}

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

	public boolean getLogflag() {
		return this.logflag;
	}

	public void setLogflag(boolean logflag) {
		this.logflag = logflag;
	}

	public String getParentcode() {
		return this.parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public boolean getIsmenu() {
		return this.ismenu;
	}

	public void setIsmenu(boolean ismenu) {
		this.ismenu = ismenu;
	}

	public boolean getIscommon() {
		return this.iscommon;
	}

	public void setIscommon(boolean iscommon) {
		this.iscommon = iscommon;
	}

	public boolean getIsnode() {
		return this.isnode;
	}

	public void setIsnode(boolean isnode) {
		this.isnode = isnode;
	}

	public byte getGrade() {
		return this.grade;
	}

	public void setGrade(byte grade) {
		this.grade = grade;
	}

	public byte getOrderid() {
		return this.orderid;
	}

	public void setOrderid(byte orderid) {
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

	public Set<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> SysUsers) {
		this.sysUsers = SysUsers;
	}

	public Set<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(Set<SysRole> SysRoles) {
		this.sysRoles = SysRoles;
	}
/**
 * 在list中排序的依据
 */
	private int compareid;
	public int getCompareid() {
		return this.compareid;
	}
	public void setCompareid(int compareid){
		this.compareid=compareid;
	}
}