/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawJiekuanren;
import com.changpeng.lawcase.model.TlawLawcase;

/**
 * 
 * 写案件日志
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaseViewAction extends com.changpeng.lawcase.workflow.WorkFlowAction  {

	private long caseid;
	private TlawLawcase lawcase;

	/**
	 * @return the lawcase
	 */
	public TlawLawcase getLawcase() {
		return lawcase;
	}

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		// ServletActionContext c=null;

		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		if (lawcase == null) {
			this.message = "案件信息不存在,请返回";
			return "message";
		}
		TlawJiekuanren jiekuanren = this.lawcase.getJiekuanren();
		if (jiekuanren == null) {

			this.message = "案件的贷款信息不存在,请返回";
			return "message";

		}

		return SUCCESS;
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

}
