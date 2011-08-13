/**
 * 
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawPaimaiInfo;
import com.changpeng.lawcase.workflow.WorkFlowAction;

/**
 * 
 * 
 * 台帐管理有录入拍卖信息等
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class PaimaiInfoCreateEditAction extends WorkFlowAction {

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

		System.out.println("paimaiinfo==="+paimaiinfo);
		
		this.paimaiinfo.setModifyuserid(this.curuser.getUserid());
		this.paimaiinfo.setModifyusername(this.curuser.getUsername());
		this.paimaiinfo.setCaseid(caseid);
		paimaiinfo.setModifytime(timestamp);
		if (isedit) {
			getSession().update(paimaiinfo);
			super.saveOperlog("修改拍卖信息", 2);
		} else {
			getSession().save(paimaiinfo);
			super.saveOperlog("录入拍卖信息", 1);
		}
		this.message = "案件拍卖信息记录成功";
		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "liandate",paimaiinfo.getLiandate());
		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "liandate",paimaiinfo.getLiandate());
		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "totiaojiedate",dfyyyymmdd.format(new Date()));

		// 绩效==1的话,同时记录下诉讼承办人的缴费以及立案绩效
		// if (!isedit&&paimaiinfo.getJixiao() == 1) {

		this.nextpage = "caseDoView.action?caseid=" + caseid;
		return SUCCESS;

	}

	public String input() throws Exception {
		this.paimaiinfo = (TlawPaimaiInfo) getSession().get(TlawPaimaiInfo.class, caseid);
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		if (this.paimaiinfo != null) {
			isedit = true;
		} else {
			paimaiinfo = new TlawPaimaiInfo();
			paimaiinfo.setCaseid(caseid);

		}

		set("paimaiinfo", paimaiinfo);
		return "input";
	}

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	private boolean isedit;

	private TlawPaimaiInfo paimaiinfo;

	public TlawPaimaiInfo getPaimaiinfo() {
		if (paimaiinfo == null)
			paimaiinfo = (TlawPaimaiInfo) get("paimaiinfo");
		return paimaiinfo;
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
