package com.changpeng.models;

import java.util.Date;

/**
 * LogLessonListen entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LogLessonListen implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer lawyerid;
	private Integer lessonid;
	private Integer teacherid;
	private String title;
	private Date firsttime;

	// Constructors

	/** default constructor */
	public LogLessonListen() {
	}


	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLawyerid() {
		return this.lawyerid;
	}

	public void setLawyerid(Integer lawyerid) {
		this.lawyerid = lawyerid;
	}

	public Integer getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(Integer lessonid) {
		this.lessonid = lessonid;
	}

	public Integer getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}

	public Date getFirsttime() {
		return this.firsttime;
	}

	public void setFirsttime(Date firsttime) {
		this.firsttime = firsttime;
	}

	
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		//return title;
		System.out.println("AAAAAAAAAA");
		if(this.title.length()>15){
			
			return this.title.substring(0,15)+"...";
		}else{
			return title;
		}
	}
	
	public String getTitleTrim(){
		if(this.title.length()>15){
			
			return this.title.substring(0,15)+"...";
		}else{
			return title;
		}
			
			
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}