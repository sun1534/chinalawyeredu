package com.changpeng.models;

import java.sql.Timestamp;

/**
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OfficeProperties implements java.io.Serializable {

	// Fields

	private int groupid;
	private String filename;
	private String photo;
	private String createusername;
	private Timestamp createtime;

	/**
	 * @return the groupid
	 */
	public int getGroupid() {
		return groupid;
	}
	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * @return the createusername
	 */
	public String getCreateusername() {
		return createusername;
	}
	/**
	 * @param createusername the createusername to set
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}
	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}



}