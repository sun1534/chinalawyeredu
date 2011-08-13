/**
 * CaseTiaojieListAction.java
 */
package com.changpeng.lawcase.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflNode;

/**
 * 调解失败的时候，通知到诉讼承办人，这里只是得到诉讼承办人的userid
 * 
 * @author 华锋 Jan 10, 201010:22:29 PM
 * 
 */
public class CaseTiaojieErrorAction extends WorkFlowAction {

	private long userid;

	public long getUserid() {
		return this.userid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		this.userid = lawcase.getSusongworkid();
		String tonext="tiaojieerror";
		this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr("案件调解失败");
		TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
		if (nextnode == null) {
			this.message = "下一个节点为空,请联系管理员";
			return "message";
		}
		this.nodeid = nextnode.getNodeid();
//		this.message = "案件转为异常案件成功";
//		this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
//		return "flowmessage";

		return SUCCESS;
	}
}