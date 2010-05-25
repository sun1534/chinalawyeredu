package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Areas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogVideoLook implements java.io.Serializable {

	// Fields

	private int id;
	private int lawyerid;
	private int provinceid;
	private int cityid;
	private int officeid;
	private int lessonid;
	private Timestamp intime;
	private Timestamp outtime;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}
	/**
	 * @param lawyerid the lawyerid to set
	 */
	public void setLawyerid(int lawyerid) {
		this.lawyerid = lawyerid;
	}
	/**
	 * @return the provinceid
	 */
	public int getProvinceid() {
		return provinceid;
	}
	/**
	 * @param provinceid the provinceid to set
	 */
	public void setProvinceid(int provinceid) {
		this.provinceid = provinceid;
	}
	/**
	 * @return the cityid
	 */
	public int getCityid() {
		return cityid;
	}
	/**
	 * @param cityid the cityid to set
	 */
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	/**
	 * @return the officeid
	 */
	public int getOfficeid() {
		return officeid;
	}
	/**
	 * @param officeid the officeid to set
	 */
	public void setOfficeid(int officeid) {
		this.officeid = officeid;
	}
	/**
	 * @return the intime
	 */
	public Timestamp getIntime() {
		return intime;
	}
	/**
	 * @param intime the intime to set
	 */
	public void setIntime(Timestamp intime) {
		this.intime = intime;
	}
	/**
	 * @return the outtime
	 */
	public Timestamp getOuttime() {
		return outtime;
	}
	/**
	 * @param outtime the outtime to set
	 */
	public void setOuttime(Timestamp outtime) {
		this.outtime = outtime;
	}
	/**
	 * @return the lessonid
	 */
	public int getLessonid() {
		return lessonid;
	}
	/**
	 * @param lessonid the lessonid to set
	 */
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

}