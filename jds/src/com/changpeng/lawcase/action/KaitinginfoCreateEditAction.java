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
public class KaitinginfoCreateEditAction extends WorkFlowAction {

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

		// ServletActionContext c=null;
		Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());

		this.kaiting.setModifyuserid(this.curuser.getUserid());
		this.kaiting.setModifyusername(this.curuser.getUsername());
		this.kaiting.setCaseid(caseid);
		this.kaiting.setModifytime(timestamp);
		if (isedit) {
			getSession().update(kaiting);
			if (type != null && type.equals("caijue")) {
				this.message = "裁决/判决信息修改成功";
				super.saveOperlog("修改裁决/判决信息", 2);
			} else if (type != null && type.equals("jixiao")) {
				this.message = "开庭资料准备绩效信息修改成功";
				super.saveOperlog("修改案件的开庭资料准备绩效信息", 2);
			} else {
				this.message = "开庭信息修改成功";
				super.saveOperlog("修改开庭信息", 2);
			}
		} else {
			kaiting.setKaitingid(caseid);
			getSession().save(kaiting);
			if (type != null && type.equals("caijue")) {
				this.message = "裁决/判决信息新增成功";
				super.saveOperlog("录入裁决/判决信息", 1);
			} else if (type != null && type.equals("jixiao")) {
				this.message = "录入案件的开庭资料准备绩效成功";
				super.saveOperlog("录入案件的开庭资料准备绩效", 2);
			} else {
				
				
				//录入了开庭信息的话，就设置为已登记
				//这里转为存量案件
				TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
				lawcase.setIsdengji(1);
				getSession().update(lawcase);
				
				this.message = "开庭信息新增成功";
				super.saveOperlog("录入开庭信息", 1);
			}
		}
		//记录开庭资料准备绩效,加入到那个case_jixiao的表中
		if(type!=null&&type.equals("jixiao")){
			TlawLawcase lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
			long workid = lawcase.getSusongworkid();
			TlawCaseJixiao jixiao = new TlawCaseJixiao();
			jixiao.setActionid(actionid);
			jixiao.setNodeid(nodeid);
			jixiao.setUserid(workid);   //给诉讼承办人
			jixiao.setJixiaoid(1005);
			jixiao.setThenumber(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getJixiaofenshu());
			jixiao.setLogtime(df.format(timestamp));
			jixiao.setRemarks("开庭资料准备绩效");
			jixiao.setCaseid(caseid);
			jixiao.setWhylog(kaiting.getJixiaowhy());
			LawcaseService.saveJixiao(getSession(), jixiao, this.curuser);
			
			
			
		}

		// 不管如何,都修改先那个立案信息里的数据

		getSession().update(lianinfo);

		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "liandate",kaiting.getLiandate());
		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "liandate",kaiting.getLiandate());
		// com.changpeng.lawcase.service.LawcaseService.updateStageDate(getSession(),
		// caseid, "totiaojiedate",dfyyyymmdd.format(new Date()));

		// 绩效==1的话,记录领开庭传票绩效，不知道记在谁的身上，现在算在诉讼承办人吧
		// if (!isedit&&kaiting.getJixiao() == 1) {
		if (chuanpiaojixiao == 1) {

			// 这里要判断这个人是否已经记录了绩效，如果记录了则只更新下记录时间等
			this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
			long workid = lawcase.getSusongworkid();

			TlawCaseJixiao jixiao = new TlawCaseJixiao();
			jixiao.setActionid(actionid);
			jixiao.setNodeid(nodeid);
			jixiao.setUserid(workid);
			jixiao.setJixiaoid(1002);
			jixiao.setThenumber(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getJixiaofenshu());
			jixiao.setLogtime(df.format(timestamp));
			jixiao.setRemarks("录入开庭信息时为诉讼承办人记录领开庭传票绩效");
			jixiao.setCaseid(caseid);
			jixiao.setWhylog(CommanDatas.ALLJIXIAOS.get(jixiao.getJixiaoid()).getWhylog());
			LawcaseService.saveJixiao(getSession(), jixiao, this.curuser);

		}
		this.nextpage = "caseDoView.action?caseid=" + caseid;
		return SUCCESS;
	}

	public String input() throws Exception {
		this.kaiting = (TlawKaitinginfo) getSession().get(TlawKaitinginfo.class, caseid);
		this.lawcase = (TlawLawcase) getSession().get(TlawLawcase.class, caseid);
		lianinfo = (TlawLianinfo) getSession().get(TlawLianinfo.class, caseid);
		if (lawcase == null) {
			this.message = "该案件信息不存在,请返回";
			return "message";
		}
		if (lianinfo == null) {
			this.message = "案件立案信息还不存在,请先录入案件立案信息";
			return "message";
		}
		System.out.println("this.kaiting::::::" + this.kaiting);
		if (this.kaiting != null) {
			isedit = true;
		} else {
			kaiting = new TlawKaitinginfo();
			kaiting.setCaseid(caseid);
			kaiting.setKaitingtime("9:30");
			kaiting.setKaitingid(caseid);
			// kaiting.setJigou(lianinfo.getJigou());
			// kaiting.setFaguan(lianinfo.getFaguan());
			// kaiting.setFaguanlinkphone(lianinfo.getFaguanlinkphone());
			// kaiting.setZhuli(lianinfo.getZhuli());
			// kaiting.setZhulilinkphone(lianinfo.getZhulilinkphone());

		}

		set("kaiting", kaiting);
		set("lianinfo", lianinfo);
		if (type == null || type.equals(""))
			return "input";
		else if (type.equals("caijue"))
			return "caijue";
		else if (type.equals("jixiao"))
			return "jixiao";
		return "input";
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
