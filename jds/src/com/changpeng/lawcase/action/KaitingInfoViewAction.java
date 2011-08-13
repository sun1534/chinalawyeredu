/**
 * 
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawCaseJixiao;
import com.changpeng.lawcase.model.TlawKaitinginfo;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawLianinfo;
import com.changpeng.lawcase.service.LawcaseService;
import com.changpeng.lawcase.util.CommanDatas;
import com.changpeng.lawcase.workflow.WorkFlowAction;

/**
 * 
 * 
 * 台帐管理有录入立案信息等
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class KaitingInfoViewAction extends WorkFlowAction {

	private static java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private int chuanpiaojixiao;

	/**
	 * @return the chuanpiaojixiao
	 */
	public int getChuanpiaojixiao() {
		return chuanpiaojixiao;
	}

	/**
	 * @param chuanpiaojixiao
	 *            the chuanpiaojixiao to set
	 */
	public void setChuanpiaojixiao(int chuanpiaojixiao) {
		this.chuanpiaojixiao = chuanpiaojixiao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		// TODO Auto-generated method stub

		this.kaiting = (TlawKaitinginfo) getSession().get(TlawKaitinginfo.class, caseid);

		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		this.lianinfo = (TlawLianinfo) getSession().get(TlawLianinfo.class, caseid);
		if (lawcase == null) {
			this.message = "该案件信息不存在,请返回";
			return "message";
		}
		if (lianinfo == null) {
			this.message = "案件立案信息不存在";
			return "message";
		}

		if (kaiting == null) {
			this.message = "案件开庭信息不存在";
			return "message";
		}

		System.out.println("this.kaiting::::::" + this.kaiting);

		return SUCCESS;
	}

	private String type = "";

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	private boolean isedit;

	private TlawKaitinginfo kaiting;

	public TlawKaitinginfo getKaiting() {
		if (kaiting == null)
			kaiting = (TlawKaitinginfo) get("kaiting");
		return kaiting;
	}

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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
