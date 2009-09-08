package com.changpeng.models;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * SysGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysGroup implements java.io.Serializable {

	// Fields

	private int groupid;
	private int parentid;
	private int grouplevel;
	private String groupenname;
	private String groupname;
	private String contacter;
	private String phone;
	private String fax;
	private boolean delflag;
	private int grouptype;
	private int directgroup;
	private String address;
	private String postcode;
	private String createuser;
	private Timestamp createtime;
	private String comments;
	private int createtype;
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
//	private Set<SysRight> sysRights = new HashSet<SysRight>(0);

	private SysUnionparams sysUnionparams;
	private String systemno;
	private int usercnts;

	// Constructors

	/**
	 * @return the usercnts
	 */
	public int getUsercnts() {
		return usercnts;
	}




	/**
	 * @param usercnts the usercnts to set
	 */
	public void setUsercnts(int usercnts) {
		this.usercnts = usercnts;
	}




	/** default constructor */
	public SysGroup() {
	}




	// Property accessors

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getParentid() {
		return this.parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public int getGrouplevel() {
		return this.grouplevel;
	}

	public void setGrouplevel(int grouplevel) {
		this.grouplevel = grouplevel;
	}

	public String getGroupenname() {
		return this.groupenname;
	}

	public void setGroupenname(String groupenname) {
		this.groupenname = groupenname;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getContacter() {
		return this.contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public boolean getDelflag() {
		return this.delflag;
	}

	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}

	public int getGrouptype() {
		return this.grouptype;
	}

	public void setGrouptype(int grouptype) {
		this.grouptype = grouptype;
	}

	public int getDirectgroup() {
		return this.directgroup;
	}

	public void setDirectgroup(int directgroup) {
		this.directgroup = directgroup;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
//
//	public int getProvinceid() {
//		return this.provinceid;
//	}
//
//	public void setProvinceid(int provinceid) {
//		this.provinceid = provinceid;
//	}
//
//	public int getCityid() {
//		return this.cityid;
//	}
//
//	public void setCityid(int cityid) {
//		this.cityid = cityid;
//	}
//
//	public int getDistrictid() {
//		return this.districtid;
//	}
//
//	public void setDistrictid(int districtid) {
//		this.districtid = districtid;
//	}

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

	public int getCreatetype() {
		return this.createtype;
	}

	public void setCreatetype(int createtype) {
		this.createtype = createtype;
	}
//	/**
//	 * @return the sysRights
//	 */
//	public Set<SysRight> getSysRights() {
//		return sysRights;
//	}
//	/**
//	 * @param sysRights the sysRights to set
//	 */
//	public void setSysRights(Set<SysRight> sysRights) {
//		this.sysRights = sysRights;
//	}




	/**
	 * @return the sysUnionparams
	 */
	public SysUnionparams getSysUnionparams() {
		return sysUnionparams;
	}




	/**
	 * @param sysUnionparams the sysUnionparams to set
	 */
	public void setSysUnionparams(SysUnionparams sysUnionparams) {
		this.sysUnionparams = sysUnionparams;
	}




	/**
	 * @param sysUsers the sysUsers to set
	 */
	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}




	/**
	 * @return the sysUsers
	 */
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}




	/**
	 * @return the systemno
	 */
	public String getSystemno() {
		return systemno;
	}




	/**
	 * @param systemno the systemno to set
	 */
	public void setSystemno(String systemno) {
		this.systemno = systemno;
	}




//	/**
//	 * @return the lessonshareds
//	 */
//	public Set getLessonshareds() {
//		return lessonshareds;
//	}
//
//	/**
//	 * @param lessonshareds the lessonshareds to set
//	 */
//	public void setLessonshareds(Set lessonshareds) {
//		this.lessonshareds = lessonshareds;
//	}


}