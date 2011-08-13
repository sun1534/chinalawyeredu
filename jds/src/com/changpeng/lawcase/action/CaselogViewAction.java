/**
 * 
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawCaselog;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawLianinfo;

/**
 * 
 * 查看案件代理日志情况记录表
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaselogViewAction extends com.changpeng.lawcase.workflow.WorkFlowAction {

	
	
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
	
		
		this.lawcase=(TlawLawcase)getSession().get(TlawLawcase.class,caseid);
		this.loglist=getSession().createQuery("from TlawCaselog a where a.caseid="+caseid+" order by a.caselogid desc").list();
		this.lianinfo = (TlawLianinfo) getSession().get(TlawLianinfo.class, caseid);
		
		
		this.nextpage = "caseDoView.action?caseid="+caseid;

		return SUCCESS;
	}

	/**
	 * 案件日志列表,包括承办人写的日志以及调解日志
	 */
	private List loglist;
	public List getLoglist(){
		return this.loglist;
	}
	
	private TlawLianinfo lianinfo;

	public TlawLianinfo getLianinfo(){
		return this.lianinfo;
	}
	

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	


	

}
