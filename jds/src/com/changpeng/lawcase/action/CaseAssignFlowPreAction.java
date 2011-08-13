/**
 * 
 */
package com.changpeng.lawcase.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawOperlog;
import com.changpeng.lawcase.service.LawcaseService;
import com.sxit.common.action.AbstractAction;
import com.sxit.system.model.TsysRole;
import com.sxit.workflow.model.TwflDirection;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 在节点处理处，将案件提交给总经理进行分配,这里主要是得到下一个nodeid即可
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class CaseAssignFlowPreAction extends com.changpeng.lawcase.workflow.WorkFlowAction {

	private int assigntype;

	/**
	 * @return the assigntype
	 */
	public int getAssigntype() {
		return assigntype;
	}

	/**
	 * @param assigntype
	 *            the assigntype to set
	 */
	public void setAssigntype(int assigntype) {
		this.assigntype = assigntype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub
		if (caseid == 0) {
			this.message = "您没有选择任何案件,请返回";
			return "message";
		}

		// 页面判断，如果已经分配了，则不再显示那个选择框
		// 将状态变为1,也就是不再是新增的案件了，变为入口案件了,只是还未分配诉讼承办人

		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "preassigntime", dfyyyymmdd.format(new java.util.Date()));

		if (assigntype == 1){
			lawcase.setStatusid(1); // 已经是入口案件了
			lawcase.setSusongworkid(0L);
		}
		else if (assigntype == 2){
			lawcase.setStatusid(10);
			lawcase.setZhixingworkid(0L);
		}
		getSession().update(lawcase);

		TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), lawcase.getNodeid(),
				tonext);
		this.nodeid = nextnode.getNodeid();
		return SUCCESS;
	}
}