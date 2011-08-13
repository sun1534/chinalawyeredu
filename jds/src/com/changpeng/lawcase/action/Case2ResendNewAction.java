/**
 * 
 */
package com.changpeng.lawcase.action;

import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflDirection;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 重新将案件转到入口案件
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class Case2ResendNewAction extends WorkFlowAction {
	private DateFormat df = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

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
			this.message = "请务必输入重新导入到入口案件的理由";
			return "message";
		}

		if (caseid == 0) {

			this.message = "您没有选择任何案件,请返回";
			return "message";
		}

		System.out.println("gogogocaseid::::" + caseid);

		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class,
				caseid);
		lawcase.setStatusid(1); // 1为入口案件
		lawcase.setWhynot(lawcase.getWhynot() + ",由"
				+ this.curuser.getUsername() + "在" + df.format(new Date())
				+ "重新转到入口案件,理由:" + why); // 转为异常案件的原因

		this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr(why);

		getSession().update(lawcase);
		this.saveOperlog("将案件重新立案比提交总经理分配:"+why, 8);
		TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(
				getSession(), nodeid, tonext);
		if (nextnode == null) {
			this.message = "下一个节点为空,请联系管理员";
			return "message";
		}

		this.message = "案件重新转入到入口案件成功";
		this.btnvalue = "提交到下一节点:"
				+ nextnode.getNodename();
		
		this.nodeid=nextnode.getNodeid();
		
		return "flowmessage";

	}

	private String tonext;

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

	@Override
	public String input() throws Exception {
		return INPUT;
	}
}