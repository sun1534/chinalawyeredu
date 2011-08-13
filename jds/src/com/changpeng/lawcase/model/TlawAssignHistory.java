package com.changpeng.lawcase.model;

import java.sql.Timestamp;

/**
 * 
 * 案件分配历史记录
 * 
 * TlawJiekuanren entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawAssignHistory implements java.io.Serializable {

	// Fields

	private int historyid;

	private TlawLawcase lawcase;

	private Timestamp assigntime;
	private long assigneduserid;
	private String assignedusername;
	/**
	 * 分配的诉讼承办人还是执行承办人
	 * 1是诉讼承办人2是执行承办人
	 */
	private int assigntype=1;

	private long createuserid;
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
	 * @return the historyid
	 */
	public int getHistoryid() {
		return historyid;
	}

	/**
	 * @param historyid
	 *            the historyid to set
	 */
	public void setHistoryid(int historyid) {
		this.historyid = historyid;
	}

	/**
	 * @return the lawcase
	 */
	public TlawLawcase getLawcase() {
		return lawcase;
	}

	/**
	 * @param lawcase
	 *            the lawcase to set
	 */
	public void setLawcase(TlawLawcase lawcase) {
		this.lawcase = lawcase;
	}

	/**
	 * @return the assigntime
	 */
	public Timestamp getAssigntime() {
		return assigntime;
	}

	/**
	 * @param assigntime
	 *            the assigntime to set
	 */
	public void setAssigntime(Timestamp assigntime) {
		this.assigntime = assigntime;
	}

	/**
	 * @return the assigneduserid
	 */
	public long getAssigneduserid() {
		return assigneduserid;
	}

	/**
	 * @param assigneduserid
	 *            the assigneduserid to set
	 */
	public void setAssigneduserid(long assigneduserid) {
		this.assigneduserid = assigneduserid;
	}

	/**
	 * @return the assignedusername
	 */
	public String getAssignedusername() {
		return assignedusername;
	}

	/**
	 * @param assignedusername
	 *            the assignedusername to set
	 */
	public void setAssignedusername(String assignedusername) {
		this.assignedusername = assignedusername;
	}

	/**
	 * @return the createuserid
	 */
	public long getCreateuserid() {
		return createuserid;
	}

	/**
	 * @param createuserid
	 *            the createuserid to set
	 */
	public void setCreateuserid(long createuserid) {
		this.createuserid = createuserid;
	}

	/**
	 * @return the assigntype
	 */
	public int getAssigntype() {
		return assigntype;
	}

	/**
	 * @param assigntype the assigntype to set
	 */
	public void setAssigntype(int assigntype) {
		this.assigntype = assigntype;
	}
}