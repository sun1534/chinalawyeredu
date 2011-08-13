/**
 * 
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawHistory;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.util.StringUtil;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflAction;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 台帐管理员实行最后的结案动作
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class CaseEndReallyAction extends WorkFlowAction {

	private String planlogs;
	private String oldplanlogs;
	
	/**
	 * @return the oldplanlogs
	 */
	public String getOldplanlogs() {
		return oldplanlogs;
	}

	/**
	 * @param oldplanlogs the oldplanlogs to set
	 */
	public void setOldplanlogs(String oldplanlogs) {
		this.oldplanlogs = oldplanlogs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
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

	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub
		Timestamp stamp = new java.sql.Timestamp(System.currentTimeMillis());
		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		lawcase.setStatusid(30);
		lawcase.setModifytime(stamp);
		lawcase.setPlanlogs(planlogs);
		getSession().update(lawcase);
		
		TlawHistory history = new TlawHistory();
		history.setActionid(actionid);
		if (actionid != 0) {
			TwflAction action = (TwflAction) getSession().get(TwflAction.class, actionid);
			history.setDomessage(action.getActionname());
		} else {
			history.setDomessage("提交");
		}
		super.saveOperlog("台帐最后结案:"+planlogs, 8);
		
		history.setDotime(stamp);
		history.setNodeid(lawcase.getNodeid());
		history.setDouserid(curuser.getUserid());
		history.setDousername(curuser.getUsername());
		history.setCaseid(caseid);
		history.setRemarks("案件最终结案:"+planlogs);
		getSession().save(history);
		
		
		this.message = "案件转化为出口案件成功,请返回查看";
		this.nextpage = "caseDoView.action?caseid=" + caseid;
		return SUCCESS;

	}
	
	public String input(){
		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		this.planlogs=lawcase.getPlanlogs();
		this.oldplanlogs=planlogs;
		return INPUT;
	}
}