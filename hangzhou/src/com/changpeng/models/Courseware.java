package com.changpeng.models;

import java.util.Date;

/**
 * Courseware entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Courseware implements java.io.Serializable {

	// Fields

	private int wareid;
	private Coursetype coursetype;
	private String warename;
	private String warefile;
	private Float waretime;
	private Float xuefen;
	private Boolean isupload;
	private String createuser;
	private Date createtime;
	private int readcount;

	// Constructors

	/** default constructor */
	public Courseware() {
	}

	/** minimal constructor */
	public Courseware(Coursetype coursetype, String warename, String warefile,
			Float waretime, Float xuefen, int readcount) {
		this.coursetype = coursetype;
		this.warename = warename;
		this.warefile = warefile;
		this.waretime = waretime;
		this.xuefen = xuefen;
		this.readcount = readcount;
	}

	/** full constructor */
	public Courseware(Coursetype coursetype, String warename, String warefile,
			Float waretime, Float xuefen, Boolean isupload, String createuser,
			Date createtime, int readcount) {
		this.coursetype = coursetype;
		this.warename = warename;
		this.warefile = warefile;
		this.waretime = waretime;
		this.xuefen = xuefen;
		this.isupload = isupload;
		this.createuser = createuser;
		this.createtime = createtime;
		this.readcount = readcount;
	}

	// Property accessors

	public int getWareid() {
		return this.wareid;
	}

	public void setWareid(int wareid) {
		this.wareid = wareid;
	}

	public Coursetype getCoursetype() {
		return this.coursetype;
	}

	public void setCoursetype(Coursetype coursetype) {
		this.coursetype = coursetype;
	}

	public String getWarename() {
		return this.warename;
	}

	public void setWarename(String warename) {
		this.warename = warename;
	}

	public String getWarefile() {
		return this.warefile;
	}

	public void setWarefile(String warefile) {
		this.warefile = warefile;
	}

	public Float getWaretime() {
		return this.waretime;
	}

	public void setWaretime(Float waretime) {
		this.waretime = waretime;
	}

	public Float getXuefen() {
		return this.xuefen;
	}

	public void setXuefen(Float xuefen) {
		this.xuefen = xuefen;
	}

	public Boolean getIsupload() {
		return this.isupload;
	}

	public void setIsupload(Boolean isupload) {
		this.isupload = isupload;
	}

	public String getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getReadcount() {
		return this.readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	
	private Lawyerlessonxf userlessonxf;

	/**
	 * @return the userlessonxf
	 */
	public Lawyerlessonxf getUserlessonxf() {
		return userlessonxf;
	}

	/**
	 * @param userlessonxf the userlessonxf to set
	 */
	public void setUserlessonxf(Lawyerlessonxf userlessonxf) {
		this.userlessonxf = userlessonxf;
	}

}