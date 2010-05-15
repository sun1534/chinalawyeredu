package com.changpeng.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Jifenbudeng entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class JifenbudengApply implements java.io.Serializable {

	// Fields

	private int budengid;
	private int lessonid;
	private String title;
	private Date budengdate;
	private String lawyerno;
	private int lawyerid;
	private String lawyername;

	private Float xuefen;
	private int provinceid;
	private int cityid;
	private int officeid;

	private int applyuserid;
	private String applyuser;
	private Timestamp applytime;
	private String applyreason;

	private int theyear;
	private boolean islocal;

	private int confirmuserid;
	private String confirmuser;
	private Timestamp confirmtime;
	private int status;
	private String confirmcontent;
	private String remarks;

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the budengid
	 */
	public int getBudengid() {
		return budengid;
	}

	/**
	 * @param budengid
	 *            the budengid to set
	 */
	public void setBudengid(int budengid) {
		this.budengid = budengid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the budengdate
	 */
	public Date getBudengdate() {
		return budengdate;
	}

	/**
	 * @param budengdate
	 *            the budengdate to set
	 */
	public void setBudengdate(Date budengdate) {
		this.budengdate = budengdate;
	}

	/**
	 * @return the lawyerno
	 */
	public String getLawyerno() {
		return lawyerno;
	}

	/**
	 * @param lawyerno
	 *            the lawyerno to set
	 */
	public void setLawyerno(String lawyerno) {
		this.lawyerno = lawyerno;
	}

	/**
	 * @return the lawyerid
	 */
	public int getLawyerid() {
		return lawyerid;
	}

	/**
	 * @param lawyerid
	 *            the lawyerid to set
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
	 * @param lawyername
	 *            the lawyername to set
	 */
	public void setLawyername(String lawyername) {
		this.lawyername = lawyername;
	}

	/**
	 * @return the xuefen
	 */
	public Float getXuefen() {
		return xuefen;
	}

	/**
	 * @param xuefen
	 *            the xuefen to set
	 */
	public void setXuefen(Float xuefen) {
		this.xuefen = xuefen;
	}

	/**
	 * @return the applyuserid
	 */
	public int getApplyuserid() {
		return applyuserid;
	}

	/**
	 * @param applyuserid
	 *            the applyuserid to set
	 */
	public void setApplyuserid(int applyuserid) {
		this.applyuserid = applyuserid;
	}

	/**
	 * @return the applyuser
	 */
	public String getApplyuser() {
		return applyuser;
	}

	/**
	 * @param applyuser
	 *            the applyuser to set
	 */
	public void setApplyuser(String applyuser) {
		this.applyuser = applyuser;
	}

	/**
	 * @return the applytime
	 */
	public Timestamp getApplytime() {
		return applytime;
	}

	/**
	 * @param applytime
	 *            the applytime to set
	 */
	public void setApplytime(Timestamp applytime) {
		this.applytime = applytime;
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
	 * @return the officeid
	 */
	public int getOfficeid() {
		return officeid;
	}

	/**
	 * @param officeid
	 *            the officeid to set
	 */
	public void setOfficeid(int officeid) {
		this.officeid = officeid;
	}

	/**
	 * @return the theyear
	 */
	public int getTheyear() {
		return theyear;
	}

	/**
	 * @param theyear
	 *            the theyear to set
	 */
	public void setTheyear(int theyear) {
		this.theyear = theyear;
	}

	/**
	 * @return the islocal
	 */
	public boolean isIslocal() {
		return islocal;
	}

	/**
	 * @param islocal
	 *            the islocal to set
	 */
	public void setIslocal(boolean islocal) {
		this.islocal = islocal;
	}

	/**
	 * @return the confirmuserid
	 */
	public int getConfirmuserid() {
		return confirmuserid;
	}

	/**
	 * @param confirmuserid
	 *            the confirmuserid to set
	 */
	public void setConfirmuserid(int confirmuserid) {
		this.confirmuserid = confirmuserid;
	}

	/**
	 * @return the confirmuser
	 */
	public String getConfirmuser() {
		return confirmuser;
	}

	/**
	 * @param confirmuser
	 *            the confirmuser to set
	 */
	public void setConfirmuser(String confirmuser) {
		this.confirmuser = confirmuser;
	}

	/**
	 * @return the confirmtime
	 */
	public Timestamp getConfirmtime() {
		return confirmtime;
	}

	/**
	 * @param confirmtime
	 *            the confirmtime to set
	 */
	public void setConfirmtime(Timestamp confirmtime) {
		this.confirmtime = confirmtime;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the confirmcontent
	 */
	public String getConfirmcontent() {
		return confirmcontent;
	}

	/**
	 * @param confirmcontent
	 *            the confirmcontent to set
	 */
	public void setConfirmcontent(String confirmcontent) {
		this.confirmcontent = confirmcontent;
	}

	/**
	 * @return the lessonid
	 */
	public int getLessonid() {
		return lessonid;
	}

	/**
	 * @param lessonid
	 *            the lessonid to set
	 */
	public void setLessonid(int lessonid) {
		this.lessonid = lessonid;
	}

	public String getIslocalstr() {
		return islocal ? "是" : "否";
	}

	public String getStatusStr() {
		if (status == 0)
			return "暂未处理";
		else if (status == 1)
			return "申请通过";
		else if (status == 2)
			return "申请未通过:" + confirmcontent;
		else if (status == 3)
			return "已取消申请";
		else
			return "其他";
	}

	/**
	 * @return the applyReason
	 */
	public String getApplyreason() {
		return applyreason;
	}

	/**
	 * @param applyReason
	 *            the applyReason to set
	 */
	public void setApplyreason(String applyReason) {
		this.applyreason = applyReason;
	}
}