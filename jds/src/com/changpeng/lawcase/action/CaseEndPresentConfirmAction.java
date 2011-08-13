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
 * 总经理审批结案
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class CaseEndPresentConfirmAction extends WorkFlowAction {
	private String cfmcontent;
	public void setCfmcontent(String content){
		this.cfmcontent=content;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

//		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
//		lawcase.setStatusid(20);
//		getSession().update(lawcase);

		String tonext = "";
		TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
		if (nextnode == null) {
			this.message = "下一个节点为空,请联系管理员";
			return "message";
		}
		
		super.saveOperlog("总裁审批结案:"+cfmcontent, 8);
		
		this.nodeid = nextnode.getNodeid();
		this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
		this.message = "审批意见录入成功,请提交台帐进行最后的结案动作";
		
		this.remarks=com.changpeng.lawcase.util.StringUtil.str2hexstr(cfmcontent);
		// return SUCCESS;
		return "flowmessage";
	}
}