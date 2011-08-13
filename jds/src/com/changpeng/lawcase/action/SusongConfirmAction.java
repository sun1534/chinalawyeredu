/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawSusong;
import com.changpeng.lawcase.util.Clob2String;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 审批管理员对诉状材料进行审批，这里用tlawoperlog专门来记录这个审批情况
 * 
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class SusongConfirmAction extends com.changpeng.lawcase.workflow.WorkFlowAction {
	private String canedit;

	public void setCanedit(String edit) {
		this.canedit = edit;
	}

	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		// ServletActionContext c=null;

		// zh
		susong.setConfirmid(this.curuser.getUserid());
		susong.setConfirmname(this.curuser.getUsername());

		if (canedit != null && !canedit.equals("")) {
			if (thecontent == null) {
				this.message = "材料内容不能为空,请务必输入";
				return "message";
			}
			susong.setThecontent(Hibernate.createClob(thecontent));
		}

		susong.setConfirmtime(new java.sql.Timestamp(System.currentTimeMillis()));
		if (tonext.equals("pass")) {
			susong.setStatusid(1);// 审批通过
			this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr("审批通过");
			this.message = "诉状材料审批处理成功：审批通过";
			super.saveOperlog("审批通过诉状材料", 8);

			com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "susongconfirmdate",
					dfyyyymmdd.format(new java.util.Date()));

		} else { // 不pass的话，这个人必须是写诉讼的那个人，否则就可以自己去选
			this.userid = susong.getCreateuserid();
			susong.setStatusid(2);// 审批不通过
			this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr("审批不通过:" + susong.getCfmcontent());
			this.message = "诉状材料审批处理成功：审批不通过";
			super.saveOperlog("审批不通过诉状材料:" + susong.getCfmcontent(), 8);
		}
		getSession().update(susong);

		TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
		if (nextnode == null) {
			this.message = "下一个节点为空,请联系管理员";
			return "message";
		}
		this.nodeid = nextnode.getNodeid();

		this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
		return "flowmessage";
		// return SUCCESS;
	}

	private long userid;

	public long getUserid() {
		return this.userid;
	}

	public String input() throws Exception {
		this.tonext = "pass";
		this.susong = (TlawSusong) getSession().get(TlawSusong.class, caseid);
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		if (this.susong != null) {
			this.thecontent = Clob2String.clob2String(susong.getThecontent());
		} else {
			this.message = "对应案件的诉讼材料还不存在,无法审批";
			return "message";
		}
		set("susong", susong);
		return "input";
	}

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	private String thecontent;

	private com.changpeng.lawcase.model.TlawSusong susong;

	public TlawSusong getSusong() {
		if (susong == null)
			susong = (TlawSusong) get("susong");
		return susong;
	}

	/**
	 * @return the thecontent
	 */
	public String getThecontent() {
		return thecontent;
	}

	/**
	 * @param thecontent
	 *            the thecontent to set
	 */
	public void setThecontent(String thecontent) {
		this.thecontent = thecontent;
	}

}
