package com.changpeng.models;

import java.sql.Timestamp;
import java.text.DateFormat;

/**
 * Lessons entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Lessons implements java.io.Serializable {

	// Fields
private int isshare;
	private int lessonid;
	private int lessonidOfserver;
	private int lessonstyle;
	private String title;
	private int lessonstate;
	private String teachers;
	private int teachertype;
	private int lessontype;
	private String lessontypedesc;
	private Float xuefen = 0f;
	private Timestamp lessondate;
	private Timestamp lessonend;
	private String lessonaddress;
	private String lessoncontent;
	private String onlinefile;
	private String fenshuoff = "100";
	private String attach;
	// private String shared;
	private int provinceid;
	private int cityid;
	// 课程来源，课件平台的groupid为0。这里事务所的话，不考虑往上扔课程。即使往上扔课程的话，也记到市律协里面
	private int groupid;
	private boolean deleteflag;
	private String createuser;
	private int createuserid;
	private Timestamp createtime;
	private BasicLawyerlessonxf yihuoxuefen;
//	private Set lessonshareds = new HashSet(0);
	private int onlineType;
	private static final DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 较好，好，差，非常差
	 */
	private int audioQuality;
	private int videoQuality;
	/**
	 * 单位为元
	 */
	private Float price=0f;
	private int teacherid;
	
	private int istuijian;
	
	private String tuijianContent;
	
	private String pic;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTuijianContent() {
		return tuijianContent;
	}
	
	public String getTuijianContentTrim() {
		if(this.tuijianContent!=null && this.tuijianContent!=""){
			if(this.tuijianContent.length()>10)
				return this.tuijianContent.substring(0,10)+"...";
				return tuijianContent;
		}
		return tuijianContent;
		
	}

	public void setTuijianContent(String tuijianContent) {
		this.tuijianContent = tuijianContent;
	}
	
	public int getIstuijian() {
		return istuijian;
	}
	public void setIstuijian(int istuijian) {
		this.istuijian = istuijian;
	}
	/**
	 * @return the price
	 */
	public Float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Float price) {
		this.price = price;
	}
	/**
	 * @return the teacherid
	 */
	public int getTeacherid() {
		return teacherid;
	}
	/**
	 * @param teacherid the teacherid to set
	 */
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	/**
	 * @return the audioQuality
	 */
	public int getAudioQuality() {
		return audioQuality;
	}
	public String getAudioQualityStr() {
		return com.changpeng.lessons.util.CommonDatas.QUALITIES.get(audioQuality)==null?"未知":com.changpeng.lessons.util.CommonDatas.QUALITIES.get(audioQuality);
	}public String getVideoQualityStr() {
		return com.changpeng.lessons.util.CommonDatas.QUALITIES.get(videoQuality)==null?"未知":com.changpeng.lessons.util.CommonDatas.QUALITIES.get(videoQuality);
	}
	/**
	 * @param audioQuality the audioQuality to set
	 */
	public void setAudioQuality(int audioQuality) {
		this.audioQuality = audioQuality;
	}

	/**
	 * @return the videoQuality
	 */
	public int getVideoQuality() {
		return videoQuality;
	}

	/**
	 * @param videoQuality the videoQuality to set
	 */
	public void setVideoQuality(int videoQuality) {
		this.videoQuality = videoQuality;
	}
	
	

	public String getLessondatestr(){
		if(lessondate!=null)
		return df.format(lessondate);
		return df.format(new java.util.Date());
	}

	// Constructors

	/**
	 * @return the onlineType
	 */
	public int getOnlineType() {
		return onlineType;
	}

	/**
	 * @param onlineType
	 *            the onlineType to set
	 */
	public void setOnlineType(int onlineType) {
		this.onlineType = onlineType;
	}

	/**
	 * @return the lessonshareds
	 */
//	public Set getLessonshareds() {
//		return lessonshareds;
//	}

	/**
	 * @param lessonshareds
	 *            the lessonshareds to set
	 */
//	public void setLessonshareds(Set lessonshareds) {
//		this.lessonshareds = lessonshareds;
//	}

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
	
	public String getTitleTrim(){
		if(this.title.length()>20)
			return this.title.substring(0,19)+"...";
			return title;
	}
	
	public String getTitleTrim1(){
		if(this.title.length()>10)
			return this.title.substring(0,10)+"...";
			return title;
	}
	
	public String getTitleTrim2(){
		if(this.title.length()>15)
			return this.title.substring(0,14)+"...";
			return title;
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

	public String getLessoncontentTrim() {
		if(this.lessoncontent.length()>30)
			return this.lessoncontent.substring(0,30)+"...";
			return lessoncontent;
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

	// public String getShared() {
	// return this.shared;
	// }
	//
	// public void setShared(String shared) {
	// this.shared = shared;
	// }

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
	 * @param provinceid
	 *            the provinceid to set
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
	 * @param cityid
	 *            the cityid to set
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
	 * @param groupid
	 *            the groupid to set
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
	 * @param createuserid
	 *            the createuserid to set
	 */
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}

	/**
	 * @param createuser
	 *            the createuser to set
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	/**
	 * @return the yihuoxuefen
	 */
	public BasicLawyerlessonxf getYihuoxuefen() {
		return yihuoxuefen;
	}

	/**
	 * @param yihuoxuefen
	 *            the yihuoxuefen to set
	 */
	public void setYihuoxuefen(BasicLawyerlessonxf yihuoxuefen) {
		this.yihuoxuefen = yihuoxuefen;
	}
	/**
	 * @return the isshare
	 */
	public int getIsshare() {
		return isshare;
	}
	/**
	 * @param isshare the isshare to set
	 */
	public void setIsshare(int isshare) {
		this.isshare = isshare;
	}

	// public Set getLessonreplies() {
	// return this.lessonreplies;
	// }
	//
	// public void setLessonreplies(Set lessonreplies) {
	// this.lessonreplies = lessonreplies;
	// }
	//
	// public Set getLessonscores() {
	// return this.lessonscores;
	// }
	//
	// public void setLessonscores(Set lessonscores) {
	// this.lessonscores = lessonscores;
	// }
	//
	// public Set getLawyerlessonxfs() {
	// return this.lawyerlessonxfs;
	// }
	//
	// public void setLawyerlessonxfs(Set lawyerlessonxfs) {
	// this.lawyerlessonxfs = lawyerlessonxfs;
	// }

}