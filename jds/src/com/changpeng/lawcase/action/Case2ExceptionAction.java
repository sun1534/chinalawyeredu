/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 将案件转为异常案件
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class Case2ExceptionAction extends WorkFlowAction {
	private String why;

	/**
	 * @return the why
	 */
	public String getWhy() {
		return why;
	}

	/**
	 * @param why
	 *            the why to set
	 */
	public void setWhy(String why) {
		this.why = why;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		if (why == null || why.equals("")) {
			this.message = "请务必输入转为异常案件的理由";
			return "message";
		}

		if (caseid == 0) {
			this.message = "您没有选择任何案件,请返回";
			return "message";
		}

		System.out.println("gogogocaseid::::" + caseid);

		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		lawcase.setStatusid(40); // 40为异常案件
		lawcase.setIsqisu(0);// 设置为未起诉
		lawcase.setWhynot(why); // 转为异常案件的原因
		this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr(why);
		getSession().update(lawcase);

		this.saveOperlog("将案件转为异常案件:" + why, 8);

		TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
		if (nextnode == null) {
			this.message = "下一个节点为空,请联系管理员";
			return "message";
		}
		this.nodeid = nextnode.getNodeid();
		this.message = "案件转为异常案件成功";
		this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
		return "flowmessage";
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

}