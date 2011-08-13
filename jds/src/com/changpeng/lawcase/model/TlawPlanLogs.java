package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * 案件进度备注信息,主要是台帐录入的数据
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawPlanLogs implements java.io.Serializable {

	// Fields

	private Long planlogid;
	private Long caseid;
	private Long createuserid;
	private String planlogs;
	private int actionid;
	private Timestamp createtime;
	private String createusername;
	/**
	 * @return the planlogid
	 */
	public Long getPlanlogid() {
		return planlogid;
	}
	/**
	 * @param planlogid the planlogid to set
	 */
	public void setPlanlogid(Long planlogid) {
		this.planlogid = planlogid;
	}
	/**
	 * @return the caseid
	 */
	public Long getCaseid() {
		return caseid;
	}
	/**
	 * @param caseid the caseid to set
	 */
	public void setCaseid(Long caseid) {
		this.caseid = caseid;
	}
	/**
	 * @return the createuserid
	 */
	public Long getCreateuserid() {
		return createuserid;
	}
	/**
	 * @param createuserid the createuserid to set
	 */
	public void setCreateuserid(Long createuserid) {
		this.createuserid = createuserid;
	}
	/**
	 * @return the planlogs
	 */
	public String getPlanlogs() {
		return planlogs;
	}
	/**
	 * @param planlogs the planlogs to set
	 */
	public void setPlanlogs(String planlogs) {
		this.planlogs = planlogs;
	}
	/**
	 * @return the actionid
	 */
	public int getActionid() {
		return actionid;
	}
	/**
	 * @param actionid the actionid to set
	 */
	public void setActionid(int actionid) {
		this.actionid = actionid;
	}
	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the createusername
	 */
	public String getCreateusername() {
		return createusername;
	}
	/**
	 * @param createusername the createusername to set
	 */
	public void setCreateusername(String createusername) {
		this.createusername = createusername;
	}
	}