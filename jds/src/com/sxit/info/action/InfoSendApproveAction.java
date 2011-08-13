/**
 * 
 */
package com.sxit.info.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;

import com.sxit.common.action.AbstractAction;
import com.sxit.info.model.TinfInfo;
import com.sxit.info.model.TinfSet;
import com.sxit.info.util.Power;
import com.sxit.wait.util.WaitWork;
import com.sxit.system.model.TsysUser;

/**
 * @author sg
 * @TODO 提交审批
 * @createtime 2008-9-4
 * @version v1.0
 */
public class InfoSendApproveAction extends AbstractAction {
	
	private TinfInfo info;
	
	public InfoSendApproveAction(){
		rights = "inf3,1";
	}

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {
		String waitidstring ="";
		getSession().update(info);
		List approvelist = Power.whoPower(getSession(), "approve" ,info.getTinfType().getTypeid());
		for (int i = 0; i < approvelist.size(); i++) {
			int waitid = 0;
			TinfSet infset = (TinfSet) approvelist.get(i);
			TsysUser waitUser = (TsysUser) getSession().get(TsysUser.class,
					infset.getUserid());
			TsysUser curuser = (TsysUser) get("curuser");
			String subject = "新提交信息[" + info.getInfoname() + "]待审批";
			String url = "../info/infoApprove.action?infoid="+info.getInfoid();
			String flowstep = "待批";
			String target = "";
			waitid = WaitWork.Sendwait(getSession(), waitUser, subject, url, 2, curuser
					.getUsername(), flowstep, target);
			waitidstring = waitidstring + waitid + ",";
		}
		info.setWaitid(waitidstring);
		set("info", info);
		nextpage="infoView.action?infoid="+info.getInfoid()+"&pagenumber=0";
		message = "提交成功！";
		return SUCCESS;
	}
	
	public TinfInfo getInfo() {
		if (info == null)
			info = (TinfInfo) get("info");
		return info;
	}
	
//	public void setInfo(TinfInfo info){
//		System.out.println("1111================");
//		this.info = info;
//	}

}
