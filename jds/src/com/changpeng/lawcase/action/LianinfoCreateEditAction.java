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
import com.changpeng.lawcase.model.TlawLianinfo;
import com.changpeng.lawcase.service.LawcaseService;
import com.changpeng.lawcase.util.CommanDatas;
import com.changpeng.lawcase.workflow.WorkFlowAction;
import com.sxit.workflow.model.TwflNode;

/**
 * 
 * 
 * 台帐管理有录入立案信息等
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class LianinfoCreateEditAction extends WorkFlowAction {

	private static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

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
		TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		this.lianinfo.setCreateuserid(this.curuser.getUserid());
		this.lianinfo.setCreateusername(this.curuser.getUsername());
		this.lianinfo.setCaseid(caseid);
		lianinfo.setLastupdate(timestamp);
		if (isedit) {
			getSession().update(lianinfo);
			this.message = "立案缴费信息修改成功";
			super.saveOperlog("修改立案缴费信息", 2);
		} else {
			lianinfo.setLianid(caseid);
			this.lianinfo.setCreatetime(timestamp);
			getSession().save(lianinfo);
			this.message = "立案缴费信息新增成功";
			super.saveOperlog("录入立案缴费信息", 1);
			
			lawcase.setIslian(1);//已经立案
			getSession().update(lawcase);
		}
		
		com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "liandate",lianinfo.getLiandate());
		com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "jiaofeidate",lianinfo.getFeedate());
		//到调解的时间
		com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(), caseid, "totiaojiedate",dfyyyymmdd.format(new Date()));
	
		// 绩效==1的话,同时记录下诉讼承办人的缴费以及立案绩效
		// if (!isedit&&lianinfo.getJixiao() == 1) {
		if (lianinfo.getJixiao() == 1) {

			// 这里要判断这个人是否已经记录了绩效，如果记录了则只更新下记录时间等
			this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
			long workid = lawcase.getSusongworkid();

			TlawCaseJixiao jixiao = new TlawCaseJixiao();
			jixiao.setActionid(actionid);
			jixiao.setNodeid(nodeid);
			jixiao.setUserid(workid);
			jixiao.setJixiaoid(1001);
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
			this.message = "立案缴费信息记录成功";
			this.btnvalue = "提交到下一节点:" + nextnode.getNodename();
			
			
			
			//这里转为存量案件
			lawcase.setStatusid(10); 
			lawcase.setIsjiaofei(1);
			getSession().update(lawcase);

			return "flowmessage";
		} else {
			this.nextpage = "caseDoView.action?caseid=" + caseid;
			return SUCCESS;
		}
	}

	public String input() throws Exception {
		this.lianinfo = (TlawLianinfo) getSession().get(TlawLianinfo.class, caseid);
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		if (this.lianinfo != null) {
			isedit = true;
		} else {
			lianinfo = new TlawLianinfo();
			lianinfo.setCaseid(caseid);
			lianinfo.setLianid(caseid);
		}

		set("lianinfo", lianinfo);
		return "input";
	}

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	private boolean isedit;

	private TlawLianinfo lianinfo;

	public TlawLianinfo getLianinfo() {
		if (lianinfo == null)
			lianinfo = (TlawLianinfo) get("lianinfo");
		return lianinfo;
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

}
