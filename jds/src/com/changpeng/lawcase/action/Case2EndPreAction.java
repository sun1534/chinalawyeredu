/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.util.LawcaseUtil;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 转结案,只是将案件转为结案中状态,这里录入案件进度备注信息吧..
 * 最后还需要总经理的审批以及总裁的审批
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class Case2EndPreAction extends WorkFlowAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		lawcase.setStatusid(20);
		lawcase.setPlanlogs(planlogs);
		getSession().update(lawcase);

		String tonext = "";
		TwflNode nextnode = LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
		if (nextnode == null) {
			this.message = "下一个节点为空,请联系管理员";
			return "message";
		}
		
//		this.remarks=com.changpeng.lawcase.util.StringUtil.str2hexstr(planlogs);
		
		super.saveOperlog("台帐转结案:"+planlogs, 8);
		this.nodeid = nextnode.getNodeid();
		this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
		this.message = "转结案成功,请提交到总经理进行审批";
		// return SUCCESS;
		return "flowmessage";
	}
	private String planlogs;
	
	
	
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


	public String input()throws Exception{
		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		this.planlogs=lawcase.getPlanlogs();
		
		
		return INPUT;
	}
}