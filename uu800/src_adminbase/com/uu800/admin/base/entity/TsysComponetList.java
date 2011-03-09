package com.uu800.admin.base.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * TsysRight entity. @author MyEclipse Persistence Tools
 */

public class TsysComponetList implements java.io.Serializable {


    // Fields    

     private String rightid;
     private String rightcode;
     private long grade;
     private String rightname;
 	private boolean checked;     // 记录该权限是否与对应的菜单关联
	private Set<TsysComponetList> childRights = new HashSet(0);
 
	/** default constructor */
	public TsysComponetList() {
	}
	public TsysComponetList(TsysComponetList right) {		
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
	public String getRightname() {
		return rightname;
	}
	public void setRightname(String rightname) {
		this.rightname = rightname;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Set<TsysComponetList> getChildRights() {
		return childRights;
	}
	public void setChildRights(Set<TsysComponetList> childRights) {
		this.childRights = childRights;
	}

}