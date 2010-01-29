package com.changpeng.models;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Lessons entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessons implements java.io.Serializable {

	// Fields

	private int lessonid;
	private int lessonidOfserver;
	private int lessonstyle;
	private String title;
	private int lessonstate;
	private String teachers;
	private int teachertype;
	private int lessontype;
	private String lessontypedesc;
	private Float xuefen=0f;
	private Timestamp lessondate;
	private Timestamp lessonend;
	private String lessonaddress;
	private String lessoncontent;
	private String onlinefile;
	private String fenshuoff="100";
	private String attach;
//	private String shared;
	private int provinceid;
	private int cityid;
	 //课程来源，课件平台的groupid为0。这里事务所的话，不考虑往上扔课程。即使往上扔课程的话，也记到市律协里面
	private int groupid;
	private boolean deleteflag;
	private String createuser;
	private int createuserid;
	private Timestamp createtime;
private Lawyerlessonxf yihuoxuefen;
	private Set lessonshareds = new HashSet(0);
	private int onlineType;
	
	// Constructors

	/**
	 * @return the onlineType
	 */
	public int getOnlineType() {
		return onlineType;
	}

	/**
	 * @param onlineType the onlineType to set
	 */
	public void setOnlineType(int onlineType) {
		this.onlineType = onlineType;
	}

	/**
	 * @return the lessonshareds
	 */
	public Set getLessonshareds() {
		return lessonshareds;
	}

	/**
	 * @param lessonshareds the lessonshareds to set
	 */
	public void setLessonshareds(Set lessonshareds) {
		this.lessonshareds = lessonshareds;
	}

	/** default constructor */
	public Lessons() {
	}




	// Property accessors

	public int getLessonid() {
		return this.lessonid;
	}

	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public int getLessonidOfserver() {
		return this.lessonidOfserver;
	}

	public void setLessonidOfserver(int lessonidOfserver) {
		this.lessonidOfserver = lessonidOfserver;
	}

	public int getLessonstyle() {
		return this.lessonstyle;
	}

	public void setLessonstyle(int lessonstyle) {
		this.lessonstyle = lessonstyle;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLessonstate() {
		return this.lessonstate;
	}

	public void setLessonstate(int lessonstate) {
		this.lessonstate = lessonstate;
	}

	public String getTeachers() {
		return this.teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public int getTeachertype() {
		return this.teachertype;
	}

	public void setTeachertype(int teachertype) {
		this.teachertype = teachertype;
	}

	public int getLessontype() {
		return this.lessontype;
	}

	public void setLessontype(int lessontype) {
		this.lessontype = lessontype;
	}

	public String getLessontypedesc() {
		return this.lessontypedesc;
	}

	public void setLessontypedesc(String lessontypedesc) {
		this.lessontypedesc = lessontypedesc;
	}

	public Float getXuefen() {
		return this.xuefen;
	}

	public void setXuefen(Float xuefen) {
		this.xuefen = xuefen;
	}

	public Timestamp getLessondate() {
		return this.lessondate;
	}

	public void setLessondate(Timestamp lessonTimestamp) {
		this.lessondate = lessonTimestamp;
	}

	public Timestamp getLessonend() {
		return this.lessonend;
	}

	public void setLessonend(Timestamp lessonend) {
		this.lessonend = lessonend;
	}

	public String getLessonaddress() {
		return this.lessonaddress;
	}

	public void setLessonaddress(String lessonaddress) {
		this.lessonaddress = lessonaddress;
	}

	public String getLessoncontent() {
		return this.lessoncontent;
	}

	public void setLessoncontent(String lessoncontent) {
		this.lessoncontent = lessoncontent;
	}

	public String getOnlinefile() {
		return this.onlinefile;
	}

	public void setOnlinefile(String onlinefile) {
		this.onlinefile = onlinefile;
	}

	public String getFenshuoff() {
		return this.fenshuoff;
	}

	public void setFenshuoff(String fenshuoff) {
		this.fenshuoff = fenshuoff;
	}

	public String getAttach() {
		return this.attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

//	public String getShared() {
//		return this.shared;
//	}
//
//	public void setShared(String shared) {
//		this.shared = shared;
//	}



	public boolean getDeleteflag() {
		return this.deleteflag;
	}

	public void setDeleteflag(boolean deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getCreateuser() {
		return this.createuser;
	}



	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
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
	 * @return the createuserid
	 */
	public int getCreateuserid() {
		return createuserid;
	}

	/**
	 * @param createuserid the createuserid to set
	 */
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	/**
	 * @param createuser the createuser to set
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	/**
	 * @return the yihuoxuefen
	 */
	public Lawyerlessonxf getYihuoxuefen() {
		return yihuoxuefen;
	}

	/**
	 * @param yihuoxuefen the yihuoxuefen to set
	 */
	public void setYihuoxuefen(Lawyerlessonxf yihuoxuefen) {
		this.yihuoxuefen = yihuoxuefen;
	}

//	public Set getLessonreplies() {
//		return this.lessonreplies;
//	}
//
//	public void setLessonreplies(Set lessonreplies) {
//		this.lessonreplies = lessonreplies;
//	}
//
//	public Set getLessonscores() {
//		return this.lessonscores;
//	}
//
//	public void setLessonscores(Set lessonscores) {
//		this.lessonscores = lessonscores;
//	}
//
//	public Set getLawyerlessonxfs() {
//		return this.lawyerlessonxfs;
//	}
//
//	public void setLawyerlessonxfs(Set lawyerlessonxfs) {
//		this.lawyerlessonxfs = lawyerlessonxfs;
//	}

}