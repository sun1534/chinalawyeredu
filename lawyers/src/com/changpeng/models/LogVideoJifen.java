package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Areas entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogVideoJifen implements java.io.Serializable {

	// Fields
	private int lessonid;
	private int lawyerid;
	private Float allminutes=0f;
	private Float lookedminutes=0f;
	private int jifenyear;
	private int nowyear;
	private int id;
	private int xfid;
	private int visitid;
	private float pxxf;
	private Timestamp jifentime;
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
	 * @return the xfid
	 */
	public int getXfid() {
		return xfid;
	}
	/**
	 * @param xfid the xfid to set
	 */
	public void setXfid(int xfid) {
		this.xfid = xfid;
	}
	/**
	 * @return the visitid
	 */
	public int getVisitid() {
		return visitid;
	}
	/**
	 * @param visitid the visitid to set
	 */
	public void setVisitid(int visitid) {
		this.visitid = visitid;
	}
	/**
	 * @return the jifentime
	 */
	public Timestamp getJifentime() {
		return jifentime;
	}
	/**
	 * @param jifentime the jifentime to set
	 */
	public void setJifentime(Timestamp jifentime) {
		this.jifentime = jifentime;
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
	 * @return the allminutes
	 */
	public Float getAllminutes() {
		return allminutes;
	}
	/**
	 * @param allminutes the allminutes to set
	 */
	public void setAllminutes(Float allminutes) {
		this.allminutes = allminutes;
	}
	/**
	 * @return the lookedminutes
	 */
	public Float getLookedminutes() {
		return lookedminutes;
	}
	/**
	 * @param lookedminutes the lookedminutes to set
	 */
	public void setLookedminutes(Float lookedminutes) {
		this.lookedminutes = lookedminutes;
	}
	/**
	 * @return the jifenyear
	 */
	public int getJifenyear() {
		return jifenyear;
	}
	/**
	 * @param jifenyear the jifenyear to set
	 */
	public void setJifenyear(int jifenyear) {
		this.jifenyear = jifenyear;
	}
	/**
	 * @return the nowyear
	 */
	public int getNowyear() {
		return nowyear;
	}
	/**
	 * @param nowyear the nowyear to set
	 */
	public void setNowyear(int nowyear) {
		this.nowyear = nowyear;
	}
	/**
	 * @return the pxxf
	 */
	public float getPxxf() {
		return pxxf;
	}
	/**
	 * @param pxxf the pxxf to set
	 */
	public void setPxxf(float pxxf) {
		this.pxxf = pxxf;
	}
	

}