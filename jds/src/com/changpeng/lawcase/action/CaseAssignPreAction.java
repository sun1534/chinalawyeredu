/**
 * 
 */
package com.changpeng.lawcase.action;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawOperlog;
import com.changpeng.lawcase.service.LawcaseService;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.TsysRole;

/**
 * 
 * 将多个案件初始分配，即点案件的“提交分配”按钮，之后案件的nodeid改变并hotman换为事业部总经理处理（也可以不）
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class CaseAssignPreAction extends com.changpeng.lawcase.workflow.WorkFlowAction  {

	private long[] check;
	/**
	 * @return the caseid
	 */
	public long[] getCheck() {
		return check;
	}

	/**
	 * @param caseid
	 *            the caseid to set
	 */
	public void setCheck(long[] check) {
		this.check = check;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub
		if (check == null || check.length == 0) {
			this.message = "您没有选择任何案件,请返回";
			return "message";
		}
		
		int i=0;
		// 页面判断，如果已经分配了，则不再显示那个选择框
		for (long _caseid : check) {
		
			i+=getSession().createSQLQuery("update tlaw_lawcase a set a.nodeid=21002,a.statusid=0 where a.caseid=" + _caseid).executeUpdate();
		
//			TlawOperlog log=new TlawOperlog();
//			log.setCaseid((long)caseid);
			String content=this.curuser.getUsername()+"提交案件给总经理分配";
//			log.setOpertype(21002);		
			LawcaseService.saveOperlog(getSession(), caseid, content, 21002, this.curuser);
//			LawcaseService.saveOperlog(getSession(), log, this.curuser);
		}
		this.message = "提交"+i+"个案件到事业部总经理进行分配处理成功";
		return SUCCESS;
	}
}