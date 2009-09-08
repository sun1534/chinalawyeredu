package com.changpeng.models;

/**
 * SysGroupExcludeRights entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysGroupExcludeRights implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer groupid;
	private String rightcode;
	private Byte recursion;
	private String remarks;
	// Constructors

	/**
 * @return the remarks
 */
public String getRemarks() {
	return remarks;
}

/**
 * @param remarks the remarks to set
 */
public void setRemarks(String remarks) {
	this.remarks = remarks;
}

	/** default constructor */
	public SysGroupExcludeRights() {
	}

	/** minimal constructor */
	public SysGroupExcludeRights(Integer groupid, String rightcode) {
		this.groupid = groupid;
		this.rightcode = rightcode;
	}

	/** full constructor */
	public SysGroupExcludeRights(Integer groupid, String rightcode, Byte recursion) {
		this.groupid = groupid;
		this.rightcode = rightcode;
		this.recursion = recursion;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupid() {
		return this.groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getRightcode() {
		return this.rightcode;
	}

	public void setRightcode(String rightcode) {
		this.rightcode = rightcode;
	}

	public Byte getRecursion() {
		return this.recursion;
	}

	public void setRecursion(Byte recursion) {
		this.recursion = recursion;
	}

}