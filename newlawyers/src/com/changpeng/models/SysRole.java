package com.changpeng.models;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	// Fields

	private int roleid;
	private String rolename;
	private boolean datavisible;
	private int gradeid;
	private boolean cansamegrade;
	private String createuser;
	private Timestamp createtime;
	private String comments;
	private Set<SysRoleVisible> sysRoleVisibles = new HashSet<SysRoleVisible>(0);
	private Set<SysRight> sysRights = new HashSet<SysRight>(0);
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);

	// Constructors

	/** default constructor */
	public SysRole() {
	}


	// Property accessors

	public int getRoleid() {
		return this.roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public boolean getDatavisible() {
		return this.datavisible;
	}

	public void setDatavisible(boolean datavisible) {
		this.datavisible = datavisible;
	}

	public int getGradeid() {
		return this.gradeid;
	}

	public void setGradeid(int gradeid) {
		this.gradeid = gradeid;
	}

	public boolean getCansamegrade() {
		return this.cansamegrade;
	}

	public void setCansamegrade(boolean cansamegrade) {
		this.cansamegrade = cansamegrade;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * @return the sysRoleVisibles
	 */
	public Set<SysRoleVisible> getSysRoleVisibles() {
		return sysRoleVisibles;
	}


	/**
	 * @param sysRoleVisibles the sysRoleVisibles to set
	 */
	public void setSysRoleVisibles(Set<SysRoleVisible> sysRoleVisibles) {
		this.sysRoleVisibles = sysRoleVisibles;
	}


	/**
	 * @return the sysRights
	 */
	public Set<SysRight> getSysRights() {
		return sysRights;
	}


	/**
	 * @param sysRights the sysRights to set
	 */
	public void setSysRights(Set<SysRight> sysRights) {
		this.sysRights = sysRights;
	}


	/**
	 * @return the sysUsers
	 */
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}


	/**
	 * @param sysUsers the sysUsers to set
	 */
	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	

}