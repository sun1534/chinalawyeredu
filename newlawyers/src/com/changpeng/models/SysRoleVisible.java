package com.changpeng.models;

/**
 * SysRoleVisible entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRoleVisible implements java.io.Serializable {

	// Fields

	private int visibleid;
	private SysRole sysRole;
	private String thetable;
	private String thefield;
	private boolean recursion;

	// Constructors

	/** default constructor */
	public SysRoleVisible() {
	}

	/** minimal constructor */
	public SysRoleVisible(boolean recursion) {
		this.recursion = recursion;
	}



	// Property accessors

	public int getVisibleid() {
		return this.visibleid;
	}

	public void setVisibleid(int visibleid) {
		this.visibleid = visibleid;
	}

	public SysRole getSysRole() {
		return this.sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public String getThetable() {
		return this.thetable;
	}

	public void setThetable(String thetable) {
		this.thetable = thetable;
	}

	public String getThefield() {
		return this.thefield;
	}

	public void setThefield(String thefield) {
		this.thefield = thefield;
	}

	public boolean getRecursion() {
		return this.recursion;
	}

	public void setRecursion(boolean recursion) {
		this.recursion = recursion;
	}

}