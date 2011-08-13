/**
 * 
 */
package com.changpeng.lawcase.workflow;

import com.changpeng.lawcase.service.LawcaseLogicService;
import com.sxit.common.action.AbstractAction;

/**
 * @author 华锋 Dec 26, 2009-11:02:23 PM
 * 
 */
public abstract class WorkFlowAction extends AbstractAction {

	protected java.text.DateFormat dfyyyymmdd = new java.text.SimpleDateFormat("yyyy-MM-dd");
	public int nodeid;
	public int actionid;
	public long caseid;
	public String remarks;
	public String tonext;
	protected String btnvalue;

	public String getBtnvalue() {
		return this.btnvalue;
	}

	/**
	 * @return the tonext
	 */
	public String getTonext() {
		return tonext;
	}

	/**
	 * @param tonext
	 *            the tonext to set
	 */
	public void setTonext(String tonext) {
		this.tonext = tonext;
	}

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
	 * @return the nodeid
	 */
	public int getNodeid() {
		return nodeid;
	}

	/**
	 * @param nodeid
	 *            the nodeid to set
	 */
	public void setNodeid(int nodeid) {
		this.nodeid = nodeid;
	}

	/**
	 * @return the caseid
	 */
	public long getCaseid() {
		return caseid;
	}

	/**
	 * @param caseid
	 *            the caseid to set
	 */
	public void setCaseid(long caseid) {
		this.caseid = caseid;
	}

	/**
	 * @return the actionid
	 */
	public int getActionid() {
		return actionid;
	}

	/**
	 * @param actionid
	 *            the actionid to set
	 */
	public void setActionid(int actionid) {
		this.actionid = actionid;
	}

	protected void saveOperlog(String logcontent, int opertype) {
		LawcaseLogicService.saveOperlog(getSession(), caseid, actionid, logcontent, opertype);
	}

	// @Override
	// protected String go() throws JDBCException, HibernateException {
	//		
	//		
	// return workflow();
	// }

	// protected abstract String workflow() throws JDBCException,
	// HibernateException;
}
