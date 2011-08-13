/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.workflow.WorkFlowAction;

/**
 * 
 * 台帐管理员根据调解人输入的案件调解信息录入案件进度备注
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class CasePlanlogWriteAction extends WorkFlowAction {
	private String planlog;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		if (planlog == null || planlog.equals("")) {
			this.message = "您没有输入案件进度备注信息";
			return "message";
		}

		if (caseid == 0) {
			this.message = "您没有选择任何案件,请返回";
			return "message";
		}

		System.out.println("planlog::::" + planlog);

		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		lawcase.setPlanlogs(planlog);
		getSession().update(lawcase);

		this.actionid = 220002;// 对应为记录案件进度备注的actionid;
		this.saveOperlog("记录案件进度备注:" + planlog, 1);

		this.message = "案件进度备注信息记录成功";
		this.nextpage = "caselogList.action?logtype=1&pagenumber=" + pagenumber + "&caseid=" + caseid + "&jilubeizhu=1";
		return SUCCESS;

	}

	/**
	 * @return the planlog
	 */
	public String getPlanlog() {
		return planlog;
	}

	/**
	 * @param planlog
	 *            the planlog to set
	 */
	public void setPlanlog(String planlog) {
		this.planlog = planlog;
	}

}