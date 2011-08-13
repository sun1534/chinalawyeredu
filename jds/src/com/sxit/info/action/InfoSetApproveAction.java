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
import com.sxit.wait.model.TwatWait;
import com.sxit.wait.util.WaitWork;
import com.sxit.system.model.TsysUser;

/**
 * @author sg
 * @TODO  审批
 * @createtime 2008-9-4
 * @version v1.0
 */
public class InfoSetApproveAction extends AbstractAction {
	
	private TinfInfo info;
	
	public InfoSetApproveAction(){
		rights = "inf2,1";
	}

	/* (non-Javadoc)
	 * @see com.sxit.common.action.AbstractAction#go()
	 */
	@Override
	protected String go() throws JDBCException, HibernateException {

//		System.out.println("=-----------------------==========");
//		System.out.println("info==3============"+info.getStatusid());
//		System.out.println(info.getStatusid());
//		System.out.println(info.getInfoid());
		curuser = (TsysUser) get("curuser");
		int waitid;
		String waitidstring = info.getWaitid();
		if (waitidstring != null && !waitidstring.equals("")) {
			String[] waitids = waitidstring.split(",");
			for (int i = 0; i < waitids.length; i++) {
				waitid = Integer.valueOf(waitids[i]);
				WaitWork.EndWait(getSession(), waitid, curuser);
			}
		}
		
		waitidstring = "";
		if (info.getStatusid() == 3) {
			List approvelist = Power.whoPower(getSession(), "prom", info
					.getTinfType().getTypeid());
			for (int i = 0; i < approvelist.size(); i++) {

				TinfSet infset = (TinfSet) approvelist.get(i);
				TsysUser waitUser = (TsysUser) getSession().get(TsysUser.class,
						infset.getUserid());
				TsysUser curuser = (TsysUser) get("curuser");
				String subject = "新审批信息[" + info.getInfoname() + "]待发布";
				String url = "../info/infoProm.action?infoid="+info.getInfoid();
				String flowstep = "";
				String target = "";
				waitid = WaitWork.Sendwait(getSession(), waitUser, subject, url, 2,
						curuser.getUsername(), flowstep, target);
				waitidstring = waitidstring + waitid + ",";
			}
			info.setWaitid(waitidstring);
		}
		
		if(info.getStatusid() == 4){
			TsysUser waitUser = info.getCreateuser();
			String subject = "信息[" + info.getInfoname() + "]未通过审批";
			String url = "../info/infoView.action?infoid="+info.getInfoid();
			String flowstep = "";
			String target = "";
			WaitWork.Sendwait(getSession(), waitUser, subject, url, 2,
					curuser.getUsername(), flowstep, target);
		}
		
		getSession().update(info);
		nextpage="infoApproveList.action?pagenumber="+pagenumber;
		message = "提交成功！";
		return SUCCESS;
	}
	
	public TinfInfo getInfo() {
		if (info == null)
			info = (TinfInfo) get("info");
		return this.info;
	}
	

}
