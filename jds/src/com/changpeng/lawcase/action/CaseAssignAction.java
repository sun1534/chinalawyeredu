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
import com.changpeng.lawcase.service.LawcaseService;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.system.model.TsysRole;
import com.sxit.workflow.model.TwflDirection;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 将多个案件分配给选中的某些人
 * 
 * @author 华锋 Oct 26, 2009-9:25:13 PM
 * 
 */
public class CaseAssignAction extends WorkFlowAction {

	private long userid;

	/**
	 * @return the userid
	 */
	public long getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}

	/**
	 * 1是分配诉讼承办人2是分配执行承办人
	 */
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

		System.out.println("caseid::::::::" + caseid);

		if (caseid == 0) {

			this.message = "您没有选择任何案件,请返回";
			this.nextpage = "javascript:history.go(-1)";
			return "message";
		}

		// 页面判断，如果已经分配了，则不再显示那个选择框

		String username = com.changpeng.lawcase.util.CommanDatas.USER_ID_NAME.get(userid);

		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		if (assigntype == 1) {
			// this.userid=lawcase.getSusongworkid();
			lawcase.setStatusid(1); //案件装为入口案件
			lawcase.setSusongworkid(userid);
			lawcase.setSusongworkname(username);
			lawcase.setIsqisu(1);// 设置为已起诉
			this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr("将案件分配给诉讼承办人:" + username);
			this.message = "诉讼案件分配给诉讼承办人:" + lawcase.getSusongworkname() + "成功";
			this.saveOperlog("总经理分配案件给诉讼承办人:" + lawcase.getSusongworkname(), 8);
			com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "ASSIGNDATE", dfyyyymmdd
					.format(new java.util.Date()));

		} else if (assigntype == 2) {//分配了执行承办人，这里对于诉讼来说，就是阶段性已结案件
			// this.userid=lawcase.getZhixingworkid();
			lawcase.setStatusid(11);  //案件设置为阶段性结案,对诉讼承办人来说的是,但实际上已经还算是存量案件
			lawcase.setZhixingworkid(userid);
			lawcase.setZhixingworkname(username);
			this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr("将案件分配给执行承办人:" + username);
			this.message = "诉讼案件分配给执行承办人:" + lawcase.getZhixingworkname() + "成功";
			this.saveOperlog("总经理分配案件给执行承办人:" + lawcase.getZhixingworkname(), 8);
			com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "ASSIGNZHIXINGDATE",
					dfyyyymmdd.format(new java.util.Date()));

		}
		/**
		 * 记录案件的分配记录情况,也就是分配给了哪些个律师
		 */
//		com.changpeng.lawcase.service.LawcaseService.saveAssignHistory(getSession(), caseid, assigntype, userid, this.curuser.getUserid());
		getSession().update(lawcase);

		TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), lawcase.getNodeid(),
				tonext);
		if (nextnode == null) {
			this.message = "下一个节点为空,请联系管理员";
			return "message";
		}
		this.nodeid = nextnode.getNodeid();

		this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
		return "flowmessage";
	}

	@Override
	public String input() throws Exception {
		// 角色402代表诉讼案件承办人
		TsysRole role = (TsysRole) getSession().get(TsysRole.class, 402);
		if (role != null)
			roleusers = role.getTsysUserRoles();
		else
			roleusers = new HashSet();

	
		System.out.println("roleusers:::::::::" + roleusers);
		return INPUT;
	}

	private Set roleusers;

	/**
	 * @return the roleusers
	 */
	public Set getRoleusers() {
		return roleusers;
	}
}