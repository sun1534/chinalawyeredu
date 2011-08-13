/**
 * 
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawCaseJixiao;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawPingguInfo;
import com.changpeng.lawcase.service.LawcaseService;
import com.changpeng.lawcase.util.CommanDatas;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 
 * 台帐管理有录入评估信息等
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class PingguInfoCreateEditAction extends WorkFlowAction {

	private static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private int isjixiao;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		// ServletActionContext c=null;
		Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

		this.pingguinfo.setModifyuserid(this.curuser.getUserid());
		this.pingguinfo.setModifyusername(this.curuser.getUsername());
		this.pingguinfo.setCaseid(caseid);
		pingguinfo.setModifytime(timestamp);
		if (isedit) {
			getSession().update(pingguinfo);
			this.message = "执行阶段评估信息修改成功";
			super.saveOperlog("修改立案缴费信息", 2);
		} else {
			getSession().save(pingguinfo);
			this.message = "立案缴费信息新增成功";
			super.saveOperlog("录入立案缴费信息", 1);
		}
		
//		com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "liandate",pingguinfo.getLiandate());
//		com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "liandate",pingguinfo.getLiandate());
//		com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "totiaojiedate",dfyyyymmdd.format(new Date()));
	
		// 绩效==1的话,同时记录下诉讼承办人的缴费以及立案绩效
		// if (!isedit&&pingguinfo.getJixiao() == 1) {
		if (isjixiao == 1) {

			// 这里要判断这个人是否已经记录了绩效，如果记录了则只更新下记录时间等
			this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
			long workid = lawcase.getZhixingworkid();

			TlawCaseJixiao jixiao = new TlawCaseJixiao();
			jixiao.setActionid(actionid);
			jixiao.setNodeid(nodeid);
			jixiao.setUserid(workid);
			jixiao.setJixiaoid(1007);
			jixiao.setThenumber(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getJixiaofenshu());
			jixiao.setLogtime(df.format(timestamp));
			jixiao.setRemarks("评估信息录入时候同时记录的执行承办人的评估绩效");
			jixiao.setCaseid(caseid);
			jixiao.setWhylog(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getWhylog());
			// jixiao.setCreatetime(new
			// java.sql.Timestamp(System.currentTimeMillis()));
			// jixiao.setCreateuserid(this.curuser.getUserid());
			// jixiao.setCreateusername(this.curuser.getUsername());
			LawcaseService.saveJixiao(getSession(), jixiao, this.curuser);

//			TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
//			if (nextnode == null) {
//				this.message = "下一个节点为空,请联系管理员";
//				return "message";
//			}
//			this.nodeid = nextnode.getNodeid();
			this.message = "执行阶段评估信息并执行承办人的评估绩效记录成功";
//			this.btnvalue = "提交到下一节点:" + nextnode.getNodename();

//			return "flowmessage";
		} else {
			this.message = "执行阶段评估信息记录成功";
			
		}
		this.nextpage = "caseDoView.action?caseid=" + caseid;
		return SUCCESS;
	}

	public String input() throws Exception {
		this.pingguinfo = (TlawPingguInfo) getSession().get(TlawPingguInfo.class, caseid);
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		if (this.pingguinfo != null) {
			isedit = true;
		} else {
			pingguinfo = new TlawPingguInfo();
			pingguinfo.setCaseid(caseid);
		
		}

		set("pingguinfo", pingguinfo);
		return "input";
	}

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	private boolean isedit;

	private TlawPingguInfo pingguinfo;

	public TlawPingguInfo getPingguinfo() {
		if (pingguinfo == null)
			pingguinfo = (TlawPingguInfo) get("pingguinfo");
		return pingguinfo;
	}

	/**
	 * @return the isedit
	 */
	public boolean getIsedit() {
		return isedit;
	}

	/**
	 * @param isedit
	 *            the isedit to set
	 */
	public void setIsedit(boolean isedit) {
		this.isedit = isedit;
	}

	/**
	 * @return the isjixiao
	 */
	public int getIsjixiao() {
		return isjixiao;
	}

	/**
	 * @param isjixiao the isjixiao to set
	 */
	public void setIsjixiao(int isjixiao) {
		this.isjixiao = isjixiao;
	}

}
