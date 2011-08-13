/**
 * 
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;
import java.text.DateFormat;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawCaselog;
import com.changpeng.lawcase.model.TlawLawcase;

/**
 * 
 * 写案件日志
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaselogWriteAction extends com.changpeng.lawcase.workflow.WorkFlowAction {

	private static final DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
	private TlawCaselog log;

	public TlawCaselog getLog() {
		if (log == null)
			log = (TlawCaselog) get("log");
		return log;
	}

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		// ServletActionContext c=null;
		Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		if (log.getLogtime() == null || log.getLogtime().equals(""))
			log.setLogtime(df.format(timestamp));
		log.setCaseid(caseid);
		String logcontent = "";
		int logtype = 1;
		if (!isedit) {
			log.setCreatetime(timestamp);
			log.setCreateuserid(this.curuser.getUserid());
			log.setCreateusername(this.curuser.getUsername());
			log.setLogtype(logtype);
			getSession().save(log);
			if (logtype == 0) {
				this.actionid=210015;
				logcontent = "承办人记录代理日志";
				this.message = "新增诉讼案件日志成功";
			} else {
				
				com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "tiaojiedate", log.getLogtime());

				
				this.actionid=210015;
				logcontent = "调解人记录调解日志";
				this.message = "新增案件调解日志成功";
			}
		} else {
			logtype = 2;
			getSession().update(log);
			if (logtype == 0) {
				logcontent = "承办人修改代理日志:" + log.getCaselogid();
				this.message = "诉讼案件日志信息修改成功";
			} else {
				this.actionid=220001;
				logcontent = "调解人修改调解日志:" + log.getCaselogid();
				this.message = "案件调解日志信息修改成功";
			}
		}
		this.nextpage = "caseDoView.action?caseid=" + caseid;
		super.saveOperlog(logcontent, 1);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		log = (TlawCaselog) getSession().get(TlawCaselog.class, caselogid);
		if (log == null) {
			isedit = false;
			log = new TlawCaselog();
			log.setLogtime(df.format(timestamp));
		} else {
			isedit = true;
		}
		set("log", log);

		return INPUT;
	}

	private int logtype;
//	private long caseid;

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

//	/**
//	 * @return the caseid
//	 */
//	public long getCaseid() {
//		return caseid;
//	}
//
//	/**
//	 * @param caseid
//	 *            the caseid to set
//	 */
//	public void setCaseid(long caseid) {
//		this.caseid = caseid;
//	}

	private boolean isedit;

	public boolean getIsedit(boolean isedit) {
		return isedit;
	}

	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
	}

	private long caselogid;

	/**
	 * @return the caselogid
	 */
	public long getCaselogid() {
		return caselogid;
	}

	/**
	 * @param caselogid
	 *            the caselogid to set
	 */
	public void setCaselogid(long caselogid) {
		this.caselogid = caselogid;
	}

	/**
	 * @return the logtype
	 */
	public int getLogtype() {
		return logtype;
	}

	/**
	 * @param logtype
	 *            the logtype to set
	 */
	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}

}
