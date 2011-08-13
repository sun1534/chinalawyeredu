package com.changpeng.lawcase.model;

import java.sql.Timestamp;

import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 整个案件的处理流程
 * 
 * TlawJiekuanren entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TlawHistory implements java.io.Serializable {

	// Fields

	private int historyid;

//	private TlawLawcase lawcase;

	private long caseid;
	private int nodeid;
//	private TwflNode node;
	private int actionid;
//	private com.sxit.workflow.model.TwflAction action;

	private Timestamp dotime;
	private long douserid;
	private String dousername;
	private String domessage;
	private String remarks;
	/**
	 * @return the historyid
	 */
	public int getHistoryid() {
		return historyid;
	}
	/**
	 * @param historyid the historyid to set
	 */
	public void setHistoryid(int historyid) {
		this.historyid = historyid;
	}
//	/**
//	 * @return the lawcase
//	 */
//	public TlawLawcase getLawcase() {
//		return lawcase;
//	}
//	/**
//	 * @param lawcase the lawcase to set
//	 */
//	public void setLawcase(TlawLawcase lawcase) {
//		this.lawcase = lawcase;
//	}
	/**
	 * @return the nodeid
	 */
	public int getNodeid() {
		return nodeid;
	}
	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}
//	/**
//	 * @return the node
//	 */
//	public TwflNode getNode() {
//		return node;
//	}
//	/**
//	 * @param node the node to set
//	 */
//	public void setNode(TwflNode node) {
//		this.node = node;
//	}
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
	 * @return the action
	 */
//	public com.sxit.workflow.model.TwflAction getAction() {
//		return action;
//	}
//	/**
//	 * @param action the action to set
//	 */
//	public void setAction(com.sxit.workflow.model.TwflAction action) {
//		this.action = action;
//	}
	/**
	 * @return the dotime
	 */
	public Timestamp getDotime() {
		return dotime;
	}
	/**
	 * @param dotime the dotime to set
	 */
	public void setDotime(Timestamp dotime) {
		this.dotime = dotime;
	}
	/**
	 * @return the douserid
	 */
	public long getDouserid() {
		return douserid;
	}
	/**
	 * @param douserid the douserid to set
	 */
	public void setDouserid(long douserid) {
		this.douserid = douserid;
	}
	/**
	 * @return the dousername
	 */
	public String getDousername() {
		return dousername;
	}
	/**
	 * @param dousername the dousername to set
	 */
	public void setDousername(String dousername) {
		this.dousername = dousername;
	}
	/**
	 * @return the domessage
	 */
	public String getDomessage() {
		return domessage;
	}
	/**
	 * @param domessage the domessage to set
	 */
	public void setDomessage(String domessage) {
		this.domessage = domessage;
	}
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
	/**
	 * @return the caseid
	 */
	public long getCaseid() {
		return caseid;
	}
	/**
	 * @param caseid the caseid to set
	 */
	public void setCaseid(long caseid) {
		this.caseid = caseid;
	}

}