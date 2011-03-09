package com.uu800.admin.base.entity;

/**
 * TsysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Role implements java.io.Serializable {

	// Fields

	private long roleid;
	private String rolename;
	private long roletype;
	private boolean checked;     // 记录该权限是否与对应的菜单关联

	// Constructors

	/** default constructor */
	public Role() {
	}
	public Role(TsysRole role) {
		this.roleid=role.getRoleid();
		this.rolename=role.getRolename();
		this.roletype=role.getRoletype();
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public long getRoletype() {
		return roletype;
	}
	public void setRoletype(long roletype) {
		this.roletype = roletype;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}