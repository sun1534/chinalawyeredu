
package com.changpeng.models;

// Generated 2008-5-4 12:08:05 by Hibernate Tools 3.2.0.CR1

import java.sql.Timestamp;

/**
 * Lxskrecs generated by hbm2java
 */
public class Lxskrecs implements java.io.Serializable {

	private int skrecid;
	private String kahao;
	private int lessonid;
	private String skdate;
	private String skmode;
	private String ischeck;
	private Timestamp uploadtime;
	//刷卡的时长
private float timelong;
private String remarks;
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

	public Lxskrecs() {
	}

	public Lxskrecs(String kahao, int lessonid, String skdate, String skmode, String ischeck, Timestamp uploadtime) {
		this.kahao = kahao;
		this.lessonid = lessonid;
		this.skdate = skdate;
		this.skmode = skmode;
		this.ischeck = ischeck;
		this.uploadtime = uploadtime;
	}

	public int getSkrecid() {
		return this.skrecid;
	}

	public void setSkrecid(int skrecid) {
		this.skrecid = skrecid;
	}

	public String getKahao() {
		return this.kahao;
	}

	public void setKahao(String kahao) {
		this.kahao = kahao;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public String getSkdate() {
		return this.skdate;
	}

	public void setSkdate(String skdate) {
		this.skdate = skdate;
	}

	public String getSkmode() {
		return this.skmode;
	}

	public void setSkmode(String skmode) {
		this.skmode = skmode;
	}

	public String getIscheck() {
		return this.ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public Timestamp getUploadtime() {
		return this.uploadtime;
	}

	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}

	/**
	 * @return the timelong
	 */
	public float getTimelong() {
		return timelong;
	}

	/**
	 * @param timelong the timelong to set
	 */
	public void setTimelong(float timelong) {
		this.timelong = timelong;
	}

}