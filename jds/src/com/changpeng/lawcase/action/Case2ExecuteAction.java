/**
 * CaseTiaojieListAction.java
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawCaseJixiao;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.service.LawcaseService;
import com.changpeng.lawcase.util.CommanDatas;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflNode;

/**
 * 审理阶段信息完成,转到执行阶段。 这里要求台帐管理员录入案件进度备注信息,记录诉讼承办人的签生效绩效
 * 
 * @author 华锋 Jan 10, 201010:22:29 PM
 * 
 */
public class Case2ExecuteAction extends WorkFlowAction {
	private static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private int isjixiao;
	private String planlogs;

	/**
	 * @param isjixiao
	 *            the isjixiao to set
	 */
	public void setIsjixiao(int isjixiao) {
		this.isjixiao = isjixiao;
	}

	/**
	 * @param planlogs
	 *            the planlogs to set
	 */
	public void setPlanlogs(String planlogs) {
		this.planlogs = planlogs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		String tonext = "";
		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		long workid = lawcase.getSusongworkid();
		lawcase.setPlanlogs(planlogs);
		getSession().update(lawcase);
		if (isjixiao == 1) {
			Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

			// 这里要判断这个人是否已经记录了绩效，如果记录了则只更新下记录时间等
			

			TlawCaseJixiao jixiao = new TlawCaseJixiao();
			jixiao.setActionid(actionid);
			jixiao.setNodeid(nodeid);
			jixiao.setUserid(workid);
			jixiao.setJixiaoid(1003);
			jixiao.setThenumber(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getJixiaofenshu());
			jixiao.setLogtime(df.format(timestamp));
			jixiao.setRemarks("录入立案缴费信息时为诉讼承办人加入制作诉状及立案绩效");
			jixiao.setCaseid(caseid);
			jixiao.setWhylog(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getWhylog());
			// jixiao.setCreatetime(new
			// java.sql.Timestamp(System.currentTimeMillis()));
			// jixiao.setCreateuserid(this.curuser.getUserid());
			// jixiao.setCreateusername(this.curuser.getUsername());
			LawcaseService.saveJixiao(getSession(), jixiao, this.curuser);

			TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
			if (nextnode == null) {
				this.message = "下一个节点为空,请联系管理员";
				return "message";
			}
			this.nodeid = nextnode.getNodeid();
			this.message = "案件进度备注信息并诉讼承办人签生效信息记录成功";

			this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
			return "flowmessage";

		} else {
			this.message = "案件进度备注信息记录成功";
			this.nextpage = "caseDoView.action?caseid=" + caseid;
			return SUCCESS;
		}
	}

	/**
	 * @return the planlogs
	 */
	public String getPlanlogs() {
		return planlogs;
	}

	public String input() {
		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		this.planlogs = lawcase.getPlanlogs();
		return INPUT;
	}

	/**
	 * @return the isjixiao
	 */
	public int getIsjixiao() {
		return isjixiao;
	}
}