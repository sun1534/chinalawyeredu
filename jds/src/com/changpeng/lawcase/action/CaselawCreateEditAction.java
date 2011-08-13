/**
 * 
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;
import java.text.DateFormat;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawJiekuanren;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawStageDate;
import com.sxit.common.action.AbstractAction;

/**
 * 
 * 
 * 单个的方式新增修改诉讼案件基本信息
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class CaselawCreateEditAction extends AbstractAction  {
	private static DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd");

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

		if (isedit) {
			lawcase.setBank(com.changpeng.lawcase.util.CommanDatas.CASE_BANKS.get(lawcase.getBankid()));
			getSession().update(lawcase);
			getSession().update(jiekuanren);
			this.message = "诉讼案件信息修改成功";
			com.changpeng.lawcase.service.LawcaseLogicService.saveOperlog(getSession(), lawcase.getCaseid(), 21001, "修改案件："+lawcase.getCaseid(), 2);

		} else {

			this.jiekuanren.setCreatetime(timestamp);
			this.jiekuanren.setCreateuserid(this.curuser.getUserid());
			this.jiekuanren.setCreateusername(this.curuser.getUsername());

			this.lawcase.setCreatetime(timestamp);
			this.lawcase.setCreateuserid(this.curuser.getUserid());
			this.lawcase.setCreateusername(this.curuser.getUsername());
			this.lawcase.setBank(com.changpeng.lawcase.util.CommanDatas.CASE_BANKS.get(lawcase.getBankid()));
			this.lawcase.setNodeid(21001); // 当前节点是刚新增
			this.lawcase.setHotman(this.curuser.getUserid());
			this.lawcase.setStatusid(-1);

			getSession().save(lawcase);
			jiekuanren.setLawcase(lawcase);
			jiekuanren.setCaseid(lawcase.getCaseid());
			getSession().save(jiekuanren);
			
			
			TlawStageDate stagedate=new TlawStageDate();
			stagedate.setCreatedate(df.format(lawcase.getCreatetime()));
			stagedate.setThedate(lawcase.getThedate());
			stagedate.setCaseid(caseid);
			stagedate.setLawcase(lawcase);
			getSession().save(stagedate);
			
			this.message = "诉讼案件新增成功";
			com.changpeng.lawcase.service.LawcaseLogicService.saveOperlog(getSession(), lawcase.getCaseid(), 21001, "新增案件："+lawcase.getCaseid(), 1);
		}
		
		System.out.println(this.message);
		
//		if (this.backurl != null && !"".equals(backurl))
//			this.nextpage = backurl;
//		else{
//			this.nextpage = "newcaseList.action";
//		}
		
		this.nextpage="caseDoView.action?caseid="+lawcase.getCaseid();;
		return SUCCESS;
	}

	public String input() throws Exception {
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		if (this.lawcase != null) {
			isedit = true;
			this.jiekuanren = this.lawcase.getJiekuanren();
			if (this.jiekuanren == null) {
				this.message = "案件的借款人数据有误,请联系技术支持人员";
				return "message";
			}

		} else {
			lawcase = new TlawLawcase();
			jiekuanren = new TlawJiekuanren();
		}

		set("jiekuanren", jiekuanren);
		set("lawcase", lawcase);
		return "input";
	}

	private long caseid;
	private boolean isedit;
	private String backurl;
	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		if (lawcase == null)
			lawcase = (TlawLawcase) get("lawcase");
		return lawcase;
	}

	private TlawJiekuanren jiekuanren;

	public TlawJiekuanren getJiekuanren() {
		if (jiekuanren == null)
			jiekuanren = (TlawJiekuanren) get("jiekuanren");
		return jiekuanren;
	}

	/**
	 * @return the caseid
	 */
	public long getCaseid() {
		return caseid;
	}

	/**
	 * @param caseid
	 *            the caseid to set
	 */
	public void setCaseid(long caseid) {
		this.caseid = caseid;
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
	 * @return the backurl
	 */
	public String getBackurl() {
		return backurl;
	}

	/**
	 * @param backurl
	 *            the backurl to set
	 */
	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

}
