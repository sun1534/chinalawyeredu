package com.changpeng.models;

import java.sql.Timestamp;

/**
 * Lessonscore entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessonscore implements java.io.Serializable {

	// Fields

	private int lessonscoreid;
	private int lessonid;

	private int score;
	private int userid;
	private String username;
	private Timestamp scoretime;

	// Constructors

	/** default constructor */
	public Lessonscore() {
	}


	// Property accessors

	public int getLessonscoreid() {
		return this.lessonscoreid;
	}

	public void setLessonscoreid(int lessonscoreid) {
		this.lessonscoreid = lessonscoreid;
	}

	public int getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getScoretime() {
		return this.scoretime;
	}

	public void setScoretime(Timestamp scoretime) {
		this.scoretime = scoretime;
	}

}