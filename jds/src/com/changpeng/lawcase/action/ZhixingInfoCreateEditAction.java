/**
 * 
 */
package com.changpeng.lawcase.action;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.changpeng.lawcase.model.TlawCaseJixiao;
import com.changpeng.lawcase.model.TlawLawcase;
import com.changpeng.lawcase.model.TlawZhixingInfo;
import com.changpeng.lawcase.service.LawcaseService;
import com.changpeng.lawcase.util.CommanDatas;
import com.changpeng.lawcase.workflow.WorkFlowAction;

/**
 * 
 * 
 * 台帐管理有录入执行立案信息等
 * 
 * @author 华锋 Oct 24, 2009-9:18:18 AM
 * 
 */
public class ZhixingInfoCreateEditAction extends WorkFlowAction {

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

		this.zhixinginfo.setModifyuserid(this.curuser.getUserid());
		this.zhixinginfo.setModifyusername(this.curuser.getUsername());
		this.zhixinginfo.setCaseid(caseid);
		zhixinginfo.setModifytime(timestamp);
		if (isedit) {
			getSession().update(zhixinginfo);

			super.saveOperlog("修改立案缴费信息", 2);
		} else {
			getSession().save(zhixinginfo);

			super.saveOperlog("录入立案缴费信息", 1);
		}

		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "liandate",zhixinginfo.getLiandate());
		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "liandate",zhixinginfo.getLiandate());
		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "totiaojiedate",dfyyyymmdd.format(new Date()));

		// 绩效==1的话,同时记录下执行承办人的缴费以及立案绩效
		// if (!isedit&&zhixinginfo.getJixiao() == 1) {
		if (isjixiao == 1) {

			// 这里要判断这个人是否已经记录了绩效，如果记录了则只更新下记录时间等
			this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
			long workid = lawcase.getZhixingworkid();

			TlawCaseJixiao jixiao = new TlawCaseJixiao();
			jixiao.setActionid(actionid);
			jixiao.setNodeid(nodeid);
			jixiao.setUserid(workid);
			jixiao.setJixiaoid(1006); // 1006为执行承办人的制作诉状和立案绩效
			jixiao.setThenumber(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getJixiaofenshu());
			jixiao.setLogtime(df.format(timestamp));
			jixiao.setRemarks("录入执行立案信息时为执行承办人加入的制作诉状及立案绩效");
			jixiao.setCaseid(caseid);
			jixiao.setWhylog(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getWhylog());
			LawcaseService.saveJixiao(getSession(), jixiao, this.curuser);

//			TwflNode nextnode = com.changpeng.lawcase.util.LawcaseUtil.getNextNode(getSession(), nodeid, tonext);
//			if (nextnode == null) {
//				this.message = "下一个节点为空,请联系管理员";
//				return "message";
//			}
//			this.nodeid = nextnode.getNodeid();
			this.message = "执行立案信息以及制作诉状和立案绩效记录成功";
//			this.btnvalue = "提交到下一节点:" + nextnode.getNodename();

//			return "flowmessage";
		} else {
			this.message = "执行立案信息记录成功";
		}
		this.nextpage = "caseDoView.action?caseid=" + caseid;
		return SUCCESS;
	}

	public String input() throws Exception {
		this.zhixinginfo = (TlawZhixingInfo) getSession().get(TlawZhixingInfo.class, caseid);
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);

		if (this.zhixinginfo != null) {
			isedit = true;
		} else {
			zhixinginfo = new TlawZhixingInfo();
			zhixinginfo.setCaseid(caseid);
		}

		set("zhixinginfo", zhixinginfo);
		return "input";
	}

	private TlawLawcase lawcase;

	public TlawLawcase getLawcase() {
		return this.lawcase;
	}

	private boolean isedit;

	private TlawZhixingInfo zhixinginfo;

	public TlawZhixingInfo getZhixinginfo() {
		if (zhixinginfo == null)
			zhixinginfo = (TlawZhixingInfo) get("zhixinginfo");
		return zhixinginfo;
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
	 * @param isjixiao
	 *            the isjixiao to set
	 */
	public void setIsjixiao(int isjixiao) {
		this.isjixiao = isjixiao;
	}
	
	private static Map<Integer,Integer> MONTHTS=new LinkedHashMap<Integer,Integer>();
	static{
		for(int i=1;i<=12;i++)
			MONTHTS.put(i,i);
	}
	public Map getMonths(){
		return MONTHTS;
	}

}
