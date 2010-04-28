package com.changpeng.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Lawyerlessonxf entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BasicLawyerlessonxf implements java.io.Serializable {

	// Fields

	protected int xfid;
	protected int lessonid;
	protected String title;
	protected int lawyerid;
	protected String lawyername;

	protected int learnmode;
	protected Date pxdate;
	protected Float pxreqminutes=0f;
	protected Float pxminutes=0f;
	protected Float pxxf=0f;
	protected String remarks;
	protected Timestamp lastupdate;
	protected boolean isfull;
	protected int provinceid;
	protected int cityid;
	protected int officeid;

	protected int theyear;
	protected int islastyear;
	// Constructors

	/**
	 * @return the theyear
	 */
	public int getTheyear() {
		return theyear;
	}



	/**
	 * @param theyear the theyear to set
	 */
	public void setTheyear(int theyear) {
		this.theyear = theyear;
	}



	/**
	 * @return the islastyear
	 */
	public int getIslastyear() {
		return islastyear;
	}



	/**
	 * @param islastyear the islastyear to set
	 */
	public void setIslastyear(int islastyear) {
		this.islastyear = islastyear;
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



	/** default constructor */
	public BasicLawyerlessonxf() {
	}



	// Property accessors

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
	 * @return the lawyername
	 */
	public String getLawyername() {
		return lawyername;
	}



	/**
	 * @param lawyername the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}



	public int getXfid() {
		return this.xfid;
	}

	public void setXfid(int xfid) {
		this.xfid = xfid;
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



	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLearnmode() {
		return this.learnmode;
	}

	public void setLearnmode(int learnmode) {
		this.learnmode = learnmode;
	}

	

	public Float getPxreqminutes() {
		return this.pxreqminutes;
	}

	public void setPxreqminutes(Float pxreqminutes) {
		this.pxreqminutes = pxreqminutes;
	}

	public Float getPxminutes() {
		return this.pxminutes;
	}

	public void setPxminutes(Float pxminutes) {
		this.pxminutes = pxminutes;
	}

	public Float getPxxf() {
		return Float.parseFloat(com.changpeng.jifen.util.NumberUtil.toMoney(this.pxxf+""));
	}

	public void setPxxf(Float pxxf) {
		this.pxxf = pxxf;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	/**
	 * @return the pxdate
	 */
	public Date getPxdate() {
		return pxdate;
	}

	/**
	 * @param pxdate the pxdate to set
	 */
	public void setPxdate(Date pxdate) {
		this.pxdate = pxdate;
	}

	



	/**
	 * @return the lastupdate
	 */
	public Timestamp getLastupdate() {
		return lastupdate;
	}



	/**
	 * @param lastupdate the lastupdate to set
	 */
	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}



	/**
	 * @return the isfull
	 */
	public boolean getIsfull() {
		return isfull;
	}



	/**
	 * @param isfull the isfull to set
	 */
	public void setIsfull(boolean isfull) {
		this.isfull = isfull;
	}



	
}