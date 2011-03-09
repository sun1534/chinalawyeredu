package com.uu800.admin.base.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 用于编辑菜单权限时间保存菜单对应的菜单权限
 * 
 * @author MyEclipse Persistence Tools
 */

public class Right implements java.io.Serializable {

	// Fields

	private String rightid;
	private String rightcode;
	private long grade;
	private boolean checked;     // 记录该权限是否与对应的菜单关联
	private String rightname;
	private Set<Right> childRights = new HashSet(0);

	// Constructors

	/** default constructor */
	public Right() {
	}
	public Right(TsysRight right) {		
		this.rightid=right.getRightid();
		this.rightcode=right.getRightcode();
		this.grade=right.getGrade();
		this.rightname=right.getRightname();
	}

	public String getRightid() {
		return rightid;
	}

	public void setRightid(String rightid) {
		this.rightid = rightid;
	}

	public String getRightcode() {
		return rightcode;
	}

	public void setRightcode(String rightcode) {
		this.rightcode = rightcode;
	}

	public long getGrade() {
		return grade;
	}

	public void setGrade(long grade) {
		this.grade = grade;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getRightname() {
		return rightname;
	}

	public void setRightname(String rightname) {
		this.rightname = rightname;
	}

	public Set<Right> getChildRights() {
		return childRights;
	}

	public void setChildRights(Set<Right> childRights) {
		this.childRights = childRights;
	}

}