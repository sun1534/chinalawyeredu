/**
 * 
 */
package com.changpeng.lawcase.action;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawZhixing;
import com.changpeng.lawcase.util.Clob2String;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 审批管理员对诉状材料进行审批，这里用tlawoperlog专门来记录这个审批情况
 * 
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class ZhixingConfirmAction extends WorkFlowAction {
	private String canedit;
	public void setCanedit(String edit){
		this.canedit=edit;
	}
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		// ServletActionContext c=null;

		// zh
		if(canedit!=null&&!canedit.equals("")){
			if(thecontent==null){
				this.message="材料内容不能为空,请务必输入";	
				return "message";
				}
			zhixing.setThecontent(Hibernate.createClob(thecontent));
		}
		System.out.println("this.curuser.getUserid():::"+this.curuser.getUserid());
		zhixing.setConfirmid(this.curuser.getUserid());
		zhixing.setConfirmname(this.curuser.getUsername());

		zhixing.setConfirmtime(new java.sql.Timestamp(System.currentTimeMillis()));
		if (tonext.equals("pass")) {
			zhixing.setStatusid(1);// 审批通过
			this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr("审批通过");
			this.message = "诉状材料审批处理成功：审批通过";
			super.saveOperlog("审批通过执行材料:"+zhixing.getCfmcontent(), 8);
			com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "zhixingconfirmdate",dfyyyymmdd.format(new java.util.Date()));

		} else {
			this.userid=zhixing.getCreateuserid();
			zhixing.setStatusid(2);// 审批不通过
			this.remarks = com.changpeng.lawcase.util.StringUtil.str2hexstr("审批不通过:" + zhixing.getCfmcontent());
			this.message = "诉状材料审批处理成功：审批不通过";
			super.saveOperlog("审批不通过执行材料:"+zhixing.getCfmcontent(), 8);
		}
		getSession().update(zhixing);

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

	public String input() throws Exception {
		this.tonext = "pass";
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		this.zhixing = (TlawZhixing) getSession().get(TlawZhixing.class, caseid);
		if (this.zhixing != null) {
			this.thecontent = Clob2String.clob2String(zhixing.getThecontent());
		} else {
			this.message = "对应案件的执行材料还不存在,无法审批";
			return "message";
		}
		set("zhixing", zhixing);
		return "input";
	}
	private TlawLawcase lawcase;
	public TlawLawcase getLawcase() {
		return this.lawcase;
	}
	private long userid;
	public long getUserid(){
		return this.userid;
	}
	private String thecontent;

	private com.changpeng.lawcase.model.TlawZhixing zhixing;

	public TlawZhixing getZhixing() {
		if (zhixing == null)
			zhixing = (TlawZhixing) get("zhixing");
		return zhixing;
	}

	/**
	 * @return the thecontent
	 */
	public String getThecontent() {
		return thecontent;
	}
	/**
	 * @param thecontent the thecontent to set
	 */
	public void setThecontent(String thecontent) {
		this.thecontent = thecontent;
	}

}
